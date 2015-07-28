package com.edaisong.core.util;

import java.net.InetAddress;
import java.net.UnknownHostException;
import java.util.ArrayList;
import java.util.List;

public class SystemUtils {
	public static List<String> GetLocalIpInfo(){
		String ip = "";
		String address = "";
		List<String> result = new ArrayList<>();
		InetAddress addr;
		try {
			addr = InetAddress.getLocalHost();
			ip = addr.getHostAddress().toString();// 获得本机IP　　
			address = addr.getHostName().toString();// 获得本机名称
		} catch (UnknownHostException e) {
			e.printStackTrace();
		}

		result.add(ip);
		result.add(address);
		return result;
	}
}
