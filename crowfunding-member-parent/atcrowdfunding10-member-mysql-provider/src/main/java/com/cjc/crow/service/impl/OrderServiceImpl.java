package com.cjc.crow.service.impl;

import com.cjc.crow.entity.*;
import com.cjc.crow.mapper.AddressMapper;
import com.cjc.crow.mapper.OrderMapper;
import com.cjc.crow.mapper.OrderProjectMapper;
import com.cjc.crow.service.api.OrderService;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.util.List;

/**
 * Created by IntelliJ IDEA.
 * User: cjc
 * Date: 2021/1/23
 * Time: 17:18
 * To change this template use File | Settings | File Templates.
 **/
@Service
@Transactional
public class OrderServiceImpl implements OrderService {

    @Resource
    private OrderProjectMapper orderProjectMapper;

    @Resource
    private OrderMapper orderMapper;

    @Resource
    private AddressMapper addressMapper;

    public OrderProjectVO getOrderProjectVO(Integer projectId, Integer returnId) {

        return orderProjectMapper.selectOrderProjectVO(projectId,returnId);


    }

    public List<Address> getAddressByMemberId(Integer memberId) {
        List<Address> addressList = addressMapper.selectAddressByMemberId(memberId);
        return addressList;
    }

    public Address saveAddress(Address address) {
        addressMapper.insert(address);
        return address;
    }

    @Transactional(readOnly = false, propagation = Propagation.REQUIRES_NEW, rollbackFor = Exception.class)
    public void saveOrder(OrderVO orderVO) {

        Order orderPO = new Order();

        BeanUtils.copyProperties(orderVO, orderPO);

        OrderProject orderProjectPO = new OrderProject();

        BeanUtils.copyProperties(orderVO.getOrderProjectVO(), orderProjectPO);

        // 保存orderPO自动生成的主键是orderProjectPO需要用到的外键
        orderMapper.insert(orderPO);

        // 从orderPO中获取orderId
        Integer id = orderPO.getId();

        // 将orderId设置到orderProjectPO
        orderProjectPO.setOrderId(id);

        orderProjectMapper.insert(orderProjectPO);
    }

}
