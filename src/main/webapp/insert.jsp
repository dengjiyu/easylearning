<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
 <html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Insert title here</title>
</head>
<body>
<form action="${pageContext.request.contextPath }/user/insert" method="post" id="myform">
    	<table align="center"> 
    		<tr>
    			<td><p>指定ID: &nbsp;<input type="text" id="id" name="id" style="width: 150px" /></p></td>
    			
    		</tr>       
    		<tr>
    			<td><p>用户账户：<input type="text" id="username" name="username" style="width: 150px" /></p></td>
    			
    		</tr>
    		<tr>
    			<td><p>用户密码：<input type="password" id="password" name="password" style="width: 150px"/></p></td>
    		</tr>
    		
    	
    		<tr>
    			<td><input type="submit" value="注册" id="login"/></td>
    			
    		</tr>
    	</table> 	
    </form>
</body>
</html>