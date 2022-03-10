package com.cjc.crow.service.api;

import com.cjc.crow.entity.Member;

/**
 * Created by IntelliJ IDEA.
 * User: cjc
 * Date: 2020/11/1
 * Time: 10:36
 * To change this template use File | Settings | File Templates.
 **/
public interface MemberService {


    Member getMemberByLoginAcct(String loginAcct);

    /**
     * 保存member
     * @param member
     */
    void saveMember(Member member);
}



