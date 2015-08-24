
package com.edaisong.business.controller;


import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import com.edaisong.api.service.inter.IClienterService;
import com.edaisong.business.entity.UserContext;
import com.edaisong.entity.BusinessMessage;
import com.edaisong.entity.common.PagedResponse;
import com.edaisong.entity.domain.BusinessClientersModel;
import com.edaisong.entity.domain.OrderListModel;
import com.edaisong.entity.req.PagedBusinessClientersReq;
import com.edaisong.entity.req.PagedCustomerSearchReq;

@Controller
@RequestMapping("clienter")
public class ClienterController {
	@Autowired
	private IClienterService clienterService;
	
	@RequestMapping("list")
	public ModelAndView list() {
		ModelAndView model = new ModelAndView("businessView");
		model.addObject("subtitle", "骑士管理");
		model.addObject("currenttitle", "骑士管理");
		model.addObject("viewPath", "clienter/list");
		return model;
	}
	
	/**
	 * 骑士列表
	 * @author pengyi
	 * @param 分页请求信息
	 * @param request
	 * @return
	 */
	@RequestMapping("listdo")
	public ModelAndView listdo(PagedBusinessClientersReq searchWebReq,HttpServletRequest request) {
		ModelAndView view = new ModelAndView("clienter/listdo");   
		int businessId = UserContext.getCurrentContext(request).getBusiness().getId();
		searchWebReq.setBusinessId(businessId);
		PagedResponse<BusinessClientersModel> resp = clienterService.getBusinessClienters(searchWebReq);
		view.addObject("listData", resp);
		return view;
	}
	
	/**
	 * 商家中心骑士列表页面右上角自定义查询
	 * @author pengyi
	 * @date 20150821
	 * @return
	 */
	@RequestMapping("customerlistdo")
	public ModelAndView customerlistdo(String search,Integer CurrentPage,HttpServletRequest request) {
		PagedBusinessClientersReq req=new PagedBusinessClientersReq();
		req.setBusinessId(UserContext.getCurrentContext(request).getBusiness().getId());
		req.setSearch(search);
		req.setCurrentPage(CurrentPage);
		req.setWorkStatus(2);//2为查询全部上班状态,查询骑士名字或手机号时不需要查询上班状态
		PagedResponse<BusinessClientersModel> resp = clienterService.getBusinessClienters(req);
		ModelAndView view = new ModelAndView("clienter/listdo");
		view.addObject("listData", resp);
		return view;
	}
}
