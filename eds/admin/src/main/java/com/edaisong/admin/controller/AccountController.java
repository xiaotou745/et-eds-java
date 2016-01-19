package com.edaisong.admin.controller;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.edaisong.admin.common.LoginUtil;
import com.edaisong.admin.common.UserContext;
import com.edaisong.api.common.LoginHelper;
import com.edaisong.api.redis.RedisService;
import com.edaisong.api.service.inter.IAccountCityRelationService;
import com.edaisong.api.service.inter.IAccountDeliveryRelationService;
import com.edaisong.api.service.inter.IAccountLoginLogService;
import com.edaisong.api.service.inter.IAccountService;
import com.edaisong.api.service.inter.IAuthorityAccountMenuSetService;
import com.edaisong.api.service.inter.IAuthorityRoleService;
import com.edaisong.api.service.inter.IDeliveryCompanyService;
import com.edaisong.api.service.inter.IPublicProvinceCityService;
import com.edaisong.core.security.AES;
import com.edaisong.core.security.MD5Util;
import com.edaisong.core.util.CookieUtils;
import com.edaisong.core.util.IPUtil;
import com.edaisong.core.util.JsonUtil;
import com.edaisong.core.util.ParseHelper;
import com.edaisong.core.util.PropertyUtils;
import com.edaisong.entity.Account;
import com.edaisong.entity.AccountCityRelation;
import com.edaisong.entity.AccountDeliveryRelation;
import com.edaisong.entity.AccountLog;
import com.edaisong.entity.AuthorityRole;
import com.edaisong.entity.DeliveryCompany;
import com.edaisong.entity.common.PagedResponse;
import com.edaisong.entity.domain.AreaModel;
import com.edaisong.entity.domain.SimpleUserInfoModel;
import com.edaisong.entity.req.PagedAccountReq;
import com.edaisong.entity.req.UpdatePwdReq;


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
	@Autowired
	private IAuthorityRoleService authorityRoleService;
	@Autowired
	private IAccountDeliveryRelationService accountDeliveryRelationService;
	@Autowired
	private IAccountCityRelationService accountCityRelationService;

	@RequestMapping("list")
	public ModelAndView list() {
		ModelAndView view = new ModelAndView("adminView");
		view.addObject("subtitle", "用户设置");
		view.addObject("currenttitle", "用户管理");

		List<AreaModel> listArea = publicProvinceCityService.getOpenCityByJiBie(3);
		List<DeliveryCompany> listDc = deliveryCompanyService.getDeliveryCompanyList();

		view.addObject("listArea", listArea);
		view.addObject("listDc", listDc);
		List<AuthorityRole> datalist=authorityRoleService.selectList();
		view.addObject("roleData", datalist);
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
	@RequestMapping("adduser")
	@ResponseBody
	public int addUser(HttpServletRequest request,Account account,
			String cityrelations,String deliveryrelations) {
		if (account==null||
				account.getLoginname()==null||
				account.getLoginname().isEmpty()||
				account.getUsername()==null||
				account.getUsername().isEmpty()
				) {
			return -1;
		}
		UserContext context = UserContext.getCurrentContext(request);
		account.setLcuser(context.getUserName());
		account.setFauser(context.getUserName());
		String password = MD5Util.MD5(account.getPassword());
		account.setPassword(password);
		int result= accountService.insert(account);
		if (result>0) {
			saveCityAndDeliveryRelations(request, account, "", cityrelations,"", deliveryrelations);
		}
		return result;
	}
	private Map<String,List<String>> getUpdateRelation(String oldIDs,String newIDs){
		List<String> newList = new ArrayList<>();
		List<String> oldList = new ArrayList<>();
		List<String> diffList = new ArrayList<>();
		if (newIDs != null && !newIDs.isEmpty()) {
			Collections.addAll(newList, newIDs.split(","));
			Collections.addAll(diffList, newIDs.split(","));
		}
		if (oldIDs != null && !oldIDs.isEmpty()) {
			Collections.addAll(oldList, oldIDs.split(","));
		}

		diffList.removeAll(oldList);// diffList中剩余的是新增的id
		oldList.removeAll(newList);// oldList中剩余的是被删掉的id
		Map<String,List<String>> result=new HashMap<String, List<String>>();
		result.put("add", diffList);
		result.put("del", oldList);
		//diffList.addAll(oldList);// diffList中剩余的是发生了变更的id
		return result;
	}
	private void saveCityAndDeliveryRelations(HttpServletRequest request,Account account,
			String oldcityrelations,String newcityrelations,
			String olddeliveryrelations,String newdeliveryrelations){
		UserContext context = UserContext.getCurrentContext(request);
		Map<String,List<String>> changeCityList=getUpdateRelation(oldcityrelations,newcityrelations);
		if (changeCityList.size()>0) {
			List<AccountCityRelation> authCityList = new ArrayList<>();
			for (String cityid : changeCityList.get("add")) {
				AccountCityRelation authset = new AccountCityRelation();
				authset.setAccountid(account.getId());
				authset.setCityid(Integer.parseInt(cityid));
				authset.setUpdateby(context.getUserName());
				authset.setCreateby(context.getUserName());
				authset.setIsenable((short)1);
				authCityList.add(authset);
			}
			for (String cityid : changeCityList.get("del")) {
				AccountCityRelation authset = new AccountCityRelation();
				authset.setAccountid(account.getId());
				authset.setCityid(Integer.parseInt(cityid));
				authset.setUpdateby(context.getUserName());
				authset.setCreateby(context.getUserName());
				authset.setIsenable((short)0);
				authCityList.add(authset);
			}
			accountCityRelationService.modifyAuthList(authCityList);
		}
		Map<String,List<String>> changeDeliveryList=getUpdateRelation(olddeliveryrelations,newdeliveryrelations);
		if (changeDeliveryList.size()>0) {
			List<AccountDeliveryRelation> authDeliveryList = new ArrayList<>();
			for (String deliveryid : changeDeliveryList.get("add")) {
				AccountDeliveryRelation authset = new AccountDeliveryRelation();
				authset.setAccountid(account.getId());
				authset.setDeliverycompanyid(Integer.parseInt(deliveryid));
				authset.setCreateby(context.getUserName());
				authset.setIsenable(1);
				authDeliveryList.add(authset);
			}
			for (String deliveryid : changeDeliveryList.get("del")) {
				AccountDeliveryRelation authset = new AccountDeliveryRelation();
				authset.setAccountid(account.getId());
				authset.setDeliverycompanyid(Integer.parseInt(deliveryid));
				authset.setCreateby(context.getUserName());
				authset.setIsenable(0);
				authDeliveryList.add(authset);
			}
			accountDeliveryRelationService.modifyAuthList(authDeliveryList);
		}
	}
	@RequestMapping("updateuser")
	@ResponseBody
	public int updateUser(HttpServletRequest request,Account account,
			String oldcityrelations,String newcityrelations,
			String olddeliveryrelations,String newdeliveryrelations) {
		if (account==null||
				account.getLoginname()==null||
				account.getLoginname().isEmpty()||
				account.getUsername()==null||
				account.getUsername().isEmpty()
				) {
			return -1;
		}
		if (account.getPassword()!=null&&!account.getPassword().isEmpty()) {
			String password = MD5Util.MD5(account.getPassword());
			account.setPassword(password);
		}
		UserContext context = UserContext.getCurrentContext(request);
		account.setLcuser(context.getUserName());
		int result= accountService.update(account);
		if (result>0) {
			saveCityAndDeliveryRelations(request, account, oldcityrelations,
					newcityrelations, olddeliveryrelations,
					newdeliveryrelations);
		}
		return result;
	}
	@RequestMapping("getauthoritycityrelations")
	@ResponseBody
	public List<Integer> getAuthorityCityRelations(HttpServletRequest request,int userId) {
		return accountCityRelationService.getAuthorityCitys(userId);
	}
	@RequestMapping("getauthoritydeliveryrelations")
	@ResponseBody
	public List<Integer> getAuthorityDeliveryRelations(HttpServletRequest request,int userId) {
		return accountDeliveryRelationService.getAuthorityCitys(userId);
	}
	@RequestMapping("getuserinfo")
	@ResponseBody
	public Account getUserInfo(HttpServletRequest request,int userId) {
		return accountService.getByID(userId);
	}
	@RequestMapping(value = "login", method = { RequestMethod.POST })
	public void login(HttpServletRequest request, HttpServletResponse response,
			@RequestParam String username, @RequestParam String password, @RequestParam String code,
			 Integer rememberMe) throws ServletException, IOException {
		String basePath = PropertyUtils.getProperty("java.admin.url");
		Date loginTime = new Date();
		String sessionCode = LoginHelper.getAuthCode(request,LoginUtil.ADMIN_JSESSIONID);
		//一次性验证码,防止暴力破解
		LoginHelper.removeAuthCodeCookie(request, response,"admin",LoginUtil.ADMIN_JSESSIONID);
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
		loginUser.setPassword("");
		loginUser.setRoleId(account.getRoleid());
		loginUser.setUserName(account.getUsername());
		String encyCookie=AES.aesEncrypt(JsonUtil.obj2string(loginUser));
		 
		CookieUtils.setCookie(request,response,"admin", LoginUtil.LOGIN_COOKIE_NAME, encyCookie, cookieMaxAge,
				true);
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
		CookieUtils.deleteCookie(request, response,"admin", LoginUtil.LOGIN_COOKIE_NAME);
		UserContext context=UserContext.getCurrentContext(request);
		int loginFrom=context.getLoginFrom();
		if (loginFrom==0) {
			response.sendRedirect(PropertyUtils.getProperty("net.admin.url") + "/account/login");
		}else {
			response.sendRedirect(PropertyUtils.getProperty("java.admin.url") + "/");
		}
	}
	@RequestMapping(value = "changepwd")
	public ModelAndView changePwd(){
		ModelAndView view = new ModelAndView("adminView");
		view.addObject("subtitle", "管理员");
		view.addObject("currenttitle", "修改密码");
		view.addObject("viewPath", "account/changepwd");
		return view;
	}
	@RequestMapping(value = "updatepwd")
	@ResponseBody
	public int updatePwd(HttpServletRequest request, UpdatePwdReq req){
		UserContext context = UserContext.getCurrentContext(request);
		req.setUserId(context.getId());
		return accountService.updatePwd(req);
	}
}
