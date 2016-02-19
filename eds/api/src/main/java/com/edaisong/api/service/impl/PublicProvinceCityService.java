package com.edaisong.api.service.impl;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.edaisong.api.redis.RedisService;
import com.edaisong.api.service.inter.IPublicProvinceCityService;
import com.edaisong.entity.common.ResponseBase;
import com.edaisong.entity.common.ResponseCode;
import com.edaisong.entity.domain.AreaModel;
import com.edaisong.entity.domain.AreaModelList;
import com.edaisong.entity.domain.OpenCityModel;
import com.edaisong.api.dao.inter.IPublicProvinceCityDao;
import com.edaisong.core.util.PropertyUtils;

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
			resetOpenCityListRedis(); // 都更新成功时 更新redis缓存
		}
		return modifyOpenCityResp;
	}

	/**
	 * 修改开发城市后更新Redis缓存
	 * 
	 * @author CaoHeYang
	 * @Date 20150727
	 */
	@Override
	public List<AreaModel> resetOpenCityListRedis() {
		List<AreaModel> opencitys = publicProvinceCityDao.getOpenCitySql();
		if (opencitys != null) {
			AreaModelList areaList = new AreaModelList();
			areaList.setAreaModels(opencitys);
			areaList.setVersion(PropertyUtils.getProperty("apiVersion", ""));
			// redisService.set(
			// RedissCacheKey.Ets_Service_Provider_Common_GetOpenCity_New,
			// JsonUtil.obj2string(areaList));
		}//TODO:注释掉的代码是否需要打开?
		return opencitys;
	}

	private List<AreaModel> getOpenCityListFromRedis() {
		// String jsonData = redisService.get(
		// RedissCacheKey.Ets_Service_Provider_Common_GetOpenCity_New,
		// String.class);
		// if (jsonData == null || jsonData.isEmpty()) {
		return resetOpenCityListRedis();
		// }
		// AreaModelList areaList = JsonUtil
		// .str2obj(jsonData, AreaModelList.class);
		// return areaList.getAreaModels();
		//TODO:注释掉的代码是否需要打开?
	}
	@Override
	public List<AreaModel> getOpenCityByJiBie(int jiBie)
	{
		List<AreaModel> list = getOpenCityListFromRedis();
		List<AreaModel> listnew = new ArrayList<AreaModel>();

		for (AreaModel item : list) {
			if (item.getJiBie() == jiBie) {
				listnew.add(item);
			}
		}
		return listnew;
	}
	/**
	 * 按照accountID获取二级开放城市列表
	 * @author zhaohailong
	 */
	@Override
	public List<AreaModel> getOpenCityListByAccountID(int accountID) {
		
		if (accountID == 0) {
			return getOpenCityByJiBie(3);
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
		List<AreaModel> listnew = new ArrayList<AreaModel>();

		for (AreaModel item : list) {
			if (item.getJiBie() == 4&&item.getParentId()==cityId) {
				listnew.add(item);
			}
		}
		return listnew;
	}
}
