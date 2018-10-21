package com.yang.test.java.image;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.image.BufferedImage;
import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Arrays;

import javax.imageio.ImageIO;

import org.apache.commons.io.FileUtils;
import org.apache.commons.io.IOUtils;

public class ImgTools {

	/**
	 * 
	 * 将图片压缩到指定大小以内
	 * 
	 * 
	 * 
	 * @param srcImgData 源图片数据
	 * 
	 * @param maxSize    目的图片大小
	 * 
	 * @return 压缩后的图片数据
	 * 
	 */
	public static byte[] compressUnderSize(byte[] srcImgData, long maxSize) {
		double scale = 0.9;
		byte[] imgData = Arrays.copyOf(srcImgData, srcImgData.length);
		if (imgData.length > maxSize) {
			do {
				try {
					imgData = compress(imgData, scale);
				} catch (IOException e) {
					throw new IllegalStateException("压缩图片过程中出错，请及时联系管理员！", e);
				}
			} while (imgData.length > maxSize);
		}
		return imgData;
	}

	/**
	 * 
	 * 按照 宽高 比例压缩
	 * 
	 * 
	 * 
	 * @param imgIs 待压缩图片输入流
	 * 
	 * @param scale 压缩刻度
	 * 
	 * @param out   输出
	 * 
	 * @return 压缩后图片数据
	 * 
	 * @throws IOException 压缩图片过程中出错
	 * 
	 */
	public static byte[] compress(byte[] srcImgData, double scale) throws IOException {
		BufferedImage bi = ImageIO.read(new ByteArrayInputStream(srcImgData));
		int width = (int) (bi.getWidth() * scale); // 源图宽度
		int height = (int) (bi.getHeight() * scale); // 源图高度
		Image image = bi.getScaledInstance(width, height, Image.SCALE_SMOOTH);
		BufferedImage tag = new BufferedImage(width, height, BufferedImage.TYPE_INT_RGB);
		Graphics g = tag.getGraphics();
		g.setColor(Color.RED);
		g.drawImage(image, 0, 0, null); // 绘制处理后的图
		g.dispose();
		ByteArrayOutputStream bOut = new ByteArrayOutputStream();
		ImageIO.write(tag, "JPEG", bOut);
		return bOut.toByteArray();
	}

	public static void main(String[] args) throws IOException {
		byte[] data = ImgTools.compressUnderSize(readInByteArray(new File("c:/1.jpg")), 102400);
		FileUtils.writeByteArrayToFile(new File("c:/2.jpg"), data);
	}

	private static byte[] readInByteArray(File imgFile) throws FileNotFoundException, IOException {
		return IOUtils.toByteArray(new FileInputStream(imgFile));
	}
}