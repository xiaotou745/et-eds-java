package com.edaisong.api_http.entity;

public class ResultModel<T> {
	private int Status = ReturnRnums.Success.value();
	private String Message = ReturnRnums.Success.desc();
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
	public ResultModel<T> setStatus(int status) {
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
	public ResultModel<T> setMessage(String message) {
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
	public ResultModel<T> setResult(T result) {
		Result = result;
		return this;
	}

}
