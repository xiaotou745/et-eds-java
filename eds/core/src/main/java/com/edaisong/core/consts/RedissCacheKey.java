package com.edaisong.core.consts;


/**
 * redis 緩存 key 值
 * @author CaoHeYang
 * @modify pengyi
 */
public class RedissCacheKey {
	/**
	 * 获取开通城市缓存key
	 * redisservice中会统一增加java前缀和版本前缀
	 */
    public static final String Ets_Service_Provider_Common_GetOpenCity_New = "Ets_Service_Provider_Common_GetOpenCity_";
    public static final String LOGIN_COUNT_B = "LoginCount_B_";//商家登录次数
    public final static String Business_LOGIN_COOKIE = "Business_login_";//登录Cookie的key,对应redis的缓存key
    public final static String GroupBusiness_LOGIN_COOKIE = "GroupBusiness_login_";
    public static final String Order_TimeSpan = "jOrder_TimeSpan_";//商家发单时间戳
    public static final String Menu_Auth = "Menu_Auth_";//用户有权限的菜单
    public static final String GlobalConfig_Key ="GlobalConfig_%s_0";
    
    public static final String SSCancelOrder = "SSCancelOrder";
  /// <summary>
    /// 商家端注册
    /// </summary>
    public static final String RegisterCount_B = "RegisterCount_B_%s";
    public static final String HttpQuartz_Key = "HttpQuartz_Key_";//HttpQuartz,redis锁 ，防止服务重复执行
 
    /**
     *  商家端找回密码缓存KEY
     */
    public static final String  CheckCodeFindPwd_B = "CheckCodeFindPwd_B_%s";
    /**
     * 商家注册缓存key
     */
    public static final String PostRegisterInfo_B ="PostRegisterInfo_B_%s";
    /**
     * B端修改绑定手机号验证当前手机号
     */
    public static final String Business_SendCode_ModifyPhone ="RR_Business_SendCode_ModifyPhone__%s";
    /**
     * 修改绑定手机号验证新手机号
     */
    public static final String Business_SendCode_ModifyPhoneNewPhone ="RR_Business_SendCode_ModifyPhoneNewPhone__%s";
}
