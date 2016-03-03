package com.edaisong.business.controller;

import java.util.Date;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.edaisong.api.service.inter.IGroupBusinessRelationService;
import com.edaisong.business.common.UserContext;
import com.edaisong.core.enums.BindOptType;
import com.edaisong.entity.common.PagedResponse;
import com.edaisong.entity.domain.GroupBusinessRelationModel;
import com.edaisong.entity.req.BusinessBindOptionReq;
import com.edaisong.entity.req.PagedBizBindsReq;

@Controller
@RequestMapping("businessmanager")
public class BusinessManagerController {
	
	@Autowired
	private IGroupBusinessRelationService groupBusinessRelationService;
	/**
	 * 订单列表页面
	 * 
	 * @author zhaohailong
	 * @Date 20150806
	 * @return
	 */
	@RequestMapping("list")
	public ModelAndView list(HttpServletRequest request) {
		ModelAndView view = new ModelAndView("businessView");
		
		view.addObject("subtitle", "门店管理");
		view.addObject("currenttitle", "门店列表");
		view.addObject("viewPath", "businessmanager/list");
		String string=groupBusinessRelationService.getGroupBusListString(UserContext.getCurrentContext(request).getBusinessID());
		view.addObject("BusList",string );
		return view;
	}
	/**
	 * 订单列表页面
	 * 
	 * @author zhaohailong
	 * @Date 20150806
	 * @return
	 */
	@RequestMapping("listdo")
	public ModelAndView listdo(PagedBizBindsReq req,HttpServletRequest request) {
		
		ModelAndView view = new ModelAndView("businessmanager/listdo");
		req.setGroupBusinessId(UserContext.getCurrentContext(request).getBusinessID());//设置ID
		PagedResponse<GroupBusinessRelationModel> resp = groupBusinessRelationService.getBusinessBindList(req);
		view.addObject("listData", resp);
		return view;
	}
	
	/**
	 * 订单列表页面
	 * 
	 * @author zhaohailong
	 * @Date 20150806
	 * @return
	 */
	@RequestMapping("removebind")
	@ResponseBody
	public int removebind(int bid,HttpServletRequest request) {
		BusinessBindOptionReq req=new BusinessBindOptionReq();
		req.setGroupId(UserContext.getCurrentContext(request).getBusinessID());
		req.setBusinessId(bid);
		req.setOptId(UserContext.getCurrentContext(request).getBusinessID());
		req.setOptType((short)BindOptType.RemoveBind.value());
		req.setOptName(UserContext.getCurrentContext(request).getBusinessName());
		req.setRemark("解除绑定");
		return groupBusinessRelationService.removeBusinessBind(req)?1:0;
	}

}
