<%@ page language="java" pageEncoding="UTF-8" contentType="text/html; charset=UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<link href="/app/views/hetong/css/common.css" type="text/css" rel="stylesheet"> 
<!-- <script src="js/jquery-1.7.2.min.js"></script> -->
<script type="text/javascript" src="/app/views/hetong/js/publish2.js"></script>
<script type="text/javascript" src="/app/views/hetong/js/pageUtil.js"></script>
<style>
a {
    color: #428bca;
    text-decoration: none;
}
.tabList td a {
    color: #428bca;
}
h1, .h1, h2, .h2, h3, .h3 {
    margin-top: initial;
    margin-bottom: initial;
}
.wrap1180 a:hover,.wrap1180 a:focus {
    text-decoration: initial;
}
.addJg *{
    -webkit-box-sizing: initial;
    -moz-box-sizing: initial;
    box-sizing: initial;
} 
.addJg {
    top: 10%;
}
.sidebar {
    z-index: 0;
}
a:hover, a:focus {
    text-decoration: initial;
}
th {
    text-align: center;
}
.paging {
    height: 24px;
    line-height: 24px;
    padding: 20px;
    text-align: center;
}
.paging a {
    margin: 0 6px;
}
.paging a.cur {
    font-weight: bold;
}
.blank {
    font-size: 20px;
    color: #bbb4b4;
    margin-top: 20px;
}
.wrap1180 {
    width: initial;
}
</style>
<div class="wrap1180">
    <div style="height:31px;line-height:31px">
    	<a href="#" class="addGroup fr">+ 添加用户</a>
        <a href="#">用户查询</a> > <span class="schoolName">${customer.customerName}</span>
    </div>
    <div class="tabList">
    	<table cellpadding="0" cellspacing="0">
        	<thead>
            	<tr>
                	<th width="10%" align="center" class="nobor">序号</th>
                    <th width="10%" align="center">用户名称</th>
                    <th width="10%" align="center">省份</th>
                    <th width="10%" align="center">性质</th>
                    <th width="10%" align="center">类别</th>
                    <th width="10%" align="center">负责人</th>
					<th width="10%" align="center">小组</th>
                    <!-- <th align="center">最后合作时间</th> -->
                    <th width="10%" align="center">公司名称</th>
                    <th width="10%" align="center">备注</th>
                    <th width="10%" align="center" class="nobor02">操作</th>
                </tr>
            </thead>
            <tbody>
                <c:forEach var="customerDepart" items="${customerDepartList}" varStatus="cs">
            	<tr <c:if test="${cs.index%2==0 }">class="trBg"</c:if> >
                	<td align="center" class="nobor">${cs.index + 1}</td>
                    <td align="center">${customerDepart.customerName}</td>
                    <td align="center">${customerDepart.province}</td>
                    <td align="center">${customerDepart.xingzhi}</td>
                    <td align="center">${customerDepart.category}</td>
                    <td align="center">${customerDepart.charger}</td>
                    <td align="center">${customerDepart.fourthLevel}</td>
                    <%-- <td align="center">${customerDepart.lastDate}</td> --%>
                    <td align="center">${customerDepart.firstLevel}</td>
                    <td align="center">${customerDepart.remarks}</td>
                    <td align="center" class="nobor02"><a href="javascript:void(0);" onclick="updateUI(${customerDepart.id })">修改</a>&nbsp;&nbsp;&nbsp;&nbsp;<a href="javascript:void(0);" onclick="remove(${customerDepart.id })">删除</a></td>
                </tr>
                </c:forEach>
                <!-- <tr>
                	<td align="center" class="nobor">1</td>
                    <td align="center">教务处</td>
                    <td align="center">呼和浩特</td>
                    <td align="center">2015/4</td>
                    <td align="center">2016/7</td>
                    <td align="center">高校</td>
                    <td align="center">教务</td>
                    <td align="center">冯树东</td>
                    <td align="center">北京世纪独秀技术公司</td>
                    <td align="center">名办</td>
                    <td align="center" class="nobor02"><a href="#">修改</a>&nbsp;&nbsp;&nbsp;&nbsp;<a href="#">删除</a></td>
                </tr> -->
            </tbody>
        </table>
    </div>
    <c:if test="${listsize==0 }"><div class="blank" align="center">无记录</div></c:if> 
    <div class="paging">
	</div>
  <!--   <div class="page">
        <a href="#">首页</a>
        <a href="#">上一页</a>
        <a href="#">1</a>
        <a href="#">2</a>
        <a href="#">3</a>
        <a href="#">4</a>
        <a href="#">5</a>
        <a href="#">下一页</a>
        <a href="#">尾页</a>
    </div> -->
