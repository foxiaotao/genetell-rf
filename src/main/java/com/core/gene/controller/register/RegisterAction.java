package com.core.gene.controller.register;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.util.Assert;
import org.springframework.web.bind.annotation.RequestMapping;

import com.core.gene.bean.User;
import com.core.gene.bean.msg.ReturnBean;
import com.core.gene.service.UserService;
import com.core.gene.session.SessionProvider;
import com.core.gene.util.StringUtil;
import com.core.gene.util.alidayu.SMSUtil;
import com.taobao.api.ApiException;
import com.core.gene.session.HttpSessionProvider;

@Controller
@RequestMapping(value="/reg")
public class RegisterAction {
	
	@Autowired
    UserService userServiceImpl;
	@Autowired
	SessionProvider sessionProvider;                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                           
 /*
  * 验证码
  */
 @RequestMapping(value="/getSmsCode.do")
 public void getSmsCode(HttpServletRequest request, HttpServletResponse response, User record) throws ApiException{
	 String receNum=request.getParameter(record.getTelphone());
	 
	 Assert.notNull(record,"注册信息不能为空");
	 
	 Assert.notNull(record.getTelphone(),"注册手机号码不能为空");
	 String code = StringUtil.randomNumber(6);
	 
	 System.out.println("code******************"+code);
	 
	 SMSUtil.sentSmsCode("武汉白原科技", code, receNum, "SMS_58170004");                           
	 sessionProvider.setAttribute(request, response, receNum, code);
 }
	 /*
	  * 注册
	  * 提交注册时进入这个方法
	 */

	@RequestMapping(value="/register.do")
	public ResponseEntity register(HttpServletRequest request, HttpServletResponse response,User record,String code)throws Exception{
		   Assert.notNull(record,"注册信息不能为空");
		   Assert.notNull(record.getUsername(),"用户名不能为空");
		   Assert.notNull(record.getPassword(),"用户密码不能为空");
		   Assert.notNull(record.getTelphone(),"注册手机号码不能为空");
		  Assert.notNull(code,"验证码不能为空");
		   String sessionCode = (String) sessionProvider.getAttribute(request, response, "15071473394");
		   if(code.equals(sessionCode)){
			   userServiceImpl.insert(record);
			   return new ResponseEntity(new ReturnBean<User>("register success", true),HttpStatus.OK);
		   }else{
			   return new ResponseEntity(new ReturnBean<User>("yanzhengma error", false),HttpStatus.OK);
		   }
		   
	  }

	}