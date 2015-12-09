package com.edaisong.toolsapi.service.inter;

import java.util.List;

import com.edaisong.toolsentity.common.RequestBase;
import com.edaisong.toolsentity.domain.ShortcutKeys;

/**
 * 服务接口 IShortcutKeysService
 * 
 * @author wangyuchuan
 * @date 2015-12-09 15:52:46
 *
 */
public interface IShortcutKeysService {
	/**
	 * 新增一条记录
	 * 
	 * @author wangyuchuan
	 * @date 2015-12-09 15:52:46
	 * @param shortcutKeys
	 *            要新增的对象
	 * @return 返回新增对象的自增Id
	 */
	Integer create(ShortcutKeys shortcutKeys);

	/**
	 * 更新一条记录
	 * 
	 * @author wangyuchuan
	 * @date 2015-12-09 15:52:46
	 * @param shortcutKeys
	 *            要更改的对象
	 */
	void modify(ShortcutKeys shortcutKeys);

	/**
	 * 删除一条记录
	 * 
	 * @author wangyuchuan
	 * @date 2015-12-09 15:52:46
	 * @param id
	 *            自增ID
	 */
	void remove(Integer id);

	/**
	 * 根据Id得到一个对象实体
	 * 
	 * @author wangyuchuan
	 * @date 2015-12-09 15:52:46
	 * @param id
	 *            自增ID
	 */
	ShortcutKeys getById(Integer id);

	/**
	 * 获取指定工具的快捷键
	 * 
	 * @param toolName
	 *            工具名称
	 * @return 返回指定工具的快捷键
	 */
	List<ShortcutKeys> getByToolName(String toolName);

	/**
	 * 获取所有工具列表
	 * 
	 * @return 返回所有工具列表
	 */
	List<String> getToolNames();

	/**
	 * 查询方法
	 * 
	 * @author wangyuchuan
	 * @date 2015-12-09 15:52:46
	 * @param queryInfo
	 *            查询条件
	 */
	List<ShortcutKeys> query(RequestBase queryInfo);
}