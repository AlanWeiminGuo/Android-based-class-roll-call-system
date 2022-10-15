<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<meta name="viewport" content="width=device-width,initial-scale=1,maximum-scale=1.0" />
<meta http-equiv="X-UA-Compatible" content="IE=edge,chrome=1">
<title>登陆</title>
<script type="text/javascript">
if (top.location != self.location){   
    top.location = self.location;   
}
</script>
<style type="text/css">
.common-login-div {
	width: 400px;
	height: 320px; 
	background-color: #E7F2FD;
	position: absolute;
	left: 50%;
	top: 50%;
	margin-left: -205px;
	margin-top: -140px;
	box-shadow: 10px 10px 5px #545F64;
}

.common-body-bg {
	background-image: url("res/img/login-bg.jpg");
}

.common-login-title {
	padding: 20px 10px;
	background-color: #545F64;
	text-align: center;
	font-size: 26px;
	color: #fff;
}

.common-header {
	height: 60px;
	border-bottom: 1px solid #404553;
	background-color: #23262E;
}

.common-logo {
	width: 44px;
	height: 32px;
	position: absolute;
	left: 16px;
	top: 15px;
}

.common-nav{
	position: absolute;
	right: 0px;
}
</style>
<%@ include file="/WEB-INF/jsp/common/common.jsp"%>
</head>
<body class="common-body-bg">
	<div class="common-login-div">
		<div class="common-login-title">后台管理</div>
		<div style="margin-left: 50px;margin-top: 25px;">
			<form class="layui-form layui-form-pane" id="form">
				<div class="layui-form-item">
				    <label class="layui-form-label">用户名</label>
				    <div class="layui-input-inline">
				    	<input type="text" id="username" placeholder="用户名或工号" class="layui-input">
					</div>
				</div>
				<div class="layui-form-item">
				    <label class="layui-form-label">密码</label>
				    <div class="layui-input-inline">
				    	<input type="password" id="password" placeholder="密码" class="layui-input">
					</div>
				</div>
				
				<button class="layui-btn" type="button" style="margin-left: 100px;margin-top: 20px;width: 100px;" id="btn">登陆</button>
			</form>
		</div>
	</div>
<script type="text/javascript">
$(document).ready(function(){
	 var form = layui.form;
     //刷新界面 所有元素
     form.render();
	//登陆
	$('#btn').click(function(){
		var username = $('#username').val();
		var password = $('#password').val();
		if (!username) {
			layer.alert('用户名或工号不能为空');
			return;
		}
		if (!password) {
			layer.alert('密码不能为空');
			return;
		}
		
			var url = '${base}/user/login';
			var data = {
				username: username,
				password: $('#password').val()
			};
			var index = layer.load(1);
			$.post(url, data, function(result) {
				if (result.retcode == 1) {
					window.location.href = '${base}/index';
				} else {
					layer.alert(result.msg);
				}
			}, 'json').always(function(){
				//关闭弹层
				layer.close(index);
			});
	});
});
</script>
</body>
</html>