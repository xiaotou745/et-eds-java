package com.edaisong.core.util;

import java.util.Date;
import java.util.Random;

/**
 * 单号生成帮助类
 * 
 * @author CaoHeYang
 * @Date 20150824
 */
public class OrderNoHelper {

	/**
	 * 订单号生成 获取随机部分
	 * 
	 * @author CaoHeYang
	 * @Date 20150824
	 * @param len
	 * @return
	 */
	public static String createRandomData(int len) {
		int length = len - 1;
		int[] randMembers = new int[length];
		int[] validateNums = new int[length];
		StringBuilder validateNumberStr = new StringBuilder();
		// 生成起始序列值
		long milli = System.currentTimeMillis() + 8 * 3600 * 1000;
		long seekSeek = (milli * 10000) + 621355968000000000L;
		Random seekRand = new Random(seekSeek);
		int beginSeek = seekRand.nextInt(Integer.MAX_VALUE - length * 10000)
				% (Integer.MAX_VALUE - length * 10000 + 1);
		int[] seeks = new int[length];
		for (int i = 0; i < length; i++) {
			beginSeek += 10000;
			seeks[i] = beginSeek;
		}
		// 生成随机数字
		for (int i = 0; i < length; i++) {
			Random rand = new Random(seeks[i]);
			int pownum = 1 * (int) Math.pow(10, length);
			randMembers[i] = rand.nextInt(Integer.MAX_VALUE)
					% (Integer.MAX_VALUE - pownum + 1) + pownum;
		}
		// 抽取随机数字
		for (int i = 0; i < length; i++) {
			String numStr = randMembers[i] + "";
			int numLength = numStr.length();
			Random rand = new Random();
			int numPosition = rand.nextInt(numLength - 1) % (numLength);
			validateNums[i] = Integer.parseInt(numStr.substring(numPosition,
					numPosition + 1));
		}
		return validateNumberStr + "";
	}

	/**
	 * 截取timespan做为订单号
	 * 
	 * @author CaoHeYang
	 * @Date 20150824
	 * @param timeSpan
	 *            时间戳
	 * @param splitLen
	 *            长度
	 * @return
	 */
	public static String splitTimeSpan(String timeSpan, int splitLen) {
		if (timeSpan == null || timeSpan.isEmpty()
				|| timeSpan.length() < splitLen) {
			return "";
		}
		return timeSpan.substring(timeSpan.length() - splitLen);
	}

	/**
	 * 根据userId+时间+时间戳+随机数 生成订单号(15位)
	 * 
	 * @author CaoHeYang
	 * @Date 20150824
	 * @param userId
	 * @param timeSpan
	 *            时间戳
	 * @return
	 */
	public static String generateOrderCode(int userId, String timeSpan) {
		String result = userId
				+ ParseHelper.ToDateString(new Date(), "yyMMddHHmmss")
				+ createRandomData(3) + splitTimeSpan(timeSpan, 3);
		return result;
	}

	/**
	 * 根据userId+时间+时间戳+随机数 生成订单号(15位) 默认时间戳根据当前时间生成
	 *
	 * @author CaoHeYang
	 * @Date 20150824
	 * @param userId
	 * @return
	 */
	public static String generateOrderCode(int userId) {
		String result = userId
				+ ParseHelper.ToDateString(new Date(), "yyMMddHHmmss")
				+ createRandomData(3)
				+ splitTimeSpan(System.currentTimeMillis() + "", 3);
		return result;
	}

}
