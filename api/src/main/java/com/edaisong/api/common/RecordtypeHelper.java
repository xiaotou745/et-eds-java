package com.edaisong.api.common;

import java.util.ArrayList;
import java.util.List;

import com.edaisong.core.enums.BusinessBalanceRecordRecordType;
import com.edaisong.core.enums.ClienterBalanceRecordRecordType;
import com.edaisong.entity.common.RecordType;
/**
 * app获取所有的筛选条件类型 集合
 * @author CaoHeYang
 * @date 20150909
 */
public class RecordtypeHelper {
  
	/**
	 * B端获取所有的筛选条件类型
	 * @author CaoHeYang
	 * @date 20150909
	 * @return
	 */
	public static List<RecordType> getRecordtypeB() {
		List<RecordType> recordTypes = new ArrayList<RecordType>();
		for (BusinessBalanceRecordRecordType c : BusinessBalanceRecordRecordType.values()) {
			RecordType recordType = new RecordType();
			recordType.setCode(c.value());
			recordType.setDesc(c.desc());
			recordType.setType(c.type());
			recordTypes.add(recordType);
		}
		return recordTypes;
	}
	/**
	 * C端获取所有的筛选条件类型
	 * @author CaoHeYang
	 * @date 20150909
	 * @return
	 */
	public static List<RecordType> getRecordtypeC() {
		List<RecordType> recordTypes = new ArrayList<RecordType>();
		for (ClienterBalanceRecordRecordType c : ClienterBalanceRecordRecordType.values()) {
			RecordType recordType = new RecordType();
			recordType.setCode(c.value());
			recordType.setDesc(c.desc());
			recordType.setType(c.type());
			recordTypes.add(recordType);
		}
		return recordTypes;
	}
}
