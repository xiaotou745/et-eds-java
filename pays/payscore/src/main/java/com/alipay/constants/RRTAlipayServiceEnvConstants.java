

/**
 * Alipay.com Inc.
 * Copyright (c) 2004-2014 All Rights Reserved.
 */
package com.alipay.constants;

/**
 * 支付宝服务窗环境常量（demo中常量只是参考，需要修改成自己的常量值）
 * 
 * @author taixu.zqq
 * @version $Id: AlipayServiceConstants.java, v 0.1 2014年7月24日 下午4:33:49 taixu.zqq Exp $
 */
public class RRTAlipayServiceEnvConstants {

	 /**支付宝公钥-从支付宝服务窗获取*/
    public static final String ALIPAY_PUBLIC_KEY = "MIGfMA0GCSqGSIb3DQEBAQUAA4GNADCBiQKBgQDDI6d306Q8fIfCOaTXyiUeJHkrIvYISRcc73s3vF1ZT7XN8RNPwJxo8pWaJMmvyTn9N4HQ632qJBVHf8sxHi/fEsraprwCtzvzQETrNRwVxLO5jVmRGi60j8Ue1efIlzPXV9je9mkjzOmdssymZkh2QhUrCmZYI/FCEa3/cNMW0QIDAQAB";
    
    /**签名编码-视支付宝服务窗要求*/
    public static final String SIGN_CHARSET      = "utf-8";

    /**字符编码-传递给支付宝的数据编码*/
    public static final String CHARSET           = "utf-8";

    /**签名类型-视支付宝服务窗要求*/
    public static final String SIGN_TYPE         = "RSA";
    
    public static final String PARTNER           = "2088911703660069";

    /** 服务窗appId  */
    //TODO !!!! 注：该appId必须设为开发者自己的服务窗id  这里只是个测试id
    public static final String APP_ID            = "2015081900222190";

    //开发者请使用openssl生成的密钥替换此处  请看文档：https://fuwu.alipay.com/platform/doc.htm#2-1接入指南
    //TODO !!!! 注：该私钥为测试账号私钥  开发者必须设置自己的私钥 , 否则会存在安全隐患 
    public static final String PRIVATE_KEY       = "MIICdgIBADANBgkqhkiG9w0BAQEFAASCAmAwggJcAgEAAoGBAKBsT+MdkRhwFcX0BoK4dAkQT3jDci6inccxY/CKtpOr+oevTaC6SW16yTLUpoRdwZOkuPfjkuL0G4+CpGI1IscVTaRGhnubMJKYO8zcahbUMn9zulHjBdAq9puf9HMtqfrhd/XdwkW9LDLj/zTTWAoGj3ZMOdkPnp/BgQe5T0WBAgMBAAECgYA9RsIJBX0rsXyPnVNzURN5dtJ0VWti5tiPgo0jD0kIBRPwCgUD1MRBXjpGATYNr2ZGiA/jF/k+WXHBkmxJ2vQKLoY++lvhC7ghp7TQFwAwVvz35FIsCpAUX2TQCEVtyavZHfC/4yX1/Nkpphf6r8JIC77rnpS7HeVW8boTp2tL9QJBAMvr7uQqwCkhmDmM8mXVbW+1QMUv197zNwQbzqJpapLF0mBA2bRN4uY09Qez3/8HC5oBaTmeobxWTweEnCckcisCQQDJZIQDw8CFoVX7AsDwlHv+yzPjz2Op2my7tbHT9Rou8atqldw8s9R1RT6LBy8oWQ3oHKANlN5ZLbKINqLCIU0DAkAvS6k8gi3PXFtR/b66n6WiIwfCtDX9H6vC6DAkuw5cvETuzhuwFeBqRB4Qi0eIfrnSHkGpe4FHjT0HIVqWOX3BAkBfgr0dL/QZK/ej8J1iO3lG0EYOr2d7wWw55aStehtt0g1Sojnty/dhmnJb6w9RWlK/FvxNFKIStxppgUfVO4fTAkEAxJE0C0mqGxqnUUJNnLK8DwYzsOdb4JjN/mvR63hNvpj3Dq/rhiFsVSSmHTbSxPdHixBuXwKMePtfmZA7CceGWA==";

    //TODO !!!! 注：该公钥为测试账号公钥  开发者必须设置自己的公钥 ,否则会存在安全隐患
    public static final String PUBLIC_KEY        = "MIGfMA0GCSqGSIb3DQEBAQUAA4GNADCBiQKBgQCgbE/jHZEYcBXF9AaCuHQJEE94w3Iuop3HMWPwiraTq/qHr02gukltesky1KaEXcGTpLj345Li9BuPgqRiNSLHFU2kRoZ7mzCSmDvM3GoW1DJ/c7pR4wXQKvabn/RzLan64Xf13cJFvSwy4/8001gKBo92TDnZD56fwYEHuU9FgQIDAQAB";

    /**支付宝网关*/
    public static final String ALIPAY_GATEWAY    = "https://openapi.alipay.com/gateway.do";

    /**授权访问令牌的授权类型*/
    public static final String GRANT_TYPE        = "authorization_code";
}