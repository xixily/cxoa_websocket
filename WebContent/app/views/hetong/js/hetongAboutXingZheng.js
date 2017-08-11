$(function(){
	$('#chooseProduct').selectlist({
		width: 130,
		height: 18,
	 	onChange: function(){
	 		var name = $("#chooseProduct .select-button").val();
	 		if(name=='第三方产品'){
	 			$("#disanfangLi").attr("style","display:block;");
	 		}else{
	 			$("#disanfangLi").attr("style","display:none;");
	 		} 
	 		if(name=='其他'){
	 			$("#otherLi").attr("style","display:block;");
	 		}else{
	 			$("#otherLi").attr("style","display:none;");
	 			$("#other").val("");
	 		}
		} 
	});	
	
	$('select').selectlist({
		width: 130,
		height: 18,		
	});
})

$(function(){
	$('#windowAboutFapiao2').on('click',".selectNav p",function(){
		var ul=$(".selectNew");
		if(ul.css("display")=="none"){
			ul.slideDown();
		}else{
			ul.slideUp();
		}
	})
	
	$('.delete').on('click',function(){
		$(".selectNew").slideUp()
	})
	
	$('#windowAboutFapiao2').on('click',".selectSet",function(){
		var _name = $(this).attr("name");
		if( $("[name="+_name+"]").length > 1 ){
			$("[name="+_name+"]").removeClass("selectCur");
			$(this).addClass("selectCur");
		} else {
			if( $(this).hasClass("selectCur") ){
				$(this).removeClass("selectCur");
			} else {
				$(this).addClass("selectCur");
			}
		}
	})
	$('#windowAboutFapiao2').on('click',".selectNav li",function(){
		var li=$(this).text();
		$(".selectNav p").html(li);
		$(".selectNew").hide();
		/*$(".set").css({background:'none'});*/
		$("p").removeClass("selectCur") ;
	})
	//只要没有归档编号，显示为合同未回
	var guidangNum = $("#guidangNum").val();
	if(guidangNum==''){
		$("#guidangNum").val("合同未回");
	}	
})

/*----------------------------------------------------产品--------------------------------------------------------------*/
//--添加产品弹窗,没在common中,独立出来
$("#addProduct").click(function(){
	$("#sureAboutProduct").attr("style","display:inline;"); 
	$("#updateProduct").attr("style","display:none;");
	$("#deleteProduct").attr("style","display:none;");
	
	$("#productMoney").val("");
	$("#productAmount").val("");
	$("#effectiveDate").val("");
	$("#endDate").val("");
	$("#disanfangChanpin").val("");
    var productName = $("#chooseProduct .select-button").val();
	if(productName=="第三方产品"){
		$("#disanfangLi").attr("style","display:block;");
	}else{
	//非第三方产品
		$("#disanfangLi").attr("style","display:none;");
	}
	//去掉"其他"下的输入框
	if(productName=="其他"){
		$("#otherLi").attr("style","display:block;");
	}else{
		$("#otherLi").attr("style","display:none;");
	}
	$("#other").val("");
})

//--添加产品,与common相同
$("#sureAboutProduct").click(function(){
	var name = $("#chooseProduct .select-button").val();
	var money = $("#productMoney").val();
	var amount =$("#productAmount").val();
	var begain =$("#effectiveDate").val();
	var end =$("#endDate").val();
	var ctid =$("#htNum").text();	
	//验证产品金额
	var reg = /(^[1-9]([0-9]+)?(\.[0-9]{1,2})?$)|(^(0){1}$)|(^[0-9]\.[0-9]([0-9])?$)/;
	var productMoney = $("#productMoney").val();
	 if(!reg.test(productMoney)){
		$.messager.alert('提示：',"请输入正确的产品金额");
		 return;
	 }
	if(name=='其他'){
		name = $("#other").val();
	}
	 //保存合同概要
	if(name=='第三方产品'){
	  var htgaiyao = $("#disanfangChanpin").val();
	  var contractContent = $("#contractContent").val();
	  var contractContentFinal = contractContent+htgaiyao;
	  $("#contractContent").val(contractContentFinal);
	  $.post('public/ht/addHtgaiyao.action',{"htgaiyao":htgaiyao,"ctid":ctid},function(res){})
	}
	var data = {"name":name,"amount":amount,"begain":begain,"end":end,"ctid":ctid,"money":money}
  $.post('public/ht/addItemPrice.action',data,function(res){
		if(res.success==true){
			var a = res.obj.money;
			var b = res.obj.amount;
			if(a==null){
				 a ="";
			}
			if(b==null){
				b ="";
			}
		var tr = "<tr><td>"+res.obj.id+"</td><td>"+res.obj.name+"</td><td>"+a+"</td><td>"+b+"</td><td>"+res.obj.begain+"</td><td>"+res.obj.end+"</td><td><a id="+res.obj.id+" class='dele' href='javascript:;' onclick='updateProduct("+res.obj.id+",this)'>修改</a></td></tr>";
		$("#productTable").append(tr);	
		$(".pop_product").hide();	
		$(".maskLayer").hide();
		}else{
			$.messager.alert('提示：',res.msg);
		}
	})
})

