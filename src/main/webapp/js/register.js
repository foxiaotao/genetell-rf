
function getFocus()  //设置用户名文本框获取焦点
{
    document.getElementById("txtuname").focus();
}
 
function checkname()  //检查用户名
{
    var myname=document.getElementById("username").value;
    var myDivname=document.getElementById("uname");
    var regName=/^[a-z\d_\u4e00-\u9fa5]{6,16}/i;
    if(regName.test(myname)){
			myDivname.innerHTML="<font color='green'>用户名合法</font>";
			return true;
            }else{
                 myDivname.innerHTML="<font color='red'>用户名必须是长度6-12 并且只能够是字符</font> ";
                 return false;
            }
    if(myname=="")
    {
        myDivname.innerHTML="<font color='red'>用户名不能为空!</font>";
        return false;
    }
    for(var i=0;i<myname.length;i++)
    {
        var text=myname.charAt(i);
        if(!(text<=9&&text>=0)&&!(text>='a'&&text<='z')&&!(text>='A'&&text<='Z')&&text!="_"&&!(text.length<6))
        {
         myDivname.innerHTML="<font color='red'>用户名只能是数字、字母、下划线组成！长度必须是6-16 </font>";
         break;
        }
        
    }
    // if(myname.length>=6)
    // {
        // myDivname.innerHTML="<font color='green'>用户名合法</font>";
        // return true;
    // }else if(myname.length<5){
    	// myDivname.innerHTML="<font color='red'>用户名长度必须是6-16位</font>";
        // return false;
    // }
}
 
function checkuserpassword()  //检查密码 
{
		 var mypassword=document.getElementById("password").value;
		 var myDivpassword=document.getElementById("upassword");
		
		 if(mypassword=="")
		 {
		  myDivpassword.innerHTML="<font color='red'>密码不能为空!</font>";
		  return false;
		 }
		 else if(mypassword.length<6)
		 {
		  myDivpassword.innerHTML="<font color='red'>密码至少应为六位!</font>";
		  return false;
		 }
		 else{
		 	myDivpassword.innerHTML="<font color='green'>密码合法</font>"
		 }
		 
}
 
function checkpwdagin()  //检查确认密码
{
	 var myispassword=document.getElementById("txtpwdagin").value;
	 var myDivispassword=document.getElementById("pwdagin");
	 if(myispassword=="")
	 {
	     myDivispassword.innerHTML="<font color='red'>确认密码不能为空!</font>";
	     return false;
	 }
	 else if(document.getElementById("password").value!=document.getElementById("txtpwdagin").value)
	 {
	  myDivispassword.innerHTML="<font color='red'>确认密码与密码不一致!</font>";
	  return false;
	 } 
	 else
	 {
	  myDivispassword.innerHTML="<font color='green'>两次输入密码一致</font>";
	  return true;
	 } 
}
 
function checktelephone()  //检查电话号码
{
	 var mytelephone=document.getElementById("txttelephone").value;
	 var myDivtelephone=document.getElementById("telephone");
	 if(mytelephone!="")
	 {
	     var reg = /^[0-9]{11}$/i;
	     if(!reg.test(mytelephone))
	        {
	            myDivtelephone.innerHTML="<font color='red'>只能输入11位数字！例：13595144582或08514785214</font>";
	            return false;
	        }
	     else
	     {
	      myDivtelephone.innerHTML="<font color='green'>√</font>";
	      return true;
	     }
	 }
	
}