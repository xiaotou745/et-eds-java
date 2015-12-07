package com.edaisong.toolsapi.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.edaisong.toolsentity.domain.Role;
import com.edaisong.toolsapi.service.ServiceCacheProvider;
import com.edaisong.toolsapi.service.inter.IRoleService;
import com.edaisong.toolsapi.dao.inter.IRoleDao;

/**
 * 服务提供对象 RoleService
 * 
 * @author wangyuchuan
 * @date 2015-11-25 14:55:29
 *
 */
@Service
public class RoleService implements IRoleService {
	@Autowired
	private IRoleDao roleDao;
	@Autowired
	private ServiceCacheProvider serviceCache;

	/**
	 * 新增一条记录
	 * 
	 * @author wangyuchuan
	 * @date 2015-11-25 14:55:29
	 * @param role
	 *            要新增的对象
	 * @return 返回新增对象的自增Id
	 */
	public Integer create(Role role) {
		return roleDao.insert(role);
	}

	/**
	 * 更新一条记录
	 * 
	 * @author wangyuchuan
	 * @date 2015-11-25 14:55:29
	 * @param role
	 *            要更改的对象
	 */
	public void modify(Role role) {
		roleDao.update(role);
	}

	/**
	 * 删除一条记录
	 * 
	 * @author wangyuchuan
	 * @date 2015-11-25 14:55:29
	 * @param id
	 *            角色Id
	 */
	public void remove(Integer id) {
		roleDao.delete(id);
		serviceCache.refreshRolePrivilegeInCache(id);
	}

	/**
	 * 根据Id得到一个对象实体
	 * 
	 * @author wangyuchuan
	 * @date 2015-11-25 14:55:29
	 * @param id
	 *            角色Id
	 */
	public Role getById(Integer id) {
		return roleDao.getById(id);
	}

	/**
	 * 查询方法
	 * 
	 * @author wangyuchuan
	 * @date 2015-11-25 14:55:29
	 */
	public List<Role> getAll() {
		return roleDao.getAll();
	}
}