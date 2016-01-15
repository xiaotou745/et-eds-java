package com.edaisong.api.dao.inter;

import com.edaisong.entity.AccountDeliveryRelation;

import java.util.List;



public interface IAccountDeliveryRelationDao {
    int modifyAuthList(List<AccountDeliveryRelation> recordList);
    List<Integer>  getAuthorityCitys(int userId);
}