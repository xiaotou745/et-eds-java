package com.edaisong.entity.resp;

import java.io.Serializable;
import java.util.List;

import com.edaisong.entity.domain.DaySatisticsB;

/**
 * B端任务统计接口 返回值
 * @author CaoHeYang
 *@date 20150910
 */
public class OrderStatisticsBResp  implements Serializable{
	private int orderCount;
	private Double totalAmount;
	private int serviceClienterCount;
	private List<DaySatisticsB> datas;
	/**
	 * 订单量
	 * @return
	 */
	public int getOrderCount() {
		return orderCount;
	}
	/**
	 * 订单量
	 * @param orderCount
	 */
	public void setOrderCount(int orderCount) {
		this.orderCount = orderCount;
	}
	/**
	 * 订单金额
	 * @return
	 */
	public Double getTotalAmount() {
		return totalAmount;
	}
	/**
	 * 订单金额
	 * @param totalAmount
	 */
	public void setTotalAmount(Double totalAmount) {
		this.totalAmount = totalAmount;
	}
	/**
	 * 
	 * @return
	 */
	public int getServiceClienterCount() {
		return serviceClienterCount;
	}
	/**
	 * 服务骑士数量
	 * @param serviceClienterCount
	 */
	public void setServiceClienterCount(int serviceClienterCount) {
		this.serviceClienterCount = serviceClienterCount;
	}
	/**
	 * 天数据列表
	 * @return
	 */
	public List<DaySatisticsB> getDatas() {
		return datas;
	}
	/**
	 * 天数据列表
	 * @param datas
	 */
	public void setDatas(List<DaySatisticsB> datas) {
		this.datas = datas;
	}

}
