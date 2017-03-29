//jQuery.getJSON(url,data,success(data,status,xhr))
//加载时获取一下五险参数
//wagesCalculate.getRadix();
var wagesCalculate = {
		getAllRadix : function(callback){
			var params = {};
			var url = 'employee/getAllShebaoRadio.action';
			$.getJSON(url,function(result){
				if(result.success){
					callback(result.obj);
				}else{
					$.messager.alert('获取社保比例：','获取社保比例失败！~');
				}
			});
		},
		getRadixByCompany : function(params, callback){
//			var params = {};
//			params.company = company;
			var url = 'employee/getShebaoRadioByCompany.action';
//			if(session.shebaoRadio && (session.shebaoRadio[0].company == params.company)){
//				callback(session.shebaoRadio)
//			}else{
				$.post(url, params, function(result){
//					var result =  eval("(" + result + ")");
					if(result.success){
						session.shebaoRadio = result.obj;
						callback(result.obj);
					}else{
						$.messager.alert('消息', result.msg, 'info');
						callback(false);
					}
				});
//			}
		},
		//TODO 社保  ps:所以在添加的时候一点要做下拉菜单 householdType 一定要正确控制
		/**
		 * @param data data一定包含company,householdType
		 */
		calculateShebao : function(data, callback){
//			var radix = data.radix ? data.radix : 0;
			var oradix = data.radix ? data.radix : 0;
			if(!data.company || data.company == ''){
				data.success = false;
				callback(false);
//				return false;
			}else{
				wagesCalculate.getRadixByCompany(data, function(result){
					if(!result) callback(false);
					if(oradix == 0){
						data.subEndowmentIinsurance = 0;
						data.cEndowmentIinsurance = 0;
						data.subUnemployedInsurance = 0;
						data.cUnemployedInsurance = 0;
						data.cInjuryInsurance = 0;
						data.cBirthIinsurance = 0;
						data.subMedicare = 0;
                		data.cMedicare = 0;
                		callback(data);
//                		return data;
					}else{
						$.each(result,function(n,obj) {
							var radix = oradix;
//							radix = radix < obj.radixMin ? obj.radixMin : radix;
//							radix = radix < obj.radixMin || data.radix > obj.radixMax ? (radix < obj.radixMin ? obj.radixMin : obj.radixMax ): radix;
							if(radix < obj.radixMin){
								radix = obj.radixMin;
							}else if(data.radix > obj.radixMax){
								radix = obj.radixMax;
							}
							if(obj.shebaoType == '养老保险'){
								if(obj.householdType == 'ALL' || (obj.householdType == data.householdType)){
									data.subEndowmentIinsurance = round2((radix * obj.radio + obj.fixedValue),2);
									data.cEndowmentIinsurance = round2((radix * obj.cRadio + obj.cFixedValue),2);
//									data.subEndowmentIinsurance = Number((radix * obj.radio + obj.fixedValue).toFixed(2));
//									data.cEndowmentIinsurance = Number((radix * obj.cRadio + obj.cFixedValue).toFixed(2));
								}
							}else if(obj.shebaoType == '失业保险'){
								if(obj.householdType == 'ALL' || (obj.householdType == data.householdType)){
									data.subUnemployedInsurance = round2((radix * obj.radio + obj.fixedValue),2);
									data.cUnemployedInsurance = round2((radix * obj.cRadio + obj.cFixedValue),2);
//									data.subUnemployedInsurance = Number((radix * obj.radio + obj.fixedValue).toFixed(2));
//									data.cUnemployedInsurance = Number((radix * obj.cRadio + obj.cFixedValue).toFixed(2));
								}
								
							}else if(obj.shebaoType == '工伤保险'){
								if(obj.householdType == 'ALL' || (obj.householdType == data.householdType)){
									data.cInjuryInsurance = round2((radix * obj.cRadio + obj.cFixedValue),2);
//									data.cInjuryInsurance = Number((radix * obj.cRadio + obj.cFixedValue).toFixed(2));
								}
							}else if(obj.shebaoType == '生育保险'){
								if(obj.householdType == 'ALL' || (obj.householdType == data.householdType)){
									data.cBirthIinsurance = round2((radix * obj.cRadio + obj.cFixedValue),2);
//									data.cBirthIinsurance = Number((radix * obj.cRadio + obj.cFixedValue).toFixed(2));
								}
							}else if(obj.shebaoType == '医疗保险'){
								if(obj.householdType == 'ALL' || (obj.householdType == data.householdType)){
									data.subMedicare = round2((radix * obj.radio + obj.fixedValue),2);
									data.cMedicare = round2((radix * obj.cRadio + obj.cFixedValue),2);
//									data.subMedicare = Number((radix * obj.radio + obj.fixedValue).toFixed(2));
//									data.cMedicare = Number((radix * obj.cRadio + obj.cFixedValue).toFixed(2));
								}
							}else if(obj.shebaoType == '公积金'){
								if(obj.householdType == 'ALL' || (obj.householdType == data.householdType)){
									data.subHouseIinsurance = round2((radix * obj.radio + obj.fixedValue),2);
									data.cHouseIinsurance = round2((radix * obj.cRadio + obj.cFixedValue),2);
//									data.subMedicare = Number((radix * obj.radio + obj.fixedValue).toFixed(2));
//									data.cMedicare = Number((radix * obj.cRadio + obj.cFixedValue).toFixed(2));
								}
							}
						})
						data.success = true;
						callback(data);
					}
				});
			}
		},
		calculateShebaoOnly : function(data, callback){
			var oradix = data.radix ? data.radix : 0;
			if(!data.company || data.company == ''){
				callback(false);
			}else{
				wagesCalculate.getRadixByCompany(data, function(result){
					if(!result) callback(false);
					if(oradix == 0){
						data.subEndowmentIinsurance = 0;
						data.cEndowmentIinsurance = 0;
						data.subUnemployedInsurance = 0;
						data.cUnemployedInsurance = 0;
						data.cInjuryInsurance = 0;
						data.cBirthIinsurance = 0;
						data.subMedicare = 0;
                		data.cMedicare = 0;
                		callback(data);
//                		return data;
					}else{
						$.each(result,function(n,obj) {
							var radix = oradix;
							if(radix < obj.radixMin){
								radix = obj.radixMin;
							}else if(data.radix > obj.radixMax){
								radix = obj.radixMax;
							}
							if(obj.shebaoType == '养老基数'){
								if(obj.householdType == 'ALL' || (obj.householdType == data.householdType)){
									data.subEndowmentIinsurance = radix * obj.radio + obj.fixedValue;
									data.cEndowmentIinsurance = radix * obj.cRadio + obj.cFixedValue;
								}
							}else if(obj.shebaoType == '失业基数'){
								if(obj.householdType == 'ALL' || (obj.householdType == data.householdType)){
									data.subUnemployedInsurance = radix * obj.radio + obj.fixedValue;
									data.cUnemployedInsurance = radix * obj.cRadio + obj.cFixedValue;
								}
								
							}else if(obj.shebaoType == '工伤基数'){
								if(obj.householdType == 'ALL' || (obj.householdType == data.householdType)){
									data.cInjuryInsurance = radix * obj.cRadio + obj.cFixedValue;
								}
							}else if(obj.shebaoType == '生育基数'){
								if(obj.householdType == 'ALL' || (obj.householdType == data.householdType)){
									data.cBirthIinsurance = radix * obj.cRadio + obj.cFixedValue;
								}
							}else if(obj.shebaoType == '医疗基数'){
								if(obj.householdType == 'ALL' || (obj.householdType == data.householdType)){
									data.subMedicare = radix * obj.radio + obj.fixedValue;
									data.cMedicare = radix * obj.cRadio + obj.cFixedValue;
								}
							}
						})
						data.success = true;
						callback(data);
					}
				});
			}
		},
		//TODO 失业单位比例
		calculateCShiye : function(){
			
		},
		//TODO 失业个人比例
		calculateShiye : function(){
			
		},
		//TODO 工伤单位比例
		calculateCGongshang : function(){
			
		},
		//TODO 工伤个人比例
		calculateGongshang : function(){
			
		},
		//TODO 生育单位比例
		calculateCShengyu : function(){
			
		},
		//TODO 生育个人比例
		calculateShengyu : function(){
			
		},
		//TODO 医疗单位比例
		calculateCYiliao : function(){
			
		},
		//TODO 医疗个人比例
		calculateYiliao : function(){
			
		}
}