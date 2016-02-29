package com.edaisong.api.service.impl;

import java.util.List;
import java.util.concurrent.TimeUnit;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.edaisong.api.dao.inter.IPublicProvinceCityDao;
import com.edaisong.api.redis.RedisService;
import com.edaisong.api.service.inter.IPublicProvinceCityService;
import com.edaisong.core.consts.RedissCacheKey;
import com.edaisong.core.enums.AreaLevel;
import com.edaisong.entity.common.ResponseBase;
import com.edaisong.entity.common.ResponseCode;
import com.edaisong.entity.domain.AreaModel;
import com.edaisong.entity.domain.OpenCityModel;

@Service
public class PublicProvinceCityService implements IPublicProvinceCityService {
	@Autowired
	private IPublicProvinceCityDao publicProvinceCityDao;
	@Autowired
	private RedisService redisService;

	/**
	 * 获取开放城市列表（非分页）
	 * 
	 * @author CaoHeYang
	 */
	@Override
	public List<OpenCityModel> getOpenCityList(String cityName) {
		return publicProvinceCityDao.getOpenCityList(cityName);
	};

	/**
	 * 修改绑定城市
	 * 
	 * @author CaoHeYang
	 * @param openCityCodeList
	 *            开放城市
	 * @param closeCityCodeList
	 *            关闭城市
	 * @Date 20150721
	 */
	@Override
	public ResponseBase modifyOpenCityByCode(String openCityCodeList,
			String closeCityCodeList) {
		boolean result1 = true, result2 = true;
		if (openCityCodeList != null && !openCityCodeList.isEmpty()) {
			result1 = publicProvinceCityDao.updateOpen(openCityCodeList);
		}
		if (closeCityCodeList != null && !closeCityCodeList.isEmpty()) {
			result2 = publicProvinceCityDao.updateClose(closeCityCodeList);
		}
		ResponseBase modifyOpenCityResp = new ResponseBase();
		if (result1 == false || result2 == false) {
			modifyOpenCityResp.setResponseCode(ResponseCode.SYSTEM_ERROR);
		} else {
			redisService.remove(RedissCacheKey.RR_PublicProvinceCity);
		}
		return modifyOpenCityResp;
	}
	private List<AreaModel> getOpenCityListFromRedis() {
		List<AreaModel> listdata = redisService.get(RedissCacheKey.RR_PublicProvinceCity,List.class);
		if (listdata==null||listdata.size()==0) {
			listdata = publicProvinceCityDao.getOpenCitySql();
			if (listdata!=null&&listdata.size()>0) {
				redisService.set(RedissCacheKey.RR_PublicProvinceCity, listdata,360,TimeUnit.DAYS);
			}
		}
		 return listdata;
	}
	@Override
	public List<AreaModel> getOpenCityByJiBie(AreaLevel jiBie)
	{
		List<AreaModel> list = getOpenCityListFromRedis();
		return list.stream().filter(k -> k.getJiBie() == jiBie.value())
				.collect(Collectors.toList());
	}
	/**
	 * 按照accountID获取二级开放城市列表
	 * @author zhaohailong
	 */
	@Override
	public List<AreaModel> getOpenCityListByAccountID(int accountID) {
		
		if (accountID == 0) {
			return getOpenCityByJiBie(AreaLevel.City);
		}else {
			return publicProvinceCityDao.getOpenCityListByAccountID(accountID);	
		}
	}
	/**
	 * 根据城市Id获取对应的区县列表
	 * @author zhaohailong
	 */
	@Override
	public List<AreaModel> getOpenCityDistrict(int cityId) {
		List<AreaModel> list = getOpenCityListFromRedis();
		return list
				.stream()
				.filter(k -> k.getJiBie() == AreaLevel.District.value()
						&& k.getParentId() == cityId)
				.collect(Collectors.toList());
	}
}
