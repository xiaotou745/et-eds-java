package com.edaisong.api.service.impl;

import java.math.BigDecimal;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.edaisong.api.dal.dao.inter.IAccountDao;
import com.edaisong.api.service.inter.IAccountService;
import com.edaisong.entity.Account;
import com.edaisong.entity.common.PagedResponse;
import com.edaisong.entity.req.AccountReq;
import com.edaisong.entity.resp.AccountResp;

@Service
public class OrderSettleMoneyService{
	
    public static   float GetSettleMoney(float amount, float businessCommission, 
    		float commissionFixValue,int ordercount, float distribSubsidy,int orderform)
        {    	
 
            if (orderform > 0)  //第三方订单 不考虑外送费
            {
                distribSubsidy =0;
            }
                            
            
          return  (float) (amount*businessCommission*0.01 + (commissionFixValue + distribSubsidy)*ordercount);
        }
}
