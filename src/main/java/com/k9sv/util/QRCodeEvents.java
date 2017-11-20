package com.k9sv.util;

import java.awt.image.BufferedImage;
import java.io.File;
import java.util.Hashtable;

import javax.imageio.ImageIO;

import com.google.zxing.BarcodeFormat;
import com.google.zxing.BinaryBitmap;
import com.google.zxing.DecodeHintType;
import com.google.zxing.EncodeHintType;
import com.google.zxing.LuminanceSource;
import com.google.zxing.MultiFormatReader;
import com.google.zxing.MultiFormatWriter;
import com.google.zxing.Result;
import com.google.zxing.client.j2se.BufferedImageLuminanceSource;
import com.google.zxing.client.j2se.MatrixToImageWriter;
import com.google.zxing.common.BitMatrix;
import com.google.zxing.common.HybridBinarizer;

public class QRCodeEvents {

	public static void main(String[] args) {
		String path = "D:/aaef93cc-df27-4b57-a795-a0db0a2d863c.png";
		// createPic(text, path);
		System.out.println(decode(path));
	}

	/**
	 * 创建二维码图片
	 * 
	 * @param text
	 * @param path
	 */
	@SuppressWarnings("deprecation")
	public static void createPic(String text, String path) {
		try {
			int width = 400;
			int height = 400;
			String format = "png";
			Hashtable<EncodeHintType, String> hints = new Hashtable<EncodeHintType, String>();
			hints.put(EncodeHintType.CHARACTER_SET, "utf-8");
			BitMatrix bitMatrix = new MultiFormatWriter().encode(text,
					BarcodeFormat.QR_CODE, width, height, hints);
			File outputFile = null;
			try {
				outputFile = prepareSavedDir(path);
			} catch (Exception e1) {
				e1.printStackTrace();
			}
			MatrixToImageWriter.writeToFile(bitMatrix, format, outputFile);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	private static File prepareSavedDir(String relativePath) throws Exception {
		File dir = new File(relativePath);
		if (!dir.exists()) {
			if (!dir.mkdirs()) {
				throw new Exception("创建保存目录失败");
			}
		}
		return dir;
	}

	/**
	 * 解析二维码
	 * 
	 * @param imgPath
	 * @return
	 */
	public static String decode(String imgPath) {

		try {
			BufferedImage image = ImageIO.read(new File(imgPath));
			if (image == null) {
				System.out.println("the decode image may be not exit.");
			}
			LuminanceSource source = new BufferedImageLuminanceSource(image);
			BinaryBitmap bitmap = new BinaryBitmap(new HybridBinarizer(source));

			Hashtable<DecodeHintType, String> hints = new Hashtable<DecodeHintType, String>();
			hints.put(DecodeHintType.CHARACTER_SET, "utf-8");
			Result result = new MultiFormatReader().decode(bitmap, hints);
			return result.getText();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}
}
