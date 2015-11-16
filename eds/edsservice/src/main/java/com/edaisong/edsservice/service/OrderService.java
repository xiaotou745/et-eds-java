package com.edaisong.edsservice.service;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStreamWriter;
import java.lang.management.ManagementFactory;
import java.text.SimpleDateFormat;
import java.util.Date;

import org.apache.log4j.Logger;

import com.edaisong.api.service.inter.IOrderService;
import com.edaisong.core.util.SpringBeanHelper;

public class OrderService {
	IOrderService orderService = (IOrderService) SpringBeanHelper
			.getCustomBean("orderService");

	/**
	 * 超时取消订单服务
	 * 
	 * @author 窦海超
	 * @throws IOException
	 * @date 2015年11月5日 11:03:18
	 */
	public void outTimeCanelOrder() throws IOException {
		// orderService.outTimeCanelOrder();

		System.out.println("================");
	}

}
