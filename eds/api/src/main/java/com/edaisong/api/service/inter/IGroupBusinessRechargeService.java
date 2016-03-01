package com.edaisong.api.service.inter;

import java.util.List;

import com.edaisong.entity.GroupBusinessRecharge;
import com.edaisong.entity.common.PagedResponse;
import com.edaisong.entity.domain.GroupBusinessBalance;
import com.edaisong.entity.req.PagedGroupBusinessRechargeReq;

public interface IGroupBusinessRechargeService {
    int insert(GroupBusinessRecharge record);
    GroupBusinessRecharge getByOrderNo(String orderNO);
    int recharge(GroupBusinessRecharge record);
    List<GroupBusinessBalance> getGroupBalance(int groupBusinessID); 
    PagedResponse<GroupBusinessRecharge> getGroupBusinessRechargelist(PagedGroupBusinessRechargeReq search);
}
