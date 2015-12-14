package com.edaisong.api.service.inter;

import com.edaisong.entity.taobao.req.TaoBaoAccessTokenReq;

public interface ITaoBaoAccessService {
	String getAccessToken(TaoBaoAccessTokenReq taoBaoAccessTokenReq);
}
