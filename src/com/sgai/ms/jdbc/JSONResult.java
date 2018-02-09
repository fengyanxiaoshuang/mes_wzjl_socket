package com.sgai.ms.jdbc;

import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class JSONResult implements IResult {

	public Object parse(ResultSet rs) throws SQLException {
		ResultSetMetaData rsmd = null;
		rsmd = rs.getMetaData();
		int cols = rsmd.getColumnCount();
		StringBuffer json = new StringBuffer();
		json.append("[");
		while(rs.next()){
			String entity = "{";
			for(int i=1;i<=cols;i++){
				String value = rs.getString(i);
				String name = rsmd.getColumnName(i);
				value  = value==null?"":value;
				entity += "'"+name+"':'"+value+"',";
			}
			entity = entity.substring(0,entity.length()-1);
			entity += "}";
			json.append(entity).append(",");
		}
		if(json.length()==1)
			return null;
		json = json.replace(json.length()-1, json.length(), "]");
		return json.toString();
	}
	
	
	public Map<String,String> parseMap(ResultSet rs) throws SQLException {
		Map<String,String> map=new HashMap<String,String>();	
		while(rs.next()){
			map.put(rs.getString("mat_id"), rs.getString("weight"));
		}
		return map;
	}
	
	public Map<String,String> parseMapMat(ResultSet rs) throws SQLException {
		Map<String,String> map=new HashMap<String,String>();	
		while(rs.next()){
			map.put(rs.getString("METER_DESC"), rs.getString("SCALE_IP"));
		}
		return map;
	}
}
