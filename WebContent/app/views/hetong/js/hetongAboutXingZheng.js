/*----------------------------------------------------产品--------------------------------------------------------------*/
//点添加产品按钮
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
	})

//添加产品
$("#sureAboutProduct").click(function(){
	 
	var name = $("#chooseProduct .select-button").val();
	var money = $("#productMoney").val();
	var amount =$("#productAmount").val();
	var begain =$("#effectiveDate").val();
	var end =$("#endDate").val();
	var ctid =$("#htNum").text();
	var disanfangChanpin = $("#disanfangChanpin").val();
	
	 //保存合同概要
	if(name=='第三方产品'){
	  var htgaiyao = $("#disanfangChanpin").val();
	 /* $("#contractContent").append(htgaiyao);*/
	  var contractContent = $("#contractContent").val();
	  var contractContentFinal = contractContent+htgaiyao;
	  $("#contractContent").val(contractContentFinal);
	  $.post('public/ht/addHtgaiyao.action',{"htgaiyao":htgaiyao,"ctid":ctid},function(res){
			
	  })
	}
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
	var id =$("#htNum").text();//合同编号
	
	$.post('public/ht/toUpdateItemPrice.action',data,function(res){
		//第三方产品(999)
		 if(res.success==true){
			var ItemPrice = res.obj;
			var name = ItemPrice.name;
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
				})
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
	var id = $("#htNum").text();
	var disanfangChanpin = $("#disanfangChanpin").val();
	if(name=='第三方产品'){
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
	$("#sureAboutFapiaoXingzheng").attr("style","display:inline;");
	$("#updateFapiao").attr("style","display:none;");
	$("#deleteFapiao").attr("style","display:none;");
	/*$("#yujihuikuanDateli").attr("style","display:none;");*/
	$("#HtForFapiao").attr("disabled",true);
	
	$("#kaipiaoAmount").val("");
	$("#daxieAmount").val("");
	$("#kaipiaoCompany").val("");
	$("#kaipiaodDanwei").val("");
	$("#fapiaoType").val("");
	$("#kaipiaoDate").val("");
	$("#remarkAboutFapiao").val("");
	$("#kaipiaoAmountError").text("");
	$("#huikuanAmount").val("");
	$("#huikuanDate").val("");
	$("#caiwuyuefen").val("");
	$("#yujihuikuanDate").val("");
	$("#pinmingUl").empty();
	$("#pinming").text("点击选择栏目");
	addOrUpdateFapiao = '-1';
	
	//合同编号
	var id = $("#htNum").text();
	var date = new Date();
	var currentTime = date.Format("yyyy-MM-dd");
	//所属公司
	var company = $("#company2").val();
	//单位名称
	var danwei = $("#danweiName2").val();
	 //财务月份(默认显示当月)  没有月份 赋值   有 不赋值
	var date = new Date();
	var currentMonth = date.getMonth();
	var caiwuyuefen = $("#caiwuyuefen").val();
	var currentMonthTrue = Number(currentMonth)+1
	
	//赋值当月
	$("#caiwuyuefen").val(currentMonthTrue);
	
	$("#HtForFapiao").val(id);
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


//添加发票(点击确定按钮)
$("#sureAboutFapiaoXingzheng").click(function(){
	var hetongNumber =$("#htNum").text();//合同编号
	var money =$("#kaipiaoAmount").val();
	var company =$("#kaipiaoCompany").val();
	var departMement =$("#kaipiaodDanwei").val();
	var name = $("#pinming").text();//品名
	var yujihuikuanDate = $("#yujihuikuanDate").val();
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
	
	var applicationDate =$("#applicationTimeAboutFapiao").text(); //申请时间
	var capitalMoney =$("#daxieAmount").val();
	var type = $("#fapiaoType .select-button").val();
	var date =$("#kaipiaoDate").val();
	var huiKuan = $("#huikuanAmount").val();
	var receivedpaymentsdate = $("#huikuanDate").val();//回款日期
	var fundType = $("#zijin .select-button").val();//资金类型
	var account = $("#zhanghu .select-button").val();//账户
	var remark = $("#remarkAboutFapiao").val();
    var caiwuMonth = $("#caiwuyuefen").val();//财务月份
    var yujihuikuanDate = $("#yujihuikuanDate").val();//预计回款时间
	
	var data ={"hetongNumber":hetongNumber,"applicationDate":applicationDate,"money":money,"capitalMoney":capitalMoney,"company":company,"departMement":departMement,"type":type,"name":name,"date":date,"remark":remark,"huiKuan":huiKuan,"receivedpaymentsdate":receivedpaymentsdate,"fundType":fundType,"account":account,"caiwuMonth":caiwuMonth,"yujihuikuanDate":yujihuikuanDate};
	  
 
 $.post('public/ht/addFaPiao.action',data,function(res){
			if(res.success==true){
				var date = res.obj.date;
				var date2 = new Date(date).Format("yyyy-MM-dd");
				var a = res.obj.huiKuan;
				var b = res.obj.receivedpaymentsdate;
				var huikuanDate = new Date(b).Format("yyyy-MM-dd");
				if(a==null){
					 a ="";
				}
				if(huikuanDate==null){
					huikuanDate ="";
				}
				var tr = "<tr><td>"+res.obj.id+"</td><td>"+date2+"</td><td>"+res.obj.company+"</td><td>"+res.obj.departMement+"</td><td>"+res.obj.type+"</td><td>"+res.obj.name+"</td>" +
				"<td>"+res.obj.money+"</td><td>"+a+"</td><td>"+huikuanDate+"</td><td>"+res.obj.remark+"</td><td>"+res.obj.hetongNumber+"</td><td>"+res.obj.fundType+"</td><td>"+res.obj.account+"</td><td><a id="+res.obj.id+" class='dele' href='javascript:;' onclick='updateFapiaoXingzheng("+res.obj.id+",this)'>修改</a></td></tr>";	
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
function updateFapiaoXingzheng(id,obj){
//	debugger;
	tr = $(obj).parent().parent();
	
	//确定按钮隐藏    修改删除按钮显示
	$("#sureAboutFapiaoXingzheng").attr("style","display:none");
	$("#updateFapiao").attr("style","display:inline");
	$("#deleteFapiao").attr("style","display:inline");
	$("#yujihuikuanDateli").attr("style","display:inline;");
	$("#HtForFapiao").attr("disabled",false);
	
	var data ={"fapiaoId":id}
	//清空错误消息
	$("#kaipiaoAmountError").text("");
	//合同编号
	var id = $("#htNum").text();
	$("#HtForFapiao").val(id);
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
		//date 开票时间
		 if(res.success==true){
		 var fapiao = res.obj;
		 var fapiaoId = fapiao.id;
		 addOrUpdateFapiao = fapiaoId;//通过addOrUpdateFapiao的值判断是增加还是修改
		 var date2 = new Date(fapiao.date).Format("yyyy-MM-dd");
		 var applicationDate = new Date(fapiao.applicationDate).Format("yyyy-MM-dd");
		 var yujihuikuanDate = new Date(fapiao.yujihuikuanDate).Format("yyyy-MM-dd");
		 
	//财务月份(默认显示当月)  没有月份 赋值   有 不赋值
		var date = new Date();
		var currentMonth = date.getMonth();
		var caiwuyuefen = $("#caiwuyuefen").val();
		var currentMonthTrue = Number(currentMonth)+1;
		if(fapiao.caiwuMonth==null||fapiao.caiwuMonth==""){
			//赋值当月
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
			/*var hetongNumber =$("#htNum").text();//合同编号
			var applicationDate =$("#applicationTimeAboutFapiao").text(); //申请时间*/
			$("#kaipiaoAmount").val(fapiao.money);
			$("#daxieAmount").val(fapiao.capitalMoney);
			$("#kaipiaoCompany").val(fapiao.company);
			$("#kaipiaodDanwei").val(fapiao.departMement);
			/* $("#fapiaoType").val(fapiao.type); */
			$("#pinming").text(fapiao.name);
			$("#kaipiaoDate").val(date2);
			$("#remarkAboutFapiao").val(fapiao.remark);
			$("#fapiaoId").attr("value",fapiao.id);
			$("#fapiaoType .select-button").attr("value",fapiao.type);
			//销售申请时间(库里取)
			$("#applicationTimeAboutFapiao").text(applicationDate);
			$("#yujihuikuanDate").val(yujihuikuanDate);
			$("#huikuanAmount").val(fapiao.huiKuan);
			$("#zijin .select-button").val(fapiao.fundType);
			$("#zhanghu .select-button").val(fapiao.account);
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
		var b = validateFapiaoAmount();
		if(b==false){
			return;
		}
		var hetongNumber =$("#htNum").text();//合同编号
		var hetongId = $("#HtForFapiao").val();//从弹框获取的合同ID
		/*var hetongNumber =$("#htNum").text();//合同编号
		var applicationDate =$("#applicationTimeAboutFapiao").text(); //申请时间*/
		
		var fapiaoID = $("#fapiaoId").val();
		var money =$("#kaipiaoAmount").val();
		var capitalMoney =$("#daxieAmount").val();
		var company =$("#kaipiaoCompany").val();
		var departMement =$("#kaipiaodDanwei").val();
		var type =$("#fapiaoType .select-button").val();
		var name =$("#pinming").text();
		var date =$("#kaipiaoDate").val();
		var remark = $("#remarkAboutFapiao").val();
		var huiKuan = $("#huikuanAmount").val();//回款金额
		var receivedpaymentsdate = $("#huikuanDate").val();//回款日期
		var fundType = $("#zijin .select-button").val();//资金类型
		var account = $("#zhanghu .select-button").val();//账户
		var yujihuikuanDate = $("#yujihuikuanDate").val();//预计回款时间
		var caiwuMonth = $("#caiwuyuefen").val();
		
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
				/* $.messager.alert('提示：',res.msg);*/
				if(Number(hetongNumber)==Number(hetongId)){
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
					 tr.children('td').eq(10).text(hetongNumber);
					 tr.children('td').eq(11).text(fundType);
					 tr.children('td').eq(12).text(account);
					 
					 $(".pop_invoice").hide();
					 $(".maskLayer").hide();
			/*		 //遍历整个表格获取回款情况总和
					  var total = 0; //总回款金额
					  var totalKaiPiao = 0; //总开票金额
					  var contractId = $("#htNum").text();
				      var trList = $("#tableAboutFapiao").find("tr");
				      var latestDate;//最新日期
				      var latestDate2;
				      var latestDate_ = '';
				      for (var i=0;i<trList.length;i++) {
					        var tdArr = trList.eq(i).find("td");//一行所有的td
					        var huikuanDate = tdArr.eq(8).text();
					        if(huikuanDate!=''){
					        	latestDate = huikuanDate;
					        	break;
					        }
					    }
				      
				      var latestDate = $("#tableAboutFapiao").find("tr:last").children().eq(8).text();
				     if(latestDate != null){
				    	latestDate2 = new Date(Date.parse(latestDate));
				     var latestDate2 = new Date(Date.parse(latestDate));
				     	var huikuanDate2;
					    for (var i=0;i<trList.length;i++) {
					        var tdArr = trList.eq(i).find("td");//一行所有的td
					        var huikuanAmount = tdArr.eq(7).text();
					        var kaipiaoAmount = tdArr.eq(6).text();
					        var total = Number(huikuanAmount)+Number(total);
					        var totalKaiPiao = Number(kaipiaoAmount)+Number(totalKaiPiao);
					        var huikuanDate = tdArr.eq(8).text();
					       if(huikuanDate!=''){
					    	   huikuanDate2 = new Date(Date.parse(huikuanDate));
					       }
					        if(huikuanDate2>latestDate2){
					        	latestDate2 = huikuanDate2;
					        }
					        latestDate_ = latestDate2.Format("yyyy-MM-dd");
					    }
				     }
					 var data={"contractId":contractId,"total":total,"latestDate2":latestDate_,"totalKaiPiao":totalKaiPiao};
				    $.post('public/ht/updateHuikuanAndDate.action',data,function(res){
						 if(res.success==true){
							console.log("更新回款金额和日期成功!");
						 }
					}); */
				}else{
					 tr.remove();
					/* //遍历整个表格获取回款情况总和
					  var total = 0; //总回款金额
					  var totalKaiPiao = 0; //总开票金额
					  var contractId = $("#htNum").text();
				      var trList = $("#tableAboutFapiao").find("tr");
				      var latestDate;//最新日期
				      var latestDate2;
				      var latestDate_ = '';
				      //找基准
				      for (var i=0;i<trList.length;i++) {
					        var tdArr = trList.eq(i).find("td");//一行所有的td
					        var huikuanDate = tdArr.eq(8).text();
					        if(huikuanDate!=''){
					        	latestDate = huikuanDate;
					        	break;
					        }
					    }
				      
				      var latestDate = $("#tableAboutFapiao").find("tr:last").children().eq(8).text();
				     if(latestDate != null){
				    	latestDate2 = new Date(Date.parse(latestDate));
				     var latestDate2 = new Date(Date.parse(latestDate));
				     	var huikuanDate2;
					    for (var i=0;i<trList.length;i++) {
					        var tdArr = trList.eq(i).find("td");//一行所有的td
					        var huikuanAmount = tdArr.eq(7).text();
					        var kaipiaoAmount = tdArr.eq(6).text();
					        var total = Number(huikuanAmount)+Number(total);
					        var totalKaiPiao = Number(kaipiaoAmount)+Number(totalKaiPiao);
					        var huikuanDate = tdArr.eq(8).text();
					       if(huikuanDate!=''){
					    	   huikuanDate2 = new Date(Date.parse(huikuanDate));
					       }
					        if(huikuanDate2>latestDate2){
					        	latestDate2 = huikuanDate2;
					        }
					        latestDate_ = latestDate2.Format("yyyy-MM-dd");
					    }
				     }
					 var data={"contractId":contractId,"total":total,"latestDate2":latestDate_,"totalKaiPiao":totalKaiPiao};
				    $.post('public/ht/updateHuikuanAndDate.action',data,function(res){
						 if(res.success==true){
							console.log("更新回款金额和日期成功!");
						 }
					}); */
					$(".pop_invoice").hide();
					$(".maskLayer").hide();
					
				}
			
		        
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
function updateFahuoXinzheng(id,obj){
	tr = $(obj).parent().parent();
	//确定按钮隐藏    修改删除按钮显示
	$("#sureAboutFaHuo").attr("style","display:none");
	$("#updateFahuoXingzheng").attr("style","display:inline");
	$("#deleteFahuo").attr("style","display:inline");
	var postAddress = $("#postAddress").val();
	var data ={"fahuoId":id}
  
	$.post('public/ht/toUpdateFahuo.action',data,function(res){
		
		 if(res.success==true){
			var faHuo = res.obj;
			var date2 = new Date(faHuo.applicationDate).Format("yyyy-MM-dd")
			console.log(faHuo);
			$("#htNumRelationFaHuo").val(faHuo.hetongCode);
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
	$("#updateFahuoXingzheng").click(function(){
		var fahuoId = $("#fahuoId").val();
		var mailno = $("#expressNum").val();
		var hetongCode = $("#htNumRelationFaHuo").val();
		var d_contact = $("#receiver").val();
		var d_tel = $("#tel").val();
		var d_address = $("#receiveAddress").val();
		var jDate = $("#postDate").val();
		var postMethod = $("#expressCom .select-button").val();
		var content = $("#expressContent").val();
		var htNum = $("#htNum").text();//合同编号
		

		var data ={"mailno":mailno,"d_contact":d_contact,
				"d_tel":d_tel,"d_address":d_address,"jDate":jDate,"postMethod":postMethod,"content":content,"fahuoId":fahuoId,"hetongCode":hetongCode};
		
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
						 tr.children('td').eq(4).text(jDate);
						 tr.children('td').eq(5).text(content);
						 tr.children('td').eq(6).text(postMethod);
						 tr.children('td').eq(7).text(mailno);
						 
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
		} 
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

$(function(){
	$('select').selectlist({
		width: 130,
		height: 18,
		
	});
})

//通过按钮  更新开票金额和回款金额
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
	
$("#saveXingzheng").click(function(){
	var id = $("#htNum").text();
	var yinhuashui = $("#yinhuashui .select-button").val();
	var huaizhangAmount = $("#huaizhangAmount").val();
	var company = $("#company2").val();//所属公司
    var guidangNum = $("#guidangfenshu").val();//归档份数
    var kuaijifenlei = $("#kuaiji .select-button").val();//会计分类
    var dengjiTime = $("#dengjiTime").val();//登记时间
	 $.post('public/ht/updateContractXingzheng.action',{"id":id,"yinhuashui":yinhuashui,"huaizhangAmount":huaizhangAmount,"company":company,"guidangNum":guidangNum,"kuaijifenlei":kuaijifenlei,"dengjiTime":dengjiTime},function(res){
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

//未通过   更新合同状态  添加错误消息
$("#noPass").click(function(){
	var id = $("#htNum").text();
	var company = $("#company").val();//所属公司
	var depart =$("#danweiName").val();//单位名称
	var dengjiTime = $("#dengjiTime").val();
	var operator = $("#shenqingren").val();
	var lianxifangshi = $("#telphone").val();
	var contractMoney = $("#contractAmountError").val();
	var endTime = $("#endTime").val();
	var productInfo = $("#productInfo").val();
	var fapiaoStatus = $("#fapiaoCondition").val();
	var youjiStatus = $("#kuaidiCondition").val();
	var agreementText = $("#htgaiyao").val();
	var payMethod = $("#payMethod").val();
	/* var remark = $("#remark").val(); */
	var data = {"id":id,"company":company,"depart":depart,"dengjiTime":dengjiTime,"operator":operator,"lianxifangshi":lianxifangshi,
			"contractMoney":contractMoney,"endTime":endTime,"productInfo":productInfo,"fapiaoStatus":fapiaoStatus,"youjiStatus":youjiStatus,
			"agreementText":agreementText,"payMethod":payMethod};
	$.post('public/ht/addErrorInfo.action',data,function(res){
		
	})
	
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

$("#cancel").click(function(){
	$.get('public/ht/contractList.action',function(result){
		$('#container').html(result);
	})
})

//提示错误消息 
$(".addbnt").click(function(){
$(this).next().toggle();
})

	//点"回款金额"自动出现开票金额
	$("#huikuanAmount").click(function(){
		var kaipiaoAmount = $("#kaipiaoAmount").val();
		$("#huikuanAmount").val(kaipiaoAmount);
	})
	
	
	//点完回款日期自动出现当天日期+邮箱
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

//归档时间 点击 直接显示为当天日期   生成归档编号  发送邮件信息
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
		var tijiaoTime2 = new Date(tijiaoTime).Format("yyyy-MM-dd");
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
