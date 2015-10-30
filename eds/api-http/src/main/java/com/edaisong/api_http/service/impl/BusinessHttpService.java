package com.edaisong.api_http.service.impl;

import com.edaisong.api_http.service.inter.IBusinessHttpService;
import com.edaisong.entity.common.HttpResultModel;
import com.edaisong.entity.req.IsAllowInputMoneyReq;
/**
 *  商家相关 
 * @author CaoHeYang
 * @date 20151030
 */
public class BusinessHttpService implements IBusinessHttpService {

	/**
	 * 获取商家是否需要录入金额才可以发单 0 需要 1 不需要  默认0
	 * @author CaoHeYang
	 * @date 20151030
	 * @param par
	 * @return
	 */
	@Override
	public HttpResultModel<Integer> getIsAllowInputMoney(IsAllowInputMoneyReq par) {

		return null;
	}

}
