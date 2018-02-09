package com.sgai.ms.socket;

import java.io.BufferedWriter;
import java.io.DataOutputStream;
import java.io.IOException;
import java.io.OutputStreamWriter;
import java.net.ConnectException;
import java.net.InetSocketAddress;
import java.net.Socket;
import java.net.SocketAddress;
import java.net.UnknownHostException;

import com.sgai.ms.main.MenuFrame;

public class SocketSendButton{
	private static MenuFrame menuFrame;
	
	private String ip;
	
	private int port1;
	
	private String message1;
	
	private String message2;

	public static MenuFrame getMenuFrame() {
		return menuFrame;
	}

	public void setMenuFrame(MenuFrame menuFrame) {
		this.menuFrame = menuFrame;
	}
	
	public String getIp() {
		return ip;
	}

	public void setIp(String ip) {
		this.ip = ip;
	}

	public int getPort1() {
		return port1;
	}

	public void setPort1(int port1) {
		this.port1 = port1;
	}

	public String getMessage1() {
		return message1;
	}

	public void setMessage1(String message1) {
		this.message1 = message1;
	}

	public String getMessage2() {
		return message2;
	}

	public void setMessage2(String message2) {
		this.message2 = message2;
	}
	
	public void SocketSend(){
		Socket clientSocket = null;
		try {
			clientSocket = new Socket();
			SocketAddress socketAddress = new InetSocketAddress(ip, port1);
			clientSocket.connect(socketAddress, 5000);
			DataOutputStream dataOS = new DataOutputStream(clientSocket.getOutputStream());
			OutputStreamWriter outSW = new OutputStreamWriter(dataOS, "ISO8859-1");
			BufferedWriter bw = new BufferedWriter(outSW);
			int a =0;
			while (true) {
				if(a==1){
					bw.write(message2);
					bw.flush();
					break;
				}else{
					bw.write(message1);
					bw.flush();
					Thread.sleep(1000);
					a=1;
				}
			}
		} catch (UnknownHostException uhe) {
			System.out.println("Error1:" + uhe.getMessage());
		} catch (ConnectException ce) {
			System.out.println("Error2:" + ce.getMessage());
		} catch (IOException ioe) {
			System.out.println("Error3:" + ioe.getMessage());
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			try {
				if(clientSocket != null){
					clientSocket.close();
					System.out.println("SocketSend.close");
				}
			} catch (IOException e) {
				System.out.println("Error:" + e.getMessage());
			}
		}
	}
}