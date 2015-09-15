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
			//cipher.doFinal(str.getBytes()), Base64.DEFAULT
			byte[] bs=cipher.doFinal(Base64.getEncoder().encode(str.getBytes()));
			String strTmp = new String(bs);
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
			byte [] bs= Base64.getDecoder().decode(str);
			String strTmp = new String(cipher.doFinal(bs));
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