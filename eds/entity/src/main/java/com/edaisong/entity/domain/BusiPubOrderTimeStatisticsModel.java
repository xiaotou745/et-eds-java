package com.edaisong.entity.domain;

/**
 * 商家发布订单时间统计
 * @author pengyi
 * @date 20150819
 */
public class BusiPubOrderTimeStatisticsModel {
	private int hour;//小时
	private int pubCount;//发单数量
	public int getHour() {
		return hour;
	}
	public void setHour(int hour) {
		this.hour = hour;
	}
	public int getPubCount() {
		return pubCount;
	}
	public void setPubCount(int pubCount) {
		this.pubCount = pubCount;
	}
}
