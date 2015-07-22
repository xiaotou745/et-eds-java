package com.edaisong.api.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.edaisong.api.service.inter.IPublicProvinceCityService;
import com.edaisong.entity.PublicProvinceCity;
import com.edaisong.entity.common.ResponseCode;
import com.edaisong.entity.domain.OpenCityModel;
import com.edaisong.entity.resp.ModifyOpenCityResp;
import com.edaisong.api.dal.dao.inter.IPublicProvinceCityDao;


@Service
public class PublicProvinceCityService implements IPublicProvinceCityService
{

	@Autowired
	 private  IPublicProvinceCityDao publicProvinceCityDao;
	 
	/**
	 * 获取开放城市列表（非分页）
	 * @author CaoHeYang 
	 */
	@Override
	public  List<OpenCityModel> getOpenCityList(String  cityName) {
		return publicProvinceCityDao.getOpenCityList(cityName);
	};

  
	 /**
	  * 修改绑定城市    
	  * @author CaoHeYang 
	  * @param openCityCodeList 开放城市
	  * @param closeCityCodeList 关闭城市
	  * @Date 20150721
	  */
	@Override
    public ModifyOpenCityResp ModifyOpenCityByCode(String openCityCodeList, String closeCityCodeList)
    {
		boolean result1=true,result2=true;
		if (openCityCodeList!=null&&!openCityCodeList.isEmpty()) {
			result1= publicProvinceCityDao.updateOpen(openCityCodeList);
		}
		if (closeCityCodeList!=null&&!closeCityCodeList.isEmpty()) {
			result2= publicProvinceCityDao.updateClose(closeCityCodeList);
		}
		ModifyOpenCityResp modifyOpenCityResp=new ModifyOpenCityResp();
		if (result1==false||result2==false) {
			modifyOpenCityResp.setResponseCode(ResponseCode.SYSTEM_ERROR);
		}
	    return modifyOpenCityResp; 
    }
}
