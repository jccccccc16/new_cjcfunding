package com.cjc.funding.service.impl;

import com.cjc.funding.entity.Admin;
import com.cjc.funding.entity.AdminExample;
import com.cjc.funding.entity.Role;
import com.cjc.funding.mapper.AdminMapper;
import com.cjc.funding.mapper.RoleMapper;
import com.cjc.funding.mvc.conifg.SecurityAdmin;
import com.cjc.funding.util.constant.CrowConstant;
import com.cjc.funding.util.exception.LoginFailedException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.AuthorityUtils;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by IntelliJ IDEA.
 * User: cjc
 * Date: 2020/10/18
 * Time: 10:22
 * To change this template use File | Settings | File Templates.
 **/
@Component
public class MyUserDetailService implements UserDetailsService {

    @Autowired
    private AdminMapper adminMapper;

    @Autowired
    private RoleMapper roleMapper;

    private Logger logger = LoggerFactory.getLogger(MyUserDetailService.class);


    @Override
    public UserDetails loadUserByUsername(String loginAcct) throws UsernameNotFoundException {

        logger.info("loginAcct: "+loginAcct);

        // 1.根据loginAcct查找用户
        AdminExample adminExample = new AdminExample();
        AdminExample.Criteria criteria = adminExample.createCriteria();
        criteria.andLoginAcctEqualTo(loginAcct);
        List<Admin> adminList = adminMapper.selectByExample(adminExample);

        // 2.判断是否查询结果集是否为空
        if(adminList==null || adminList.size()==0){
            throw new LoginFailedException(CrowConstant.MESSAGE_LOGIN_FAILED);
        }

        // 3.获取管理员对象
        Admin admin = adminList.get(0);

        // 4.获取该管理员对应的角色
        // 4.1 查找角色
        List<Role> roleList = roleMapper.selectAssignRole(admin.getId());

         // 4.2 便利角色列表，封装到
        List<GrantedAuthority> authorityList =new ArrayList<>();

        if(roleList!=null || roleList.size()!=0){
            for (Role role : roleList) {
                String roleName = role.getName();
                GrantedAuthority grantedAuthority = new SimpleGrantedAuthority("ROLE_"+roleName);
                authorityList.add(grantedAuthority);
            }
        }

        // 5.封装到userDetails中
        SecurityAdmin securityAdmin = new SecurityAdmin(admin, authorityList);

        return securityAdmin;
    }
}
