<%@ page language="java" contentType="text/html; charset=utf-8"
    pageEncoding="utf-8"%>
<%@ include file="./include/include.jsp"%> 
<%
	String telephone = request.getParameter("telephone");
	String password = request.getParameter("password");
	request.setAttribute(telephone,telephone);
%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8">
<title>login</title>
</head>
<body>
	<script>
// 		$(function(){
// 			var tel = "w";
<%-- 			var tel = <%=0telephone%>; --%>
// 			if(tel!=null && tel!= undefined && tel!=""){
// 				$("input[name='username']").val(tel);
// 			}
// 		})
	</script>
   <h1>login</h1>
   <form action="${ctx}/user/login.do">
   
   		用户名：<input type="text" name="username" width="100" placeholder="用户名/手机号码/邮箱"/></br></br>
   		密xx码：<input type="password" name="password" width="100" value="<%=password%>"/></br></br>
   		<input type="submit" value="登陆"/>&nbsp;&nbsp;&nbsp;<input type="button" value="注册"/>
   </form>
</body>
</html>