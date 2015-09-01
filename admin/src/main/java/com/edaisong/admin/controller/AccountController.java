package com.edaisong.admin.controller;

import java.io.IOException;
import java.util.Date;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import com.edaisong.admin.common.LoginUtil;
import com.edaisong.api.common.LoginHelper;
import com.edaisong.api.service.inter.IAccountLoginLogService;
import com.edaisong.api.service.inter.IAccountService;
import com.edaisong.api.service.inter.IAuthorityAccountMenuSetService;
import com.edaisong.api.service.inter.IDeliveryCompanyService;
import com.edaisong.api.service.inter.IPublicProvinceCityService;
import com.edaisong.core.cache.redis.RedisService;
import com.edaisong.core.util.CookieUtils;
import com.edaisong.core.util.IPUtil;
import com.edaisong.core.util.JsonUtil;
import com.edaisong.core.util.ParseHelper;
import com.edaisong.core.util.PropertyUtils;
import com.edaisong.entity.Account;
import com.edaisong.entity.AccountLog;
import com.edaisong.entity.DeliveryCompany;
import com.edaisong.entity.common.PagedResponse;
import com.edaisong.entity.domain.AreaModel;
import com.edaisong.entity.domain.SimpleUserInfoModel;
import com.edaisong.entity.req.PagedAccountReq;


//import java.util.function.Predicate;
@Controller
@RequestMapping("account")
public class AccountController {
	@Autowired
	private RedisService redisService;
	@Autowired
	private IAccountService accountService;
	@Autowired
	private IPublicProvinceCityService publicProvinceCityService;
	@Autowired
	private IDeliveryCompanyService deliveryCompanyService;
	@Autowired
	private IAccountLoginLogService accountLoginLogService;
	@Autowired
	private IAuthorityAccountMenuSetService authorityAccountMenuSetService;

	@RequestMapping("list")
	public ModelAndView list() {
		ModelAndView view = new ModelAndView("adminView");
		view.addObject("subtitle", "用户设置");
		view.addObject("currenttitle", "用户管理");

		List<AreaModel> listArea = publicProvinceCityService.getOpenCityByJiBie(1);
		List<DeliveryCompany> listDc = deliveryCompanyService
				.getDeliveryCompanyList();

		view.addObject("listArea", listArea);
		view.addObject("listDc", listDc);
		view.addObject("viewPath", "account/list");
		return view;
	}

	@RequestMapping("listdo")
	public ModelAndView list(PagedAccountReq req) {
		PagedResponse<Account> resp = accountService.queryAccount(req);
		ModelAndView view = new ModelAndView();
		view.addObject("viewPath", "account/listdo");
		view.addObject("listData", resp);
		return view;
	}
	
	@RequestMapping("code")
	public ModelAndView code(HttpServletRequest request, HttpServletResponse response) {
		ModelAndView mv = new ModelAndView("account/code");
		return mv;
	}
	
	@RequestMapping(value = "login", method = { RequestMethod.POST })
	public void login(HttpServletRequest request, HttpServletResponse response,
			@RequestParam String username, @RequestParam String password, @RequestParam String code,
			 Integer rememberMe) throws ServletException, IOException {
		String basePath = PropertyUtils.getProperty("static.admin.url");
		Date loginTime = new Date();
		String sessionCode = LoginHelper.getAuthCode(request);
		//一次性验证码,防止暴力破解
		LoginHelper.removeAuthCodeCookie(request, response);
		// 如果已登录,直接返回
		boolean isLogin = LoginUtil.checkIsLogin(request,response);
		// 如果已登录,直接返回已登录
		if (isLogin) {
			response.sendRedirect(basePath+"/order/list");
			return;
		}
		AccountLog log = new AccountLog();
		log.setIp(IPUtil.getIpAddr(request));
		log.setLoginName(username);
		log.setLoginTime(loginTime);
		//log.setBrowser(request.getHeader("user-agent"));
		String error = "";
		Account account = null;
		// 验证码不正确
		if (sessionCode == null || !sessionCode.toString().toLowerCase().equals(code.toLowerCase())) {
			error = "验证码不正确";
		}else{
			account = accountService.login(username, password);
			if (account == null) {
				error = "用户名或密码错误";
			}
			else if(account.getStatus() != 1){
				error = "您的账号已经被禁用,请联系管理员";
			}
		}
		if(!error.equals("")){
			log.setRemark(error);
			accountLoginLogService.addLog(log);
			request.setAttribute("error", error);
			request.getServletContext().getRequestDispatcher("/login.jsp").forward(request, response);
			return;
		}
		// 登录成功,写cookie
		int cookieMaxAge = -1;
		// 选择记住我,默认cookie24小时,否则随浏览器的关闭而失效
		boolean isRemeberMe = rememberMe!= null && rememberMe.equals(1);
		if (isRemeberMe) {
			cookieMaxAge = 7 * 60 * 60 * 24;
		}
		account.setPassword(password);//这个是为了给cookie准备的参数
		error = "成功";
		log.setRemark(error);
		accountLoginLogService.addLog(log);
		SimpleUserInfoModel loginUser = new SimpleUserInfoModel();
		loginUser.setAccountType(ParseHelper.ToInt(account.getAccounttype(), 1));
		loginUser.setGroupId(ParseHelper.ToInt(account.getGroupid(), 0));
		loginUser.setId(account.getId());
		loginUser.setLoginName(account.getLoginname());
		loginUser.setPassword(password);
		loginUser.setRoleId(account.getRoleid());
		loginUser.setUserName(account.getUsername());
		CookieUtils.setCookie(request,response, LoginUtil.LOGIN_COOKIE_NAME, JsonUtil.obj2string(loginUser), cookieMaxAge,
				true);
		List<Integer> menuList = authorityAccountMenuSetService.getMenuIdsByAccountId(account.getId());
		if(menuList != null){
			CookieUtils.setCookie(request,response, LoginUtil.MENU_LIST_COOKIE_NAME, JsonUtil.obj2string(menuList), cookieMaxAge,
					false);
		}
		response.sendRedirect(basePath+"/order/list");
	}
	
	/**
	 * 注销
	 * 
	 * @author pengyi
	 * @param request
	 * @param response
	 * @return
	 * @throws IOException 
	 */
	@RequestMapping(value = "logoff")
	public void logoff(HttpServletRequest request, HttpServletResponse response) throws IOException {
		// 删除登录cookie
		Cookie cookie = CookieUtils.getCookieByName(LoginUtil.LOGIN_COOKIE_NAME, request);
		if (cookie != null) {
			CookieUtils.deleteCookie(request, response, cookie);
		}
		Cookie menuList = CookieUtils.getCookieByName(LoginUtil.MENU_LIST_COOKIE_NAME, request);
		if (menuList != null) {
			CookieUtils.deleteCookie(request, response, menuList);
		}
		response.sendRedirect(PropertyUtils.getProperty("static.admin.url") + "/");
	}
}
