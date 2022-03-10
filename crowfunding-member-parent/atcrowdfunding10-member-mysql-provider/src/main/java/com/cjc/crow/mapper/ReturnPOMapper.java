package com.cjc.crow.mapper;

import com.cjc.crow.entity.ReturnPO;
import com.cjc.crow.entity.ReturnPOExample;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface ReturnPOMapper {

    int countByExample(ReturnPOExample example);

    int deleteByExample(ReturnPOExample example);

    int insert(ReturnPO record);

    int insertSelective(ReturnPO record);

    List<ReturnPO> selectByExample(ReturnPOExample example);

    int updateByExampleSelective(@Param("record") ReturnPO record, @Param("example") ReturnPOExample example);

    int updateByExample(@Param("record") ReturnPO record, @Param("example") ReturnPOExample example);

    void insertReturnBatch(
            @Param("returnPOList") List<ReturnPO> returnPOList,
            @Param("projectId") Integer projectId
    );
}