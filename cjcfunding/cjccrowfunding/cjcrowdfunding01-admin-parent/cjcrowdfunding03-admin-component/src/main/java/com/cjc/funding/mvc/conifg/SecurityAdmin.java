package com.cjc.funding.mvc.conifg;

import com.cjc.funding.entity.Admin;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.User;

import java.util.Collection;

/**
 * Created by IntelliJ IDEA.
 * User: cjc
 * Date: 2020/10/18
 * Time: 16:32
 * To change this template use File | Settings | File Templates.
 *
 * 扩张spring security 的usr 使到保留一个admin的全部信息
 **/
public class SecurityAdmin extends User {

    private Admin originalAdmin;


    public SecurityAdmin(Admin admin,Collection<? extends GrantedAuthority> authorities) {

        super(admin.getUserName(), admin.getUserPswd(),authorities);
        this.originalAdmin = admin;


    }

    public Admin getOriginalAdmin() {
        return originalAdmin;
    }

    public void setOriginalAdmin(Admin originalAdmin) {
        this.originalAdmin = originalAdmin;
    }
}
