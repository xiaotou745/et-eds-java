package com.edaisong.api.activemq;

import javax.jms.Destination;
import javax.jms.JMSException;
import javax.jms.Message;
import javax.jms.Session;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jms.core.JmsTemplate;
import org.springframework.jms.core.MessageCreator;
import org.springframework.stereotype.Service;

@Service
public class ActiveMqService {
	@Autowired
	private JmsTemplate jmsTemplate;
	@Autowired
	private Destination queueDestination;

	public void sendMessage(final String message) {
		//System.out.println("日志生产者发了一个日志消息：" + message);
		try {
			jmsTemplate.send(queueDestination, new MessageCreator() {
				public Message createMessage(Session session) throws JMSException {
					return session.createTextMessage(message);
				}
			});
		} catch (Exception e) {
			e.printStackTrace();
		}

	}
}
