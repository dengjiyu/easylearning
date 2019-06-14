<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<%@ page import="top.easylearning.util.*"%>
<%
	String path = request.getContextPath();
	String dataname = (String) request.getAttribute("dataname");
	dataname = (dataname == null ? "" : dataname);

	List<Map<String, Object>> list = (List<Map<String, Object>>) request.getAttribute("listdata");

	//分页功能
	DividePage pUtil = (DividePage) request.getAttribute("pUtil");
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

<script type="text/javascript">
function search() {
	var th = document.form1;
	th.action="<%=path%>/servlet/DataAction?action_flag=list";
		th.submit();
	}

function first(){
	   var th = document.form1;
	   th.action="<%=path%>/servlet/DataAction?action_flag=list&pageNum=1";
	   th.submit();
	 }

	 function forward(){
		   var th = document.form1;
		   th.action="<%=path%>/servlet/DataAction?action_flag=list&pageNum=<%=pUtil.getCurrentPage() - 1%>";
		   th.submit();
		 }

	  function next(){
	   var th = document.form1;
	   th.action="<%=path%>/servlet/DataAction?action_flag=list&pageNum=<%=pUtil.getCurrentPage() + 1%>";
	   th.submit();
	 }
	 function end(){
	   var th = document.form1;
	   th.action="<%=path%>/servlet/DataAction?action_flag=list&pageNum=<%=pUtil.getPageCount()%>";
	   th.submit();
	 }
	 function changePage(currentPage){
		  var th = document.form1;
		   th.action="<%=path%>/servlet/DataAction?action_flag=list&pageNum="
				+ currentPage;
		th.submit();
	}

		
	/*************复选框批量删除****************/
	//得到当前页面的name=ids，的元素进行遍历，达到全选和全不选的操作
	function selectAll(flag) {
		var ids = document.getElementsByName("ids");
		for (var i = 0; i < ids.length; i++) {
			ids[i].checked = flag;

		}
	}
	//查看复选框所选的个数
	function getSelectCount() {
		var count = 0;
		var ids = document.getElementsByName("ids");
		for (var i = 0; i < ids.length; i++) {
			if (ids[i].checked) {
				count++;
			}
		}
		return count;
	}
	 function del(){
		   var th = document.form1;
		   if(getSelectCount()<1){
		     alert("至少要选中一个选项!!");
		     return ;
		   }
		   th.action="<%=path%>/servlet/DataAction?action_flag=del";
		th.submit();
	}
	/*************复选框批量删除****************/

	/*************复选框查看操作****************/
	//得到复选框具体选的是哪一个
	function getSelectValue() {
		var ids = document.getElementsByName("ids");
		for (var i = 0; i < ids.length; i++) {
			if (ids[i].checked) {
				return ids[i].value;
			}
		}
	}
	function view() {
		var th = document.form1;
		if(getSelectCount()<1){
			alert("至少要选中一个选项!!");
		     return ;
			}
		if(getSelectCount()>1){
			alert("仅仅只能选中一个选项进行查询!!");
		     return ;
			}

		th.action = "<%=path%>/servlet/DataAction?action_flag=view&id="
				+ getSelectValue();
		th.submit();
	}
	/*************复选框查看操作****************/
</script>



</head>
<body>
	<form name="form1" method="post">
		<div class="container" style="margin-top: 30px">
			<div class="row clearfix">
				<div class="col-md-12 column">
					<div class="panel panel-info">

						<div class="panel-heading form-inline">
							<input class="form-control" type="text" name="dataname"
								value="<%=dataname%>" placeholder="按文章标题查询" /> <a
								class="btn btn-success" style="margin-left: 10px"
								href="javascript:search();"><span
								class="glyphicon glyphicon-search" aria-hidden="true"></span></a>

							<div class="form-group pull-right">
								<a class="btn btn-danger" style="margin-left: 10px"
									href="javascript:del();">删除</a>
							</div>
							<div class="form-group pull-right">
								<a class="btn btn-primary" href="javascript:view();">查看</a>
							</div>

						</div>



						<!-- http://v3.bootcss.com/css/#tables 条纹状表框 -->
						<table class="table table-striped">

							<thead>

								<tr>
									<th><input type="checkbox" name="checkall"
										class="col-md-2 col-md-offset-5 column"
										style="width: 20px; height: 15px; margin-left: 100%; text-align: center;"
										value="" onclick="javascript:selectAll(this.checked);"></th>
									<th class="col-md-4 col-md-offset-4 column"
										style="font-size: 15px; text-align: center;">标题</th>
									<th class="col-md-2 col-md-offset-5 column"
										style="font-size: 15px; text-align: center;">作者</th>
									<th class="col-md-6 col-md-offset-3 column"
										style="font-size: 15px; text-align: center;">内容</th>
								</tr>

							</thead>



							<tbody>
								<%
									if (!list.isEmpty()) {
										for (Map<String, Object> map : list) {
								%>
								<tr>
									<td><input name="ids" type="checkbox" class="text2"
										style="width: 20px; height: 15px; margin-left: 100%"
										value="<%=map.get("id")%>"></td>
									<td style="text-align: center;"><%=map.get("dataname")%></td>
									<td style="text-align: center;"><%=map.get("dataauther")%></td>
									<td><%=map.get("datacontent")%></td>
								</tr>
								<%
									}
									}
								%>
							</tbody>

						</table>





						<span class="label label-info" style="margin-left: 90%;">共<%=pUtil.getPageCount()%>页
						</span>




						<div class="col-md-4 col-md-offset-4 column"
							style="position: fixed; bottom: 0; margin-left: 24%">
							<ul class="pager ">
								<li class="previous"><a href="javascript:first();">&larr;
										首页</a></li>

								<li><a href="javascript:forward();">上一页</a></li>
								<li><a href="javascript:next();">下一页</a></li>
								<li class="next"><a href="javascript:end();">末页 &rarr;</a></li>

								<select name="select" class="btn btn-default"
									style="WIDTH: 40px" onchange="changePage(this.value)">
									<%
										int len = pUtil.getPageCount();
										for (int i = 1; i <= len; i++) {
									%>
									<option value="<%=i%>"
										<%=(i == pUtil.getCurrentPage() ? "selected" : "")%>>
										<%=i%>
									</option>
									<%
										}
									%>
								</select>

							</ul>
						</div>

					</div>
				</div>
			</div>
		</div>
	</form>
</body>
</html>
