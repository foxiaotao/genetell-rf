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
    	 //tel 文本框 键盘 按键 弹起 事件
    	 $("input[name='telephone']").keyup(function(){
    		 var tel = $("input[name='telephone']").val();
    		 if(tel.length>=11){
    			 //如果手机号长度为11 才能点 获取短信验证码
    			 $("#getSmsCodeBut").attr("disabled",false);
    		 }else{
    			 $("#getSmsCodeBut").attr("disabled",true);
    		 }
    	 });
    	 
    	 //sms code
    	 $("#getSmsCodeBut").click(function(){
    		 $.ajax({ 
    			 url: "${ctx}/reg/getSmsCode.do", 
    			 contentType: "application/json",
    			 data:{telephone:$("input[name='telephone']").val()},
    			 success: function(result){
    				 if(result.success){
    				 	alert("验证码已发送");
    				 }else{
    					 alert("验证码未发送，"+result.msg);
    				 }
    			 },
    			 error: function (errorMsg) {
    				 alert("验证码发送失败");
    			 }
    		 });
    	 });
    	 
    	 //提交
    	 $("#registerBut").click(function(){
    		 //验证所有不能 空
    		 
    		 if($("input[name='password']").val() != $("input[name='password2']").val()){
    			 alert("两次密码输入不一样");
    			 return ;
    		 }
    		 $("#regForm").submit();
    	 });
      })
   </script>
   <h1>register</h1>
   <form action="${ctx}/reg/register.do" id="regForm">
   		<span>	    手机号码：</span><input name="telephone" width='120' value="17600299715"/></br></br>
   		<span>	短信验证码：</span><input name="code" width='50'>&nbsp;&nbsp;<input type="button" id="getSmsCodeBut" disabled="true" value="获取短信验证码"/></br></br>
   		<span>		用户名：</span><input name="username" width='120'/></br></br>
   		<span>		   邮箱：</span><input name="email" width='120'/></br></br>
   		<span>		   密码：</span><input type="password" name="password" width='120'/></br></br>
   		<span>	   再次密码：</span><input type="password" name="password2" width='120'/></br></br>
   		&nbsp;&nbsp;&nbsp;&nbsp;<input	type="button" id="registerBut" value="注册">&nbsp;&nbsp;&nbsp;&nbsp;<input type="button" value="取消">
   </form>
</body>
</html>