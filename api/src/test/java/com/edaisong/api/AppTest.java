package com.edaisong.api;

import java.lang.annotation.Annotation;
import java.lang.annotation.Documented;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;

import junit.framework.Test;
import junit.framework.TestCase;
import junit.framework.TestSuite;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.edaisong.api.business.SqlSessionFactoryPool;
import com.edaisong.api.service.inter.IOrderService;
import com.edaisong.core.util.JsonUtil;
import com.edaisong.entity.req.CancelOrderBusinessReq;
import com.edaisong.entity.req.OrderDetailBusinessReq;
import com.edaisong.entity.resp.CancelOrderBusinessResp;
import com.edaisong.entity.resp.OrderDetailBusinessResp;


/**
 * Unit test for simple App.
 */

public class AppTest 
    extends TestCase
{
 

	IOrderService testService;
	
	public AppTest(){
		testService =  SqlSessionFactoryPool
				.getCustomBeanByType(IOrderService.class);
	}
	
//    /**
//     * Create the test case
//     *
//     * @param testName name of the test case
//     */
//    public AppTest( String testName )
//    {
//        super( testName );
//    }
//
//    /**
//     * @return the suite of tests being tested
//     */
//    public static Test suite()
//    {
//        return new TestSuite( AppTest.class );
//    }
//
//    /**
//     * Rigourous Test :-)
//     */
//    public void testApp()
//    {
//        assertTrue( true );
//    }
//    
    
    /**
     * Rigourous Test :-)
     */

    public void testOrder()
    {
    	OrderDetailBusinessReq para=new OrderDetailBusinessReq();
    	para.setBusinessId(2008);
    	para.setOrderNo("2008150630165417002");
        OrderDetailBusinessResp res= testService.getOrderDetailBusiness(para);
    	System.out.println(JsonUtil.obj2string(res));
        assertTrue( true );
      
    }

    public void testCanelOrder()
    {
    	CancelOrderBusinessReq cancelOrderBusinessReq=new CancelOrderBusinessReq();
    	cancelOrderBusinessReq.setBusinessId(2008);
    	cancelOrderBusinessReq.setOrderId(143525);
    	cancelOrderBusinessReq.setOrderNo("2008150630165417002");
    	
        CancelOrderBusinessResp res= testService.cancelOrderBusiness(cancelOrderBusinessReq);	
    }
   
    
    public void testenum()
    {
    	

    	

    	
    }
    
    @Documented
    @Retention(RetentionPolicy.RUNTIME)
    public @interface Person{
        String name();
    }
    
	@Person(name="sdasddasda")
    public enum Cs{
    	@Person(name="sssssssss")
    	ss,
    	@Person(name="xxxxxx")
    	xxx,
    }
	
	@Person(name="Css")
	public   class  Css{
		
		
	}
}
