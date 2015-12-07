package com.edaisong.api.service.impl;

import java.util.Date;
import java.util.List;

import org.omg.CORBA.Request;
import org.apache.activemq.transport.stomp.FrameTranslator.Helper;
import org.apache.poi.openxml4j.opc.StreamHelper;
import org.fusesource.hawtbuf.codec.VariableCodec;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.edaisong.api.dao.inter.IBusinessBalanceRecordDao;
import com.edaisong.api.dao.inter.IBusinessDao;
import com.edaisong.api.dao.inter.IGroupBusinessDao;
import com.edaisong.api.dao.inter.IMarkDao;
import com.edaisong.api.dao.inter.IOrderTipDao;
import com.edaisong.api.redis.RedisService;
import com.edaisong.api.service.inter.IBusinessService; 
import com.edaisong.api.service.inter.IOrderTipService;
import com.edaisong.core.consts.GlobalSettings;
import com.edaisong.core.consts.RedissCacheKey;
import com.edaisong.core.enums.BusinessPushOrderType;
import com.edaisong.core.enums.BusinessStatusEnum;
import com.edaisong.core.security.MD5Util;
import com.edaisong.core.util.HttpUtil;
import com.edaisong.core.util.ParseHelper;
import com.edaisong.core.util.PropertyUtils;
import com.edaisong.entity.Business;
import com.edaisong.entity.BusinessBalanceRecord;
import com.edaisong.entity.BusinessClienterRelation;
import com.edaisong.entity.BusinessExpressRelation;
import com.edaisong.entity.BusinessLoginLog;
import com.edaisong.entity.BusinessOptionLog;
import com.edaisong.entity.GroupBusiness;
import com.edaisong.entity.OrderTip;
import com.edaisong.entity.common.HttpResultModel;
import com.edaisong.entity.common.PagedResponse;
import com.edaisong.entity.common.ResponseCode;
import com.edaisong.entity.domain.BindClienterBusiness;
import com.edaisong.entity.domain.BusinessBasicInfoModel;
import com.edaisong.entity.domain.BusinessDetailModel;
import com.edaisong.entity.domain.BusinessModel;
import com.edaisong.entity.domain.BusinessModifyModel;
import com.edaisong.entity.domain.BusinessRechargeDetailModel;
import com.edaisong.entity.domain.TagRelationModel;
import com.edaisong.entity.domain.OrderRespModel;
import com.edaisong.entity.req.BCheckCodeReq;
import com.edaisong.entity.req.BusinessMoney;
import com.edaisong.entity.req.BusinessRegisterReq;
import com.edaisong.entity.req.ModifyTagReq;
import com.edaisong.entity.req.BusinessReq;
import com.edaisong.entity.req.GetPushOrderTypeReq;
import com.edaisong.entity.req.MyOrderBReq;
import com.edaisong.entity.req.OrderDetailBReq;
import com.edaisong.entity.req.PagedBusinessReq;
import com.edaisong.entity.resp.BusinessLoginResp;
import com.edaisong.entity.resp.MyOrderBResp;
import com.edaisong.entity.resp.MyOrderDetailBResp;

@Service
public class OrderTipService implements IOrderTipService {

	@Autowired
	private IOrderTipDao iOrderTipDao;

	@Override
	public List<OrderTip> getList() {
		return iOrderTipDao.getList();
	}


	
}
