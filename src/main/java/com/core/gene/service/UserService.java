package com.core.gene.service;

import com.core.gene.bean.User;

import java.util.List;
import java.util.Map;

public interface UserService {
    int deleteByPrimaryKey(Long id) throws Exception;

    int insert(User record) throws Exception;

    int updateByPrimaryKeySelective(User record) throws Exception;

	/** 按需求 一个username 只能有一个用户，但是为了程序保证有多个相同username的时候程序不报错。
	 * @param username
	 * @return
	 */
	List<User> getUserByUsername(String username);

	int countByEntity(User record);
	List<User> selectPageByEntity(User record, int startPage, int pagesize);
}