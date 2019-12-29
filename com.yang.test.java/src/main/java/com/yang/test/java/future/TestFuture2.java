package com.yang.test.java.future;
import java.util.concurrent.Callable;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.Executors;

import org.springframework.util.concurrent.ListenableFutureCallback;
import org.springframework.util.concurrent.ListenableFutureTask;

public class TestFuture2 {

	public static void main(String[] args) throws InterruptedException, ExecutionException {
		ListenableFutureTask<String> task = new ListenableFutureTask<String>(new Callable<String>() {
			@Override
			public String call() throws Exception {
				Thread.sleep(5000);
				return "success";
			}
		});
		task.addCallback(new ListenableFutureCallback<String>() {
			@Override
			public void onFailure(Throwable throwable) {
				System.out.println("调用失败");
			}

			@Override
			public void onSuccess(String s) {
				System.out.println("调用成功：" + s);
			}
		});
		Executors.newSingleThreadExecutor().submit(task);
		
		task.get();

		System.out.println(1);
	}
}