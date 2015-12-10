package com.edaisong.toolsapi.mongo;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.stereotype.Service;

import com.edaisong.toolscore.util.JsonUtil;
import com.edaisong.toolsentity.common.PagedResponse;
import com.edaisong.toolsentity.domain.ActionLog;
import com.edaisong.toolsentity.req.PagedMongoLogReq;
import com.mongodb.BasicDBObject;
import com.mongodb.DBCollection;
import com.mongodb.DBCursor;
import com.mongodb.DBObject;
import com.mongodb.QueryOperators;
import com.mongodb.util.JSON;

@Service
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
	public PagedResponse<ActionLog> selectPart(String tableName,PagedMongoLogReq req) throws Exception {
		if (req.getCurrentPage()==0) {
			req.setCurrentPage(1);  //默认第一页
		}
		if(req.getPageSize()==0){
			req.setPageSize(15);  //默认页容量
		}
		PagedResponse<ActionLog> result=new PagedResponse<ActionLog>();

		result.setCurrentPage(req.getCurrentPage());
		result.setPageSize(req.getPageSize());


		DBCollection collection = mongoTemplate.getCollection(tableName);
		createIndex(collection);
		DBCursor querycursor = collection.find(req.getQueryObject());
		if (req.getSortObject()!=null) {
			querycursor = querycursor.sort(req.getSortObject());
		}
		result.setTotalRecord(querycursor.count());
		int totalPage=0;
		int modPage=result.getTotalRecord()%req.getPageSize();
		if (modPage==0) {
			totalPage=result.getTotalRecord()/req.getPageSize();
		}else {
			totalPage=result.getTotalRecord()/req.getPageSize()+1;
		}
		result.setTotalPage(totalPage);
		
		querycursor=querycursor.skip((req.getCurrentPage()-1)*req.getPageSize()).limit(req.getPageSize());
		List<ActionLog> dataList=new ArrayList<ActionLog>();
		String json="";
		while (querycursor.hasNext()) {
			json=JsonUtil.obj2string(querycursor.next());
			ActionLog log=JsonUtil.str2obj(json, ActionLog.class);
			dataList.add(log);
		}
		result.setResultList(dataList);
		return result;
	}
	/**
	 * 第一次查询时，创建索引
	 * @author hailongzhao
	 * @date 20151208
	 * @param collection
	 */
	private void createIndex(DBCollection collection){
			List<DBObject> list = collection.getIndexInfo();
			if (list.size() == 1) {
				collection.createIndex(new BasicDBObject("sourceSys", 1));
				collection.createIndex(new BasicDBObject("requestType", 1));
				collection.createIndex(new BasicDBObject("requestUrl", 1));
				collection.createIndex(new BasicDBObject("methodName", 1));
				collection.createIndex(new BasicDBObject("requestTime", 1));
				collection.createIndex(new BasicDBObject("stackTrace", 1));
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
