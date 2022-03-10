package com.cjc.crow.handler;

import com.cjc.crow.api.MySqlRemoteService;
import com.cjc.crow.api.RedisRemoteService;
import com.cjc.crow.config.ShortMessageProperties;
import com.cjc.crow.constant.CrowdConstant;
import com.cjc.crow.entity.Member;
import com.cjc.crow.entity.MemberLoginVO;
import com.cjc.crow.entity.MemberVO;
import com.cjc.crow.util.CrowdUtil;
import com.cjc.crow.util.ResultEntity;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.*;


import javax.annotation.Resource;
import javax.servlet.http.HttpSession;
import javax.websocket.Session;


import java.util.concurrent.TimeUnit;

/**
 * Created by IntelliJ IDEA.
 * User: cjc
 * Date: 2020/11/5
 * Time: 22:47
 * To change this template use File | Settings | File Templates.
 **/
@Controller
public class MemberHandler {

    @Autowired
    private ShortMessageProperties shortMessageProperties;

    @Resource
    private RedisRemoteService redisRemoteService;

    @Resource
    private MySqlRemoteService mySqlRemoteService;

    private Logger logger = LoggerFactory.getLogger(MemberHandler.class);


    @RequestMapping("/auth/member/logout")
    public String logout(HttpSession session) {

        session.invalidate();

        return "redirect:http://localhost:8080";
    }


    @RequestMapping("/auth/member/send/short/message.json")
    public @ResponseBody
    ResultEntity<String> sendMessage(@RequestParam("phoneNum") String phoneNum) {

        logger.debug("获取的手机号码为: " + phoneNum);

//        ResultEntity<String> sendMessageResultEntity = CrowdUtil.sendCodeByShortMessage(
//                shortMessageProperties.getHost(),
//                shortMessageProperties.getPath(),
//                shortMessageProperties.getMethod(),
//                shortMessageProperties.getAppcode(),
//                phoneNum, shortMessageProperties.getTpl_id());

        ResultEntity<String> sendMessageResultEntity = CrowdUtil.sendCodeByShortMessage(phoneNum);

        // 判断是否发送成功
        if (ResultEntity.SUCCESS.equals(sendMessageResultEntity.getResult())) {

            // 获取验证码
            String code = sendMessageResultEntity.getData();

            logger.debug("验证码: " + code);

            String key = CrowdConstant.REDIS_CODE_PREFIX + phoneNum;

            ResultEntity<String> saveCodeResultEntity =
                    redisRemoteService.setRedisKeyValueRemoteWithTimeout(key, code, 15, TimeUnit.MINUTES);

            // 如果存入redis成功
            if (ResultEntity.SUCCESS.equals(saveCodeResultEntity.getResult())) {

                logger.debug("saveCodeResultEntity: " + saveCodeResultEntity);

                return ResultEntity.successWithoutData();

            } else { // 存入失败

                return saveCodeResultEntity;
            }
        } else {  // 发送失败

            logger.debug("saveCodeResultEntity: " + sendMessageResultEntity);

            return sendMessageResultEntity;
        }
    }


