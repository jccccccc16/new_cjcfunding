package com.cjc.funding.service.api;

import com.cjc.funding.entity.Admin;
import com.cjc.funding.entity.Role;
import com.github.pagehelper.PageInfo;

import java.util.List;

/**
 * Created by IntelliJ IDEA.
 * User: cjc
 * Date: 2020/9/27
 * Time: 20:22
 * To change this template use File | Settings | File Templates.
 * 角色服务
 **/
public interface RoleService {



    List<Role> getRoleByKeyword(String keyword);

    public PageInfo<Role> getPageInfo(String keyword, Integer pageNum, Integer pageSize);


    void save(Role role);

    /**
     * 根据id删除角色
     * @param
     */
    void deleteRole(List<Integer> roleIdList);


    /**
     * 查找该管理员拥有的角色
     * @param adminId
     * @return
     */
    List<Role> getAssignedRoles(Integer adminId);

    /**
     * 查找该管理员没有的角色
     * @param adminId
     * @return
     */
    List<Role> getUnAssignedRoles(Integer adminId);
}
