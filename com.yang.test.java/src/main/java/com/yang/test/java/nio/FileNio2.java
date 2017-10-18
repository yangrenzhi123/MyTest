package com.yang.test.java.nio;

import java.io.IOException;
import java.io.RandomAccessFile;
import java.nio.ByteBuffer;
import java.nio.channels.FileChannel;

@SuppressWarnings("resource")
public class FileNio2 {

	public static void main(String[] args) throws IOException {
		RandomAccessFile f = new RandomAccessFile("F:/1.txt", "r");
		FileChannel fileChannel = f.getChannel();
		ByteBuffer buf = ByteBuffer.allocate(10);

		int bytesRead;
		//do {
			buf.clear();
			bytesRead = fileChannel.read(buf);
			buf.flip();
			byte[] content = new byte[buf.limit()];
			buf.get(content);
			System.out.print(new String(content));
		//} while (bytesRead != -1);
	}
}