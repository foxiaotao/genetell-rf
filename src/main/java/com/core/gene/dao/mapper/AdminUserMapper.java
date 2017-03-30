package com.core.gene.dao.mapper;

import com.core.gene.bean.AdminUser;
import com.core.gene.bean.AdminUserExample;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface AdminUserMapper {
    int countByExample(AdminUserExample example);

    int deleteByExample(AdminUserExample example);

    int deleteByPrimaryKey(Long id);

    int insert(AdminUser record);

    int insertSelective(AdminUser record);

    List<AdminUser> selectAll();

    List<AdminUser> selectByExample(AdminUserExample example);

    List<AdminUser> selectByExampleWithPage(@Param("example") AdminUserExample example, @Param("startPage") int startPage, @Param("endPage") int endPage);
    List<AdminUser> getAdminUserByUsername(String username);

    AdminUser selectByPrimaryKey(Long id);

    int updateByExampleSelective(@Param("record") AdminUser record, @Param("example") AdminUserExample example);

    int updateByExample(@Param("record") AdminUser record, @Param("example") AdminUserExample example);

    int updateByPrimaryKeySelective(AdminUser record);

    int updateByPrimaryKey(AdminUser record);
}