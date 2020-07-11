package com.foxconn.iot.assets.mapper;

import com.foxconn.iot.assets.model.UmsCompanyRelation;
import com.foxconn.iot.assets.model.UmsCompanyRelationExample;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface UmsCompanyRelationMapper {
    long countByExample(UmsCompanyRelationExample example);

    int deleteByExample(UmsCompanyRelationExample example);

    int deleteByPrimaryKey(Long id);

    int insert(UmsCompanyRelation record);

    int insertSelective(UmsCompanyRelation record);

    List<UmsCompanyRelation> selectByExample(UmsCompanyRelationExample example);

    UmsCompanyRelation selectByPrimaryKey(Long id);

    int updateByExampleSelective(@Param("record") UmsCompanyRelation record, @Param("example") UmsCompanyRelationExample example);

    int updateByExample(@Param("record") UmsCompanyRelation record, @Param("example") UmsCompanyRelationExample example);

    int updateByPrimaryKeySelective(UmsCompanyRelation record);

    int updateByPrimaryKey(UmsCompanyRelation record);
}