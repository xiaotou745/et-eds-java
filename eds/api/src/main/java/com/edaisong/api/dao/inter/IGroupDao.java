package com.edaisong.api.dao.inter;

import java.util.List;

import com.edaisong.entity.Group;
import com.edaisong.entity.common.PagedResponse;
import com.edaisong.entity.domain.GroupApiConfigModel;
import com.edaisong.entity.domain.GroupModel;
import com.edaisong.entity.req.GroupReq;
import com.edaisong.entity.req.PagedGroupReq;

public interface IGroupDao {
	int deleteByPrimaryKey(Long id);

	int insert(Group record);

	int insertSelective(Group record);

	Group selectByPrimaryKey(Long id);

	int updateByPrimaryKeySelective(Group record);

	int updateByPrimaryKey(Group record);

	List<GroupModel> getGroupListByID(Long id);

	List<GroupModel> getGroupList(GroupReq req);

	/**
	 * 查询第三方集团
	 * 
	 * @author CaoHeYang
	 * @param req
	 * @date 20160118
	 * @return
	 */
	PagedResponse<GroupApiConfigModel> getGroupListByPage(PagedGroupReq req);

	/**
	 * 更新集团状态
	 * 
	 * @author CaoHeYang
	 * @date 20160118
	 * @param group
	 * @return
	 */
	Integer updateGroupStatus(Group group);
}