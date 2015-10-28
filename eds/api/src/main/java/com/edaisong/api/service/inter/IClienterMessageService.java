package com.edaisong.api.service.inter;
import com.edaisong.entity.ClienterMessage;

/**
 * 骑士站内消息
 * @author CaoHeYang
 * @date 20150909
 */
public interface IClienterMessageService {
	
	/**
	 * 获取最新消息
	 * @author CaoHeYang
	 * @param clienterId
	 * @date 20150909
	 * @return
	 */
	ClienterMessage getLatestMessage(int clienterId);
}
