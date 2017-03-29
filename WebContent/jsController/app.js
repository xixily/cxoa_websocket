(function($){
	var post = $.post;
	$.post = undefined;
	function post_mask(url, data, callback, dataType){
		post(url,data,function(data, textStatus, jqXHR){
			if(typeof data == "string"){
				try{
					data = eval("(" + data + ")");
				}catch(e){
					data = {msg: data};
				}
			}
			callback(data, textStatus, jqXHR);
		},dataType)
	}
	$.post = post_mask;
})($);
$.ajaxSetup({
    cache: true
});
var session = {
    logined : false,
    user : {},
	jiagou : [],
	menus:[],
//    testText : '我在这里存放全局缓存数据'
};
function SFservice(){
	var url1 = 'http://bspoisp.sit.sf-express.com:11080/bsp-oisp/sfexpressService';
	var url2 = 'https://bspoisp.sit.sf-express.com:11443/bsp-oisp/sfexpressService';
	var xmlHead = "<?xml version='1.0' encoding='UTF-8'?><Request service='服务名' lang='zh-CN'><Head>utf8</Head><Body>";
	var xmlEnd = "</Body></Request>";
	var xmlBody="<orderid>123456</orderid>"+"<mailno>755123456789,001123456789,002123456789</mailno>" + "<is_gen_bill_no>1</is_gen_bill_no>";
	var xml = xmlHead + xmlBody + xmlEnd;
	var checkword = 'j8DzkIFgmlomPt0aLuwU';
	var params = {};
	params.xml = xml;
	/**
	 * md5 加密 到 base64 加密
	 */
	var md5Ckey = $.md5(xml + checkword);
	var verifyCode = $.base64encode(md5Ckey);
	params.verifyCode = verifyCode;
	var action = {};
	action.url = url1;
	action.data =  params;
	sendActionE(action, function(result){
		console.log("result:%o",result);
	});
//	$.post(url1,params,function(result){
//	    console.log(result);
//	})
};
function generateWagesDate(){
	  var calendar = $('#calendar_wagesDate').calendar('options');
	    var year = calendar.year;
	    var month = calendar.month;
	    var lastDay = new Date(year,month,0);
	    var dayLength = lastDay.getDate();
	    var day = {};
	    var monthDay = session.monthDay =  [];
	    var lizhiDay = 0;
	    for(var i = 1;i<=dayLength;i++){
	        day = new Date(year,month - 1,i)
	        if(day.getDay()!=6&&day.getDay()!=0){
	        	lizhiDay ++;
	        }
	        var data = {};
	        data.date = day.getFullYear() + "." + (day.getMonth()<9?'0'+(day.getMonth()+1):day.getMonth()+1) + "." + day.getDate();
	        data.lizhiDay = lizhiDay;
	        data.ruzhiDay = 21 - lizhiDay;
	        monthDay.push(data);
	    }
}
function init(){
	$.post('employee/getCompany.action',{},function(result){
//		var result =  eval("(" + result + ")");
		if(result){
			session.companys = result;
		}
	})
}
var editIndex = undefined;
$(window).on('beforeunload', function (e) {
    if (session.logined){
//    	north.logoutFun();
//    	return '警告：请使用系统的退出按钮正常退出系统!';
    }
});
/**
 * 此login方法暂时弃用。
 */
function login(){
	$('#login_form').form('submit', {
		onSubmit: function(){
			var isValid = $(this).form('validate');
			if (!isValid){
				console.log('登陆校验失败！');
				$.messager.progress('close');	// hide progress bar while the form is invalid
			}
			return isValid;	// return false will stop the form submission
		},
		success: function(data_string){
			var data = $.parseJSON(data_string);
			console.log('登陆获取的data：o%', data);
			$.messager.progress('close');	// hide progress bar while submit successfully
			if(data.success){
				session.logined = data.success;
				session.user = data.obj;
				$('#main_body').css('display','');
				$('#login_dlg').dialog('close');
				initClickHandler();
				west.initWestTree();
				employee.employee.initEmployee();
			}
		}
	});
}
/**
 * @author dengxuefeng
 * @requires jquery.js easyui.js
 * @callback [data.data]<strong>form表单的元素值</strong>、[src]<strong>当前元素dom</strong>
 * 初始化
 */
function initClickHandler() {
    $(document).on("click", ".do_action", function(event) {
//        console.log('do_action!');
        event.preventDefault();//关闭默认事件
        event.stopImmediatePropagation();//停止冒泡
        var src = $(event.currentTarget);
        var action = src.attr("appaction");
        var frm = src.closest("form");
        if(!frm.form('validate')){
            console.log('form 表单校验失败！');
            return false;
        }
        var data = getDataOfForm(frm);
//        console.log('o%', data);
        if (data.succeed) {
            var actionHandler = eval(action);//jQuery.gloabEval()全局方法
            if (actionHandler) {
                actionHandler(data.data, src);
            } else {
                console.log('action handler [' + action + '] is not support!');
            }
        }
    });
}

/**
 * @author dengxuefeng
 * @requires jquery.js
 * 遍历form表单，取出input,textarea,select里面的值，返回data
 */
