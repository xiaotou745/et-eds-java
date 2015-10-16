package com.edaisong.core.util;

import java.net.InetAddress;
import java.net.UnknownHostException;
import java.util.ArrayList;
import java.util.List;
import java.util.Properties;

import javax.mail.Message;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;

public class SystemUtils {
	public static List<String> getLocalIpInfo(){
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
	/**
	 * 发送邮件
	 * @param title
	 * @param body
	 * @param emailHost
	 * @param emailFrom
	 * @param emailTo
	 * @param emailUserName
	 * @param emailPwd
	 */
	public static void sendEmailTo(String title,String body,String emailHost,String emailFrom,
			String emailTo,String emailUserName,String emailPwd){
		try {
		    Properties props = new Properties();
		    // Setup mail server
		    props.put("mail.smtp.host", emailHost);// 设置smtp主机
		    props.put("mail.smtp.auth", "true");// 使用smtp身份验证
		    Session session = Session.getDefaultInstance(props, null);
		    MimeMessage message = new MimeMessage(session);
		    message.setFrom(new InternetAddress(emailFrom));
		    message.addRecipient(Message.RecipientType.TO, new InternetAddress(emailTo));
		    message.setSubject(title);//标题
		    message.setText(body,"utf-8");//内容
		    //http协议部分会自动转换成超链接
		    message.saveChanges();
		    // Send message
		    Transport transport = session.getTransport("smtp");
		    transport.connect(emailHost, emailUserName, emailPwd);
		    transport.sendMessage(message, message.getAllRecipients());
		} catch (Exception e) {
			e.printStackTrace();
		}
		
	}
	/**
	 * 发送报警邮件
	 * @param title
	 * @param body
	 */
	public static void sendAlertEmail(String title,String body){
		try {
			String emailHost = PropertyUtils.getProperty("EmailHost");//"smtp.263.net";//发送邮件服务器
		    String emailFrom = PropertyUtils.getProperty("EmailFrom");//"edssys@etao.cn";
		    String emailTo = PropertyUtils.getProperty("EmailTo");//"zhao.hailong@etao.cn";
		    String emailUserName = PropertyUtils.getProperty("EmailUserName");//"edssys@etao.cn";
		    String emailPwd = PropertyUtils.getProperty("EmailPwd");//"eds1234";
		    sendEmailTo(title,body,emailHost,emailFrom,emailTo,emailUserName,emailPwd);
		    
		} catch (Exception e) {
			e.printStackTrace();
		}
		
	}
}
