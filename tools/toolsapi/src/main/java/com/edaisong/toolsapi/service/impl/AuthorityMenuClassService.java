package com.edaisong.toolsapi.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.edaisong.toolsapi.dao.inter.IAuthorityMenuClassDao;
import com.edaisong.toolsapi.service.inter.IAuthorityMenuClassService;
import com.edaisong.toolsentity.AuthorityAccountMenuSet;
import com.edaisong.toolsentity.AuthorityMenuClass;
import com.edaisong.toolsentity.domain.MenuEntity;
import com.edaisong.toolsentity.req.AddNewMenuReq;

@Service
public class AuthorityMenuClassService implements IAuthorityMenuClassService {
	@Autowired
	private IAuthorityMenuClassDao dao;
	
	@Override
	public List<MenuEntity> getMenuListByUserID(int userID) {
		return dao.getMenuListByUserID(userID);
	}

	@Override
	public boolean checkHasAuth(int userID,int menuID) {
		List<MenuEntity> data=dao.getMenuListByUserID(userID);
		if (data!=null&&data.size()>0) {
			for (MenuEntity menuEntity : data) {
				if (menuID==menuEntity.getMenuid()) {
					return true;
				}
			}
		}

		return false;
	}

	@Override
	public List<MenuEntity> getAuthSettingList(int userID) {
		return dao.getAuthSettingList(userID);
	}

	@Override
	public List<MenuEntity> getRoleAuthSettingList(int roleID) {
		return dao.getRoleAuthSettingList(roleID);
	}

	@Override
	public List<AuthorityMenuClass> getListMenuByParId(int parId) {
		return dao.getListMenuByParId(parId);
	}

	@Override
	public AuthorityMenuClass getMenuById(int id) {
		return dao.getMenuById(id);
	}

	@Override
	public boolean addMenu(AuthorityMenuClass req) {
		return dao.addMenu(req);
	}



}
