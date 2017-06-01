<%@ page language="java" pageEncoding="UTF-8" contentType="text/html; charset=UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!-- <!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" /> </head>
<title>销售人员查看合同列表</title>
-->

<link href="/app/views/hetong/css/global.css" rel="stylesheet" type="text/css" />
<link href="/app/views/hetong/css/space.css" rel="stylesheet" type="text/css" />
<link href="/app/views/hetong/css/selectlist.css" rel="stylesheet" type="text/css" />

<style>
.main * {
    -webkit-box-sizing: content-box;
    -moz-box-sizing: content-box;
    box-sizing: content-box;
}

.main {
    width: inherit;
}

.divtable {
    width: inherit;
}
</style>

<!-- 
<body> -->
<div class="main">
	<!-- <div class="untreated"><span>提交反馈<a href="#">12</a>份</span></div> -->
	
	<div class="contract">
		<div class="divcheck">
			<!-- <a class="rease leftF" href="#">+新增合同</a> -->
			<!-- <a class="expor leftF" href="#">导出</a> -->
			<span class="leftF"><b class="cet icons"></b>未通过合同</span>
		</div>
		<div class="divtable">
			<div style="/* width:1500px; */">
			<table width="100%" border="0" cellspacing="0" cellpadding="0">
			  <tr>
				<th width="4%">序号</th>
				<th width="8%">合同编号</th>
				<th width="8%">单位名称</th>
				<th width="6%">用户性质</th>
				<th width="4%">单位性质</th>
				<th width="9%">登记时间</th>
				<th width="12%">所含产品</th>
				<th width="8%">所属公司</th>
				<th width="6%">合同金额</th>
				<th width="6%">回款金额</th>
				<th width="6%">回款时间</th>
				<th width="6%">开票金额</th>
				<th width="8%">处理状态</th>
				<th width="4%">详情</th>
				<th width="4%">操作</th>
			  </tr>
			
			   
			  <c:forEach var="c" items="${pageBean.list }" varStatus="cs">
			  <c:if test="${c.dealConditon!='1'}">
			  <tr>
				<td>${cs.index + 1}</td>
				<td>${c.id }</td>
				<td>${c.depart }</td>
				<td>${c.user_property }</td>
				<td>${c.xingzhi }</td>
				<td>${c.dengjiTime }</td>
				<td>${c.product }</td>
				<td>${c.company }</td>
				<td>${c.contractMoney }</td>
				<td>${c.receivedAmount }</td>
				<td>${c.receiveTime}</td>
				<td>${c.kaipiaoMoney }</td>
				<c:if test="${c.dealConditon=='0'}"><td>未处理</td></c:if>
                <%-- <c:if test="${c.dealConditon=='1'}"><td>审核未通过</td></c:if> --%>
                <c:if test="${c.dealConditon=='2'}"><td>审核已通过</td></c:if>
                <c:if test="${c.dealConditon=='3'}"><td>合同完结</td></c:if>
                <c:if test="${c.dealConditon=='4'}"><td>暂存</td></c:if>
                <c:if test="${c.dealConditon==null}"><td>无</td></c:if>
                <%-- <c:if test="${c.dealConditon==''}"><td>无</td></c:if> --%>
								
                <td ><a class="detail" id="${c.id }" href="javascript:void(0)">详情</a></td>
                <c:if test="${c.dealConditon=='0'}"><td><a class="delete" id="${c.id }" href="javascript:void(0)">删除</a></td></c:if>
                <c:if test="${c.dealConditon=='4'}"><td><a class="delete" id="${c.id }" href="javascript:void(0)">删除</a></td></c:if>
                <c:if test="${c.dealConditon=='2'}"><td></td></c:if>
			  </tr>
			  </c:if> 
			  
			  <c:if test="${c.dealConditon =='1'}">
			   <tr>
			   <td bgcolor="#dcfcd3">${cs.index + 1}</td>
				<td bgcolor="#dcfcd3">${c.id }</td>
				<td bgcolor="#dcfcd3">${c.depart }</td>
				<td bgcolor="#dcfcd3">${c.user_property }</td>
				<td bgcolor="#dcfcd3">${c.xingzhi }</td>
				<td bgcolor="#dcfcd3">${c.dengjiTime }</td>
				<td bgcolor="#dcfcd3">${c.product }</td>
				<td bgcolor="#dcfcd3">${c.company }</td>
				<td bgcolor="#dcfcd3">${c.contractMoney }</td>
				<td bgcolor="#dcfcd3">${c.receivedAmount } </td>
				<td bgcolor="#dcfcd3">${c.receiveTime}</td>
				<td bgcolor="#dcfcd3">${c.kaipiaoMoney }</td>
				<td bgcolor="#dcfcd3">审核未通过</td>
				<%-- <c:if test="${c.dealConditon=='0'}"><td>未处理</td></c:if> --%>
                <%-- <c:if test="${c.dealConditon=='1'}"><td>审核未通过</td></c:if> --%>
                <%-- <c:if test="${c.dealConditon=='2'}"><td>审核已通过</td></c:if> --%>
                <%-- <c:if test="${c.dealConditon=='3'}"><td>合同完结</td></c:if> --%>
                <td bgcolor="#dcfcd3"><a class="detail" id="${c.id }" href="javascript:void(0)">详情</a></td>
                <td bgcolor="#dcfcd3"></td>
                <%-- <c:if test="${c.dealConditon=='0'}"><td bgcolor="#dcfcd3"><a class="delete" id="${c.id }" href="javascript:void(0)">删除</a></td></c:if>
                <c:if test="${c.dealConditon=='4'}"><td bgcolor="#dcfcd3"><a class="delete" id="${c.id }" href="javascript:void(0)">删除</a></td></c:if> --%>
			  </tr>
			  
			  </c:if> 
			   </c:forEach>
	<!-- 		  <tr>
				<td bgcolor="#dcfcd3"><input type="checkbox" name="checkbox" /></td>
				<td bgcolor="#dcfcd3">1</td>
				<td bgcolor="#dcfcd3">cx10285</td>
				<td bgcolor="#dcfcd3">吉大尔雅项目</td>
				<td bgcolor="#dcfcd3">吉林大学</td>
				<td bgcolor="#dcfcd3">教图</td>
				<td bgcolor="#dcfcd3">吉林</td>
				<td bgcolor="#dcfcd3">王允亚</td>
				<td bgcolor="#dcfcd3">王允亚</td>
				<td bgcolor="#dcfcd3">2017-3-31</td>
				<td bgcolor="#dcfcd3">中文发现、读秀</td>
				<td bgcolor="#dcfcd3">世纪超星</td>
				<td bgcolor="#dcfcd3">300，000</td>
			  </tr>
			  <tr>
				<td bgcolor="#fbd3d3"><input type="checkbox" name="checkbox" /></td>
				<td bgcolor="#fbd3d3">1</td>
				<td bgcolor="#fbd3d3">cx10285</td>
				<td bgcolor="#fbd3d3">吉大尔雅项目</td>
				<td bgcolor="#fbd3d3">吉林大学</td>
				<td bgcolor="#fbd3d3">教图</td>
				<td bgcolor="#fbd3d3">吉林</td>
				<td bgcolor="#fbd3d3">王允亚</td>
				<td bgcolor="#fbd3d3">王允亚</td>
				<td bgcolor="#fbd3d3">2017-3-31</td>
				<td bgcolor="#fbd3d3">中文发现、读秀</td>
				<td bgcolor="#fbd3d3">世纪超星</td>
				<td bgcolor="#fbd3d3">300，000</td>
			  </tr> -->
			  
			</table>
			</div>
		</div>
		<div class="paging">
		
		</div>
	</div>
	
	  <c:if test="${empty pageBean.list}">  
         <span class="leftF"><b class="cet icons"></b>没有相关合同信息</span>                     
      </c:if> 
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
</script>
<script type="text/javascript">
/* $('.divtable tr').toggle(function(){
	$(this).addClass('curtent');
},function(){
	$(this).removeClass('curtent');
}) */

 // 分页  
