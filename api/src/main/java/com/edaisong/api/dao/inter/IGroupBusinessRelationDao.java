package com.edaisong.api.dao.inter;

import com.edaisong.entity.GroupBusinessRelation;
import com.edaisong.entity.common.PagedResponse;
import com.edaisong.entity.domain.GroupBusinessRelationModel;
import com.edaisong.entity.req.BusinessBindOptionReq;
import com.edaisong.entity.req.PagedBizBindsReq;

public interface IGroupBusinessRelationDao {
    int deleteByPrimaryKey(Integer id);

    int insert(GroupBusinessRelation record);

    int insertSelective(GroupBusinessRelation record);

    GroupBusinessRelation selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(GroupBusinessRelation record);

    int updateByPrimaryKey(GroupBusinessRelation record);
    
    /**
     * 获得集团绑定商户列表(分页),只包含已绑定的门店
     * @author pengyi
     * @date 2015年9月9日 上午10:53:56
     * @version 1.0
     * @param req
     * @return
     */
    PagedResponse<GroupBusinessRelationModel> getBusinessBindList(PagedBizBindsReq req);
    
    /**
     * 移除门店绑定
     * @author pengyi
     * @date 2015年9月9日 下午4:00:47
     * @version 1.0
     * @param req
     * @return
     */
    boolean removeBusinessBind(BusinessBindOptionReq req);
    
    /**
     * 添加门店绑定
     * @author pengyi
     * @date 2015年9月9日 下午4:41:52
     * @version 1.0
     * @param req
     * @return
     */
    boolean addBusinessBind(BusinessBindOptionReq req);
    
    /**
     * 确认是否已经绑定
     * @author pengyi
     * @date 2015年9月9日 下午4:46:54
     * @version 1.0
     * @param req
     * @return
     */
    boolean checkHaveBind(BusinessBindOptionReq req);
    
    /**
     * 获得门店列表信息(包含已绑定和未绑定的所有门店)
     * @author pengyi
     * @date 2015年9月10日 上午10:20:32
     * @version 1.0
     * @param req
     * @return
     */
    PagedResponse<GroupBusinessRelationModel> getBusinessList(PagedBizBindsReq req);
}