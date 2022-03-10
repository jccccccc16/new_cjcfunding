package com.cjc.crow.api;

import com.cjc.crow.entity.*;
import com.cjc.crow.util.ResultEntity;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * Created by IntelliJ IDEA.
 * User: cjc
 * Date: 2020/11/1
 * Time: 10:07
 * To change this template use File | Settings | File Templates.
 **/
@FeignClient("crow-mysql")
public interface MySqlRemoteService {

    @RequestMapping("/get/memberpo/by/login/acct/remote")
    ResultEntity<Member> getMemberByLoginAcct(@RequestParam("loginacct") String loginAcct);

    @RequestMapping("/save/member/remote")
    public ResultEntity<Member> saveMember(@RequestBody Member member);


    @ResponseBody
    @RequestMapping("/save/projectVO")
    public ResultEntity<String> saveProjectVORemote(

            @RequestBody ProjectVO projectVO,
            @RequestParam("memberId") Integer memberId
    );


    @ResponseBody
    @RequestMapping("/get/portal/type/VO/remote")
    public ResultEntity<List<PortalTypeVO>> getPortalTypeVORemote();


    @ResponseBody
    @RequestMapping("/get/project/detail/by/project/id/remote/{id}")
    public ResultEntity<ProjectDetailVO>
        getProjectDetailByProjectId(@PathVariable("id") Integer projectId);


    @ResponseBody
    @RequestMapping("/get/order/project/vo/remote")
    public ResultEntity<OrderProjectVO> getOrderProjectVORemote(@RequestParam("projectId") Integer projectId, @RequestParam("returnId") Integer returnId);

    @ResponseBody
    @RequestMapping("/get/address/remote")
    ResultEntity<List<Address>> getAddressRemote(@RequestParam("id")Integer id);

    @ResponseBody
    @RequestMapping("/save/address/remote")
    ResultEntity<Address> saveAddressRemote(@RequestBody Address address);

    @RequestMapping("/save/order/remote")
    ResultEntity<String> saveOrderRemote(@RequestBody OrderVO orderVO);
}