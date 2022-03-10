package com.cjc.crow.mapper;

import com.cjc.crow.entity.OrderProject;
import com.cjc.crow.entity.OrderProjectExample;
import java.util.List;

import com.cjc.crow.entity.OrderProjectVO;
import org.apache.ibatis.annotations.Param;

public interface OrderProjectMapper {
    int countByExample(OrderProjectExample example);

    int deleteByExample(OrderProjectExample example);

    int insert(OrderProject record);

    int insertSelective(OrderProject record);

    List<OrderProject> selectByExample(OrderProjectExample example);

    int updateByExampleSelective(@Param("record") OrderProject record, @Param("example") OrderProjectExample example);

    int updateByExample(@Param("record") OrderProject record, @Param("example") OrderProjectExample example);

    public OrderProjectVO selectOrderProjectVO(@Param("projectId")Integer projectId, @Param("returnId") Integer returnId);

}