package com.edaisong.toolsentity;

import java.util.Date;

public class AppDbConfig {
    private Long id;

    private String appname;

    private String dburl;

    private String redisurl;

    private Date createtime;

    private Date updatetime;

    private String createname;

    private String updatename;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getAppname() {
        return appname;
    }

    public void setAppname(String appname) {
        this.appname = appname == null ? null : appname.trim();
    }

    public String getDburl() {
        return dburl;
    }

    public void setDburl(String dburl) {
        this.dburl = dburl == null ? null : dburl.trim();
    }

    public String getRedisurl() {
        return redisurl;
    }

    public void setRedisurl(String redisurl) {
        this.redisurl = redisurl == null ? null : redisurl.trim();
    }

    public Date getCreatetime() {
        return createtime;
    }

    public void setCreatetime(Date createtime) {
        this.createtime = createtime;
    }

    public Date getUpdatetime() {
        return updatetime;
    }

    public void setUpdatetime(Date updatetime) {
        this.updatetime = updatetime;
    }

    public String getCreatename() {
        return createname;
    }

    public void setCreatename(String createname) {
        this.createname = createname == null ? null : createname.trim();
    }

    public String getUpdatename() {
        return updatename;
    }

    public void setUpdatename(String updatename) {
        this.updatename = updatename == null ? null : updatename.trim();
    }
}