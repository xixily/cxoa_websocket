/**
 * Created by dengxf on 2016/12/7.
 */
/**
 * 将form里面的内容序列化成json
 * 相同的checkbox用分号拼接起来
 * @param {dom} 指定的选择器
 * @param {obj} 需要拼接在后面的json对象
 * @method serializeJson
 * */
(function($){
    //添加校验规则
	function validate(){
		return true;
	}

    //清除表单
    function clear(target){
		$('input,select,textarea', target).each(function(){
			var t = this.type, tag = this.tagName.toLowerCase();
			if (t == 'text' || t == 'hidden' || t == 'password' || t == 'textarea' || t == 'number' || t == 'email' || t == 'date'){
				this.value = '';
			} else if (t == 'file'){
				var file = $(this);
				if (!file.hasClass('textbox-value')){
					var newfile = file.clone().val('');
					newfile.insertAfter(file);
					file.remove();
				}
			} else if (t == 'checkbox' || t == 'radio'){
				this.checked = false;
			} else if (tag == 'select'){
				this.selectedIndex = -1;
			}

		});
		var form = $(target);
//		var opts = $.data(target, 'form').options;
//		for(var i=opts.fieldTypes.length-1; i>=0; i--){
//			var type = opts.fieldTypes[i];
//			var field = form.find('.'+type+'-f');
//			if (field.length && field[type]){
//				field[type]('clear');
//			}
//		}
		form.form('validate');
    }

    //表单提交
    function submit(onSuccess,onError){
        onSuccess();
    }

    //序列化表单值
    function serializeJson(jq,otherString){
		var $this = jq ? $(jq) : this;
		var serializeObj={},
		array=$this.serializeArray();
		$(array).each(function(){
			if(serializeObj[this.name]){
				serializeObj[this.name]+=';'+this.value;
			}else{
				serializeObj[this.name]=this.value;
			}
		});
		
		if(otherString!=undefined){
			var otherArray = otherString.split(';');
			$(otherArray).each(function(){
				var otherSplitArray = this.split(':');
				serializeObj[otherSplitArray[0]]=otherSplitArray[1];
			});
		}
		return serializeObj;
	};
    $.fn.serializeJson = serializeJson;

    //直接表单取值
	function getDataFromForm(jq,otherString) {
		var form = jq ? $(jq) : this;
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
		return data;
	}
    $.fn.getDataFromForm = getDataFromForm;

	/**
	 * 将josn对象赋值给form
	 * @param {dom} 指定的选择器
	 * @param {obj} 需要给form赋值的json对象
	 * @method serializeJson
	 * */
    function setForm(jq,jsonValue){
		var obj = jq ? $(jq):this;
		$.each(jsonValue,function(name,ival){
			var $oinput = obj.find("input[name="+name+"]");
			if($oinput.attr("type")=="checkbox"){
				if(ival !== null){
					var checkboxObj = $("[name="+name+"]");
					var checkArray = ival.split(";");
					for(var i=0;i<checkboxObj.length;i++){
						for(var j=0;j<checkArray.length;j++){
							if(checkboxObj[i].value == checkArray[j]){
								checkboxObj[i].click();
							}
						}
					}
				}
			}
			else if($oinput.attr("type")=="radio"){
				$oinput.each(function(){
					var radioObj = $("[name="+name+"]");
					for(var i=0;i<radioObj.length;i++){
						if(radioObj[i].value == ival){
							radioObj[i].click();
						}
					}
				});
			}
			else if($oinput.attr("type")=="textarea"){
				obj.find("[name="+name+"]").html(ival);
			}
			else{
				obj.find("[name="+name+"]").val(ival);
			}
		})
	}
    $.fn.setForm = setForm;

    //方法调用规则（参照easyui）
    $.fn.form = function(options, params){
        if (typeof options == 'string'){
//            this.each(function(){
//                initForm(this);
//            });
            return $.fn.form.methods[options](this,params);
        }

        return this.each(function(){
            initForm(this, options);
            setForm(this);
        });
    };

    //方法定义
    $.fn.form.methods = {
        serializeJson: function(jq,params){
			jq.each(function(){
				serializeJson(this,params);
			})
		},
        setForm: function(jq,params){
			jq.each(function(){
				setForm(this,params);
			})
		},
        validate: validate,
		clear: function(jq){
			return jq.each(function(){
				clear(this);
			});
		},
        submit:submit
    };

})($)
