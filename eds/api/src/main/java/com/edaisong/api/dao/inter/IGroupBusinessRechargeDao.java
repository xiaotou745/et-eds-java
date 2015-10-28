package com.edaisong.api.dao.inter;

import java.util.List;

import com.edaisong.entity.GroupBusinessRecharge;
import com.edaisong.entity.domain.GroupBusinessBalance;

public interface IGroupBusinessRechargeDao {
    int insert(GroupBusinessRecharge record);

    int update(GroupBusinessRecharge record);
    GroupBusinessRecharge getByOrderNo(String orderNO);
    List<GroupBusinessBalance> getGroupBalance(int groupBusinessID);
}