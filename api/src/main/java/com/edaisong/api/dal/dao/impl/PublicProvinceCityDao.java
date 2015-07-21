package com.edaisong.api.dal.dao.impl;


import com.edaisong.entity.Group;
import com.edaisong.entity.PublicProvinceCity;
import com.edaisong.entity.domain.OpenCityModel;

import java.util.HashMap;
import java.util.List;
import java.util.Map;





import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.edaisong.api.dal.dao.inter.IPublicProvinceCityDao;;




/**
 * 
 * @author CaoHeYang
 *
 */
@Repository
public class PublicProvinceCityDao implements IPublicProvinceCityDao{
	@Autowired
	private SqlSessionFactory superManReadOnlySqlServerSessionFactory;
	
	/**
	 * 
	 */
	@Override
	public int deleteByPrimaryKey(Integer code){
		return 0;
	}
	@Override
	public  int insert(PublicProvinceCity record){
		return 0;
	}
	@Override
	public  int insertSelective(PublicProvinceCity record)
	{
		return 0;
	}
	
	@Override
	public   PublicProvinceCity selectByPrimaryKey(Integer code)	{
		return new PublicProvinceCity();
	}
	@Override
	public   int updateByPrimaryKeySelective(PublicProvinceCity record)
	{
		return 0;
	}
	
	/**
	 * 
	 */
	@Override
	public   int updateByPrimaryKey(PublicProvinceCity record) {
		return 0;
	};
	
	/**
	 * 获取开放城市列表（非分页）
	 * @author CaoHeYang 
	 */
	@Override
	public  List<OpenCityModel> getOpenCityList(String  cityName) {
		SqlSession session = superManReadOnlySqlServerSessionFactory
				.openSession();
		try {
			Map<String, Object> paramMap = new HashMap<>();
			paramMap.put("cityName", cityName);
			List<OpenCityModel> list = session
					.selectList(
							"com.edaisong.api.dal.dao.inter.IPublicProvinceCityDao.getOpenCityList",
							paramMap);
			return list;
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		} finally {
			session.close();
		}
	};
	
	
	 /**
	  * 修改绑定城市    
	  * @author CaoHeYang 
	  * @param openCityCodeList 开放城市
	  * @param closeCityCodeList 关闭城市
	  * @Date 20150721
	  */
	@Override
   public boolean ModifyOpenCityByCode(String openCityCodeList, String closeCityCodeList)
   {		
		SqlSession session = superManReadOnlySqlServerSessionFactory
		.openSession();
		try {
			Map<String, Object> paramMap = new HashMap<>();
			paramMap.put("OpenCityCodeList", openCityCodeList);
			paramMap.put("MainCode", openCityCodeList.substring(0,openCityCodeList.indexOf(',')));
			int count = session
					.update(
							"com.edaisong.api.dal.dao.inter.IPublicProvinceCityDao.updateOpen",
							paramMap);
			return count>0;
		} catch (Exception e) {
			e.printStackTrace();
			return false;
		} finally {
			session.close();
		}
   }
}
