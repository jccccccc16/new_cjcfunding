package com.cjc.crow.service.api;

import com.cjc.crow.entity.Address;
import com.cjc.crow.entity.OrderProjectVO;
import com.cjc.crow.entity.OrderVO;

import java.util.List;

/**
 * Created by IntelliJ IDEA.
 * User: cjc
 * Date: 2021/1/23
 * Time: 17:18
 * To change this template use File | Settings | File Templates.
 **/
public interface OrderService {



    public OrderProjectVO getOrderProjectVO(Integer projectId, Integer returnId);

    public List<Address> getAddressByMemberId(Integer memberId);

    Address saveAddress(Address address);

    public void saveOrder(OrderVO orderVO);


}
