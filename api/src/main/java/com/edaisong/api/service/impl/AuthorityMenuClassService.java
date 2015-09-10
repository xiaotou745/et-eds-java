package com.edaisong.api.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.edaisong.api.dao.inter.IAuthorityMenuClassDao;
import com.edaisong.api.service.inter.IAuthorityMenuClassService;
import com.edaisong.entity.AuthorityAccountMenuSet;
import com.edaisong.entity.AuthorityMenuClass;
import com.edaisong.entity.MenuEntity;
import com.edaisong.entity.req.AddNewMenuReq;

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
