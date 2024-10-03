package com.truemen.api.chat.infrastructure.dao;

import org.apache.ibatis.annotations.Mapper;

import com.truemen.api.chat.infrastructure.po.Groups;

import java.util.List;

/** 

 */
@Mapper
public interface IGroupsDao {

    Groups queryGroupsById(String groupsId);

}
