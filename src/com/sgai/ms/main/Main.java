package com.sgai.ms.main;

public class Main {
	public static void main(String[] args) {
		// �������ж���, ע��, ���������ϵ
		//������ҳ��
		MenuFrame menuFrame = new MenuFrame();
		ClientContext clientContext = new ClientContext();
		clientContext.setMenuFrame(menuFrame);
		menuFrame.setClientContext(clientContext);
		// ʹ��show()��ʾ����
		clientContext.show();
	}
}