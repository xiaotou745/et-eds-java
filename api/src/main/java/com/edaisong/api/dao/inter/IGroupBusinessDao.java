package com.edaisong.api.dao.inter;

import com.edaisong.entity.GroupBusiness;
import com.edaisong.entity.common.PagedResponse;
import com.edaisong.entity.domain.GroupBusinessModel;
import com.edaisong.entity.req.PagedGroupBusinessReq;

public interface IGroupBusinessDao { 
    int insert(GroupBusiness record);
 
    GroupBusiness selectByPrimaryKey(Integer id); 
    
    int updateByPrimaryKey(GroupBusiness record);
    
    PagedResponse<GroupBusinessModel> getPageList(
			PagedGroupBusinessReq req);
}