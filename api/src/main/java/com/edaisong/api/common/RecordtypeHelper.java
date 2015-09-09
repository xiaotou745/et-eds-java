package com.edaisong.api.common;

import java.util.ArrayList;
import java.util.List;

import com.edaisong.core.enums.BusinessBalanceRecordRecordType;
import com.edaisong.entity.common.RecordType;

public class RecordtypeHelper {
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
}