//--更新产品弹窗(回显),与common相同
var tr;
function updateProduct(id,obj){
    tr = $(obj).parent().parent();
    console.log(tr);
	//确定按钮隐藏    修改删除按钮显示
	$("#sureAboutProduct").attr("style","display:none");
	$("#updateProduct").attr("style","display:inline");
	$("#deleteProduct").attr("style","display:inline");
	var data ={"itemPriceId":id}
	var id = $("#htNum").text();//合同编号
	//根据id获得产品信息,此处取名不当
	$.post('public/ht/toUpdateItemPrice.action',data,function(res){
		 if(res.success==true){
			var ItemPrice = res.obj;
			var name = ItemPrice.name;
		//	var start = name.indexOf("第三方产品");
			//是第三方产品
			if(name=="第三方产品"){
				var htGaiyao;
				$.ajax({
					type:'post',
					url:'public/ht/getHtgaiyao.action',
					data:{"id":id},
					dataType:'json',
					async: false,
					success:function(res){
						htGaiyao = res.obj;
					}
				});
				$("#disanfangLi").attr("style","display:block;");
				//回显合同概要
				$("#disanfangChanpin").val(htGaiyao);
			}else{
			//非第三方产品
				$("#disanfangLi").attr("style","display:none;");
			}
			//去掉"其他"下的输入框
			if(name=="其他"){
				$("#otherLi").attr("style","display:block;");
			}else{
				$("#otherLi").attr("style","display:none;");
			}
 			$("#other").val("");
			$("#chooseProduct .select-button").attr("value",ItemPrice.name);
	        $("#productMoney").val(ItemPrice.money);
			$("#productAmount").val(ItemPrice.amount);
			$("#effectiveDate").val(ItemPrice.begain);
			$("#endDate").val(ItemPrice.end);
			$("#productID").attr("value",ItemPrice.id);
		 }
	}); 
	$(".maskLayer").show();		
	if($(".maskLayer").css('display')=='block'){
		$(".maskLayer").fadeTo('fast',0.7);
		$('.maskLayer').height($(document).height());
	}		
	$(".pop_product").show();
	$('.pop_product').css({
		left:function(){
			return ($(window).width()-$(this).width())/2;
		},
		top:function(){
			return ($(window).height()-$(this).height())/2+$(window).scrollTop();	
		}
	});
}

//--修改产品,与common相同
$("#updateProduct").click(function(){
	var productID = $("#productID").val();
	var name = $("#chooseProduct .select-button").val();
	var money = $("#productMoney").val();
	var amount =$("#productAmount").val();
	var begain =$("#effectiveDate").val();
	var end =$("#endDate").val();
	var id = $("#htNum").text();//合同编号
	//验证产品金额
	var reg = /(^[1-9]([0-9]+)?(\.[0-9]{1,2})?$)|(^(0){1}$)|(^[0-9]\.[0-9]([0-9])?$)/;
	var productMoney = $("#productMoney").val();
	if(!reg.test(productMoney)){
		$.messager.alert('提示：',"请输入正确的产品金额");
	//	$("#productMoneyError").text("请输入正确的产品金额");
		return;
	}
	if(name=='第三方产品'){
		var disanfangChanpin = $("#disanfangChanpin").val();
		$("#contractContent").val(disanfangChanpin);
		$.ajax({
			type:'post',
			url:'public/ht/updateHtgaiyao.action',
			data:{"id":id,"disanfangChanpin":disanfangChanpin},
			dataType:'json',
			async: false,
			success:function(res){
				
			}
		});
	}
	if(name=='其他'){
		name = $("#other").val();
	}
	var data = {"name":name,"amount":amount,"begain":begain,"end":end,"money":money,"productID":productID}
	$.post('public/ht/doUpdateItemPrice.action',data,function(res){
		 if(res.success==true){
			 /*$.messager.alert('提示：',res.msg);*/
			 tr.children('td').eq(0).text(productID);
			 tr.children('td').eq(1).text(name);
			 tr.children('td').eq(2).text(money);
			 tr.children('td').eq(3).text(amount);
			 tr.children('td').eq(4).text(begain);
			 tr.children('td').eq(5).text(end);
			 $(".pop_product").hide();	
			 $(".maskLayer").hide();
		 }else{
			 $.messager.alert('提示：',res.msg);
		 }
	});
})

//--删除产品,与common相同
$("#deleteProduct").click(function(){
	var productID = $("#productID").val();
	$.messager.confirm("提示","您确认要删除当前产品吗？",function(){
		$.post('public/ht/deleteItemPrice.action',{"itemPriceId":productID},function(res){			   
			   if(res.success==true){
				   tr.remove();
				  /* $.messager.alert('提示：',res.msg);*/
				   $(".pop_product").hide();
				   $(".maskLayer").hide();
				 }else{
					 $.messager.alert('提示：',res.msg);
			   }
	       })
	})
	  
});


/*------------------------------------------------------发票-----------------------------------------------------------*/
var addOrUpdateFapiao = '-1';//add代表新增   update代表更新
//--添加发票弹窗,与common不相同,独立出来,有几个id还要再次查看sureAboutFapiaoXingzheng,danweiName2,company2
$("#addFapiao").click(function(){
	addOrUpdateFapiao = '-1';
	var ul=$(".selectNew");
	if(ul.css("display")!="none"){
		ul.hide();
	}
	$("#sureAboutFapiaoXingzheng").attr("style","display:inline;");
	$("#updateFapiao").attr("style","display:none;");
	$("#deleteFapiao").attr("style","display:none;");
	
	$("#kaipiaoAmount").val("");//开票金额
	$("#daxieAmount").val("");//大写金额
	$("#kaipiaoCompany").val("");//开票公司
	$("#kaipiaodDanwei").val("");//开票单位
	$("#fapiaoType").val("");//发票类型
	$("#remarkAboutFapiao").val("");//备注
	$("#kaipiaoAmountError").text("");
	$("#yujihuikuanDate").val("");//预计回款日期
	$("#pinmingUl").empty();//发票品名中的列表
	var date = new Date();
	var currentTime = date.Format("yyyy-MM-dd");
	$("#applicationTimeAboutFapiao").text(currentTime); //申请时间
	$("#kaipiaoDate").val(currentTime);//开票时间
	//所属公司
	var company = $("#company2").val();
	$("#kaipiaoCompany").val(company);//开票公司
	var danwei = $("#danweiName2").val();
	$("#kaipiaodDanwei").val(danwei);//开票单位
	$("#pinming").text("点击选择栏目");  //发票品名	
	var id = $("#htNum").text();
	$("#HtForFapiao").val(id); //合同编号
	$("#HtForFapiao").attr("disabled",true);
	//下拉的选项不重置也行,此处账户和资金类型没重置
	$("#huikuanAmount").val("");  //回款金额
	$("#huikuanDate").val("");  //回款日期
	 //财务月份(默认显示当月)  
	var date = new Date();
	var currentMonth = date.getMonth();
	var currentMonthTrue = Number(currentMonth)+1	
	//赋值当月
	$("#caiwuyuefen").val(currentMonthTrue);	//财务月份
	//发票品名选项
// 	var company = $("#kaipiaoCompany").val();	 	
 	if(company!=""){
		$.post('public/ht/getKaipiaoOption.action',{"company":company},function(res){
			var kaipiaoList = res.obj;
			for(var i=0;i<kaipiaoList.length;i++){
				var option = "<li>"+kaipiaoList[i]+"</li>";
				$("#pinmingUl").append(option);
			}
		});
	}
})	


