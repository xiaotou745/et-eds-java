package com.edaisong.toolsapi.dao.impl;
 
import java.util.List;
import java.util.HashMap;
import java.util.Map;

import org.springframework.stereotype.Repository;

import com.edaisong.toolsapi.common.DaoBase;
import com.edaisong.toolsentity.domain.User;
import com.edaisong.toolsapi.dao.inter.IUserDao;
 
/**
 * 数据访问对象 UserDao
 * @author wangyuchuan
 * @date 2015-11-25 15:03:50
 *
 */
@Repository
public class UserDao extends DaoBase implements IUserDao {
    /**
     * 新增一条记录
     * @author wangyuchuan
     * @date 2015-11-25 15:03:50
     * @param user 要新增的对象
     * @return  返回新增对象的自增Id
     */
    @Override
    public Integer insert(User user) {
        return getMasterSqlSessionUtil().insert("com.edaisong.toolsapi.dao.inter.IUserDao.insert", user);
    }
 
    /**
     * 更新一条记录
     * @author wangyuchuan
     * @date 2015-11-25 15:03:50
     * @param user 要更改的对象
     */
    @Override
    public void update(User user) {
        getMasterSqlSessionUtil().update("com.edaisong.toolsapi.dao.inter.IUserDao.update", user);
    }
 
    /**
     * 删除一条记录
     * @author wangyuchuan
     * @date 2015-11-25 15:03:50
     * @param id 用户ID
     */
    @Override
    public void delete(Integer id) {
        getMasterSqlSessionUtil().update("com.edaisong.toolsapi.dao.inter.IUserDao.delete", id);
    }
    
    public void disable(Integer id, boolean isDisable){
    	Map<String, Object> params = new HashMap<String, Object>();
		params.put("isDisable", isDisable);
		params.put("id", id);
    	getMasterSqlSessionUtil().update("com.edaisong.toolsapi.dao.inter.IUserDao.disable",params);
    }
    
    public void setRoles(Integer id, String roleIds){
    	Map<String, Object> params = new HashMap<String, Object>();
    	params.put("id", id);
		params.put("roleIds", roleIds);
    	getMasterSqlSessionUtil().update("com.edaisong.toolsapi.dao.inter.IUserDao.setRoles",params);
    }
 
    /**
     * 根据Id得到一个对象实体
     * @author wangyuchuan
     * @date 2015-11-25 15:03:50
     * @param id 用户ID
     */
    @Override
    public User getById(Integer id) {
        return getMasterSqlSessionUtil().selectOne("com.edaisong.toolsapi.dao.inter.IUserDao.getById", id);
    }
 
    /**
     * 查询方法
     * @author wangyuchuan
     * @date 2015-11-25 15:03:50
     */
    @Override
    public List<User> getAll() {
    	return getMasterSqlSessionUtil().selectList("com.edaisong.toolsapi.dao.inter.IUserDao.getAll");
    }

	@Override
	public User getByLoginName(String loginName) {
		return getMasterSqlSessionUtil().selectOne("com.edaisong.toolsapi.dao.inter.IUserDao.getByName", loginName);
	}
}
