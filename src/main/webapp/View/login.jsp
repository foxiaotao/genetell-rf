<%@ page language="java" contentType="text/html; charset=utf-8"
    pageEncoding="utf-8"%>
<%@ include file="./include/include.jsp"%> 
<%
	String telephone = request.getParameter("telephone");
	String password = request.getParameter("password");
%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8">
<title>gene欢迎你</title>
</head>
<body>
   <h1>login</h1>
   <form action="${ctx}/user/login.do">
   		用户名：<input type="text" name="username" width="100" value="<%=telephone%>"/></br></br>
   		密xx码：<input type="password" name="password" width="100" value="<%=password%>"/></br></br>
   		<input type="submit" value="登陆"/>&nbsp;&nbsp;&nbsp;<input type="button" value="注册"/>
   </form>
</body>
</html>