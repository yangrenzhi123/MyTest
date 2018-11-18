package com.yang.test.java.nio;

import java.io.File;
import java.io.IOException;
import java.io.RandomAccessFile;
import java.nio.ByteBuffer;
import java.nio.channels.FileChannel;
import java.util.ArrayList;
import java.util.List;

@SuppressWarnings("resource")
public class FileNio4 {

	public static void main(String[] args) throws IOException, InterruptedException {
		File txt = new File("C:/1.txt");
		if(txt.exists()) {
			txt.delete();
		}
		txt.createNewFile();
		RandomAccessFile f = new RandomAccessFile("C:/1.txt", "rw");
		final FileChannel fileChannel = f.getChannel();

		
		
		
		
		
		
		for(int i=0;i<100;i++) {
			List<Thread> tl = new ArrayList<>();
			for(int h=0;h<1000;h++) {
				tl.add(new Thread(new Runnable() {
					public void run() {
						String newData = "1";
						ByteBuffer buf = ByteBuffer.allocate(1);
						buf.clear();
						buf.put(newData.getBytes());
						buf.flip();
						try {
							fileChannel.write(buf);
						} catch (IOException e) {
							e.printStackTrace();
						}
					}
				}));
			}
			for(int j=0;j<1000;j++) {
				tl.get(j).start();
			}
		}
		
		Thread.sleep(2000);
		File file = new File("C:/1.txt");
		System.out.println(file.length());
	}
}