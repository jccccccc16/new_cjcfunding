package com.cjc.funding.test.com.cjc.funding.service;

import com.cjc.funding.entity.Admin;
import com.cjc.funding.service.api.AdminService;
import junit.framework.TestCase;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = {"classpath:spring-persist-tx.xml","classpath:spring-persist-mybatis.xml"})
public class AdminServiceTest extends TestCase {

    @Autowired
    private AdminService adminService;

    @Test
    public void testSave() {
        Admin admin = new Admin(null,"cjc1317","cjc1317","cjc1317","cjc1317","2020-09-21");
        adminService.save(admin);
    }

    @Test
    public void testGetAll() {
    }

    public void testGetById() {
    }

    @Test
    public void testGetAdminByAccount() {
        Admin adminByAccount = adminService.getAdminByAccount("cjc1317", "cjc1317");
        System.out.println(adminByAccount);

    }
}