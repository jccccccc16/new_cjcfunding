package com.cjc.funding.mvc.controller;

import com.cjc.funding.entity.Role;
import com.cjc.funding.service.api.AdminService;
import com.cjc.funding.service.api.AuthService;
import com.cjc.funding.service.api.RoleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

/**
 * Created by IntelliJ IDEA.
 * User: cjc
 * Date: 2020/10/14
 * Time: 16:03
 * To change this template use File | Settings | File Templates.
 * 权限相关
 **/

@Controller
public class AssignController {

    @Autowired
    private AuthService authService;

    @Autowired
    private AdminService adminService;

    @Autowired
    private RoleService roleService;

    @RequestMapping("/assign/to/assign/role/page.html")
    public String toAssignRolePage(
            @RequestParam("adminId") Integer adminId,
            ModelMap map
    ){
        // 1.c查询已分配的角色
        List<Role> assignedRoleList = roleService.getAssignedRoles(adminId);

        List<Role> unAssignedRoleList = roleService.getUnAssignedRoles(adminId);

        // 2.存入模型
        map.addAttribute("assignedRoleList",assignedRoleList);
        map.addAttribute("unAssignedRoleList",unAssignedRoleList);
        return "assign-role";
    }

    /**
     * 给管理员添加角色
     * @return
     */
    @RequestMapping("/asssign/do/role/assign.html")
    public String assignRole(
            @RequestParam("adminId") Integer adminId,
            @RequestParam("pageNum") Integer pageNum,
            @RequestParam("keyword") String keyword,
            @RequestParam("roleList") List<Integer> roleIdList){

        adminService.saveAdminRoleRelationship(adminId,roleIdList);


        //

        return "redirect:/admin/get/page.html?keyword="+keyword+"&pageNum="+pageNum;    }

}
