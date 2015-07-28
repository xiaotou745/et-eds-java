package com.edaisong.api.service.inter;

import java.util.List;

import com.edaisong.entity.PublicProvinceCity;
import com.edaisong.entity.domain.AreaModel;
import com.edaisong.entity.domain.OpenCityModel;
import com.edaisong.entity.resp.ModifyOpenCityResp;

/**
 * 省市区通用 业务逻辑层
 * 
 * @author CaoHeYang 20150720
 */
public interface IPublicProvinceCityService {

	/**
	 * 获取开放城市列表（非分页）
	 * 
	 * @author CaoHeYang
	 * @param cityName
	 *            城市名称
	 */
	List<OpenCityModel> getOpenCityList(String cityName);

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
	ModifyOpenCityResp ModifyOpenCityByCode(String openCityCodeList,
			String closeCityCodeList);

	/**
	 * 修改开发城市后更新Redis缓存
	 * 
	 * @author CaoHeYang
	 * @Date 20150727
	 */
	void ResetOpenCityListRedis();

	/**
	 * 获取开通城市 窦海超 2015年7月28日 17:37:30
	 * 
	 * @return
	 */
	List<AreaModel> GettOpenCity();
}
