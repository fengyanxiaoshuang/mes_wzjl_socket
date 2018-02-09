package com.sgai.ms.jdbc;

import java.util.Map;

public interface IDataSource {
	public void close() throws Exception;
	public boolean isAutoCommit() throws Exception;
	public void setAutoCommit(boolean autoCommit);
	public void commit() throws Exception;
	public void rollback() throws Exception;
	public int insert(String sql) throws Exception;
	public int update(String sql) throws Exception;
	public int delete(String sql) throws Exception;
	public String query(String sql) throws Exception;
	public Map<String,String> queryMap(String data) throws Exception;
	public Map<String,String> queryMapMat(String data) throws Exception;
}
