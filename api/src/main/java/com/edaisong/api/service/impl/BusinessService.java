package com.edaisong.api.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.edaisong.api.dal.dao.inter.IBusinessDao;
import com.edaisong.api.service.inter.IBusinessService;
import com.edaisong.entity.Business;
import com.edaisong.entity.BusinessOptionLog;
import com.edaisong.entity.common.PagedResponse;
import com.edaisong.entity.domain.BusinessDetailModel;
import com.edaisong.entity.domain.BusinessModel;
import com.edaisong.entity.domain.BusinessModifyModel;
import com.edaisong.entity.req.BusinessLoginReq;
import com.edaisong.entity.req.PagedBusinessReq;
import com.edaisong.entity.resp.BusinessLoginResp;

@Service
public class BusinessService implements IBusinessService {

	@Autowired
	private IBusinessDao iBusinessDao;

	@Override
	public PagedResponse<BusinessModel> getBusinessList(PagedBusinessReq req) {
		return iBusinessDao.getBusinessList(req);
	}

	/**
	 * 商家登录
	 * @param req
	 * @return
	 */
	public BusinessLoginResp login(BusinessLoginReq req){
		return null;
	}

	@Override
	public BusinessDetailModel getBusinessDetailByID(int businessID) {
		return iBusinessDao.getBusinessDetailByID(businessID);
	}

	@Override
	public List<BusinessOptionLog> getOpLogByBusinessID(int businessID) {
		return iBusinessDao.getOpLogByBusinessID(businessID);
	}

	@Override
	public int modifyBusiness(BusinessModifyModel detailModel) {
		BusinessDetailModel oldModel = iBusinessDao
				.getBusinessDetailByID(detailModel.getId());
		String remark = GetRemark(oldModel, detailModel);
		if (remark.isEmpty()) {
			return -1;
		}
		detailModel.setRemark(remark);
		return iBusinessDao.modifyBusiness(detailModel);
	}

	private String GetRemark(BusinessDetailModel brm, BusinessModifyModel model) {
		StringBuffer remark = new StringBuffer();
		if (brm != null && brm.getId() > 0) {
			if (!brm.getAddress().equals(model.getAddress())) {
				remark.append(String.format("商户地址原值:%s,修改为%s;", brm.getAddress(),
						model.getAddress()));
			}
			if (brm.getGroupid() != model.getGroupid()) {
				remark.append(String.format("第三方id原值:%s,修改为%s;",
						brm.getGroupid(), model.getGroupid()));
			}
			if (!brm.getName().equals(model.getName())) {
				remark.append(String.format("商户名原值:%s,修改为%s;", brm.getName(),
						model.getName()));
			}
			if (!brm.getPhoneno2().equals(model.getPhoneno2())) {
				remark.append(String.format("联系电话原值:%s,修改为%s;",
						brm.getPhoneno2(), model.getPhoneno2()));
			}
			// 座机
			if (!brm.getLandline().equals(model.getLandline())) {
				remark.append(String.format("联系座机原值:%s,修改为%s;",
						brm.getLandline(), model.getLandline()));
			}
			if (brm.getDistribsubsidy().compareTo(model.getDistribsubsidy()) != 0) {
				remark.append(String.format("配送费原值:%s,修改为%s;",
						brm.getDistribsubsidy(), model.getDistribsubsidy()));
			}
			if (!brm.getCity().equals(model.getCity())) {
				remark.append(String.format("城市原值:%s,修改为%s;", brm.getCity(),
						model.getCity()));
			}
			if (!brm.getDistrict().equals(model.getDistrict())) {
				remark.append(String.format("区域原值:%s,修改为%s;",
						brm.getDistrict(), model.getDistrict()));
			}
			if (brm.getLongitude().compareTo(model.getLongitude()) != 0) {
				remark.append(String.format("经度原值:%s,修改为%s;",
						brm.getLongitude(), model.getLongitude()));
			}
			if (brm.getLatitude().compareTo(model.getLatitude()) != 0) {
				remark.append(String.format("纬度原值:%s,修改为%s;",
						brm.getLatitude(), model.getLatitude()));
			}
			if (brm.getCommissiontype() != model.getCommissiontype()) {
				remark.append(String.format("结算类型原值:%s,修改为%s;",
						brm.getCommissiontype(), model.getCommissiontype()));
				if (model.getCommissiontype() == 1) {
					remark.append(String.format("固定比例原值:%s,修改为%s;",
							brm.getBusinesscommission(),
							model.getBusinesscommission()));
				}
				if (model.getCommissiontype() == 2) {
					remark.append(String.format("固定金额原值:%s,修改为%s;",
							brm.getCommissionfixvalue(),
							model.getCommissionfixvalue()));
				}
			}
			// 补贴策略 BusinessGroupId
			if (brm.getBusinessgroupid() != model.getBusinessgroupid()) {
				remark.append(String.format("补贴策略原值:%s,修改为%s;",
						brm.getBusinessgroupid(), model.getBusinessgroupid()));
			}
			// 餐费结算方式
			if (brm.getMealssettlemode() != model.getMealssettlemode()) {
				remark.append(String.format("餐费结算方式原值:%s,修改为%s;",
						brm.getMealssettlemode(), model.getMealssettlemode()));
			}
			// 一键发单
			if (brm.getOnekeypuborder() != model.getOnekeypuborder()) {
				remark.append(String.format("一键发单原值:%s,修改为%s;",
						brm.getOnekeypuborder(), model.getOnekeypuborder()));
			}
			// 余额可以透支
			if (brm.getIsallowoverdraft() != model.getIsallowoverdraft()) {
				remark.append(String.format("余额透支原值:%s,修改为%s;",
						brm.getIsallowoverdraft(), model.getIsallowoverdraft()));
			}
			// 雇主任务时间限制
			if (brm.getIsemployertask() != model.getIsemployertask()) {
				remark.append(String.format("余额透支原值:%s,修改为%s;",
						brm.getIsemployertask(), model.getIsemployertask()));
			}
			// 第三方Id
			if (model.getOriginalbusiid() != model.getOriginalbusiid()) {
				remark.append(String.format("第三方ID原值:%s,修改为%s;",
						brm.getOriginalbusiid(), model.getOriginalbusiid()));
			}
		}
		if (remark.length() > 0) {
			String tipString = model.getOptName() + "通过后台管理系统修改商户信息:";
			return tipString + remark.toString();
		}
		return "";
	}
}
