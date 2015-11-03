package com.edaisong.entity.domain;

/**
 * 商户端 我的骑士
 * 
 * @version 3.0
 * @author CaoHeYang
 * @Date 20151103
 */
public class ServiceClienters {
	private int relationId;
	private int clienterId;
	private String trueName;
	private String phoneNo;
	private String headPhoto;

	/**
	 * 骑士商家关系id
	 * 
	 * @return
	 */
	public int getRelationId() {
		return relationId;
	}

	/**
	 * 骑士商家关系id
	 * 
	 * @param relationId
	 */
	public void setRelationId(int relationId) {
		this.relationId = relationId;
	}

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
	 * 骑士名称
	 * 
	 * @return
	 */
	public String getTrueName() {
		return trueName;
	}

	/**
	 * 骑士名称
	 * 
	 * @param trueName
	 */
	public void setTrueName(String trueName) {
		this.trueName = trueName;
	}

	/**
	 * 骑士手机号
	 * 
	 * @return
	 */
	public String getPhoneNo() {
		return phoneNo;
	}

	/**
	 * 骑士手机号
	 * 
	 * @param phoneNo
	 */
	public void setPhoneNo(String phoneNo) {
		this.phoneNo = phoneNo;
	}

	/**
	 * 骑士头像
	 * 
	 * @return
	 */
	public String getHeadPhoto() {
		return headPhoto;
	}

	/**
	 * 骑士头像
	 * 
	 * @param headPhoto
	 */
	public void setHeadPhoto(String headPhoto) {
		this.headPhoto = headPhoto;
	}

}
