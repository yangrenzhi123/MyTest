package com.yang.test.java.ZXing;

import java.io.File;
import java.io.IOException;
import java.nio.file.Path;

import com.google.zxing.BarcodeFormat;
import com.google.zxing.MultiFormatWriter;
import com.google.zxing.WriterException;
import com.google.zxing.client.j2se.MatrixToImageWriter;
import com.google.zxing.common.BitMatrix;

public class ZXing {
	public static void main(String[] args) {
		int width = 300;
		int height = 300;
		String format = "png";
		String contents = "http://wwww.baidu.com";
		try {
			BitMatrix bm = new MultiFormatWriter().encode(contents, BarcodeFormat.QR_CODE, width, height);
			Path file = new File("C:/img.png").toPath();
			MatrixToImageWriter.writeToPath(bm, format, file);
		} catch (WriterException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
}