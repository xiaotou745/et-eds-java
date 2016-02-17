package com.edaisong.toolsadmin.controller;

import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.edaisong.toolsadmin.common.UserContext;
import com.edaisong.toolsapi.common.ToolsHelper;
import com.edaisong.toolsapi.service.inter.ILineHistoryService;
import com.edaisong.toolscore.enums.DevPlatformType;
import com.edaisong.toolscore.enums.OnLineProductType;
import com.edaisong.toolscore.util.EnumHelper;
import com.edaisong.toolscore.util.HtmlHelper;
import com.edaisong.toolsentity.LineHistory;
import com.edaisong.toolsentity.common.PagedResponse;
import com.edaisong.toolsentity.req.LineHistoryReq;
import com.edaisong.toolsentity.req.PagedLineHistoryReq; 

/*
 * 上线里程相关
 * wangchao
 */
@Controller
@RequestMapping("linehistory")
public class LineHistoryController {
	@Autowired
	private ToolsHelper toolsHelper;
	@Autowired
	private ILineHistoryService lineHistoryService;
	@RequestMapping("list")
	public ModelAndView list() {
		ModelAndView view = new ModelAndView("adminView");
		view.addObject("subtitle", "上线里程");
		view.addObject("currenttitle", "上线里程"); 
		view.addObject("appNameList", toolsHelper.getConnList());
		view.addObject("viewPath", "linehistory/list"); 
		return view;
	}
	/**
	 * 根据数据库名称获取项目下拉框选项
	 * @param dbname
	 * @return
	 */
	@RequestMapping("getdevplatform")
	@ResponseBody
	public String getdevplatform(String dbname)
	{
		String optionString="";
		if(dbname.toLowerCase().equals("superman"))
		{
			optionString=HtmlHelper.getSelect("aa", DevPlatformType.getListbyId(1), "desc", "value");
		}else {
			optionString=HtmlHelper.getSelect("aa", DevPlatformType.getListbyId(2), "desc", "value");
		}
		return optionString;
	}
	/**
	 * 根据项目ID获取上线产品
	 * @param dbname
	 * @return
	 */
	@RequestMapping("getonlineproduct")
	@ResponseBody
	public String getonlineproduct(int typeid)
	{
		String optionString="";
		if(typeid!=-1)
		{
			optionString=HtmlHelper.getSelect("aa",OnLineProductType.getListbyplatform(typeid), "desc", "value");
		}
		return optionString;
	}
	/**
	 * 
	 * @param req
	 * @return
	 */
	@RequestMapping("listdo")
	public ModelAndView listdo(PagedLineHistoryReq req) {
		ModelAndView view = new ModelAndView("linehistory/listdo"); 
		PagedResponse<LineHistory> datalist  =lineHistoryService.getList(req);
		view.addObject("listData", datalist);
		return view;
	}
	/**
	 * 
	 * 添加一个上线记录
	 * @param request
	 * @param req
	 * @return
	 */
	@RequestMapping("addlinehistory")
	@ResponseBody
	public int addLineHistory(HttpServletRequest request,LineHistoryReq req){
		UserContext context=UserContext.getCurrentContext(request);
		req.setCreateName(context.getUserName());
		return lineHistoryService.addLineHistory(req);
	}

	@RequestMapping("modifylinehistory")
	@ResponseBody
	public int modifyLineHistory(HttpServletRequest request,LineHistoryReq req){
		UserContext context=UserContext.getCurrentContext(request);
		req.setModifyName(context.getUserName());
		return lineHistoryService.modifyLineHistory(req);
	}
	@RequestMapping("deletelinehistory")
	@ResponseBody
	public int deleteLineHistory(HttpServletRequest request,LineHistoryReq req){
		UserContext context=UserContext.getCurrentContext(request);
		req.setModifyName(context.getUserName());
		return lineHistoryService.deleteLineHistory(req);
	}
}
