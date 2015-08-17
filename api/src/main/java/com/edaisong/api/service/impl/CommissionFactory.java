package com.edaisong.api.service.impl;

import org.springframework.stereotype.Service;

import com.edaisong.entity.domain.OrderCommission;


@Service
public class CommissionFactory {	

	  public static OrderPriceService GetCommission(int commissionFormulaMode)
      {
          switch (commissionFormulaMode)
          {
              case 0:
                  return new DefaultOrPriceService();
              case 1:
                  return new TimeOrPriceService();
              case 2:
                  return new BreakEvenPointOrPriceService();
              case 3:
                  return new AmountOrPriceService();
              case 4:
                  return new BaseCommissionOrPriceService();
              default:
                  return new DefaultOrPriceService();
          }

      }
}
