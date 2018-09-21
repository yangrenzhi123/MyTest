package com.yang.test.java.nio;

import java.io.IOException;
import java.io.RandomAccessFile;
import java.nio.ByteBuffer;
import java.nio.channels.FileChannel;

@SuppressWarnings("resource")
public class FileNio3 {

	public static void main(String[] args) throws IOException {
		RandomAccessFile f = new RandomAccessFile("C:/1.txt", "rw");
		FileChannel fileChannel = f.getChannel();
		ByteBuffer buf = ByteBuffer.allocate(1024);

		for (int i = 0; i < 10; i++) {
			String newData = (i+1) + "\r\n";
			buf.clear();
			buf.put(newData.getBytes());
			buf.flip();
			while (buf.hasRemaining()) {
				fileChannel.write(buf);
			}
		}

		for (int i = 0; i < 10; i++) {
			String newData = (i+1) + "\r\n";
			buf.clear();
			buf.put(newData.getBytes());
			buf.flip();
			while (buf.hasRemaining()) {
				fileChannel.write(buf);
			}
		}
	}
}