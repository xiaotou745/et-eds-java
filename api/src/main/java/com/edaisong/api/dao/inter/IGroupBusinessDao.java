package com.edaisong.api.dao.inter;

import com.edaisong.entity.Business;
import com.edaisong.entity.GroupBusiness;
import com.edaisong.entity.common.PagedResponse;
import com.edaisong.entity.req.PagedGroupBusinessReq;
import com.edaisong.entity.resp.BusinessLoginResp;

public interface IGroupBusinessDao { 
    int insert(GroupBusiness record);
 
    GroupBusiness selectByPrimaryKey(Integer id); 
    
    int updateByPrimaryKey(GroupBusiness record);
	PagedResponse<GroupBusiness> getBusinessList(PagedGroupBusinessReq req);
	/**
	 * 集团商家登录
	 * @author hailongzhao
	 * @date 20150910
	 * @param 手机号
	 * @param 密码
	 * @return
	 */
	GroupBusiness getByPhoneNoAndPwd(String phoneNo,String password);
}