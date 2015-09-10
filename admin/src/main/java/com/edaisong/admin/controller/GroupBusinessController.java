package com.edaisong.admin.controller;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.edaisong.entity.GroupBusiness;
import com.edaisong.entity.domain.GroupBusinessModel;
import com.edaisong.entity.req.PagedGroupBusinessReq;
import com.edaisong.admin.common.UserContext;
import com.edaisong.api.service.inter.IGroupBusinessBindOptionLogService;
import com.edaisong.api.service.inter.IGroupBusinessRelationService;
import com.edaisong.api.service.inter.IGroupBusinessService;
import com.edaisong.core.enums.BindOptType;
import com.edaisong.entity.common.PagedResponse;
import com.edaisong.entity.common.ResponseBase;
import com.edaisong.entity.domain.GroupBusinessBindOptionLogModel;
import com.edaisong.entity.domain.GroupBusinessRelationModel;
import com.edaisong.entity.req.BusinessBindOptionReq;
import com.edaisong.entity.req.PagedBizBindsReq;
import com.edaisong.entity.req.PagedBusinessBindLogReq;

/**
 * 集团商户controller
 * @author  pengyi 
 * @date 2015年9月9日 上午10:20:39
 * @version 1.0
 * @parameter
 * @since
 */
@Controller
@RequestMapping("groupbusiness")
public class GroupBusinessController {
	
	@Autowired
	private IGroupBusinessRelationService groupBusinessRelationService;
	@Autowired
	private IGroupBusinessBindOptionLogService groupBusinessBindOptionLogService;
	@Autowired
	 private IGroupBusinessService  groupBusinessService;
	/*
	 * 集团商户管理
	 * WangChao
	 */
	@RequestMapping("list")
	public ModelAndView list(){ 
		ModelAndView model = new ModelAndView("adminView");
		model.addObject("subtitle", "集团商户管理");
		model.addObject("currenttitle", "集团商户管理"); 
		model.addObject("viewPath", "groupbusiness/list");
		return model;
	}
	/*
	 * 集团管理列表
	 * WangChao
	 */
	@RequestMapping("listdo")
	public ModelAndView listdo(PagedGroupBusinessReq req) {	 
		PagedResponse<GroupBusiness> resp = groupBusinessService.getBusinessList(req); 
		ModelAndView model = new ModelAndView("groupbusiness/listdo");
		model.addObject("listData", resp);
		return model;
	}	
	/**
	 * 绑定列表(分页)
	 * @author pengyi
	 * @date 2015年9月9日 下午5:35:31
	 * @version 1.0
	 * @param groupBusinessId
	 * @return
	 */
	@RequestMapping("businessbindlist")
	public ModelAndView businessBindList(int groupBusinessId){
		ModelAndView model = new ModelAndView("adminView");
		model.addObject("subtitle", "集团管理");
		model.addObject("currenttitle", "集团门店管理");
		model.addObject("viewPath", "groupbusiness/businessbindlist");
		return model;
	}
	
	@RequestMapping("businessbindlistdo")
	public ModelAndView businessBindListdo(PagedBizBindsReq req){
		PagedResponse<GroupBusinessRelationModel> resp = groupBusinessRelationService.getBusinessBindList(req);
		ModelAndView model = new ModelAndView("groupbusiness/businessbindlistdo");
		model.addObject("listData", resp);
		return model;
	}
	
	/**
	 * 绑定日志列表(分页)
	 * @author pengyi
	 * @date 2015年9月9日 下午5:35:16
	 * @version 1.0
	 * @param groupBusinessId
	 * @return
	 */
	@RequestMapping("businessbindloglist")
	public ModelAndView businessBindLogList(int groupBusinessId){
		ModelAndView model = new ModelAndView("adminView");
		model.addObject("subtitle", "集团管理");
		model.addObject("currenttitle", "绑定记录");
		model.addObject("viewPath", "groupbusiness/businessbindloglist");
		return model;
	}
	
	@RequestMapping("businessbindloglistdo")
	public ModelAndView businessBindLogListdo(PagedBusinessBindLogReq req){
		PagedResponse<GroupBusinessBindOptionLogModel> resp = groupBusinessBindOptionLogService.getBusinessBindLogList(req);
		ModelAndView model = new ModelAndView("groupbusiness/businessbindloglistdo");
		model.addObject("listData", resp);
		return model;
	}
	
	/**
	 * 门店列表(绑定门店用)
	 * @author pengyi
	 * @date 2015年9月10日 上午10:13:12
	 * @version 1.0
	 * @param groupBusinessId
	 * @return
	 */
	@RequestMapping("businesslist")
	public ModelAndView businessList(int groupBusinessId){
		ModelAndView model = new ModelAndView("adminView");
		model.addObject("subtitle", "集团管理");
		model.addObject("currenttitle", "绑定记录");
		model.addObject("viewPath", "groupbusiness/businesslist");
		return model;
	}
	
	@RequestMapping("businesslistdo")
	public ModelAndView businessListdo(PagedBizBindsReq req){
		PagedResponse<GroupBusinessRelationModel> resp = groupBusinessRelationService.getBusinessList(req);
		ModelAndView model = new ModelAndView("groupbusiness/businesslist");
		model.addObject("listData", resp);
		return model;
	}
	
	@RequestMapping("removebusinessbind")
	@ResponseBody
	public ResponseBase removeBusinessBind(BusinessBindOptionReq req, HttpServletRequest request) {
		ResponseBase response = new ResponseBase();
		req.setOptId(UserContext.getCurrentContext(request).getId());
		req.setOptType((short)BindOptType.RemoveBind.value());
		req.setOptName(UserContext.getCurrentContext(request).getName());
		req.setRemark("解除绑定");
		req.setBusinessId(req.getBusinessId());
		req.setGroupId(req.getGroupId());
		if (groupBusinessRelationService.removeBusinessBind(req)) {
			response.setMessage("绑定成功");
			return response;
		}
		response.setMessage("绑定失败");
		return response;
	}
	
	@RequestMapping("addbusinessbind")
	@ResponseBody
	public ResponseBase addBusinessBind(BusinessBindOptionReq req, HttpServletRequest request) {
		ResponseBase response = new ResponseBase();
		req.setOptId(UserContext.getCurrentContext(request).getId());
		req.setOptType((short)BindOptType.Bind.value());
		req.setOptName(UserContext.getCurrentContext(request).getName());
		req.setRemark("添加绑定");
		req.setBusinessId(req.getBusinessId());
		req.setGroupId(req.getGroupId());
		if (groupBusinessRelationService.checkHaveBind(req)) {
			response.setMessage("此条绑定关系已存在！");
		}else{
			if (groupBusinessRelationService.addBusinessBind(req)) {
				response.setMessage("绑定成功");
				response.setResponseCode(1);
			}
		}
		return response;
	}
}
