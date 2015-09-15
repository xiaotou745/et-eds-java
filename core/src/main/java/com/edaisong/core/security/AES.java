package com.edaisong.core.security;

import java.util.Base64;

import javax.crypto.Cipher;
import javax.crypto.spec.SecretKeySpec;

/**
 * AES加解密
 * 
 * @author talver
 */
public class AES {
	final static String AES_KEY = "k;)*(+nmjdsf$#@d";

	/**
	 * AES加密
	 * 
	 * @param str
	 *            待加密字符串
	 * @return 加密后字符串
	 */
	public static String aesEncrypt(String str) {
		try {
			String password = AES_KEY;
			SecretKeySpec skeySpec = new SecretKeySpec(password.getBytes(), "AES");
			Cipher cipher = Cipher.getInstance("AES/ECB/PKCS5Padding");
			cipher.init(Cipher.ENCRYPT_MODE, skeySpec);
			String strTmp = "";//TODO :Base64.encodeToString(cipher.doFinal(str.getBytes()) );
			return strTmp;
		} catch (Exception e) {
			e.printStackTrace();
		}
		return str;
	}

	/**
	 * AES解密
	 * 
	 * @param str
	 *            待解密字符串
	 * @return 解密后字符串
	 */
	public static String aesDecrypt(String str) {
		try {
			String password = AES_KEY;
			SecretKeySpec skeySpec = new SecretKeySpec(password.getBytes(), "AES");
			Cipher cipher = Cipher.getInstance("AES/ECB/PKCS5Padding");
			cipher.init(Cipher.DECRYPT_MODE, skeySpec);
			String strTmp = "";//todo new String(cipher.doFinal(Base64.decode(str, Base64.DEFAULT)));
			return strTmp;
		} catch (Exception ex) {
			ex.printStackTrace();
		}
		return str;
	}

	public static void main(String[] args) {
		String str = "易代送";
		String encrypt = aesEncrypt(str);
		System.out.println("99999999999" + encrypt);
		System.out.println("11111111111" + aesDecrypt(encrypt));

	}
}