package com.edaisong.toolsapi.common;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;
import com.edaisong.toolscore.util.PropertyUtils;
import com.edaisong.toolsentity.domain.ConnectionInfo;

/**
 * 根据配置创建连接工厂
 * 
 * @author hailongzhao
 * @date 20151130
 *
 */
public class DaoUtil {
	/**
	 * 根据配置创建sqlserver连接工厂
	 * 
	 * @param conInfo
	 * @date 20151130
	 * @return
	 */
	public static Statement createStatement(ConnectionInfo conInfo) {
		try {
			String driverName = "com.microsoft.sqlserver.jdbc.SQLServerDriver";
			String sqlserver = "jdbc:sqlserver://%s:%s;DatabaseName=%s;";
			String url = String.format(sqlserver, conInfo.getHost(),conInfo.getPort(), conInfo.getDataBase());
			Class.forName(driverName);
			Connection dbConn = DriverManager.getConnection(url,conInfo.getUserName(), conInfo.getPassWord());
			Statement ste = dbConn.createStatement(ResultSet.TYPE_SCROLL_SENSITIVE,ResultSet.CONCUR_UPDATABLE);
			return ste;
		} catch (Exception e) 
			throw new RuntimeException("初始化sqlserver连接时出错" + e.getMessage());
		}
	}
}
