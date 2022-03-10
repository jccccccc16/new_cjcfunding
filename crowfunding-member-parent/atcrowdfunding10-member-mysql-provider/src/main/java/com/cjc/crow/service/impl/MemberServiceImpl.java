package com.cjc.crow.service.impl;

import com.cjc.crow.constant.CrowdConstant;
import com.cjc.crow.entity.Member;
import com.cjc.crow.entity.MemberExample;
import com.cjc.crow.exception.LoginFailedException;
import com.cjc.crow.service.api.MemberService;
import com.cjc.crow.mapper.MemberMapper;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.util.List;

/**
 * Created by IntelliJ IDEA.
 * User: cjc
 * Date: 2020/11/1
 * Time: 10:36
 * To change this template use File | Settings | File Templates.
 **/
//@Transactional(readOnly = true)  // 只读
@Service
public class MemberServiceImpl  implements MemberService {

    @Resource
    private MemberMapper memberMapper;

    public Member getMemberByLoginAcct(String loginAcct) throws LoginFailedException {

        // 1.填充查询条件
        MemberExample memberExample = new MemberExample();

        MemberExample.Criteria criteria = memberExample.createCriteria();

        criteria.andLoginacctEqualTo(loginAcct);

        // 2.查询
        List<Member> memberList = memberMapper.selectByExample(memberExample);

        // 3.判断所查询的值是否合法
        // 3.1 判断是否为空
        if(memberList==null || memberList.size()==0){
            // 抛出异常
            throw new LoginFailedException(CrowdConstant.MESSAGE_LOGIN_FAILED);

        }

        // 4.返回member
        return memberList.get(0);

    }

    public void saveMember(Member member) {

        memberMapper.insertSelective(member);


    }
}
