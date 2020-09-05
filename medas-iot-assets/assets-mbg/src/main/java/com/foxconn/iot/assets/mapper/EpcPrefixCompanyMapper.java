package com.foxconn.iot.assets.mapper;

import com.foxconn.iot.assets.model.EpcPrefixCompany;
import com.foxconn.iot.assets.model.EpcPrefixCompanyExample;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface EpcPrefixCompanyMapper {
    long countByExample(EpcPrefixCompanyExample example);

    int deleteByExample(EpcPrefixCompanyExample example);

    int deleteByPrimaryKey(Long id);

    int insert(EpcPrefixCompany record);

    int insertSelective(EpcPrefixCompany record);

    List<EpcPrefixCompany> selectByExample(EpcPrefixCompanyExample example);

    EpcPrefixCompany selectByPrimaryKey(Long id);

    int updateByExampleSelective(@Param("record") EpcPrefixCompany record, @Param("example") EpcPrefixCompanyExample example);

    int updateByExample(@Param("record") EpcPrefixCompany record, @Param("example") EpcPrefixCompanyExample example);

    int updateByPrimaryKeySelective(EpcPrefixCompany record);

    int updateByPrimaryKey(EpcPrefixCompany record);
}