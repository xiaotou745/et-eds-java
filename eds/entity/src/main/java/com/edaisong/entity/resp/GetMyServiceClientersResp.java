package com.edaisong.entity.resp;

import java.util.List;

import com.edaisong.entity.domain.ServiceClienters;

/**
 * 商戶端 查詢 我的骑士 数量信息
 * 
 * @version 3.0
 * @author CaoHeYang
 * @Date 20151104
 */
public class GetMyServiceClientersResp {
	private int waitAduitCount;
	private int serviceCount;
	private List<ServiceClienters> list;

	/**
	 * 服务中骑士数量
	 * 
	 * @return
	 */
	public int getWaitAduitCount() {
		return waitAduitCount;
	}

	/**
	 * 服务中骑士数量
	 * 
	 * @param waitAduitCount
	 */
	public void setWaitAduitCount(int waitAduitCount) {
		this.waitAduitCount = waitAduitCount;
	}

	/**
	 * 申请中骑士数量
	 * 
	 * @return
	 */
	public int getServiceCount() {
		return serviceCount;
	}

	/**
	 * 申请中骑士数量
	 * 
	 * @param serviceCount
	 */
	public void setServiceCount(int serviceCount) {
		this.serviceCount = serviceCount;
	}

	/**
	 * 骑士
	 * 
	 * @return
	 */
	public List<ServiceClienters> getList() {
		return list;
	}

	/**
	 * 骑士
	 * 
	 * @param list
	 */
	public void setList(List<ServiceClienters> list) {
		this.list = list;
	}

}