</div>

<!---------------添加机构---------------------->
<div class="addBg"></div>
<div class="addJg" style="width:1000px;left:20%">
	<h3 class="addH3"><span class="gb fr"></span>添加机构</h3>
    <div class="addCon">
    	<h3>机构信息</h3>
    	<form id="form1" name="form1"> 
    	<input type="hidden" name="id" id="childUnitId"/>
        <table cellpadding="0" cellspacing="0">
        	<tbody>
            	<tr>
                	<td align="right" width="13%">用户名称：</td>
                    <td align="left"><input id="customerName" name="customerName" type="text" class="addInp"></td>
                    <td align="right" width="20%">省份：</td>
                    <td align="left"><input id="province" name="province" type="text" class="addInp"></td>
                </tr>
                <!-- <tr>
                	<td align="right" width="13%">开始合作时间：</td>
                    <td align="left"><input type="text" class="addInp"></td>
                    <td align="right" width="20%">最后合作时间：</td>
                    <td align="left"><input type="text" class="addInp"></td>
                </tr> -->
                <tr>
                	<td align="right" width="13%">类别：</td>
                    <td align="left">
                    	<div class="selectBox" style="width:298px">
                            <p value="0" id="category">点击选择类别</p>
                            <ul style="width:298px">
                                <!-- <li value="1"><a href="javascript:void(0)">全部</a></li> -->
                                <li value="个人"><a href="javascript:void(0)">个人</a></li>
                                <li value="中小学"><a href="javascript:void(0)">中小学</a></li>
                                <li value="保卫处"><a href="javascript:void(0)">保卫处</a></li>
                                <li value="信息中心"><a href="javascript:void(0)">信息中心</a></li>
                                <li value="公图"><a href="javascript:void(0)">公图</a></li>
                                <li value="内部"><a href="javascript:void(0)">内部</a></li>
                                <li value="创业学院"><a href="javascript:void(0)">创业学院</a></li>
                                <li value="加工"><a href="javascript:void(0)">加工</a></li>
                                <li value="史强中职"><a href="javascript:void(0)">史强中职</a></li>
                                <li value="后台"><a href="javascript:void(0)">后台</a></li>
                                <li value="大医"><a href="javascript:void(0)">大医</a></li>
                                <li value="大成"><a href="javascript:void(0)">大成</a></li>
                                <li value="大雅"><a href="javascript:void(0)">大雅</a></li>
                                <li value="学习本"><a href="javascript:void(0)">学习本</a></li>
                                <li value="报告厅"><a href="javascript:void(0)">报告厅</a></li>
                                <li value="政府机构"><a href="javascript:void(0)">政府机构</a></li>
                                <li value="教务"><a href="javascript:void(0)">教务</a></li>
                                <li value="教图"><a href="javascript:void(0)">教图</a></li>
                                <li value="朗润"><a href="javascript:void(0)">朗润</a></li>
                                <li value="期刊"><a href="javascript:void(0)">期刊</a></li>
                                <li value="机构"><a href="javascript:void(0)">机构</a></li>
                                <li value="法源"><a href="javascript:void(0)">法源</a></li>
                                <li value="海外"><a href="javascript:void(0)">海外</a></li>
                                <li value="版权"><a href="javascript:void(0)">版权</a></li>
                                <li value="电大社区"><a href="javascript:void(0)">电大社区</a></li>
                                <li value="研发产品"><a href="javascript:void(0)">研发产品</a></li>
                                <li value="研究生院"><a href="javascript:void(0)">研究生院</a></li>
                                <li value="研究院"><a href="javascript:void(0)">研究院</a></li>
                                <li value="视频"><a href="javascript:void(0)">视频</a></li>
                                <li value="超星个人站"><a href="javascript:void(0)">超星个人站</a></li>
                            </ul>
                        </div>
                    </td>
                    <td align="right" width="20%">销售邮箱：</td>
                    <td align="left"><input id="email" name="email" type="text" class="addInp"></td>
                </tr>
                <tr>
                	<td align="right" width="13%">销售负责人：</td>
                    <td align="left"><input id="charger" name="charger" type="text" class="addInp" disabled="disabled"></td>
                    <td align="right" width="20%">公司：</td>
                    <td align="left"><input id="firstLevel" name="firstLevel" type="text" class="addInp" disabled="disabled"></td>
                </tr>
                <tr>
                	<td align="right" width="13%">部门：</td>
                    <td align="left"><input id="secondLevel" name="secondLevel" type="text" class="addInp" disabled="disabled"></td>
                    <td align="right" width="20%">岗位：</td>
                    <td align="left"><input id="thirdLevel" name="thirdLevel" type="text" class="addInp" disabled="disabled"></td>
                </tr>
                <tr>
                	<td align="right" width="13%">小组：</td>
                    <td align="left"><input id="fourthLevel" name="fourthLevel" type="text" class="addInp" disabled="disabled"></td>
                    <td align="right" width="20%">细胞核邮箱：</td>
                    <td align="left"><input id="cemail" name="cemail" type="text" class="addInp" disabled="disabled"></td>
                </tr>
                <tr>
                	<td align="right" width="18%">省级指导邮箱：</td>
                    <td align="left"><input id="gemail" name="gemail" type="text" class="addInp" disabled="disabled"></td>
                    <td align="right" width="20%">行业指导邮箱：</td>
                    <td align="left"><input id="hemail" name="hemail" type="text" class="addInp"></td>
                </tr>
                <tr>
                	<td align="right" width="13%" valign="top">备注：</td>
                    <td align="left" colspan="3">
                    	<textarea class="addArea" id="remarks" name="remarks" style="width:750px"></textarea>
                    </td>
                </tr>
                <tr>
                   <td align="center" colspan="4">
                    	<input type="button" value="保存" class="save sure">  
                        <input type="button" value="取消" class="save cancel" style="background:#ccc">
                    </td>
                </tr>
            </tbody>
        </table>
        </form>
    </div>
