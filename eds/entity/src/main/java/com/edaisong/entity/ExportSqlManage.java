package com.edaisong.entity;

public class ExportSqlManage {
    private Long id;

    private String name;

    private String executetime;

    private String receiveemail;

    private Short isenable;

    private String sqltext;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name == null ? null : name.trim();
    }

    public String getExecutetime() {
        return executetime;
    }

    public void setExecutetime(String executetime) {
        this.executetime = executetime == null ? null : executetime.trim();
    }

    public String getReceiveemail() {
        return receiveemail;
    }

    public void setReceiveemail(String receiveemail) {
        this.receiveemail = receiveemail == null ? null : receiveemail.trim();
    }

    public Short getIsenable() {
        return isenable;
    }

    public void setIsenable(Short isenable) {
        this.isenable = isenable;
    }

    public String getSqltext() {
        return sqltext;
    }

    public void setSqltext(String sqltext) {
        this.sqltext = sqltext == null ? null : sqltext.trim();
    }
}