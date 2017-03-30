<%@ page language="java" contentType="text/html; charset=utf-8"
    pageEncoding="utf-8"%>
<%@ include file="../include/include.jsp"%> 
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8">
<title>register</title>
</head>
<body>
   <script type="text/javascript">
     $(function(){
    	 $("input[name='telephone']").val(1310000111);//test
    	 
    	 //tel 文本框 键盘 按键 弹起 事件
    	 $("input[name='telephone']").keyup(function(){
    		 var tel = $("input[name='telephone']").val();
    		 if(tel.length===11){
    			 //如果手机号长度为11 才能点 获取短信验证码
    			 $("#getSmsCodeBut").attr("disabled",false);
    		 }else{
    			 $("#getSmsCodeBut").attr("disabled",true);
    		 }
    	 });
    	 
    	 
    	 $("#getSmsCodeBut").click(function(){
    		 $.ajax({ 
    			 url: "${ctx}/reg/getSmsCode.do", 
    			 contentType: "application/json",
    			 data:{telephone:$("input[name='telephone']").val()}
    			 success: function(result){
    				 alert("注册成功");
    				 console.log(result);
    			 },
    			 error: function (errorMsg) {
    				 alert("注册失败");
    			 }
    		 }
    	 });
      })
   </script>
   <h1>register</h1>
   <form action="${ctx}/reg/register.do">
   		<span>	    手机号码：</span><input name="telephone" width='120'/></br></br>
   		<span>	短信验证码：</span><input name="code" width='50'>&nbsp;&nbsp;<input type="button" id="getSmsCodeBut" disabled="true" value="获取短信验证码"/></br></br>
   		<span>		   邮箱：</span><input name="email" width='120'/></br></br>
   		<span>		   密码：</span><input type="password" name="password" width='120'/></br></br>
   		<span>	   再次密码：</span><input type="password" name="password2" width='120'/></br></br>
   		&nbsp;&nbsp;&nbsp;&nbsp;<input	type="submit" value="注册">&nbsp;&nbsp;&nbsp;&nbsp;<input type="button" value="取消">
   </form>
</body>
</html>