package com.edaisong.api.dao.inter;

import com.edaisong.entity.Business;
import com.edaisong.entity.GroupBusiness;
import com.edaisong.entity.common.PagedResponse;
import com.edaisong.entity.domain.GroupBusinessModel;
import com.edaisong.entity.req.GroupBusinessReq;
import com.edaisong.entity.req.PagedGroupBusinessReq;
public interface IGroupBusinessDao { 
 
    /**
	 * 集团商家登录
	 * @author hailongzhao
	 * @date 20150910
	 * @param 手机号
	 * @param 密码
	 * @return
	 */
	GroupBusiness getByPhoneNoAndPwd(String phoneNo,String password);
	
	PagedResponse<GroupBusinessModel> getPageList(PagedGroupBusinessReq req);
	
	GroupBusinessModel getSingle(GroupBusinessReq gbr);

	int addGroupBusiness(GroupBusiness groupBusiness);

	int modifyGroupBusiness(GroupBusiness groupBusiness);
}