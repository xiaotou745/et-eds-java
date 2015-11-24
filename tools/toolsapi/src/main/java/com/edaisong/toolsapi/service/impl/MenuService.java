package com.edaisong.toolsapi.service.impl;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.edaisong.toolsentity.domain.Menu;
import com.edaisong.toolsapi.service.inter.IMenuService;
import com.edaisong.toolsapi.dao.inter.IMenuDao;

/**
 * 服务提供对象 MenuService
 * 
 * @author wangyuchuan
 * @date 2015-11-23 14:38:45
 *
 */
@Service
public class MenuService implements IMenuService {
	@Autowired
	private IMenuDao menuDao;

	/**
	 * 新增一条记录
	 * 
	 * @author wangyuchuan
	 * @date 2015-11-23 14:38:45
	 * @param menu
	 *            要新增的对象
	 * @return 返回新增对象的自增Id
	 */
	public Integer create(Menu menu) {
		return menuDao.insert(menu);
	}

	/**
	 * 更新一条记录
	 * 
	 * @author wangyuchuan
	 * @date 2015-11-23 14:38:45
	 * @param menu
	 *            要更改的对象
	 */
	public void modify(Menu menu) {
		menuDao.update(menu);
	}

	/**
	 * 删除一条记录
	 * 
	 * @author wangyuchuan
	 * @date 2015-11-23 14:38:45
	 * @param id
	 *            菜单ID
	 */
	public void remove(Integer id) {
		menuDao.delete(id);
	}

	/**
	 * 根据Id得到一个对象实体
	 * 
	 * @author wangyuchuan
	 * @date 2015-11-23 14:38:45
	 * @param id
	 *            菜单ID
	 */
	public Menu getById(Integer id) {
		return menuDao.getById(id);
	}

	@Override
	public List<Menu> getAll() {
		// TODO Auto-generated method stub
		return null;
	}

}