    @RequestMapping(value = "/auth/member/register", method = RequestMethod.POST)
    public String saveMember(MemberVO memberVO, ModelMap map) {

        // 1.获取redis中的验证码
        // ① 获取memberVo中的手机号
        String phoneNum = memberVO.getPhoneNum();

        // ② 将memberVo中的手机号进行拼接成redis中的key
        String key = CrowdConstant.REDIS_CODE_PREFIX + phoneNum;

        // ③ 根据此key在redis中查找
        ResultEntity<String> redisResultEntity = redisRemoteService.getRedisStringByKeyRemote(key);

        // ④ 检查查询操作是否有效
        String result = redisResultEntity.getResult();

        // 判断是否失败
        if (ResultEntity.FAILED.equals(result)) {

            logger.info(redisResultEntity.getMessage());

            map.addAttribute(CrowdConstant.ATTR_NAME_MESSAGE, redisResultEntity.getMessage());

            return "member-reg";
        }


        // 2.判断验证码是否为空
        String redisCode = redisResultEntity.getData();

        // 判断验证是否为空
        if (redisCode == null || redisCode.equals("")) {

            logger.info("redis中的验证码为空");

            map.addAttribute(CrowdConstant.ATTR_NAME_MESSAGE, CrowdConstant.MESSAGE_CODE_NOT_EXIST);

            return "member-reg";
        }

        // 获取表单的验证码
        String formCode = memberVO.getCode();

        // 如果验证码不正确
        if (!formCode.equals(redisCode)) {

            logger.info("验证码不正确");

            map.addAttribute(CrowdConstant.ATTR_NAME_MESSAGE, CrowdConstant.MESSAGE_CODE_INVALID);

            return "member-reg";

        }


        // ① 正确，将验证码移出
        redisRemoteService.removeRedisKeyRemote(key);

        // 4.执行密码加密
        BCryptPasswordEncoder bCryptPasswordEncoder = new BCryptPasswordEncoder();
        String userpswd = memberVO.getUserpswd();
        String encodePswd = bCryptPasswordEncoder.encode(userpswd);
        memberVO.setUserpswd(encodePswd);

        // 5.保存member
        // ①创建空member
        Member member = new Member();

        // ②复制属性
        BeanUtils.copyProperties(memberVO, member);

        ResultEntity<Member> mysqlResultEntity = mySqlRemoteService.saveMember(member);

        // 6.如果保存失败
        String mysqlResult = mysqlResultEntity.getResult();

        if (ResultEntity.FAILED.equals(mysqlResult)) {

            // 输出保存失败的信息
            logger.info(mysqlResultEntity.getMessage());

            map.addAttribute(CrowdConstant.ATTR_NAME_MESSAGE, mysqlResultEntity.getMessage());

            return "member-reg";
        }

        // 保存成功，跳转到登录页面

        return "redirect:http://localhost/auth/member/to/login/page.html";

    }

    @RequestMapping(value = "/auth/member/do/login",method = RequestMethod.POST)
    public String login(
            @RequestParam("loginacct") String loginacct,
            @RequestParam("userpswd") String userpswd,
            ModelMap modelMap,
            HttpSession session){

        ResultEntity<Member> loginAcctResultEntity = mySqlRemoteService.getMemberByLoginAcct(loginacct);

        // 1.如果失败，跳转到登录页面
        if(ResultEntity.FAILED.equals(loginAcctResultEntity.getResult())){

            modelMap.addAttribute(CrowdConstant.ATTR_NAME_MESSAGE,loginAcctResultEntity.getMessage());

            logger.info("loginAcctResultEntity"+loginAcctResultEntity);

            return "member-login";
        }

        Member mysqlMember = loginAcctResultEntity.getData();

        if(mysqlMember == null){

            modelMap.addAttribute(CrowdConstant.ATTR_NAME_MESSAGE,CrowdConstant.MESSAGE_LOGIN_FAILED);

            return "member-login";
        }


        // 2.如果查询成功，则比对密码
        // 由于每次加密的盐值都不一样，所以每次加密出来的字符串都不一样
        // 不能将密码加密后两两对比
        BCryptPasswordEncoder bCryptPasswordEncoder = new BCryptPasswordEncoder();

        String mysqlPswdEncode = mysqlMember.getUserpswd();

        // 将页面的密码和数据中查找的密码对比
        // 密码不正确
        if(!bCryptPasswordEncoder.matches(userpswd,mysqlPswdEncode)){

            // 返回登录页面
            modelMap.addAttribute(CrowdConstant.ATTR_NAME_MESSAGE,CrowdConstant.MESSAGE_LOGIN_FAILED);

            return "member-login";
        }

        // 密码正确
        // 3.创建memberLoginVO，添加到session中
        MemberLoginVO memberLoginVO = new MemberLoginVO();

        // ①属性赋值
        BeanUtils.copyProperties(mysqlMember,memberLoginVO);

        session.setAttribute(CrowdConstant.ATTR_NAME_LOGIN_MEMBER,memberLoginVO);

        // 跳转到用户中心页面
        return "redirect:http://localhost/auth/member/to/center.html";



    }


//    @RequestMapping("/member/my/crowd")
//    public String toMyCrowd(HttpSession session,ModelMap modelMap){
//
//        MemberLoginVO memberLoginVO = (MemberLoginVO) session.getAttribute(CrowdConstant.ATTR_NAME_LOGIN_MEMBER);
//        Integer memberLoginVOId = memberLoginVO.getId();
//
//        // 项目信息
//
//        // 回报个数
//
//        // 交易状态
//
//        //
//
//
//        return "member-center";
//    }
//



}
