package com.edaisong.toolsapi.dao.impl;

import java.util.List;
import java.util.HashMap;
import java.util.Map;
import org.springframework.stereotype.Repository;
import com.edaisong.toolsapi.common.DaoBase;
import com.edaisong.toolsentity.domain.Menu;
import com.edaisong.toolsapi.dao.inter.IMenuDao;

/**
 * 数据访问对象 MenuDao
 * 
 * @author wangyuchuan
 * @date 2015-11-23 14:25:05
 *
 */
@Repository
public class MenuDao extends DaoBase implements IMenuDao {
	/**
	 * 新增一条记录
	 * 
	 * @author wangyuchuan
	 * @date 2015-11-23 14:25:05
	 * @param menu
	 *            要新增的对象
	 * @return 返回新增对象的自增Id
	 */
	@Override
	public int insert(Menu menu) {
		getMasterSqlSessionUtil().insert("com.edaisong.toolsapi.dao.inter.IMenuDao.insert", menu);
		return menu.getId();
	}

	/**
	 * 更新一条记录
	 * 
	 * @author wangyuchuan
	 * @date 2015-11-23 14:25:05
	 * @param menu
	 *            要更改的对象
	 */
	@Override
	public void update(Menu menu) {
		getMasterSqlSessionUtil().update("com.edaisong.toolsapi.dao.inter.IMenuDao.update", menu);
	}

	/**
	 * 删除一条记录
	 * 
	 * @author wangyuchuan
	 * @date 2015-11-23 14:25:05
	 * @param id
	 *            菜单ID
	 */
	@Override
	public void delete(Integer id) {
		getMasterSqlSessionUtil().delete("com.edaisong.toolsapi.dao.inter.IMenuDao.delete", id);
	}

	/**
	 * 根据Id得到一个对象实体
	 * 
	 * @author wangyuchuan
	 * @date 2015-11-23 14:25:05
	 * @param id
	 *            菜单ID
	 */
	@Override
	public Menu getById(Integer id) {
		return getMasterSqlSessionUtil().selectOne("com.edaisong.toolsapi.dao.inter.IMenuDao.getById", id);
	}

	@Override
	public List<Menu> getAll() {
		return getMasterSqlSessionUtil().selectList("com.edaisong.toolsapi.dao.inter.IMenuDao.getAll");
	}

}