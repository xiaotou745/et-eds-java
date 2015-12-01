package com.edaisong.toolsapi.common;

import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.SimpleMongoDbFactory;

import com.edaisong.toolscore.enums.ServerType;
import com.edaisong.toolscore.util.JsonUtil;
import com.edaisong.toolsentity.domain.ConnectionInfo;
import com.mongodb.MongoClientURI;

/**
 * mongodb帮助类（连接指定的数据库）
 * @author hailongzhao
 *
 */
public class MongoDbUtil {
	/**
	 * 根据连接信息创建mongodb连接客户端
	 * @param conInfo
	 * @author hailongzhao
	 * @date 20151201
	 * @return
	 */
   public MongoTemplate getMongoTemplate(ConnectionInfo conInfo){
		try {
			//"mongodb://user:pass@localhost:port/database"
			//"mongodb://localhost:port/database
			String url="";
			if (conInfo.getUserName()==null||conInfo.getUserName().isEmpty()) {
				String noNameMongodb = "mongodb://%s:%s/%s";
				url= String.format(noNameMongodb,conInfo.getHost(),conInfo.getPort(),conInfo.getDataBase());
			}else {
				String mongodb = "mongodb://%s:%s@%s:%s/%s";
				url= String.format(mongodb,conInfo.getUserName(),conInfo.getPassWord(),conInfo.getHost(),conInfo.getPort(),conInfo.getDataBase());
			}
			
			MongoClientURI uri=new MongoClientURI(url);
			SimpleMongoDbFactory factory=new SimpleMongoDbFactory(uri);
		
			return new MongoTemplate(factory);
		} catch (Exception e) {
			throw new RuntimeException("创建MongoTemplate连接工厂时出错:" + e.getMessage());
		}
		
	}
}