//--添加发票,少了财务月份
$("#sureAboutFapiaoXingzheng").click(function(){
	var hetongNumber =$("#htNum").text();//合同编号
	var applicationDate =$("#applicationTimeAboutFapiao").text(); //申请时间
	var money =$("#kaipiaoAmount").val();//开票金额
	var capitalMoney =$("#daxieAmount").val();//大写金额
	var company =$("#kaipiaoCompany").val();//开票公司
	var departMement =$("#kaipiaodDanwei").val();//开票单位
	var type = $("#fapiaoType .select-button").val();//发票类型
	var name = $("#pinming").text();//品名
	var date =$("#kaipiaoDate").val();//开票日期
	var yujihuikuanDate = $("#yujihuikuanDate").val();//预计回款日期
	var remark = $("#remarkAboutFapiao").val();//备注

//	var state = '1';	
//比销售多的五个字段
	var huiKuan = $("#huikuanAmount").val();//回款金额
	var receivedpaymentsdate = $("#huikuanDate").val();//回款日期
	var fundType = $("#zijin .select-button").val();//资金类型
	var account = $("#zhanghu .select-button").val();//账户
    var caiwuMonth = $("#caiwuyuefen").val();//财务月份
	var b = validateFapiaoAmount();
	if(b==false){
		return;
	}
	if(money==""){
		$.messager.alert('提示：','请填写发票金额');
		return;
	}
	if(company==""){
		$.messager.alert('提示：','请填写开票公司');
		return;
	}
	if(departMement==""){
		$.messager.alert('提示：','请填写开票单位');
		return;
	}
	if(name=="点击选择栏目"){
		$.messager.alert('提示：','请选择发票品名');
		return;
	}
	if(yujihuikuanDate==""){
		$.messager.alert('提示：','请选择开票预计回款时间');
		return;
	}	
	var data ={"hetongNumber":hetongNumber,"applicationDate":applicationDate,"money":money,"capitalMoney":capitalMoney,"company":company,"departMement":departMement,"type":type,"name":name,"date":date,"remark":remark,"huiKuan":huiKuan,"receivedpaymentsdate":receivedpaymentsdate,"fundType":fundType,"account":account,"caiwuMonth":caiwuMonth,"yujihuikuanDate":yujihuikuanDate};
    $.post('public/ht/addFaPiao.action',data,function(res){
			if(res.success==true){
				var date = res.obj.date;
				var yujihuikuanDate = res.obj.yujihuikuanDate;
				var date2 = "";
				var yujihuikuanDate2 = "";
				if(date!=null&&date!=""){
					date2 = new Date(date).Format("yyyy-MM-dd");
				}
				if(yujihuikuanDate!=null&&yujihuikuanDate!=""){
					yujihuikuanDate2 = new Date(yujihuikuanDate).Format("yyyy-MM-dd");
				}
				var a = res.obj.huiKuan;
				var b = res.obj.receivedpaymentsdate;
				var huikuanDate = "";
				if(b!=null&&b!=""){
					huikuanDate = new Date(b).Format("yyyy-MM-dd");
				}
				if(a==null){
					 a ="";
				}
				var tr = "<tr><td>"+res.obj.id+"</td><td>"+date2+"</td><td>"+res.obj.company+"</td><td>"+res.obj.departMement+"</td><td>"+res.obj.type+"</td><td>"+res.obj.name+"</td>" +
				"<td>"+res.obj.money+"</td><td>"+yujihuikuanDate2+"</td><td>"+a+"</td><td>"+huikuanDate+"</td><td>"+res.obj.remark+"</td><td>"+res.obj.fundType+"</td><td>"+res.obj.account+"</td><td><a id="+res.obj.id+" class='dele' href='javascript:;' onclick='updateFapiaoXingzheng("+res.obj.id+",this)'>修改</a></td></tr>";	
				$("#tableAboutFapiao").append(tr);	
				$(".pop_invoice").hide();
				$(".maskLayer").hide();
			}else{
				$.messager.alert('提示：',res.msg);
			}
	})
})

//--品名搜索
$("#pinmingSearch").keyup(function(){
	var pinmingSearch = $("#pinmingSearch").val();
	var company = $("#kaipiaoCompany").val();	
	if(kaipiaoCompany!=""){
		$.post('public/ht/getKaipiaoOptionSearch.action',{"company":company,"pinmingSearch":pinmingSearch},function(res){
			var kaipiaoList = res.obj;
			$("#pinmingUl").empty();
			for(var i=0;i<kaipiaoList.length;i++){
				var option = "<li>"+kaipiaoList[i]+"</li>";
				$("#pinmingUl").append(option);
			}
		});
	}
})

