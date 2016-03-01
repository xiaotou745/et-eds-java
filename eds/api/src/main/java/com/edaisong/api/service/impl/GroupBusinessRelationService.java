package com.edaisong.api.service.impl;


import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.edaisong.api.dao.inter.IGroupBusinessRelationDao;
import com.edaisong.api.service.inter.IGroupBusinessRelationService;
import com.edaisong.entity.common.PagedResponse;
import com.edaisong.entity.domain.GroupBusinessRelationModel;
import com.edaisong.entity.req.BusinessBindOptionReq;
import com.edaisong.entity.req.PagedBizBindsReq;

/**
 *
 * @author  pengyi 
 * @date 2015年9月9日 上午10:55:01
 * @version 1.0
 * @parameter
 * @since
 */
@Service
public class GroupBusinessRelationService implements IGroupBusinessRelationService{

	@Autowired
	private IGroupBusinessRelationDao groupBusinessRelationDao;
	@Override
	public PagedResponse<GroupBusinessRelationModel> getBusinessBindList(PagedBizBindsReq req) {
		return groupBusinessRelationDao.getBusinessBindList(req);
	}

	/**
	 * 删除骑士绑定
	 * @author pengyi
	 * @date 20150901
	 */
	@Override
	public boolean removeBusinessBind(BusinessBindOptionReq req) {
		return groupBusinessRelationDao.removeBusinessBind(req);
	}

	@Override
	public boolean addBusinessBind(BusinessBindOptionReq req) {
		return groupBusinessRelationDao.addBusinessBind(req);
	}

	@Override
	public boolean checkHaveBind(BusinessBindOptionReq req) {
		return groupBusinessRelationDao.checkHaveBind(req);
	}

	/**
	 * 获得所有门店列表(包含绑定和未绑定的)
	 */
	@Override
	public PagedResponse<GroupBusinessRelationModel> getBusinessList(PagedBizBindsReq req) {
		return groupBusinessRelationDao.getBusinessList(req);
	}

	@Override
	public String getGroupBusListString(int groupId) {
		List<GroupBusinessRelationModel> modellist=groupBusinessRelationDao.getBusinessListForStr(groupId);
		StringBuilder sBuilder=new StringBuilder();
		sBuilder.append("[");
		for (int i = 0; i < modellist.size(); i++) {
			sBuilder.append("{\"label\":\""+modellist.get(i).getName()+"\",");
			sBuilder.append("\"value\":\""+modellist.get(i).getBusinessid()+"\",");
			String tem=modellist.get(i).getIsbind().equals(Short.valueOf("1"))?"false":"true";
			sBuilder.append("\"unlink\":"+tem+"}");
			sBuilder.append(",");
		}
		String str=sBuilder.substring(0, sBuilder.length()-1);
		str+="]";
		return str;
	}
}
