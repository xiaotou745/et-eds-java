package com.edaisong.api.service.inter;

import java.util.List;

import com.edaisong.entity.AccountDeliveryRelation;

public interface IAccountDeliveryRelationService {
    int modifyAuthList(List<AccountDeliveryRelation> recordList);
    List<Integer>  getAuthorityCitys(int userId);
}