// --更新发票弹窗(回显),与common不同
function updateFapiaoXingzheng(id,obj){
	tr = $(obj).parent().parent();
	var ul=$(".selectNew");
	if(ul.css("display")!="none"){
		ul.hide();
	}
	//确定按钮隐藏    修改删除按钮显示
	$("#sureAboutFapiaoXingzheng").attr("style","display:none");
	$("#updateFapiao").attr("style","display:inline");
	$("#deleteFapiao").attr("style","display:inline");
//为什么要inline?	$("#yujihuikuanDateli").attr("style","display:inline;");
	$("#HtForFapiao").attr("disabled",false);
	var data ={"fapiaoId":id}
	//清空错误消息
	$("#kaipiaoAmountError").text("");
	var id = $("#htNum").text();
	$("#HtForFapiao").val(id);
	//发票品名选项
	$("#pinmingUl").empty();
 	var company = $("#company2").val();	
 	if(company!=""){
		$.post('public/ht/getKaipiaoOption.action',{"company":company},function(res){
			var kaipiaoList = res.obj;
			for(var i=0;i<kaipiaoList.length;i++){
				var option = "<li>"+kaipiaoList[i]+"</li>";
				$("#pinmingUl").append(option);
			}
		});
	} 
	$.post('public/ht/toUpdateFaPiao.action',data,function(res){
		if(res.success==true){
			var fapiao = res.obj;
			var fapiaoId = fapiao.id;
			addOrUpdateFapiao = fapiaoId;//通过addOrUpdateFapiao的值判断是增加还是修改
			/*var date2 = new Date(fapiao.date).Format("yyyy-MM-dd");
			var applicationDate = new Date(fapiao.applicationDate).Format("yyyy-MM-dd");
			var yujihuikuanDate = new Date(fapiao.yujihuikuanDate).Format("yyyy-MM-dd");*/
			var date2 = "";
			var applicationDate = "";
			var yujihuikuanDate = "";
			if(fapiao.date!=null&&fapiao.date!=""){
				 date2 = new Date(fapiao.date).Format("yyyy-MM-dd");
			}
			if(fapiao.applicationDate!=null&&fapiao.applicationDate!=""){
				 applicationDate = new Date(fapiao.applicationDate).Format("yyyy-MM-dd");//申请时间
			}
			if(fapiao.yujihuikuanDate!=null&&fapiao.yujihuikuanDate!=""){
				 yujihuikuanDate = new Date(fapiao.yujihuikuanDate).Format("yyyy-MM-dd");
			}
			$("#applicationTimeAboutFapiao").text(applicationDate);
			$("#kaipiaoAmount").val(fapiao.money);
			$("#daxieAmount").val(fapiao.capitalMoney);
			$("#kaipiaoCompany").val(fapiao.company);
			$("#kaipiaodDanwei").val(fapiao.departMement);
			$("#fapiaoType .select-button").attr("value",fapiao.type);
			$("#pinming").text(fapiao.name);
			$("#kaipiaoDate").val(date2);
			$("#yujihuikuanDate").val(yujihuikuanDate);
			$("#remarkAboutFapiao").val(fapiao.remark);
			$("#fapiaoId").attr("value",fapiao.id);
			$("#kaipiaoAmount").attr("onBlur","validateFapiaoAmount("+fapiaoId+")");
			//行政比销售多五个字段
			$("#huikuanAmount").val(fapiao.huiKuan);
			$("#zijin .select-button").val(fapiao.fundType);
			$("#zhanghu .select-button").val(fapiao.account);
			
			//财务月份(默认显示当月)  没有月份 赋值   有 不赋值
			var date = new Date();
			var currentMonth = date.getMonth();
			var caiwuyuefen = $("#caiwuyuefen").val();
			var currentMonthTrue = Number(currentMonth)+1;
			if(fapiao.caiwuMonth==null){
				$("#caiwuyuefen").val(currentMonthTrue);
			}else{
				$("#caiwuyuefen").val(fapiao.caiwuMonth);
			} 
			if(fapiao.receivedpaymentsdate==null){
				$("#huikuanDate").val("");
			}else{
				var receivedpaymentsdate2 = new Date(fapiao.receivedpaymentsdate).Format("yyyy-MM-dd");
				$("#huikuanDate").val(receivedpaymentsdate2);
			}
		}
	});
	$(".maskLayer").show();		
	if($(".maskLayer").css('display')=='block'){
		$(".maskLayer").fadeTo('fast',0.7);
		$('.maskLayer').height($(document).height());
	}		
	$(".pop_invoice").show();
	$('.pop_invoice').css({left:function(){
		return ($(window).width()-$(this).width())/2;
	},top:function(){
		return ($(window).height()-$(this).height())/2+$(window).scrollTop();	
	}
});
return false;
}


