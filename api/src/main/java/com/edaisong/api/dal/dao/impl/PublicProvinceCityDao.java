package com.edaisong.api.dal.dao.impl;

import com.edaisong.entity.PublicProvinceCity;
import com.edaisong.entity.domain.OpenCityModel;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import org.springframework.stereotype.Repository;
import com.edaisong.api.dal.dao.inter.IPublicProvinceCityDao;

/**
 * 
 * @author CaoHeYang
 *
 */
@Repository
public class PublicProvinceCityDao extends DaoBase implements
		IPublicProvinceCityDao {

	/**
	 * 
	 */
	@Override
	public int deleteByPrimaryKey(Integer code) {
		return 0;
	}

	@Override
	public int insert(PublicProvinceCity record) {
		return 0;
	}

	@Override
	public int insertSelective(PublicProvinceCity record) {
		return 0;
	}

	@Override
	public PublicProvinceCity selectByPrimaryKey(Integer code) {
		return new PublicProvinceCity();
	}

	@Override
	public int updateByPrimaryKeySelective(PublicProvinceCity record) {
		return 0;
	}

	/**
	 * 
	 */
	@Override
	public int updateByPrimaryKey(PublicProvinceCity record) {
		return 0;
	};

	/**
	 * 获取开放城市列表（非分页）
	 * 
	 * @author CaoHeYang
	 */
	@Override
	public List<OpenCityModel> getOpenCityList(String cityName) {
		Map<String, Object> paramMap = new HashMap<>();
		paramMap.put("cityName", cityName);
		List<OpenCityModel> list = getReadOnlySqlSessionUtil()
				.selectList(
						"com.edaisong.api.dal.dao.inter.IPublicProvinceCityDao.getOpenCityList",
						paramMap);
		return list;

	};

	/**
	 * 绑定开放城市
	 * 
	 * @author CaoHeYang
	 * @param openCityCodeList
	 *            开放城市
	 * @Date 20150721
	 */
	@Override
	public boolean updateOpen(String openCityCodeList) {
		Map<String, Object> paramMap = new HashMap<>();
		paramMap.put("OpenCityCodeList", openCityCodeList);
		paramMap.put("MainCode",
				openCityCodeList.substring(0, openCityCodeList.indexOf(',')));
		int count = getMasterSqlSessionUtil()
				.update("com.edaisong.api.dal.dao.inter.IPublicProvinceCityDao.updateOpen",
						paramMap);
		return count > 0;
	}

	/**
	 * 关闭开放城市
	 * 
	 * @author CaoHeYang
	 * @param closeCityCodeList
	 *            关闭城市
	 * @Date 20150721
	 */
	@Override
	public boolean updateClose(String closeCityCodeList) {
		Map<String, Object> paramMap = new HashMap<>();
		paramMap.put("OpenCityCodeList", closeCityCodeList);
		paramMap.put("MainCode",
				closeCityCodeList.substring(0, closeCityCodeList.indexOf(',')));
		int count = getMasterSqlSessionUtil()
				.update("com.edaisong.api.dal.dao.inter.IPublicProvinceCityDao.updateClose",
						paramMap);
		return count > 0;

	}
}
