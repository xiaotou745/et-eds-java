package com.edaisong.api.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;

import com.edaisong.api.dal.dao.inter.IDeliveryCompanyDao;
import com.edaisong.api.dal.dao.inter.IPublicProvinceCityDao;
import com.edaisong.api.service.inter.IDeliveryCompanyService;
import com.edaisong.entity.DeliveryCompany;

/*
 * 获取物流公司
 */
public class DeliveryCompanyService implements IDeliveryCompanyService {
	@Autowired
	private  IDeliveryCompanyDao iDeliveryCompDao;
	public List<DeliveryCompany> GetDeliveryCompanyList(){
		return iDeliveryCompDao.GetDeliveryCompanyList();
	}
}
