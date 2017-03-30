package com.core.gene.controller.user;

import com.core.gene.annotation.Authority;
import com.core.gene.bean.User;
import com.core.gene.bean.msg.ReturnBean;
import com.core.gene.service.UserService;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
@RequestMapping(value="/User")
public class UserAction {
    @Autowired
    UserService userServiceImpl;

    @RequestMapping(value="/delete.do")
    public void delete(Long id) throws Exception {
        userServiceImpl.deleteByPrimaryKey(id);
    }

    @RequestMapping(value="/insert.do")
    public void insert(User record) throws Exception {
        userServiceImpl.insert(record);
    }

    @RequestMapping(value="/findByPage.do")
    public ResponseEntity findByPage(User record, int pageSize, int page) throws Exception {
        int startPage=pageSize*(page-1);
        
        int total = userServiceImpl.countByEntity(record);
        List<User> list = userServiceImpl.selectPageByEntity(record,startPage,pageSize);
        System.out.println(total);
        System.out.println(list.size());
        return new ResponseEntity(new ReturnBean<User>("查询成功",true,list,total), HttpStatus.OK);
    }

    @RequestMapping(value="/update.do")
    public void update(User record) throws Exception {
        userServiceImpl.updateByPrimaryKeySelective(record);
    }
    
    @RequestMapping(value="/register.do")
    public void register(User record)throws Exception{
    	userServiceImpl.insert(record);
    };
}