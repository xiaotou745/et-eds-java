package com.edaisong.entity.resp;

import java.util.List;


import com.edaisong.entity.MenuEntity;
import com.edaisong.entity.common.ResponseBase;

public class AuthorityMenuResp extends ResponseBase{
private	List<MenuEntity> menuList;

public List<MenuEntity> getMenuList() {
	return menuList;
}

public void setMenuList(List<MenuEntity> menuList) {
	this.menuList = menuList;
}
}
