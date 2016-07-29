<%@page pageEncoding="UTF-8" contentType="text/html; charset=UTF-8"%>
<!DOCTYPE html >
<html>
<head>
	<meta charset="utf-8">
	<meta http-equiv="X-UA-Compatible" content="IE=edge">
	<meta name="viewport" content="width=device-width, initial-scale=1">
	<!-- 上述3个meta标签*必须*放在最前面，任何其他内容都*必须*跟随其后！ -->
	<meta name="description" content="">
	<meta name="author" content="">
	
	<title>登陆</title>
	
	<link href="//cdn.bootcss.com/bootstrap/3.3.5/css/bootstrap.min.css" rel="stylesheet">
	<link rel="stylesheet" type="text/css" href="resources/css/signin.css" />
	<%@include file="views/common/common.jsp"%>
</head>
<body>
	<div class="container">
		<form class="form-signin" method="post" id="loginForm"
			action="<%=contextPath%>/rest/users/login">
			<h2 class="form-signin-heading">请登陆</h2>
			<label for="username" class="sr-only">用户名</label>
			<input type="text" id="username" class="form-control" placeholder="用户名" required autofocus>
			<label for="password" class="sr-only">密 码</label>
			<input type="password" id="password" class="form-control" placeholder="密码" required>
			<button class="btn btn-lg btn-primary btn-block" type="button" onclick="login()">登 陆</button>
			<div>
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