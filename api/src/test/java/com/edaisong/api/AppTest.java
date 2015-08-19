package com.edaisong.api;

import java.math.BigDecimal;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.lang.Double;

import junit.framework.Assert;
import junit.framework.TestCase;

import com.edaisong.api.common.OrderPriceBaseProvider;
import com.edaisong.api.common.SpringBeanHelper;
import com.edaisong.api.service.inter.IOrderService;
import com.edaisong.core.util.JsonUtil;
import com.edaisong.entity.domain.BusiPubOrderTimeStatisticsModel;
import com.edaisong.entity.domain.BusinessOrderSummaryModel;
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

	OrderPriceBaseProvider baseCommissionService;


	public AppTest() {
		testService = SpringBeanHelper
				.getCustomBeanByType(IOrderService.class);
		baseCommissionService = (OrderPriceBaseProvider)SpringBeanHelper.getCustomBean("baseCommissionOrPriceService");
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
		model.setAmount(new Double(100));
		model.setBusinessCommission(new Double(10));
		model.setBusinessGroupId(7);
		model.setCommissionFixValue(new Double(5));
		model.setCommissionType(1);
		model.setDistribSubsidy(new Double(2.0));
		model.setStrategyId(4);
		
		Double ret = baseCommissionService.getCurrenOrderCommission(model);
		
	}
	
	public void testGetBusinessOrderSummary(){
		BusinessOrderSummaryModel model = testService.getBusinessOrderSummary(1812);
		//Assert.assertTrue(model.getName()!= null);
	}
	
	public void testGetBusiPubOrderTimeStatistics(){
		Calendar c = Calendar.getInstance();
		c.set(c.get(Calendar.YEAR), 1, c.get(Calendar.DATE), 0, 0, 0);
		Date startTime = c.getTime();
		c.set(c.get(Calendar.YEAR), 10, c.get(Calendar.DATE), 0, 0, 0);
		c.add(Calendar.DATE, 1);
		Date endTime = c.getTime();
		List<BusiPubOrderTimeStatisticsModel> models = testService.getBusiPubOrderTimeStatistics(1812,startTime,endTime);
		Assert.assertTrue(models.size() > 0);
	}
}
