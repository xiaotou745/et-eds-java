package com.edaisong.toolsapi.service.impl;

import java.util.List;

import org.apache.activemq.broker.UserIDBroker;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import com.edaisong.toolscore.security.MD5Util;
import com.edaisong.toolscore.util.ListUtils;
import com.edaisong.toolsentity.domain.User;
import com.edaisong.toolsentity.view.LoginResult;
import com.edaisong.toolsapi.service.ServiceCacheProvider;
import com.edaisong.toolsapi.service.inter.IUserService;
import com.edaisong.toolsapi.dao.inter.IUserDao;

/**
 * 服务提供对象 UserService
 * 
 * @author wangyuchuan
 * @date 2015-11-25 15:03:50
 *
 */
@Service
public class UserService implements IUserService {
	@Autowired
	private IUserDao userDao;

	@Autowired
	private ServiceCacheProvider serviceCache;

	/**
	 * 新增一条记录
	 * 
	 * @author wangyuchuan
	 * @date 2015-11-25 15:03:50
	 * @param user
	 *            要新增的对象
	 * @return 返回新增对象的自增Id
	 */
	public Integer create(User user) {
		user.setLoginPwd(MD5Util.MD5(user.getLoginPwd()));
		return userDao.insert(user);
	}

	/**
	 * 更新一条记录
	 * 
	 * @author wangyuchuan
	 * @date 2015-11-25 15:03:50
	 * @param user
	 *            要更改的对象
	 */
	public void modify(User user) {
		userDao.update(user);
	}

	/**
	 * 删除一条记录
	 * 
	 * @author wangyuchuan
	 * @date 2015-11-25 15:03:50
	 * @param id
	 *            用户ID
	 */
	public void remove(Integer id) {
		userDao.delete(id);
	}

	/**
	 * 根据Id得到一个对象实体
	 * 
	 * @author wangyuchuan
	 * @date 2015-11-25 15:03:50
	 * @param id
	 *            用户ID
	 */
	public User getById(Integer id) {
		return userDao.getById(id);
	}

	/**
	 * 查询方法
	 * 
	 * @author wangyuchuan
	 * @date 2015-11-25 15:03:50
	 */
	public List<User> getAll() {
		return userDao.getAll();
	}

	@Override
	public void disable(Integer userId, boolean isDisable) {
		// TODO Auto-generated method stub
		userDao.disable(userId, isDisable);
	}

	@Override
	public void setRoles(Integer userId, String roleIds) {
		userDao.setRoles(userId, roleIds);
		// 刷新此用户在缓存中的权限
		List<Integer> lstRoleIds = ListUtils.str2intlist(roleIds, ",");
		serviceCache.refreshUserPrivilegesInCache(userId, lstRoleIds);
	}

	@Override
	public LoginResult login(User user) {
		if (user == null || StringUtils.isEmpty(user.getLoginName())) {
			return LoginResult.userNameIsNull;
		}
		if (StringUtils.isEmpty(user.getLoginPwd())) {
			return LoginResult.passwordIsNull;
		}
		User user2 = userDao.getByLoginName(user.getLoginName());
		if (user2 == null) {
			return LoginResult.userNotExists;
		}
		if (user2.getIsDisable()) {
			return LoginResult.userIsDisable;
		}
		String md5Password = MD5Util.MD5(user.getLoginPwd());
		if (!md5Password.equals(user2.getLoginPwd())) {
			return LoginResult.passwordError;
		}
		user.setUserName(user2.getUserName());
		user.setId(user2.getId());

		// 保存用户菜单权限到缓存
		serviceCache.refreshUserPrivilegesInCache(user2);

		return LoginResult.success;
	}
}