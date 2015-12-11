package com.edaisong.admin.controller;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.velocity.app.event.ReferenceInsertionEventHandler.referenceInsertExecutor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.edaisong.admin.common.UserContext;
import com.edaisong.api.service.inter.IQuartzService;
import com.edaisong.core.quartz.QuartzManager;
import com.edaisong.core.util.SpringBeanHelper;
import com.edaisong.entity.QuartzServiceModel;
import com.edaisong.entity.common.PagedRequestBase;
import com.edaisong.entity.common.PagedResponse;
import com.edaisong.entity.req.PagedQuartzServiceReq;
import com.edaisong.entity.req.QuartzUpdateReq;

@Controller
@RequestMapping("service")
public class ServiceControler {

	@Autowired
	IQuartzService quartzService;

	/**
	 * @author haichao
	 * @date 2015年12月10日 10:15:58 获取所有服务列表
	 * */
	@RequestMapping("list")
	public ModelAndView list(HttpServletRequest request,
			HttpServletResponse response) {
		ModelAndView model = new ModelAndView("adminView");
		model.addObject("subtitle", "服务管理");
		model.addObject("currenttitle", "服务管理");
		model.addObject("viewPath", "service/list");

		return model;
	}

	@RequestMapping("listdo")
	public ModelAndView listdo(PagedQuartzServiceReq req) {
		ModelAndView model = new ModelAndView("service/listdo");
		PagedResponse<QuartzServiceModel>  list = quartzService.pagedQuery(req);
		model.addObject("listData", list); // 获取所有服务列表

		return model;
	}

	@ResponseBody
	@RequestMapping(value="updatestatus")
	public int updateStatus(QuartzUpdateReq req,HttpServletRequest request) {
		UserContext context=UserContext.getCurrentContext(request);
		req.setUpdateName(context.getUserName());
		return quartzService.mainJob(req);
	}
	/**
	 * 新增一个服务
	 * @author hailongzhao
	 * @param record
	 * @param request
	 * @return
	 */
	@ResponseBody
	@RequestMapping(value="insert")
	public int insert(QuartzServiceModel record,HttpServletRequest request) {
		UserContext context=UserContext.getCurrentContext(request);
		record.setCreateName(context.getUserName());
		record.setUpdateName(context.getUserName());
		return quartzService.insert(record);
	}
	/**
	 * 保存一个服务的配置信息
	 * @param record
	 * @param optype
	 * @param request
	 * @return
	 */
	@ResponseBody
	@RequestMapping(value="save")
	public int save(QuartzServiceModel record,int optype,HttpServletRequest request) {
		String beanName=getBeanName(record.getBeanName());
		try {
			SpringBeanHelper.getCustomBean(beanName);
		} catch (Exception e) {
			return -1;
		}
		UserContext context=UserContext.getCurrentContext(request);
		record.setCreateName(context.getUserName());
		record.setUpdateName(context.getUserName());
		record.setBeanName(beanName);
		if (optype==0) {//0表示修改，1表示新增
			return quartzService.update(record);
		}else {
			return quartzService.insert(record);
		}
	}
	private String getBeanName(String beanName){
		String result=beanName;
		int index = result.lastIndexOf(".");
		if (index > 0) {
			result = result.substring(index + 1);
		}

		if (!Character.isLowerCase(result.charAt(0))) {
			result = Character.toLowerCase(result.charAt(0))
					+ result.substring(1);
		}
		return result;
	}
	/**
	 * 检查cron表达式是否有效
	 * @param cron
	 * @return
	 */
	@ResponseBody
	@RequestMapping(value="checkcron")
	public int checkCron(String cron) {
		if(quartzService.checkCron(cron)){
			return 1;
		}
		return 0;
	}
}
