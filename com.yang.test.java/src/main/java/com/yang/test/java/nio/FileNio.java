package com.yang.test.java.nio;

import java.io.IOException;
import java.io.RandomAccessFile;
import java.nio.ByteBuffer;
import java.nio.channels.FileChannel;
import java.util.Random;

public class FileNio {

	public static void main(String[] args) throws IOException {
		RandomAccessFile raf = new RandomAccessFile("C:/1.txt", "rw");
		raf.seek(raf.length()); //不覆盖源文件
		FileChannel fileChannel = raf.getChannel();
		ByteBuffer buf = ByteBuffer.allocate(1024);

		Random r = new Random();
		long a = System.currentTimeMillis();
		
		for (int i = 0; i < 100000; i++) {
			int v = r.nextInt(400000000);
			
			String newData = (v+1) + "\r\n";
			buf.clear();
			buf.put(newData.getBytes());
			buf.flip();
			while (buf.hasRemaining()) {
				fileChannel.write(buf);
			}

//			int bytesRead;
//			do {
//				buf.clear();
//				bytesRead = fileChannel.read(buf);
//				buf.flip();
//				byte[] content = new byte[buf.limit()];
//				buf.get(content);
//				System.out.print(new String(content));
//			} while (bytesRead != -1);
		}
		
		System.out.println(System.currentTimeMillis() - a);
		raf.close();
	}
}