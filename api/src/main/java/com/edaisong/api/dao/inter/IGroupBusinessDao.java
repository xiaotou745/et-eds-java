package com.edaisong.api.dao.inter;

import com.edaisong.entity.GroupBusiness;
import com.edaisong.entity.common.PagedResponse;
import com.edaisong.entity.domain.GroupBusinessModel;
import com.edaisong.entity.req.GroupBusinessReq;
import com.edaisong.entity.req.PagedGroupBusinessReq;

public interface IGroupBusinessDao { 
    
    int updateByPrimaryKey(GroupBusiness record);
    
    PagedResponse<GroupBusinessModel> getPageList(
			PagedGroupBusinessReq req);

	GroupBusinessModel getSingle(GroupBusinessReq gbr);

	int addGroupBusiness(GroupBusiness groupBusiness);

	int modifyGroupBusiness(GroupBusiness groupBusiness);
}