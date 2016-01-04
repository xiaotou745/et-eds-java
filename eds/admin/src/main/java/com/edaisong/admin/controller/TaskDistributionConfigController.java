package com.edaisong.admin.controller;


import java.text.ParseException;
import java.util.Date;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

















import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.edaisong.admin.common.UserContext;
import com.edaisong.api.service.inter.IOrderTipService;
import com.edaisong.api.service.inter.ITaskDistributionConfigService;
import com.edaisong.entity.AuthorityRole;
import com.edaisong.entity.OrderTip;
import com.edaisong.entity.TaskDistributionConfig;
import com.edaisong.entity.common.HttpResultModel;
import com.edaisong.entity.common.PagedResponse;
import com.edaisong.entity.req.PagedClienterBalanceRecordReq;
import com.edaisong.entity.req.ClienterOptionReq;
import com.edaisong.entity.req.PagedClienterReq;
import com.edaisong.entity.req.PagedFeedbackReq;
import com.edaisong.entity.req.PagedOrderTipReq;
import com.edaisong.entity.req.PagedTaskDistributionConfigReq;
import com.edaisong.entity.resp.TaskDistributionConfigResp;



@Controller
@RequestMapping("taskdistributionconfig")
public class TaskDistributionConfigController {
	 //任务费用
	 @Autowired
	 private ITaskDistributionConfigService  taskDistributionConfigService;	 

	/**
	 * 任务费用列表管理页面 
	 * @author hulignbo
	 * @Date  
	 * @param search 查询条件实体
	 * @return
	 */
	@RequestMapping("list")
	public ModelAndView list(){			
		ModelAndView model = new ModelAndView("adminView");
		model.addObject("subtitle", "管理");
		model.addObject("currenttitle", "配送费配置");	    
		model.addObject("viewPath", "taskdistributionconfig/list");
		return model;	
	}	
	
	/**
	 * 任务费用列表列表页面 
	 * @author hulignbo
	 * @Date 
	 * @param search 查询条件实体
	 * @return
	 * @throws ParseException 
	 */	
	@RequestMapping("listdo")
	public ModelAndView listdo(PagedTaskDistributionConfigReq req) throws ParseException {		
		
		PagedResponse<TaskDistributionConfig> resp = taskDistributionConfigService.query(req);
		ModelAndView model = new ModelAndView("taskdistributionconfig/listdo");
		model.addObject("listData", resp);
		return model;
	}		
	
	@RequestMapping("add")
	@ResponseBody
	public HttpResultModel<TaskDistributionConfigResp> add(TaskDistributionConfig record) {
		return taskDistributionConfigService.add(record);
	}
   
	
	@RequestMapping("modify")
	@ResponseBody
	public HttpResultModel<TaskDistributionConfigResp> modify(TaskDistributionConfig record) {
		return taskDistributionConfigService.modify(record);
	}


}
