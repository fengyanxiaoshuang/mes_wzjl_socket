package com.sgai.ms.config;
public interface ILogger<T> {
	int SEND    = 0;
	int RECEIVE = 1;
	int SYSTEM  = 2;
	
	int SUCCESS = 0;
	int FAILED  = 1;
	int INFO    = 2;

	/**
	 * 记录日志
	 * @param type 日志类型(SEND|RECEIVE|SYSTEM)
	 * @param result 结果(SUCCESS|FAILED|INFO)
	 * @param msg 日志内容
	 */
	public void log(int type,int result,T msg);
	
	/**
	 * 记录异常日志信息
	 * @param type 日志类型(SEND|RECEIVE|SYSTEM)
	 * @param msg 日志内容
	 * @param e 异常信息
	 */
	public void error(int type,T msg,Exception e);
	
}
