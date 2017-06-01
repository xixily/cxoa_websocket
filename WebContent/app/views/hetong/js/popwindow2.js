// JavaScript Document

//产品
$("a.product").click(function(){
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
});	

$(".pop_product a.dele,.pop_product a.bnt").click(function(){
	$(".pop_product").hide();	
	$(".maskLayer").hide();
	return false;
});

//发票
$("a.invoice").click(function(){
	var company = $("#company").val(); //所属公司
	if(company==""){
		$.messager.alert('提示：','请先填写所属公司信息！');
		return;
	}
	var company = $("#company").val(); //所属公司
	if(company==""){
		$.messager.alert('提示：','请先填写所属公司信息！');
		return;
	}
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
});	
$(".pop_invoice a.dele,.pop_invoice a.bnt").click(function(){
	$(".pop_invoice").hide();
	$(".maskLayer").hide();
	return false;
});

//快递
$("a.courier").click(function(){
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
});	
$(".pop_courier a.dele,.pop_courier a.bnt").click(function(){
	$(".pop_courier").hide();	
	$(".maskLayer").hide();
	return false;
});
	
//弹出层固定在屏幕中央 并随屏滚动
$(window).scroll(function(){
	$('.pop_product,.pop_invoice,.pop_courier').css({top:function(){
			return ($(window).height()-$(this).height())/2+$(window).scrollTop();
		},left:function(){
			return ($(window).width()-$(this).width())/2;	
		}
	});
});