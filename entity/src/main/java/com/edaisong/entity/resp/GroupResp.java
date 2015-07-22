
package com.edaisong.entity.resp;

import java.util.List;

import com.edaisong.entity.Group;
import com.edaisong.entity.common.ResponseBase;
import com.edaisong.entity.domain.GroupModel;

public class GroupResp extends ResponseBase{
private	List<GroupModel> groupList;


public List<GroupModel> getGroupList() {
	return groupList;
}

public void setGroupList(List<GroupModel> groupList) {
	this.groupList = groupList;
}
}
