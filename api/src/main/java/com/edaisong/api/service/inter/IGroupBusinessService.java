package com.edaisong.api.service.inter;

import org.springframework.stereotype.Service;

import com.edaisong.entity.Business;
import com.edaisong.entity.GroupBusiness;
import com.edaisong.entity.common.PagedResponse;
import com.edaisong.entity.domain.GroupBusinessModel;
import com.edaisong.entity.req.GroupBusinessReq;
import com.edaisong.entity.req.PagedGroupBusinessReq;
import com.edaisong.entity.resp.BusinessLoginResp;
 
@Service
public interface IGroupBusinessService { 
	
	PagedResponse<GroupBusinessModel> getPageList(
			PagedGroupBusinessReq req);
	/**
	 * 集团商家登录
	 * @author hailongzhao
	 * @date 20150910
	 * @param 手机号
	 * @param 密码
	 * @return
	 */
	GroupBusiness login(String phoneNo,String password);
	GroupBusinessModel getSingle(GroupBusinessReq gbr);

	int addGroupBusiness(GroupBusiness groupBusiness);

	int modifyGroupBusiness(GroupBusiness groupBusiness);
}
