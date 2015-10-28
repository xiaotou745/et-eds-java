package com.edaisong.core.util;

import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class ResultSetHelper {
	/**
	 * 获取一个ResultSet所有的列名列表
	 * 
	 * @param rs
	 * @return
	 * @throws SQLException
	 */
	public static List<String> getColumnNames(ResultSet rs) throws SQLException {
		List<String> result = new ArrayList<String>();
		ResultSetMetaData metaData = rs.getMetaData();
		int columnCount = metaData.getColumnCount();
		for (int i = 0; i < columnCount; i++) {
			result.add(metaData.getColumnName(i));
		}
		return result;
	}
}
