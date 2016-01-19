package com.edaisong.api.dao.impl;

import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;








import org.springframework.stereotype.Repository;

import com.edaisong.api.common.DaoBase;
import com.edaisong.api.dao.inter.IGroupDao;
import com.edaisong.core.util.ParseHelper;
import com.edaisong.entity.Group;
import com.edaisong.entity.common.PagedResponse;
import com.edaisong.entity.domain.GroupApiConfigModel;
import com.edaisong.entity.domain.GroupModel;
import com.edaisong.entity.req.GroupReq;
import com.edaisong.entity.req.PagedGroupReq;

@Repository
public class GroupDao extends DaoBase implements IGroupDao {
	@Override
	public int deleteByPrimaryKey(Long id) {
		// TODO Auto-generated method stub
		return 0;
	}

	 @Override
	public int insert(Group record) {
			return getMasterSqlSessionUtil().insert(
					"IGroupDao.insert", record);
	}

	@Override
	public int insertSelective(Group record) {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public Group selectByPrimaryKey(Long id) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public int updateByPrimaryKeySelective(Group record) {
		return getMasterSqlSessionUtil()
				.update("IGroupDao.updateByPrimaryKeySelective",
						record);
	}

	@Override
	public int updateByPrimaryKey(Group record) {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public List<GroupModel> getGroupListByID(Long id) {
		Map<String, Object> paramMap = new HashMap<>();
		paramMap.put("id", id);
		List<GroupModel> list = getMasterSqlSessionUtil().selectList(
				"IGroupDao.getGroupListByID",
				paramMap);
		return list;

	}

	@Override
	public List<GroupModel> getGroupList(GroupReq req) {
		Map<String, Object> paramMap = new HashMap<>();
//		 paramMap.put("isvalid", req.getIsValid());
//		paramMap.put("groupname", req.getGroupName());
//		paramMap.put("appkey", req.getAppKey());
		List<GroupModel> list = getMasterSqlSessionUtil().selectList(
				"IGroupDao.getGroupList",
				paramMap);
		return list;
	}

	
	/**
	 * 查询第三方集团 
	 * @author CaoHeYang
	 * @param req
	 * @date 20160118
	 * @return
	 */
   public	 PagedResponse<GroupApiConfigModel>  getGroupListByPage(PagedGroupReq req){
	   return getReadOnlySqlSessionUtil().selectPageList("IGroupDao.getGroupListByPage", req);
   }
   
    /**
	 * 更新集团状态
	 * 
	 * @author CaoHeYang
	 * @date 20160118
	 * @param group
	 * @return
	 */
   public Integer updateGroupStatus(Group group){
		return getMasterSqlSessionUtil().update("IGroupDao.updateGroupStatus",group);
   }
   
	/**
	 * 判断集团是否已经存在
	 * @author CaoHeYang
	 * @date 20160118
	 * @param req
	 * @return
	 */
	public Integer hasExistsGroup(Group req) {
		return  ParseHelper.ToInt(getReadOnlySqlSessionUtil().selectOne("IGroupDao.hasExistsGroup",req), -1) ;
	}
	
	/**
	 * 创建集团
	 *  @date 20160118
	 *  @author CaoHeYang
	 * @param record
	 * @return
	 */
	public int  addGroup(Group record) {
		return getMasterSqlSessionUtil().insert("IGroupDao.addGroup", record);
	}
}