function getDataOfForm(form) {
    var succeed = true;
    var data = {};
    var inputs = form.find("input,textarea,select");
    if (inputs) {
        for (var i = 0; i < inputs.length; i++) {
            var ele = $(inputs[i]);
            var name = ele.attr("name");
            var value = $.trim(ele.val());
            if (value.indexOf("****") != -1 && ele.attr("shieldedTel")) {
                value = ele.attr("shieldedTel") || '';
            }
            if (ele[0].type == 'checkbox') {
                var isGroup = ele.attr("fieldgroup");
                if (isGroup) {
                    if (!ele[0].checked) {
                        continue;
                    }
                } else {
                    value = ele[0].checked;
                }
            } else if (ele[0].type == 'radio') {
                if (ele[0].checked)
                    data[name] = value; else continue;
            }
            if (!name)
                continue;

            if (name.match(/\w\[\]/g)) {
                name = name.substring(0, name.length - 2);
                if (!data[name]) {
                    data[name] = new Array();
                }
                data[name].push(value);
            } else if ((ele[0].tagName).toLowerCase() == "select" && ele[0].name == 'role') {
                data[name] = new Array();
                var options = ele[0].options;
                for (var j = 0, len = options.length; j < len; j++) {
                    data[name].push(options[j].value);
                }
            }else {
                data[name] = value;

            }
        }
    }
    return {
        succeed: succeed,
        data: data
    };
}

/**
 * @author dengxuef
 * 
 * @requires jQuery
 * 
 * @param action : {url:'',data:data}
 * @param callback 回调函数
 * @param method 默认get 
 * @param async 是否异步 
 * @param timeout 超时时间
 * 
 * @description 发送do_action 请求 
 */
function sendAction(action, callback, method, async, timeout) {
	async = async ? true : false;
	method = (method && method == "post") ? 'post' : 'get';
	timeout = timeout ? timeout : 20000;
	if (!session.logined) {
		alert('即将退出,请您先登陆！');
		north.logoutFun();
	}
	if (!action.url) {
		alert('您的请求不存在！');
		return false;
	}
	$.ajax({
		type : method,
		url : action.url,
		async : async,
		data : action.data,
		timeout : timeout,
		dataType : "json",
		success : function(data) {
			if (data.succeed) {
				callback(data.obj);
			} else {
				alert('请求失败！');
			}
		}
	})
}

/**
 * @author dengxuef
 * 
 * @requires jQuery
 * 
 * @param action : {url:'',data:data}
 * @param callback 回调函数
 * @param method 默认get 
 * @param async 是否异步 
 * @param timeout 超时时间
 * 
 * @description 发送do_action 请求 
 */
function sendActionE(action, callback, async, timeout) {
	async = async ? true : false;
	var method = "post";
	timeout = timeout ? timeout : 20000;
	if (!session.logined) {
		alert('即将退出,请您先登陆！');
		north.logoutFun();
	}
	if (!action.url) {
		alert('您的请求不存在！');
		return false;
	}
	$.ajax({
		url : action.url,
		type : 'post',
		async : async,
		data : action.data,
		timeout : timeout,
		dataType : "jsonp",
		jsonp:'callback',
//		jsonpCallback:'callback',
		beforeSend:function(XMLHttpRequest){
			console.log("XMLHttpRequest:%o",XMLHttpRequest);
			return true;
		},
		complete: function(XMLHttpRequest, textStatus){
			console.log("[complete]XMLHttpRequest:%o,textStatus:%o",XMLHttpRequest);
		},
		success : function(data, textStatus) {
			if (data.succeed) {
				callback(data.obj);
			} else {
				alert('请求失败！');
			}
			this;
		}
	})
}

/**
 * 
 * @param id
 */
function formartDate(id){
	 $("#" + id + " input[class='easyui-datebox datebox-f combo-f textbox-f']").each(function () {  
	        $(this).combobox({
	        	formatter:function(date){
	        		var y = date.getFullYear();
	        		var m = date.getMonth()+1;
	        		var d = date.getDate();
	        		return m+'.'+d+'.'+y;
	        	}
	        });
	    });  
//	easyui-datebox datebox-f combo-f textbox-f
}

/**
 * @author dengxf
 * @param id form表单的id
 * @param isDisabled boolean型，默认true
 * @description 对指定id下的form表单设置 disabled
 */
