package com.edaisong.api.dao.impl;


import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Repository;

import com.edaisong.api.common.DaoBase;
import com.edaisong.api.dao.inter.IClienterDao; 
import com.edaisong.entity.Clienter; 
import com.edaisong.entity.common.PagedResponse;
import com.edaisong.entity.domain.BusinessClientersModel;
import com.edaisong.entity.domain.ClienterBindInfoModel;
import com.edaisong.entity.domain.ClienterModel;
import com.edaisong.entity.domain.ClienterStatus;
import com.edaisong.entity.domain.ImportClienterInfo;
import com.edaisong.entity.domain.MyBusinessModel; 
import com.edaisong.entity.req.ClienterOptionReq; 
import com.edaisong.entity.req.ModifyVehicleReq;
import com.edaisong.entity.req.MyBusinessReq;
import com.edaisong.entity.req.PagedClienterReq;
import com.edaisong.entity.req.PagedBusinessClientersReq;
import com.edaisong.entity.req.PagedClienterSearchReq;
import com.edaisong.entity.req.UserStatusReq;
import com.edaisong.entity.resp.ClienterUserStatusResp; 
import com.edaisong.entity.resp.MyBusinessResp;




@Repository
public class ClienterDao extends DaoBase implements IClienterDao {

	@Override
	public int updateByPrimaryKeySelective(Clienter record) {
		// TODO Auto-generated method stub
		Map<String, Object> paramMap = new HashMap<>();
		paramMap.put("id", record.getId());
		paramMap.put("status", record.getStatus());	
		//其它的暂时没有写
		return getMasterSqlSessionUtil()
				.update("com.edaisong.api.dao.inter.IClienterDao.updateByPrimaryKeySelective",
						paramMap);
	}

	@Override
	public int updateByPrimaryKey(Clienter record) {
		// TODO Auto-generated method stub
		return 0;
	}
	
	@Override
	public int updateMoneyById(ClienterOptionReq record) {
	    
		Map<String, Object> paramMap = new HashMap<>();
		paramMap.put("clienterId", record.getClienterId());
		paramMap.put("optName", record.getOptName());			
		paramMap.put("remark", record.getRemark());	
		paramMap.put("rechargeAmount", record.getRechargeAmount().doubleValue());
		paramMap.put("recordType", 8);	
		paramMap.put("withwardId", 0);	
		
		return getMasterSqlSessionUtil()
				.update("com.edaisong.api.dao.inter.IClienterDao.updateMoneyById",
						paramMap);
	}	
	
	@Override
	public PagedResponse<ClienterModel> query(PagedClienterReq req) {
		PagedResponse<ClienterModel> model = getReadOnlySqlSessionUtil()
				.selectPageList(
						"com.edaisong.api.dao.inter.IClienterDao.query",
						req);
		return model;	
	}

	/**
	 * 获得商家下的所有骑士 ,包含骑士已接单的数量
	 * @author pengyi
	 * @date 20150901
	 */
	@Override
	public PagedResponse<BusinessClientersModel> getBusinessClienters(PagedBusinessClientersReq req) {		
		return getReadOnlySqlSessionUtil().selectPageList("com.edaisong.api.dao.inter.IClienterDao.getBusinessClienters", req);
	}

    /**
     * 更新骑士余额
     * @param amount
     * @param clienterId
     *  @Date 20150831
	 * @param business
     */
	@Override
	public int updateCAccountBalance(Double amount, int clienterId) {
		Map<String, Object> parasMap = new HashMap();
		parasMap.put("Money", amount);
		parasMap.put("Id", clienterId);
		return getMasterSqlSessionUtil()
				.update("com.edaisong.api.dao.inter.IClienterDao.updateCAccountBalance",
						parasMap);
	}

