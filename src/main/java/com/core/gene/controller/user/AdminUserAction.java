package com.core.gene.controller.user;

import com.core.gene.annotation.Authority;
import com.core.gene.bean.AdminUser;
import com.core.gene.bean.User;
import com.core.gene.service.AdminUserService;

import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
@RequestMapping(value="/AdminUser")
public class AdminUserAction {
    @Autowired
    AdminUserService adminUserServiceImpl;

    @RequestMapping(value="/delete.do")
    public void delete(Long id) throws Exception {
        adminUserServiceImpl.deleteByPrimaryKey(id);
    }

    @RequestMapping(value="/insert.do")
    public void insert(AdminUser record) throws Exception {
        adminUserServiceImpl.insert(record);
    }

    @RequestMapping(value="/findByPage.do")
    @ResponseBody
    public Map<String,Object> findByPage(AdminUser record, int rows, int page) throws Exception {
        int startPage=rows*(page-1)+1;
        int endPage=rows*page;
        return adminUserServiceImpl.findByPage(record,startPage,endPage);
    }

    @RequestMapping(value="/update.do")
    @Authority(auth = true)
    public void update(AdminUser record) throws Exception {
        adminUserServiceImpl.updateByPrimaryKeySelective(record);
    }
    
    @RequestMapping(value="/register.do")
    public void register(AdminUser record)throws Exception{
    	adminUserServiceImpl.insert(record);
    };
}