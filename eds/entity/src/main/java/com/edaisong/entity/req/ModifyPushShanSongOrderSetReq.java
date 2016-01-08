package com.edaisong.entity.req;

/**
 * 修改 是否 接收 里程计算的推单
 * 
 * @author CaoHeYang
 * @date 20160108
 */
public class ModifyPushShanSongOrderSetReq {
	private int clienterId;
	private int set;

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

	/**
	 * 推单设置 0 不开启 1开启
	 * 
	 * @return
	 */
	public int getSet() {
		return set;
	}

	/**
	 * 推单设置 0 不开启 1开启
	 * 
	 * @param set
	 */
	public void setSet(int set) {
		this.set = set;
	}

}
