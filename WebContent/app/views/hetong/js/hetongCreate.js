//添加产品
$("#sureAboutProduct").click(function(){
	
	var name = $("#chooseProduct .select-button").val();
	var money = $("#productMoney").val();
	var amount =$("#productAmount").val();
	var begain =$("#effectiveDate").val();
	var end =$("#endDate").val();
	var ctid =$("#htNum").text();
	var data = {"name":name,"amount":amount,"begain":begain,
			"end":end,"ctid":ctid,"money":money}
	  $.post('public/ht/addItemPrice.action',data,function(res){
			if(res.success==true){
				/*alert(res.msg);
				$(".popwindow pop_product").removeAttr("style");*/ 
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



//添加快递
$("#sureAboutFaHuo").click(function(){
	var mailno = $("#expressNum").val();
	/*var hetongCode = $("htNumRelationFaHuo").text();*/
	var hetongCode = $("#htNum").text();
	
	var d_contact = $("#receiver").val();
	var d_tel = $("#tel").val();
	/*var d_company = $("#receiveCom").val();*/
	/*var d_post_code = $("#email").val();*/
	var d_address = $("#receiveAddress").val();
	/*var sender = $("#post").val();*/
	var jDate = $("#postDate").val();
	/*var postMethod = $("#expressCom").val();*/
	var postMethod = $("#expressCom .select-button").val();
	/*var remark = $("#remarkAboutExpress").val();*/
	var content = $("#expressContent").val();
	var postAddress = $("#postAddress").val();
	
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
	
	var data = {"mailno":mailno,"hetongCode":hetongCode,"d_contact":d_contact,"d_tel":d_tel,
			"d_address":d_address,
			"jDate":jDate,"postMethod":postMethod,"content":content,"applicationDate":applicationDate,"postAddress":postAddress}
	  $.post('public/ht/addFaHuo.action',data,function(res){
			if(res.success==true){
			var tr = "<tr><td>"+res.obj.orderid+"</td><td>"+res.obj.d_contact+"</td><td>"+res.obj.d_address+"</td><td>"+res.obj.d_tel+"</td><td>"+res.obj.jDate+"</td>" +
					"<td>"+res.obj.content+"</td><td>"+res.obj.postMethod+"</td><td>"+res.obj.mailno+"</td><td><a id="+res.obj.orderid+" class='dele' href='javascript:;' onclick='updateFahuo("+res.obj.orderid+",this)'>修改</a></td></tr>";	
			$("#tableAboutFahuo").append(tr);	
			$(".pop_courier").hide();	
			$(".maskLayer").hide();
			}else{
				$.messager.alert('提示：',res.msg);
			}
			})
})




//添加发票
$("#sureAboutFapiao").click(function(){
	var hetongNumber =$("#htNum").text();//合同编号
	var money =$("#kaipiaoAmount").val();
	var flag = true;
	var b = validateFapiaoAmount();
	if(b==false){
		flag = false;
	}
	if(hetongNumber==""){
		flag = false;
		$.messager.alert('提示：','请填写完合同和产品信息再添加发票');
		return;
	}
	if(money==""){
		flag = false;
		$.messager.alert('提示：','请填写发票金额');
	}
	
	if(flag == false){
		return;
	}
	
	var applicationDate =$("#applicationTimeAboutFapiao").text(); //申请时间
	var money =$("#kaipiaoAmount").val();
	var capitalMoney =$("#daxieAmount").val();
	var company =$("#kaipiaoCompany").val();
	var departMement =$("#kaipiaodDanwei").val();
	/*$("#fapiaoType").val();*/
	var type = $("#fapiaoType .select-button").val();
	var name =$("#pinming").val();
	var date =$("#kaipiaoDate").val();
	var remark = $("#remarkAboutFapiao").val();
	
	
	var data ={"hetongNumber":hetongNumber,"applicationDate":applicationDate,"money":money,"capitalMoney":capitalMoney,"company":company,"departMement":departMement,"type":type,"name":name,"date":date,"remark":remark};
	  $.post('public/ht/addFaPiao.action',data,function(res){
			if(res.success==true){
				var date = res.obj.date;
				var date2 = new Date(date).Format("yyyy-MM-dd");
				var a = res.obj.huiKuan;
				var b = res.obj.receivedpaymentsdate
				if(a==null){
					 a ="";
				}
				if(b==null){
					 b ="";
				}
				var tr = "<tr><td>"+res.obj.id+"</td><td>"+date2+"</td><td>"+res.obj.company+"</td><td>"+res.obj.departMement+"</td><td>"+res.obj.type+"</td><td>"+res.obj.name+"</td>" +
				"<td>"+res.obj.money+"</td><td>"+a+"</td><td>"+b+"</td><td>"+res.obj.remark+"</td><td><a id="+res.obj.id+" class='dele' href='javascript:;' onclick='updateFapiao("+res.obj.id+",this)'>修改</a></td></tr>";	
				$("#tableAboutFapiao").append(tr);	
				$(".pop_invoice").hide();
				$(".maskLayer").hide();
			}else{
				$.messager.alert('提示：',res.msg);
			}
		})
})

//更新产品
var tr;
function updateProduct(id,obj){
    tr = $(obj).parent().parent();
    console.log(tr);
	//确定按钮隐藏    修改删除按钮显示
	$("#sureAboutProduct").attr("style","display:none");
	$("#updateProduct").attr("style","display:inline");
	$("#deleteProduct").attr("style","display:inline");
	var data ={"itemPriceId":id}
	
	$.post('public/ht/toUpdateItemPrice.action',data,function(res){
		
		 if(res.success==true){
			var ItemPrice = res.obj;
			
	        $("#productMoney").val(ItemPrice.money);
			$("#productAmount").val(ItemPrice.amount);
			$("#effectiveDate").val(ItemPrice.begain);
			$("#endDate").val(ItemPrice.end);
			$("#productID").attr("value",ItemPrice.id);
			$("#chooseProduct .select-button").attr("value",ItemPrice.name)
		 }
	}); 
	
$(".maskLayer").show();		
	if($(".maskLayer").css('display')=='block'){
		$(".maskLayer").fadeTo('fast',0.7);
		$('.maskLayer').height($(document).height());
	}		
	$(".pop_product").show();
	$('.pop_product').css({left:function(){
		return ($(window).width()-$(this).width())/2;
	},top:function(){
		return ($(window).height()-$(this).height())/2+$(window).scrollTop();	
	}
});

return false;
}

//修改产品
$("#updateProduct").click(function(){
	var productID = $("#productID").val();
	
	var name = $("#chooseProduct .select-button").val();
	var money = $("#productMoney").val();
	var amount =$("#productAmount").val();
	var begain =$("#effectiveDate").val();
	var end =$("#endDate").val();
	
	var data = {"name":name,"amount":amount,"begain":begain,
			"end":end,"money":money,"productID":productID}
	
	$.post('public/ht/doUpdateItemPrice.action',data,function(res){
		
		 if(res.success==true){
			 $.messager.alert('提示：',res.msg);
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

//删除产品
$("#deleteProduct").click(function(){
	var productID = $("#productID").val();
	$.messager.confirm("提示","您确认要删除当前产品吗？",function(){
		$.post('public/ht/deleteItemPrice.action',{"itemPriceId":productID},function(res){
			   
			   if(res.success==true){
				   //如何操作？
				   tr.remove();
				   $.messager.alert('提示：',res.msg);
				   $(".pop_product").hide();	
				   $(".maskLayer").hide();
				 }else{
					 $.messager.alert('提示：',res.msg);
			   }
	       })
	})
	  
});	


//更新发票(回显)
function updateFapiao(id,obj){
	tr = $(obj).parent().parent();
	
	//确定按钮隐藏    修改删除按钮显示
	$("#sureAboutFapiao").attr("style","display:none");
	$("#updateFapiao").attr("style","display:inline");
	$("#deleteFapiao").attr("style","display:inline");
	var data ={"fapiaoId":id}
	
	//合同编号
	var id = $("#htNum").text();
	var date = new Date();
	var currentTime = date.Format("yyyy-MM-dd");
/*	//所属公司
	var company = $("#company").val();
	//单位名称
	var danwei = $("#danwei .select-button").val();*/
	$("#HtForFapiao").text(id);
	$("#applicationTimeAboutFapiao").text(currentTime);
	/*$("#kaipiaoCompany").val(company);
	$("#kaipiaodDanwei").val(danwei);*/

	$.post('public/ht/toUpdateFaPiao.action',data,function(res){
		
		 if(res.success==true){
		 var fapiao = res.obj;
		 var date2 = new Date(fapiao.date).Format("yyyy-MM-dd")
			/*var hetongNumber =$("#htNum").text();//合同编号
			var applicationDate =$("#applicationTimeAboutFapiao").text(); //申请时间*/
			$("#kaipiaoAmount").val(fapiao.money);
			$("#daxieAmount").val(fapiao.capitalMoney);
			$("#kaipiaoCompany").val(fapiao.company);
			$("#kaipiaodDanwei").val(fapiao.departMement);
			$("#fapiaoType").val(fapiao.type);
			$("#pinming").val(fapiao.name);
			$("#kaipiaoDate").val(date2);
			$("#remarkAboutFapiao").val(fapiao.remark);
			$("#fapiaoId").attr("value",fapiao.id);
			$("#fapiaoType .select-button").attr("value",fapiao.type);
			
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

//修改发票
$("#updateFapiao").click(function(){
	/*var flag = true;
	var b = validateFapiaoAmount();
	if(b==false){
		flag = false;
	}
	if(flag == false){
		return;
	}*/
	
	/*var hetongNumber =$("#htNum").text();//合同编号
	var applicationDate =$("#applicationTimeAboutFapiao").text(); //申请时间*/
	var fapiaoID = $("#fapiaoId").val();
	var money =$("#kaipiaoAmount").val();
	var capitalMoney =$("#daxieAmount").val();
	var company =$("#kaipiaoCompany").val();
	var departMement =$("#kaipiaodDanwei").val();
	var type =$("#fapiaoType .select-button").val();
	var name =$("#pinming").val();
	var date =$("#kaipiaoDate").val();
	var remark = $("#remarkAboutFapiao").val();
	var receivedpaymentsdate = $("#huikuanDate").val();//回款日期
	var huiKuan = $("#huikuanAmount").val();//回款金额
	
	var fundType = $("#zijin .select-button").val();//资金类型
	var account = $("#zhanghu .select-button").val();//账户
	
	if(fundType==undefined){
		fundType = "";
	}
	console.log(fundType);
	if(account==undefined){
		account = "";
	}
	console.log(account);

	var data ={"money":money,"capitalMoney":capitalMoney,
			"company":company,"departMement":departMement,"type":type,"name":name,"date":date,
			"remark":remark,"fapiaoID":fapiaoID,"receivedpaymentsdate":receivedpaymentsdate,"huiKuan":huiKuan,"fundType":fundType,"account":account};
	
	$.post('public/ht/doUpdateFapiao.action',data,function(res){
		
		 if(res.success==true){
			 $.messager.alert('提示：',res.msg);
			
			 tr.children('td').eq(0).text(fapiaoID);
			 tr.children('td').eq(1).text(date);
			 tr.children('td').eq(2).text(company);
			 tr.children('td').eq(3).text(departMement);
			 tr.children('td').eq(4).text(type);
			 tr.children('td').eq(5).text(name);
			 tr.children('td').eq(6).text(money);
			 tr.children('td').eq(7).text(huiKuan);
			 tr.children('td').eq(8).text(receivedpaymentsdate);
			 tr.children('td').eq(9).text(remark);
			 
			 $(".pop_invoice").hide();
			 $(".maskLayer").hide();
	        
		 }else{
			$.messager.alert('提示：',res.msg);
		 }
	});
})


//删除发票
$("#deleteFapiao").click(function(){
	var fapiaoID = $("#fapiaoId").val();
	
	$.messager.confirm("提示","您确认要删除当前发票吗？",function(){
		
		   $.post('public/ht/deleteFaPiao.action',{"fapiaoID":fapiaoID},function(res){
			   if(res.success==true){
				   //如何操作？
				   tr.remove();
				   $.messager.alert('提示：',res.msg);
				   $(".pop_invoice").hide();
				   $(".maskLayer").hide();
				 }else{
				   $.messager.alert('提示：',res.msg);
			   }
	       })
		
	  })
});	


//-------------------------------------------------------------快递------------------------------------------------------------------

//更新快递(回显)
function updateFahuo(id,obj){
	tr = $(obj).parent().parent();
	//确定按钮隐藏    修改删除按钮显示
	$("#sureAboutFaHuo").attr("style","display:none");
	$("#updateFahuo").attr("style","display:inline");
	$("#deleteFahuo").attr("style","display:inline");
	var postAddress = $("#postAddress").val();
	var data ={"fahuoId":id}
  
	$.post('public/ht/toUpdateFahuo.action',data,function(res){
		
		 if(res.success==true){
			var faHuo = res.obj;
			
			var date2 = new Date(faHuo.applicationDate).Format("yyyy-MM-dd")
			console.log(faHuo);
			$("#htNumRelationFaHuo").text(faHuo.hetongCode);
			$("#applicationTimeAboutFahuo").text(date2);
	        $("#receiver").val(faHuo.d_contact);
			$("#tel").val(faHuo.d_tel);
			/*$("#receiveCom").val(faHuo.d_company);*/
			$("#email").val(faHuo.d_post_code);
			$("#receiveAddress").val(faHuo.d_address);
			$("#post").val(faHuo.sender);
			$("#postAddress").val(faHuo.postAddress);
			$("#postDate").val(faHuo.jDate);
			/*$("#expressCom").val(faHuo.postMethod);*/
			/*$("#expressCom .select-button").val(faHuo.postMethod);*/
			$("#expressCom .select-button").attr("value",faHuo.postMethod);
			$("#expressNum").val(faHuo.mailno);
			$("#expressContent").val(faHuo.content);
			$("#remarkAboutExpress").val(faHuo.remark);
			$("#fahuoId").attr("value",faHuo.orderid);
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

//修改快递
$("#updateFahuo").click(function(){
	var fahuoId = $("#fahuoId").val();
	
	var mailno = $("#expressNum").val();
	/*var hetongCode = $("#htNum").text();*/
	var d_contact = $("#receiver").val();
	var d_tel = $("#tel").val();
	/*var d_company = $("#receiveCom").val();*/
	/*var d_post_code = $("#email").val();*/
	var d_address = $("#receiveAddress").val();
	//var sender = $("#post").val();
	var jDate = $("#postDate").val();
	/*var postMethod = $("#expressCom").val();*/
	var postMethod = $("#expressCom .select-button").val();
	//var remark = $("#remarkAboutExpress").val();
	var content = $("#expressContent").val();
	/*var applicationDate = $("#applicationTimeAboutFahuo").text();*/

	var data ={"mailno":mailno,"d_contact":d_contact,
			"d_tel":d_tel,"d_address":d_address,"jDate":jDate,"postMethod":postMethod,"content":content,"fahuoId":fahuoId};
	
	$.post('public/ht/doUpdateFahuo.action',data,function(res){
		
		 if(res.success==true){
			 $.messager.alert('提示：',res.msg);
			 
			 tr.children('td').eq(0).text(fahuoId);
			 tr.children('td').eq(1).text(d_contact);
			 /*tr.children('td').eq(2).text(d_company);*/
			 tr.children('td').eq(2).text(d_address);
			 tr.children('td').eq(3).text(d_tel);
			 tr.children('td').eq(4).text(jDate);
			 tr.children('td').eq(5).text(content);
			 tr.children('td').eq(6).text(postMethod);
			 tr.children('td').eq(7).text(mailno);
			 
			 $(".pop_courier").hide();	
			 $(".maskLayer").hide();
	        
		 }
	});
})

//删除快递
$("#deleteFahuo").click(function(){
	var fahuoId = $("#fahuoId").val();
	
	$.messager.confirm("提示","您确认要删除当前快递吗？",function(){
		  $.post('public/ht/deleteFahuo.action',{"fahuoId":fahuoId},function(res){
			   if(res.success==true){
				   //如何操作？
				   tr.remove();
				   $.messager.alert('提示：',res.msg);
				   $(".pop_courier").hide();	
				   $(".maskLayer").hide();
				 }else{
					 $.messager.alert('提示：',res.msg);
			   }
	       })
	   })
   });	



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

