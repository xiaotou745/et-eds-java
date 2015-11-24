package com.edaisong.toolsapi.dao.inter;

import java.util.List;
import com.edaisong.toolsentity.domain.Menu;

/**
 * 领域对象接口 IMenuDao
 * 
 * @author wangyuchuan
 * @date 2015-11-23 14:25:05
 *
 */
public interface IMenuDao {
	/**
	 * 新增一条记录
	 * 
	 * @author wangyuchuan
	 * @date 2015-11-23 14:25:05
	 * @param menu
	 *            要新增的对象
	 * @return 返回新增对象的自增Id
	 */
	int insert(Menu menu);

	/**
	 * 更新一条记录
	 * 
	 * @author wangyuchuan
	 * @date 2015-11-23 14:25:05
	 * @param menu
	 *            要更改的对象
	 */
	void update(Menu menu);

	/**
	 * 删除一条记录
	 * 
	 * @author wangyuchuan
	 * @date 2015-11-23 14:25:05
	 * @param id
	 *            菜单ID
	 */
	void delete(Integer id);

	/**
	 * 根据Id得到一个对象实体
	 * 
	 * @author wangyuchuan
	 * @date 2015-11-23 14:25:05
	 * @param id
	 *            菜单ID
	 */
	Menu getById(Integer id);

	/**
	 * 查询方法
	 * 
	 * @author wangyuchuan
	 * @date 2015-11-23 14:25:05
	 */
	List<Menu> getAll();

}