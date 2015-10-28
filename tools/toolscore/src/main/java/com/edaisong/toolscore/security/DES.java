package com.edaisong.toolscore.security;

import javax.crypto.Cipher;
import javax.crypto.SecretKey;
import javax.crypto.SecretKeyFactory;
import javax.crypto.spec.DESKeySpec;
import javax.crypto.spec.IvParameterSpec;

public class DES {

	private final static String DES = "DES";
	private final static String UTF8 = "UTF-8";
	private static String EncryptionKey = "meydarin";
	private static String EncryptionIV = "y3ee2h1a";

	public static String encrypt(String src) throws Exception {
		return encrypt(src, EncryptionKey);
	}

	public static String encrypt(String src, String key) throws Exception {
		byte[] srcBytes = src.getBytes(UTF8);
		byte[] keyBytes = key.getBytes(UTF8);
		byte[] resultBytes = encrypt(srcBytes, keyBytes, EncryptionIV.getBytes(UTF8));
		return toHexString(resultBytes);
	}

	private static byte[] encrypt(byte[] src, byte[] key, byte[] iv) throws Exception {
		// DES算法要求有一个可信任的随机数源
		// SecureRandom sr = new SecureRandom();

		// 从原始密匙数据创建DESKeySpec对象
		DESKeySpec desKeySpec = new DESKeySpec(key);

		// 创建一个密匙工厂，然后用它把DESKeySpec转换成一个SecretKey对象
		SecretKeyFactory keyFactory = SecretKeyFactory.getInstance(DES);
		SecretKey secretKey = keyFactory.generateSecret(desKeySpec);

		IvParameterSpec ivParam = new IvParameterSpec(iv);

		// Cipher对象实际完成加密操作
		Cipher cipher = Cipher.getInstance("DES/CBC/PKCS5Padding");

		// 用密匙初始化Cipher对象
		cipher.init(Cipher.ENCRYPT_MODE, secretKey, ivParam);
		// cipher.init(Cipher.ENCRYPT_MODE, secretKey, sr);// 或者用一个随机数源

		return cipher.doFinal(src);
	}

	/**
	 *
	 * @param src 数据源
	 * @param key 密钥，长度必须是8的倍数
	 * @return
	 * @throws Exception
	 */
	public static byte[] decrypt(byte[] src, byte[] key, byte[] ivs) throws Exception {
		// DES算法要求有一个可信任的随机数源
		// SecureRandom sr = new SecureRandom();

		// 从原始密匙数据创建一个DESKeySpec对象
		DESKeySpec desKeySpec = new DESKeySpec(key);
		// 创建一个密匙工厂，然后用它把DESKeySpec对象转换成一个SecretKey对象
		SecretKeyFactory keyFactory = SecretKeyFactory.getInstance(DES);
		SecretKey securekey = keyFactory.generateSecret(desKeySpec);

		IvParameterSpec ivParam = new IvParameterSpec(ivs);
		// Cipher对象实际完成解密操作
		Cipher cipher = Cipher.getInstance("DES/CBC/PKCS5Padding");
		// 用密匙初始化Cipher对象
		cipher.init(Cipher.DECRYPT_MODE, securekey, ivParam);
		// cipher.init(Cipher.ENCRYPT_MODE, secretKey, sr);// 或者用一个随机数源

		// 正式执行解密操作
		return cipher.doFinal(src);
	}

	public static String decrypt(String value) throws Exception
	{
		byte[] srcs = convertHexString(value);
		String DeString = new String(decrypt(srcs, EncryptionKey.getBytes(UTF8), EncryptionIV.getBytes(UTF8)), "utf-8");
		// return URLDecoder.decode(DeString);
		return DeString;
	}

	private static byte[] convertHexString(String ss) {
		byte digest[] = new byte[ss.length() / 2];
		for (int i = 0; i < digest.length; i++)
		{
			String byteString = ss.substring(2 * i, 2 * i + 2);
			int byteValue = Integer.parseInt(byteString, 16);
			digest[i] = (byte) byteValue;
		}
		return digest;
	}

	private static String toHexString(byte[] b) {
		StringBuffer hexString = new StringBuffer();
		for (int i = 0; i < b.length; i++) {
			String plainText = Integer.toHexString(0xff & b[i]);
			if (plainText.length() < 2)
				plainText = "0" + plainText;
			hexString.append(plainText);
		}
		return hexString.toString();
	}
}
