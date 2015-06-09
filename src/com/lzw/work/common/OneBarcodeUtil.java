package com.lzw.work.common;

import java.awt.image.BufferedImage;
import java.io.FileOutputStream;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.jbarcode.JBarcode;
import org.jbarcode.encode.Code39Encoder;
import org.jbarcode.encode.EAN13Encoder;
import org.jbarcode.encode.InvalidAtributeException;
import org.jbarcode.paint.BaseLineTextPainter;
import org.jbarcode.paint.EAN13TextPainter;
import org.jbarcode.paint.WideRatioCodedPainter;
import org.jbarcode.paint.WidthCodedPainter;
import org.jbarcode.util.ImageUtil;

import com.lzw.work.dwf.action.BaseManagerAction;

public class OneBarcodeUtil {
	public static void main(String[] paramArrayOfString) {
		try {
			JBarcode localJBarcode = new JBarcode(EAN13Encoder.getInstance(),
					WidthCodedPainter.getInstance(),
					EAN13TextPainter.getInstance());
			// 生成. 欧洲商品条码(=European Article Number)
			// 这里我们用作图书条码
			String str = "788515004012";
			BufferedImage localBufferedImage = localJBarcode.createBarcode(str);
			saveToGIF(localBufferedImage, "EAN13.gif");

			localJBarcode.setEncoder(Code39Encoder.getInstance());
			localJBarcode.setPainter(WideRatioCodedPainter.getInstance());
			localJBarcode.setTextPainter(BaseLineTextPainter.getInstance());
			localJBarcode.setShowCheckDigit(false);
			// xx
//			str = "1BCF06B4-52A2-417F-A87A-FA1D9A522A35";
			System.out.println(System.currentTimeMillis());
			localBufferedImage = localJBarcode.createBarcode((String.valueOf(System.currentTimeMillis())));
			saveToPNG(localBufferedImage, "Code39.png");

		} catch (Exception localException) {
			localException.printStackTrace();
		}
	}
	
	protected static Log log = LogFactory.getLog(OneBarcodeUtil.class);
	
	public static void createBarcodeOfEAN13(String code) throws InvalidAtributeException {
		JBarcode localJBarcode = new JBarcode(EAN13Encoder.getInstance(),
				WidthCodedPainter.getInstance(),
				EAN13TextPainter.getInstance());
		BufferedImage localBufferedImage = localJBarcode.createBarcode(code);
		saveToGIF(localBufferedImage, code+".gif");
	}

	public static void createBarcodeOfCode39(String code) throws InvalidAtributeException {
		JBarcode localJBarcode = new JBarcode(Code39Encoder.getInstance(),
				WideRatioCodedPainter.getInstance(), BaseLineTextPainter.getInstance());
		localJBarcode.setShowCheckDigit(false);
		BufferedImage localBufferedImage = localJBarcode.createBarcode(code);
		saveToPNG(localBufferedImage, code+".png");
	}

	public static void saveToJPEG(BufferedImage paramBufferedImage, String paramString) {
		saveToFile(paramBufferedImage, paramString, "jpeg");
	}

	public static void saveToPNG(BufferedImage paramBufferedImage, String paramString) {
		saveToFile(paramBufferedImage, paramString, "png");
	}

	public static void saveToGIF(BufferedImage paramBufferedImage, String paramString) {
		saveToFile(paramBufferedImage, paramString, "gif");
	}

	public static void saveToFile(BufferedImage paramBufferedImage,
			String paramString1, String paramString2) {
		
		String barcodePaht =System.getProperty("2code");
		try {
			FileOutputStream localFileOutputStream = new FileOutputStream(barcodePaht+"/"+paramString1);
			ImageUtil.encodeAndWrite(paramBufferedImage, paramString2,
					localFileOutputStream, 96, 96);
			localFileOutputStream.close();
		} catch (Exception localException) {
			log.error("创建条形码文件错误",localException);
		}
	}
}
