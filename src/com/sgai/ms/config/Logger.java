package com.sgai.ms.config;

import java.text.SimpleDateFormat;
import java.util.Date;

public class Logger implements ILogger<String> {

	static final String FILE_NAME      = "appletUpload.log";
	static final int    FILE_MAX_COUNT = 10;
	static final long   FILE_MAX_SIZE  = 10*1024*1024;//10MB
	String method;
	String filePath;
	String view;
	SimpleDateFormat sdf;
	
	private void fileLog(int result,String msg){
		StringBuffer sb = new StringBuffer();
		sb.append("[").append(sdf.format(new Date())).append("]");
		sb.append("[").append(result).append("]");
		sb.append("  ").append(msg).append("\r\n");
	}
	
	public void log(int type, int result, String msg) {
		if(msg!=null){
			fileLog(result,msg);
		}
	}
	
	public void error(int type,String msg, Exception e){		
		StringBuffer sb = new StringBuffer();
		if(sb!=null)
			sb.append(msg).append(" ");
		
		sb.append(e.getMessage()).append("\r\n");
		for(int i=0;i<e.getStackTrace().length;i++){
			sb.append("\tat ").append(e.getStackTrace()[i]).append("\r\n");
		}
		
		fileLog(1,sb.toString());
	}
	
	public String getLogFilePath(){
		return filePath+FILE_NAME;
	}

}
