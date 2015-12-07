package com.edaisong.toolsentity.domain;
 
import java.util.Date;
 
/**
 * 实体类User. (属性说明自动提取数据库字段的描述信息)
 * @author wangyuchuan
 * @date 2015-11-25 15:03:50
 *
 */
public class User {
    /**
     * 用户ID
     */
    private Integer id;
 
    /**
     * 登录账号
     */
    private String loginName;
 
    /**
     * 登录密码
     */
    private String loginPwd;
 
    /**
     * 用户名
     */
    private String userName;
 
    /**
     * 所属角色
     */
    private String roleIds;
 
    /**
     * 是否有效
     */
    private Boolean isEnable;
 
    /**
     * 是否禁用
     */
    private Boolean isDisable;
 
    /**
     * 创建人
     */
    private String createBy;
 
    /**
     * 创建时间
     */
    private Date createTime;
 
 
    /**
     * 获取用户ID
     */
    public Integer getId() {
        return id;
    }
    /**
     * 设置用户ID
     * @param id 用户ID
     */
    public void setId(Integer id) {
        this.id = id;
    }
 
    /**
     * 获取登录账号
     */
    public String getLoginName() {
        return loginName;
    }
    /**
     * 设置登录账号
     * @param loginName 登录账号
     */
    public void setLoginName(String loginName) {
        this.loginName = loginName;
    }
 
    /**
     * 获取登录密码
     */
    public String getLoginPwd() {
        return loginPwd;
    }
    /**
     * 设置登录密码
     * @param loginPwd 登录密码
     */
    public void setLoginPwd(String loginPwd) {
        this.loginPwd = loginPwd;
    }
 
    /**
     * 获取用户名
     */
    public String getUserName() {
        return userName;
    }
    /**
     * 设置用户名
     * @param userName 用户名
     */
    public void setUserName(String userName) {
        this.userName = userName;
    }
 
    /**
     * 获取所属角色
     */
    public String getRoleIds() {
        return roleIds;
    }
    /**
     * 设置所属角色
     * @param roleIds 所属角色
     */
    public void setRoleIds(String roleIds) {
        this.roleIds = roleIds;
    }
 
    /**
     * 获取是否有效
     */
    public Boolean getIsEnable() {
        return isEnable;
    }
    /**
     * 设置是否有效
     * @param isEnable 是否有效
     */
    public void setIsEnable(Boolean isEnable) {
        this.isEnable = isEnable;
    }
 
    /**
     * 获取是否禁用
     */
    public Boolean getIsDisable() {
        return isDisable;
    }
    /**
     * 设置是否禁用
     * @param isDisable 是否禁用
     */
    public void setIsDisable(Boolean isDisable) {
        this.isDisable = isDisable;
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