// --修改发票,与common不同
$("#updateFapiao").click(function(){		
	var fapiaoID = $("#fapiaoId").val();
	var money =$("#kaipiaoAmount").val();
	var capitalMoney =$("#daxieAmount").val();
	var company =$("#kaipiaoCompany").val();
	var departMement =$("#kaipiaodDanwei").val();
	var type =$("#fapiaoType .select-button").val();
	var name =$("#pinming").text();
	var date =$("#kaipiaoDate").val();
	var yujihuikuanDate = $("#yujihuikuanDate").val();//预计回款时间
	var remark = $("#remarkAboutFapiao").val();
	//行政比销售多五个字段
	var huiKuan = $("#huikuanAmount").val();//回款金额
	var receivedpaymentsdate = $("#huikuanDate").val();//回款日期
	var fundType = $("#zijin .select-button").val();//资金类型
	var account = $("#zhanghu .select-button").val();//账户
	var caiwuMonth = $("#caiwuyuefen").val();
	var b = validateFapiaoAmount();
	if(b==false){
		return;
	}
	//行政可以修改合同编号
	var hetongNumber =$("#htNum").text();//合同编号
	var hetongId = $("#HtForFapiao").val();//从弹框获取的合同ID		
	if(fundType==undefined){
		fundType = "";
	}
	if(account==undefined){
		account = "";
	}
	var data ={"money":money,"capitalMoney":capitalMoney,
			"company":company,"departMement":departMement,"type":type,"name":name,"date":date,
			"remark":remark,"fapiaoID":fapiaoID,"receivedpaymentsdate":receivedpaymentsdate,"huiKuan":huiKuan,"fundType":fundType,"account":account,"yujihuikuanDate":yujihuikuanDate,"caiwuMonth":caiwuMonth,"hetongId":hetongId};
	
	$.post('public/ht/doUpdateFapiao.action',data,function(res){
		 if(res.success==true){
			//如果没修改合同编号
			if(Number(hetongNumber)==Number(hetongId)){
				 tr.children('td').eq(0).text(fapiaoID);
				 tr.children('td').eq(1).text(date);
				 tr.children('td').eq(2).text(company);
				 tr.children('td').eq(3).text(departMement);
				 tr.children('td').eq(4).text(type);
				 tr.children('td').eq(5).text(name);
				 tr.children('td').eq(6).text(money);
				 tr.children('td').eq(7).text(yujihuikuanDate);
				 tr.children('td').eq(8).text(huiKuan);
				 tr.children('td').eq(9).text(receivedpaymentsdate);
				 tr.children('td').eq(10).text(remark);
				 tr.children('td').eq(11).text(fundType);
				 tr.children('td').eq(12).text(account);
				 $(".pop_invoice").hide();
				 $(".maskLayer").hide();
			}else{
				 tr.remove();
				$(".pop_invoice").hide();
				$(".maskLayer").hide();
			}
		 }else{
			$.messager.alert('提示：',res.msg);
		 }
	});
})

// --删除发票,与common相同
$("#deleteFapiao").click(function(){
	var fapiaoID = $("#fapiaoId").val();
	$.messager.confirm("提示","您确认要删除当前发票吗？",function(){
	   $.post('public/ht/deleteFaPiao.action',{"fapiaoID":fapiaoID},function(res){
		   if(res.success==true){
			   tr.remove();
			  /* $.messager.alert('提示：',res.msg);*/
			   $(".pop_invoice").hide();
			   $(".maskLayer").hide();
			 }else{
			   $.messager.alert('提示：',res.msg);
		   }
       })
	})
});	


//-------------------------------------------------------------快递------------------------------------------------------------------
//行政不需要添加快递
/*	//点击新增快递按钮	
	$("#addFahuo").click(function(){
	$("#sureAboutFaHuo").attr("style","display:inline;");
	$("#updateFahuo").attr("style","display:none;");
	$("#deleteFahuo").attr("style","display:none;");
	
	$("#receiver").val("");
	$("#tel").val("");
	$("#email").val("");
	$("#receiveAddress").val("");
	$("#post").val("");
	$("#postAddress").val("");
	$("#postDate").val("");
	$("#expressCom .select-button").val("EMS");
	$("#expressContent").val("");
	
	//合同编号
	var id = $("#htNum").text();
	var date = new Date();
	var currentTime = date.Format("yyyy-MM-dd");
	//获取发件地
	//获取所属公司
	var company = $("#company").val();
	if(company!=""){
		$.post('public/ht/getFajiandi.action',{"company":company},function(res){
			 if(res.success==true){
				
				var companyInfo = res.obj;
				$("#postAddress").val(companyInfo.address);
			 }else{
				$("#postAddress").val("");
			 }
			 
		}); 
	}
	
	$("#htNumRelationFaHuo").text(id);
	$("#applicationTimeAboutFahuo").text(currentTime);
	
	})


//添加快递
$("#sureAboutFaHuo").click(function(){
	var mailno = $("#expressNum").val();
	var hetongCode = $("htNumRelationFaHuo").text();
	var hetongCode = $("#htNum").text();
	var d_contact = $("#receiver").val();
	var d_tel = $("#tel").val();
	var d_address = $("#receiveAddress").val();
	var jDate = $("#postDate").val();
	var postMethod = $("#expressCom .select-button").val();
	var content = $("#expressContent").val();//快递内容
	var postAddress = $("#postAddress").val();//发件地
	var applicationDate = $("#applicationTimeAboutFahuo").text();
	if(hetongCode==""){
		$.messager.alert('提示：','请填写完合同和产品信息再添加快递');
		return;
	}
	if(d_contact==""){
	$.messager.alert('提示：','请填写收件人信息');
	return;
	}
	if(d_tel==""){
	$.messager.alert('提示：','请填写联系电话');
	return;
	}
	if(d_address==""){
	$.messager.alert('提示：','请填写收件地址');
	return;	
	}
	if(postAddress==""){
		$.messager.alert('提示：','请填写发件地');
		return;	
	}
	if(jDate==""){
		$.messager.alert('提示：','请填写邮寄日期');
		return;	
	}
	if(content==""){
		$.messager.alert('提示：','请填写快递内容');
		return;	
	}
	
	var data = {"mailno":mailno,"hetongCode":hetongCode,"d_contact":d_contact,"d_tel":d_tel,
			"d_address":d_address,
			"jDate":jDate,"postMethod":postMethod,"content":content,"applicationDate":applicationDate,"postAddress":postAddress}
	  $.post('public/ht/addFaHuo.action',data,function(res){
			if(res.success==true){
				var a = res.obj.mailno;
				if(a==null){
					 a ="";
				}
			var tr = "<tr><td>"+res.obj.orderid+"</td><td>"+res.obj.d_contact+"</td><td>"+res.obj.d_address+"</td><td>"+res.obj.d_tel+"</td><td>"+res.obj.jDate+"</td>" +
					"<td>"+res.obj.content+"</td><td>"+res.obj.postMethod+"</td><td>"+a+"</td><td><a id="+res.obj.orderid+" class='dele' href='javascript:;' onclick='updateFahuo("+res.obj.orderid+",this)'>修改</a></td></tr>";	
			$("#tableAboutFahuo").append(tr);	
			$(".pop_courier").hide();	
			$(".maskLayer").hide();
			}else{
				$.messager.alert('提示：',res.msg);
			}
			})
})*/

