package com.yang.test.servlet.springmvc.taskjob;


/**
 * Hello world!
 *
 */
public class TaskJob {

	public void SayHello() {
		// TODO Auto-generated method stub
		try {
			// ҵ���߼��������
			System.out.println("ʱ��[" + new java.util.Date().toLocaleString()
					+ "]----->��Һð���");
		} catch (Exception e) {
			System.out.println("������������쳣");
		}
	}

}
