package com.cjc.crow.handler;

import com.cjc.crow.entity.Address;
import com.cjc.crow.entity.OrderProjectVO;
import com.cjc.crow.entity.OrderVO;
import com.cjc.crow.service.api.OrderService;
import com.cjc.crow.service.impl.OrderServiceImpl;
import com.cjc.crow.util.ResultEntity;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.util.List;

/**
 * Created by IntelliJ IDEA.
 * User: cjc
 * Date: 2021/1/23
 * Time: 17:17
 * To change this template use File | Settings | File Templates.
 **/
@RestController
public class OrderProviderHandler {

    @Resource
    private OrderService orderService;

    private Logger logger = LoggerFactory.getLogger(OrderProviderHandler.class);


    @ResponseBody
    @RequestMapping("/get/order/project/vo/remote")
    public ResultEntity<OrderProjectVO> getOrderProjectVORemote(
            @RequestParam("projectId") Integer projectId,
            @RequestParam("returnId") Integer returnId){

        try{
            OrderProjectVO orderProjectVO = orderService.getOrderProjectVO(projectId,returnId);
            return ResultEntity.successWithData(orderProjectVO);
        }catch (Exception e){
            e.printStackTrace();
            return ResultEntity.failed(e.getMessage());
        }

    }

    @ResponseBody
    @RequestMapping("/get/address/remote")
    ResultEntity<List<Address>> getAddressRemote(@RequestParam("id")Integer id){

        try{
            List<Address> addressList = orderService.getAddressByMemberId(id);

            ResultEntity<List<Address>> resultEntity = ResultEntity.successWithData(addressList);
            return resultEntity;
        }catch (Exception e){
            e.printStackTrace();
            return ResultEntity.failed(e.getMessage());

        }
    }

    @ResponseBody
    @RequestMapping("/save/address/remote")
    ResultEntity<Address> saveAddressRemote(@RequestBody Address address){
        logger.info(address.toString());
        try{
            Address addressWithId = orderService.saveAddress(address);
            return ResultEntity.successWithData(addressWithId);
        }catch (Exception e){
            e.printStackTrace();
            logger.warn("address插入失败!");
            return ResultEntity.failed(e.getMessage());
        }
    }

    @RequestMapping("/save/order/remote")
    ResultEntity<String> saveOrderRemote(@RequestBody OrderVO orderVO) {

        try {
            orderService.saveOrder(orderVO);

            return ResultEntity.successWithoutData();

        } catch (Exception e) {
            e.printStackTrace();

            return ResultEntity.failed(e.getMessage());
        }

    }


}