	 /**
     * 更新骑士余额
     * @param amount
     * @param clienterId
     *  @Date 20150831
	 * @param business
     */
	@Override
	public int updateCAllowWithdrawPrice(Double amount, int clienterId) {
		Map<String, Object> parasMap = new HashMap();
		parasMap.put("Money", amount);
		parasMap.put("Id", clienterId);
		return getMasterSqlSessionUtil()
				.update("com.edaisong.api.dao.inter.IClienterDao.updateCAllowWithdrawPrice",
						parasMap);
	}
	/**
	 * 更新骑士余额和可提现金额
	 * @author CaoHeYang
	 * @param amount
	 * @param clienterId
	 * @date 20150831
	 * @return
	 */
	@Override
	public int updateCBalanceAndWithdraw(Double amount, int clienterId) {
		Map<String, Object> parasMap = new HashMap();
		parasMap.put("Money", amount);
		parasMap.put("Id", clienterId);
		return getMasterSqlSessionUtil()
				.update("com.edaisong.api.dao.inter.IClienterDao.updateCBalanceAndWithdraw",
						parasMap);
	}

	@Override
	public boolean updateClienterIsBind(int clienterId, int isBind) {
		Map<String, Object> parasMap = new HashMap();
		parasMap.put("clienterId", clienterId);
		parasMap.put("isBind", isBind);
		return getMasterSqlSessionUtil()
				.update("com.edaisong.api.dao.inter.IClienterDao.updateClienterIsBind",
						parasMap) > 0;
	}

	/**
	 * 获得骑士和绑定信息列表
	 * @author pengyi
	 * @date 20150901
	 */
	@Override
	public PagedResponse<ClienterBindInfoModel> getClienterBindInfoList(PagedClienterSearchReq req) {
		return getReadOnlySqlSessionUtil()
				.selectPageList("com.edaisong.api.dao.inter.IClienterDao.getClienterBindInfoList",
						req);
	}

	@Override
	public List<ImportClienterInfo> getInfosByPhones(List<String> phoneNos) {
		return getReadOnlySqlSessionUtil()
				.selectList("com.edaisong.api.dao.inter.IClienterDao.getInfosByPhones",
						phoneNos);
	}

	@Override
	public String getNameByPhone(String phoneNo) {
		return getReadOnlySqlSessionUtil()
				.selectOne("com.edaisong.api.dao.inter.IClienterDao.getNameByPhone",
						phoneNo);
	}

	@Override
	public Integer getId(String phoneNo, String trueName) {
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("phoneNo", phoneNo);
		map.put("trueName", trueName);
		return getReadOnlySqlSessionUtil()
				.selectOne("com.edaisong.api.dao.inter.IClienterDao.getId",
						map);
	}

	@Override
	public Clienter selectByPrimaryKey(Integer id) {
		return getReadOnlySqlSessionUtil().selectOne("com.edaisong.api.dao.inter.IClienterDao.selectByPrimaryKey",id);
	}

	@Override
	public Clienter selectByPrimaryKeyWrite(Integer id) {
		return getMasterSqlSessionUtil().selectOne("com.edaisong.api.dao.inter.IClienterDao.selectByPrimaryKey",id);
	}
	/**
	 * 获取用户状态信息
	 * @author CaoHeYang
	 * @param userid
	 * @date 20150911
	 * @return
	 */
	@Override
	public ClienterStatus getUserStatus(int userid) {
		return getReadOnlySqlSessionUtil()
				.selectOne("com.edaisong.api.dao.inter.IClienterDao.getUserStatus",
						userid);
	}

	@Override
	public List<MyBusinessModel> getMyBusiness(MyBusinessReq myBusinessReq) { 
		return getReadOnlySqlSessionUtil().selectList("com.edaisong.api.dao.inter.IClienterDao.getMyBusiness",myBusinessReq);
	}

	@Override
	public MyBusinessResp getServiceBusinessTotal(MyBusinessReq myBusinessReq) {
		return	getReadOnlySqlSessionUtil().selectOne("com.edaisong.api.dao.inter.IClienterDao.getServiceBusinessTotal",myBusinessReq);
		 
	}

	@Override
	public ClienterUserStatusResp getUserStatus(UserStatusReq req) {
		return	getReadOnlySqlSessionUtil().selectOne("com.edaisong.api.dao.inter.IClienterDao.getUserStatusForFlash",req);
	}

	@Override
	public int modifyVehicle(ModifyVehicleReq req) {
		return getMasterSqlSessionUtil().update("com.edaisong.api.dao.inter.IClienterDao.modifyVehicle",req);
	} 
}
