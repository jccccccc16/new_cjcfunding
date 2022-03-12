package com.cjc.funding.test;

import com.cjc.funding.entity.Admin;
import com.cjc.funding.util.constant.CrowConstant;
import com.cjc.funding.util.utils.CrowUtils;
import org.junit.Test;

import java.lang.reflect.Field;
import java.util.Date;

public class StringTest {

    @Test
    public void testMd5(){
        String source="admin";
        String encoded= CrowUtils.md5(source);
        System.out.println(encoded);
    }

    @Test
    public void testStringEqualTo(){
        boolean b = CrowUtils.equalTo("123", "123");
        System.out.println(b);
    }




    @Test
    public void testDateToString(){
        String s = CrowUtils.DataToStringConverter(new Date(), "yyyy-MM-dd hh:mm:ss");
        System.out.println(s);
    }


    @Test
    public void testGetValue(){
        Admin admin = new Admin();
        admin.setLoginAcct("1316");
        admin.setUserPswd("1316");
        String userPswd = CrowUtils.getFieldValue(admin, "userPswd", String.class);
        System.out.println(userPswd);
    }

    @Test
    public void testReflect() throws NoSuchFieldException {
        Class clazz = Admin.class;
        Field userPswd = clazz.getDeclaredField("userPswd");
        Class<?> type = userPswd.getType();
        System.out.println(type);
        System.out.println(String.class);
    }

    @Test
    public void testSetter(){

        Admin admin = new Admin();
        String value="123";
        CrowUtils.setFieldValue("userPswd",String.class,value,admin);
        System.out.println(admin);


    }

    @Test
    public void testCompleteModel(){
        Admin oldAdmin = new Admin();
        Admin newAdmin = new Admin();
        oldAdmin.setUserPswd("123");
        oldAdmin.setLoginAcct("123");
        oldAdmin.setCreateTime(CrowUtils.DataToStringConverter(new Date(), CrowConstant.ATTR__PATTERN_DATE_YYYY_MM_DD_HH_MM_SS));
        oldAdmin.setEmail("123");
        oldAdmin.setId(123);
        oldAdmin.setUserName("123");
        Admin admin = CrowUtils.completeModel(newAdmin, oldAdmin, Admin.class);
        System.out.println(admin);
    }
}
