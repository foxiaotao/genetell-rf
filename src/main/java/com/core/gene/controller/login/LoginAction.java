package com.core.gene.controller.login;

import java.io.Serializable;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.beanutils.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.util.Assert;
import org.springframework.web.bind.annotation.RequestMapping;

import com.core.gene.bean.AdminUser;
import com.core.gene.bean.SessionsUser;
import com.core.gene.bean.User;
import com.core.gene.bean.msg.ReturnBean;
import com.core.gene.service.AdminUserService;
import com.core.gene.service.UserService;
import com.core.gene.session.SessionProvider;

@Controller
@RequestMapping(value="/")
public class LoginAction {
    @Autowired
    UserService userServiceImpl;
    @Autowired
    AdminUserService adminUserServiceImpl;
    @Autowired
	SessionProvider sessionProvider;
    
    
    @RequestMapping(value="/user/login.do")
    public ResponseEntity userLogin(HttpServletRequest request, HttpServletResponse response,User record) throws Exception {
    	Assert.notNull(record,"user 不能为null");//user 不能为null
    	//密码不能为空
    	Assert.notNull(record.getPassword(),"Password 不能为null");//Password 不能为null
    	//用户名不能为空username
    	Assert.notNull(record.getUsername(),"Username 不能为null");//Username 不能为null
    	// 是否需要 密码在数据库 密文报错（insert时候将密码md5加密  登陆时将页面密码加密 在和数据库比较）
    	//根据用户名去查询出这个user，在对user进行判断,如果user为空，大于一，没有时各是什么情况
    	//只有一个时，那就根据密码判断（看查询出来的用户密码是不是和登陆的密码一致）
    	List<User> loginUser = userServiceImpl.getUserByUsername(record.getUsername());
    	
    	//这里不将username 和password 一同传入 xml  查询是否有  usernmae= 123 and password = 11，而是通过用户名把user查出来，先判断这个username 有多少个 ，正常 只有一个
    	if(loginUser==null){
    		return new ResponseEntity(new ReturnBean("用户不存在",false), HttpStatus.OK);
    	}else if(loginUser.size()>1){
    		//有对个usernmae对应 
    		//返回到error界面，或者页面提示报错
    		return new ResponseEntity(new ReturnBean("用户不唯一",false), HttpStatus.OK);
    	}else if(loginUser.size() == 0){
    		return new ResponseEntity(new ReturnBean("用户不存在",false), HttpStatus.OK);
    	}else{
    		//只有1 个，登陆成功保存这个用户
    		if(record.getPassword().equals(loginUser.get(0).getPassword())){
    			//session
    			SessionsUser users = new SessionsUser();
    			BeanUtils.copyProperties(users, loginUser.get(0));
    			sessionProvider.setAttribute(request, response, "SESSIONSUSER",(Serializable) users);
    			//登陆成功
    			return new ResponseEntity(new ReturnBean("登陆成功",true), HttpStatus.OK);
    		}else{
    			//登陆失败
    			return new ResponseEntity(new ReturnBean("用户名密码错误",false), HttpStatus.OK);
    		}
    	}
    } 
    
    
    //管理员登陆 
    
    @RequestMapping(value="/admin/login.do")
    public ResponseEntity adminLogin(HttpServletRequest request,HttpServletResponse response,AdminUser record) throws Exception {
    	Assert.notNull(record,"admin 不能为空");
    	Assert.notNull(record.getUsername(), "Username 不能为空");
    	Assert.notNull(record.getPassword(), "Password 不能为空");
    	List<AdminUser> loginAdminUser=adminUserServiceImpl.getAdminUserByUsername(record.getUsername());
    	if(loginAdminUser==null){
    		return  new ResponseEntity(new ReturnBean("用户不存在",false),HttpStatus.OK);
    	}else if(loginAdminUser.size()>1){
    		return new ResponseEntity(new ReturnBean("用户不唯一",false),HttpStatus.OK);
    	}else if(loginAdminUser.size() == 0){
    		return new ResponseEntity(new ReturnBean("用户不存在",false),HttpStatus.OK);
    	}else{
    		if(record.getPassword().equals(loginAdminUser.get(0).getPassword())){
    			SessionsUser users=new SessionsUser();
    			BeanUtils.copyProperties(users, loginAdminUser.get(0));
    			sessionProvider.setAttribute(request, response, "SESSIONSUSER", users);
    			return new ResponseEntity(new ReturnBean("登陆成功",true),HttpStatus.OK);
    		}else{
    			return new ResponseEntity(new ReturnBean("用户名密码错误",false),HttpStatus.OK);
    		}
    	}
    }
}