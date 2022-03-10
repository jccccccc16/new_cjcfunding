package com.cjc.funding.mvc.controller;

import com.cjc.funding.entity.Admin;
import com.cjc.funding.entity.Student;
import com.cjc.funding.service.api.AdminService;

import com.cjc.funding.util.utils.CrowUtils;
import com.cjc.funding.util.utils.ResultEntity;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import java.util.List;
@Controller
public class AjaxController {

    @Autowired
    private AdminService adminService;

    private Logger logger = LoggerFactory.getLogger(AjaxController.class);

    @RequestMapping("/getAdminByAjax.json")
    public @ResponseBody
    List<Admin> getAdminByAjax() {
        List<Admin> adminList = adminService.getAll();
        logger.info(adminList+"");
        return adminList;

    }

    @RequestMapping("/getAdminById.json")
    public @ResponseBody Admin getAdminById(@RequestParam("id") Integer id){
        return adminService.getById(id);
    }

    @RequestMapping("/student.json")
    public @ResponseBody
    String student(@RequestBody Student student){
        System.out.println(student);
        logger.info(student.toString());
        return "success";
    }

    @RequestMapping(value = "/testResultEntity.json",method = RequestMethod.POST)
    public @ResponseBody ResultEntity<Student> testResultEntity(@RequestBody Student student, HttpServletRequest request){
        logger.info(student.toString());
        ResultEntity<Student> studentResultEntity = ResultEntity.successWithData(student);
        boolean judgeRequestType = CrowUtils.judgeRequestType(request);
        logger.info("judgeRequestType"+judgeRequestType);
        return studentResultEntity;
    }





}
