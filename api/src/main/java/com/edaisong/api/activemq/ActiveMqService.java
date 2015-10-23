package com.edaisong.api.activemq;

import javax.jms.Destination;
import javax.jms.JMSException;
import javax.jms.Message;
import javax.jms.Session;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jms.core.JmsTemplate;
import org.springframework.jms.core.MessageCreator;
import org.springframework.stereotype.Service;

import com.edaisong.core.util.PropertyUtils;
import com.edaisong.core.util.StringUtils;
import com.edaisong.core.util.SystemUtils;

//@Service
public class ActiveMqService {
	@Autowired
	private JmsTemplate jmsTemplate;
	@Autowired
	private Destination queueDestination;
/**
 * 异步发送mq消息
 * @author hailongzhao
 * @date 20151023
 * @param message
 */
	public void asynSendMessage(final String message) {
		Thread dThread = new Thread(new Runnable() {
			@Override
			public void run() {
				synSendMessage(message);
			}
		});
		dThread.setDaemon(false);
		dThread.start();
	}
	/**
	 * 同步发送mq消息
	 * @author hailongzhao
	 * @date 20151023
	 * @param message
	 */
	public void synSendMessage(final String message){
		try {
			jmsTemplate.send(queueDestination, new MessageCreator() {
				public Message createMessage(Session session) throws JMSException {
					return session.createTextMessage(message);
				}
			});
		} catch (Exception e) {
			e.printStackTrace();
			String isSendMail = PropertyUtils.getProperty("IsSendMail");
			if (isSendMail.equals("1")) {
				String stackTrace = StringUtils.getStackTrace(e);
				SystemUtils.sendAlertEmail("ActiveMq_java项目预警", e.getMessage()+"\n"+stackTrace);
			}
		}
	}
}
