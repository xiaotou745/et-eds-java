package com.edaisong.api_http.service.impl;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collector;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.edaisong.api.service.inter.IOrderRegionService;
import com.edaisong.api_http.service.inter.IOrderRegionHttpService;
import com.edaisong.core.enums.BusinessOrderEnum;
import com.edaisong.core.enums.returnenums.HttpReturnRnums;
import com.edaisong.entity.OrderRegion;
import com.edaisong.entity.common.HttpResultModel;
import com.edaisong.entity.req.OrderRegionReq;
import com.edaisong.entity.resp.OrderRegionResp; 
import com.edaisong.entity.resp.TwoOrderRegion;

@Service
public class OrderRegionHttpService implements IOrderRegionHttpService {

	@Autowired
	private IOrderRegionService iOrderRegionService;
	
	@Override
	public HttpResultModel<List<OrderRegionResp>> getOrderRegion(
			OrderRegionReq orderRegionReq) { 
		HttpResultModel<List<OrderRegionResp>> result=new HttpResultModel<List<OrderRegionResp>>();
		if(orderRegionReq.getBusinessId() <=0){
			result.setStatus(BusinessOrderEnum.BusinessIdEmpty.value());
			result.setMessage(BusinessOrderEnum.BusinessIdEmpty.desc());
			return result;
		}
		
		result.setStatus(HttpReturnRnums.Success.value());
		result.setMessage(HttpReturnRnums.Success.desc());
		List<OrderRegionResp> orderRegionRespList =  new ArrayList<OrderRegionResp>();
		
		List<OrderRegion> orderRegionList = iOrderRegionService.getOrderRegion(orderRegionReq);
		
		if(orderRegionList!=null && orderRegionList.size()>0){ 
			//获取一级区域信息
			List<OrderRegion> firstOrderRegionList=	orderRegionList.stream().filter(k->k.getParentid().equals(0)).collect(Collectors.toList()); 
			List<OrderRegion> selectTwoOrderRegionList = orderRegionList.stream().filter(m->m.getParentid() != 0).collect(Collectors.toList());
			if(firstOrderRegionList.size() !=9){
				result.setStatus(HttpReturnRnums.NoData.value());
				result.setMessage(HttpReturnRnums.NoData.desc());
				return result;
			}
			for (OrderRegion firserOrderRegion : firstOrderRegionList) { 
				OrderRegionResp orderRegionResp = new OrderRegionResp();
				orderRegionResp.setId(firserOrderRegion.getId());
				orderRegionResp.setRegionName(firserOrderRegion.getName()); 
				orderRegionRespList.add(orderRegionResp);		
			} 
			//获取二级区域信息
			for (OrderRegionResp firstOrderRegionResp : orderRegionRespList) { 
				List<TwoOrderRegion> twoOrderRegionList = new ArrayList<TwoOrderRegion>();  
				List<OrderRegion> twoOrderRegion=selectTwoOrderRegionList.stream().filter(i->i.getParentid().equals(firstOrderRegionResp.getId())).collect(Collectors.toList());
				for (OrderRegion orderRegion2 : twoOrderRegion) {
					TwoOrderRegion twoOrderRegion2 = new TwoOrderRegion();
					twoOrderRegion2.setId(orderRegion2.getId());
					twoOrderRegion2.setRegionName(orderRegion2.getName());
					twoOrderRegionList.add(twoOrderRegion2);
				}
				firstOrderRegionResp.setTwoOrderRegionList(twoOrderRegionList);
			}
			
		}else{
			result.setStatus(HttpReturnRnums.NoData.value());
			result.setMessage(HttpReturnRnums.NoData.desc());
		}
		result.setResult(orderRegionRespList);
		return result;   
	}

}