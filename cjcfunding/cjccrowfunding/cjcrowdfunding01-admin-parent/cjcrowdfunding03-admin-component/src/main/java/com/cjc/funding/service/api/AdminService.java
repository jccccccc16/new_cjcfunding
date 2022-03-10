package com.cjc.funding.service.api;


import com.cjc.funding.entity.Admin;
import com.github.pagehelper.PageInfo;

import java.util.List;

/**
 *
 */
public interface AdminService {

    /**
     * 插入一个管理员
     * @param admin
     */
    void save(Admin admin);

    /**
     * 查找全部admin
     * @return
     */
    List<Admin> getAll();

    Admin getById(Integer id);

    Admin getAdminByAccount(String loginAcct, String userPswd);

    /**
     * 用于ajax，loginAcct是否存在
     * @param loginAcct
     * @return
     */
    boolean getAdminByLoginAcct(String loginAcct);

    /**
     * 用于查询admin分页
     * @param keyword
     * @return
     */
    PageInfo<Admin> getPageInfo(String keyword,Integer pageNum,Integer pageSize);

    /**
     * 根据id删除
     * @param id
     */
    void deleteById(Integer id);

    void edit(Admin admin);

    /**
     * 给管理员添加角色
     * @param adminId
     * @param roleIdList
     */
    void saveAdminRoleRelationship(Integer adminId, List<Integer> roleIdList);
}
