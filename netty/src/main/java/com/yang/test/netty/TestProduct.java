package com.yang.test.netty;

import java.io.IOException;
import java.io.OutputStream;
import java.net.Socket;
import java.net.UnknownHostException;
import java.util.ArrayList;
import java.util.List;
import java.util.Properties;
import java.util.Random;
import java.util.concurrent.CountDownLatch;

@SuppressWarnings({"resource"})
public class TestProduct {

	public static final Properties properties = new Properties();
	static final int tn = 8;
	static final List<OutputStream> sockets = new ArrayList<>();
	
	public static void main(String[] args) throws InterruptedException, UnknownHostException, IOException {
		for(int i=0;i<tn;i++) {
			OutputStream os = new Socket("127.0.0.1", 8080).getOutputStream();
			sockets.add(os);
		}

		
		
		while (true) {
			test();
		}
	}

	static void test() throws InterruptedException, UnknownHostException, IOException {
		int count = 12000;
		final CountDownLatch latch = new CountDownLatch(count);
		
		

		List<Thread> l = new ArrayList<>();
		for (int i = 0; i < count; i++) {
			Thread a = new Thread(new Runnable() {
				public void run() {
					try {
						OutputStream os = sockets.get(new Random().nextInt(tn));
						os.write("5501020304050607E7010402040000021289C1FBC83F0A78689BB91F514C01ADD241C856F7AAEF60BFC24AFEB4015E5D3DBBAAB7F3AAB25881C0CA9F71486577B395390370F6A84AE0E0820D201C169C05021EA28228189B6940A005F994E54906A44906727E53EA3A1FC2900F6556193856FEF0E86B3E4D06DA598B382BB8E4EC38068D8672D35BB4134913020C6CE7D8D46466A8446FC5355779000EB4016562DAA056B43185B34CF04D6750D29EE4126D02AABB293802B348D5BE822C79A788F14EE2B06CA6B460D00D11B434C3055264388C3011482220D3B93626".getBytes());
					} catch (Exception e) {
						e.printStackTrace();
					} finally {
						latch.countDown();
					}
				}
			});
			l.add(a);
		}
		
		
		long a = System.currentTimeMillis();
		for (int i = 0; i < count; i++) {
			l.get(i).start();
		}

		latch.await();
		System.out.println(System.currentTimeMillis() - a);
	}
}