package com.edaisong.api.service.impl;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.edaisong.api.dao.inter.IOrderDao;
import com.edaisong.api.dao.inter.IOrderRegionDao;
import com.edaisong.api.dao.inter.IOrderRegionLogDao;
import com.edaisong.api.service.inter.IOrderRegionService; 
import com.edaisong.core.enums.OrderRegionLogOptType;
import com.edaisong.core.util.SystemUtils;
import com.edaisong.entity.OrderRegion;
import com.edaisong.entity.OrderRegionLog;
import com.edaisong.entity.req.OrderRegionReq;

@Service
public class OrderRegionService implements IOrderRegionService {
	@Autowired
	private IOrderRegionLogDao orderRegionLogDao;
	@Autowired
	private IOrderRegionDao orderRegionDao;
	@Autowired
	private IOrderDao orderDao;
	/**
	 * 添加操作日志
	 * @param opType 操作类型（默认1新增，2修改，3删除）
	 * @param orderRegion
	 */
	private void addLog(OrderRegionLogOptType optType,OrderRegion orderRegion,String oldName){
		OrderRegionLog record=new OrderRegionLog();
		record.setOrderregionid(orderRegion.getId());
		record.setBusinessid(orderRegion.getBusinessid());
		record.setOptname(orderRegion.getOptname());
		if (orderRegion.getOptname()==null||orderRegion.getOptname().isEmpty()) {
			record.setOpttype(optType.value());
		}
		record.setOpttype(optType.value());
		String regionLevel="";
		if (orderRegion.getParentid()>0) {
			regionLevel="二级区域:"+orderRegion.getName();
		}else {
			regionLevel="一级区域:"+orderRegion.getName();
		}
		switch (optType) {
			case add:
			    record.setRemark("新增"+regionLevel);
				break;
			case update:
				if (orderRegion.getParentid()>0) {
					record.setRemark("修改二级区域名称:从("+oldName+")改为了("+orderRegion.getName()+")");
				}else {
					record.setRemark("修改一级级区域名称:从("+oldName+")改为了("+orderRegion.getName()+")");
				}
				break;
			case delete:
				record.setRemark("删除"+regionLevel);
				break;
			default:
				break;
		}
		orderRegionLogDao.insert(record);
	}
	/*
	 * 获取商户的区域信息
	 * wangchao
	 * update by zhaohl 20151120
	 */ 
	@Override
	public List<OrderRegion> getOrderRegion(OrderRegionReq orderRegionReq) {
		List<OrderRegion> oldData = orderRegionDao
				.getOrderRegion(orderRegionReq);
		for (OrderRegion orderRegion : oldData) {
			// 如果一个有效的一级区域haschild=false，但是却有有效的二级区域，则修复数据
			if (orderRegion.getStatus().equals((byte)1)
					&& orderRegion.getParentid().equals(0)
					&& orderRegion.getHaschild() == false) {
				List<OrderRegion> childList = oldData
						.stream()
						.filter(t -> orderRegion.getStatus().equals((byte)1)
								&& t.getParentid().equals(orderRegion.getId()))
						.collect(Collectors.toList());
				if (childList.size() > 0) {
					orderRegionDao.updateAllHasChild();
					SystemUtils.sendAlertEmail("business_java项目预警", orderRegionReq.getBusinessId()+"商家区域数据haschild异常");
					return orderRegionDao.getOrderRegion(orderRegionReq);
				}
			}
		}
		return oldData;
	}
	@Override
	@Transactional(rollbackFor = Exception.class, timeout = 30)
	public Integer updateRegionList(List<OrderRegion> regionList,List<OrderRegion> oldList) {
		if (regionList==null||regionList.size()==0) {
			return 0;
		}
		List<OrderRegion> resultList=new ArrayList<OrderRegion>();
		for (OrderRegion orderRegion : regionList) {
			OrderRegion  temp=oldList.stream().filter(t ->t.getId().equals(orderRegion.getId())).findFirst().get();
			if (!orderRegion.getName().trim().equals(temp.getName().trim())) {
				resultList.add(orderRegion);
				addLog(OrderRegionLogOptType.update,orderRegion,temp.getName().trim());
			}
		}
		if (resultList.size()>0) {
			return orderRegionDao.updateRegionList(resultList);
		}
		return 0;
	}
	@Override
	@Transactional(rollbackFor = Exception.class, timeout = 30)
	public Integer insertRegionList(List<OrderRegion> regionList,List<OrderRegion> oldList) {
		if (regionList==null||regionList.size()==0) {
			return 0;
		}
		List<Integer>  allIdList=regionList.stream().map(t->t.getId()).collect(Collectors.toList());
		List<Integer>  parentIdList=regionList.stream().filter(t ->t.getParentid().equals(0)).map(t->t.getId()).collect(Collectors.toList());
		List<Integer>  childIdList=regionList.stream().filter(t ->parentIdList.contains(t.getParentid())).map(t->t.getId()).collect(Collectors.toList());
		allIdList.removeAll(parentIdList);
		allIdList.removeAll(childIdList);

		//如果是在原有的一级区域下新增的二级区域，且原有的一级区域下没有二级区域，
		//则需要更新这个一级区域的HasChild标记为1
		if (allIdList.size()>0) {
			List<Integer> updateList=new ArrayList<>();
			List<OrderRegion> tempParent=new ArrayList<OrderRegion>();
			tempParent.addAll(regionList);
			tempParent.addAll(oldList);
			List<Integer> parentList=tempParent.stream().filter(t ->allIdList.contains(t.getId())).map(t->t.getParentid()).distinct().collect(Collectors.toList());
			for (Integer parId : parentList) {
				OrderRegion  temp=tempParent.stream().filter(t ->t.getId().equals(parId)).findFirst().get();
				if (temp!=null&&!temp.getHaschild()) {
					updateList.add(parId);
				}
			}
			if (updateList.size()>0) {
				orderRegionDao.updateHasChildByIds(1,updateList);	
			}
			//在以前的一级区域下新增的二级区域，直接保存
			List<OrderRegion>  noParentList=regionList.stream().filter(t ->allIdList.contains(t.getId())).collect(Collectors.toList());
			for (OrderRegion orderRegion : noParentList) {
				orderRegionDao.insert(orderRegion);
				addLog(OrderRegionLogOptType.add,orderRegion,null);
			}
		}
		//本次操作中新增的一级区域和一级区域下的二级区域是通过前端生成的parentid关联的，并不是最终的id
		//因此，需要先保存一级区域，生成一级区域的db中的id，然后将这个一级区域下所有的二级区域的parentid设置为真正的parentid
		List<OrderRegion>  oldParentList=regionList.stream().filter(t ->t.getParentid().equals(0)).collect(Collectors.toList());
		for (OrderRegion parentRegion : oldParentList) {
			List<OrderRegion>  oldChildList=regionList.stream().filter(t ->parentRegion.getId().equals(t.getParentid())).collect(Collectors.toList());
			orderRegionDao.insert(parentRegion);
			addLog(OrderRegionLogOptType.add,parentRegion,null);
			for (OrderRegion childRegion : oldChildList) {
				childRegion.setParentid(parentRegion.getId());
				orderRegionDao.insert(childRegion);
				addLog(OrderRegionLogOptType.add,childRegion,null);
			}
		}
		return regionList.size();
	}
	@Override
	@Transactional(rollbackFor = Exception.class, timeout = 30)
	public List<String> deleteRegionList(List<Integer> idList,List<OrderRegion> oldList) {
		List<String> result=new ArrayList<String>();
		if (idList==null||idList.size()==0) {
			return result;
		}

		List<OrderRegion>  regionList=oldList.stream().filter(t ->idList.contains(t.getId())).collect(Collectors.toList());
		List<Integer>  parentIdList=regionList.stream().filter(t ->t.getParentid().equals(0)).map(t->t.getId()).collect(Collectors.toList());
		
		boolean isParentDel=true;
		List<Integer> deleteIds=new ArrayList<Integer>();
		List<Integer> noDelete=new ArrayList<Integer>();
		//删除一级区域时，需要先判断当前一级区域下的所有二级区域中是否还有未完成订单
		//如果任何一个二级区域有，则该一级区域不能删除
		for (Integer parentId : parentIdList) {
			isParentDel=true;
			List<Integer>  childIdList=regionList.stream().filter(t ->t.getParentid().equals(parentId)).map(t->t.getId()).collect(Collectors.toList());
			for (Integer childId : childIdList) {
				Long numCount=orderDao.queryIngOrderByRegionId((long)childId);
				if (numCount>0) {
					isParentDel=false;
					noDelete.add(childId);
				}else {
					deleteIds.add(childId);
				}
			}
			if (isParentDel) {
				deleteIds.add(parentId);
			}else {
				noDelete.add(parentId);
			}
		}

		List<Integer>  allIdList=regionList.stream().map(t->t.getId()).collect(Collectors.toList());
		allIdList.removeAll(deleteIds);
		allIdList.removeAll(noDelete);
		List<Integer> updateIds=new ArrayList<Integer>();
		for (Integer childId : allIdList) {//一级区域没删除，只是删除二级区域时，需要判断是否还有二级区域
			Long numCount=orderDao.queryIngOrderByRegionId((long)childId);
			if (numCount>0) {
				noDelete.add(childId);
			}else {
				OrderRegion  oldRegion=regionList.stream().filter(t ->t.getId().equals(childId)).findFirst().get();
				if (oldRegion.getParentid()>0) {//当前删除的是二级区域，则需要判断当前区域的父区域是否还有二级区域
					List<Integer>  brotherIdList=oldList.stream().filter(t ->t.getParentid().equals(oldRegion.getParentid())).map(t->t.getId()).collect(Collectors.toList());
					brotherIdList.removeAll(allIdList);
					if (brotherIdList.size()==0&&!updateIds.contains(oldRegion.getParentid())) {
						updateIds.add(oldRegion.getParentid());
					}
				}
				deleteIds.add(childId);
			}
		}
		if (deleteIds.size()>0) {
			orderRegionDao.deleteByIds(deleteIds);
			for (Integer id : deleteIds) {
				OrderRegion  oldRegion=regionList.stream().filter(t ->t.getId().equals(id)).findFirst().get();
				addLog(OrderRegionLogOptType.delete,oldRegion,null);
			}
		}
		if (updateIds.size()>0) {
			orderRegionDao.updateHasChildByIds(0,updateIds);
		}
		if (noDelete.size()>0) {
			result=regionList.stream().filter(t ->noDelete.contains(t.getId())).map(t->t.getName()).collect(Collectors.toList());
		}
		return result;
	} 
}
