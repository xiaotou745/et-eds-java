package com.edaisong.toolsapi.dao.inter;

import java.util.List;

import com.edaisong.toolsentity.domain.User;

/**
 * 领域对象接口 IUserDao
 * 
 * @author wangyuchuan
 * @date 2015-11-25 15:03:50
 *
 */
public interface IUserDao {
	/**
	 * 新增一条记录
	 * 
	 * @author wangyuchuan
	 * @date 2015-11-25 15:03:50
	 * @param user
	 *            要新增的对象
	 * @return 返回新增对象的自增Id
	 */
	Integer insert(User user);

	/**
	 * 更新一条记录
	 * 
	 * @author wangyuchuan
	 * @date 2015-11-25 15:03:50
	 * @param user
	 *            要更改的对象
	 */
	void update(User user);

	/**
	 * 删除一条记录
	 * 
	 * @author wangyuchuan
	 * @date 2015-11-25 15:03:50
	 * @param id
	 *            用户ID
	 */
	void delete(Integer id);

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
	 * 根据登录名获取用户
	 * 
	 * @param loginName
	 *            登录名
	 * @return 返回用户信息
	 */
	User getByLoginName(String loginName);

	/**
	 * 查询方法
	 * 
	 * @author wangyuchuan
	 * @date 2015-11-25 15:03:50
	 */
	List<User> getAll();

	void disable(Integer userId, boolean isDisable);

	void setRoles(Integer userId, String roleIds);
}