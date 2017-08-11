<%@ page language="java" pageEncoding="UTF-8"
	contentType="text/html; charset=UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!-- <!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" /> -->

<link href="/app/views/hetong/css/global.css" rel="stylesheet"
	type="text/css" />
<!-- <link href="/app/views/hetong/css/style.css" rel="stylesheet"
	type="text/css" />
<link href="/app/views/hetong/css/selectlist.css" rel="stylesheet"
	type="text/css" /> -->

<style>
.main * {
	-webkit-box-sizing: content-box;
	-moz-box-sizing: content-box;
	box-sizing: content-box;
}

.main {
	width: inherit;
}
.main ul,ol{list-style-type:none;} 
.divtable {
	width: inherit;
}

.query_list li .fidtext {
    border: solid #ccc 1px;
}
/* .select-button {
    padding: 0px 24px 0px 0px;
} */
</style>
<div app-data="body">
	<div class="main">
		<div class="untreated">
			<span>未处理合同：<a href="javascript:void(0)">${unhandledCount }</a>份
			</span>
			<!-- <span>未处理发票：<a href="#">15</a>张</span> -->
		</div>
		<div class="query">
			<form id="form1" name="form1" method="post" action="">
				<h2>查询条件</h2>
				<div class="query_list">
					<ul>
						<!-- <li>
						<div class="quy_tit">合同信息：</div>
						<input type="text" name="textfield" class="fidtext" />
					</li> -->
						<!-- <li>
							<div class="quy_tit">年 份：</div> <select id="edu" name="edu"
							class="leftF">
								<option value="0">2017年</option>
								<option value="1">2016年</option>
								<option value="2">2015年</option>
								<option value="3">2014年</option>
								<option value="4">2009年</option>
						</select>
						</li> -->
						<!-- <li style="width:468px;">
						<div class="quy_tit">起止日期：</div>
						<input type="text" name="textfield" class="fidtext" />
						<span class="leftF" style=" margin:0 10px;">至</span>
						<input type="text" name="textfield" class="fidtext" />
					</li> -->
						<li>
							<div class="quy_tit">购买单位：</div> <input id="purchaseCom"
							type="text" name="textfield" class="fidtext" />
						</li>
						<li>
							<div class="quy_tit">所属公司：</div> 
							<select id="gongsi" name="gongsi" class="leftF">
							<option value="0">全部</option>
							<c:forEach var="c" items="${companyInfoList }">
							  <c:if test="${c!= null }">
							    <option value="${c.name}">${c.name}</option>
							    </c:if>   
							</c:forEach>
								
						    </select>

						</li>
						<li>
							<div class="quy_tit">单位性质：</div> 
							<select id="danwei" name="danwei" class="leftF">
								<option value="0">全部</option>
							<c:forEach var="p" items="${propertyList }">
							<c:if test="${p!=null }">
								<option value="${p }">${p }</option>
							</c:if>
							</c:forEach>
						</select>
						</li>
						<%-- <li>
							<div class="quy_tit">产品名称：</div> 
							<select id="chanpin" name="chanpin" class="leftF">
							<option value="0">全部</option>
							<c:forEach var="p" items="${productList }">
							    <c:if test="${p!=null }">
								   <option value="1">${p }</option>
								</c:if>
							</c:forEach>
						   </select>
						   
						</li> --%>
						
						<li>
							<div class="quy_tit">小组/细胞核：</div> <input id="group" type="text"
							name="textfield" class="fidtext" />
						</li>
						<li>
							<div class="quy_tit">项目负责人：</div> <input id="responsibility"
							type="text" name="textfield" class="fidtext" />
						</li>
						<li>
							<div class="quy_tit">用户ID：</div> <input id="userId" type="text"
							name="textfield" class="fidtext" />
						</li>
						<!-- 	<li>
						<div class="quy_tit">合同金额：</div>
						<input type="text" name="textfield" class="fidtext" style="width:47px;" />
						<span class="leftF" style=" margin:0 2px;">至</span>
						<input type="text" name="textfield" class="fidtext" style="width:47px;" />
					</li> -->
						<li> 
							<div class="quy_tit">合同状态：</div> 
							<select id="zhuangtai" name="zhuangtai" class="leftF">
								<option value="-10">全部</option>
								<option value="0"> 未处理</option>
								<option value="5"> 未处理(修改)</option>
								<option value="1">审核未通过</option>
								<option value="6">审核未通过(修改)</option>
								<option value="2">审核已通过</option>
								<option value="3">合同完结</option>
								<option value="7">合同废弃</option>
						   </select>
						</li>
						<!-- <li>
						<div class="quy_tit">回帐情况：</div>
						<select id="huizhang" name="huizhang" class="leftF">
							<option value="0">全部</option>
							<option value="1">款项</option>
						</select>
					</li> -->
					</ul>
					<div class="clear"></div>
				</div>
				<div class="query_bnt">
					<input id="search" type="button" name="button" value="搜索"class="bnt" />
					<input id="reset" type="reset" name="button" value="重置"
						class="bnt" style="background: #c9c9c9;" />
				</div>
			</form>
		</div>
		<div class="contract">
			<div class="divcheck">
				
				
				
				<!-- <a class="expor leftF" href="/file/exportContractExcel.action">导出</a> -->
				<!-- <span class="leftF"><b class="add icons"></b>坏账合同</span>  -->
				<span class="leftF"><b class="rec icons"></b>待处理合同</span>
					 <label class="leftF"><input id="undeal" type="checkbox" name="checkbox" />只显示未处理</label>
			</div>
			<div class="divtable">
				<div style="/*width: 1500px;*/">
					<table width="100%" border="0" cellspacing="0" cellpadding="0">
						<tr>
							<th width="3%">序号</th>
							<th width="4%">合同编号</th>
							<th width="8%">单位名称</th>
							<th width="4%">单位性质</th>
							<th width="5%">省份</th>
							<th width="4%">小组/细胞核</th>
							<th width="6%">负责人</th>
							<th width="6%">提交日期</th>
							<th width="10%">所含产品</th>
							<th width="5%">所属公司</th>
							<th width="6%">合同金额</th>
							<th width="6%">回款金额</th>
							<th width="7%">回款时间</th>
							<th width="5%">开票总金额</th>
							<th width="5%">坏账</th>
							<th width="6%">处理状态</th>
							<th width="4%">详情</th>
							<th width="6%">操作</th>
						</tr>

					<c:forEach var="c" items="${pageBean.list }" varStatus="cs">
					<c:if test="${c.dealConditon=='0'||c.dealConditon=='5'}">
				    <tr>
					<td bgcolor="#dcfcd3">${cs.index + 1}</td>
					<td bgcolor="#dcfcd3">${c.id }</td>
					<td bgcolor="#dcfcd3">${c.depart }</td>
					<td bgcolor="#dcfcd3">${c.xingzhi }</td>
					<td bgcolor="#dcfcd3">${c.cprovince }</td>
					<td bgcolor="#dcfcd3">${c.fourthLevel }</td>
					<td bgcolor="#dcfcd3">${c.charger }</td>
					<td bgcolor="#dcfcd3">${c.submitTime }</td>
					<td bgcolor="#dcfcd3">${c.product }</td>
					<td bgcolor="#dcfcd3">${c.company }</td>
					<td bgcolor="#dcfcd3">${c.contractMoney }</td>
					<td bgcolor="#dcfcd3">${c.receivedAmount }</td>
					<td bgcolor="#dcfcd3">${c.receiveTime }</td>
					<td bgcolor="#dcfcd3">${c.kaipiaoMoney}</td>
					<td bgcolor="#dcfcd3">${c.year }</td>
				    <c:if test="${c.dealConditon=='0'}">
				    <td bgcolor="#dcfcd3">未处理</td>
				    <td bgcolor="#dcfcd3"><a class="detail" id="${c.id }"	href="javascript:void(0)">详情</a></td>
				    </c:if>
				    <c:if test="${c.dealConditon=='5'}">
				    <td bgcolor="#dcfcd3">未处理(修改)</td>
				    <td bgcolor="#dcfcd3"><a class="detail2" id="${c.id }"	href="javascript:void(0)">详情</a></td>
				    </c:if>
				    <td bgcolor="#dcfcd3"></td>
			        </tr>
				    </c:if> 
 
                    <c:if test="${c.dealConditon!='0'&&c.dealConditon!='5'}"> 
							<tr>
								<td>${cs.index + 1}</td>
								<td>${c.id }</td>
								<td>${c.depart }</td>
								<td>${c.xingzhi }</td>
								<td>${c.cprovince }</td>
								<td>${c.fourthLevel }</td>
								<td>${c.charger }</td>
								<td>${c.submitTime }</td>
								<td>${c.product }</td>
								<td>${c.company }</td>
								<td>${c.contractMoney }</td>
								<td>${c.receivedAmount }</td>
								<td>${c.receiveTime }</td>
								<td>${c.kaipiaoMoney}</td>
								<td>${c.year }</td>
								<c:if test="${c.dealConditon=='1'}">
									<td>审核未通过</td>
								</c:if>
								<c:if test="${c.dealConditon=='2'}">
									<td class="dealCondition">审核已通过</td>
								</c:if>
								<c:if test="${c.dealConditon=='3'}">
									<td>合同完结</td>
								</c:if>
								<c:if test="${c.dealConditon=='6'}">
									<td>审核未通过(修改)</td>
								</c:if>
								<c:if test="${c.dealConditon=='7'}">
									<td>废弃</td>
								</c:if>
								<c:if test="${c.dealConditon==null}">
									<td>无</td>
								</c:if>
								<td><a class="detail" id="${c.id }"	href="javascript:void(0)">详情</a></td>
								
								<c:if test="${c.dealConditon=='1'}">
								     <td></td>
								</c:if>
								<c:if test="${c.dealConditon=='3'}">
								     <td></td>
								</c:if>
								<c:if test="${c.dealConditon=='7'}">
								     <td></td>
								</c:if>
								
								
								<c:if test="${c.dealConditon=='2'}">
								     <td><a class="wanjie" id="${c.id }" href="javascript:void(0)">完结</a>&nbsp; <a class="abandon" id="${c.id }" href="javascript:void(0)">废弃</a></td>
								</c:if>
								
								
							</tr>
                           </c:if> 
						</c:forEach>
					</table>
				</div>
			</div>

			<div class="paging">
			</div>
		</div>
	</div>
