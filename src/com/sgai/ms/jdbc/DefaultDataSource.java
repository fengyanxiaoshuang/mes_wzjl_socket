package com.sgai.ms.jdbc;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.Map;

public class DefaultDataSource implements IDataSource {
	
	protected Connection connection = null;
	protected PreparedStatement ps = null;
	protected ResultSet rs = null;
	protected IResult result = null;
	private String driver;
	
	
	
	private String conn;
	private String userId;
	private String password;
	boolean autoCommit = true;
	public DefaultDataSource(){
		this.result = new JSONResult();
	}
	
	public DefaultDataSource(String conn,String oracleName,String userId,String password){
		this();
		this.driver = "oracle.jdbc.driver.OracleDriver";
		this.conn = "jdbc:oracle:thin:@"+ conn + ":" + oracleName;
		this.userId = userId;
		this.password = password;
	}
	
	public void getConnection() throws Exception{
		try{
			Class.forName(driver);
		}catch(ClassNotFoundException e){
			e.printStackTrace();
		}
		connection = DriverManager.getConnection(conn,userId==null?"":userId,password==null?"":password);
		connection.setAutoCommit(autoCommit);
	}
	
	public void close(){

		try {
			if(rs!=null){
				rs.close();
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}

		try {
			if(ps!=null){
				ps.close();
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}

		try {
			if(connection!=null){
				connection.close();
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}

	}
	
	public int update(String sql) throws Exception{
		int r = 0;

		getConnection();
		if(connection==null)
			throw new Exception("Can't get connection!");
		
		//System.out.println(sql);
		ps = connection.prepareStatement(sql);
		r = ps.executeUpdate();
		return r;
	}
	
	public int insert(String sql) throws Exception{
		return update(sql);
	}
	
	public int delete(String sql){
		return delete(sql);
	}
	
	public String query(String data) throws Exception{
		String json = null;
		try{
			getConnection();
			ps = connection.prepareStatement(data);
			rs = ps.executeQuery();
			json = (String)result.parse(rs);
		}catch(Exception e){
			if(!autoCommit){
				try{
					connection.rollback();
				}catch(Exception e1){
					throw e1;
				}
			}
			throw e;
		}finally{
			if(autoCommit)
				close();
		}
		return json;
	}
	
	
	public Map<String,String> queryMap(String data) throws Exception{
		 Map<String,String> map=new HashMap<String,String>();
		try{
			getConnection();
			ps = connection.prepareStatement(data);
			rs = ps.executeQuery();
			map = result.parseMap(rs);
		}catch(Exception e){
			if(!autoCommit){
				try{
					connection.rollback();
				}catch(Exception e1){
					throw e1;
				}
			}
			throw e;
		}finally{
			if(autoCommit)
				close();
		}
		return map;
	}
	
	public Map<String,String> queryMapMat(String data) throws Exception{
		 Map<String,String> map=new HashMap<String,String>();
		try{
			getConnection();
			ps = connection.prepareStatement(data);
			rs = ps.executeQuery();
			map = result.parseMapMat(rs);
		}catch(Exception e){
			if(!autoCommit){
				try{
					connection.rollback();
				}catch(Exception e1){
					throw e1;
				}
			}
			throw e;
		}finally{
			if(autoCommit)
				close();
		}
		return map;
	}
	
	
	public void commit() throws Exception{
		if(autoCommit)
			return;
		try{
			connection.commit();
		}catch(Exception e){
			throw e;
		}finally{
			close();
		}
	}
	
	public void rollback() throws Exception{
		if(autoCommit)
			return;
		try{
			connection.rollback();
		}catch(Exception e){
			throw e;
		}finally{
			close();
		}
	}
	
	public boolean isAutoCommit(){
		return autoCommit;
	}
	
	public void setAutoCommit(boolean autoCommit){
		this.autoCommit = autoCommit;
	}
}
