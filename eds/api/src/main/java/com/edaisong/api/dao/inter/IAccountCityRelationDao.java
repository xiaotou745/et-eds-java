package com.edaisong.api.dao.inter;

import java.util.List;

import com.edaisong.entity.AccountCityRelation;

public interface IAccountCityRelationDao {

    int modifyAuthList(List<AccountCityRelation> recordList);
    
    /**
	 * 获取后台用户可查看的城市集合
	 * @author CaoHeYang
	 * @date 20151125
	 * @param userId
	 * @return
	 */
    List<Integer>  getAuthorityCitys(int userId);
}