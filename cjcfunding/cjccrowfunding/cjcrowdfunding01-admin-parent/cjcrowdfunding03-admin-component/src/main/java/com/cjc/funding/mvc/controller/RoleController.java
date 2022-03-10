package com.cjc.funding.mvc.controller;

import com.cjc.funding.entity.Admin;
import com.cjc.funding.entity.Role;
import com.cjc.funding.service.api.RoleService;
import com.cjc.funding.util.constant.CrowConstant;
import com.cjc.funding.util.utils.ResultEntity;
import com.github.pagehelper.PageInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.List;

/**
 * Created by IntelliJ IDEA.
 * User: cjc
 * Date: 2020/9/27
 * Time: 21:46
 * To change this template use File | Settings | File Templates.
 * 角色模块handler
 **/
@Controller
public class RoleController {

    @Autowired
    private RoleService roleService;



    @RequestMapping("/role/get/page/info.json")
    public @ResponseBody
    ResultEntity<PageInfo<Role>> getPageInfo(
            @RequestParam(value = "keyword",defaultValue = "") String keyword,
            @RequestParam(value = "pageNum",defaultValue = "1") Integer pageNum,
            @RequestParam(value = "pageSize",defaultValue = "5") Integer pageSize,
            ModelMap map
    ){

        PageInfo<Role> pageInfo = roleService.getPageInfo(keyword, pageNum, pageSize);
        ResultEntity<PageInfo<Role>> pageInfoResultEntity = ResultEntity.successWithData(pageInfo);
        return pageInfoResultEntity;
    }

    @RequestMapping("/role/do/save.json")
    public @ResponseBody ResultEntity<Object> saveRole(
            @RequestParam(value = "roleName",required = true)String roleName){

        Role role = new Role(null,roleName);
        roleService.save(role);
        ResultEntity<Object> resultEntity = ResultEntity.successWithMessageNoData("保存成功");
        return resultEntity;
    }

    @RequestMapping("/role/remove/by/role/id/array.json")
    public @ResponseBody
    ResultEntity<String> removeByRoleIdArray(@RequestBody List<Integer> roleIdList){
        roleService.deleteRole(roleIdList);
        return ResultEntity.successWithoutData();
    }

}