// --更新快递弹窗(回显),与common不同
function updateFahuoXinzheng(id,obj){
	tr = $(obj).parent().parent();
	//确定按钮隐藏    修改删除按钮显示
	$("#sureAboutFaHuo").attr("style","display:none");
	$("#updateFahuoXingzheng").attr("style","display:inline");
	$("#deleteFahuo").attr("style","display:inline");
//	var postAddress = $("#postAddress").val();
	var data ={"fahuoId":id}  
	$.post('public/ht/toUpdateFahuo.action',data,function(res){		
		 if(res.success==true){
			var faHuo = res.obj;
			var date2 = "";
			if(faHuo.applicationDate!=null&&faHuo.applicationDate!=""){
				date2 = new Date(faHuo.applicationDate).Format("yyyy-MM-dd")
			}
			$("#htNumRelationFaHuo").val(faHuo.hetongCode);  
			$("#applicationTimeAboutFahuo").text(date2);
	        $("#receiver").val(faHuo.d_contact);
			$("#tel").val(faHuo.d_tel);
			$("#receiveAddress").val(faHuo.d_address);
			$("#postAddress").val(faHuo.postAddress);
			$("#expressCom .select-button").attr("value",faHuo.postMethod);
			$("#expressContent").val(faHuo.content);
			$("#fahuoId").attr("value",faHuo.orderid);
			//行政比销售多两个字段
			$("#expressNum").val(faHuo.mailno);  //快递编号
		//	$("#remarkAboutExpress").val(faHuo.remark);
			$("#postDate").val(faHuo.jDate);  //邮寄日期
		 }
	}); 
	$(".maskLayer").show();		
	if($(".maskLayer").css('display')=='block'){
		$(".maskLayer").fadeTo('fast',0.7);
		$('.maskLayer').height($(document).height());
	}		
	$(".pop_courier").show();
	$('.pop_courier').css({left:function(){
		return ($(window).width()-$(this).width())/2;
	},top:function(){
		return ($(window).height()-$(this).height())/2+$(window).scrollTop();	
	}
	});
	return false;
}

// --修改快递,与common不同
$("#updateFahuoXingzheng").click(function(){
	var fahuoId = $("#fahuoId").val();
	var d_contact = $("#receiver").val();
	var d_tel = $("#tel").val();
	var d_address = $("#receiveAddress").val();
	var postAddress = $("#postAddress").val();  //发件地
	var postMethod = $("#expressCom .select-button").val();
	var content = $("#expressContent").val();
	//行政比销售多两个字段,加合同编号就三个
	var jDate = $("#postDate").val();//邮寄日期
	var htNum = $("#htNum").text();//合同编号
	var mailno = $("#expressNum").val();//运单号
	var hetongCode = $("#htNumRelationFaHuo").val();//合同编号,取输入框的,不能取页面的,可能修改了
	
	var data ={"mailno":mailno,"d_contact":d_contact,
			"d_tel":d_tel,"d_address":d_address,"jDate":jDate,"postMethod":postMethod,"content":content,"fahuoId":fahuoId,"hetongCode":hetongCode,"postAddress":postAddress};
	var reg = new RegExp("^[0-9]*$");
	if(reg.test(hetongCode)){
		$.post('public/ht/doUpdateFahuoXingzheng.action',data,function(res){
			 if(res.success==true){
				 if(Number(htNum)==Number(hetongCode)){
					 /* $.messager.alert('提示：',res.msg);*/
					 tr.children('td').eq(0).text(fahuoId);
					 tr.children('td').eq(1).text(d_contact);
					 /*tr.children('td').eq(2).text(d_company);*/
					 tr.children('td').eq(2).text(d_address);
					 tr.children('td').eq(3).text(d_tel);
					 tr.children('td').eq(4).text(content);
					 tr.children('td').eq(5).text(postMethod);
					 tr.children('td').eq(6).text(mailno);
					 tr.children('td').eq(7).text(jDate); 
					 $(".pop_courier").hide();	
					 $(".maskLayer").hide();
				 }else{
					 tr.remove();
					 $(".pop_courier").hide();	
					 $(".maskLayer").hide();
				 }
			 }
		});
	}
})

// --删除快递,与common相同
$("#deleteFahuo").click(function(){
	var fahuoId = $("#fahuoId").val();	
	$.messager.confirm("提示","您确认要删除当前快递吗？",function(){
		  $.post('public/ht/deleteFahuo.action',{"fahuoId":fahuoId},function(res){
			   if(res.success==true){
				   tr.remove();
				  /* $.messager.alert('提示：',res.msg);*/
				   $(".pop_courier").hide();	
				   $(".maskLayer").hide();
				 }else{
					 $.messager.alert('提示：',res.msg);
			   }
	       })
	   })
   });

// --通过按钮,把合同状态变成2
  $("#pass").click(function(){
	 var id = $("#htNum").text();
	 $.post('public/ht/updateContractCondition.action',{"id":id},function(res){
		if(res.success==true){
			$.messager.alert('提示：',res.msg);
			$.get('public/ht/contractList.action',function(result){
				$('#container').html(result);
			})
		}else{
			$.messager.alert('提示：',res.msg);
		}
	}) 
})
// --保存按钮,保存五个字段
$("#saveXingzheng").click(function(){
	var id = $("#htNum").text();
	var yinhuashui = $("#yinhuashui .select-button").val();  //印花税
    var kuaijifenlei = $("#kuaiji .select-button").val();//会计分类
    var guidangNum = $("#guidangfenshu").val();//归档份数
	var huaizhangAmount = $("#huaizhangAmount").val();  //坏账金额
    var dengjiTime = $("#dengjiTime").val();//登记时间
//	var company = $("#company2").val();//所属公司
	 $.post('public/ht/updateContractXingzheng.action',{"id":id,"yinhuashui":yinhuashui,"huaizhangAmount":huaizhangAmount,"guidangNum":guidangNum,"kuaijifenlei":kuaijifenlei,"dengjiTime":dengjiTime},function(res){
		 if(res.success==true){
	    	$.messager.alert('提示：',res.msg);
	    	/*$.get('public/ht/contractList.action',function(result){
				$('#container').html(result);
			})*/
		 }else{
			 $.messager.alert('提示：',res.msg);
		 }
	 })
})

