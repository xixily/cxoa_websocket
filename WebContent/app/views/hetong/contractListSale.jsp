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
			<!-- <label class="leftF"><input type="checkbox" name="checkbox" />全选</label> -->
			<!-- <a class="rease leftF" href="#">+新增合同</a> -->
			<!-- <a class="dele leftF" href="#">删除合同</a> -->
			<!-- <a class="expor leftF" href="#">导出</a> -->
			<span class="leftF"><b class="cet icons"></b>未通过合同</span>
		</div>
		<div class="divtable">
			<div style="width:1500px;">
			<table width="100%" border="0" cellspacing="0" cellpadding="0">
			  <tr>
				<th width="4%">序号</th>
				<th width="8%">合同编号</th>
				<th width="8%">单位名称</th>
				<th width="6%">用户性质</th>
				<th width="8%">单位性质</th>
				<th width="9%">登记时间</th>
				<th width="12%">所含产品</th> 
				<th width="8%">所属公司</th>
				<th width="6%">合同金额</th>
				<th width="6%">回款金额</th>
				<th width="8%">处理状态</th>
				<th width="4%">详情</th>
				<th width="4%">操作</th>
			  </tr>
			  
			   
			  <c:forEach var="c" items="${pageBean.list }" varStatus="cs">
			  <c:if test="${c[42]!='1'}"> 
			  <tr>
				<td>${cs.index + 1}</td>
				<td>${c[35]}</td>
				<td>${c[29]}</td>
				<td>${c[59]}</td>
				<td>${c[54]}</td>
				<td>${c[63]}</td>
				<td>${c[37]}</td>
				<td>${c[57]}</td>
				<td>${c[36]}</td>
				<td>${c[20]}</td>
				 
				<c:if test="${c[42]=='0'}"><td>未处理</td></c:if>
                <c:if test="${c[42]=='1'}"><td>审核未通过</td></c:if>
                <c:if test="${c[42]=='2'}"><td>审核已通过</td></c:if>
                <c:if test="${c[42]=='3'}"><td>合同完结</td></c:if>
                <c:if test="${c[42]=='4'}"><td>暂存</td></c:if>
                <td ><a class="detail" id="${c[35]}" href="javascript:void(0)">详情</a></td>
                <c:if test="${c[42]=='0'}"><td><a class="delete" id="${c[35]}" href="javascript:void(0)">删除</a></td></c:if>
                <c:if test="${c[42]=='4'}"><td><a class="delete" id="${c[35]}" href="javascript:void(0)">删除</a></td></c:if> 
			 </tr>
			  </c:if> 
			  
			   <c:if test="${c[42] =='1'}">
			   <td bgcolor="#dcfcd3">${cs.index + 1}</td>
				<td bgcolor="#dcfcd3">${c[35]}</td>
				<td bgcolor="#dcfcd3">${c[29]}</td>
				<td bgcolor="#dcfcd3">${c[59]}</td>
				<td bgcolor="#dcfcd3">${c[54]}</td>
				<td bgcolor="#dcfcd3">${c[63]}</td>
				<td bgcolor="#dcfcd3">${c[37]}</td>
				<td bgcolor="#dcfcd3">${c[57]}</td>
				<td bgcolor="#dcfcd3">${c[36]}</td>
				<td bgcolor="#dcfcd3">${c[20]} </td>
				<td bgcolor="#dcfcd3">审核未通过</td>
				<%-- <c:if test="${c[42]=='0'}"><td>未处理</td></c:if>
                <c:if test="${c[42]=='1'}"><td>审核未通过</td></c:if>
                <c:if test="${c[42]=='2'}"><td>审核已通过</td></c:if>
                <c:if test="${c[42]=='3'}"><td>合同完结</td></c:if> --%>
                <td bgcolor="#dcfcd3"><a class="detail" id="${c[35] }" href="javascript:void(0)">详情</a></td>
                <c:if test="${c[42]=='0'}"><td bgcolor="#dcfcd3"><a class="delete" id="${c[35] }" href="javascript:void(0)">删除</a></td></c:if>
                <c:if test="${c[42]=='4'}"><td bgcolor="#dcfcd3"><a class="delete" id="${c[35] }" href="javascript:void(0)">删除</a></td></c:if>
                
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
			/* location.href="/ht/contractList.action?page="+nextPage; */
			
			$.get('ht/contractListSale.action',{page:nextPage},function(result){
				$('#container').html(result);
				})
		}
		
$(".delete").click(function(){
	var id = $(this).attr("id");
	
	$.messager.confirm("提示","您确认要删除当前合同吗？",function(){
		  $.post('ht/deleteContract.action',{"id":id},function(res){
			   /* debugger; */
			   if(res.success==true){
					$.get('ht/contractListSale.action',function(result){
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
	$.get('ht/contractDetailForSale.action',{id:id},function(result){
		$('#container').html(result);
		})
	
	
})
</script>

