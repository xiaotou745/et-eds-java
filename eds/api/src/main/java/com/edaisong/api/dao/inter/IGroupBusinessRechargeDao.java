package com.edaisong.api.dao.inter;

import java.util.List;

import com.edaisong.entity.GroupBusinessRecharge;
import com.edaisong.entity.common.PagedResponse;
import com.edaisong.entity.domain.BusinessBalanceRecordModel;
import com.edaisong.entity.domain.GroupBusinessBalance;
import com.edaisong.entity.req.PagedBusinessBalanceRecordReq;
import com.edaisong.entity.req.PagedGroupBusinessRechargeReq;

public interface IGroupBusinessRechargeDao {
    int insert(GroupBusinessRecharge record);

    int update(GroupBusinessRecharge record);
    GroupBusinessRecharge getByOrderNo(String orderNO);
    List<GroupBusinessBalance> getGroupBalance(int groupBusinessID);
    
	PagedResponse<GroupBusinessRecharge> getGroupBusinessRechargelist(PagedGroupBusinessRechargeReq search);
}