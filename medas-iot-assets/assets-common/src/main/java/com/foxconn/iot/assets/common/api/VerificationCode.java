package com.foxconn.iot.assets.common.api;

import java.awt.BasicStroke;
import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics2D;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.io.OutputStream;
import java.util.Random;

import javax.imageio.ImageIO;

public class VerificationCode {
	
	private static int width = 100;
	
	private static int height = 30;
	
	private static String[] fontNames = { "Arial", "Verdana", "Arial Black" };
	private static Color bgColor = new Color(255, 255, 255);
	private static Random random = new Random();
	private static String codes = "23456789ABCDEFGHJKLMNPQRSTUVWXYZ";
	
	/**
	 * 获取一个随意颜色
	 * 
	 * @return
	 */
	private static Color randomColor() {
		int red = random.nextInt(200);
		int green = random.nextInt(200);
		int blue = random.nextInt(200);
		return new Color(red, green, blue);
	}

	/**
	 * 获取一个随机字体
	 * 
	 * @return
	 */
	private static Font randomFont() {
		String name = fontNames[random.nextInt(fontNames.length)];
		int style = random.nextInt(4);
		int size = random.nextInt(5) + 24;
		return new Font(name, style, size);
	}

	/**
	 * 获取一个随机字符
	 * 
	 * @return
	 */
	private static char randomChar() {
		return codes.charAt(random.nextInt(codes.length()));
	}

	/**
	 * 创建一个空白的BufferedImage对象
	 * 
	 * @return
	 */
	private static BufferedImage createImage() {
		BufferedImage image = new BufferedImage(width, height, BufferedImage.TYPE_INT_RGB);
		Graphics2D g2 = (Graphics2D) image.getGraphics();
		g2.setColor(bgColor);
		g2.fillRect(0, 0, width, height);
		return image;
	}

	public static BufferedImage getImage(String text) {
		BufferedImage image = createImage();
		Graphics2D g2 = (Graphics2D) image.getGraphics();
		for (int i = 0; i < text.length(); i++) {
			String s = text.substring(i, i + 1);
			g2.setColor(randomColor());
			g2.setFont(randomFont());
			float x = i * width * 1.0f / 4;
			g2.drawString(s, x, height - 8);
		}
		drawLine(image);
		return image;
	}

	/**
	 * 绘制干扰线
	 * 
	 * @param image
	 */
	private static void drawLine(BufferedImage image) {
		Graphics2D g2 = (Graphics2D) image.getGraphics();
		int num = 2;
		for (int i = 0; i < num; i++) {
			int x1 = random.nextInt(width);
			int y1 = random.nextInt(height);
			int x2 = random.nextInt(width);
			int y2 = random.nextInt(height);
			g2.setColor(randomColor());
			g2.setStroke(new BasicStroke(1f));
			g2.drawLine(x1, y1, x2, y2);
		}
	}
	
	/**
	 * 输出图片
	 * 
	 * @param image
	 * @param out
	 * @throws IOException
	 */
	public static void output(BufferedImage image, OutputStream out) throws IOException {
		ImageIO.write(image, "JPEG", out);
	}
	
	/**
	 * 获取一个验证码
	 * 
	 * @param length
	 * @return
	 */
	public static String getCode(int length) {
		StringBuffer sb = new StringBuffer();
		for (int i = 0; i < 4; i++) {
			String s = randomChar() + "";
			sb.append(s);
		}
		return sb.toString();
	}
}
