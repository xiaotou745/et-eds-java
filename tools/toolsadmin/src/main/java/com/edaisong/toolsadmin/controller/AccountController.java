package com.edaisong.toolsadmin.controller;

import java.io.IOException;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.edaisong.toolsadmin.common.UserIndentity;
import com.edaisong.toolsapi.common.RedisKeys;
import com.edaisong.toolsapi.common.VerificationCodeHelper;
import com.edaisong.toolsapi.service.inter.IUserService;
import com.edaisong.toolscore.util.AjaxResult;
import com.edaisong.toolscore.util.PropertyUtils;
import com.edaisong.toolscore.util.StringUtils;
import com.edaisong.toolsentity.domain.User;
import com.edaisong.toolsentity.view.LoginResult;
import com.edaisong.toolsentity.view.LoginUser;

@Controller
@RequestMapping("account")
public class AccountController {

	@Autowired
	private IUserService userService;

	/**
	 * 验证码刷新
	 * 
	 * @param request
	 * @param response
	 * @return
	 */
	@RequestMapping("code")
	public ModelAndView code(HttpServletRequest request, HttpServletResponse response) {
		ModelAndView mv = new ModelAndView("account/code");
		return mv;
	}

	/**
	 * 登录方法
	 * @param request
	 * @param response
	 * @param username
	 * @param password
	 * @param code
	 * @param rememberMe
	 * @return 返回登录结果
	 * @throws Exception
	 */
	@RequestMapping(value = "login", method = RequestMethod.POST)
	@ResponseBody
	public AjaxResult login(HttpServletRequest request, HttpServletResponse response, String username, String password,
			String code, boolean rememberMe) throws Exception {
		if (UserIndentity.getIndentity(request).isLogin()) {
			return AjaxResult.Success();
		}
		String sessionCode = VerificationCodeHelper.getAuthCode(request, RedisKeys.ADMIN_JSESSIONID);
		User loginUser = new User();
		if (StringUtils.isEmpty(sessionCode) || StringUtils.isEmpty(code)
				|| !sessionCode.toLowerCase().equals(code.toLowerCase())) {
			return AjaxResult.Error("验证码不正确");
		} else {
			loginUser.setLoginName(username);
			loginUser.setLoginPwd(password);
			LoginResult loginResult = userService.login(loginUser);
			if (!loginResult.equals(LoginResult.success)) {
				return AjaxResult.Error(loginResult.getDesc());
			}
		}

		// 登录成功,写cookie
		int cookieMaxAge = -1;
		// 选择记住我,默认cookie24小时,否则随浏览器的关闭而失效
		if (rememberMe) {
			cookieMaxAge = 7 * 60 * 60 * 24;
		}
		LoginUser user = new LoginUser();
		user.setId(loginUser.getId());
		user.setLoginName(loginUser.getLoginName());
		user.setUserName(loginUser.getUserName());
		UserIndentity.getIndentity(request).login(user, request, response, cookieMaxAge);

		return AjaxResult.Success();
	}

	/**
	 * 注销方法
	 * @param request
	 * @param response
	 * @throws IOException
	 */
	@RequestMapping(value = "logoff")
	public void logout(HttpServletRequest request, HttpServletResponse response) throws IOException {
		// 删除登录cookie
		UserIndentity.getIndentity(request).logoff(request, response);
		response.sendRedirect(PropertyUtils.getProperty("java.toolsadmin.url") + "/");
	}
}
