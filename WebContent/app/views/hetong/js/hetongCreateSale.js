/*----------------------------------------------------产品--------------------------------------------------------------*/
//点添加产品按钮时，创建合同对象
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
	var id = $("#htNum").text();//合同编号
	if(id==""||null==id){
		 $.get('public/ht/addRealContract.action',function(res){
			 if(res.success==true){
				var htId =  res.obj;
				console.log(htId);
				$("#htNum").text(htId);
			 }
			 
		}); 
	}else{
	}
	})

//添加产品
$("#sureAboutProduct").click(function(){
	var name = $("#chooseProduct .select-button").val();
	var money = $("#productMoney").val();
	var amount =$("#productAmount").val();
	var begain =$("#effectiveDate").val();
	var end =$("#endDate").val();
	var ctid =$("#htNum").text();
	
	/*if(name=='第三方产品'){
		name = '第三方产品('+disanfangChanpin+')';
	}*/
	var data = {"name":name,"amount":amount,"begain":begain,
			"end":end,"ctid":ctid,"money":money};
	 //保存合同概要
	if(name=='第三方产品'){
	  var htgaiyao = $("#disanfangChanpin").val();
	  /*$("#contractContent").append(htgaiyao);*/
	  var contractContent = $("#contractContent").val();
	  var contractContentFinal = contractContent+htgaiyao;
	  
	  $("#contractContent").val(contractContentFinal);
	  $.post('public/ht/addHtgaiyao.action',{"htgaiyao":htgaiyao,"ctid":ctid},function(res){
			
	  })
	}		
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

//更新产品(回显)
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
	
	
	$.post('public/ht/toUpdateItemPrice.action',data,function(res){
		//第三方产品(999)
		 if(res.success==true){
			var ItemPrice = res.obj;
			var name = ItemPrice.name;
			/*var subName = name.substring(6,name.length-1);*/
			var start = name.indexOf("第三方产品");
			//是第三方产品
			if(start==0){
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
	var id = $("#htNum").text();//合同编号
	
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
	
	var data = {"name":name,"amount":amount,"begain":begain,
			"end":end,"money":money,"productID":productID}
	
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

//删除产品
$("#deleteProduct").click(function(){
	var productID = $("#productID").val();
	$.messager.confirm("提示","您确认要删除当前产品吗？",function(){
		$.post('public/ht/deleteItemPrice.action',{"itemPriceId":productID},function(res){
			   
			   if(res.success==true){
				   //如何操作？
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
	//点击增开发票
	$("#addFapiao").click(function(){
	var ul=$(".selectNew");
	if(ul.css("display")!="none"){
	//	ul.css("display","none");
		ul.hide();;
	}
	$("#sureAboutFapiao").attr("style","display:inline;");
	$("#updateFapiao").attr("style","display:none;");
	$("#deleteFapiao").attr("style","display:none;");
	
	
	
	$("#kaipiaoAmount").val("");
	$("#daxieAmount").val("");
	$("#kaipiaoCompany").val("");
	$("#kaipiaodDanwei").val("");
	$("#fapiaoType").val("");
	/* $("#pinming").text(""); */
	$("#remarkAboutFapiao").val("");
	$("#kaipiaoAmountError").text("");
	$("#yujihuikuanDate").val("");
	$("#pinmingUl").empty();
	addOrUpdateFapiao = '-1';
	//合同编号
	var id = $("#htNum").text();
	var date = new Date();
	var currentTime = date.Format("yyyy-MM-dd");
	//所属公司
	var company = $("#company").val();
	//单位名称
	var danwei = $("#danwei .select-button").val();
	$("#HtForFapiao").text(id);
	$("#applicationTimeAboutFapiao").text(currentTime);
	$("#kaipiaoDate").val(currentTime);
	$("#kaipiaoCompany").val(company);
	$("#kaipiaodDanwei").val(danwei);
	
	
	//发票品名选项
		var company = $("#kaipiaoCompany").val();	
		if(kaipiaoCompany!=""){
		$.post('public/ht/getKaipiaoOption.action',{"company":company},function(res){
			var kaipiaoList = res.obj;
			for(var i=0;i<kaipiaoList.length;i++){
				var option = "<li>"+kaipiaoList[i]+"</li>";
				$("#pinmingUl").append(option);
			}
		});
	}
	})	


//添加发票
$("#sureAboutFapiao").click(function(){
	var hetongNumber =$("#htNum").text();//合同编号
	var money =$("#kaipiaoAmount").val();
	var company = $("#kaipiaoCompany").val();
	var kaipiaodDanwei = $("#kaipiaodDanwei").val();
	var pinming = $("#pinming").text();
	var yujihuikuanDate = $("#yujihuikuanDate").val();
	var b = validateFapiaoAmount();
	if(b==false){
		return;
	}
	if(hetongNumber==""){
		$.messager.alert('提示：','请填写完合同和产品信息再添加发票');
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
	if(kaipiaodDanwei==""){
		$.messager.alert('提示：','请填写开票单位');
		return;
	}
	if(pinming=="点击选择栏目"){
		$.messager.alert('提示：','请选择发票品名');
		return;
	}
	if(yujihuikuanDate==""){
		$.messager.alert('提示：','请选择开票预计回款时间');
		return;
	}
	
	var applicationDate =$("#applicationTimeAboutFapiao").text(); //申请时间
	var money =$("#kaipiaoAmount").val();
	var capitalMoney =$("#daxieAmount").val();
	var company =$("#kaipiaoCompany").val();
	var departMement =$("#kaipiaodDanwei").val();
	/*$("#fapiaoType").val();*/
	var type = $("#fapiaoType .select-button").val();
	/*var name =$("#pinming").val();*/
	var name = $("#pinming").text()
	var date =$("#kaipiaoDate").val();
	var remark = $("#remarkAboutFapiao").val();
	var yujihuikuanDate = $("#yujihuikuanDate").val();
	
	
	var data ={"hetongNumber":hetongNumber,"applicationDate":applicationDate,"money":money,"capitalMoney":capitalMoney,"company":company,"departMement":departMement,"type":type,"name":name,"date":date,"remark":remark,"yujihuikuanDate":yujihuikuanDate};
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

//品名搜索
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
	

//更新发票(回显)
function updateFapiao(id,obj){
	tr = $(obj).parent().parent();
	
	//确定按钮隐藏    修改删除按钮显示
	$("#sureAboutFapiao").attr("style","display:none");
	$("#updateFapiao").attr("style","display:inline");
	$("#deleteFapiao").attr("style","display:inline");
	var data ={"fapiaoId":id}
	//清空错误消息
	$("#kaipiaoAmountError").text("");
	//合同编号
	var id = $("#htNum").text();
	/*var date = new Date();
	var currentTime = date.Format("yyyy-MM-dd");*/
/*	//所属公司
	var company = $("#company").val();
	//单位名称
	var danwei = $("#danwei .select-button").val();*/
	$("#HtForFapiao").text(id);
	/*$("#applicationTimeAboutFapiao").text(currentTime);*/
	/*$("#kaipiaoCompany").val(company);
	$("#kaipiaodDanwei").val(danwei);*/
	
	//发票品名选项
 	var company = $("#kaipiaoCompany").val();	
 	if(kaipiaoCompany!=""){
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
		 var date2 = new Date(fapiao.date).Format("yyyy-MM-dd");
		 var applicationDate = new Date(fapiao.applicationDate).Format("yyyy-MM-dd");//申请时间
		 var yujihuikuanDate2 = new Date(fapiao.yujihuikuanDate).Format("yyyy-MM-dd");
			/*var hetongNumber =$("#htNum").text();//合同编号
			var applicationDate =$("#applicationTimeAboutFapiao").text(); //申请时间*/
			$("#kaipiaoAmount").val(fapiao.money);
			$("#daxieAmount").val(fapiao.capitalMoney);
			$("#kaipiaoCompany").val(fapiao.company);
			$("#kaipiaodDanwei").val(fapiao.departMement);
			$("#kaipiaoDate").val(date2);
			$("#remarkAboutFapiao").val(fapiao.remark);
			$("#fapiaoId").attr("value",fapiao.id);
			$("#fapiaoType .select-button").attr("value",fapiao.type);
			$("#pinming").text(fapiao.name);
			$("#yujihuikuanDate").val(yujihuikuanDate2);
			$("#applicationTimeAboutFapiao").text(applicationDate);
			
			$("#kaipiaoAmount").attr("onBlur","validateFapiaoAmount("+fapiaoId+")");
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
	var hetongId = $("#HtForFapiao").text();
	var fapiaoID = $("#fapiaoId").val();
	var money =$("#kaipiaoAmount").val();
	var capitalMoney =$("#daxieAmount").val();
	var company =$("#kaipiaoCompany").val();
	var departMement =$("#kaipiaodDanwei").val();
	var type =$("#fapiaoType .select-button").val();
	var name =$("#pinming").text();
	var date =$("#kaipiaoDate").val();
	var remark = $("#remarkAboutFapiao").val();
	var yujihuikuanDate = $("#yujihuikuanDate").val();//开票预计回款时间

	var b = validateFapiaoAmount();
	if(b==false){
		return;
	}
	
	var data ={"money":money,"capitalMoney":capitalMoney,
			"company":company,"departMement":departMement,"type":type,"name":name,"date":date,
			"remark":remark,"fapiaoID":fapiaoID,"yujihuikuanDate":yujihuikuanDate,"hetongId":hetongId};

	$.post('public/ht/doUpdateFapiao.action',data,function(res){
		
		 if(res.success==true){
			 /*$.messager.alert('提示：',res.msg);*/
			
			 tr.children('td').eq(0).text(fapiaoID);
			 tr.children('td').eq(1).text(date);
			 tr.children('td').eq(2).text(company);
			 tr.children('td').eq(3).text(departMement);
			 tr.children('td').eq(4).text(type);
			 tr.children('td').eq(5).text(name);
			 tr.children('td').eq(6).text(money);
			/*  tr.children('td').eq(7).text(huiKuan);
			 tr.children('td').eq(8).text(receivedpaymentsdate); */
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
				   /*$.messager.alert('提示：',res.msg);*/
				   $(".pop_invoice").hide();
				   $(".maskLayer").hide();
				 }else{
				   $.messager.alert('提示：',res.msg);
			   }
	       })
		
	  })
});	


//-------------------------------------------------------------快递------------------------------------------------------------------
	//点击新增快递按钮	
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
	/*var hetongCode = $("htNumRelationFaHuo").text();*/
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
})


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
			var date2 = new Date(faHuo.applicationDate).Format("yyyy-MM-dd");
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
			 /*$.messager.alert('提示：',res.msg);*/
			 
			 tr.children('td').eq(0).text(fahuoId);
			 tr.children('td').eq(1).text(d_contact);
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
				  /* $.messager.alert('提示：',res.msg);*/
				   $(".pop_courier").hide();	
				   $(".maskLayer").hide();
				 }else{
					 $.messager.alert('提示：',res.msg);
			   }
	       })
	   })
   });


var addOrUpdateFapiao = '-1';//add代表新增   update代表更新
$(function(){
	$('#danwei').selectlist({
		width: 130,
		height: 18,
		//选择项目单位携带出对应的用户ID和单位ID
	 	onChange: function(){
			/* var depart = $("#danwei .select-button").val(); */
			var danweiId = $($("#danwei").children("input").get(0)).val()//用户单位表主键
			var data ={"danweiId":danweiId};
			  $.get('public/ht/getUserAndDepartId.action',data,function(res){
				        var customerDepart = res.obj;
				        //用户ID取用户列表的ID，即用户单位的单位编号
				        var userId= customerDepart.dId;
				        //用户单位表ID是单位ID
				        var danweiId= customerDepart.id;
				        $("#userId").val(userId);
				        $("#danweiId").val(danweiId);
			});  
		} 
	});	
	
	$('#chooseProduct').selectlist({
		width: 130,
		height: 18,
	 	onChange: function(){
	 		var name = $("#chooseProduct .select-button").val();
	 		if(name=='第三方产品'){
	 			$("#disanfangLi").attr("style","display:block;");
	 		}else{
	 			$("#disanfangLi").attr("style","display:none;");
	 			$("#disanfangChanpin").val("");
	 		}
		} 
	});	
})

$(function(){
	$('#windowAboutFapiao').on('click',".selectNav p",function(){
		var ul=$(".selectNew");
		if(ul.css("display")=="none"){
			ul.slideDown();
		}else{
			ul.slideUp();
		}
	})
	
	$('#windowAboutFapiao').on('click',".selectSet",function(){
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
	$('#windowAboutFapiao').on('click',".selectNav li",function(){
		var li=$(this).text();
		$(".selectNav p").html(li);
		$(".selectNew").hide();
		/*$(".set").css({background:'none'});*/
		$("p").removeClass("selectCur") ;
	})
})

$(function(){
	$('select').selectlist({
		width: 130,
		height: 18,
		
	});
	var danweiId = $($("#danwei").children("input").get(0)).val()//用户单位表主键
	var data ={"danweiId":danweiId};
	  $.get('public/ht/getUserAndDepartId.action',data,function(res){
		        var customerDepart = res.obj;
		        //用户ID取用户列表的ID，即用户单位的单位编号
		        var userId= customerDepart.dId;
		        //用户单位表ID是单位ID
		        var danweiId= customerDepart.id;
		        $("#userId").val(userId);
		        $("#danweiId").val(danweiId);
	  });
})


	
//创建合同(保存,仅销售人员可见)
$("#contractSave").click(function(){
	var flag = true;
	var b = validateContractAmount();
	if(b==false){
		flag = false;
	}
	if(flag == false){
		return;
	}
	
	var id = $("#htNum").text();//合同编号
	var company = $("#company").val();
	var depart = $("#danwei .select-button").val();
	var cid = $("#userId").val();
	var didNum = $("#danweiId").val();
	var contractMoney = $("#contractAmount").val();
	var agreementNumber = $("#contractCount").val();
	var endTime = $("#endTime").val();
	var agreementText = $("#contractContent").val(); //合同内容
	var remarksText = $("#remark").val();  //备注
	var payMethod = $("#payMethod").val();
	var danweiId = $($("#danwei").children("input").get(0)).val();//用户单位表主键
	
	var data={"id":id,"company":company,"depart":depart,"cid":cid,"didNum":didNum,
					"contractMoney":contractMoney,
					"agreementNumber":agreementNumber,"endTime":endTime,"agreementText":agreementText,
					"remarksText":remarksText,"payMethod":payMethod,"danweiId":danweiId};		
	 $.post('public/ht/addContractNormal.action',data,function(res){
			if(res.success==true){
				$.messager.alert('提示：',res.msg);
				$.get('public/ht/contractListSale.action',function(result){
					$('#container').html(result);
					})
			}else{
				$.messager.alert('提示：',res.msg);
			 }
	}); 
	
})

//创建合同(提交)
$("#contractSubmit").click(function(){
	var id = $("#htNum").text();//合同编号
	var company = $("#company").val();//所属公司
	var contractMoney = $("#contractAmount").val();//合同金额
	var agreementNumber = $("#contractCount").val();//合同份数
	var endTime = $("#endTime").val();//合同到期时间
	var b = validateContractAmount();
	if(b==false){
		return;
	}
	if(company==""){
		$.messager.alert('提示：','请填写所属公司!');
		return;
	}
	if(contractMoney==""){
		$.messager.alert('提示：','请填写合同金额!');
		return;
	}	
	if(agreementNumber==""){
		$.messager.alert('提示：','请填写合同份数!');
		return;
	}	
	if(endTime==""){
		$.messager.alert('提示：','请填写合同到期时间!');
		return;
	}	
	
	//根据合同编号分别查询该合同下的产品、发票、快递
	var chanpinNum;
	var fapiaoNum;
	var kuaidiNum;
	if(id!=''){
		$.ajax({
			type:'POST',
		    url:'public/ht/getChanpinFapiaoFahuoNum.action',
		    data:{"id":id},
		    dataType:'json',
		    async:false,
		    success:function(res){
		    	var numList = res.obj;
				 chanpinNum = numList[0];
				 fapiaoNum = numList[1];
				 kuaidiNum = numList[2];
		    }
		})
	}
	
	//没有产品
	if(Number(chanpinNum)==0){
		$.messager.alert('提示：','请添加完产品后再提交!');
		return;
	}
	//没有创建发票
	if(Number(fapiaoNum)==0){
		$.messager.alert('提示：','请添加完发票后再提交!');
		return;
	}
	//没有创建快递
	if(Number(kuaidiNum)==0){
		$.messager.alert('提示：','请添加完快递后再提交!');
		return;
	}
	
	var depart = $("#danwei .select-button").val();
	var cid = $("#userId").val();
	var didNum = $("#danweiId").val();
	var agreementText = $("#contractContent").val(); //合同内容
	var remarksText = $("#remark").val();  //备注
	var payMethod = $("#payMethod").val();
	var danweiId = $($("#danwei").children("input").get(0)).val();//用户ID
	
	var data={"id":id,"company":company,"depart":depart,"cid":cid,"didNum":didNum,
					"contractMoney":contractMoney,
					"agreementNumber":agreementNumber,"endTime":endTime,"agreementText":agreementText,
					"remarksText":remarksText,"payMethod":payMethod,"danweiId":danweiId};		
	 $.post('public/ht/addContractNomalSubmit.action',data,function(res){
			if(res.success==true){
				$.messager.alert('提示：',res.msg);
				$.get('public/ht/contractListSale.action',function(result){
					$('#container').html(result);
					})
			}else{
				$.messager.alert('提示：',res.msg);
			 } 
	}); 
	
})

//取消按钮
$("#contractCancel").click(function(){
	var id = $("#htNum").text();//合同编号
	
	$.messager.confirm("提示","您确认要放弃当前操作吗？",function(){
		
		//如果没有生成合同，直接回到合同列表页面;如果已经生成合同，提示 要放弃当前操作吗?  删除当前合同和合同所属的产品,发票,快递
		if(id!=null&&id!=""){
			var data = {"id":id};
			$.post('public/ht/contractNormalCancel.action',data,function(res){
				if(res.success==true){
					$.get('public/ht/contractListSale.action',function(result){
						$('#container').html(result);
						})
				   }
				})
		}else{
			$.get('public/ht/contractListSale.action',function(result){
				$('#container').html(result);
				})
		 }
	  })
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
    var reg = new RegExp("^[0-9]*$");
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


