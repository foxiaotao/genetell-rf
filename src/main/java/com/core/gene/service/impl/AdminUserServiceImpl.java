package com.core.gene.service.impl;

import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.core.gene.bean.AdminUser;
import com.core.gene.bean.AdminUserExample;
import com.core.gene.bean.AdminUserExample.Criteria;
import com.core.gene.dao.mapper.AdminUserMapper;
import com.core.gene.service.AdminUserService;

@Service
public class AdminUserServiceImpl implements AdminUserService {
    @Autowired
    AdminUserMapper adminUserMapper;

    public int deleteByPrimaryKey(Long id) throws Exception {
        return adminUserMapper.deleteByPrimaryKey(id);
    }

    public int insert(AdminUser record) throws Exception {
        return adminUserMapper.insert(record);
    }
    public List<AdminUser> getAdminUserByUsername(String username) {
		return adminUserMapper.getAdminUserByUsername(username);
	}
  
    public Map<String,Object> findByPage(AdminUser record, int startPage, int endPage) throws Exception {
        Map<String,Object> map=new LinkedHashMap<String,Object>();
        AdminUserExample example=new AdminUserExample();
        Criteria criteria = example.createCriteria();
        //����������ѯ�����д��?����
        map.put("total", adminUserMapper.selectByExampleWithPage(example,startPage,endPage));
        map.put("rows", adminUserMapper.countByExample(example));
        return map;
    }

    public int updateByPrimaryKeySelective(AdminUser record) throws Exception {
        return adminUserMapper.updateByPrimaryKeySelective(record);
    }

	
	
}