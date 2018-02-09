package com.sgai.ms.main;

import javax.swing.JFrame;
import javax.swing.JOptionPane;

import com.sgai.ms.jdbc.DefaultDataSource;
import com.sgai.ms.jdbc.IDataSource;
import com.sgai.ms.socket.SocketConnect;
import com.sgai.ms.socket.SocketSend;
import com.sgai.ms.socket.SocketSendButton;

/** 客户界面控制器: 客户端上下文环境 */
public class ClientContext {

	private MenuFrame menuFrame;

	private SocketConnect socketConnect;
	
	private SocketSend socketSend;
	
	private SocketSendButton socketSendButton;

	public void setSocketSendButton(SocketSendButton socketSendButton) {
		this.socketSendButton = socketSendButton;
	}

	public void setSocketConnect(SocketConnect socketConnect) {
		this.socketConnect = socketConnect;
	}

	public void setMenuFrame(MenuFrame menuFrame) {
		this.menuFrame = menuFrame;
	}

	public SocketSend getSocketSend() {
		return socketSend;
	}

	public void setSocketSend(SocketSend socketSend) {
		this.socketSend = socketSend;
	}

	public void show() {
		menuFrame.setVisible(true);
	}

	/**
	 * 退出系统 控制逻辑
	 * 
	 * @param source
	 *            代表从哪一个界面退出. 是一个窗口的引用
	 */
	public void exit(JFrame source) {
		int val = JOptionPane.showConfirmDialog(source, "离开吗?");
		if (val == JOptionPane.YES_OPTION) {
			System.exit(0);// 结束当前Java进程
		}
	}

	// 开始方法
	public void connect(JFrame source) {
		String[] config = menuFrame.getConfig();
		if (config == null) {
			return;
		}
		SocketConnect socketConnect = new SocketConnect();
		SocketSend socketSend = new SocketSend();
		socketConnect.setIp(config[4]);
		socketConnect.setPort2(Integer.parseInt(config[7]));
		socketConnect.setMenuFrame(menuFrame);
		socketConnect.setMySelf(socketConnect);
		socketConnect.setSocketSend(socketSend);
		this.setSocketConnect(socketConnect);
		socketConnect.start();
		
		socketSend.setIp(config[4]);
		socketSend.setPort1(Integer.parseInt(config[5]));
		socketSend.setTime(config[6]);
		socketSend.setMessage1(config[8]);
		socketSend.setMessage2(config[9]);
		socketSend.setMenuFrame(menuFrame);
		socketSend.setSocketConnect(socketConnect);
		this.setSocketSend(socketSend);
		socketSend.start();
		menuFrame.start();
	}

	// 停止方法
	public void end() {
		menuFrame.end();
		if(socketConnect.isFlag()){
			socketConnect.end();
		}
		if(socketSend.isFlag()){
			socketSend.end();
		}		
	}

	// 称重方法
	public void weight() {
		menuFrame.weightStart();
		String[] config = menuFrame.getConfig();
		if (config == null) {
			return;
		}
		SocketSendButton socketSendButton = new SocketSendButton();
		socketSendButton.setIp(config[4]);
		socketSendButton.setPort1(Integer.parseInt(config[5]));
		socketSendButton.setMessage1(config[8]);
		socketSendButton.setMessage2(config[9]);
		socketSendButton.setMenuFrame(menuFrame);
		socketSendButton.SocketSend();
		menuFrame.weightEnd();
	}

	// 保存方法
	public void save() {
		String[] weight = menuFrame.getWeight();
		if (weight == null) {
			return;
		}
		IDataSource dataSource = null;
		dataSource = new DefaultDataSource(weight[0],weight[1],weight[2],weight[3]);
		String sql = "insert into MES_MS_STANDARD_RECORD (CREATED_BY, CREATED_DT,VERSION, METER_CODE, MAT_ID, WEIGHT, WEIGHT_TIME)" +
				    "values ('auto',sysdate,1,'"+weight[4]+"',"+weight[5]+","+weight[6]+",sysdate)";
		
		System.out.println(sql);
		try {
			dataSource.insert(sql);
			dataSource.commit();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		menuFrame.save();
		JOptionPane.showMessageDialog(menuFrame, "保存重量成功！");
	}
}