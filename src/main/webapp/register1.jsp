<%@ page language="java" contentType="text/html; charset=utf-8"
	pageEncoding="utf-8"%>

<%
	String path = request.getContextPath();
%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8">
<title>后台管理员注册</title>
<script type="text/javascript">
	function dosubmit() {
		var th = document.form1;
		if (th.username.value == "") {
			alert("用户名不能为空！！");
			th.username.focus();
			return;
		}
		if (th.realname.value == "") {
			alert("姓名不能为空！！");
			return;
			th.realname.focus();
		}
		if (th.password.value == "") {
			alert("密码不能为空！！");
			th.password.focus();
			return;
		}
		if (th.validate.value == "") {
			alert("产品码不能为空！！");
			th.validate.focus();
			return;
		}
		th.action="<%=path%>/servlet/RegisterAction";
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
					<h3 class="text-center">管理员注册界面</h3>
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
					<div class="form-group">
						<label>姓名</label><input type="text" name="realname"
							class="form-control" />
					</div>
					<div class="form-group">
						<label>产品号</label><input type="text" name="validate"
							class="form-control" />
					</div>


					<TABLE style="margin: 0px auto;">
						<TBODY>
							<TR>
								<TD height=20 align="center"><a
									href="javascript:dosubmit();"><img
										src="<%=path%>/images/ok_1.jpg" name="Image8" width="80"
										height="30" border="0"></a>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;<a
									href="<%=path%>/index.jsp"><img
										src="<%=path%>/images/fh_1.jpg" name="Image9" width="80"
										height="30" border="0"></a></TD>

							</TR>
						</TBODY>
					</TABLE>



				</div>
			</div>
		</div>
	</form>
</body>
</html>