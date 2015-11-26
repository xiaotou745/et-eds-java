package com.edaisong.api.service.inter;

import java.util.List;

public interface IAccountCityRelationService {
	 /**
		 * 获取后台用户可查看的城市集合
		 * @author CaoHeYang
		 * @date 20151125
		 * @param userId
		 * @return
		 */
	    List<String>  getAuthorityCitys(int userId);
}
