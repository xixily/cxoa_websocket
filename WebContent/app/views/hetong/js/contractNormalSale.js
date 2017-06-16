$(function(){
	/*$('select').selectlist({
		width: 130,
		height: 18,
	});*/
	var danweiId = $($("#danwei").children("input").get(0)).val()//用户单位表主键
	var data ={"danweiId":danweiId};
	$.get('public/ht/getUserAndDepartId.action',data,function(res){
        var customerDepart = res.obj;
        //用户ID取用户列表的ID，即用户单位的单位编号,母单位
        danweiId= customerDepart.dId;
        //用户单位表ID是单位ID,子单位
        var userId= customerDepart.id;
        $("#userId").val(userId);
        $("#danweiId").val(danweiId);
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
	
	$('.delete').on('click',function(){
		$(".selectNew").slideUp()
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
		$(".set").css({background:'none'});
		$("p").removeClass("selectCur") ;
	})

})

//添加产品弹框
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
	var id = $("#htNum").text();//合同编号
	if(id==""||null==id){
		 $.get('public/ht/addRealContract.action',function(res){
			 if(res.success==true){
				var htId =  res.obj;
				$("#htNum").text(htId);
			 }
		}); 
	}
})
	
// --创建合同(保存,仅销售人员可见)
$("#contractSave").click(function(){
	var flag = validateContractAmount();
	if(flag==false){
		return
	}
	var id = $("#htNum").text();//合同编号
	var company = $("#company").val(); //所属公司
	var depart = $("#danwei .select-button").val();  //单位名称
	var cid = $("#userId").val();  //用户id
	var didNum = $("#danweiId").val();  //单位id
	var contractMoney = $("#contractAmount").val();  //合同金额
	var agreementNumber = $("#contractCount").val();  //合同份数
	var endTime = $("#endTime").val();  //合同到期时间
	var agreementText = $("#contractContent").val(); //合同内容(合同概要)
	var remarksText = $("#remark").val();  //备注
	var payMethod = $("#payMethod").val();  //付款方式
	var danweiId = $($("#danwei").children("input").get(0)).val();//用户单位表主键,和cid相同
	var dealConditon = 4;//合同状态,暂存

	var data={"id":id,"company":company,"depart":depart,"cid":cid,"didNum":didNum,
					"contractMoney":contractMoney,
					"agreementNumber":agreementNumber,"endTime":endTime,"agreementText":agreementText,
					"remarksText":remarksText,"payMethod":payMethod,"danweiId":danweiId,"dealConditon":dealConditon};		
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

// --创建合同(提交)
$("#contractSubmit").click(function(){
	var id = $("#htNum").text();//合同编号
	var company = $("#company").val();//所属公司
	var depart = $("#danwei .select-button").val(); //单位名称
	var cid = $("#userId").val(); //用户id
	var didNum = $("#danweiId").val(); //单位id
	var contractMoney = $("#contractAmount").val();//合同金额
	var agreementNumber = $("#contractCount").val();//合同份数
	var endTime = $("#endTime").val();//合同到期时间
	var agreementText = $("#contractContent").val(); //合同内容
	var remarksText = $("#remark").val();  //备注
	var payMethod = $("#payMethod").val(); //付款方式
	var danweiId = $($("#danwei").children("input").get(0)).val();//用户单位表主键,和cid相同
	var dealConditon = 0;//合同状态,未处理
	
	//提交比保存多了下列验证,----验证开始
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
	//----验证结束
	var data={"id":id,"company":company,"depart":depart,"cid":cid,"didNum":didNum,
					"contractMoney":contractMoney,
					"agreementNumber":agreementNumber,"endTime":endTime,"agreementText":agreementText,
					"remarksText":remarksText,"payMethod":payMethod,"danweiId":danweiId,"dealConditon":dealConditon};		
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

// --取消按钮
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

