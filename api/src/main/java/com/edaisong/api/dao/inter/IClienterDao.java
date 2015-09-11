package com.edaisong.api.dao.inter;

import java.util.List;

import com.edaisong.entity.Clienter;
import com.edaisong.entity.common.PagedResponse;
import com.edaisong.entity.domain.BusinessClientersModel;
import com.edaisong.entity.domain.ClienterBindInfoModel;
import com.edaisong.entity.domain.ClienterModel;
import com.edaisong.entity.domain.ImportClienterInfo;
import com.edaisong.entity.req.ClienterOptionReq;
import com.edaisong.entity.req.PagedClienterReq;
import com.edaisong.entity.req.PagedBusinessClientersReq;
import com.edaisong.entity.req.PagedClienterSearchReq;


public interface IClienterDao {

    int updateByPrimaryKeySelective(Clienter record);

    int updateByPrimaryKey(Clienter record);    
    
    int updateMoneyById(ClienterOptionReq req) ;    
    
    PagedResponse<ClienterModel> query(PagedClienterReq req);
    
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
	PagedResponse<ClienterBindInfoModel> getClienterBindInfoList(PagedClienterSearchReq req);
	
	/**
	 * 根据电话获得骑士姓名
	 * @author pengyi
	 * @date 2015年9月7日 下午1:49:29
	 * @version 1.0
	 * @param phoneNos
	 * @return
	 */
	List<ImportClienterInfo> getInfosByPhones(List<String> phoneNos);
	
	String getNameByPhone(String phoneNo);
	
	/**
	 * 根据电话和姓名获得骑士id
	 * @author pengyi
	 * @date 2015年9月7日 下午2:47:04
	 * @version 1.0
	 * @param phoneNo
	 * @param trueName
	 * @return
	 */
	Integer getId(String phoneNo, String trueName);
	
	/**
	 * 根据骑士id获得实体对象
	 * @author pengyi
	 * @date 2015年9月11日 上午9:51:13
	 * @version 1.0
	 * @param id
	 * @return
	 */
	Clienter selectByPrimaryKey(Integer id);
}