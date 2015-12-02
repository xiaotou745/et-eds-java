package com.edaisong.toolsapi.common;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.edaisong.toolsentity.domain.ConnectionInfo;

/**
 * 原生sqlserver数据库连接帮助类
 * @author hailongzhao
 * @date 20151130
 *
 */
public class SQLServerUtil {
	/**
	 * 查询一个结果集
	 * @param conInfo
	 * @param sql
	 * @author hailongzhao
	 * @date 20151201
	 * @return
	 */
	public static List<Map<String, String>> executeResultSet(ConnectionInfo conInfo,String sql){
		return (List<Map<String, String>>)executeSql(0,conInfo, sql);
	}
	/**
	 * 执行update，insert，delete
	 * @param conInfo
	 * @param sql
	 * @author hailongzhao
	 * @date 20151201
	 * @return
	 */
	public static int executeUpdate(ConnectionInfo conInfo,String sql){
		return (int)executeSql(1,conInfo, sql);
	}
	/**
	 * 根据配置创建sqlserver连接
	 * 
	 * @param conInfo
	 * @author hailongzhao
	 * @date 20151201
	 * @return
	 */
	private static  Statement createStatement(ConnectionInfo conInfo) {
		try {
			String driverName = "com.microsoft.sqlserver.jdbc.SQLServerDriver";
			String sqlserver = "jdbc:sqlserver://%s:%s;DatabaseName=%s;";
			String url = String.format(sqlserver, conInfo.getHost(),conInfo.getPort(), conInfo.getDataBase());
			Class.forName(driverName);
			Connection dbConn = DriverManager.getConnection(url,conInfo.getUserName(), conInfo.getPassWord());
			Statement ste = dbConn.createStatement(ResultSet.TYPE_SCROLL_SENSITIVE,ResultSet.CONCUR_UPDATABLE);
			dbConn.setAutoCommit(true);
			return ste;
		} catch (Exception e) {
			throw new RuntimeException("初始化sqlserver连接时出错" + e.getMessage());
		}
	}
	/**
	 * 封装sql执行
	 * @param executeType 0是查询结果集，1是更新
	 * @param conInfo
	 * @param sql
	 * @author hailongzhao
	 * @return
	 */
	private static Object executeSql(int executeType,ConnectionInfo conInfo,String sql){
		Statement ste=null;
		try {
			ste=createStatement(conInfo);
			switch (executeType) {
			case 0:
				ResultSet rs= ste.executeQuery(sql);
				return getListData(rs);
			case 1:
				return ste.executeUpdate(sql);
			default:
				break;
			}
			return null;
		} catch (Exception e) {
			throw new RuntimeException(e.getMessage());
		}finally{
			if (ste!=null) {
				try {
					ste.close();
					ste.getConnection().close();
				} catch (Exception e) {
				}
			}
		}
	}
	/**
	 * ResultSet转换为List<Map<String, String>>，并关闭ResultSet
	 * @param rs
	 * @author hailongzhao
	 * @date 20151201
	 * @return
	 */
	private static List<Map<String, String>> getListData(ResultSet rs){
		List<Map<String, String>> result = new ArrayList<Map<String, String>>();
		try {
			ResultSetMetaData rsmd = rs.getMetaData();
			int colCount = rsmd.getColumnCount();
			String columnName ="";
			String columnValue ="";
			while (rs.next()) {
				Map<String, String> rowMap = new HashMap<>();
				for (int j = 0; j < colCount; j++) {
				    columnName = rsmd.getColumnName(j+1);
					columnValue = rs.getString(columnName);
					rowMap.put(columnName, columnValue);
				}
				result.add(rowMap);
			}
		} catch (Exception e) {
			throw new RuntimeException("ResultSet转换为List时出错:" + e.getMessage());
		}finally{
			try {
				rs.close();
			} catch (Exception e) {
			}
		}
		return result;
	}
}
