package com.edaisong.api.dal.dao.impl;


import com.edaisong.entity.PublicProvinceCity;
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
	public  List<PublicProvinceCity> getOpenCityList(String  cityName) {
		return null;
	};
}
