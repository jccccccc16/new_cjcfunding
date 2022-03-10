package com.cjc.funding.mapper;

import com.cjc.funding.entity.AdminExample;
import com.cjc.funding.entity.Role;
import com.cjc.funding.entity.RoleExample;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * 管理角色
 */
public interface RoleMapper {

    List<Role> selectRoleByKeyword(String keyword);

    int countByExample(RoleExample example);

    int deleteByExample(RoleExample example);

    int deleteByPrimaryKey(Integer id);

    int insert(Role record);

    int insertSelective(Role record);

    List<Role> selectByExample(AdminExample example);

    Role selectByPrimaryKey(Integer id);

    int updateByExampleSelective(@Param("record") Role record, @Param("example") RoleExample example);

    int updateByExample(@Param("record") Role record, @Param("example") RoleExample example);

    int updateByPrimaryKeySelective(Role record);

    int updateByPrimaryKey(Role record);

    List<Role> selectAssignRole(Integer adminId);

    List<Role> selectUnAssignRole(Integer adminId);
}