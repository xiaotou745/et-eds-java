package com.edaisong.toolsapi.dao.impl;

import java.util.List;
import java.util.HashMap;
import java.util.Map;

import org.springframework.stereotype.Repository;

import com.edaisong.toolsapi.common.DaoBase;
import com.edaisong.toolsentity.common.RequestBase;
import com.edaisong.toolsentity.domain.ShortcutKeys;
import com.edaisong.toolsapi.dao.inter.IShortcutKeysDao;

/**
 * 数据访问对象 ShortcutKeysDao
 * 
 * @author wangyuchuan
 * @date 2015-12-09 15:52:46
 *
 */
@Repository
public class ShortcutKeysDao extends DaoBase implements IShortcutKeysDao {
	/**
	 * 新增一条记录
	 * 
	 * @author wangyuchuan
	 * @date 2015-12-09 15:52:46
	 * @param shortcutKeys
	 *            要新增的对象
	 * @return 返回新增对象的自增Id
	 */
	@Override
	public Integer insert(ShortcutKeys shortcutKeys) {
		return getMasterSqlSessionUtil()
				.insert("com.edaisong.toolsapi.dao.inter.IShortcutKeysDao.insert", shortcutKeys);
	}

	/**
	 * 更新一条记录
	 * 
	 * @author wangyuchuan
	 * @date 2015-12-09 15:52:46
	 * @param shortcutKeys
	 *            要更改的对象
	 */
	@Override
	public void update(ShortcutKeys shortcutKeys) {
		getMasterSqlSessionUtil().update("com.edaisong.toolsapi.dao.inter.IShortcutKeysDao.update", shortcutKeys);
	}

	/**
	 * 删除一条记录
	 * 
	 * @author wangyuchuan
	 * @date 2015-12-09 15:52:46
	 * @param id
	 *            自增ID
	 */
	@Override
	public void delete(Integer id) {
		getMasterSqlSessionUtil().delete("com.edaisong.toolsapi.dao.inter.IShortcutKeysDao.delete", id);
	}

	/**
	 * 根据Id得到一个对象实体
	 * 
	 * @author wangyuchuan
	 * @date 2015-12-09 15:52:46
	 * @param id
	 *            自增ID
	 */
	@Override
	public ShortcutKeys getById(Integer id) {
		return getMasterSqlSessionUtil().selectOne("com.edaisong.toolsapi.dao.inter.IShortcutKeysDao.getById", id);
	}

	/**
	 * 查询方法
	 * 
	 * @author wangyuchuan
	 * @date 2015-12-09 15:52:46
	 * @param shortcutKeysQueryInfo
	 *            查询条件
	 */
	@Override
	public List<ShortcutKeys> select(RequestBase queryInfo) {
		return getMasterSqlSessionUtil().selectList("com.edaisong.toolsapi.dao.inter.IShortcutKeysDao.select",
				queryInfo);
	}

	@Override
	public List<ShortcutKeys> getByToolName(String toolName) {
		return getMasterSqlSessionUtil().selectList("com.edaisong.toolsapi.dao.inter.IShortcutKeysDao.getByToolName",
				toolName);
	}

	@Override
	public List<String> getToolNames() {
		return getMasterSqlSessionUtil().selectList("com.edaisong.toolsapi.dao.inter.IShortcutKeysDao.getToolNames");
	}
}