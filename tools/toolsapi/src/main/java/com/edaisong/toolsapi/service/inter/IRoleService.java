package com.edaisong.toolsapi.service.inter;
 
import java.util.List;
import com.edaisong.toolsentity.domain.Role;
 
/**
 * 服务接口 IRoleService
 * @author wangyuchuan
 * @date 2015-11-25 14:55:29
 *
 */
public interface IRoleService {
    /**
     * 新增一条记录
     * @author wangyuchuan
     * @date 2015-11-25 14:55:29
     * @param role 要新增的对象
     * @return  返回新增对象的自增Id
     */
    Integer create(Role role);
 
    /**
     * 更新一条记录
     * @author wangyuchuan
     * @date 2015-11-25 14:55:29
     * @param role 要更改的对象
     */
    void modify(Role role);
 
    /**
     * 删除一条记录
     * @author wangyuchuan
     * @date 2015-11-25 14:55:29
     * @param id 角色Id
     */
    void remove(Integer id);
 
    /**
     * 根据Id得到一个对象实体
     * @author wangyuchuan
     * @date 2015-11-25 14:55:29
     * @param id 角色Id
     */
    Role getById(Integer id);
 
    /**
     * 查询方法
     * @author wangyuchuan
     * @date 2015-11-25 14:55:29
     */
    List<Role> getAll();
}