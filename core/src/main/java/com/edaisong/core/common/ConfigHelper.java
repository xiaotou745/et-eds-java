package com.edaisong.core.common;

import com.edaisong.core.util.PropertyUtils;

public class ConfigHelper {

	/**
	 * 获取SOLR商品数据URL
	 * 
	 * @return SOLR商品数据URL
	 */
	public static String getSolrProductUrl() {
		return PropertyUtils.getProperty("solr.product.url", "http://192.168.3.14:8080/solr/bm_product");
	}

	/**
	 * 获取SOLR基础数据URL
	 * 
	 * @return SOLR基础数据URL
	 */
	public static String getSolrBasicUrl() {
		return PropertyUtils.getProperty("solr.basic.url", "http://192.168.3.14:8080/solr/bm_basic");
	}

	/**
	 * 获取SOLR问答数据URL
	 * 
	 * @return SOLR问答数据URL
	 */
	public static String getSolrAskUrl() {
		return PropertyUtils.getProperty("solr.ask.url", "http://192.168.3.14:8080/solr/bm_ask");
	}

	/**
	 * 获取搜索站点域名
	 * 
	 * @return
	 */
	public static String getSearchUrl() {
		return PropertyUtils.getProperty("search.url", "http://search.beimai.com");
	}

	public static String getOrderUrl() {
		return PropertyUtils.getProperty("order.url", "http://order.beimai.com");
	}

	/**
	 * 获取搜索URL地址
	 * 
	 * @return 搜索URL
	 */
	public static String getSearchAPIUrl() {
		return PropertyUtils.getProperty("search.api.url", "http://api.beimai.com/query");
	}

	/**
	 * 获取API接口域名URL
	 * 
	 * @return API接口域名URL
	 */
	public static String getAPIUrl() {
		return PropertyUtils.getProperty("api.url", "http://api.beimai.com");
	}

	/**
	 * 获取北迈官网地址
	 * 
	 * @return 北迈官网地址
	 */
	public static String getWWWUrl() {
		return PropertyUtils.getProperty("www.url", "http://www.beimai.com");
	}

	public static String getPassportUrl() {
		return PropertyUtils.getProperty("passport.url", "http://passport.beimai.com");
	}

	public static String getLoginUrl() {
		return PropertyUtils.getProperty("login.url", "http://passport.beimai.com");
	}

	/**
	 * 获取页面底部帮忙中心类型ID
	 * 
	 * @return
	 */
	public static String getPageHelperClassId() {
		return PropertyUtils.getProperty("bottom.articleClassId", "1");
	}

	/**
	 * 获取页面底部帮忙中心类型ID
	 * 
	 * @return
	 */
	public static String getArticleChar() {
		return PropertyUtils.getProperty("bottom.articleChar", "n");
	}

	/**
	 * 获取applicationName
	 * 
	 * @return
	 */
	public static String getAppName() {
		return PropertyUtils.getProperty("appName", "search");
	}

	/**
	 * 获取订单数量Url地址
	 * 
	 * @return
	 */
	public static String getOrderCountApiUrl() {
		return PropertyUtils.getProperty("user.orderCount.url",
				"http://www1.beimai.com/api/user.ashx/GetCurrentUserOrderCount");
	}

	/**
	 * 获取迷你购物车信息API
	 * 
	 * @return
	 */
	public static String getShopcarGetUrl() {
		return PropertyUtils.getProperty("shopcar.get.url",
				"http://www1.beimai.com/api/user.ashx/GetCurrentUserShopCars");
	}

	/**
	 * 获取当前用户信息接口地址
	 */
	public static String getUserInfoUrl() {
		return PropertyUtils.getProperty("user.get.url", "http://www1.beimai.com/api/user.ashx/GetCurrentUserInfo");
	}

	/**
	 * 获取用户拥有的车型接口地址？
	 * 
	 * @return
	 */
	public static String getOwnedCarsUrl() {
		return PropertyUtils.getProperty("ownedCarsUrl", "http://www1.beimai.com/api/user.ashx/GetOwnedCars");
	}
	
	/**
	 * 获取快速订购下单接口地址
	 * @return
	 */
	public static String getSaveFastOrderApi(){
		return PropertyUtils.getProperty("saveFastOrder", "http://www1.beimai.com/api/user.ashx/SaveInquiryrecode");
	}
	
	/**
	 * 获取关键字地址
	 * @return
	 */
	public static String getKeywordUrl(){
		return PropertyUtils.getProperty("getKeywordUrl", "http://www1.beimai.com/ajaxget/randsearchword.ashx");
	}
}
