package com.edaisong.toolsapi.service.inter;

import java.util.List;
import com.edaisong.toolsentity.domain.Menu;

/**
 * 服务接口 IMenuService
 * 
 * @author wangyuchuan
 * @date 2015-11-23 14:25:05
 *
 */
public interface IMenuService {
	/**
	 * 新增一条记录
	 * 
	 * @author wangyuchuan
	 * @date 2015-11-23 14:25:05
	 * @param menu
	 *            要新增的对象
	 * @return 返回新增对象的自增Id
	 */
	Integer create(Menu menu);

	/**
	 * 更新一条记录
	 * 
	 * @author wangyuchuan
	 * @date 2015-11-23 14:25:05
	 * @param menu
	 *            要更改的对象
	 */
	void modify(Menu menu);

	/**
	 * 删除一条记录
	 * 
	 * @author wangyuchuan
	 * @date 2015-11-23 14:25:05
	 * @param id
	 *            菜单ID
	 */
	void remove(Integer id);

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
	 * 获取所有菜单列表
	 * 
	 * @author wangyuchuan
	 * @date 2015-11-23 14:25:05
	 */
	List<Menu> getAll();

}