</div>
<script type="text/javascript">
$(function(){
	page.showPage(${pageBean.currentPage},${pageBean.totalPages},"changeMethod");
});
$(function(){
	$("input").attr("autocomplete","off");
});
function changeMethod(nextPage){
	var dId = ${customer.id};
	$.get('public/ht/childUnitList.action',{'dId':dId,'page':nextPage},function(result){
		   $('#container').empty();
		   $('#container').html(result);
	})
}

$(".gb").click(function(){
	$(".addBg").hide();
	$(".addJg").hide();	
})	

$(".addGroup").click(function(){
	$('form')[0].reset();
	$('#childUnitId').val("");
	$("#category").attr("value",0);
	$("#category").text("点击选择类别");
	$(".addBg").show();
	$(".addJg").show()	
})	

//添加或更新,后台根据有无id判断
$(".sure").click(function(){
//	charger firstLevel secondLevel thirdLevel fourthLevel cemail gemail
	//移除disabled才能序列化表单
	$("#charger").removeAttr("disabled");
	$("#firstLevel").removeAttr("disabled");
	$("#secondLevel").removeAttr("disabled");
	$("#thirdLevel").removeAttr("disabled");
	$("#fourthLevel").removeAttr("disabled");
	$("#cemail").removeAttr("disabled");
	$("#gemail").removeAttr("disabled");
	var data = $("form").serialize();
	/* var data = {
            uname:$("#uname").val(),
            mobileIpt:$("#mobileIpt").val(),
            birthday:$("#birthday").val()
        }; */
//	data = decodeURIComponent(data, true);
	data = data+"&dId=${customer.id}&xingzhi=${customer.xingzhi}";
	var category = $("#category").attr("value");
//	var a = $("#charger").val(); 
	if(category!=0){
		data = data+"&category="+category;
	}
	$.post('public/ht/addorUpdateChildUnit.action',data,function(res){
	   if(res.success==true){
			var dId = ${customer.id};
			$.get('public/ht/childUnitList.action',{'dId':dId},function(result){
				$('#container').empty();
				$('#container').html(result);
			})
		  //  $.messager.alert('提示:','操作成功');
		}else{
			//如果出错加回disable
			$("#charger").attr("disabled","disabled");
			$("#firstLevel").attr("disabled","disabled");
			$("#secondLevel").attr("disabled","disabled");
			$("#thirdLevel").attr("disabled","disabled");
			$("#fourthLevel").attr("disabled","disabled");
			$("#cemail").attr("disabled","disabled");
			$("#gemail").attr("disabled","disabled");
			$.messager.alert('提示：','保存失败');
	    }
    })
	$(".addBg").hide();
	$(".addJg").hide();	  
})	
$(".cancel").click(function(){
	$(".addBg").hide();
	$(".addJg").hide();
})

