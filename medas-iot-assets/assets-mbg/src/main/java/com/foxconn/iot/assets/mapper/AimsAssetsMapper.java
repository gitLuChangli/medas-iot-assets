package com.foxconn.iot.assets.mapper;

import com.foxconn.iot.assets.model.AimsAssets;
import com.foxconn.iot.assets.model.AimsAssetsExample;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface AimsAssetsMapper {
    long countByExample(AimsAssetsExample example);

    int deleteByExample(AimsAssetsExample example);

    int deleteByPrimaryKey(Long id);

    int insert(AimsAssets record);

    int insertSelective(AimsAssets record);

    List<AimsAssets> selectByExample(AimsAssetsExample example);

    AimsAssets selectByPrimaryKey(Long id);

    int updateByExampleSelective(@Param("record") AimsAssets record, @Param("example") AimsAssetsExample example);

    int updateByExample(@Param("record") AimsAssets record, @Param("example") AimsAssetsExample example);

    int updateByPrimaryKeySelective(AimsAssets record);

    int updateByPrimaryKey(AimsAssets record);
}