function disabledForm(formId, isDisabled){
	var attr="disable";  
    if(!isDisabled){  
       attr="enable"; 
       isDisabled = false;
    }else{
    	attr="disable"; 
    	isDisabled = true;
    }
    $("form[id='"+formId+"'] :text").attr("disabled",isDisabled);  
    $("form[id='"+formId+"'] textarea").attr("disabled",isDisabled);  
    $("form[id='"+formId+"'] select").attr("disabled",isDisabled);  
    $("form[id='"+formId+"'] :radio").attr("disabled",isDisabled);  
    $("form[id='"+formId+"'] :checkbox").attr("disabled",isDisabled);  
    $("form[id='"+formId+"'] :input").attr("disabled",isDisabled);  
      
    //禁用jquery easyui中的下拉选（使用input生成的combox）  
  
    $("#" + formId + " input[class='easyui-combobox combobox-f combo-f']").each(function () {  
        $(this).combobox(attr);
    });  
    
    $("#" + formId + ' input[class="easyui-combobox combobox-f combo-f textbox-f"]').each(function () {  
    	$(this).combobox(attr);
    });  
    
    $("#" + formId + ' input[class="easyui-switchbutton switchbutton-f"]').each(function () {  
    	$(this).switchbutton(attr);
    });  
      
    //禁用jquery easyui中的下拉选（使用select生成的combox）  
    $("#" + formId + " select[class='combobox-f combo-f']").each(function () {  
        $(this).combobox(attr);
    });  
    //禁用jquery easyui中的日期组件dataBox  
    $("#" + formId + " input[class='easyui-datebox datebox-f combo-f textbox-f']").each(function () {  
        $(this).datebox(attr);
    });  
//    //禁用jquery easyui中的linkbutton组件
//    $("#" + formId + " a[class~='easyui-linkbutton']").each(function () {  
//    	$(this).linkbutton({disabled:isDisabled})
//    });  
//    //禁用jquery easyui中的menubutton组件
//    $("#" + formId + " a[class~='easyui-menubutton']").each(function () {  
//    	$(this).menubutton(attr);
//    });  
}
function disabledButton(formId, isDisabled){
	var attr="disable";  
    if(!isDisabled){  
       attr="enable"; 
       isDisabled = false;
    }else{
    	attr="disable"; 
    	isDisabled = true;
    }
    //禁用jquery easyui中的linkbutton组件
    $("#" + formId + " a[class~='easyui-linkbutton']").each(function () {  
    	$(this).linkbutton({disabled:isDisabled})
    });
    //禁用jquery easyui中的menubutton组件
    $("#" + formId + " a[class~='easyui-menubutton']").each(function () {  
    	$(this).menubutton(attr);
    }); 
    }
function closeDialog(dom){
	var dialog = dom.closest(".easyui-dialog");
	dialog.dialog('close');
}

function clearForm(dom){
	var form = dom.closest("form");
	form.form('clear');
}

function submitForm(dom, callback,disable){
	var form = dom.closest("form");
//	console.log(form);
	form.form('submit',{
         onSubmit:function(){
        	 if($(this).form('enableValidation').form('validate')){
        		 if(disable){
        				dom.linkbutton('disable');
        			}
        		 return $(this).form('enableValidation').form('validate');
        	 }
         },
         success:function(result){
        	 var result =  eval("(" + result + ")");
        	 if(callback){
        		 callback(result);
        	 }else{
        		 $.messager.alert('消息', result.msg, 'info');
        	 }
         }
     });
}
/**
 * @author dengxf
 * 
 * @requires jQuery,EasyUI
 * 
 * panel关闭时回收内存，主要用于layout使用iframe嵌入网页时的内存泄漏问题
 */
$.fn.panel.defaults.onBeforeDestroy = function() {
	var frame = $('iframe', this);
	try {
		if (frame.length > 0) {
			for ( var i = 0; i < frame.length; i++) {
				frame[i].contentWindow.document.write('');
				frame[i].contentWindow.close();
			}
			frame.remove();
			if ($.browser.msie) {
				CollectGarbage();
			}
		}
	} catch (e) {
	}
};

/**
 * 时间datebox控件格式化
 */
$.fn.datebox.defaults.formatter = function(date){
	var y = date.getFullYear();
	var m = date.getMonth()+1;
	var d = date.getDate();
	return y + '.' + (m<10?('0'+m):m) + '.' + (d<10?('0'+d):d);
}
/**
 * 时间datebox控件解析
 */
$.fn.datebox.defaults.parser = function(s){
	 if (!s) return new Date();
	    var ss = (s.split('.'));
	    var y = parseInt(ss[0],10);
	    var m = parseInt(ss[1]?ss[1]:'01',10);
	    var d = parseInt(ss[2]?ss[2]:'01',10);
	    if (!isNaN(y) && !isNaN(m) && !isNaN(d)){
	    		return new Date(y,m-1,d);
	    } else {
	        return new Date();
	    }
}
/**
 * 自定义时间格式化规则
 * @param date
 * @returns {String}
 */
function myformatter(date){
    var y = date.getFullYear();
    var m = date.getMonth()+1;
    var d = date.getDate();
    return y+'.'+(m<10?('0'+m):m)+'.'+(d<10?('0'+d):d);
}
/**
 * 自定义时间解析规则
 * @param s
 * @returns {Date}
 */
function myparser(s){
    if (!s) return new Date();
    var ss = (s.split('.'));
    var y = parseInt(ss[0],10);
    var m = parseInt(ss[1],10);
    var d = parseInt(ss[2],10);
    if (!isNaN(y) && !isNaN(m) && !isNaN(d)){
        return new Date(y,m-1,d);
    } else {
        return new Date();
    }
}
/**
 * 创建销毁临时form表，用于临时下载文件请求<br/>
 * <strong>创建之后记得用destoryForm删除临时表</strong>
 */
