package com.edaisong.edsservice.service;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStreamWriter;
import java.lang.management.ManagementFactory;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;

import com.edaisong.api.dao.inter.IOrderDao;
import com.edaisong.api.service.inter.IOrderChildService;
import com.edaisong.api.service.inter.IOrderService;
import com.edaisong.core.util.SpringBeanHelper;
import com.edaisong.entity.req.OrderChildCancelReq;
import com.edaisong.core.util.ParseHelper;
public class OrderService {
/*	IOrderService orderService = (IOrderService) SpringBeanHelper
			.getCustomBean("orderService");*/
	IOrderChildService orderChildService = (IOrderChildService) SpringBeanHelper
			.getCustomBean("orderChildService");
	
	/**
	 * 超时取消订单服务
	 * 
	 * @author 窦海超
	 * @throws IOException
	 * @date 2015年11月5日 11:03:18
	 */
	public void outTimeCanelOrder() throws IOException {
		// orderService.outTimeCanelOrder();		
		 
		OrderChildCancelReq req=new OrderChildCancelReq(); 
		Date date=new Date();
		Calendar calendar = new GregorianCalendar();
		calendar.setTime(date);
		calendar.add(calendar.DATE,-1);//把日期往后增加一天.整数往后推,负数往前移动
		Date startDate=calendar.getTime(); //这个时间就是日期往后推一天的结果 
		SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd");
		req.setStartTime(formatter.format(startDate));		
		req.setEndTime(ParseHelper.ToDateString(date,"yyyy-MM-dd"));
		
		orderChildService.cancelOrderChild(req);
		System.out.println("================");
	}

}
