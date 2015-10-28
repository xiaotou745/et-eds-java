package com.edaisong.entity.resp;

import java.util.List;
import com.edaisong.entity.domain.DaySatisticsB;
import com.edaisong.entity.domain.DaySatisticsC;

/**
 * C端任务统计接口 返回值实体
 * @author WangXuDan
 * @date 20150910
 */
public class OrderStatisticsCResp {
	private int totalOrderCount;
	private double totalOrderCommission;
	private List<DaySatisticsC> datas;
	/**
	 * 订单总量
	 * @return
	 */
	public int getTotalOrderCount() {
		return totalOrderCount;
	}
	/**
	 * 订单总量
	 * @param totalOrderCount
	 */
	public void setTotalOrderCount(int totalOrderCount) {
		this.totalOrderCount = totalOrderCount;
	}
	/**
	 * 订单金额
	 * @return
	 */
	public Double getTotalOrderCommission() {
		return totalOrderCommission;
	}
	/**
	 * 佣金总金额
	 * @param totalOrderCommission
	 */
	public void setTotalOrderCommission(Double totalOrderCommission) {
		this.totalOrderCommission = totalOrderCommission;
	}
	/**
	 * 天数据列表
	 * @return
	 */
	public List<DaySatisticsC> getDatas() {
		return datas;
	}
	/**
	 * 天数据列表
	 * @param datas
	 */
	public void setDatas(List<DaySatisticsC> datas) {
		this.datas = datas;
	}

}