</div>

<script type="text/javascript" src="/app/views/hetong/js/selectlist.js"></script>
<script type="text/javascript" src="/app/views/hetong/js/pageUtil.js"></script>
<script type="text/javascript">
$(function(){
	$('select').selectlist({
		width: 130,
		height: 18
	});		
})
		
 // 分页  
$(document).ready(function(){
	//alert($pageBean.currentPage);
	page.showPage(${pageBean.currentPage},${pageBean.totalPages},"changeMethod");
});	
		
		
function changeMethod(nextPage){
	var danwei = $("#danwei .select-button").val();//单位性质
	var gongsi = $("#gongsi .select-button").val();//所属公司
	/* var chanpin = $("#chanpin .select-button").val();//产品 */
	/* var shengfen = $("#shengfen .select-button").val();//省份 */
	var zhuangtai = $("#zhuangtai .selected").attr("data-value");//状态 
	
	 //购买单位
	 var purchaseCom = $("#purchaseCom").val();
	 var purchaseCom1 = purchaseCom.trim()
	 //小组细胞核
	 var group = $("#group").val().trim();
	 //负责人
	 var responsibility = $("#responsibility").val().trim();
	 //用户ID
	 var userId = $("#userId").val().trim();
	 
     var data = {"purchaseCom":purchaseCom1,"danwei":danwei,"gongsi":gongsi,"group":group,"responsibility":responsibility,"zhuangtai":zhuangtai,"userId":userId,"page":nextPage};
    
     if($('input[id="undeal"]').prop("checked")){
 		$.get('public/ht/contractList.action',{zhuangtai:0,page:nextPage},function(result){
 			$('#container').html(result);
 			$('input[id="undeal"]').attr("checked","checked");
 			})
 	}else{
 	   $.post_('public/ht/contractList.action',data,function(result){
 			$('#container').empty();
 			
 			$('#container').html(result);
 			$("#purchaseCom").val(purchaseCom);
 			if(danwei!='全部'){
 			$("#danwei").val(danwei);
 			}
 			/* $("#danwei .select-button").val(danwei); */
 			if(gongsi!='全部'){
 				$("#gongsi").val(gongsi);
 			}
 			/* $("#gongsi .select-button").val(gongsi); */
 			
 			if(zhuangtai!='全部'){
 				$("#zhuangtai").val(zhuangtai);
 			}
 			$("#zhuangtai .select-button").val(zhuangtai);
 			
 			/* if(shengfen!='全国'){
 				$("#shengfen").val(shengfen);
 			}
 			$("#shengfen .select-button").val(shengfen); */
 			
 			$("#userId").val(userId);
 			$("#group").val(group);
 			$("#responsibility").val(responsibility);
 		}) 
 	}
     
/* 	if($('input[id="undeal"]').prop("checked")){
		$.get('public/ht/getUndealcontractList.action',{page:nextPage},function(result){
			$('#container').html(result);
			$('input[id="undeal"]').attr("checked","checked");
			})
	}else if(purchaseCom1 !=''){
		//搜索条件跳转
		var data = {"purchaseCom":purchaseCom,"page":nextPage};
		$.post_('public/ht/contractListCondition.action',data,function(result){
			$("#container").empty();
			$("#container").html(result);
			$("#purchaseCom").val(purchaseCom);
			})
	}else if(group !=''){
		//搜索条件跳转
		var data = {"group":group,"page":nextPage};
		$.post_('public/ht/contractListCondition.action',data,function(result){
			$("#container").empty();
			$("#container").html(result);
			$("#group").val(group);
			})
	}else if(responsibility !=''){
		//搜索条件跳转
		var data = {"responsibility":responsibility,"page":nextPage};
		$.post_('public/ht/contractListCondition.action',data,function(result){
			$("#container").empty();
			$("#container").html(result);
			$("#responsibility").val(responsibility);
			})
	}else if(userId !=''){
		//搜索条件跳转
		var data = {"userId":userId,"page":nextPage};
		$.post_('public/ht/contractListCondition.action',data,function(result){
			$("#container").empty();
			$("#container").html(result);
			$("#userId").val(userId);
			})
	}else{
		$.get('public/ht/contractList.action',{page:nextPage},function(result){
			$('#container').html(result);
			})
        } */
}
			

