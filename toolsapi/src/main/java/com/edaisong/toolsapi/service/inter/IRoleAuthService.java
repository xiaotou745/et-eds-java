package com.edaisong.toolsapi.service.inter;

import java.util.List;

import com.edaisong.toolsentity.RoleAuth;
import com.edaisong.toolsentity.RoleInfo;



public interface IRoleAuthService {
	/**
	 * 修改给定角色的权限列表
	 * @author hailongzhao
	 * @date 20150902
	 * @return
	 */
	public boolean modifyAuthList(List<RoleAuth> authList) ;
}
