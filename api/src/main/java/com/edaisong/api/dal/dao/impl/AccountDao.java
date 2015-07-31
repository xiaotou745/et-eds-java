package com.edaisong.api.dal.dao.impl;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Repository;

import com.edaisong.api.dal.dao.inter.IAccountDao;
import com.edaisong.core.common.ParseHelper;
import com.edaisong.entity.Account;
import com.edaisong.entity.common.PagedResponse;
import com.edaisong.entity.req.AccountReq;
import com.edaisong.entity.resp.AccountResp;

@Repository
public class AccountDao extends DaoBase implements IAccountDao {
	// 查询所有管理后台用户列表
	@Override
	public PagedResponse<Account> query(AccountReq req) {

		Map<String, Object> map = new HashMap<String, Object>();
		System.out.println(req.getKeyword());
		String Where = " 1=1 ";
		if (req.getKeyword() != null && req.getKeyword() != "") {
			Where = " UserName like '%" + req.getKeyword() + "%'";

		}
		int PageSize = 10;
		int CurrentPage = req.getCurrentPage();
		map.put("Where", Where);
		map.put("TotalRecord", 0);
		map.put("TotalPage", 0);
		map.put("PageSize", PageSize);
		map.put("CurrentPage", CurrentPage);
		List<com.edaisong.entity.Account> list = getReadOnlySqlSessionUtil()
				.selectList("com.edaisong.api.dal.dao.inter.IAccountDao.query",
						map);

		PagedResponse<Account> resp = new PagedResponse<Account>();
		resp.setResultList(list);
		resp.setPageSize(PageSize);
		resp.setCurrentPage(CurrentPage);
		resp.setTotalRecord(ParseHelper.ToInt(map.get("TotalRecord"), 0));
		resp.setTotalPage(ParseHelper.ToInt(map.get("TotalPage"), 0));
		return resp;
	}

	@Override
	public int deleteByPrimaryKey(Integer id) {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public int insert(Account record) {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public int insertSelective(Account record) {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public Account selectByPrimaryKey(Integer id) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public int updateByPrimaryKeySelective(Account record) {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public int updateByPrimaryKey(Account record) {
		// TODO Auto-generated method stub
		return 0;
	}

}
