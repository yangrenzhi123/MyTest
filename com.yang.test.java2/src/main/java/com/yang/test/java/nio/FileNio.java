package com.yang.test.java.nio;

import java.io.IOException;
import java.io.RandomAccessFile;
import java.nio.ByteBuffer;
import java.nio.channels.FileChannel;

@SuppressWarnings("resource")
public class FileNio {

	public static void main(String[] args) throws IOException {
		RandomAccessFile f = new RandomAccessFile("C:/1.txt", "rw");
		FileChannel fileChannel = f.getChannel();
		ByteBuffer buf = ByteBuffer.allocate(1024);

		for (int i = 0; i < 10; i++) {
			String newData = i + "\r\n";
			buf.clear();
			buf.put(newData.getBytes());
			buf.flip();
			while (buf.hasRemaining()) {
				fileChannel.write(buf);
			}

			int bytesRead;
			do {
				buf.clear();
				bytesRead = fileChannel.read(buf);
				buf.flip();
				byte[] content = new byte[buf.limit()];
				buf.get(content);
				System.out.print(new String(content));
			} while (bytesRead != -1);
		}
	}
}