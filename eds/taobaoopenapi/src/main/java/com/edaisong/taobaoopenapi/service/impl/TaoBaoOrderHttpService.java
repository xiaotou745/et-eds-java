package com.edaisong.taobaoopenapi.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;



import com.edaisong.entity.taobao.TaoBaoResponseBase;
import com.edaisong.entity.taobao.req.TaoBaoAsk;
import com.edaisong.entity.taobao.req.TaoBaoConfirm;
import com.edaisong.entity.taobao.req.TaoBaoLocationUpdate;
import com.edaisong.entity.taobao.req.TaoBaoPickUp;
import com.edaisong.entity.taobao.req.TaoBaoUpdate;
import com.edaisong.taobaoopenapi.service.inter.ITaoBaoOrderHttpService;

/**
 * 订单模块
 * 
 * @author CaoHeYang
 * @date 20150910
 */
@Service
public class TaoBaoOrderHttpService implements ITaoBaoOrderHttpService {
	/**
	 * 确认接单接口(API)
	 * 
	 * @param req
	 * @return
	 */
	@Override
	public TaoBaoResponseBase ask(TaoBaoAsk req) {
		// TODO Auto-generated method stub
		return null;
	}
	/**
	 * 更新配送员信息接口（API）
	 * 
	 * @param req
	 * @return
	 */
	@Override
	public TaoBaoResponseBase update(TaoBaoUpdate req) {
		// TODO Auto-generated method stub
		return null;
	}
	/**
	 * 取件（API）
	 * 
	 * @param req
	 * @return
	 */
	@Override
	public TaoBaoResponseBase pickUp(TaoBaoPickUp req) {
		// TODO Auto-generated method stub
		return null;
	}
	/**
	 * 妥投（API）
	 * 
	 * @param req
	 * @return
	 */
	@Override
	public TaoBaoResponseBase confirm(TaoBaoConfirm req) {
		// TODO Auto-generated method stub
		return null;
	}
	/**
	 * 更新配送员位置信息（API）
	 * 
	 * @param req
	 * @return
	 */
	@Override
	public TaoBaoResponseBase locationUpdate(TaoBaoLocationUpdate req) {
		// TODO Auto-generated method stub
		return null;
	}

	

}
