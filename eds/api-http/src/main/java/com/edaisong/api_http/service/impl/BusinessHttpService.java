package com.edaisong.api_http.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.edaisong.api.service.inter.IBusinessService;
import com.edaisong.api_http.service.inter.IBusinessHttpService;
import com.edaisong.core.enums.ClienterBindBusinessEnum;
import com.edaisong.entity.common.HttpResultModel;
import com.edaisong.entity.domain.BindClienterBusiness;
import com.edaisong.entity.req.IsAllowInputMoneyReq;

/**
 * 商家相关
 * 
 * @author CaoHeYang
 * @date 20151030
 */
@Service
public class BusinessHttpService implements IBusinessHttpService {
	/**
	 * 商家
	 */
	@Autowired
	private IBusinessService businessService;

	/**
	 * 获取商家是否需要录入金额才可以发单 0 需要 1 不需要 默认0
	 * 
	 * @author CaoHeYang
	 * @date 20151030
	 * @param par
	 * @return
	 */
	@Override
	public HttpResultModel<Integer> getIsAllowInputMoney(IsAllowInputMoneyReq par) {
		HttpResultModel<Integer> result = new HttpResultModel<Integer>();
		result.setResult(businessService.getIsAllowInputMoney(par));
		return result;
	}

	@Override
	public HttpResultModel<Object> bindClienter(
			BindClienterBusiness bindClienterBusiness) {
	   HttpResultModel<Object> result = new HttpResultModel<Object>(); 
	   result.setStatus(ClienterBindBusinessEnum.Success.value());
	   result.setMessage(ClienterBindBusinessEnum.Success.desc());
	    
	   boolean b = businessService.getClienterBind(bindClienterBusiness);
	   if(!b){
		   int bindResult =  businessService.bindClienter(bindClienterBusiness);
		   if(bindResult<=0){
			   result.setStatus(ClienterBindBusinessEnum.Fail.value());
			   result.setMessage(ClienterBindBusinessEnum.Fail.desc());
		   }
	   }else{
		   result.setStatus(ClienterBindBusinessEnum.HadBind.value());
		   result.setMessage(ClienterBindBusinessEnum.HadBind.desc());
	   } 
	   return result;
	   
	}

}