var downloadForm = {
		/**
		 * 创建一个临时form，用于文件下载请求
		 * @param formId
		 * @returns {String}
		 */
	createForm : function(formId){
		var rformId = formId? formId : 'export_query';
		var dom = $('#' + rformId);
		if(dom){
			dom.remove();
		}
		var frm = $("<form>");
		frm.attr("style","display:none");
		frm.attr("target","");
		frm.attr("id",rformId);
		frm.attr("method","post");
		$("body").append(frm);//将表单放置在web中
		return rformId;
		
	},
	/**
	 * 销毁创建的临时form
	 * @param formId
	 */
	destoryForm : function(formId){
		if(!formId||formId==''){
			$("#export_query").remove();
		}
		$("#"+formId).remove();
	}
}

/**
 * 创建progressbar，用于进度提示<br/>
 * 		<strong>创建之后调用endProgress</strong> 
 */
var progressDialog = {
		/**
		 * 创建临时progressbar Dialog
		 */
		createProgressDialog : function(shape,time,modal){
			$('#progress_dialog').remove();
			var dialog = $("<div>");
			var progress = $("<div>");
			dialog.addClass("easyui-dialog");
			dialog.attr("data-options","title:'',modal:true");
			dialog.attr("id","progress_dialog");
			progress.attr("id","dialog_progress");
		}
}
 
/**
 * 创建销毁临时form表，用于提示框<br/>
 * <strong>创建之后记得用destoryForm删除临时表</strong>
 */
var confirmDialog = {
		/**
		 * 创建一个临时dialog，用于删除确认
		 * @param url 必要的,要删除提交的action地址
		 * @param message 必要的,弹出的提示消息
		 * @data data 必要的,删除给的传入参数
		 * @returns {String}
		 */
		createUrlDialog : function(url, message, data){
			var confirmId = 'confirm_dialog';
			var dialog = $('<div>');
			dialog.attr("style","display:none");
			dialog.attr("id",confirmId);
			dialog.append('<div style="height:26px;margin: 0 auto;"><h3>'+ message + '</h3></div>');
			dialog.append('<form id="confirm_dialog_form"></form>');
			dialog.addClass('easyui-dialog');
			$("body").append(dialog);//将dialog放置在web中
			$('#confirm_dialog').dialog({
			    title: '消息提示',
			    width: 400,
			    height: 200,
			    closed: false,
			    cache: false,
			    modal: true,
			    buttons:[{
					text:'确定',
					iconCls:'icon-ok',
					handler:function(){
						$(confirmId).form('submit');
						load('正在删除...');
						confirmDialog.destoryDialog(confirmId);
					}
				},{
					text:'取消',
					iconCls:'icon-cancel',
					handler:function(){
						confirmDialog.destoryDialog(confirmId);
					}
				}]
			});
			$('#confirm_dialog_form').form({
			    url : url,
			    queryParams : data,
			    success : function(result){
			    	disLoad();
			    	if(result.success){
			    		alert(result.msg);
			    	}
			    }
			});
			return confirmId;
		},
		createDialog : function(message, callback, cancelCallback, confirmId){
			var confirmId = confirmId?confirmId:'confirm_dialog';
			var dialog = $('<div>');
			dialog.attr("style","display:none");
			dialog.attr("id",confirmId);
			dialog.append('<div><h3 style="text-align: center;margin-top: 46px;">'+ message + '</h3></div>');
			dialog.addClass('easyui-dialog');
			$("body").append(dialog);//将dialog放置在web中
			$('#' + confirmId).dialog({
			    title: '消息提示',
			    width: 400,
			    height: 200,
			    closed: false,
			    cache: false,
			    modal: true,
			    buttons:[{
					text:'确定',
					iconCls:'icon-ok',
					handler:function(){
						callback(confirmId);
					}
				},{
					text:'取消',
					iconCls:'icon-cancel',
					handler:function(){

						if(cancelCallback){
							cancelCallback(confirmId);
						}
						confirmDialog.destoryDialog(confirmId);
					}
				}]
			});
			return confirmId;
		},
		/**
		 * 销毁创建的临时dialog
		 * @param confirmId
		 */
		destoryDialog : function(confirmId){
			if(!confirmId||confirmId==''){
				$("#confirm_dialog").dialog('destroy');
			}else{
				$("#"+confirmId).dialog('destroy');
			}
		}
}
//弹出加载层
function load(message) {  
	var message = message ? message:"正在加载，请稍后...";
    $("<div class=\"datagrid-mask\"></div>").css({ display: "block", width: "100%", height: $(window).height() }).appendTo("body");  
    $("<div class=\"datagrid-mask-msg\"></div>").html(message).appendTo("body").css({ display: "block", left: ($(document.body).outerWidth(true) - 190) / 2, top: ($(window).height() - 45) / 2 });  
}  
  
//取消加载层  
function disLoad() {  
    $(".datagrid-mask").remove();  
    $(".datagrid-mask-msg").remove();  
}

/**
 * 使panel和datagrid在加载时提示
 * 
 * @author dengxf
 * 
 * @requires jQuery,EasyUI
 * 
 */
$.fn.panel.defaults.loadingMessage = '加载中....';
$.fn.datagrid.defaults.loadMsg = '加载中....';

