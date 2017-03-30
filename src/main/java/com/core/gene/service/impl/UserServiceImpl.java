package com.core.gene.service.impl;

import com.core.gene.bean.User;
import com.core.gene.bean.UserExample.Criteria;
import com.core.gene.bean.UserExample;
import com.core.gene.dao.mapper.UserMapper;
import com.core.gene.service.UserService;

import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UserServiceImpl implements UserService {
    @Autowired
    UserMapper userMapper;

    public int deleteByPrimaryKey(Long id) throws Exception {
        return userMapper.deleteByPrimaryKey(id);
    }

    public int insert(User record) throws Exception {
        return userMapper.insert(record);
    }


    public int updateByPrimaryKeySelective(User record) throws Exception {  
        return userMapper.updateByPrimaryKeySelective(record);
    }

    /** 按需求 一个username 只能有一个用户，但是为了程序保证有多个相同username的时候程序不报错。
	 * @param username
	 * @return
	 */
	public List<User> getUserByUsername(String username) {
		return userMapper.getUserByUsername(username);
	}
    /** 条件查询  手动
     * author:ranfen
     * date:2017年3月27日 下午2:30:49
     * @param record
     * @param startPage
     * @param endPage
     * @return
     */
    public List<User> selectPageByEntity(User record, int startPage, int pageSize){
	    return userMapper.selectPageByUser(record,startPage,pageSize);
	}
	public int countByEntity(User record) {
		return userMapper.countByEntity(record);
	}
}