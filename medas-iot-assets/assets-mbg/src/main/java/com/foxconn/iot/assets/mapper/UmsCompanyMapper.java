package com.foxconn.iot.assets.mapper;

import com.foxconn.iot.assets.model.UmsCompany;
import com.foxconn.iot.assets.model.UmsCompanyExample;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface UmsCompanyMapper {
    long countByExample(UmsCompanyExample example);

    int deleteByExample(UmsCompanyExample example);

    int deleteByPrimaryKey(Long id);

    int insert(UmsCompany record);

    int insertSelective(UmsCompany record);

    List<UmsCompany> selectByExample(UmsCompanyExample example);

    UmsCompany selectByPrimaryKey(Long id);

    int updateByExampleSelective(@Param("record") UmsCompany record, @Param("example") UmsCompanyExample example);

    int updateByExample(@Param("record") UmsCompany record, @Param("example") UmsCompanyExample example);

    int updateByPrimaryKeySelective(UmsCompany record);

    int updateByPrimaryKey(UmsCompany record);
}