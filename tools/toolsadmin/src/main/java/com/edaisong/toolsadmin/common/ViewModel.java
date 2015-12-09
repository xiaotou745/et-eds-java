package com.edaisong.toolsadmin.common;

import org.springframework.web.servlet.ModelAndView;

public class ViewModel extends ModelAndView {

	public final static String defaultViewName = "adminView";
	public final static String KEY_VIEW_DATA = "viewData";
	public final static String KEY_VIEW_PATH = "viewPath";
	public final static String KEY_SUBTITLE = "subtitle";
	public final static String KEY_CURRENT_TITLE = "currenttitle";
	public final static String KEY_FOTTER_JS = "footerJs";

	public ViewModel() {

	}

	public ViewModel(String viewName) {
		super(viewName);
	}

	public ViewModel(String viewPath, String subTitle, String currentTitle, String footerJs) {
		super(defaultViewName);
		setViewPath(viewPath);
		setSubTitle(subTitle);
		setCurrentTitle(currentTitle);
		setFooterJs(footerJs);
	}

	public static ViewModel create() {
		return new ViewModel();
	}

	public static ViewModel create(String viewName) {
		return new ViewModel(viewName);
	}

	public static ViewModel createDefault() {
		return new ViewModel(defaultViewName);
	}

	public ViewModel setViewNameN(String viewName) {
		this.setViewName(viewName);
		return this;
	}

	public ViewModel setViewPath(String viewPath) {
		this.addObject(KEY_VIEW_PATH, viewPath);
		return this;
	}

	public ViewModel setSubTitle(String subTitle) {
		this.addObject(KEY_SUBTITLE, subTitle);
		return this;
	}

	public ViewModel setCurrentTitle(String currentTitle) {
		this.addObject(KEY_CURRENT_TITLE, currentTitle);
		return this;
	}

	public ViewModel setFooterJs(String footerJs) {
		this.addObject(KEY_FOTTER_JS, footerJs);
		return this;
	}

	public ViewModel setViewData(Object model) {
		this.addObject(KEY_VIEW_DATA, model);
		return this;
	}
}
