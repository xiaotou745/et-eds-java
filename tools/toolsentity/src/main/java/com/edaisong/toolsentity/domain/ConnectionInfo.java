package com.edaisong.toolsentity.domain;


public class ConnectionInfo {
	private String host;
	private int port;
	private String dataBase;
	private String userName;
	private String passWord;
	public String getHost() {
		return host;
	}
	public void setHost(String host) {
		this.host = host;
	}
	public int getPort() {
		return port;
	}
	public void setPort(int port) {
		this.port = port;
	}

	public String getUserName() {
		return userName;
	}
	public void setUserName(String userName) {
		this.userName = userName;
	}
	public String getPassWord() {
		return passWord;
	}
	public void setPassWord(String passWord) {
		this.passWord = passWord;
	}
	public String getDataBase() {
		return dataBase;
	}
	public void setDataBase(String dataBase) {
		this.dataBase = dataBase;
	}
}
