package com.core.gene.dao.mapper;

import com.core.gene.bean.User;
import com.core.gene.bean.UserExample;

import java.util.List;

import org.apache.ibatis.annotations.Param;

public interface UserMapper {

    int deleteByPrimaryKey(Long id);

    int insert(User record);

    int insertSelective(User record);

    List<User> selectAll();

    User selectByPrimaryKey(Long id);

    int updateByPrimaryKeySelective(User record);

    int updateByPrimaryKey(User record);

    //一个参数（siring，int）等类型时  可以不用加@Param("record") ，多个string，或者对象的时候在xml中取值出现多个匹配或者模糊，就需要加以区分
	List<User> getUserByUsername(String username);

	List<User> selectPageByUser(@Param("record") User record, @Param("startPage") int startPage, @Param("pageSize") int pageSize);

	int countByEntity(User record);
}