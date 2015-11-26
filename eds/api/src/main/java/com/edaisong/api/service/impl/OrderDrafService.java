package com.edaisong.api.service.impl;

import java.text.ParseException;
import java.util.Date;
import java.util.Random;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.edaisong.api.dao.inter.IFeedbackDao;
import com.edaisong.api.dao.inter.IOrderDraftDao;
import com.edaisong.api.service.inter.IFeedbackService;
import com.edaisong.api.service.inter.IOrderDraftService;
import com.edaisong.core.enums.OrderDraftReturn;
import com.edaisong.core.enums.PublishOrderReturnEnum;
import com.edaisong.core.util.OrderNoHelper;
import com.edaisong.core.util.ParseHelper;
import com.edaisong.core.util.StringUtils;
import com.edaisong.entity.Feedback;
import com.edaisong.entity.OrderDraft;
import com.edaisong.entity.common.HttpResultModel;
import com.edaisong.entity.common.PagedResponse;
import com.edaisong.entity.domain.FeedbackModel;
import com.edaisong.entity.req.OrderDraftGetReq;
import com.edaisong.entity.req.OrderDraftReq;
import com.edaisong.entity.req.PagedFeedbackReq;
import com.edaisong.entity.resp.OrderDraftResp;
/*
 * 闪送发单
 * */
@Service
public class OrderDrafService implements IOrderDraftService {
	@Autowired
	private IOrderDraftDao orderDraftDao ;

