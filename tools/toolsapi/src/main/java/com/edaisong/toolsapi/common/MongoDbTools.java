package com.edaisong.toolsapi.common;

import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.SimpleMongoDbFactory;

import com.edaisong.toolscore.util.JsonUtil;
import com.edaisong.toolsentity.domain.ConnectionInfo;
import com.mongodb.MongoClientURI;

public class MongoDbTools {
   public MongoTemplate getMongoTemplate(String con){
		try {
			//"mongodb://user:pass@localhost:port/database"
			//"mongodb://localhost:port/database"
			ConnectionInfo conInfo = JsonUtil.str2obj(con, ConnectionInfo.class);
			MongoClientURI uri=new MongoClientURI(conInfo.getUrl());
			SimpleMongoDbFactory factory=new SimpleMongoDbFactory(uri);
		
			return new MongoTemplate(factory);
		} catch (Exception e) {
			throw new RuntimeException("创建MongoTemplate连接工厂时出错:" + e.getMessage());
		}
		
	}
}
