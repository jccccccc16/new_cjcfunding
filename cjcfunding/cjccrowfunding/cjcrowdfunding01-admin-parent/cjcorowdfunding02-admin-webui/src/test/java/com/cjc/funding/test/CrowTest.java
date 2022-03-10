package com.cjc.funding.test;

import com.cjc.funding.entity.Admin;
import com.cjc.funding.entity.Role;
import com.cjc.funding.mapper.AdminMapper;
import com.cjc.funding.service.api.AdminService;
import com.cjc.funding.service.api.RoleService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import javax.sql.DataSource;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = {"classpath:spring-persist-mybatis.xml","classpath:spring-persist-tx.xml"})
public class CrowTest {

    @Autowired
    DataSource dataSource;

    @Autowired
    private AdminMapper adminMapper;


    @Autowired
    private AdminService adminService;

    @Autowired
    private RoleService roleService;

    @Test
    public void testAdminMapperInsert(){
        Admin admin = new Admin(null,"cjc","cjc1316",
                "cjc1316@qq.com","cjc",null);
        adminMapper.insert(admin);

    }
    @Test
    public void test(){
        for(int i=0;i<123;i++){
            adminService.save(new Admin(null,"cjc"+i,"cjc"+i,
                    "cjc1316@qq.com","cjc",null));
        }
    }

    /**
     * 测试事务是否配置成功
     */
    @Test
    public void testTx(){
        adminService.save(new Admin(1, "jcc", "jcc", "jcc", "jcc", "2020-9-18"));
    }

    @Test
    public void test01(){
        Connection connection=null;
        try {
            connection= dataSource.getConnection();
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        System.out.println(connection);
    }


    @Test
    public void testGetAll(){

        List<Admin> all = adminService.getAll();
        System.out.println(all+"0000");
        for (Admin admin : all) {
            System.out.println(admin);
        }
    }


    @Test
    public void testSaveRole(){
        for(int i=0;i<125;i++){
            roleService.save(new Role(null,"role"+i));
        }
    }





}