$("#email").blur(function(){
	var email = $("#email").val();
	$.get('public/ht/getUseInfo.action',{'email':email},function(result){
		   var userName = result.obj.userName;
		   var orgStructure = result.obj.orgStructure;
		   if(userName!=null){
			   $("#charger").val(userName.username);
		   }
		   if(orgStructure!=null){
			   $("#firstLevel").val(orgStructure.firstLevel);
			   $("#secondLevel").val(orgStructure.secondLevel);
			   $("#thirdLevel").val(orgStructure.thirdLevel);
			   $("#fourthLevel").val(orgStructure.fourthLevel);
			   $("#cemail").val(orgStructure.cellCoreEmail);
			   $("#gemail").val(orgStructure.guidanceEmail);
		   }
		//   $('#container').empty();
		//   $('#container').html(result);
	})
})

function updateUI(id){
	$('form')[0].reset();
	$.post('public/ht/getChildUnit.action',{'id':id},function(res){
		   if(res.success==true){
			   if(res.obj.category!=null){
				   $("#category").attr("value",res.obj.category);
			   		$("#category").text(res.obj.category);
			   }else{
				   $("#category").attr("value",0);
			   	   $("#category").text("点击选择类别");
			   }
			   setForm(res.obj,$("form"));
			   $(".addBg").show();
			   $(".addJg").show();
			}
	})
}

function remove(id){
	$.messager.confirm("提示","确定删除？",function(){
		$.post('public/ht/removeChildUnit.action',{'id':id},function(res){
			   if(res.success==true){
					var dId = ${customer.id};
					$.get('public/ht/childUnitList.action',{'dId':dId},function(result){
						$('#container').empty();
						$('#container').html(result);
					})
				  //  $.messager.alert('提示:','操作成功');
				}else{
					$.messager.alert('提示：','删除失败');
			    }
		});
	});
}

function setForm(jsonValue,obj) {  
    $.each(jsonValue, function (name, ival) {  
        var $oinput = obj.find("input[name='" + name + "']");   
   		obj.find("[name="+name+"]").val(ival);   
    });  
};
</script>