<%@ page language="java" pageEncoding="UTF-8" contentType="text/html; charset=UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<link href="/app/views/hetong/css/common.css" type="text/css" rel="stylesheet">

<!-- <script src="js/jquery-1.7.2.min.js"></script> -->
<script type="text/javascript" src="/app/views/hetong/js/publish.js"></script>
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
    color: #fff;
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
.mylink :hover{
		color:#428bca;
}
.wrap1000 {
    width: initial;
}
</style>
<div class="wrap1000">
	<h3 class="tit">单位查询</h3>
    <div style="height:31px;">
    	<a href="#" class="addGroup fr">+ 新增单位</a>
        <div class="selectBox fl">
			<p value="0" id="label1">全部</p>
			<ul>
				<li value="0"><a href="javascript:void(0)">全部</a></li>
				<li value="id"><a href="javascript:void(0)">ID</a></li>
				<li value="remarks"><a href="javascript:void(0)">备注</a></li>
				<li value="city"><a href="javascript:void(0)">城市</a></li>
				<li value="charger"><a href="javascript:void(0)">负责人</a></li>
				<li value="province"><a href="javascript:void(0)">省份</a></li>
				<li value="xingzhi"><a href="javascript:void(0)">性质</a></li>
				<li value="customerName"><a href="javascript:void(0)">用户名称</a></li>
			</ul>
		</div>
        <input type="text" id="seach1" class="oganizName fl" placeholder="请输入检索词"/>
        <input type="submit" class="query fl"  onclick="seach(1)" value="查 询"/>
    </div>
    <div style="height:31px;">
        <div class="selectBox fl">
			<p value="0" id="label2">全部</p>
			<ul>
				<li value="0"><a href="javascript:void(0)">全部</a></li>
				<li value="id"><a href="javascript:void(0)">ID</a></li>
				<li value="remarks"><a href="javascript:void(0)">备注</a></li>
				<li value="city"><a href="javascript:void(0)">城市</a></li>
				<li value="charger"><a href="javascript:void(0)">负责人</a></li>
				<li value="province"><a href="javascript:void(0)">省份</a></li>
				<li value="xingzhi"><a href="javascript:void(0)">性质</a></li>
				<li value="customerName"><a href="javascript:void(0)">用户名称</a></li>
			</ul>
		</div>
        <input type="text" id="seach2" class="oganizName fl" placeholder="请输入检索词"/>
    </div>
    <div class="tabList">
    	<table cellpadding="0" cellspacing="0">
        	<thead>
            	<tr>
                	<th width="10%" align="center" class="nobor">序号</th>
                    <th width="15%" align="center">单位名称</th>
                    <th width="15%" align="center">代理公司</th>
                    <th width="15%" align="center">性质</th>
                    <th width="15%" align="center">所在地</th>
                    <th width="15%" align="center">备注</th>
                    <th width="15%" align="center" class="nobor02">操作</th>
                </tr>
            </thead>
            <tbody>
            
            	<c:forEach var="customer" items="${customerList}" varStatus="cs">
            	<tr <c:if test="${cs.index%2==0 }">class="trBg"</c:if> >
                	<td align="center" class="nobor">${cs.index + 1}</td>
                    <td align="center" class="mylink"><a style="color:#428bca;" href="javascript:void(0)" onclick="childUnitList(${customer.id})">${customer.customerName }</a></td>
                    <td align="center">${customer.agentCompany }</td>
                    <td align="center">${customer.xingzhi }</td>
                    <td align="center">${customer.city }</td>
                    <td align="center">${customer.remarks }</td>
                    <td align="center" class="nobor02"><a href="javascript:void(0);" onclick="updateUI(${customer.id })">修改</a>&nbsp;&nbsp;&nbsp;&nbsp;<a href="javascript:void(0);" onclick="remove(${customer.id })">删除</a></td>
                </tr>
            	</c:forEach>
            	<!-- <tr class="trBg">
                	<td align="center" class="nobor">1</td>
                    <td align="center">天津大学<span class="gray">(天津大学)</span></td>
                    <td align="center">天津教育总代理</td>
                    <td align="center">高校</td>
                    <td align="center">天津</td>
                    <td align="center">经典</td>
                    <td align="center" class="nobor02"><a href="#">修改</a>&nbsp;&nbsp;&nbsp;&nbsp;<a href="#">删除</a></td>
                </tr> -->
            </tbody>
        </table>
    </div>
    <c:if test="${listsize==0 }"><div class="blank" align="center">无记录</div></c:if> 
    <div class="paging">
	</div>
    <!-- <div class="page">
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

