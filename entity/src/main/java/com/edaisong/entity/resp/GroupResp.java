
package com.edaisong.entity.resp;

import java.util.List;
import com.edaisong.entity.Group;
import com.edaisong.entity.common.ResponseBase;

public class GroupResp extends ResponseBase{
private	List<Group> groupList;


public List<Group> getGroupList() {
	return groupList;
}

public void setGroupList(List<Group> groupList) {
	this.groupList = groupList;
}
}
