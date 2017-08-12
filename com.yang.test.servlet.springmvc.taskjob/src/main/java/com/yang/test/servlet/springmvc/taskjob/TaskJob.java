package com.yang.test.servlet.springmvc.taskjob;


/**
 * Hello world!
 *
 */
public class TaskJob {

	public void SayHello() {
		// TODO Auto-generated method stub
		try {
			// 业务逻辑代码调用
			System.out.println("时间[" + new java.util.Date().toLocaleString()
					+ "]----->大家好啊！");
		} catch (Exception e) {
			System.out.println("处理任务出现异常");
		}
	}

}
