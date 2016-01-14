package com.edaisong.upload.common;


public class HttpResultModel<T> {
	private int code = HttpReturnRnums.Success.value();
	private String msg = HttpReturnRnums.Success.desc();
	private T data;
	public int getCode() {
		return code;
	}
	public HttpResultModel<T> setCode(int code) {
		this.code = code;
		return this;
	}
	public String getMsg() {
		return msg;
	}
	public HttpResultModel<T> setMsg(String msg) {
		this.msg = msg;
		return this;
	}
	public T getData() {
		return data;
	}
	public HttpResultModel<T> setData(T data) {
		this.data = data;
		return this;
	}

}
