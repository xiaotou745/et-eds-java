package com.edaisong.toolsapi.dao.impl;

import java.util.List;
import java.util.HashMap;
import java.util.Map;
import org.springframework.stereotype.Repository;
import com.edaisong.toolsapi.common.DaoBase;
import com.edaisong.toolsentity.domain.Role;
import com.edaisong.toolsapi.dao.inter.IRoleDao;

/**
 * 数据访问对象 RoleDao
 * 
 * @author wangyuchuan
 * @date 2015-11-25 14:55:29
 *
 */
@Repository
public class RoleDao extends DaoBase implements IRoleDao {
	/**
	 * 新增一条记录
	 * 
	 * @author wangyuchuan
	 * @date 2015-11-25 14:55:29
	 * @param role
	 *            要新增的对象
	 * @return 返回新增对象的自增Id
	 */
	@Override
	public Integer insert(Role role) {
		getMasterSqlSessionUtil().insert("com.edaisong.toolsapi.dao.inter.IRoleDao.insert", role);
		return role.getId();
	}

	/**
	 * 更新一条记录
	 * 
	 * @author wangyuchuan
	 * @date 2015-11-25 14:55:29
	 * @param role
	 *            要更改的对象
	 */
	@Override
	public void update(Role role) {
		getMasterSqlSessionUtil().update("com.edaisong.toolsapi.dao.inter.IRoleDao.update", role);
	}

	/**
	 * 删除一条记录
	 * 
	 * @author wangyuchuan
	 * @date 2015-11-25 14:55:29
	 * @param id
	 *            角色Id
	 */
	@Override
	public void delete(Integer id) {
		getMasterSqlSessionUtil().delete("com.edaisong.toolsapi.dao.inter.IRoleDao.delete", id);
	}

	/**
	 * 根据Id得到一个对象实体
	 * 
	 * @author wangyuchuan
	 * @date 2015-11-25 14:55:29
	 * @param id
	 *            角色Id
	 */
	@Override
	public Role getById(Integer id) {
		return getMasterSqlSessionUtil().selectOne("com.edaisong.toolsapi.dao.inter.IRoleDao.getById", id);
	}

	/**
	 * 查询方法
	 * 
	 * @author wangyuchuan
	 * @date 2015-11-25 14:55:29
	 */
	@Override
	public List<Role> getAll() {
		return getMasterSqlSessionUtil().selectList("com.edaisong.toolsapi.dao.inter.IRoleDao.getAll");
	}

}