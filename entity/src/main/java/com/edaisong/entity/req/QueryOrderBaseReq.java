package com.edaisong.entity.req;

import com.edaisong.entity.common.PagedRequestBase;

/**
 * 移动端查询订单列表
 * @author CaoHeYang
 * @date 20150910
 */
public class QueryOrderBaseReq extends PagedRequestBase {
	private int userId;
	private int status;

	/**
	 * 用户id
	 * 
	 * @return
	 */
	public int getUserId() {
		return userId;
	}

	/**
	 * 用户id
	 * 
	 * @param userId
	 */
	public void setUserId(int userId) {
		this.userId = userId;
	}

	/**
	 * 订单状态
	 * 
	 * @return
	 */
	public int getStatus() {
		return status;
	}

	/**
	 * 订单状态
	 * 
	 * @param status
	 */
	public void setStatus(int status) {
		this.status = status;
	}
}
