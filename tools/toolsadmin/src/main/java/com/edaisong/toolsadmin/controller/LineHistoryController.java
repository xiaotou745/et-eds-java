package com.edaisong.toolsadmin.controller;

import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;
import com.edaisong.toolsentity.LineHistory;

/*
 * 上线里程相关
 * wangchao
 */
@Controller
@RequestMapping("linehistory")
public class LineHistoryController {
	@RequestMapping("list")
	public ModelAndView list() {
		ModelAndView view = new ModelAndView("adminView");
		view.addObject("subtitle", "上线里程");
		view.addObject("currenttitle", "上线里程"); 
		view.addObject("viewPath", "linehistory/list"); 
		return view;
	}
	
	@RequestMapping("listdo")
	public ModelAndView listdo() {
		ModelAndView view = new ModelAndView("linehistory/listdo");
		List<LineHistory> datalist = new ArrayList<LineHistory>();
		
		view.addObject("listData", datalist);
		return view;
	}
}
