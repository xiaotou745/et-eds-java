package com.edaisong.toolsentity;

import java.util.Date;

public class ClienterLocation {
    private Long id;

    private Double longitude;

    private Double latitude;

    private Integer clienterid;

    private Date createtime;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Double getLongitude() {
        return longitude;
    }

    public void setLongitude(Double longitude) {
        this.longitude = longitude;
    }

    public Double getLatitude() {
        return latitude;
    }

    public void setLatitude(Double latitude) {
        this.latitude = latitude;
    }

    public Integer getClienterid() {
        return clienterid;
    }

    public void setClienterid(Integer clienterid) {
        this.clienterid = clienterid;
    }

    public Date getCreatetime() {
        return createtime;
    }

    public void setCreatetime(Date createtime) {
        this.createtime = createtime;
    }
}