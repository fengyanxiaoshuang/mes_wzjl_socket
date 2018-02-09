package com.sgai.ms.socket;

import java.io.BufferedInputStream;
import java.io.FilterInputStream;
import java.io.IOException;
import java.net.ConnectException;
import java.net.Socket;
import java.net.UnknownHostException;

import javax.swing.JOptionPane;

import com.sgai.ms.main.MenuFrame;

public class SocketConnect extends Thread {

	private static MenuFrame menuFrame;
	
	private static SocketSend socketSend;

	private Thread mySelf;

	private String ip;
	
	private int port2;

	public int getPort2() {
		return port2;
	}

	public void setPort2(int port2) {
		this.port2 = port2;
	}

	private Socket clientSocket1 = null;

	private FilterInputStream is = null;
	
	private boolean flag = true; 

	public boolean isFlag() {
		return flag;
	}

	public void setFlag(boolean flag) {
		this.flag = flag;
	}

	public Thread getMySelf() {
		return mySelf;
	}

	public void setMySelf(Thread mySelf) {
		this.mySelf = mySelf;
	}

	public Object lock = new Object();

	public void setMenuFrame(MenuFrame menuFrame) {
		this.menuFrame = menuFrame;
	}
	
	public void setSocketSend(SocketSend socketSend) {
		this.socketSend = socketSend;
	}

	public void setIp(String ip) {
		this.ip = ip;
	}

	public Socket getClientSocket1() {
		return clientSocket1;
	}

	public FilterInputStream getIs() {
		return is;
	}

	public void setClientSocket1(Socket clientSocket1) {
		this.clientSocket1 = clientSocket1;
	}

	public void setIs(FilterInputStream is) {
		this.is = is;
	}

	public void run() {
		try {
			clientSocket1 = new Socket(ip, port2);
			clientSocket1.setSoTimeout(35000);
			is = new BufferedInputStream(clientSocket1.getInputStream());
			byte ibuf[] = new byte[40];
			int a = 0;
			synchronized (lock) {
				while (mySelf == Thread.currentThread()) {
					int j = is.read(ibuf, 0, 40);
					if (j == 29) {
						StringBuilder sb = null;
						a = 0;
						// 拼凑字符串 19:十万，24:个位
						sb = new StringBuilder();
						for (int k = 19; k < 25; k++) {
							if (ibuf[k] > 32) {
								sb.append(ibuf[k] - 48);
							} else if (ibuf[k] == 32) {
								sb.append("0");
							}
						}
						String ab = sb.toString();
						menuFrame.setTempWeight(ab);
					} else if (j == 6) {
						a++;
						if (a == 23) {
							break;
						}
						continue;
					}
				}
			}

		} catch (UnknownHostException uhe) {
			System.out.println("Error1:" + uhe.getMessage());
		} catch (ConnectException ce) {
			socketSend.setFlag(false);
			JOptionPane.showMessageDialog(menuFrame, "接收链接失败！");
			flag = false;
			menuFrame.end();
			System.out.println("Error2:" + ce.getMessage());
		} catch (IOException ioe) {
			if(mySelf != null){
				socketSend.setFlag(false);
				JOptionPane.showMessageDialog(menuFrame, "链接断开！");
				flag = false;
				menuFrame.end();
			}
			System.out.println("Error3:" + ioe.getMessage());
		} finally {
			
		}
	}

	public void startaa() {
		lock.notify();
	}

	public void end() {
		try {
			mySelf = null;
			if (!clientSocket1.isClosed()) {
				clientSocket1.close();
				System.out.println("clientSocket1.close");
			}
			is.close();
			System.out.println("is.close");
		} catch (IOException e) {
			System.out.println("Error:" + e.getMessage());
		} 
	}
}
