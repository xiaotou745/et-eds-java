package com.edaisong.toolsentity;

import java.util.Date;

public class AppDbConfig {
    private Long id;

    private String appname;

    private String configvalue;

    private Integer configtype;

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

	public String getConfigvalue() {
		return configvalue;
	}

	public void setConfigvalue(String configvalue) {
		this.configvalue = configvalue;
	}

	public Integer getConfigtype() {
		return configtype;
	}

	public void setConfigtype(Integer configtype) {
		this.configtype = configtype;
	}
}