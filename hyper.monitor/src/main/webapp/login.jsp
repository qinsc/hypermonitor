<%@page pageEncoding="UTF-8" contentType="text/html; charset=UTF-8"%>


<!DOCTYPE html >
<html>
<head>
<%@include file="/views/common/common.jsp"%>
<meta charset="utf-8" />
<title>Login</title>
</head>
<body>
	<div class="content">
		<form method="post" id="loginForm"
			action="<%=contextPath%>/rest/users/login">
			<h3 class="form-title">
			</h3>
			<div>
				<label> 用户名
				</label>
				<div>
					<input type="text"  name="username" id="username" />
				</div>
			</div>
			<div>
				<label> 密码 
				</label>
				<div >
					<input type="password" name="password" id="password" />
				</div>
			</div>
			<div class="form-actions">
				<input type="button" value="登陆" onclick="login()"> 
				<br/>
			</div>
			<div class="forget-password">
				<p id="errorMessage" style="color: red;"></p>
			</div>
		</form>
	</div>
	<br />
	<script type="text/javascript">
		function login() {
			var username = document.getElementById("username").value;
			var password = document.getElementById("password").value;
			if (null == username || username.trim().length == 0
					|| null == password || password.trim().length == 0) {
				document.getElementById("errorMessage").innerHTML = '请输入用户名与密码';
				return;
			}
			document.getElementById("loginForm").submit();
		}
	</script>
</body>
</html>