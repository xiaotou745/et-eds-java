package com.edaisong.toolsadmin.controller;


import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.edaisong.toolsadmin.common.UserContext;
import com.edaisong.toolsapi.service.inter.IQuartzService;
import com.edaisong.toolsentity.QuartzServiceModel;
import com.edaisong.toolsentity.common.PagedResponse;
import com.edaisong.toolsentity.domain.QuartzHttpModel;
import com.edaisong.toolsentity.req.PagedQuartzServiceReq;
import com.edaisong.toolsentity.req.QuartzUpdateReq;

@Controller
@RequestMapping("quartz")
public class ServiceControler {

	@Autowired
	IQuartzService quartzService;

/**
 * 服务管理页面
 * @author hailongzhao
 * @date 20160217
 * @return
 */
	@RequestMapping("list")
	public ModelAndView list() {
		ModelAndView model = new ModelAndView("adminView");
		model.addObject("subtitle", "服务管理");
		model.addObject("currenttitle", "服务管理");
		model.addObject("viewPath", "service/list");
		return model;
	}
	/**
	 * 服务管理页面分页
	 * @author hailongzhao
	 * @date 20160217
	 * @return
	 */
	@RequestMapping("listdo")
	public ModelAndView listdo(PagedQuartzServiceReq req) {
		ModelAndView model = new ModelAndView("service/listdo");
		PagedResponse<QuartzServiceModel>  list = quartzService.pagedQuery(req);
		model.addObject("listData", list); // 获取所有服务列表

		return model;
	}
	/**
	 * 停止或启动服务
	 * @author hailongzhao
	 * @date 20160217
	 * @return
	 */
	@ResponseBody
	@RequestMapping(value="updatestatus")
	public int updateStatus(QuartzUpdateReq req,HttpServletRequest request) {
		UserContext context=UserContext.getCurrentContext(request);
		req.setUpdateName(context.getUserName());
		return quartzService.updateStatus(req);
	}
	/**
	 * 新增一个服务
	 * @author hailongzhao
	 * @date 20160217
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
	 * @author hailongzhao
	 * @date 20160217
	 * @param record
	 * @param optype
	 * @param request
	 * @return
	 */
	@ResponseBody
	@RequestMapping(value="save")
	public int save(QuartzServiceModel record,int optype,HttpServletRequest request) {
		if(!quartzService.checkCron(record.getExecTime())){
			return -2;
		}
		UserContext context=UserContext.getCurrentContext(request);
		record.setCreateName(context.getUserName());
		record.setUpdateName(context.getUserName());
		if (optype==0) {//0表示修改，1表示新增
			return quartzService.update(record);
		}else {
			return quartzService.insert(record);
		}
	}
	@ResponseBody
	@RequestMapping(value="del")
	public int deleteService(long id) {
		return quartzService.delete(id);
	}
	/**
	 * qutartzservice调用的获取已启动的服务列表
	 * @author hailongzhao
	 * @date 20160217
	 * @param appSource
	 * @param versionCode
	 * @return
	 */
	@RequestMapping("querystartlist")
	@ResponseBody
	public QuartzHttpModel queryStartList(Integer appSource,String versionCode) {
		QuartzHttpModel result= quartzService.queryStartList(appSource);
		//如果版本号一致，则说明没有变更，则返回空集合，减少网络传输
		//此处必须和qutartzservice中的Main.refreshDo保持一致
		if (result!=null&&result.getVersionCode().equals(versionCode)) {
			result.setListData(null);
		}
		return result;
	}
}
