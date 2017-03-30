package com.core.gene.springTest;

import java.util.List;
import java.util.Map;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.http.ResponseEntity;

import com.core.gene.bean.User;
import com.core.gene.controller.user.UserAction;
import com.core.gene.service.UserService;


public class UserTest extends JunitSpringTest{

	@Autowired
    UserService userServiceImpl;
	
	
	@Test
	public void indextest(){
		//update	-2 我要提交了
 		List<User> list = userServiceImpl.selectPageByEntity(new User(), 0, 10);
		System.out.println(list.size());
	}
	

	
}
