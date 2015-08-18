package com.edaisong.api.dao.inter;
import java.util.List;






import com.edaisong.entity.PublicProvinceCity;
import com.edaisong.entity.domain.AreaModel;
import com.edaisong.entity.domain.OpenCityModel;

public interface IPublicProvinceCityDao {    
	/**
	 * 获取开放城市列表（非分页）
	 * @author CaoHeYang 
	 */
    List<OpenCityModel> getOpenCityList(String  cityName);
    
    /**
	  * 绑定开放城市    
	  * @author CaoHeYang 
	  * @param openCityCodeList 开放城市
	  * @Date 20150721
	  */
    boolean updateOpen(String openCityCodeList);
    
    /**
	  * 关闭开放城市    
	  * @author CaoHeYang 
	  * @param closeCityCodeList 关闭城市
	  * @Date 20150721
	  */
   boolean updateClose(String closeCityCodeList);
   
	/**
	 * 获取开通城市的省市区 
	 * @author CaoHeYang
	 * @Date 20150727
	 * @return 
	 */
	
    List<AreaModel> getOpenCitySql();
	/**
	 * 按照accountID获取二级开放城市列表
	 * @author zhaohailong
	 */
    List<AreaModel> getOpenCityListByAccountID(int accountID);
}