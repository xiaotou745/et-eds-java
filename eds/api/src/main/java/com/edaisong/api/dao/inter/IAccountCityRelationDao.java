package com.edaisong.api.dao.inter;

import java.util.List;

import com.edaisong.entity.AccountCityRelation;

public interface IAccountCityRelationDao {
    int deleteByPrimaryKey(Integer id);

    int insert(AccountCityRelation record);

    int insertSelective(AccountCityRelation record);

    AccountCityRelation selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(AccountCityRelation record);

    int updateByPrimaryKey(AccountCityRelation record);
    
    /**
	 * 获取后台用户可查看的城市集合
	 * @author CaoHeYang
	 * @date 20151125
	 * @param userId
	 * @return
	 */
    List<String>  getAuthorityCitys(int userId);
}