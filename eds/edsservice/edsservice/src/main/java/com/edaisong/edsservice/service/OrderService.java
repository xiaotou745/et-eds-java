package com.edaisong.edsservice.service;

import com.renrentui.renrenapi.service.inter.IOrderService;
import com.renrentui.renrencore.util.SpringBeanHelper;

public class OrderService {
	 IOrderService orderService = (IOrderService)SpringBeanHelper.getCustomBean("orderService");
		/**
		 * 超时取消订单服务
		 * 
		 * @author 窦海超
		 * @date 2015年11月5日 11:03:18
		 */
		public void outTimeCanelOrder() {
			orderService.outTimeCanelOrder();
			System.out.println("================");
		}
}
