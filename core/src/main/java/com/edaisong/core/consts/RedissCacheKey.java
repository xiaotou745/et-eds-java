package com.edaisong.core.consts;


/**
 * redis 緩存 key 值
 * @author CaoHeYang
 * @modify pengyi
 */
public class RedissCacheKey {
	/**
	 * 获取开通城市缓存key
	 */
    public static final String Ets_Service_Provider_Common_GetOpenCity_New = "java_Ets_Service_Provider_Common_GetOpenCity_";
    public static final String LOGIN_COUNT_B = "java_LoginCount_B_";//商家登录次数
    public final static String LOGIN_COOKIE_KEY = "java_user_login_";//登录Cookie的key,对应redis的缓存key
    public static final String Order_TimeSpan = "java_Order_TimeSpan_";//商家发单时间戳
    
    public static final String Ets_Dao_GlobalConfig_GlobalConfigGet = "Ets_Dao_GlobalConfig_GlobalConfigGet_%s_";
}