//  --未通过   更新合同状态  添加错误消息
$("#noPass").click(function(){
	var id = $("#htNum").text();
	var company = $("#company").val();//所属公司
	var depart =$("#danweiName").val();//单位名称
//	var dengjiTime = $("#dengjiTime").val();
//	var operator = $("#shenqingren").val();
//	var lianxifangshi = $("#telphone").val();
	var contractMoney = $("#contractAmountError").val();//合同金额
	var endTime = $("#endTime").val();//合同到期时间
	var productInfo = $("#productInfo").val();
	var fapiaoStatus = $("#fapiaoCondition").val();
	var youjiStatus = $("#kuaidiCondition").val();
	var agreementText = $("#htgaiyao").val();  //合同概要
	var payMethod = $("#payMethod").val();  //付款方式
	/* var remark = $("#remark").val(); */
	var data = {"id":id,"company":company,"depart":depart,
			"contractMoney":contractMoney,"endTime":endTime,"productInfo":productInfo,"fapiaoStatus":fapiaoStatus,"youjiStatus":youjiStatus,
			"agreementText":agreementText,"payMethod":payMethod};
	$.post('public/ht/addErrorInfo.action',data,function(res){
		
	})
	//更新合同状态
	$.post('public/ht/updatecontractNoPass.action',{"id":id},function(res){
		if(res.success==true){
			$.messager.alert('提示：',res.msg);
			$.get('public/ht/contractList.action',function(result){
				$('#container').html(result);
				})
		}else{
			$.messager.alert('提示：',res.msg);
		}
	}) 
})

// --取消按钮,返回合同列表页
$("#cancel").click(function(){
	$.get('public/ht/contractList.action',function(result){
		$('#container').html(result);
	})
})

// --提示错误消息 
$(".addbnt").click(function(){
	$(this).next().toggle();
})

// --点"回款金额"自动出现开票金额
$("#huikuanAmount").click(function(){
	var kaipiaoAmount = $("#kaipiaoAmount").val();
	$("#huikuanAmount").val(kaipiaoAmount);
})
	
// --点完回款日期自动出现当天日期+邮箱
$("#huikuanDate").click(function(){
	var huikuanDate = $("#huikuanDate").val();
	if(huikuanDate==""){
		var date = new Date()
		var dateFormat = date.Format("yyyy-MM-dd")
		$("#huikuanDate").val(dateFormat);
		//发邮件
		var saleEmail = $("#saleEmail").val();//销售邮箱
		var danweiName = $("#danweiName2").val();//单位名称
		var kaipiaoDate = $("#kaipiaoDate").val();//开票日期
		var pinming = $("#pinming").text(); //品名
		var huikuanAmount = $("#huikuanAmount").val(); //回款金额
		var subject = "到款通知 "+danweiName+kaipiaoDate+"日开的"+pinming+"发票今日已到款,到款金额为"+huikuanAmount;
		if(huikuanAmount==""){
			$.messager.alert('提示：','请输入回款金额！');
		}else{
			window.location.href="mailto:"+saleEmail+"?subject="+subject+"&body="+danweiName+kaipiaoDate+"日开的"+pinming+"发票今日已到款,到款金额为"+huikuanAmount;
		}
	}
})
	
//验证合同金额是否合法
function validateContractAmount(){
var reg = /(^[1-9]([0-9]+)?(\.[0-9]{1,2})?$)|(^(0){1}$)|(^[0-9]\.[0-9]([0-9])?$)/;
 var contractAmount = $("#contractAmount").val();
 if(reg.test(contractAmount)){
	 $("#contractAmountError").text("");
 }else{
	 $("#contractAmountError").text("合同金额请输入阿拉伯数字");
	 return false;
 }
}