/**
 * @author dengxf
 * 
 * @requires jQuery,EasyUI
 * 
 * 通用错误提示
 * 
 * 用于datagrid/treegrid/tree/combogrid/combobox/form加载数据出错时的操作
 */
var easyuiErrorFunction = function(XMLHttpRequest) {
	$.messager.progress('close');
	$.messager.alert('错误', XMLHttpRequest.responseText);
};
$.fn.datagrid.defaults.onLoadError = easyuiErrorFunction;
$.fn.treegrid.defaults.onLoadError = easyuiErrorFunction;
$.fn.tree.defaults.onLoadError = easyuiErrorFunction;
$.fn.combogrid.defaults.onLoadError = easyuiErrorFunction;
$.fn.combobox.defaults.onLoadError = easyuiErrorFunction;
$.fn.form.defaults.onLoadError = easyuiErrorFunction;

/**
 * @author dengxf
 * 
 * @requires jQuery,EasyUI
 * 
 * 为datagrid、treegrid增加表头菜单，用于显示或隐藏列，注意：冻结列不在此菜单中
 */
var createGridHeaderContextMenu = function(e, field) {
	e.preventDefault();
	var grid = $(this);/* grid本身 */
	var headerContextMenu = this.headerContextMenu;/* grid上的列头菜单对象 */
	if (!headerContextMenu) {
		var tmenu = $('<div style="width:100px;"></div>').appendTo('body');
		var fields = grid.datagrid('getColumnFields');
		for ( var i = 0; i < fields.length; i++) {
			var fildOption = grid.datagrid('getColumnOption', fields[i]);
			if (!fildOption.hidden) {
				$('<div iconCls="icon-ok" field="' + fields[i] + '"/>').html(fildOption.title).appendTo(tmenu);
			} else {
				$('<div iconCls="icon-empty" field="' + fields[i] + '"/>').html(fildOption.title).appendTo(tmenu);
			}
		}
		headerContextMenu = this.headerContextMenu = tmenu.menu({
			onClick : function(item) {
				var field = $(item.target).attr('field');
				if (item.iconCls == 'icon-ok') {
					grid.datagrid('hideColumn', field);
					$(this).menu('setIcon', {
						target : item.target,
						iconCls : 'icon-empty'
					});
				} else {
					grid.datagrid('showColumn', field);
					$(this).menu('setIcon', {
						target : item.target,
						iconCls : 'icon-ok'
					});
				}
			}
		});
	}
	headerContextMenu.menu('show', {
		left : e.pageX,
		top : e.pageY
	});
};
$.fn.datagrid.defaults.onHeaderContextMenu = createGridHeaderContextMenu;
$.fn.treegrid.defaults.onHeaderContextMenu = createGridHeaderContextMenu;

/**
 * @author dengxf
 * 
 * @requires jQuery,EasyUI
 * 
 * 扩展validatebox，添加验证两次密码功能
 */
$.extend($.fn.validatebox.defaults.rules, {
	eqPwd : {
		validator : function(value, param) {
			return value == $(param[0]).val();
		},
		message : '密码不一致！'
	}
});

/**
 * @author 夏悸
 * 
 * @requires jQuery,EasyUI
 * 
 * 扩展tree，使其支持平滑数据格式
 */
$.fn.tree.defaults.loadFilter = function(data, parent) {
	var opt = $(this).data().tree.options;
	var idFiled, textFiled, parentField;
	if (opt.parentField) {
		idFiled = opt.idFiled || 'id';
		textFiled = opt.textFiled || 'text';
		parentField = opt.parentField;
		var i, l, treeData = [], tmpMap = [];
		for (i = 0, l = data.length; i < l; i++) {
			tmpMap[data[i][idFiled]] = data[i];
		}
		for (i = 0, l = data.length; i < l; i++) {
			if (tmpMap[data[i][parentField]] && data[i][idFiled] != data[i][parentField]) {
				if (!tmpMap[data[i][parentField]]['children'])
					tmpMap[data[i][parentField]]['children'] = [];
				data[i]['text'] = data[i][textFiled];
				tmpMap[data[i][parentField]]['children'].push(data[i]);
			} else {
				data[i]['text'] = data[i][textFiled];
				treeData.push(data[i]);
			}
		}
		return treeData;
	}
	return data;
};

/**
 * @author dengxf
 * 
 * @requires jQuery,EasyUI
 * 
 * 扩展treegrid，使其支持平滑数据格式
 */
$.fn.treegrid.defaults.loadFilter = function(data, parentId) {
	var opt = $(this).data().treegrid.options;
	var idFiled, textFiled, parentField;
	if (opt.parentField) {
		idFiled = opt.idFiled || 'id';
		textFiled = opt.textFiled || 'text';
		parentField = opt.parentField;
		var i, l, treeData = [], tmpMap = [];
		for (i = 0, l = data.length; i < l; i++) {
			tmpMap[data[i][idFiled]] = data[i];
		}
		for (i = 0, l = data.length; i < l; i++) {
			if (tmpMap[data[i][parentField]] && data[i][idFiled] != data[i][parentField]) {
				if (!tmpMap[data[i][parentField]]['children'])
					tmpMap[data[i][parentField]]['children'] = [];
				data[i]['text'] = data[i][textFiled];
				tmpMap[data[i][parentField]]['children'].push(data[i]);
			} else {
				data[i]['text'] = data[i][textFiled];
				treeData.push(data[i]);
			}
		}
		return treeData;
	}
	return data;
};

