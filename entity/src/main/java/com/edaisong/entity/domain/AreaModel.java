package com.edaisong.entity.domain;


/**
 *开通城市的省市区 domain model
 * @author CaoHeYang
 * @Date 20150727
 */
public class AreaModel {
	
    private int code ;
    
   /**
    *  编码
    */
    public int getCode() {
		return code;
	}

    /**
     *  编码
     */
	public void setCode(int code) {
		this.code = code;
	}

	 /**
	    * 名称
	    */
	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	 /**
	    *  父级ID
	    */
	public int getParentId() {
		return parentId;
	}

	 /**
	    *  父级ID
	    */
	public void setParentId(int parentId) {
		this.parentId = parentId;
	}
	 /**
	    *  级别
	    */
	public int getJiBie() {
		return jiBie;
	}

	 /**
	    *  级别
	    */
	public void setJiBie(int jiBie) {
		this.jiBie = jiBie;
	}

	private String name ;
   
    private int parentId ;
   
    private int jiBie  ;

}
