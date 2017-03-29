<%@ page language="java" pageEncoding="UTF-8" contentType="text/html; charset=UTF-8"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%><!DOCTYPE html>
<head>
	<base href="<%=basePath%>">
	<title>超星OA登录</title>
	<meta name="keywords" content="超星办公平台登录" />
	<meta name="description" content="超星办公平台登录" />
	<meta charset="UTF-8">
	<meta name="viewport" content="width=device-width, initial-scale=1,minimum-scale=1,maximum-scale=1,user-scalable=no">
	
	<!-- Custom Fonts -->
	<link href="app/vendor/font-awesome/css/font-awesome.min.css" rel="stylesheet" type="text/css">
	<!-- Bootstrap Core CSS -->
	<link href="app/vendor/bootstrap/css/bootstrap.min.css" rel="stylesheet">
	<!-- Bootstrap theme CSS -->
	<link href="app/vendor/bootstrap/css/bootstrap-theme.min.css" rel="stylesheet">
	<!-- Bootstrap social CSS -->
	<link href="app/vendor/bootstrap-social/bootstrap-social.css" rel="stylesheet">
	<!-- User login CSS-->
	<link href="app/dist/css/login.css" rel="stylesheet">
</head>
<body class="templatemo-bg-image">
	<div class="container">
		<div class="col-md-12">			
			<form class="form-horizontal templatemo-login-form" role="form" action="public/user/applogin.action" method="post">
				<div class="row">
					<div class="col-md-12">
						<h1>超星OA登录</h1>
					</div>
				</div>
				<div class="row">
					<div class="col-md-12">
				        <div class="form-group">
				          <div class="col-md-12">		          	
				            <label for="email" class="control-label">登录名/邮箱：</label>
				            <div class="templatemo-input-icon-container">
				            	<i class="fa fa-user"></i>
				            	<input type="email" name="email"  class="form-control" id="email"  placeholder="邮箱(*@*.*)..." required /><br/>
								<span style="color:red;">${user_login_error }</span>
				            </div>		            		            		            
				          </div>              
				        </div>
				        <div class="form-group">
				          <div class="col-md-12">
				            <label for="password" class="control-label">密码：</label>
				            <div class="templatemo-input-icon-container">
				            	<i class="fa fa-lock"></i>
				            	<input type="password" name="password" pattern="^(?![a-zA-z]+$)(?!\d+$)(?![!@#$%^&*]+$)[a-zA-Z\d!@#$%^&*]{6,14}$" class="form-control" id="password" placeholder="密码（非纯数字或纯字符型，长度大于6）..." required />
				            	<span style="color:red;">${login_check_error }</span>
				            </div>
				          </div>
				        </div>
				        <div class="form-group">
				          <div class="col-md-12">
				            <div class="checkbox">
				                <label>
				                  <input type="checkbox"> 记住登录状态
				                </label>
				            </div>
				          </div>
				        </div>
				        <div class="form-group">
				          <div class="col-md-12">
				            <input id="login_in" type="submit" value="登录" class="btn btn-warning">
				          </div>
				        </div>
				        <div class="form-group">
				          	<!--<div class="col-md-12">-->
				        		<!--<a href="forgot-password.html" class="text-center">忘记密码?</a>-->
				       	 	<!--</div>-->
				    	</div>
					</div>
				</div>
		      </form>		      		      
		</div>
	</div>
	<script type="text/javascript">
		var email = document.getElementById('email');
		var password = document.getElementById("password");
		email.oninput = function(){
			email.setCustomValidity("");
		}
		email.oninvalid=function(){
			email.setCustomValidity("请输入正确的邮箱格式（例如：123@abc.com）");
		}
		password.oninput = function(){
			password.setCustomValidity("");
				}
		password.oninvalid=function(){
			password.setCustomValidity("请输入6到14位的非纯数字或者纯字母的密码。（例如：123abc）");
				}
	</script>
</body>
</html>