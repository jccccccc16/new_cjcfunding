package com.cjc.funding.service.impl;



import com.cjc.funding.entity.Role;
import com.cjc.funding.entity.RoleExample;
import com.cjc.funding.mapper.RoleMapper;
import com.cjc.funding.service.api.RoleService;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.List;

/**
 * Created by IntelliJ IDEA.
 * User: cjc
 * Date: 2020/9/27
 * Time: 20:44
 * To change this template use File | Settings | File Templates.
 **/
@Service
public class RoleServiceImpl  implements RoleService {

    @Autowired
    private RoleMapper roleMapper;

   private Logger logger = LoggerFactory.getLogger(RoleServiceImpl.class);

    public List<Role> getRoleByKeyword(String keyword) {

        return null;
    }

    @Override
    public PageInfo<Role> getPageInfo(String keyword, Integer pageNum, Integer pageSize) {
        // 开始分页
        PageHelper.startPage(pageNum,pageSize);

        // 根据keyword查询
        List<Role> roles = roleMapper.selectRoleByKeyword(keyword);

        // 日志输出
        if(roles==null||roles.size()==0){
            logger.warn("roles 为空");
        }else{
            logger.info("roles: "+roles.toString());
        }

        // 封装roles
        return new PageInfo<Role>(roles);
    }

    @Override
    public void save(Role role) {
        roleMapper.insert(role);
    }

    /**
     * 根据id删除角色
     * @param roleIdList
     */
    @Override
    public void deleteRole(List<Integer> roleIdList) {
        RoleExample roleExample = new RoleExample();
        RoleExample.Criteria criteria = roleExample.createCriteria();
        criteria.andIdIn(roleIdList);
        roleMapper.deleteByExample(roleExample);
    }

    @Override
    public List<Role> getAssignedRoles(Integer adminId) {

        return roleMapper.selectAssignRole(adminId);
    }

    @Override
    public List<Role> getUnAssignedRoles(Integer adminId) {

        return roleMapper.selectUnAssignRole(adminId);
    }
}
