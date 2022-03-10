package com.cjc.crow.handler;

import com.cjc.crow.entity.Member;
import com.cjc.crow.service.api.MemberService;
import com.cjc.crow.util.ResultEntity;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

/**
 * Created by IntelliJ IDEA.
 * User: cjc
 * Date: 2020/11/1
 * Time: 10:33
 * To change this template use File | Settings | File Templates.
 **/
@RestController
public class MemberProviderHandler {

    @Autowired
    private MemberService memberService;

    private Logger logger = LoggerFactory.getLogger(MemberProviderHandler.class);

    @RequestMapping("/get/memberpo/by/login/acct/remote")
    public ResultEntity<Member> getMemberByLoginAcct(@RequestParam("loginacct") String loginAcct) {


        Member memberByLoginAcct = null;

        try {
            memberByLoginAcct = memberService.getMemberByLoginAcct(loginAcct);

            return ResultEntity.successWithData(memberByLoginAcct);

        } catch (Exception exception) {

            exception.printStackTrace();

            return ResultEntity.failed(exception.getMessage());
        }
    }


    /**
     * 保存一个member
     *
     * @param member
     * @return
     */
    @RequestMapping("/save/member/remote")
    public ResultEntity<Member> saveMember(@RequestBody Member member) {

        try {

            memberService.saveMember(member);
            logger.debug("保存: "+member+" 成功");
            logger.info("保存member成功");
            return ResultEntity.successWithoutData();

        } catch (Exception exception) {

            exception.printStackTrace();
            logger.info("保存失败： "+exception.getMessage());
            return ResultEntity.failed(exception.getMessage());
        }


    }

}
