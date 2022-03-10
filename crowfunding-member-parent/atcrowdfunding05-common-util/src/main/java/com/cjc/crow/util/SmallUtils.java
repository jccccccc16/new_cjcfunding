package com.cjc.crow.util;

/**
 * Created by IntelliJ IDEA.
 * User: cjc
 * Date: 2020/11/20
 * Time: 16:55
 * To change this template use File | Settings | File Templates.
 **/
public class SmallUtils {

    public final static Integer TEN=100;
    public final static Integer HUNDRED=1000;
    public final static Integer THOUSAND=1000;

    /**
     * 生成自定义位的随机数
     * @param unit
     * @return
     */
    public static Integer getRandom(Integer unit){

        double random = Math.random();

        Integer result = (int)( random*unit);

        return result;
    }




    public static void main(String[] args) {
        Integer random = SmallUtils.getRandom(SmallUtils.HUNDRED);
        System.out.println(random);
    }

}