/**
 * @author dengxf
 * 
 * @requires jQuery,EasyUI
 * 
 * 扩展combotree，使其支持平滑数据格式
 */
$.fn.combotree.defaults.loadFilter = $.fn.tree.defaults.loadFilter;

/**
 * @author dengxf
 * 
 * @requires jQuery,EasyUI
 * 
 * 防止panel/window/dialog组件超出浏览器边界
 * @param left
 * @param top
 */
var easyuiPanelOnMove = function(left, top) {
	var l = left;
	var t = top;
	if (l < 1) {
		l = 1;
	}
	if (t < 1) {
		t = 1;
	}
	var width = parseInt($(this).parent().css('width')) + 14;
	var height = parseInt($(this).parent().css('height')) + 14;
	var right = l + width;
	var buttom = t + height;
	var browserWidth = $(window).width();
	var browserHeight = $(window).height();
	if (right > browserWidth) {
		l = browserWidth - width;
	}
	if (buttom > browserHeight) {
		t = browserHeight - height;
	}
	$(this).parent().css({/* 修正面板位置 */
		left : l,
		top : t
	});
};
$.fn.dialog.defaults.onMove = easyuiPanelOnMove;
$.fn.window.defaults.onMove = easyuiPanelOnMove;
$.fn.panel.defaults.onMove = easyuiPanelOnMove;

/**
 * @author dengxf
 * 
 * @requires jQuery,EasyUI,jQuery cookie plugin
 * 
 * 更换EasyUI主题的方法
 * 
 * @param themeName
 *            主题名称
 */
var changeTheme = function(themeName) {
	var $easyuiTheme = $('#easyuiTheme');
	var url = $easyuiTheme.attr('href');
	var href = url.substring(0, url.indexOf('themes')) + 'themes/' + themeName + '/easyui.css';
	$easyuiTheme.attr('href', href);

	var $iframe = $('iframe');
	if ($iframe.length > 0) {
		for ( var i = 0; i < $iframe.length; i++) {
			var ifr = $iframe[i];
			$(ifr).contents().find('#easyuiTheme').attr('href', href);
		}
	}

	$.cookie('easyuiThemeName', themeName, {
		expires : 7
	});
};

/**
 * @author dengxf
 * 
 * @requires jQuery
 * 
 * 将form表单元素的值序列化成对象
 * 
 * @returns object
 */
serializeObject = function(form) {
	var o = {};
	$.each(form.serializeArray(), function(index) {
		if (o[this['name']]) {
			o[this['name']] = o[this['name']] + "," + this['value'];
		} else {
			o[this['name']] = this['value'];
		}
	});
	return o;
};

/**
 * 获取html元素
 */
function getView(view, callback, errorCallback) {
    $.ajax({
        type: "GET",
        url: "./views/" + view,
        timeout: 10000,
        cache: false,
        success: function (data) {
            if (typeof callback == "function") {
                callback(data);
            }
            if (data && !data.logined) {
            	$.messager.alert("会话已经失效，请您重新登录");
                window.location.href = "./";
                return;
            }
        },
        error: function (xhr, status, error) {
            if (errorCallback && (typeof errorCallback == "function")) {
                errorCallback(xhr, status, error);
            } else {
                if (xhr.statusText != 'success') {
                	$.messager.alert('showErrorToast', '请求超时或网络问题,' + status || error);
                }
            }
        }
    });
}

/**
 * 获取页面元素，并缓存在页面内存
 */
function getBufferedView(view, callback) {
    if (!session[view]) {
        getView(view + ".html", function (data) {
            session[view] = data;
            callback(data);
        }, function () {
        	$.messager.alert('获取页面失败!');
        });
    } else {
        callback(session[view]);
    }
}

/**
 * @author dengxf
 * 
 * 增加formatString功能
 * 
 * 使用方法：formatString('字符串{0}字符串{1}字符串','第一个变量','第二个变量');
 * 
 * @returns 格式化后的字符串
 */
formatString = function(str) {
	for ( var i = 0; i < arguments.length - 1; i++) {
		str = str.replace("{" + i + "}", arguments[i + 1]);
	}
	return str;
};

/**
 * @author dengxf
 * 
 * 接收一个以逗号分割的字符串，返回List，list里每一项都是一个字符串
 * 
 * @returns list
 */
stringToList = function(value) {
	if (value != undefined && value != '') {
		var values = [];
		var t = value.split(',');
		for ( var i = 0; i < t.length; i++) {
			values.push('' + t[i]);/* 避免他将ID当成数字 */
		}
		return values;
	} else {
		return [];
	}
};

/**
 * @author dengxf
 * 
 * @requires jQuery
 * 
 * 改变jQuery的AJAX默认属性和方法
 */
