package com.edaisong.entity.domain;

import java.util.List;

/**
 * 店内任务 区域信息 一级 或者二级
 * 
 * @author CaoHeYang
 * @date 20151102
 */
public class InStoreOrderRegionInfo {
	private int  id;
	private int waitingCount;
	private String regionName;
	private int hasChild;
	private int parentId;
	private int businessId;
	private List<InStoreOrderRegionInfo> childs;
	/**
	 * 区域id
	 * @return
	 */
	public int getId() {
		return id;
	}
	/**
	 * 区域id
	 * @param id
	 */
	public void setId(int id) {
		this.id = id;
	}
	/**
	 * 区域id
	 * @return
	 */
	public int getWaitingCount() {
		return waitingCount;
	}
	/**
	 * 区域id
	 * @param waitingCount
	 */
	public void setWaitingCount(int waitingCount) {
		this.waitingCount = waitingCount;
	}
	/**
	 * 区域名称
	 * @return
	 */
	public String getRegionName() {
		return regionName;
	}
	/**
	 * 区域名称
	 * @param regionName
	 */
	public void setRegionName(String regionName) {
		this.regionName = regionName;
	}
	/**
	 * 是否含有小一级区域  0没有 1 有
	 * @return
	 */
	public int getHasChild() {
		return hasChild;
	}
	/**
	 *  是否含有小一级区域  0没有 1 有
	 * @param hasChild
	 */
	public void setHasChild(int hasChild) {
		this.hasChild = hasChild;
	}
	/**
	 * 上一级区域id  如果该级区域是一级区域 则 上一级区域id=0
	 * @return
	 */
	public int getParentId() {
		return parentId;
	}
/**
 * 上一级区域id  如果该级区域是一级区域 则 上一级区域id=0
 * @param parentId
 */
	public void setParentId(int parentId) {
		this.parentId = parentId;
	}
	/**
	 * 下一级区域集合   如果该区域无下一级区域则为null
	 * @return
	 */
	public List<InStoreOrderRegionInfo> getChilds() {
		return childs;
	}
	/**
	 *  下一级区域集合   如果该区域无下一级区域则为null
	 * @param childs
	 */
	public void setChilds(List<InStoreOrderRegionInfo> childs) {
		this.childs = childs;
	}
	/**
	 * 商户id
	 * @return
	 */
	public int getBusinessId() {
		return businessId;
	}
	/**
	 * 商户id
	 * @param businessId
	 */
	public void setBusinessId(int businessId) {
		this.businessId = businessId;
	}

}
