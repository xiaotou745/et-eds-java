package com.edaisong.entity.common;

public class HttpResultModel<T> {
	private int Status = HttpReturnRnums.Success.value();
	private String Message = HttpReturnRnums.Success.desc();
	private T Result;

	/**
	 * 状态
	 * 
	 * @return
	 */
	public int getStatus() {
		return Status;
	}

	/**
	 * 状态
	 * 
	 * @param status
	 */
	public HttpResultModel<T> setStatus(int status) {
		Status = status;
		return this;
	}

	/**
	 * 状态
	 * 
	 * @return
	 */
	public String getMessage() {
		return Message;
	}

	/**
	 * 状态
	 * 
	 * @param message
	 */
	public HttpResultModel<T> setMessage(String message) {
		Message = message;
		return this;
	}

	/**
	 * 
	 * @return
	 */
	public T getResult() {
		return Result;
	}

	/**
	 * 
	 * @param result
	 */
	public HttpResultModel<T> setResult(T result) {
		Result = result;
		return this;
	}

}
