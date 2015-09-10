package com.edaisong.api.dao.inter;

import com.edaisong.entity.GroupBusiness;

public interface IGroupBusinessDao { 
    int insert(GroupBusiness record);
 
    GroupBusiness selectByPrimaryKey(Integer id); 
    
    int updateByPrimaryKey(GroupBusiness record);
}