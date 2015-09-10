package com.edaisong.api.dao.inter;

import com.edaisong.entity.GroupBusinessBindOptionLog;
import com.edaisong.entity.common.PagedResponse;
import com.edaisong.entity.domain.GroupBusinessBindOptionLogModel;
import com.edaisong.entity.req.PagedBusinessBindLogReq;

public interface IGroupBusinessBindOptionLogDao {
    int deleteByPrimaryKey(Integer id);

    int insert(GroupBusinessBindOptionLog record);

    int insertSelective(GroupBusinessBindOptionLog record);

    GroupBusinessBindOptionLog selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(GroupBusinessBindOptionLog record);

    int updateByPrimaryKey(GroupBusinessBindOptionLog record);
    
    /**
     * 获得集团绑定商家操作日志列表
     * @author pengyi
     * @date 2015年9月10日 上午9:56:11
     * @version 1.0
     * @param req
     * @return
     */
    PagedResponse<GroupBusinessBindOptionLogModel> getBusinessBindLogList(PagedBusinessBindLogReq req);
}