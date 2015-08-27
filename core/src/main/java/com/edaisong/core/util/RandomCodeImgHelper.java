package com.edaisong.core.util;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.image.BufferedImage;
import java.util.Random;

/**
 * 验证码helper
 * 
 * @author pengyi
 * @date 20150826
 *
 */
public class RandomCodeImgHelper {
	public static BufferedImage getCodeImg(String code) {
		Random random = new Random();
		// 在内存中创建图象
		int width = 106;
		int height = 43;
		int lineCount = 100;
		int codeCount = code.length();
		BufferedImage image = new BufferedImage(width, height, BufferedImage.TYPE_INT_RGB);
		int fontWidth = width / (codeCount + 2);
		int fontHeight = height - 10;
		// 创建图象
		Graphics g = image.getGraphics();
		// 设置背景色
		g.setColor(getRandColor(random,200, 250));
		g.fillRect(0, 0, width, height);
		// 设置字体
		g.setFont(new Font("Tines Nev Roman", Font.PLAIN, 35));
		// 随机产生干扰线
		g.setColor(getRandColor(random,160, 200));
		for (int i = 0; i < lineCount; i++) {
			int xs = random.nextInt(width);
			int ys = random.nextInt(height);
			int xe = xs + random.nextInt(width / 8);
			int ye = ys + random.nextInt(height / 8);
			int red = random.nextInt(255);
			int green = random.nextInt(255);
			int blue = random.nextInt(255);
			g.setColor(new Color(red, green, blue));
			g.drawLine(xs, ys, xe, ye);
		}
	    //随机产生认证码,4位数字  
	    for(int i = 0; i < codeCount; i++){  
	    	String rand = code.charAt(i)+"";
	        //将认证码显示到图象中  
	        g.setColor(new Color(20 + random.nextInt(110),20 + random.nextInt(110),20 + random.nextInt(110)));  
	        g.drawString(rand,(i + 1) * fontWidth,fontHeight);  
	    }  
	    //图像生效  
	    g.dispose();  
		return image;
	}

	private static Color getRandColor(Random random,int fc, int bc) {
		if (fc > 255) {
			fc = 255;
		}
		if (bc < 255) {
			bc = 255;
		}
		int r = fc + random.nextInt(bc - fc);
		int g = fc + random.nextInt(bc - fc);
		int b = fc + random.nextInt(bc - fc);
		return new Color(r, g, b);
	}
}