$(document).ready(function(){
	//alert($pageBean.currentPage);
	page.showPage(${pageBean.currentPage},${pageBean.totalPages},"changeMethod");
});	
	
		
function changeMethod(nextPage){
			/* location.href="/public/ht/contractList.action?page="+nextPage; */
			
			$.get('public/ht/contractListSale.action',{page:nextPage},function(result){
				$('#container').html(result);
				})
		}
		
$(".delete").click(function(){
	var id = $(this).attr("id");
	
	$.messager.confirm("提示","您确认要删除当前合同吗？",function(){
		  $.post('public/ht/deleteContract.action',{"id":id},function(res){
			   /* debugger; */
			   if(res.success==true){
					$.get('public/ht/contractListSale.action',function(result){
					$('#container').html(result);
					})
				 }else{
					 $.messager.alert('提示：',res.msg);
			   }
	       })
	   })
});	

//点击详情
$(".detail").click(function(){
	var id = $(this).attr("id");
	var dealCondition = $(this).parent().prev().text();
	if(dealCondition=="暂存"){
		var state = 4;
	}else if(dealCondition=="审核未通过"){
		var state = 1;
	}else{}
	if(dealCondition=='未处理'||dealCondition=='审核已通过'||dealCondition=='合同完结'){
		$.get('public/ht/contractDetailForSale.action',{id:id},function(result){
			$('#container').html(result);
			})
	}else{
		//审核未通过  暂存
		$.get('public/ht/contractDetailForSale2.action',{"id":id,"state":state},function(result){
			$('#container').html(result);
			})
	}
	
})
</script>

