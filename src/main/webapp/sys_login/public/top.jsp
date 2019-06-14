<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>

<%
	String path = request.getContextPath();
%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<title>Frame top</title>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<script type="text/javascript"
	src="${pageContext.request.contextPath }/sys/style/js/jquery.js"></script>
<script type="text/javascript"
	src="${pageContext.request.contextPath }/sys/style/js/page_common.js"></script>
<link
	href="${pageContext.request.contextPath }/sys/style/css/common_style_blue.css"
	rel="stylesheet" type="text/css" />

<style type="text/css">
<!--
body {
	margin: 0;
}

#Head_1 {
	background:
		url("${pageContext.request.contextPath }/sys/style/images/img/top_head1_bg.gif")
		repeat-x scroll 0 0 transparent;
	height: 64px;
	margin: 0 auto;
	width: 100%;
}

#Head_1 #Head_1_Logo {
	float: left;
	left: 20px;
	position: absolute;
	top: 12px;
	color: #F1F9FE;
	font-family: Arial Black, Arial;
	font-size: 28px;
}

#Head_1 #Head_1_UserWelcome {
	float: right;
	color: #B3E1FF;
	font-family: "宋体";
	font-size: 12px;
	height: 25px;
	padding-top: 11px;
	margin-right: 20px;
}

#Head_1 #Head_1_FunctionButton {
	float: left;
	position: absolute;
	right: 5px;
	top: 35px;
	margin-right: 30px;
	
}

#Head_1 #Head_1_FunctionButton img {
	margin-left: 10px;
}

#Head_2 {
	background:
		url("${pageContext.request.contextPath }/sys/style/images/img/top_head2_bg.gif")
		repeat-x scroll 0 0 transparent;
	border-bottom: 1px solid #FFFFFF;
	border-top: 1px solid #A0C6E1;
	height: 36px;
	margin: 0;
	width: 100%;
}

#Head_2 #Head2_Awoke {
	float: left;
	height: 100%;
	padding-left: 19px;
	padding-top: 2px;
}

#Head_2 #Head2_Awoke #AwokeNum {
	position: absolute;
	left: 20px;
	top: 68px;
	width: 406px;
	/*height: 21px;*/
	margin-top: 0;
	padding: 0;
	padding-top: 2px;
	list-style-type: none;
	margin-bottom: 0;
	margin-left: 0;
}

#Head_2 #Head2_Awoke #AwokeNum li {
	float: left;
	margin: 3px;
	display: inline;
}

#Head_2 #Head2_Awoke #AwokeNum a {
	color: #FFFFFF;
	font-family: "宋体";
	font-size: 12px;
	text-decoration: none;
}

#Head_2 #Head2_Awoke #AwokeNum .Line {
	border-left: 1px solid #005A98;
	border-right: 1px solid #A0C6E1;
	height: 17px;
	width: 0px;
}

#Head_2 #Head2_FunctionList, .Head2_FunctionList {
	color: #FFFFFF;
	float: right;
	font-family: "宋体";
	font-size: 13px;
	height: 100%;
	padding-right: 26px;
	padding-top: 10px;
}
-->
</style>


<!-- BootStrap引入必要文件 -->
<link rel="stylesheet"
	href="/EasyLearning/bootstrap/css/bootstrap.min.css">

<link rel="stylesheet"
	href="/EasyLearning/bootstrap/css/bootstrap.min.css">
<script type="text/javascript" src="/EasyLearning/js/jquery.js"></script>
<script type="text/javascript"
	src="/EasyLearning/bootstrap/js/bootstrap.min.js"></script>
