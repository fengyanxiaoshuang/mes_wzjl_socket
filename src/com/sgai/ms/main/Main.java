package com.sgai.ms.main;

public class Main {
	public static void main(String[] args) {
		// 创建所有对象, 注入, 解决依赖关系
		//定义主页面
		MenuFrame menuFrame = new MenuFrame();
		ClientContext clientContext = new ClientContext();
		clientContext.setMenuFrame(menuFrame);
		menuFrame.setClientContext(clientContext);
		// 使用show()显示界面
		clientContext.show();
	}
}