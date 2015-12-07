package com.edaisong.toolsentity.domain;
 
import java.util.Date;
 
/**
 * 实体类Role. (属性说明自动提取数据库字段的描述信息)
 * @author wangyuchuan
 * @date 2015-11-25 14:55:29
 *
 */
public class Role {
    /**
     * 角色Id
     */
    private Integer id;
 
    /**
     * 角色名称
     */
    private String name;
 
    /**
     * 是否管理员
     */
    private Boolean isAdmin;
 
    /**
     * 是否可以删除
     */
    private Boolean canRemove;
 
    /**
     * 创建人
     */
    private String createBy;
 
    /**
     * 创建时间
     */
    private Date createTime;
 
 
    /**
     * 获取角色Id
     */
    public Integer getId() {
        return id;
    }
    /**
     * 设置角色Id
     * @param id 角色Id
     */
    public void setId(Integer id) {
        this.id = id;
    }
 
    /**
     * 获取角色名称
     */
    public String getName() {
        return name;
    }
    /**
     * 设置角色名称
     * @param name 角色名称
     */
    public void setName(String name) {
        this.name = name;
    }
 
    /**
     * 获取是否管理员
     */
    public Boolean getIsAdmin() {
        return isAdmin;
    }
    /**
     * 设置是否管理员
     * @param isAdmin 是否管理员
     */
    public void setIsAdmin(Boolean isAdmin) {
        this.isAdmin = isAdmin;
    }
 
    /**
     * 获取是否可以删除
     */
    public Boolean getCanRemove() {
        return canRemove;
    }
    /**
     * 设置是否可以删除
     * @param canRemove 是否可以删除
     */
    public void setCanRemove(Boolean canRemove) {
        this.canRemove = canRemove;
    }
 
    /**
     * 获取创建人
     */
    public String getCreateBy() {
        return createBy;
    }
    /**
     * 设置创建人
     * @param createBy 创建人
     */
    public void setCreateBy(String createBy) {
        this.createBy = createBy;
    }
 
    /**
     * 获取创建时间
     */
    public Date getCreateTime() {
        return createTime;
    }
    /**
     * 设置创建时间
     * @param createTime 创建时间
     */
    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }
 
 
}