package com.edaisong.api.service.inter;

import java.util.List;

import com.edaisong.entity.DeliveryCompany;

public interface IDeliveryCompanyService {
	//获取物流公司
	List<DeliveryCompany> GetDeliveryCompanyList();
}
