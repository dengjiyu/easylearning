<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<%
	String path = request.getContextPath();
%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8">
<title>后台管理员登录-添加</title>



<!-- BootStrap引入必要文件 -->
<link rel="stylesheet"
	href="${pageContext.request.contextPath }/bootstrap/css/bootstrap.min.css">

<link rel="stylesheet"
	href="${pageContext.request.contextPath }/bootstrap/css/bootstrap.min.css">
<script type="text/javascript" src="/EasyLearning/js/jquery.js"></script>
<script type="text/javascript"
	src="${pageContext.request.contextPath }/bootstrap/js/bootstrap.min.js"></script>

<script type="text/javascript">
	function dosubmit() {
		var th = document.form1;
		if (th.dataname.value == "") {
			alert("亲~请先编辑标题...");
			th.username.focus();
			return;
		}
		if (th.dataauther.value == "") {
			alert("亲~请先编辑作者...");
			th.username.focus();
			return;
		}
		if (th.datacontent.value == "") {
			alert("亲~请先编辑内容...");
			th.username.focus();
			return;
		}
		if (th.dataimage.value == "") {
			alert("亲~请先编辑图片...");
			th.username.focus();
			return;
		}
		th.action="<%=path%>/servlet/DataAction?action_flag=add";
		th.submit();

	}
</script>


</head>
<body>
	<!-- 重要！！！
						method="post" enctype="multipart/form-data" 
 	-->
	<form action="" name="form1" method="post"
		enctype="multipart/form-data">
		<div class="container" style="margin-top: 20px">
			<div class="row clearfix">
				<div class="col-md-12 column">

					<div class="form-group">
						<label>文章标题</label><input type="text" class="form-control"
							name="dataname" />
					</div>
					<div class="form-group">
						<label>作者</label><input type="text" class="form-control"
							name="dataauther" />
					</div>
					<label> 资料内容 </label>
					<textarea class="form-control" name="datacontent" rows="12"></textarea>


					<div class="form-group" style="margin-top: 15px">
						<label>图片上传</label><input type="file" name="dataimage" id="movie_img"/>
						<p class="help-block">亲~请为您的资料配张图吧~</p>
					</div>

					<a class="btn btn-success" href="javascript:dosubmit();">发布</a>
					&nbsp;&nbsp; <input type="reset" role="button"
						class="btn btn-default">

				</div>
			</div>
		</div>
	</form>
</body>
</html>