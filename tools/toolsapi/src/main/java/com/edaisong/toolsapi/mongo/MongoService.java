package com.edaisong.toolsapi.mongo;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.stereotype.Service;

import com.edaisong.toolscore.util.JsonUtil;
import com.edaisong.toolsentity.common.PagedResponse;
import com.edaisong.toolsentity.domain.ActionLog;
import com.edaisong.toolsentity.req.MongoLogReq;
import com.edaisong.toolsentity.req.PagedMongoLogReq;
import com.mongodb.BasicDBObject;
import com.mongodb.Bytes;
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


	/**
	 * 根据条件查询
	 * 
	 * @throws Exception
	 */
	@SuppressWarnings("resource")
	public PagedResponse<ActionLog> selectPart(String tableName,PagedMongoLogReq req) throws Exception {
		DBCursor querycursor = null;
		PagedResponse<ActionLog> result = new PagedResponse<ActionLog>();
		try {
			if (req.getCurrentPage() == 0) {
				req.setCurrentPage(1); // 默认第一页
			}
			if (req.getPageSize() == 0) {
				req.setPageSize(15); // 默认页容量
			}

			result.setCurrentPage(req.getCurrentPage());
			result.setPageSize(req.getPageSize());

			DBCollection collection = mongoTemplate.getCollection(tableName);
			createIndex(collection);
			querycursor = collection.find(req.getQueryObject());
			if (req.getSortObject() != null) {
				querycursor = querycursor.sort(req.getSortObject());
			}
			result.setTotalRecord(querycursor.count());
			int totalPage = 0;
			int modPage = result.getTotalRecord() % req.getPageSize();
			if (modPage == 0) {
				totalPage = result.getTotalRecord() / req.getPageSize();
			} else {
				totalPage = result.getTotalRecord() / req.getPageSize() + 1;
			}
			result.setTotalPage(totalPage);

			querycursor = querycursor.skip((req.getCurrentPage() - 1) * req.getPageSize()).limit(req.getPageSize());// .addOption(Bytes.QUERYOPTION_NOTIMEOUT);
			List<ActionLog> dataList = new ArrayList<ActionLog>();
			String json = "";
			while (querycursor.hasNext()) {
				json = JsonUtil.obj2string(querycursor.next());
				ActionLog log = JsonUtil.str2obj(json, ActionLog.class);
				dataList.add(log);
			}
			result.setResultList(dataList);
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			querycursor.close();
		}

		return result;
	}

	public List<String> selectDistinct(String tableName,String columName){
		try {
			DBCollection collection = mongoTemplate.getCollection(tableName);
			return collection.distinct(columName);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return new ArrayList<>();
	}
	public List<ActionLog> selectResult(String tableName,BasicDBObject req,BasicDBObject colums) throws Exception {
		List<ActionLog> dataList = new ArrayList<ActionLog>();
		DBCursor querycursor = null;
		try {
			DBCollection collection = mongoTemplate.getCollection(tableName);
			createIndex(collection);
			querycursor = collection.find(req, colums);// .addOption(Bytes.QUERYOPTION_NOTIMEOUT);
			String json = "";
			//BasicDBObject deleDbObject = new BasicDBObject();  
			while (querycursor.hasNext()) {
				json = JsonUtil.obj2string(querycursor.next());
				ActionLog log = JsonUtil.str2obj(json, ActionLog.class);
				dataList.add(log);
			}
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			if (querycursor!=null) {
				querycursor.close();
			}
		}

		return dataList;
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
				collection.createIndex(new BasicDBObject("requestTime", 1));
				collection.createIndex(new BasicDBObject("executeTime", 1));
				//如果查询时，按照(不等于)去查询，则由于索引，会导致查不出来数据
//				collection.createIndex(new BasicDBObject("requestUrl", 1));
//				collection.createIndex(new BasicDBObject("methodName", 1));
//				collection.createIndex(new BasicDBObject("stackTrace", 1));
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
	 * 删除记录
	 * 
	 * @throws Exception
	 */
	public void delete(String tableName,BasicDBObject req) throws Exception {
		DBCollection collection = mongoTemplate.getCollection(tableName);
		// 删除某一条记录
		collection.remove(req);
		// 删除全部
		// collection.drop();
	}
}
