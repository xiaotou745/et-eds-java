package com.edaisong.core.consts;

/**
 * 淘宝口碑外卖 常量
 * @author CaoHeYang
 * @date 20151112
 */
public class TaoBaoConsts {
	/**
	 * tmcuri
	 */
	public final static String TMCUri = "ws://mc.api.taobao.com/";
	/**
	 * uri
	 */
	public final static String Uri = "http://gw.api.taobao.com/router/rest";

	/**
	 * AppKey
	 */
	public final static String AppKey = "23264088";
	/**
	 * AppSecret
	 */
	public final static String AppSecret = "ce0f4f3668d039b6db74aa82904cca43";
	/**
	 * SessionKey
	 */
	public final static String SessionKey = "6100326184627d82ebfa6e1a6e44dfc0881cd93d40ee3222532754203";
	
	/**
	 * GroupName
	 */
	public final static String GroupName="default";
	
	/**
	 * 订单指派通知消息(TMC)
	 */
	public final static String OrderDispatch="taobao_waimai_OrderDispatch";
	/*
	 * 催单通知消息(TMC)
	 */
	public final static String OrderRemind ="taobao_waimai_OrderRemind";
	/**
	 *  确认接单接口(API)
	 */
	public final static String Ack="taobao.waimai.order.ack";
	/**
	 *  更新配送员信息接口（API）
	 */
	public final static String Update="taobao.waimai.delivery.update";
	/**
	 * 订单关闭通知消息(TMC)
	 */
	public final static String OrderClose="taobao_waimai_OrderClose";
	/**
	 *  取件（API）
	 */
	public final static String Pickup="taobao.waimai.delivery.pickup";
	/**
	 *妥投（API）
	 */
	public final static String Confirm="taobao.waimai.delivery.confirm";
	/**
	 * 更新配送员位置信息（API）
	 */
	public final static String LocationUpdate="taobao.waimai.delivery.location.update";
	/**
	 * 外部订单指派通知消息(TMC)
	 */
	public final static String OuterOrderDispatch="taobao_waimai_OuterOrderDispatch";
}
