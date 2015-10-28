package com.edaisong.api.service.inter;

import com.edaisong.entity.ClienterForzen;
import com.edaisong.entity.common.PagedResponse;
import com.edaisong.entity.common.ResponseBase;
import com.edaisong.entity.req.ClienterForzenBalanceReq;
import com.edaisong.entity.req.ClienterUnfreezeReq;
import com.edaisong.entity.req.PagedClienterForzenReq;

/*
 * 冻结单相关接口(service)
 * 茹化肖
 * 2015年9月9日11:16:00
 * 
 * */
public interface IClienterForzenService {
	 /*
     * 获取冻结单列表
     * 茹化肖
     * 2015年9月9日11:08:27
     * */
    PagedResponse<ClienterForzen> getForzenList(PagedClienterForzenReq par);

	ResponseBase createForzenBalance(
			ClienterForzenBalanceReq clienterForzenBalanceReq);

	ResponseBase unfreezeClienterBalance(ClienterUnfreezeReq clienterUnfreezeReq);
}

