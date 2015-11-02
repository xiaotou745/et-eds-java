package com.edaisong.entity.req;

/**
 * 骑士端获取店内任务
 * 
 * @version 3.0
 * @author CaoHeYang
 * @date 20151102
 */
public class InStoreTaskReq {
	private int clienterId;

	/**
	 * 骑士id
	 * 
	 * @return
	 */
	public int getClienterId() {
		return clienterId;
	}

	/**
	 * 骑士id
	 * 
	 * @param clienterId
	 */
	public void setClienterId(int clienterId) {
		this.clienterId = clienterId;
	}

}