	@Override
	public HttpResultModel<OrderDraftResp> add(OrderDraftReq req) {
		HttpResultModel<OrderDraftResp> resp=new HttpResultModel<OrderDraftResp>();
		OrderDraft odModel=new OrderDraft();
///*//
////		if(req.getPubname()==null || req.getPubname().equals(""))
////		{
////			resp.setStatus(OrderDraftReturn.PubNameIsNULL.value());
////			resp.setMessage(OrderDraftReturn.PubNameIsNULL.desc());
////			return resp;
////		}
////		if(req.getPubphoneno()==null || req.getPubphoneno().equals(""))
////		{
////			resp.setStatus(OrderDraftReturn.PubPhoneNoIsNULL.value());
////			resp.setMessage(OrderDraftReturn.PubPhoneNoIsNULL.desc());
////			return resp;
////		}
////		if(req.getPubaddress()==null || req.getPubaddress().equals(""))
////		{
////			resp.setStatus(OrderDraftReturn.PubAddressIsNULL.value());
////			resp.setMessage(OrderDraftReturn.PubAddressIsNULL.desc());
////			return resp;
////		}
////		if(req.getTaketype()==null)
////		{
////			resp.setStatus(OrderDraftReturn.TakeTypeIsNULL.value());
////			resp.setMessage(OrderDraftReturn.TakeTypeIsNULL.desc());
////			return resp;
////		}		
////		if(req.getRecevicename()==null || req.getRecevicename().equals(""))
////		{
////			resp.setStatus(OrderDraftReturn.ReceviceNameIsNULL.value());
////			resp.setMessage(OrderDraftReturn.ReceviceNameIsNULL.desc());
////			return resp;
////		}
////		if(req.getRecevicephoneno()==null || req.getRecevicephoneno().equals(""))
////		{
////			resp.setStatus(OrderDraftReturn.RecevicePhoneNoIsNull.value());
////			resp.setMessage(OrderDraftReturn.RecevicePhoneNoIsNull.desc());
////			return resp;
////		}
////		if(req.getReceviceaddress()==null || req.getReceviceaddress().equals(""))
////		{
////			resp.setStatus(OrderDraftReturn.ReceviceAddressIsNull.value());
////			resp.setMessage(OrderDraftReturn.ReceviceAddressIsNull.desc());
////			return resp;
////		}	
////		if(req.getProductname()==null || req.getProductname().equals(""))
////		{
////			resp.setStatus(OrderDraftReturn.ProductNameIsNull.value());
////			resp.setMessage(OrderDraftReturn.ProductNameIsNull.desc());
////			return resp;
////		}
////		if(req.getAmount()==null)
////		{
////			resp.setStatus(OrderDraftReturn.AmountIsNull.value());
////			resp.setMessage(OrderDraftReturn.AmountIsNull.desc());
////			return resp;
////		}
////		if(req.getWeight()==null )
////		{
////			resp.setStatus(OrderDraftReturn.WeightIsNull.value());
////			resp.setMessage(OrderDraftReturn.WeightIsNull.desc());
////			return resp;
////		}
////		if(req.getKm()==null)
////		{
////			resp.setStatus(OrderDraftReturn.KMIsNull.value());
////			resp.setMessage(OrderDraftReturn.KMIsNull.desc());
////			return resp;
////		}
////		
////		if(req.getIslogin())
////		{
////			odModel.setBusinessid(req.getBusinessid());//商家id
////		}
////		else
////		{
////			
////		}
//		//odModel.setOrderno(OrderNoHelper.generateOrderCode(req.getBusinessid()));
//		//odModel.setPubname(req.getPubname());//发货人
//		//odModel.setPubdate(new Date());//发货时间		
//		//odModel.setPublongitude(req.getPublongitude());//发货经度		
//		//odModel.setPublatitude(req.getPublatitude());//发货维度
//		odModel.setPubphoneno(req.getPubphoneno());//发货人手机号
//		odModel.setPubaddress(req.getPubaddress());//发货人地址 
//		odModel.setTaketype(req.getTaketype());//取货状态默认0立即，1预约
//		odModel.setTaketime(req.getTaketime());//取货时间
//		Random random = new Random();
//	    int x = random.nextInt(899999);
//		x = x+100000;
//		odModel.setTakecode(String.valueOf(x));//取货吗	
//		odModel.setTakelongitude(null);//取货经度
//		odModel.setTakelatitude(null);//取货维度
//		//odModel.setRecevicename(req.getRecevicename());//收货人姓名
//		//odModel.setRecevicephoneno(req.getRecevicephoneno());//收货人手机号
//		//odModel.setReceviceaddress(req.getReceviceaddress());//收货人地址
//		odModel.setRecevicelongitude(req.getRecevicelongitude());//收货人经度
//		odModel.setRecevicelatitude(req.getRecevicelatitude());//收货人维度
//		//odModel.setRecevicecity(null);//收货人城市
//		odModel.setReceiveprovince(null);//收货人省份
//		odModel.setReceivearea(null);//收货区域
//		odModel.setReceiveprovincecode(null);//收货人城市代码
//		odModel.setReceivecitycode(null);//收货人城市代码
//		odModel.setReceiveareacode(null);////收货区域 代码
//		//odModel.setIspay(false);//付款方式
//		odModel.setProductname(req.getProductname());//物品名称
//		//odModel.setRemark(req.getRemark());//备注
//		//odModel.setAmount(req.getAmount());//金额				
//		odModel.setWeight(req.getWeight());//订单总重量
//		odModel.setKm(req.getKm());//	距离
//		
//		int odId=orderDraftDao.insertSelective(odModel);
//		if(odId>0)
//		{
//			resp.setStatus(PublishOrderReturnEnum.Success.value());
//			resp.setMessage(PublishOrderReturnEnum.Success.desc());
//			return resp;
//		}*/
		return resp;
	}

	/**
	 * 根据未生效订
	 * 
	 * @author 胡灵波
	 * @date 2015年11月25日 17:53:35
	 * @version 1.0
	 * @param id
	 * @return
	 */
	@Override
	public HttpResultModel<OrderDraft> selectByPrimaryKey(Integer id) {
		HttpResultModel<OrderDraft> resp=new HttpResultModel<OrderDraft>();
		OrderDraft odModel=orderDraftDao.selectByPrimaryKey(id);
		resp.setResult(odModel);		
		resp.setStatus(PublishOrderReturnEnum.Success.value());
		resp.setMessage(PublishOrderReturnEnum.Success.desc());
		return resp;
	}
}