$.ajaxSetup({
	type : 'POST',
	error : function(XMLHttpRequest, textStatus, errorThrown) {
		$.messager.progress('close');
		$.messager.alert('错误', XMLHttpRequest.responseText);
	}
});

/**
 * 扩展的基本校验规则，/i 忽略大小写
 */
$.extend($.fn.validatebox.defaults.rules, { 
    minLength : { // 判断最小长度 
        validator : function(value, param) { 
            value = $.trim(value); //去空格 
            return value.length >= param[0]; 
        }, 
        message : '最少输入 {0} 个字符。'
    }, 
    length:{validator:function(value,param){ 
        var len=$.trim(value).length; 
            return len>=param[0]&&len<=param[1]; 
        }, 
            message:"输入大小不正确"
        }, 
    phone : {// 验证电话号码 
        validator : function(value) { 
            return /^((\(\d{2,3}\))|(\d{3}\-))?(\(0\d{2,3}\)|0\d{2,3}-)?[1-9]\d{6,7}(\-\d{1,4})?$/i.test(value); 
        }, 
        message : '格式不正确,请使用下面格式:020-88888888'
    }, 
    mobile : {// 验证手机号码 
        validator : function(value) { 
            return /^(11|12|13|14|15|16|17|18|19)\d{9}$/i.test(value); 
        }, 
        message : '手机号码格式不正确'
    }, 
    idcard : {// 验证身份证 
        validator : function(value) { 
            return /^[1-9]\d{5}[1-9]\d{3}((0\d)|(1[0-2]))(([0|1|2]\d)|3[0-1])\d{3}([0-9]|X)$/i.test(value); 
//            return /^\d{15}(\d{2}[A-Za-z0-9])?$/i.test(value); 
        }, 
        message : '身份证号码格式不正确'
    }, 
    intOrFloat : {// 验证整数或小数 
        validator : function(value) { 
            return /^\d+(\.\d+)?$/i.test(value); 
        }, 
        message : '请输入数字，并确保格式正确'
    }, 
    currency : {// 验证货币 
        validator : function(value) { 
            return /^\d+(\.\d+)?$/i.test(value); 
        }, 
        message : '货币格式不正确'
    }, 
    qq : {// 验证QQ,从10000开始 
        validator : function(value) { 
            return /^[1-9]\d{4,9}$/i.test(value); 
        }, 
        message : 'QQ号码格式不正确'
    }, 
    integer : {// 验证整数 
        validator : function(value) { 
            return /^[+]?[1-9]+\d*$/i.test(value); 
        }, 
        message : '请输入整数'
    },     
    chinese : {// 验证中文 
        validator : function(value) { 
            return /^[\u0391-\uFFE5]+$/i.test(value); 
        }, 
        message : '请输入中文'
    }, 
    english : {// 验证英语 
        validator : function(value) { 
            return /^[A-Za-z]+$/i.test(value); 
        }, 
        message : '请输入英文'
    }, 
    unnormal : {// 验证是否包含空格和非法字符 
        validator : function(value) { 
            return /.+/i.test(value); 
        }, 
        message : '输入值不能为空和包含其他非法字符'
    }, 
    username : {// 验证用户名 
        validator : function(value) { 
            return /^[a-zA-Z][a-zA-Z0-9_]{5,15}$/i.test(value); 
        }, 
        message : '用户名不合法（字母开头，允许6-16字节，允许字母数字下划线）'
    }, 
    faxno : {// 验证传真 
        validator : function(value) { 
//            return /^[+]{0,1}(\d){1,3}[ ]?([-]?((\d)|[ ]){1,12})+$/i.test(value); 
            return /^((\(\d{2,3}\))|(\d{3}\-))?(\(0\d{2,3}\)|0\d{2,3}-)?[1-9]\d{6,7}(\-\d{1,4})?$/i.test(value); 
        }, 
        message : '传真号码不正确'
    }, 
    zip : {// 验证邮政编码 
        validator : function(value) { 
            return /^[1-9]\d{5}$/i.test(value); 
        }, 
        message : '邮政编码格式不正确'
    }, 
    ip : {// 验证IP地址 
        validator : function(value) { 
            return /d+.d+.d+.d+/i.test(value); 
        }, 
        message : 'IP地址格式不正确'
    }, 
    name : {// 验证姓名，可以是中文或英文 
            validator : function(value) { 
                return /^[\u0391-\uFFE5]+$/i.test(value)|/^\w+[\w\s]+\w+$/i.test(value); 
            }, 
            message : '请输入姓名'
    }, 
    carNo:{ 
        validator : function(value){ 
            return /^[\u4E00-\u9FA5][\da-zA-Z]{6}$/.test(value); 
        }, 
        message : '车牌号码无效（例：粤J12350）'
    }, 
    carenergin:{ 
        validator : function(value){ 
            return /^[a-zA-Z0-9]{16}$/.test(value); 
        }, 
        message : '发动机型号无效(例：FG6H012345654584)'
    }, 
    email:{ 
        validator : function(value){ 
        return /^\w+([-+.]\w+)*@\w+([-.]\w+)*\.\w+([-.]\w+)*$/.test(value); 
    }, 
    message : '请输入有效的电子邮件账号(例：abc@126.com)'   
    }, 
    msn:{ 
        validator : function(value){ 
        return /^\w+([-+.]\w+)*@\w+([-.]\w+)*\.\w+([-.]\w+)*$/.test(value); 
    }, 
    message : '请输入有效的msn账号(例：abc@hotnail(msn/live).com)'
    },
    same:{ 
        validator : function(value, param){ 
            if($("#"+param[0]).val() != "" && value != ""){ 
                return $("#"+param[0]).val() == value; 
            }else{ 
                return true; 
            } 
        }, 
        message : '两次输入的密码不一致！'   
    },
    warnmintime : { // 判断告警的值只能一级一级的增加，最小值
        validator : function(value, param) { 
            value = $.trim(value); //去空格 
            if( value !="")
            for(var i=0;i<param.length; i++){
                $(param[i]).val();
                if($(param[i]).combobox('getValue')){
                    var temp=$.trim($(param[i]).combobox('getValue'));
                    if(temp !="" && !isNaN(temp) && parseInt(value) <= parseInt(temp))
                        return false;
                   }
            }
            return true;
        }, 
        message : '不能小于当前告警的前一级的告警时间'
    },
    warnmaxtime : { // 判断告警的值只能一级一级的增加，最大值
        validator : function(value, param) { 
            value = $.trim(value); //去空格 
            if( value !="")
            for(var i=0;i<param.length; i++){
                $(param[i]).val();
                if($(param[i]).combobox('getValue')){
                    var temp=$.trim($(param[i]).combobox('getValue'));
                    if(temp !="" && !isNaN(temp) && parseInt(value) >= parseInt(temp))
                        return false;
                   }
            }
            return true;
        }, 
        message : '不能大于当前告警的后一级的告警时间'
    },
    compareDate: {
        validator: function (value, param) {
        return dateCompare($(param[0]).datetimebox('getValue'), value); //注意easyui 时间控制获取值的方式
        },
        message: '开始日期不能大于结束日期'
        },
});
/**
 * 
 * @param number 要计算的数
 * @param fractiondigits 精确位
 * @returns {Number}
 */
