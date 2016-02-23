package com.edaisong.business.controller;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import com.edaisong.api.service.inter.IGroupBusinessRelationService;
import com.edaisong.business.common.UserContext;
import com.edaisong.entity.common.PagedResponse;
import com.edaisong.entity.domain.GroupBusinessRelationModel;
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
	public ModelAndView list() {
		ModelAndView view = new ModelAndView("businessView");
		view.addObject("subtitle", "门店管理");
		view.addObject("currenttitle", "门店列表");
		view.addObject("viewPath", "businessmanager/list");
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
		view.addObject("listData", "门店管理");
		return view;
	}

}
