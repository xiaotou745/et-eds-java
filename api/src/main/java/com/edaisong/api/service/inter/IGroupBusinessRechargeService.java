package com.edaisong.api.service.inter;

import com.edaisong.entity.GroupBusinessRecharge;

public interface IGroupBusinessRechargeService {
    int insert(GroupBusinessRecharge record);
    GroupBusinessRecharge getByOrderNo(String orderNO);
    int recharge(GroupBusinessRecharge record);
}
