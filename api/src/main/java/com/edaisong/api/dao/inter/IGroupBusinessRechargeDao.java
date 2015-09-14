package com.edaisong.api.dao.inter;

import com.edaisong.entity.GroupBusinessRecharge;

public interface IGroupBusinessRechargeDao {
    int insert(GroupBusinessRecharge record);

    int update(GroupBusinessRecharge record);
    GroupBusinessRecharge getByOrderNo(String orderNO);
}