package com.edaisong.toolsentity.domain;

import java.util.List;



public class AreaModelList {

	private List<AreaModel> areaModels;
    private String Version ;
    
    /**
     * 开放城市 集合
     * @return
     */
    public List<AreaModel> getAreaModels() {
		return areaModels;
	}
    /**
     *  开放城市 集合
     * @param areaModels
     */
	public void setAreaModels(List<AreaModel> areaModels) {
		this.areaModels = areaModels;
	}
	/**
	 * 版本号
	 * @return
	 */
	public String getVersion() {
		return Version;
	}
	/**
	 * 版本号
	 * @param version
	 */
	public void setVersion(String version) {
		Version = version;
	}

}