<!---------------添加单位---------------------->
<div class="addBg"></div>
<div class="addJg">
	<h3 class="addH3"><span class="gb fr"></span>添加单位</h3>
    <div class="addCon">
    	<h3>单位信息</h3>
    	<form id="unitform" name="form1"> 
    	<input type="hidden" name="id" id="unitId" />
        <table cellpadding="0" cellspacing="0">
        	<tbody>
            	<tr>
                	<td align="right" width="30%">单位名称：</td>
                    <td align="left"><input name="customerName" type="text" class="addInp"></td>
                </tr>
                <tr>
                	<td align="right" width="30%">曾用名：</td>
                    <td align="left"><input name="oldName" type="text" class="addInp"></td>
                </tr>
                <tr>
                	<td align="right" width="30%">代理公司：</td>
                    <td align="left"><input name="agentCompany" type="text" class="addInp"></td>
                </tr>
                <!-- <tr>
                	<td align="right" width="30%">性质：</td>
                    <td align="left"><input name="xingzhi" type="text" class="addInp"></td>
                </tr> -->
                <tr>
                	<td align="right" width="13%">性质：</td>
                    <td align="left">
                    	<div class="selectBox" style="width:298px">
                            <p value="0" id="xingzhi">点击选择性质</p>
                            <ul style="width:298px">
                                <!-- <li value="1"><a href="javascript:void(0)">全部</a></li> -->
                                <c:forEach var="p" items="${xingzhiList}">
                                <li value="${p}"><a href="javascript:void(0)">${p}</a></li>
                                </c:forEach>
                            </ul>
                        </div>
                    </td>
                </tr>
                <tr>
                	<td align="right" width="30%">省份：</td>
                    <td align="left">
                    <!-- <div class="selectBox fl">
                        <p value="0">全部</p>
                        <ul>
                            <li value="1"><a href="javascript:void(0)">全部</a></li>
                            <li value="2"><a href="javascript:void(0)">拉斯款离开拉斯款离开</a></li>
                            <li value="3"><a href="javascript:void(0)">接收到</a></li>
                        </ul>
                    </div> -->
                    <input type="text" name="province" class="addInp" style="width:105px;height:29px">
                    &emsp;市：<input type="text" name="city" class="addInp" style="width:105px;height:29px"></td>
                </tr>
                <tr>
                	<td align="right" width="30%" valign="top">备注：</td>
                    <td align="left"><textarea class="addArea" name="remarks"></textarea></td>
                </tr>
                <tr>
                	<td align="right" width="30%" valign="top"></td>
                    <td align="left">
                    	<input type="button" value="保存" class="save sure">&emsp;&emsp;
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
		page.showPage(${pageBean.currentPage},${pageBean.totalPages},"seach");
	});
	$(function(){
		$("input").attr("autocomplete","off");
	});
	$(".addGroup").click(function(){
		$('form')[0].reset();
		$('#unitId').val("");
		$("#xingzhi").attr("value",0);
		$("#xingzhi").text("点击选择性质");
		$(".addBg").show();
		$(".addJg").show();	
	})	
	$(".gb").click(function(){
		$(".addBg").hide();
		$(".addJg").hide();	
	})	
	$(".sure").click(function(){
		var data = $("form").serialize();
		var xingzhi = $("#xingzhi").attr("value");
//		var a = $("#charger").val();
		if(xingzhi!=0){
			data = data+"&xingzhi="+xingzhi;
		}
		$.post('public/ht/addorUpdateUnit.action',data,function(res){
		   if(res.success==true){
				$.get('public/ht/unitList.action',function(result){
					$('#container').empty();
					$('#container').html(result);
				})
			  //  $.messager.alert('提示:','操作成功');
			}else{
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
	function remove(id){
		$.messager.confirm("提示","确定删除？",function(){
			$.post('public/ht/removeUnit.action',{'id':id},function(res){
				   if(res.success==true){
						$.get('public/ht/unitList.action',function(result){
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
	
	function updateUI(id){
		$('form')[0].reset();
		$.post('public/ht/getUnit.action',{'id':id},function(res){
			   if(res.success==true){
				   if(res.obj.xingzhi!=null){
					   $("#xingzhi").attr("value",res.obj.xingzhi);
				   	   $("#xingzhi").text(res.obj.xingzhi);
				   }else{
					   $("#xingzhi").attr("value",0);
				   	   $("#xingzhi").text("点击选择性质");
				   }
				   setForm(res.obj,$("form"));
				   $(".addBg").show();
				   $(".addJg").show();
				}
		})
	}
	
	function childUnitList(dId){
		$.get('public/ht/childUnitList.action',{'dId':dId},function(result){
			   $('#container').empty();
			   $('#container').html(result);
		})
	}
	
	function seach(nextPage){
	//	$('form')[0].reset();
		var label1 = $("#label1").attr("value");
		var labeltext1 = $("#label1").text();
		var label2 = $("#label2").attr("value");
		var labeltext2 = $("#label2").text();
		var seach1 = $("#seach1").val();
		var seach2 = $("#seach2").val();
		var data = "";
		if(label1!=""&&seach1!=""){
			data=label1+"="+seach1+"&";
		}
		if(label2!=""&&seach2!=""){
			data=data+label2+"="+seach2+"&";
		}
		/* if(data.charAt(data.length - 1)=="&"){
			data=data.substring(0,data.length-1)
		} */
		data = data+"page="+nextPage;
		$.get('public/ht/unitList.action?'+data,function(result){
		   $('#container').empty();
		   $('#container').html(result);
		   $("#seach1").val(seach1);
		   $("#seach2").val(seach2);
		   $("#label1").text(labeltext1);
		   $("#label2").text(labeltext2);
		   $("#label1").attr("value",label1);
		   $("#label2").attr("value",label2);
		})
	}
	
	 function setForm(jsonValue,obj) {  
		  //  alert("setForm");  
		  //  var obj=this;  
		    $.each(jsonValue, function (name, ival) {  
		        var $oinput = obj.find("input[name='" + name + "']");   
		        /* if ($oinput.attr("type")== "radio" || $oinput.attr("type")== "checkbox"){  
		             $oinput.each(function(){  
		                 if(Object.prototype.toString.apply(ival) == '[object Array]'){//是复选框，并且是数组  
		                      for(var i=0;i<ival.length;i++){  
		                          if($(this).val()==ival[i])  
		                             $(this).attr("checked", "checked");  
		                      }  
		                 }else{  
		                     if($(this).val()==ival)  
		                        $(this).attr("checked", "checked");  
		                 }  
		             });  
		        }else if($oinput.attr("type")== "textarea"){//多行文本框  
		            obj.find("[name="+name+"]").html(ival);  
		        }else{   */
		   obj.find("[name="+name+"]").val(ival);   
		    //    }  
		   });  
		};
</script>