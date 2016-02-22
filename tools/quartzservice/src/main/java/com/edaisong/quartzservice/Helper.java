package com.edaisong.quartzservice;

import java.io.InputStream;
import java.util.List;
import java.util.Properties;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.edaisong.toolscore.util.JsonUtil;
import com.edaisong.toolscore.util.StringUtils;
import com.edaisong.toolscore.util.SystemUtils;

public class Helper {
	private static final Properties prop = new Properties();
	private final  static Logger log = LoggerFactory.getLogger("com.edaisong.quartzservice.Helper");
    static {
		try {
			InputStream inputStream = Helper.class.getClassLoader().getResourceAsStream("conf.properties");
			prop.load(inputStream);
			inputStream.close();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

    }
	public static String getProperty(String key){
		return prop.getProperty(key);
	}
	public static void sendMail(String remark, Exception e) {
		log.info(remark + e.getMessage());
		String isSendMail=getProperty("IsSendMail");
		if (isSendMail.equals("1")) {
			String stackTrace = StringUtils.getStackTrace(e);
			List<String> ipinfoList = SystemUtils.getLocalIpInfo();
			String appServerIP = JsonUtil.obj2string(ipinfoList);
			sendAlertEmail("quartzservice_java项目预警", remark+ "\n appServerIP:" + appServerIP + "\n" + e.getMessage()+ "\n" + stackTrace);
		}
		
	}
	private static void sendAlertEmail(String title,String body){
		try {
			String emailHost = getProperty("EmailHost");//"smtp.263.net";//发送邮件服务器
		    String emailFrom = getProperty("EmailFrom");//"edssys@etao.cn";
		    String emailTo = getProperty("EmailTo");//"zhao.hailong@etao.cn";
		    String emailUserName = getProperty("EmailUserName");//"edssys@etao.cn";
		    String emailPwd = getProperty("EmailPwd");//"eds1234";
		    SystemUtils.sendEmailTo(title,body,emailHost,emailFrom,emailTo,emailUserName,emailPwd);
		    
		} catch (Exception e) {
			e.printStackTrace();
		}
		
	}


}
