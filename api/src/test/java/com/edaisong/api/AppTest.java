package com.edaisong.api;

import java.math.BigDecimal;

import junit.framework.TestCase;

import com.edaisong.api.business.SpringBeanHelper;
import com.edaisong.api.service.impl.OrderPriceService;
import com.edaisong.api.service.inter.IOrderService;
import com.edaisong.core.util.JsonUtil;
import com.edaisong.entity.domain.OrderCommission;
import com.edaisong.entity.req.CancelOrderBusinessReq;
import com.edaisong.entity.req.OrderDetailBusinessReq;
import com.edaisong.entity.resp.CancelOrderBusinessResp;
import com.edaisong.entity.resp.OrderDetailBusinessResp;

/**
 * Unit test for simple App.
 */

public class AppTest extends TestCase {

	IOrderService testService;
	OrderPriceService baseCommissionService;

	public AppTest() {
		testService = SpringBeanHelper
				.getCustomBeanByType(IOrderService.class);
		baseCommissionService = (OrderPriceService)SpringBeanHelper.getCustomBean("baseCommissionOrPriceService");
				//.getCustomBeanByType(OrderPriceService.class);
	}

	// /**
	// * Create the test case
	// *
	// * @param testName name of the test case
	// */
	// public AppTest( String testName )
	// {
	// super( testName );
	// }
	//
	// /**
	// * @return the suite of tests being tested
	// */
	// public static Test suite()
	// {
	// return new TestSuite( AppTest.class );
	// }
	//
	// /**
	// * Rigourous Test :-)
	// */
	// public void testApp()
	// {
	// assertTrue( true );
	// }
	//

	/**
	 * Rigourous Test :-)
	 */

	public void testOrder() {
		OrderDetailBusinessReq para = new OrderDetailBusinessReq();
		para.setBusinessId(2008);
		para.setOrderNo("2008150630165417002");
		OrderDetailBusinessResp res = testService.getOrderDetailBusiness(para);
		System.out.println(JsonUtil.obj2string(res));
		assertTrue(true);

	}

	public void testCanelOrder() {
//		BusinessBalanceRecordRecordType ss  =BusinessBalanceRecordRecordType.getEnum(1);
		CancelOrderBusinessReq cancelOrderBusinessReq = new CancelOrderBusinessReq();
		cancelOrderBusinessReq.setBusinessId(2008);
		cancelOrderBusinessReq.setOrderId(143525);
		cancelOrderBusinessReq.setOrderNo("2008150630165417002");

		CancelOrderBusinessResp res = testService
				.cancelOrderBusiness(cancelOrderBusinessReq);
	}


	public void testGetCurrenOrderCommission(){
		OrderCommission model = new OrderCommission();
		model.setAmount(new BigDecimal(100));
		model.setBusinessCommission(new BigDecimal(10));
		model.setBusinessGroupId(7);
		model.setCommissionFixValue(new BigDecimal(5));
		model.setCommissionType(1);
		model.setDistribSubsidy(new BigDecimal(2.0));
		model.setStrategyId(4);
		
		BigDecimal ret = baseCommissionService.getCurrenOrderCommission(model);
		
	}
}