//验证发票金额是否合法 obj 是发票ID
function validateFapiaoAmount(obj){
	var reg = /(^[1-9]([0-9]+)?(\.[0-9]{1,2})?$)|(^(0){1}$)|(^[0-9]\.[0-9]([0-9])?$)/;
 	var id = $("#htNum").text();//合同编号
	var kaipiaoAmount = $("#kaipiaoAmount").val();//开票金额
	var htTotalAmount = $("#contractAmount").val();//合同总金额
	//全局变量为-1时,为增加发票     不为-1时  为修改发票
	if(addOrUpdateFapiao=='-1'){
		 var total = 0;
		 var trList = $("#tableAboutFapiao").find("tr");
		    for (var i=0;i<trList.length;i++) {
		        var tdArr = trList.eq(i).find("td");
		        var fapiaoAmount = tdArr.eq(6).text();
		        var total = Number(fapiaoAmount)+Number(total);
		    }
		    
		    if(Number(kaipiaoAmount)+Number(total)>Number(htTotalAmount)){
				 $("#kaipiaoAmountError").text("*开票总金额不能大于合同金额");
				 return false;
			 }
			
		     if(reg.test(kaipiaoAmount)){
		    	 $("#kaipiaoAmountError").text("");
		     }else{
		    	 $("#kaipiaoAmountError").text("*开票金额请输入阿拉伯数字");
		    	 return false;
		     }
		     
		     if(parseInt(kaipiaoAmount)>parseInt(contractAmount)){
		    	 $("#kaipiaoAmountError").text("*开票金额不能大于合同金额");
		    	 return false;
		     }
	}else{
		//不为-1时  为修改发票
		 var total = 0;
		 var fapiaoxiugai;//修改的那条发票金额
		 var trList = $("#tableAboutFapiao").find("tr");
		    for (var i=0;i<trList.length;i++) {
		        var tdArr = trList.eq(i).find("td");
		        var fapiaoAmount = tdArr.eq(6).text();
		        var fapiaoNum = tdArr.eq(0).text();//发票ID
		        if(Number(fapiaoNum)==Number(addOrUpdateFapiao)){
		        	fapiaoxiugai = tdArr.eq(6).text()
		        }
		        var total = Number(fapiaoAmount)+Number(total);
		    }
		    //减去当前ID金额
		    if(Number(kaipiaoAmount)+Number(total)-Number(fapiaoxiugai)>Number(htTotalAmount)){
				 $("#kaipiaoAmountError").text("*开票总金额不能大于合同金额");
				 return false;
			 }
			
		     if(reg.test(kaipiaoAmount)){
		    	 $("#kaipiaoAmountError").text("");
		     }else{
		    	 $("#kaipiaoAmountError").text("*开票金额请输入阿拉伯数字");
		    	 return false;
		     }
		     
		     if(parseInt(kaipiaoAmount)>parseInt(contractAmount)){
		    	 $("#kaipiaoAmountError").text("*开票金额不能大于合同金额");
		    	 return false;
		     }  
	}
}
//获取大写金额	
$("#kaipiaoAmount").blur(function(){
	var kaipiaoAmount = $("#kaipiaoAmount").val();
	var reg = /(^[1-9]([0-9]+)?(\.[0-9]{1,2})?$)|(^(0){1}$)|(^[0-9]\.[0-9]([0-9])?$)/;
	
	if(kaipiaoAmount!=""&&reg.test(kaipiaoAmount)){
		$.post('public/ht/getDaxieAmount.action',{"kaipiaoAmount":kaipiaoAmount},function(res){
			 if(res.success==true){
				number2cnMontrayUnit = res.obj;
				$("#daxieAmount").val(number2cnMontrayUnit);
			 }else{
				 $.messager.alert('提示：',res.msg);
			 }
		});
	}
})

$(function(){
	//登记时间默认为处理当天
	var dengjiTime = new Date();
	var date2 = dengjiTime.Format("yyyy-MM-dd");
	var dengjiTime2 = $("#dengjiTime").val()
	//登记时间已存在,不赋值  不存在，赋当天日期
	if(dengjiTime2==""){
		$("#dengjiTime").val(date2);
	}
	
	//坏账金额默认为0
	var huaizhangAmount = $("#huaizhangAmount").val();
	if(huaizhangAmount==""){
		$("#huaizhangAmount").val("0");
	}
})

// --保存归档时间和归档编号,归档时间 点击 直接显示为当天日期   生成归档编号  发送邮件信息
$("#guidangDate").click(function(){
	//归档时间如果为空串,点击归档时间 生成当天日期并且生成归档编号     如果已经有归档时间，则不生成归档编号(可以考虑把归档时间改为不可编辑状态)
	var guidangDate = $("#guidangDate").val();
    var company = $("#company2").val();//所属公司	
    var id = $("#htNum").text();//合同编号
    var guidangNum = $("#guidangNum").val();//归档编号
	if(guidangDate==''){
		var guidangTime = new Date();
		var date2 = guidangTime.Format("yyyy-MM-dd");
		$("#guidangDate").val(date2);
		var tijiaoTime = $("#tijiaoTime").val();
		var tijiaoTime2 = "";
		if(tijiaoTime!=null&&tijiaoTime!=""){
			tijiaoTime2 = new Date(tijiaoTime).Format("yyyy-MM-dd");
		}
		//发送邮件  	<a id="btn_guidangMailto" href="mailto:714757501@qq.com?body=邮件内容" style="width:60px;display:none; "  class="easyui-linkbutton">发送邮件</a>
		var saleEmail = $("#saleEmail").val();//销售邮箱
		var danweiName = $("#danweiName2").val();//单位名称
		var subject = "通知:"+danweiName+tijiaoTime2+"登记的合同今天收到！";
		window.location.href="mailto:"+saleEmail+"?subject="+subject+"&body="+danweiName+tijiaoTime2+"登记的合同今天收到！";
		
		var data ={"date2":date2,"company":company,"id":id};
		if(guidangNum=='合同未回'){
			//生成归档编号
			$.post("public/ht/generateGuidangnum.action",data,function(res){
				if(res.success==true){
					var guidangCode = res.obj;
					$("#guidangNum").val(guidangCode);
				}else{
					$.messager.alert('提示：',res.msg);
				}
			})
		}
	}
})

//通过未通过按钮的显示与隐藏
$(function(){
	var dealCondition = $("#dealCondition").val();
	if(dealCondition=='2'){
		//审核已通过,通过按钮隐藏
		$("#pass").attr("style","display:none;");
	}
})

Date.prototype.Format = function(format){ 

	var o = { 

	"M+" : this.getMonth()+1, //month 

	"d+" : this.getDate(), //day 

	"h+" : this.getHours(), //hour 

	"m+" : this.getMinutes(), //minute 

	"s+" : this.getSeconds(), //second 

	"q+" : Math.floor((this.getMonth()+3)/3), //quarter 

	"S" : this.getMilliseconds() //millisecond 

	}

	if(/(y+)/.test(format)) { 

	format = format.replace(RegExp.$1, (this.getFullYear()+"").substr(4 - RegExp.$1.length)); 

	}

	for(var k in o) { 

	if(new RegExp("("+ k +")").test(format)) { 

	format = format.replace(RegExp.$1, RegExp.$1.length==1 ? o[k] : ("00"+ o[k]).substr((""+ o[k]).length)); 

	 } 

	} 

	return format; 

	}

	$(function(){
		$("input").attr("autocomplete","off");
	})
