package com.foxconn.iot.assets.mapper;

import com.foxconn.iot.assets.model.TagPrefixCompany;
import com.foxconn.iot.assets.model.TagPrefixCompanyExample;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface TagPrefixCompanyMapper {
    long countByExample(TagPrefixCompanyExample example);

    int deleteByExample(TagPrefixCompanyExample example);

    int deleteByPrimaryKey(Long id);

    int insert(TagPrefixCompany record);

    int insertSelective(TagPrefixCompany record);

    List<TagPrefixCompany> selectByExample(TagPrefixCompanyExample example);

    TagPrefixCompany selectByPrimaryKey(Long id);

    int updateByExampleSelective(@Param("record") TagPrefixCompany record, @Param("example") TagPrefixCompanyExample example);

    int updateByExample(@Param("record") TagPrefixCompany record, @Param("example") TagPrefixCompanyExample example);

    int updateByPrimaryKeySelective(TagPrefixCompany record);

    int updateByPrimaryKey(TagPrefixCompany record);
}