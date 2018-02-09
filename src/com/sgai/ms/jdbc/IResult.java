package com.sgai.ms.jdbc;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Map;

public interface IResult {
	public Object parse(ResultSet rs)throws SQLException;
	public Map<String,String> parseMap(ResultSet rs) throws SQLException;
	public Map<String,String> parseMapMat(ResultSet rs) throws SQLException;
}
