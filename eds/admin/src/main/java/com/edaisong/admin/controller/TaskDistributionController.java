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
import com.edaisong.api.service.inter.ITaskDistributionService;
import com.edaisong.entity.AuthorityRole;
import com.edaisong.entity.OrderTip;
import com.edaisong.entity.TaskDistribution ;
import com.edaisong.entity.TaskDistributionConfig;
import com.edaisong.entity.common.HttpResultModel;
import com.edaisong.entity.common.PagedResponse;
import com.edaisong.entity.domain.BusinessDetailModel;
import com.edaisong.entity.req.PagedClienterBalanceRecordReq;
import com.edaisong.entity.req.ClienterOptionReq;
import com.edaisong.entity.req.PagedClienterReq;
import com.edaisong.entity.req.PagedFeedbackReq;
import com.edaisong.entity.req.PagedOrderTipReq;
import com.edaisong.entity.req.PagedTaskDistributionConfigReq;
import com.edaisong.entity.req.PagedTaskDistributionReq;
import com.edaisong.entity.resp.TaskDistributionConfigResp;
import com.edaisong.entity.resp.TaskDistributionResp;



@Controller
@RequestMapping("taskdistribution")
public class TaskDistributionController {
	 //任务费用
	 @Autowired
	 private ITaskDistributionService  taskDistributionService;	 
	 
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
		model.addObject("viewPath", "taskdistribution/list");
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
	public ModelAndView listdo(PagedTaskDistributionReq req) throws ParseException {		
		
		PagedResponse<TaskDistribution> resp = taskDistributionService.query(req);
		ModelAndView model = new ModelAndView("taskdistribution/listdo");
		model.addObject("listData", resp);
		return model;
	}		
	
	@RequestMapping("add")
	@ResponseBody
	public HttpResultModel<TaskDistributionResp> add(TaskDistribution record, HttpServletRequest request) {
		record.setCreatename(UserContext.getCurrentContext(request).getLoginName());
		record.setCreatetime(new Date());
		record.setUpdatename(UserContext.getCurrentContext(request).getLoginName());
		record.setUpdatetime(new Date());
		HttpResultModel<TaskDistributionResp> resp= taskDistributionService.add(record);
		
		TaskDistributionConfig recordConfig=new TaskDistributionConfig();
		recordConfig.setkG(5);
		recordConfig.setkM(5);
		recordConfig.setDistributionPrice(16);
		recordConfig.setIsMaster(1);
		recordConfig.setTaskDistributionId(record.getId());		
		taskDistributionConfigService.add(recordConfig);
		
		return resp;
	}

	
	@RequestMapping("modify")
	@ResponseBody
	public HttpResultModel<TaskDistributionResp> modify(TaskDistribution  record, HttpServletRequest request) {
		record.setUpdatename(UserContext.getCurrentContext(request).getLoginName());
		record.setUpdatetime(new Date());
		return taskDistributionService.modify(record);
	}
	
	
	@RequestMapping("taskdistributionbindlist")
	public ModelAndView taskdistributionbindlist(int taskDistributionId) throws Exception {

		ModelAndView model = new ModelAndView("adminView");
		model.addObject("subtitle", "管理");
		model.addObject("currenttitle", "配送费规则");
		model.addObject("viewPath", "taskdistribution/taskdistributionbindlist");
		
		TaskDistribution detail= taskDistributionService.selectByPrimaryKey(taskDistributionId);
		model.addObject("detail", detail);
		return model;
	}
	
	@RequestMapping("taskdistributionbindlistdo")
	public ModelAndView taskdistributionbindlistdo(PagedTaskDistributionConfigReq req ) throws Exception {
		PagedResponse<TaskDistributionConfig> resp = taskDistributionConfigService.query(req);
		ModelAndView model = new ModelAndView("taskdistribution/taskdistributionbindlistdo");
		model.addObject("taskDistributionId", req.taskDistributionId);
		model.addObject("listData", resp);
		return model;
	}	

	@RequestMapping("addconfig")
	@ResponseBody
	public HttpResultModel<TaskDistributionConfigResp> add(TaskDistributionConfig record) {
		return taskDistributionConfigService.add(record);
	}

	@RequestMapping("modifyconfig")
	@ResponseBody
	public HttpResultModel<TaskDistributionConfigResp> modify(TaskDistributionConfig record) {
		return taskDistributionConfigService.modify(record);
	}
	
	@RequestMapping("delconfig")
	@ResponseBody
	public int del(Integer id) {
		return taskDistributionConfigService.deleteByPrimaryKey(id);
	}
	
	@RequestMapping("selectbyprimarykeyconfig")
	@ResponseBody
	public TaskDistributionConfig selectByPrimaryKey(int id)
	{
		TaskDistributionConfig model=taskDistributionConfigService.selectByPrimaryKey(id);
		return model;
	}
	/**
	 * 里程计算器
	 * @param record
	 * @return
	 */
	@RequestMapping("calculatorconfig")
	@ResponseBody
	public double calculator(TaskDistributionConfig record) {
		return taskDistributionConfigService.calculator(record);
	}
	
	
}
