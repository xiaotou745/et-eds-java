package com.edaisong.toolsapi.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.edaisong.toolsentity.AccountInfo;
import com.edaisong.toolsentity.RoleAuth;
import com.edaisong.toolsapi.dao.inter.IAccountInfoDao;
import com.edaisong.toolsapi.dao.inter.IRoleAuthDao;
import com.edaisong.toolsapi.redis.RedisService;
import com.edaisong.toolsapi.service.inter.IRoleAuthService; 
import com.edaisong.toolscore.consts.RedissCacheKey;


@Service
public class RoleAuthService implements IRoleAuthService{

	@Autowired
	private IRoleAuthDao roleAuthDao;
	@Autowired
	private IAccountInfoDao accountInfoDao;
	@Autowired
	private RedisService redisService;
	@Override
	public boolean modifyAuthList(List<RoleAuth> authList) {
		if (authList!=null&&authList.size()>0) {
			boolean result= roleAuthDao.modifyAuthList(authList);
			//如果这个角色的权限更新成功，则将该角色下的所有用户的权限缓存移除
			if (result) {
				List<AccountInfo> roleAccounts=accountInfoDao.getByRoleID(authList.get(0).getRoleId());
				for (AccountInfo accountInfo : roleAccounts) {
					String key=RedissCacheKey.Menu_Auth+accountInfo.getId();
					redisService.remove(key);
				}
			}
		}
		return false;
	}


}