function round2(number,fractiondigits){   
    with(Math){   
        return round(number*pow(10,fractiondigits))/pow(10,fractiondigits);   
    }   
}

///为字符串添加模糊比较的方法
String.prototype.isLike = function(exp/*类似于SQL中的模糊查询字符串*/, i/*是否区分大小写*/, start/*以该字符串开始*/, end/*以该字符串结束*/) {
	var str = this;
	i = (i == null ? false : i);
	start = (start ? "^" : "");
	end = (end ? "$" : "");
	if (exp.constructor == String) {
		/*首先将表达式中的‘_’替换成‘.’，但是‘[_]’表示对‘_’的转义，所以做特殊处理*/
		var s = exp.replace(/_/g, function(m, i) {
			if (i == 0 || i == exp.length - 1) {
				return ".";
			}
			else {
				if (exp.charAt(i - 1) == "[" && exp.charAt(i + 1) == "]") {
					return m;
				}
				return ".";
			}
		});
		/*将表达式中的‘%’替换成‘.’，但是‘[%]’表示对‘%’的转义，所以做特殊处理*/
		s = s.replace(/%/g, function(m, i) {
			if (i == 0 || i == s.length - 1) {
				return ".*";
			}
			else {
				if (s.charAt(i - 1) == "[" && s.charAt(i + 1) == "]") {
					return m;
				}
				return ".*";
			}
		});

		/*将表达式中的‘[_]’、‘[%]’分别替换为‘_’、‘%’*/

		s = s.replace(/\[_\]/g, "_").replace(/\[%\]/g, "%");

		/*对表达式处理完后构造一个新的正则表达式，用以判断当前字符串是否和给定的表达式相似*/

		/*var regex = new RegExp("^" + s, i ? "" : "i");*/
		var regex = new RegExp(start + s + end, i ? "" : "i");
		return regex.test(this);
	}
	return false;
};

///为数组添加模糊查询方法
Array.prototype.selectLike = function(exp/*类似于SQL中的模糊查询字符串*/, fun, start, end) {
	var arr = [];
	start = (start?true:false);
	end = (end?true:false);
	if (fun && fun.constructor == Function) {
		for (var i = 0; i < this.length; i++) {
			if (fun(this[i], exp)) {
				arr.push(i);
			}
		}
	}
	else {
		for (var i = 0; i < this.length; i++) {
			if (this[i].isLike(exp, false, start, end)) {
				arr.push(i);
			}
		}
	}
	return arr;
};
String.prototype.trim_ = function(gloabal){
	return gloabal ? this.replace(/\s/g,"") : this.replace(/(^\s+)|(\s+$)/g,"");
};
var isEmpty = function(e){
	var obj;
	for(obj in e){
		return false;
	}
	return true;
}
//Object.prototype.isEmpty = function(){
//	var obj;
//	for(obj in this){
//		return false;
//	}
//	return true;
//}
var TIPS = "提示";
var UPDATETIPS = "更新提示：";
var REMOVETIPS = "删除提示：";
var ADDTIPS = "添加提示：";