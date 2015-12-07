package com.edaisong.toolsapi.service.inter;

import java.util.List;

import com.edaisong.toolsentity.domain.User;
import com.edaisong.toolsentity.view.LoginResult;

/**
 * 服务接口 IUserService
 * 
 * @author wangyuchuan
 * @date 2015-11-25 15:03:50
 *
 */
public interface IUserService {
	/**
	 * 新增一条记录
	 * 
	 * @author wangyuchuan
	 * @date 2015-11-25 15:03:50
	 * @param user
	 *            要新增的对象
	 * @return 返回新增对象的自增Id
	 */
	Integer create(User user);

	/**
	 * 更新一条记录
	 * 
	 * @author wangyuchuan
	 * @date 2015-11-25 15:03:50
	 * @param user
	 *            要更改的对象
	 */
	void modify(User user);

	/**
	 * 删除一条记录
	 * 
	 * @author wangyuchuan
	 * @date 2015-11-25 15:03:50
	 * @param id
	 *            用户ID
	 */
	void remove(Integer id);

	/**
	 * 禁用用户
	 * 
	 * @param userId
	 *            用户ID
	 * @param isDisable
	 *            禁用标记
	 */
	void disable(Integer userId, boolean isDisable);

	/**
	 * 根据Id得到一个对象实体
	 * 
	 * @author wangyuchuan
	 * @date 2015-11-25 15:03:50
	 * @param id
	 *            用户ID
	 */
	User getById(Integer id);

	/**
	 * 查询方法
	 * 
	 * @author wangyuchuan
	 * @date 2015-11-25 15:03:50
	 */
	List<User> getAll();

	/**
	 * 设置用户所属角色
	 * 
	 * @param userId
	 *            用户ID
	 * @param roleIds
	 *            角色ID
	 */
	void setRoles(Integer userId, String roleIds);

	/**
	 * 登录
	 * 
	 * @param user
	 *            登录用户信息
	 */
	LoginResult login(User user);
}