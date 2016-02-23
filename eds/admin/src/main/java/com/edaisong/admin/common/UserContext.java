package com.edaisong.admin.common;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import javax.servlet.http.HttpServletRequest;

import org.mvel2.util.ThisLiteral;
import org.springframework.beans.factory.annotation.Autowired;

import com.edaisong.api.service.inter.IAccountService;
import com.edaisong.api.service.inter.IAuthorityMenuClassService;
import com.edaisong.api.service.inter.IPublicProvinceCityService;
import com.edaisong.core.security.AES;
import com.edaisong.core.util.CookieUtils;
import com.edaisong.core.util.JsonUtil;
import com.edaisong.core.util.PropertyUtils;
import com.edaisong.core.util.SpringBeanHelper;
import com.edaisong.entity.Account;
import com.edaisong.entity.domain.AreaModel;
import com.edaisong.entity.domain.SimpleUserInfoModel;

public class UserContext {
	private SimpleUserInfoModel account;
	private String host="";
	private final static IAuthorityMenuClassService authorityMenuClassService;
	private final static IAccountService accountService;
	private final static IPublicProvinceCityService publicProvinceCityService;
	static {
		authorityMenuClassService = SpringBeanHelper
				.getCustomBeanByType(IAuthorityMenuClassService.class);
		accountService = SpringBeanHelper
				.getCustomBeanByType(IAccountService.class);
		publicProvinceCityService = SpringBeanHelper
				.getCustomBeanByType(IPublicProvinceCityService.class);
	}

	private UserContext(SimpleUserInfoModel account,String host) {
		this.account = account;
		this.host = host;
	}

	/**
	 * 判断当前登录用户是否有给定菜单的权限
	 * 
	 * @author hailongzhao
	 * @date 20150828
	 * @param menuID
	 * @return
	 */
	public boolean isHasAuth(int menuID) {
		return authorityMenuClassService.checkHasAuth(account.getId(), menuID);
	}
	/**
	 * 判断当前用户是否有某个AuthCode权限
	 * 茹化肖
	 * @param authCode
	 * @return
	 */
	public boolean isHasAuthByCode(String authCode) {
		return authorityMenuClassService.checkHasAuthByCode(account.getId(), authCode);
	}
	public int getId() {
		return account.getId();
	}

	public int getAccountType() {
		//return account.getAccountType();
		return accountService.getByID(account.getId()).getAccounttype();
	}

	public String getLoginName() {
		return account.getLoginName();
	}
	public String getUserName() {
		return account.getUserName();
	}
	public int getGroupId() {
		return account.getGroupId();
	}
	public static  UserContext getCurrentContext(HttpServletRequest request) {
		final String cookieKey = LoginUtil.LOGIN_COOKIE_NAME;
		String cookieValue = CookieUtils.getCookie(request, cookieKey);
		if (cookieValue != null&&!cookieValue.isEmpty()) {
			String edcrCookie=cookieValue;
			if(cookieValue.indexOf("LoginName")<0){//加密的cookie
			    edcrCookie=AES.aesDecrypt(cookieValue);
			}
			SimpleUserInfoModel account = JsonUtil.str2obj(edcrCookie,SimpleUserInfoModel.class);
			if (account != null&&
				account.getUserName()!=null&&
				!account.getUserName().isEmpty()&&
				account.getLoginName()!=null&&
				!account.getLoginName().isEmpty()) {
				return new UserContext(account,request.getHeader("host"));
			}
		}

		return null;
	}
	/**
	 * 登录来源，0表示从net版后台登录，1表示从java版后台登录
	 * @author hailongzhao
	 * @date 20150916
	 * @param loginfrom
	 */
	public  int getLoginFrom() {
		String staticUrl=PropertyUtils.getProperty("java.admin.url");
		int index=staticUrl.indexOf(".");
		if (index>0) {
			String webDomain=staticUrl.substring(staticUrl.indexOf("."));
			if (host!=null&&host.indexOf(webDomain)>0) {
				return 0;
			}
		}
		return 1;
	}
	/**
	 * 获取当前用户的有权限的城市
	 * 茹化肖
	 * @return
	 */
	public  List<AreaModel> getUserCity()
	{
		int accountID=account.getId();
		return publicProvinceCityService.getOpenCityListByAccountID(accountID);
	}
	
	/**
	 * 获取当前用户的有权限的城市(字符串分隔)
	 * 茹化肖
	 * @return
	 */
	public  String getUserCityStr()
	{
		int accountID=account.getId();
		if(this.getAccountType()==1)
		{
			//全部城市权限
			return "";
		}
		else {
			List<AreaModel> list=publicProvinceCityService.getOpenCityListByAccountID(accountID);
			
			return String.join(",", list.stream().map(t->t.getName()).collect(Collectors.toList()));
		}
	}
}
