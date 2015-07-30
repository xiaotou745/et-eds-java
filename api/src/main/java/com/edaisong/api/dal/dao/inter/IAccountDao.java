package com.edaisong.api.dal.dao.inter;

import java.util.List;

import com.edaisong.entity.Account;
import com.edaisong.entity.common.PagedResponse;
import com.edaisong.entity.req.AccountReq;
import com.edaisong.entity.resp.AccountResp;

public interface IAccountDao {
	
	PagedResponse<Account> query(AccountReq req);
	
    int deleteByPrimaryKey(Integer id);

    int insert(Account record);

    int insertSelective(Account record);

    Account selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(Account record);

    int updateByPrimaryKey(Account record);
}