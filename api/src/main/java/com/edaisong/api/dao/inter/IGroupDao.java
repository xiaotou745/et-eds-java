package com.edaisong.api.dao.inter;

import java.util.List;

import com.edaisong.entity.Group;
import com.edaisong.entity.domain.GroupModel;
import com.edaisong.entity.req.GroupReq;

public interface IGroupDao {
    int deleteByPrimaryKey(Long id);

    int insert(Group record);

    int insertSelective(Group record);

    Group selectByPrimaryKey(Long id);

    int updateByPrimaryKeySelective(Group record);

    int updateByPrimaryKey(Group record);
    
    List<GroupModel> getGroupListByID(Long id);
    
    List<GroupModel> getGroupList(GroupReq req);
}