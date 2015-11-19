package com.edaisong.api.service.inter;

import java.util.List;

import com.edaisong.entity.PublicProvinceCity;
import com.edaisong.entity.common.ResponseBase;
import com.edaisong.entity.domain.AreaModel;
import com.edaisong.entity.domain.OpenCityModel;



/**
 * 省市区通用  业务逻辑层  
 * @author CaoHeYang
 * 20150720
 */
public interface IPublicProvinceCityService {

	/**
	 * 获取开放城市列表（非分页）
	 * @author CaoHeYang 
	 * @param cityName 城市名称
	 */
	 List<OpenCityModel> getOpenCityList(String  cityName);
	 
	 /**
	  * 修改绑定城市    
	  * @author CaoHeYang 
	  * @param openCityCodeList 开放城市
	  * @param closeCityCodeList 关闭城市
	  * @Date 20150721
	  */
	 ResponseBase modifyOpenCityByCode(String openCityCodeList, String closeCityCodeList);
	 
	 /**
	  * 修改开发城市后更新Redis缓存
	  * @author CaoHeYang
	  * @Date 20150727
	  */
	 List<AreaModel> resetOpenCityListRedis();
	 
	 /**
	  * 获取开通市
	  * 赵海龙
	  * 2015年7月29日 10:40:36
	  * */
	 List<AreaModel> getOpenCityByJiBie(int jiBie);
	 
	/**
	 * 按照accountID获取二级开放城市列表
	 * @author zhaohailong
	 */
	 List<AreaModel> getOpenCityListByAccountID(int accountID);
	 
	/**
	 * 根据城市Id获取对应的区县列表
	 * @author zhaohailong
	 */
	 List<AreaModel> getOpenCityDistrict(int cityId);
}
