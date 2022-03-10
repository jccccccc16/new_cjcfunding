package com.cjc.crow.handler;

import com.cjc.crow.api.MySqlRemoteService;
import com.cjc.crow.constant.CrowdConstant;
import com.cjc.crow.entity.Address;
import com.cjc.crow.entity.MemberLoginVO;
import com.cjc.crow.entity.OrderProjectVO;
import com.cjc.crow.util.ResultEntity;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpSession;
import java.util.List;

/**
 * Created by IntelliJ IDEA.
 * User: cjc
 * Date: 2021/1/23
 * Time: 16:13
 * To change this template use File | Settings | File Templates.
 **/
@Controller
public class OrderHandler {

    @Autowired
    private MySqlRemoteService mySqlRemoteService;

    private Logger logger = LoggerFactory.getLogger(OrderHandler.class);

    @RequestMapping("/confirm/return/info/{projectId}/{returnId}")
    public String showReturnConfirmInfo(@PathVariable("projectId") Integer projectId,
                                        @PathVariable("returnId") Integer returnId,
                                        HttpSession session){

        ResultEntity<OrderProjectVO> resultEntity = mySqlRemoteService.getOrderProjectVORemote(projectId,returnId);

        // 如果获取成功
        if(ResultEntity.SUCCESS.equals(resultEntity.getResult())){
            OrderProjectVO data = resultEntity.getData();
            session.setAttribute("orderProjectVO",data);

            logger.info("查找到的data :" + data);
        }else{
            logger.info(resultEntity+"");
            logger.info("查找OrderProjectVO失败");
        }

        return "confirm_return";

    }


    @RequestMapping("/confirm/order/{returnCount}")
    public String confirmOrder(
            @PathVariable("returnCount") Integer returnCount,
            HttpSession session                   ){

        // 修改session域中的returnCount
        OrderProjectVO orderProjectVO = (OrderProjectVO) session.getAttribute("orderProjectVO");
        orderProjectVO.setReturnCount(returnCount);

        // 获取该用户信息

        MemberLoginVO memberLoginVO = (MemberLoginVO) session.getAttribute(CrowdConstant.ATTR_NAME_LOGIN_MEMBER);

        Integer id = memberLoginVO.getId();
        // 查找该用户对应的地址
        ResultEntity<List<Address>> resultEntityList = mySqlRemoteService.getAddressRemote(id);

        if (ResultEntity.SUCCESS.equals(resultEntityList.getResult())) {

            List<Address> data = resultEntityList.getData();
            logger.info("addressList:"+data);
            session.setAttribute("addressList", data);
        }

        return "confirm_order";
    }

    @ResponseBody
    @RequestMapping("/save/address.json")
    public ResultEntity<Address> saveAddress(@RequestBody Address address){
        logger.info("address:"+address.toString());
        ResultEntity<Address> resultEntity = mySqlRemoteService.saveAddressRemote(address);
        logger.info(resultEntity.toString());
        return resultEntity;
    }


}
