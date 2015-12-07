package com.edaisong.toolsapi.service.impl;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.edaisong.toolscore.util.ParseHelper;
import com.edaisong.toolscore.util.StringUtils;
import com.edaisong.toolsentity.domain.RolePrivilege;
import com.edaisong.toolsentity.req.RolePrivilegeSaveReq;
import com.edaisong.toolsapi.service.ServiceCacheProvider;
import com.edaisong.toolsapi.service.inter.IRolePrivilegeService;
import com.edaisong.toolsapi.dao.inter.IRolePrivilegeDao;

/**
 * 服务提供对象 RolePrivilegeService
 * 
 * @author wangyuchuan
 * @date 2015-11-26 13:05:55
 *
 */
@Service
public class RolePrivilegeService implements IRolePrivilegeService {
	@Autowired
	private IRolePrivilegeDao rolePrivilegeDao;

	@Autowired
	private ServiceCacheProvider serviceCache;

	@Override
	public List<Integer> getRolePrivileges(Integer roleId) {
		return serviceCache.getRolePrivilegeFromCache(roleId);
	}

	@Override
	public void saveRolePrivilege(RolePrivilegeSaveReq saveParams) {
		rolePrivilegeDao.deleteByRoleId(saveParams.getRoleId());
		if (!StringUtils.isEmpty(saveParams.getMenuIds())) {
			List<RolePrivilege> privileges = new ArrayList<RolePrivilege>();
			String[] arrMenuIds = saveParams.getMenuIds().split(",");

			for (String menuId : arrMenuIds) {
				RolePrivilege p = new RolePrivilege();
				p.setMenuId(ParseHelper.ToInt(menuId));
				p.setRoleId(saveParams.getRoleId());
				privileges.add(p);
			}

			rolePrivilegeDao.insertBatch(privileges);
		}
		// 刷新缓存
		serviceCache.refreshRolePrivilegeInCache(saveParams.getRoleId());
	}
}