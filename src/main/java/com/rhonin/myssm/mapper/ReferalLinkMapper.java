package com.rhonin.myssm.mapper;

import com.rhonin.myssm.pojo.ReferalLink;
import com.rhonin.myssm.pojo.ReferalLinkExample;

import java.util.List;

public interface ReferalLinkMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(ReferalLink record);

    int insertSelective(ReferalLink record);

    List<ReferalLink> selectByExample(ReferalLinkExample example);

    ReferalLink selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(ReferalLink record);

    int updateByPrimaryKey(ReferalLink record);
}