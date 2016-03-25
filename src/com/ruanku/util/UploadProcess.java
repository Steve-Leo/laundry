package com.ruanku.util;

import java.awt.Image;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;

import javax.imageio.ImageIO;

import com.sun.image.codec.jpeg.JPEGCodec;
import com.sun.image.codec.jpeg.JPEGEncodeParam;
import com.sun.image.codec.jpeg.JPEGImageEncoder;

/**
 * 文件上传处理类
 */
public class UploadProcess {
	
	private static final int SMALL_IMG_WIDTH = 100;
	private static final int SMALL_IMG_HEIGHT = 100;
	
	/**
	 * 将用户上传的文件保存到指定路径
	 */
	public static void uploadSrcImage(File file, String path) throws IOException {
		FileInputStream in = new FileInputStream(file);
		FileOutputStream fos = new FileOutputStream(path);
		byte buffer[] = new byte[8192];
		int count = 0;
		//开始向上传路径中刚建立的文件写入数据，每次8k字节
		while ((count = in.read(buffer)) != -1){
			fos.write(buffer,0,count);
		}
		//关闭文件流
		fos.flush();
		fos.close();
	}
	
	/**
	 * 上传压缩图片
	 */
	public static void uploadSmallImage(String srcPath, String path) throws IOException {
		/*生成压缩图并上传*/
		File srcFile = new File(srcPath);
		Image srcImage = ImageIO.read(srcFile);
		BufferedImage buffImg = getSmallImage(srcImage,SMALL_IMG_WIDTH,SMALL_IMG_HEIGHT);
		
		FileOutputStream fos = null;
		fos = new FileOutputStream(path);
		//创建JPEG图像编码器，用于编码内存中的图像数据到JPEG数据输出流
		JPEGImageEncoder encoder = JPEGCodec.createJPEGEncoder(fos);
		JPEGEncodeParam jep = JPEGCodec.getDefaultJPEGEncodeParam(buffImg);
		//编码BufferedImage对象到JPEG数据输出流
		encoder.encode(buffImg,jep);
		fos.close();
	}
	
	/*
	 * 图片压缩：指定图片的宽和高，按比例缩放
	 */
	public static BufferedImage getSmallImage(Image srcImage, int width,int height) {

		// SCALE_SMOOTH 的缩略算法 生成缩略图片的平滑度的 优先级比速度高 生成的图片质量比较好 但速度慢
		BufferedImage image = new BufferedImage(width, height,
				BufferedImage.TYPE_INT_RGB);
		image.getGraphics().drawImage(srcImage, 0, 0, width, width, null); // 绘制缩小后的图
		return image;
	}
}
