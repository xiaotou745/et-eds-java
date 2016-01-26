package com.edaisong.api.activemq;

import java.util.List;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

import javax.jms.Destination;
import javax.jms.JMSException;
import javax.jms.Message;
import javax.jms.Session;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jms.core.JmsTemplate;
import org.springframework.jms.core.MessageCreator;
import org.springframework.stereotype.Service;

import com.edaisong.core.util.JsonUtil;
import com.edaisong.core.util.PropertyUtils;
import com.edaisong.core.util.StringUtils;
import com.edaisong.core.util.SystemUtils;
import com.edaisong.entity.domain.ActionLog;

@Service
public class ActiveMqService {
	@Autowired
	private JmsTemplate jmsTemplate;
	@Autowired
	private Destination queueDestination;
	@Autowired
	private Destination serviceLogQueueDestination;
	private static   ExecutorService fixedThreadPool = Executors.newFixedThreadPool(5);  
/**
 * 异步发送mq消息
 * @author hailongzhao
 * @date 20151023
 * @param message
 */
	public void asynSendMessage(String sourceSys,final String message) {
		fixedThreadPool.execute(new Runnable() {
			@Override
			public void run() {
				synSendMessage(sourceSys,message);
			}
		});
//		Thread dThread = new Thread(new Runnable() {
//			@Override
//			public void run() {
//				synSendMessage(sourceSys,message);
//			}
//		});
//		dThread.setDaemon(false);
//		dThread.start();
	}
	/**
	 * 同步发送mq消息
	 * @author hailongzhao
	 * @date 20151023
	 * @param message
	 */
	public void synSendMessage(String sourceSys,final String message){
		try {
			jmsTemplate.send(queueDestination, new MessageCreator() {
				public Message createMessage(Session session) throws JMSException {
					return session.createTextMessage(message);
				}
			});
		} catch (Exception e) {
			//e.printStackTrace();
            System.out.println("ActiveMq发送日志消息时出错：" + e.getMessage());   
			String stackTrace = StringUtils.getStackTrace(e);
			List<String> ipinfoList = SystemUtils.getLocalIpInfo();
			String appServerIP = JsonUtil.obj2string(ipinfoList);
			SystemUtils.sendAlertEmail("ActiveMq_"+sourceSys+"_java项目预警", "appServerIP:"+appServerIP+"\n"+e.getMessage()+"\n"+stackTrace);
		}
	}
	/**
	 * 异步发送mq服务日志消息
	 * @author hailongzhao
	 * @date 20151214
	 * @param message
	 */
		public void asynSendServiceLogMessage(final String message) {
			Thread dThread = new Thread(new Runnable() {
				@Override
				public void run() {
					synSendServiceLogMessage(message);
				}
			});
			dThread.setDaemon(false);
			dThread.start();
		}
		/**
		 * 同步发送mq服务日志消息
		 * @author hailongzhao
		 * @date 20151214
		 * @param message
		 */
		public void synSendServiceLogMessage(final String message){
			try {
				jmsTemplate.send(serviceLogQueueDestination, new MessageCreator() {
					public Message createMessage(Session session) throws JMSException {
						return session.createTextMessage(message);
					}
				});
			} catch (Exception e) {
				//e.printStackTrace();
	            System.out.println("ActiveMq发送service日志消息时出错：" + e.getMessage());   
				String stackTrace = StringUtils.getStackTrace(e);
				List<String> ipinfoList = SystemUtils.getLocalIpInfo();
				String appServerIP = JsonUtil.obj2string(ipinfoList);
				SystemUtils.sendAlertEmail("ActiveMq_mqservice_java项目预警", "appServerIP:"+appServerIP+"\n"+e.getMessage()+"\n"+stackTrace);
			}
		}
}
