package com.edaisong.entity.common;

public class ResponseBase {
	private int responseCode = ResponseCode.SUCESS;
    private String message;
    private String processServerIp;

    public int getResponseCode() {
        return responseCode;
    }

    public ResponseBase setResponseCode(int responseCode) {
        this.responseCode = responseCode;
        return this;
    }

    public String getMessage() {
        return message;
    }

    public ResponseBase setMessage(String message) {
        this.message = message;
        return this;
    }

    public String getProcessServerIp() {
        return processServerIp;
    }

    public ResponseBase setProcessServerIp(String processServerIp) {
        this.processServerIp = processServerIp;
        return this;
    }
}
