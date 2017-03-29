<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
  <head>
    <base href="<%=basePath%>">
    
    <title>My JSP 'userForm.jsp' starting page</title>
    
	<meta http-equiv="pragma" content="no-cache">
	<meta http-equiv="cache-control" content="no-cache">
	<meta http-equiv="expires" content="0">    
	<meta http-equiv="keywords" content="keyword1,keyword2,keyword3">
	<meta http-equiv="description" content="This is my page">
	<script type="text/javascript" src="app/vendor/jquery/jquery.js"></script>
	<script type="text/javascript" src="app/vendor/json/json2.js"></script>
	<script type="text/javascript">
		
		$(document).ready(function(){
			var userList= new Array();
			userList.push({userName:"xx",address:"fff"});
			userList.push({userName:"zzzz",address:"ggggg"});
			$.ajax({
				url:"public/caiwu/user2.action",
				type:"post",
				data:JSON.stringify(userList),
				dataType:"json",
				contentType:"application/json",
				success:function(data){
				},error:function(data){
				}
			});
			/* $.ajax({
				url:"request/array/5,4,3,2,1",
				type:"get",
				dataType:"text",
				contentType:"text/json; charset=utf-8",
				success:function(data){
				},error:function(data){
				}
			}); */
			/* var array=new Array();
			array.push(1);
			array.push(2);
			array.push(3);
			array.push(4);
			array.push(5);
			$.ajax({
				url:"request/array",
				type:"post",
				dataType:"json",
				data:JSON.stringify(array),
				dataType:"json",
				contentType:"application/json",
				success:function(data){
				},error:function(data){
				}
			}); */
		});
	
	</script>
  </head>
  
  <body>
  		<form action="request/user" method="get" style="border:1px solid red;">
  			<table>
  				<tr><td colspan="2">这个表单演示了对象数据绑定的方法，以及对象中的Set，List，Array数据绑定（三者类似）</td></tr>
  				<tr>
  					<td>用户名：</td>
  					<td><input type="text" name="userName" value="张三"></td>
  				</tr>
  				
  				<tr>
  					<td>用户地址：</td>
  					<td><input type="text" name="address" value="江苏省无锡市新区菱湖大道200号"><br></td>
  				</tr>
  				
  				<tr>
  					<td>手机品牌：</td>
  					<td>
  						<input type="text" name="phones[0].brand" value="SONY"><br>
			  			<input type="text" name="phones[1].brand" value="MOTO"><br>
			  			<input type="text" name="phones[2].brand" value="LG"><br>
  					</td>
  				</tr>
  				
  				<tr>
  					<td>手机品牌2：</td>
  					<td>
  						<input type="text" name="phones2[0].brand" value="Apple"><br>
			  			<input type="text" name="phones2[1].brand" value="Samsung"><br>
			  			<input type="text" name="phones2[2].brand" value="HTC"><br>
  					</td>
  				</tr>
  				
  				<tr>
  					<td colspan="2" style="text-align: right;">
  						<input type="submit" value="提交">
  					</td>
  				</tr>
  			</table>
  			
  		</form>
  		
  		<form action="request/complex" method="POST" style="border:1px solid red;">
  			<table>
  				<tr>
  					<td>A对象：</td>
  					<td><input type="text" name="a.x" value="xxx"></td>
  				</tr>
  				<tr>
  					<td>B对象：</td>
  					<td><input type="text" name="b.x" value="yyy"><br></td>
  				</tr>
  				<tr>
  					<td colspan="2" style="text-align: right;">
  						<input type="submit" value="提交">
  					</td>
  				</tr>
  			</table>
  		</form>
  		
  		<form action="request/upload" method="POST" style="border:1px solid red;" enctype="multipart/from-data">
  			<table>
  				<tr><td colspan="2">这个表单演示@RequestPart的上传功能</td></tr>
  				<tr>
  					<td>B对象：</td>
  					<td><input type="file" name="file" value="选择文件"><br></td>
  				</tr>
  				
  				<tr>
  					<td colspan="2" style="text-align: right;">
  						<input type="submit" value="提交">
  					</td>
  				</tr>
  			</table>
  			
  		</form>
  </body>
</html>
