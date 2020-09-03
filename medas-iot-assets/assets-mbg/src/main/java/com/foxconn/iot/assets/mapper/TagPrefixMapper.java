package com.foxconn.iot.assets.mapper;

import com.foxconn.iot.assets.model.TagPrefix;
import com.foxconn.iot.assets.model.TagPrefixExample;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface TagPrefixMapper {
    long countByExample(TagPrefixExample example);

    int deleteByExample(TagPrefixExample example);

    int deleteByPrimaryKey(Long id);

    int insert(TagPrefix record);

    int insertSelective(TagPrefix record);

    List<TagPrefix> selectByExample(TagPrefixExample example);

    TagPrefix selectByPrimaryKey(Long id);

    int updateByExampleSelective(@Param("record") TagPrefix record, @Param("example") TagPrefixExample example);

    int updateByExample(@Param("record") TagPrefix record, @Param("example") TagPrefixExample example);

    int updateByPrimaryKeySelective(TagPrefix record);

    int updateByPrimaryKey(TagPrefix record);
}