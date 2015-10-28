package com.edaisong.business.controller;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import com.edaisong.api.service.inter.IBusinessMessageService;
import com.edaisong.business.common.UserContext;
import com.edaisong.entity.BusinessMessage;
import com.edaisong.entity.common.PagedRequestBase;
import com.edaisong.entity.common.PagedResponse;
import com.edaisong.entity.req.PagedBusinessMessageReq;

@Controller
@RequestMapping("message")
public class MessageController {
	@Autowired
	private IBusinessMessageService businessMessageService;
	/**
	 * 消息列表页
	 * 
	 * @author pengyi
	 * @date 20150821
	 * @return
	 */
	@RequestMapping("list")
	public ModelAndView list() {
		ModelAndView view = new ModelAndView("businessView");
		view.addObject("subtitle", "消息中心");
		view.addObject("currenttitle", "消息中心");
		view.addObject("viewPath", "message/list");
		return view;
	}
	
	/**
	 * 消息列表分页
	 * @param 分页请求信息
	 * @param request
	 * @return
	 */
	@RequestMapping("listdo")
	public ModelAndView listdo(PagedBusinessMessageReq searchWebReq,HttpServletRequest request) {
		ModelAndView view = new ModelAndView("message/listdo");
		int businessId = UserContext.getCurrentContext(request).getBusinessID();
		searchWebReq.setBusinessId(businessId);
		PagedResponse<BusinessMessage> resp = businessMessageService.getMessages(searchWebReq);
		view.addObject("listData", resp);
		return view;
	}
}
