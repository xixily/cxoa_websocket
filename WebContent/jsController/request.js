/**
 * 做一些常用的，且不需要经常更新的请求，第一次调用， 
 * 把请求结果存到session里面，减少对后台的访问。
 */
var request = {

	getJson : function(url, data, callback) {
		if (url && url != '') {
			var data = data ? data : {};
			$.getJSON(url, data, callback(result))
		} else {
			$.messager.alert('请求信息', '没有请求地址', 'request_error');
		}
	},
	/**
	 * @param url {String}
	 * @param data {Object}
	 * @param callback {function}
	 * @param async {boolean}
	 * @param timeout {Integer}
	 * @returns {Boolean}
	 */
	getRequest : function(url, data, callback, async, timeout) {
		if (!url || url == '') {
			$.messager.alert('请求信息', '没有请求地址', 'request_error');
			return false;
		}
		var data = data ? data : {};
		var timeout = timeout ? timeout : 20000;
		var async = async ? async : true;
		$.ajax({
			type : "GET",
			url : url,
			async : async,
			data : data,
			timeout : timeout,
			dataType : "json",
			success : function(data) {
				if (typeof callback == "function") {
					callback(data);
				}
			},
			error : function(xhr, status, error) {
				$.messager.alert('请求超时或网络问题');
			}
		});
	},
	/**
	 * @param url {String}
	 * @param data {Object}
	 * @param callback {function}
	 * @param async {boolean}
	 * @param timeout {Integer}
	 * @returns {Boolean}
	 */
	postRequest : function(url, data, callback, async, timeout) {
		if (!url || url == '') {
			$.messager.alert('请求信息', '没有请求地址', 'request_error');
			return false;
		}
		var data = data ? data : {};
		var timeout = timeout ? timeout : 20000;
		var async = async ? async : true;
		$.ajax({
			type : "POST",
			url : url,
			async : async,
			data : data,
			timeout : timeout,
			dataType : "json",
			success : function(data) {
				if (typeof callback == "function") {
					callback(data);
				}
			},
			error : function(xhr, status, error) {
				$.messager.alert('请求超时或网络问题');
			}
		});
	},
	/**
	 * 获得公司名称
	 * @param callback
	 * @param queryParams
	 * @param refresh
	 */
	getCompany : function(data, refresh) {
		var url = "employee/getCompany.action";
		if(session.company&&session.compamy.length>0){
			return session.company;
		}
		request.getJson(url, data, function(result){
			return result;
		});
	},
	getInsuranceCompany : function(callback, queryParams, refresh) {
		var url = "employee/getInsuranceCompany.action";
		if(session.company&&session.compamy.length>0){
			return session.insuranceCompany;
		}
		request.getJson(url, data, function(result){
			return result;
		});
	},
	getShebaoType : function(callback, queryParams, refresh) {
		var url = "employee/getShebaoType.action";
		if(session.company&&session.compamy.length>0){
			return session.shebaoType;
		}
		request.getJson(url, data, function(result){
			return result;
		});
	}
}