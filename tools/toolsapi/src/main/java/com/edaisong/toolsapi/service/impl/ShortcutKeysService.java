package com.edaisong.toolsapi.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.edaisong.toolsentity.common.RequestBase;
import com.edaisong.toolsentity.domain.ShortcutKeys;
import com.edaisong.toolsapi.service.inter.IShortcutKeysService;
import com.edaisong.toolsapi.dao.inter.IShortcutKeysDao;

/**
 * 服务提供对象 ShortcutKeysService
 * 
 * @author wangyuchuan
 * @date 2015-12-09 15:52:46
 *
 */
@Service
public class ShortcutKeysService implements IShortcutKeysService {
	@Autowired
	private IShortcutKeysDao shortcutKeysDao;

	/**
	 * 新增一条记录
	 * 
	 * @author wangyuchuan
	 * @date 2015-12-09 15:52:46
	 * @param shortcutKeys
	 *            要新增的对象
	 * @return 返回新增对象的自增Id
	 */
	public Integer create(ShortcutKeys shortcutKeys) {
		return shortcutKeysDao.insert(shortcutKeys);
	}

	/**
	 * 更新一条记录
	 * 
	 * @author wangyuchuan
	 * @date 2015-12-09 15:52:46
	 * @param shortcutKeys
	 *            要更改的对象
	 */
	public void modify(ShortcutKeys shortcutKeys) {
		shortcutKeysDao.update(shortcutKeys);
	}

	/**
	 * 删除一条记录
	 * 
	 * @author wangyuchuan
	 * @date 2015-12-09 15:52:46
	 * @param id
	 *            自增ID
	 */
	public void remove(Integer id) {
		shortcutKeysDao.delete(id);
	}

	/**
	 * 根据Id得到一个对象实体
	 * 
	 * @author wangyuchuan
	 * @date 2015-12-09 15:52:46
	 * @param id
	 *            自增ID
	 */
	public ShortcutKeys getById(Integer id) {
		return shortcutKeysDao.getById(id);
	}

	/**
	 * 查询方法
	 * 
	 * @author wangyuchuan
	 * @date 2015-12-09 15:52:46
	 * @param shortcutKeysQueryInfo
	 *            查询条件
	 */
	public List<ShortcutKeys> query(RequestBase shortcutKeysQueryInfo) {
		return shortcutKeysDao.select(shortcutKeysQueryInfo);
	}

	@Override
	public List<ShortcutKeys> getByToolName(String toolName) {
		return shortcutKeysDao.getByToolName(toolName);
	}

	@Override
	public List<String> getToolNames() {
		return shortcutKeysDao.getToolNames();
	}
}