package com.foxconn.iot.assets.mapper;

import com.foxconn.iot.assets.model.EpcPrefix;
import com.foxconn.iot.assets.model.EpcPrefixExample;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface EpcPrefixMapper {
    long countByExample(EpcPrefixExample example);

    int deleteByExample(EpcPrefixExample example);

    int deleteByPrimaryKey(Long id);

    int insert(EpcPrefix record);

    int insertSelective(EpcPrefix record);

    List<EpcPrefix> selectByExample(EpcPrefixExample example);

    EpcPrefix selectByPrimaryKey(Long id);

    int updateByExampleSelective(@Param("record") EpcPrefix record, @Param("example") EpcPrefixExample example);

    int updateByExample(@Param("record") EpcPrefix record, @Param("example") EpcPrefixExample example);

    int updateByPrimaryKeySelective(EpcPrefix record);

    int updateByPrimaryKey(EpcPrefix record);
}