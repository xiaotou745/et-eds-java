/**
 * Alipay.com Inc.
 * Copyright (c) 2004-2014 All Rights Reserved.
 */
package com.alipay.executor;

import com.alipay.common.MyException;
import com.alipay.factory.AlipayAPIClientFactory;
import com.alipay.parmodel.ParamPlatformModel;

/**
 * 开通服务窗开发者功能处理器
 * 
 * @author taixu.zqq
 * @version $Id: InAlipayOpenExecutor.java, v 0.1 2014年7月24日 下午5:05:13 taixu.zqq Exp $
 */
public class InAlipayVerifyExecutor implements ActionExecutor {

    /** 
     * @see com.alipay.executor.ActionExecutor#executor(java.util.Map)
     */
    @Override
    public String execute(int platform) throws MyException {
        return this.setResponse(platform);
    }

    /**
     * 设置response返回数据
     * 
     * @return
     */
    private String setResponse(int platform) throws MyException {
    	ParamPlatformModel paramPlatformModel=AlipayAPIClientFactory.getPlatformModel(platform);
        //固定响应格式，必须按此格式返回
        StringBuilder builder = new StringBuilder();
        builder.append("<success>").append(Boolean.TRUE.toString()).append("</success>");
        
        builder.append("<biz_content>").append(paramPlatformModel.getPublic_key())
            .append("</biz_content>");
        return builder.toString();
    }
}
