package com.edaisong.admin.controller;

import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.http.HttpRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import com.edaisong.api.service.impl.BiService;
import com.edaisong.api.service.inter.IBiService;
import com.edaisong.core.enums.DevPlatformType;
import com.edaisong.entity.DeliveryCompany;
import com.edaisong.entity.Everyday;
import com.edaisong.entity.domain.AreaModel;
import com.edaisong.entity.domain.LineHistoryModel;
import com.fasterxml.jackson.databind.jsonFormatVisitors.JsonArrayFormatVisitor;


@Controller
@RequestMapping("bi")
public class BIController {
	@Autowired
	private IBiService biService;
	@RequestMapping("everyday")
	public ModelAndView everyday(){
		ModelAndView view = new ModelAndView("adminView");
		view.addObject("subtitle", "每日数据统计");
		view.addObject("currenttitle", "每日数据统计"); 
		view.addObject("viewPath", "bi/everyday");
		List<Everyday> list= biService.queryEveryDay();
		view.addObject("everyDayData",list);
		return view;
	}
	/**
	 * 版本升级记录页面
	 * 茹化肖
	 * @return
	 */
	@RequestMapping("linehistory")
	public ModelAndView linehistory(){
		ModelAndView view = new ModelAndView("bi/linehistory");
		//查询上线记录
		List<LineHistoryModel> list=biService.getLineHistoryModel();
		List<String> strlist=new ArrayList<String>();
		//构建上线年份
		for (LineHistoryModel lineHistoryModel : list) {
			String yeString=lineHistoryModel.getOnLineTime().split("/")[0];
			if(!strlist.contains(yeString))
			{
				strlist.add(yeString);
			}
		}
		//产品列表
		List<DevPlatformType> typeList=DevPlatformType.getListbyId(1);
		view.addObject("list", list);
		view.addObject("yearlist", strlist);
		view.addObject("typeList", typeList);
		return view;
	}
	
	
}
