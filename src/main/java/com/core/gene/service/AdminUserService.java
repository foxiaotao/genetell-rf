package com.core.gene.service;

import com.core.gene.bean.AdminUser;
import com.core.gene.bean.User;

import java.util.List;
import java.util.Map;

public interface AdminUserService {
    int deleteByPrimaryKey(Long id) throws Exception;

    int insert(AdminUser record) throws Exception;

    
     List<AdminUser> getAdminUserByUsername(String username);

    Map<String,Object> findByPage(AdminUser record, int startPage, int endPage) throws Exception;

    int updateByPrimaryKeySelective(AdminUser record) throws Exception;
}