$("#undeal").click(function(){
	var purchaseCom = $("#purchaseCom").val();
	if($('input[id="undeal"]').prop("checked")){
		$.get('public/ht/contractList.action',{zhuangtai:0},function(result){
			$("#container").empty();
			$("#container").html(result);
			$('input[id="undeal"]').attr("checked","checked");
		})
	  }else{
		$.get('public/ht/contractList.action',function(result){
			$("#container").empty();
			$("#container").html(result);
		})
	}
	
	
})
		
$(".detail").click(function(){
	var dealCondition = $(this).parent().prev().text();
	var id = $(this).attr("id");
	if(dealCondition=='废弃'){
		$.get('public/ht/contractDetailForSale.action',{id:id},function(result){
			$('#container').html(result);
		})
	}else{
		$.get('public/ht/contractDetail.action',{id:id},function(result){
			$('#container').html(result);
		})
	}
})
//申请修改,进入对照页面
$(".detail2").click(function(){
	var id = $(this).attr("id");
	$.get('public/ht/contractCompare.action',{id:id},function(result){
		$('#container').html(result);
	})
})

	
$("#search").click(function(){
    var purchaseCom = $("#purchaseCom").val();//购买单位
	var danwei = $("#danwei .select-button").val();//单位性质
	var gongsi = $("#gongsi .select-button").val();//所属公司
	/* var chanpin = $("#chanpin .select-button").val();//产品 */
	/* var shengfen = $("#shengfen .select-button").val();//省份 */
	var group = $("#group").val();//小组
	var responsibility = $("#responsibility").val();//责任人
	var zhuangtai = $("#zhuangtai .selected").attr("data-value");//状态 
	console.log(zhuangtai);
	var userId = $("#userId").val(); //用户ID
	/* var data = {"year":year,"purchaseCom":purchaseCom,"danwei":danwei,"gongsi":gongsi,"chanpin":chanpin,"shengfen":shengfen,"group":group,"responsibility":responsibility,
		"zhuangtai":zhuangtai,"userId":userId};  */
	var data = {"purchaseCom":purchaseCom,"danwei":danwei,"gongsi":gongsi,"group":group,"responsibility":responsibility,"zhuangtai":zhuangtai,"userId":userId};
	   $.post_('public/ht/contractList.action',data,function(result){
		$('#container').empty();
		$('#container').html(result);
		$("#purchaseCom").val(purchaseCom);
		
		if(danwei!='全部'){
		$("#danwei").val(danwei);
		}
		
		 /* $("#danwei .select-button").val(danwei);  */
		if(gongsi!='全部'){
			$("#gongsi").val(gongsi);
		}
		/* $("#gongsi .select-button").val(gongsi); */
		
		
		
		if(zhuangtai!='全部'){
			$("#zhuangtai").val(zhuangtai);
		}
		$("#zhuangtai .select-button").val(zhuangtai);
		
		/* if(shengfen!='全国'){
			$("#shengfen").val(shengfen);
		}
		 $("#shengfen .select-button").val(shengfen);  */
		
		$("#userId").val(userId);
		$("#group").val(group);
		$("#responsibility").val(responsibility);
		}) 
	})
	
	
	$("#reset").click(function(){
		$("#purchaseCom").val("");
		$("#group").val("");
		$("#responsibility").val("");
		$("#userId").val("");
		$("#gongsi .select-button").val("全部");
		$("#danwei .select-button").val("全部");
	 	$("#zhuangtai .select-button").val("全部"); 
		$("#zhuangtai .selected").attr("data-value","-10");//重置为全部
	})
	//由审核已通过到合同完结
	$(".wanjie").click(function(){
	/* var id = $(this).parent().children('td').eq(1).text(); */
	var id = $(this).attr("id");
	$.messager.confirm("提示","您确认要改为合同完结状态吗？",function(){
		  $.post('public/ht/updateDealCondition.action',{"id":id},function(res){
			 
			   if(res.success==true){
					$.get('public/ht/contractList.action',function(result){
						$('#container').html(result);
					})
				   $.messager.alert('提示:','操作成功');
				 }else{
					 $.messager.alert('提示：','操作失败');
			   }
	       })
	   })
   });
   
	$(".abandon").click(function(){
	/* var id = $(this).parent().children('td').eq(1).text(); */
	var id = $(this).attr("id");
	$.messager.confirm("提示","您确认要废弃此合同吗？",function(){
		  $.post('public/ht/abandonContract.action',{"id":id},function(res){
			 
			   if(res.success==true){
					$.get('public/ht/contractList.action',function(result){
						$('#container').html(result);
					})
				   $.messager.alert('提示:','操作成功');
				 }else{
					 $.messager.alert('提示：','操作失败');
			   }
	       })
	   })
   });
   
   $(function(){
	   $("input").attr("autocomplete","off");
   }); 
   
</script>
