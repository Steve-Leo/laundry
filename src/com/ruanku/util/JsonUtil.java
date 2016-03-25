package com.ruanku.util;

import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.util.Iterator;

import net.sf.json.JSONArray;
import net.sf.json.JSONObject;

/**
 * 查询结果转换为json格式
 * @author brady
 *
 */
public class JsonUtil {
	public static JSONArray formatRsToJsonArray(ResultSet resultSet) throws SQLException {
		if (resultSet == null || resultSet.isClosed()) {
			return null;
		}
		ResultSetMetaData reMetaData = resultSet.getMetaData();
		JSONArray jsonArray = new JSONArray();
		while (resultSet.next()) {
			JSONObject singleObject = new JSONObject();
			for(int i = 1; i <= reMetaData.getColumnCount();i++){
				Object object = resultSet.getObject(i);
				singleObject.put(reMetaData.getColumnName(i), object);
			}
			jsonArray.add(singleObject);
		}
		return jsonArray;
	}
	
}
