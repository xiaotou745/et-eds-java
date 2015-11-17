package com.edaisong.core.enums;

public enum SuperPlatform {
	/**
	 * 商家
	 */
	Business(0, "商家"),
	/**
	 * 骑士
	 */
	Clienter(1, "骑士"),
	/**
	 * 服务平台
	 */
	ServicePlatform(2, "服务平台"),
	/**
	 * 管理后台
	 */
	ManagementBackGround(3, "管理后台"),
	/**
	 * 第三方对接平台
	 */
	ThirdParty(4, "第三方对接平台"),
	/**
	 * 骑士高速版
	 */
	FastClienter(5, "骑士高速版"),
	/**
	 * 区域Api发单
	 */
	NewApiPush(101, "区域Api发单"),
	/**
	 * 区域Api骑士抢单
	 */
	NewApiReceive(102, "区域Api骑士抢单"),
	/**
	 * 区域Api骑士取货
	 */
	NewApiConfirmtake(103, "区域Api骑士取货"),
	/**
	 * 区域Api骑士完成订单
	 */
	NewApiComplete(104, "区域Api骑士完成订单"),	
	/**
	 * 区域后台发单
	 */
	NewPlatformPush(201, "区域后台发单"),
	/**
	 * 区域后台审核通过
	 */
	NewPlatformAuditThrough(202, "区域后台审核通过"),
	/**
	 * 区域后台审核拒绝
	 */
	NewPlatformAuditRejection(203, "区域后台审核拒绝"),
	/**
	 * 区域系统取消订单
	 */
	NewSystemCancel(301, "区域系统取消订单");
	
	private int value = 0;
	private String desc;
	private SuperPlatform(int value, String desc) { // 必须是private的，否则编译错误
		this.value = value;
		this.desc = desc;
	}
	public int value() {
		return this.value;
	}
	public String desc() {
		return this.desc;
	}

	public static SuperPlatform getEnum(int index) {
		for (SuperPlatform c : SuperPlatform.values()) {
			if (c.value() == index) {
				return c;
			}
		}
		return null;
	}
}
