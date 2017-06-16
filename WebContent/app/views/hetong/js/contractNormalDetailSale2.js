$(function(){
	$('#windowAboutFapiao3').on('click',".selectNav p",function(){
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
	
	$('#windowAboutFapiao3').on('click',".selectSet",function(){
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
	$('#windowAboutFapiao3').on('click',".selectNav li",function(){
		var li=$(this).text();
		$(".selectNav p").html(li);
		$(".selectNew").hide();
		/*$(".set").css({background:'none'});*/
		$("p").removeClass("selectCur") ;
	})
})


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
	//去掉"其他"下的输入框
	if(productName=="其他"){
		$("#otherLi").attr("style","display:block;");
	}else{
		$("#otherLi").attr("style","display:none;");
	}
	$("#other").val("");
})
	
//更新合同  点提交 再次更新开票金额
$("#saleSubmitAgain").click(function(){
	var id = $("#htNum").text();
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
    var data={"id":id,"company":company,"depart":depart,"cid":cid,"didNum":didNum,"contractMoney":contractMoney,"agreementNumber":agreementNumber,"endTime":endTime,"agreementText":agreementText,"remarksText":remarksText,"payMethod":payMethod}		
	$.post('public/ht/updateContractNormal.action',data,function(res){
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
	$.get('public/ht/contractListSale.action',function(result){
		$('#container').html(result);
	})
})