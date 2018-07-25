package com.yang.test.java.nio;

import java.io.IOException;
import java.io.RandomAccessFile;
import java.nio.ByteBuffer;
import java.nio.channels.FileChannel;

public class Simple {

	public static void main(String[] args) throws IOException {
		RandomAccessFile f = new RandomAccessFile("C:/1.txt", "rw");
		FileChannel fileChannel = f.getChannel();
		ByteBuffer buf = ByteBuffer.allocate(13);


		buf.clear();
		buf.put("456".getBytes());
		buf.flip();
		while (buf.hasRemaining()) {
			fileChannel.write(buf);
		}
		f.close();
	}
}