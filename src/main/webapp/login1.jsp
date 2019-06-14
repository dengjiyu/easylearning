<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<%
	String path = request.getContextPath();
%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8">
<title>后台管理员登录</title>

<script type="text/javascript">
	function login() {
		var th = document.form1;
		if (th.username.value == "") {
			alert("用户名不能为空！！");
			th.username.focus();
			return ;
		}
		if (th.password.value == "") {
			alert("用户名不能为空！！");
			th.password.focus();
			return ;
		}
		th.action = "<%=path%>/servlet/LoginAction";
		th.submit();

	}
</script>

<!-- BootStrap引入必要文件 -->
<link rel="stylesheet"
	href="/EasyLearning/bootstrap/css/bootstrap.min.css">

<link rel="stylesheet"
	href="/EasyLearning/bootstrap/css/bootstrap.min.css">
<script type="text/javascript" src="/EasyLearning/js/jquery.js"></script>
<script type="text/javascript"
	src="/EasyLearning/bootstrap/js/bootstrap.min.js"></script>

</head>
<body>
	<form action="" name="form1" method="post">
		<div class="container">
			<div class="row-fluid">
				<div class="span12">
					<h3 class="text-center">管理员登录界面</h3>
				</div>
			</div>

			<div class="row clearfix">
				<div class="col-md-4 col-md-offset-4 column">

					<div class="form-group">
						<label>用户名</label><input type="text" name="username"
							class="form-control" />
					</div>
					<div class="form-group">
						<label>密码</label><input type="password" name="password"
							class="form-control" />
					</div>



					<div class="panel-footer text-center">
						<a class='btn btn-success btn-default ' href="javascript:login();" style="margin-right: 20px">登录</a>
						<a class='btn btn-default btn-warning'
							href="<%=path%>/register.jsp">注册新用户</a>
					</div>



				</div>
			</div>
		</div>
	</form>
</body>
</html>