<!-- 登录 -->
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
<!-- 注册 -->
<script type="text/javascript">
	function register() {
		var th = document.form2;
		if (th.username.value == "") {
			alert("用户名不能为空！！");
			th.username.focus();
			return;
		}
		if (th.password1.value == "") {
			alert("密码不能为空！！");
			th.password1.focus();
			return;
		}
		if (th.password2.value == "") {
			alert("密码不能为空！！");
			th.password2.focus();
			return;
		}

		
		if (th.password1.value != th.password2.value) {
			alert("两次密码不一致，请重新输入");
			th.password1.value = "";
			th.password2.value = "";
			
			
			return;
		}

		
		if (th.realname.value == "") {
			alert("姓名不能为空！！");
			return;
			th.realname.focus();
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

</head>

<body>

	<!-- 上部 -->
	<div id="Head_1">
		<!-- 标题 -->

		<div id="Head_1_Logo">
			<b style="font-family: '黑体'">“易学习App”后台管理系统</b>
			<!-- <img border="0" src="style/images/logo.png" /> -->
		</div>
		<!-- 欢迎用户的文字 -->

		<!-- 一些链接按钮 -->
		<div id="Head_1_FunctionButton">

			<a class="btn" role="button" href="#modal-login" data-toggle="modal" style="margin-top: 5px">
				<img width="110" height="35" 
				src="${pageContext.request.contextPath }/images/my/onLogin.png" />
			</a> <a class="btn" role="button" href="#modal-register"
				data-toggle="modal" style="margin-top: 5px"> <img width="110" height="35"
				src="${pageContext.request.contextPath }/images/my/onRegister.png" />
			</a>
		</div>
	</div>
	<!-- 下部 -->
	<div id="Head_2">
		<!-- 任务提醒 -->
		<div id="Head2_Awoke">
			<ul id="AwokeNum">

			</ul>
		</div>





		<!--登录对话框-->
		<div class="modal fade" id="modal-login" role="dialog"
			aria-hidden="true" aria-labelledby="myModalLabel">
			<div class="modal-dialog">
				<div class="modal-content">
					<div class="modal-header">
						<button class="close" aria-hidden="true" type="button"
							data-dismiss="modal">×</button>
						<h3 class="modal-title text-center text-primary" id="myModalLabel">
							用户登录</h3>
					</div>
					<form class="form-horizontal" role="form" method="post" 
						name="form1">
						<div class="modal-body">
							<div class="form-group">
								<label class="col-sm-4 control-label" for="username">
									用户名 </label>
								<div class="col-sm-6">
									<input class="form-control" id="username" name="username"
										type="text" required />
								</div>
							</div>
							<div class="form-group">

								<label class="col-sm-4 control-label" for="pwd"> 密码 </label>
								<div class="col-sm-6">
									<input class="form-control" id="pwd" name="password"
										type="password" required />
								</div>
							</div>
						</div>
						<div class="modal-footer">

							<a class='btn btn-success btn-default '
								href="javascript:login();" style="margin-right: 5px">登录</a>

							<button type="button" class="btn btn-default"
								data-dismiss="modal">取消</button>
						</div>
					</form>
				</div>
			</div>
		</div>

		<!--注册对话框-->
		<div class="modal fade" id="modal-register" role="dialog"
			aria-hidden="true" aria-labelledby="myModalLabel">
			<div class="modal-dialog">
				<div class="modal-content">
					<div class="modal-header">
						<button class="close" aria-hidden="true" type="button"
							data-dismiss="modal">×</button>
						<h3 class="modal-title text-center text-primary" id="myModalLabel">
							用户注册</h3>
					</div>
					<form class="form-horizontal" role="form" method="post" name="form2"
						>
						<div class="modal-body">
							<div class="form-group">

								<label class="col-sm-4 control-label" for="username">
									用户名 </label>
								<div class="col-sm-6">
									<input class="form-control" id="username" name="username" type="text"
										required />
								</div>
							</div>
							<div class="form-group">

								<label class="col-sm-4 control-label" for="pwd"> 密码 </label>
								<div class="col-sm-6">
									<input class="form-control" id="pwd" name="password1" type="password"
										required />
								</div>
							</div>
							<div class="form-group">

								<label class="col-sm-4 control-label" for="pwd2"> 确认密码 </label>
								<div class="col-sm-6">
									<input class="form-control" id="pwd2" name="password2"
										type="password" required />
								</div>
							</div>
							<div class="form-group">

								<label class="col-sm-4 control-label" for="text"> 姓名</label>
								<div class="col-sm-6">
									<input class="form-control" id="phone" name="realname" type="text"
										required />
								</div>
							</div>
							<div class="form-group">

								<label class="col-sm-4 control-label" for="text"> 产品码
								</label>
								<div class="col-sm-6">
									<input class="form-control" id="address" name="validate"
										type="text" required />
								</div>
							</div>
						</div>
						<div class="modal-footer">
						
							<a class='btn btn-success btn-default '
								href="javascript:register();" style="margin-right: 5px">注册</a>
							<button type="button" class="btn btn-default"
								data-dismiss="modal">取消</button>
						</div>
					</form>
				</div>
			</div>
		</div>



	</div>

</body>
</html>