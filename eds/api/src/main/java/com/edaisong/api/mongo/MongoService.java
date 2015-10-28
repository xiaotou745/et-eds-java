package com.edaisong.api.mongo;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.stereotype.Service;

import com.mongodb.BasicDBObject;
import com.mongodb.DBCollection;
import com.mongodb.DBCursor;
import com.mongodb.DBObject;
import com.mongodb.util.JSON;

//@Service
public class MongoService {
	@Autowired
	private MongoTemplate mongoTemplate;

	public void saveInfo() {
		DBCollection collection = mongoTemplate.getCollection("user");
		BasicDBObject document = new BasicDBObject();
		document.put("id", 1);
		document.put("name", "小明");
		collection.insert(document);
	}

	public void saveStructureInfo() {
		DBCollection collection = mongoTemplate.getCollection("user");
		// 当保存这样的json串 (带子及节点的)
		/*
		 * { "id":1, "name","小明", "address": { "city":"beijing", "code":"065000"
		 * } }
		 */

		BasicDBObject document = new BasicDBObject();
		BasicDBObject addressDocument = new BasicDBObject();
		addressDocument.put("city", "beijing");
		addressDocument.put("code", "065000");
		document.put("address", addressDocument);
		collection.insert(document);
	}

	public void saveJsonInfo(String tableName, String jsonInfo) {
		DBCollection collection = mongoTemplate.getCollection(tableName);
		DBObject dbobjct = (DBObject) JSON.parse(jsonInfo);
		collection.insert(dbobjct);
	}

	public void selectAll() throws Exception {
		DBCollection collection = mongoTemplate.getCollection("user");

		// 查询操作
		// 查询所有
		// 其中类似access数据库中游标概念
		DBCursor cursor = collection.find();
		System.out.println("mongodb中的user表结果如下：");
		while (cursor.hasNext()) {
			System.out.println(cursor.next());
		}
	}

	/**
	 * 根据条件查询
	 * 
	 * @throws Exception
	 */
	public void selectPart() throws Exception {
		DBCollection collection = mongoTemplate.getCollection("user");
		// 可以直接put
		BasicDBObject queryObject = new BasicDBObject();
		queryObject.put("id", 1);
		DBCursor querycursor = collection.find(queryObject);
		System.out.println("条件查询如下：");
		while (querycursor.hasNext()) {
			System.out.println(querycursor.next());
		}
	}

	/**
	 * 更新操作 更新一条记录
	 * 
	 * @throws Exception
	 */
	public void update() throws Exception {
		DBCollection collection = mongoTemplate.getCollection("user");

		// 更新后的对象
		// 第一种更新方式
		BasicDBObject newBasicDBObject = new BasicDBObject();
		newBasicDBObject.put("id", 2);
		newBasicDBObject.put("name", "小红");
		collection
				.update(new BasicDBObject().append("id", 1), newBasicDBObject);

		// 第二种更新方式
		// 更新某一个字段
		// BasicDBObject newBasicDBObject =new BasicDBObject().append("$set",new
		// BasicDBObject().append("name", "小红") );
		// collection.update(new BasicDBObject().append("id", 1).append("name",
		// "小明"),newBasicDBObject);
		DBCursor querycursor1 = collection.find();
		System.out.println("更新后结果如下：");
		while (querycursor1.hasNext()) {
			System.out.println(querycursor1.next());
		}
	}

	/**
	 * 删除文档，其中包括删除全部或删除部分
	 * 
	 * @throws Exception
	 */
	public void delete() throws Exception {
		DBCollection collection = mongoTemplate.getCollection("user");
		BasicDBObject queryObject1 = new BasicDBObject();
		queryObject1.put("id", 2);
		queryObject1.put("name", "小红");

		// 删除某一条记录
		collection.remove(queryObject1);
		// 删除全部
		// collection.drop();

		DBCursor cursor1 = collection.find();
		System.out.println("删除后的结果如下：");
		while (cursor1.hasNext()) {
			System.out.println(cursor1.next());
		}
	}
}
