package com.edaisong.api.dao.inter;

import com.edaisong.entity.Clienter;
import com.edaisong.entity.common.PagedResponse;
import com.edaisong.entity.domain.BusinessClientersModel;
import com.edaisong.entity.domain.ClienterModel;
import com.edaisong.entity.req.ClienterOptionReq;
import com.edaisong.entity.req.ClienterReq;
import com.edaisong.entity.req.PagedBusinessClientersReq;
import com.edaisong.entity.req.PagedClienterSearchReq;


public interface IClienterDao {

    int updateByPrimaryKeySelective(Clienter record);

    int updateByPrimaryKey(Clienter record);    
    
    int updateMoneyById(ClienterOptionReq req) ;    
    
    PagedResponse<ClienterModel> query(ClienterReq req);
    
    PagedResponse<BusinessClientersModel> getBusinessClienters(PagedBusinessClientersReq req);

  
    /**
     * 更新骑士余额
     * @param amount
     * @param clienterId
     *  @Date 20150831
	 * @param business
     */
	int updateCAccountBalance(Double amount, int clienterId);

	/**
	 * 更新骑士可提现金额
	 * @author CaoHeYang
	 * @param amount
	 * @param clienterId
	 * @date 20150831
	 * @return
	 */
	int updateCAllowWithdrawPrice(Double amount, int clienterId);
	/**
	 * 更新骑士余额和可提现金额
	 * @author CaoHeYang
	 * @param amount
	 * @param clienterId
	 * @date 20150831
	 * @return
	 */
	int updateCBalanceAndWithdraw(Double amount, int clienterId);
	
	boolean updateClienterIsBind(int clienterId, int isBind);
	
	/**
	 * 获得骑士绑定信息列表
	 * @author pengyi
	 * @date 20150901
	 * @param req
	 * @return
	 */
	PagedResponse<ClienterModel> getClienterBindInfoList(PagedClienterSearchReq req);
}