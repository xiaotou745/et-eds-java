package com.edaisong.entity.domain;

import java.math.BigDecimal;
import java.util.Date;

/**
 * 商家订单概览
 * @author pengyi
 * @date 20150818
 *
 */
public class BusinessOrderSummaryModel {
	private int unReceive;//待抢单
	private int finish;//已完成
	private int cancel;//已取消
	private int pickUp;//配送中
	private int received;//待取货
	private String name;//名称
	private BigDecimal balancePrice;//余额
	private Date lastLoginTime;//最后登录时间
	private String city;//所在城市
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public BigDecimal getBalancePrice() {
		return balancePrice;
	}
	public void setBalancePrice(BigDecimal balancePrice) {
		this.balancePrice = balancePrice;
	}
	public Date getLastLoginTime() {
		return lastLoginTime;
	}
	public void setLastLoginTime(Date lastLoginTime) {
		this.lastLoginTime = lastLoginTime;
	}
	public String getCity() {
		return city;
	}
	public void setCity(String city) {
		this.city = city;
	}
	public int getUnReceive() {
		return unReceive;
	}
	public void setUnReceive(int unReceive) {
		this.unReceive = unReceive;
	}
	public int getFinish() {
		return finish;
	}
	public void setFinish(int finish) {
		this.finish = finish;
	}
	public int getCancel() {
		return cancel;
	}
	public void setCancel(int cancel) {
		this.cancel = cancel;
	}
	public int getPickUp() {
		return pickUp;
	}
	public void setPickUp(int pickUp) {
		this.pickUp = pickUp;
	}
	public int getReceived() {
		return received;
	}
	public void setReceived(int received) {
		this.received = received;
	}
}
