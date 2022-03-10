package com.cjc.funding.mapper;

import com.cjc.funding.entity.Admin;
import com.cjc.funding.entity.AdminExample;
import java.util.List;
import org.apache.ibatis.annotations.Param;


public interface AdminMapper {
    int countByExample(AdminExample example);

    int deleteByExample(AdminExample example);

    int deleteByPrimaryKey(Integer id);

    int insert(Admin record);

    int insertSelective(Admin record);

    List<Admin> selectByExample(AdminExample example);

    Admin selectByPrimaryKey(Integer id);

    int updateByExampleSelective(@Param("record") Admin record, @Param("example") AdminExample example);

    int updateByExample(@Param("record") Admin record, @Param("example") AdminExample example);

    int updateByPrimaryKeySelective(Admin record);

    int updateByPrimaryKey(Admin record);

    List<Admin> selectAdminByKeyWord(String keyword);

    /**
     * 删除旧的角色关联
     * @param adminId
     */
    void deleteOldRelationship(Integer adminId);

    /**
     * 插入新的角色关联
     * @param adminId
     * @param roleIdList
     */
    void insertNewRoleRelationship(@Param("adminId") Integer adminId, @Param("roleIdList") List<Integer> roleIdList);
}