<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<%@ page import="top.easylearning.util.*"%>
<%
	String path = request.getContextPath();
	//接收Action中viewData方法中传递过来的值
	Map<String, Object> map = (Map<String, Object>) request.getAttribute("map");
%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8">
<title>后台管理员登录-显示</title>



<!-- BootStrap引入必要文件 -->
<link rel="stylesheet"
	href="${pageContext.request.contextPath }/bootstrap/css/bootstrap.min.css">

<link rel="stylesheet"
	href="${pageContext.request.contextPath }/bootstrap/css/bootstrap.min.css">
<script type="text/javascript" src="${pageContext.request.contextPath }/js/jquery.js"></script>
<script type="text/javascript"
	src="${pageContext.request.contextPath }/bootstrap/js/bootstrap.min.js"></script>





</head>
<body>
		<div class="container ">
			<div class="row clearfix " style="margin-top: 30px">
				<div class="col-md-12 column">
					<div class="panel panel-info ">



						<div class="panel-heading">
							<h4 style="text-align: center; font-weight:bold; width: 100%;">资料详情</h4>
						</div>
						<table class="table table-striped ">
							<tbody>
								<tr>

									<th class="col-md-2 column" style="text-align: center;">标题</th>
									<th style="text-align: center;width: 100%" class="col-md-12 column"><span><%=map.get("dataname") %></span></th>
								</tr>
							</tbody>
							<tbody>
								<tr>
									<th class="col-md-2" style="text-align: center;">作者</th>
									<th style="text-align: center;width: 100%" class="col-md-10"><span><%=map.get("dataauther") %></span></th>
								</tr>
							</tbody>

							<tbody>

								<tr>
									<th class="col-md-2" style="text-align: center;">内容</th>
									<td style="width: 100%" class="col-md-10"><%=map.get("datacontent") %></td>
								</tr>

							</tbody>

							<tbody>
								<tr>
									<th class="col-md-2 " style="text-align: center;">图片</th>
									<th class="col-md-10" style="text-align: center; "><img style="width: 100%"
										src="<%=request.getContextPath() + "\\upload\\"+map.get("dataimage")%>"></img></th>
								</tr>
							</tbody>
						</table>
					</div>
				</div>
			</div>
		</div>

</body>
</html>

