package com.cjc.funding.util.utils;

import com.cjc.funding.util.constant.CrowConstant;
import com.sun.org.apache.xpath.internal.operations.Mod;

import javax.servlet.http.HttpServletRequest;
import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.math.BigInteger;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.logging.SimpleFormatter;

/**
 * 工具
 */
public class CrowUtils {

    /**
     * 判断是否是ajax请求
     *
     * @param request
     * @return 是ajax返回ture，否，返回false
     */
    public static boolean judgeRequestType(HttpServletRequest request) {

        // 1.获取请求头中的accept和X-Requested-With
        String accept = request.getHeader("Accept");
        String requestWith = request.getHeader("X-Requested-With");
        // 2.判断accept中是否包含application/json，
        // 3.X-Requested-With中是否包含XMLHttpRequest
        if ((accept != null & accept.contains("application/json"))

                ||
                (requestWith != null & accept.contains("XMLHttpRequest"))
        ) {
            // 3.1为ajax请求
            return true;
        } else {
            // 3.2为普通请求
            return false;
        }
    }

    /**
     * 加密密码
     *
     * @param source
     * @return
     */
    public static String md5(String source) {
        // 1.判断是否为空
        if (source == null || source.length() == 0) {
            // 2.为空抛出异常
            throw new RuntimeException(CrowConstant.MESSAGE_STRING_INVALIDATE);
        }

        // 3.获取MessageDigest对象
        String algorithm = "md5";

        try {
            MessageDigest messageDigest = MessageDigest.getInstance(algorithm);
            // 4.获取字节数组
            byte[] sourceBytes = source.getBytes();
            // 5.执行加密
            byte[] digest = messageDigest.digest(sourceBytes);
            int sigNum = 1;
            BigInteger bigInteger = new BigInteger(sigNum, digest);
            int radix = 16;
            String encode = bigInteger.toString(radix).toUpperCase();
            return encode;
        } catch (NoSuchAlgorithmException e) {
            e.printStackTrace();
        }

        return null;
    }

    /**
     * 判断两个对象是否相等
     *
     * @param a
     * @param b
     * @return
     */
    public static boolean equalTo(Object a, Object b) {
        return (a == b) || (a != null && a.equals(b));
    }

    /**
     * 时间转字符串，添加进数据库
     * @return
     */
    public static String DataToStringConverter(Date date,String pattern){
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat(pattern);
        String format = simpleDateFormat.format(date);
        return format;
    }

    public static <Type> Type completeModel(Type newModel,Type oldModel,Class<Type> clazz){

        if(newModel==null || oldModel==null){
            throw new NullPointerException("不能为空");
        }

        Field[] declaredFields = clazz.getDeclaredFields();
        // 遍历declaredFields
        for (Field declaredField : declaredFields) {
            // 获取属性名
            String fieldName = declaredField.getName();

            // 获取该属性的类型
            Class<?> typeClass = declaredField.getType();

            // 获取newModel的属性值
            Object newFieldValue = getFieldValue(newModel, fieldName, typeClass);

            // 判断newModel该属性值是否为空
            if(newFieldValue==null || newFieldValue.equals("")){

                // 如果为空，把oldModel的属性值赋给newModel
                // 获取oldModel相应的属性值
                Object oldFieldValue = getFieldValue(oldModel, fieldName, typeClass);

                // 赋值给newModel
                 setFieldValue(declaredField.getName(),declaredField.getType(), oldFieldValue, newModel);
            }

            // 属性值不为空
            // 不作操作

        }
        return newModel;
    }

    /**
     * 反射获取该属性的值
     * @param model
     * @param fieldName
     * @param typeClass
     * @param <Type>
     * @return
     */
    public static <Type> Type getFieldValue(Object model,String fieldName,Class<Type> typeClass)  {

        // 得到的值
        Type value=null;
        Class modelClazz = model.getClass();

        String getterString = getGetterName(fieldName);

        // 调用method，获取对应的值
        Method getterMethod=null;
        try {
            getterMethod = modelClazz.getDeclaredMethod(getterString);
            Object  returnValue= getterMethod.invoke(model);

            // 获取相应的属性值
            value=(Type) returnValue;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return value;

    }

    public static Object setFieldValue(String fieldName,Class typeClass,Object value,Object model){

        Class modelClazz = model.getClass();

        // 属性类型



        String setterString = getSetterName(fieldName);

        // 获取setter方法
        try {
            // 调用setter方法
            Method setterMethod = modelClazz.getDeclaredMethod(setterString, typeClass);
            setterMethod.invoke(model,value);
            return model;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    public static String getGetterName(String fieldName){
        // 把属性名的第一个字母大写
        String head = fieldName.substring(0,1);
        String upperHead = head.toUpperCase();

        // 得到 getXxx()
        String getterString = "get"+upperHead+fieldName.substring(1);
        return getterString;
    }

    public static String getSetterName(String fieldName){
        // 把属性名的第一个字母大写
        // 把属性名的第一个字母大写
        String head = fieldName.substring(0,1);
        String upperHead = head.toUpperCase();

        // 得到 setXxx();
        String setterString = "set"+upperHead+fieldName.substring(1);
        return setterString;
    }


}


