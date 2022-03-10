package com.cjc.funding.mvc.controller;

import com.cjc.funding.entity.Admin;
import com.cjc.funding.entity.Role;
import com.cjc.funding.service.api.AdminService;
import com.cjc.funding.util.constant.CrowConstant;
import com.cjc.funding.util.utils.ResultEntity;
import com.github.pagehelper.PageInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.*;



/**
 * admin模块的Controller
 */
@Controller
public class AdminController {

    @Autowired
    private AdminService adminService;

    /**
     * 登录操作
     * @return
     */
//
//    @RequestMapping("/admin/do/doLogin.html")
//    public String doLogin(
//            @RequestParam("loginAcct") String loginAcct,
//            @RequestParam("userPswd") String userPswd,
//            HttpSession session){
//        Admin adminByAccount = adminService.getAdminByAccount(loginAcct, userPswd);
//        session.setAttribute(CrowConstant.ATTR_NAME_LOGIN_ADMIN,adminByAccount);
//        return "redirect:/admin/to/main/page.html";
//    }
//
//    @RequestMapping(value = "/admin/do/doLogout.html",method = RequestMethod.GET)
//    public String logout(HttpSession session){
//        session.invalidate();
//        return "redirect:/admin/to/login/page.html";
//    }

    @RequestMapping("/admin/get/page.html")
    public String getPageInfo(
            @RequestParam(value = "keyword",defaultValue = "") String keyword,
            @RequestParam(value = "pageNum",defaultValue = "1") Integer pageNum,
            @RequestParam(value = "pageSize",defaultValue = "5") Integer pageSize,
            ModelMap map
    ){

        PageInfo<Admin> pageInfo = adminService.getPageInfo(keyword, pageNum, pageSize);
        map.addAttribute(CrowConstant.ATTR_NAME_PAGEINFO,pageInfo);
        return "admin-page";
    }

    @RequestMapping("/admin/get/page.json")
    public @ResponseBody ResultEntity<PageInfo<Admin>> getPageInfoAjax(
            @RequestParam(value = "keyword",defaultValue = "") String keyword,
            @RequestParam(value = "pageNum",defaultValue = "1") Integer pageNum,
            @RequestParam(value = "pageSize",defaultValue = "5") Integer pageSize){

        PageInfo<Admin> pageInfo = adminService.getPageInfo(keyword, pageNum, pageSize);
        ResultEntity<PageInfo<Admin>> pageInfoResultEntity = ResultEntity.successWithData(pageInfo);
        return pageInfoResultEntity;

    }

    @PreAuthorize(value = "hasRole('PROADMIN')")
    @RequestMapping("/admin/delect/{id}/{pageNum}/{keyword}.html")
    public String deleteAdmin(
           @PathVariable("id") Integer id,
            @PathVariable("pageNum") Integer pageNum,
            @PathVariable("keyword") String keyword){
        // 1.调用service删除
        adminService.deleteById(id);
        // 2.删除成功，跳转到admin.page，带有关键词和页码
        return "redirect:/admin/get/page.html?keyword="+keyword+"&pageNum="+pageNum;
    }


    @PreAuthorize(value = "hasRole('PROADMIN')")
    @RequestMapping("/admin/do/save.html")
    public String saveAdmin(Admin admin){
        adminService.save(admin);
        return "redirect:/admin/get/page.html?pageNum="+Integer.MAX_VALUE;
    }

    /**
     * 修改页面的数据回显
     * @param id
     * @param modelMap
     * @return
     */
    @RequestMapping("/admin/to/edit/page.html")
    public String feedBackAdmin(@RequestParam(value = "id",required = true) Integer id,ModelMap modelMap){
        Admin admin = adminService.getById(id);
        modelMap.addAttribute(CrowConstant.ATTR_NAME_ADMIN,admin);
        return "admin-edit";


    }



    @RequestMapping("/admin/do/edit.html")
    public String editAdmin(Admin admin){
        adminService.edit(admin);
        return "redirect:/admin/get/page.html";
    }

    /**
     * ajax
     * 新增用户时，判断登录账号是否存在
     * @return
     */

    @RequestMapping("admin/do/judgeLoginAcctExist.json")
    public @ResponseBody
    ResultEntity<Object> judgeLoginAccountExist(@RequestBody String loginAcct){
        adminService.getAdminByLoginAcct(loginAcct);
        ResultEntity<Object> objectResultEntity = ResultEntity.successWithMessageNoData(CrowConstant.MESSAGE_LOGIN_ACCOUNT_ENABLED);
        return objectResultEntity;

    }




}
