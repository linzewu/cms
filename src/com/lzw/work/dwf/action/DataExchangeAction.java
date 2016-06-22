package com.lzw.work.dwf.action;

import java.awt.image.BufferedImage;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.io.Reader;
import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

import javax.annotation.PostConstruct;
import javax.annotation.Resource;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.apache.struts2.ServletActionContext;
import org.jbarcode.JBarcode;
import org.jbarcode.encode.Code39Encoder;
import org.jbarcode.encode.InvalidAtributeException;
import org.jbarcode.paint.BaseLineTextPainter;
import org.jbarcode.paint.WideRatioCodedPainter;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;

import com.google.zxing.BarcodeFormat;
import com.google.zxing.EncodeHintType;
import com.google.zxing.MultiFormatWriter;
import com.google.zxing.common.BitMatrix;
import com.lzw.work.cms.entity.DataReq;
import com.lzw.work.cms.entity.DataRes;
import com.lzw.work.cms.entity.PreCarRegister;
import com.lzw.work.cms.services.manager.DataExchangeManager;
import com.lzw.work.common.MatrixToImageWriter;
import com.lzw.work.common.OneBarcodeUtil;
import com.opensymphony.xwork2.ModelDriven;

import net.sf.json.JSONObject;

@Scope("prototype")
@Controller("exchangeAction")
public class DataExchangeAction implements ModelDriven<Object> {

	private static final String File = null;

	protected static Log log = LogFactory.getLog(DataExchangeAction.class);

	private DataReq dataReq;

	PrintWriter pw;

	JSONObject respondData;

	@Override
	public DataReq getModel() {
		dataReq = new DataReq();
		return dataReq;
	}

	@PostConstruct
	private void initAction() throws IOException {
		HttpServletResponse response = ServletActionContext.getResponse();
		response.setCharacterEncoding("UTF-8");
		pw = response.getWriter();
		respondData = new JSONObject();
	}

	@Resource(name = "dataExchangeManager")
	private DataExchangeManager dataExchangeManager;

	public void saveReq() throws IOException, InterruptedException {

		if (dataReq != null) {
			dataReq.setQueryCode(UUID.randomUUID().toString());
			createFile(dataReq);
		}

		File resFile = null;

		int count = 0;

		while (resFile == null) {
			Thread.sleep(2000);
			String queryCode = dataReq.getQueryCode();
			resFile = getFile(queryCode);
			if (count >= 9) {
				break;
			}
			count++;
		}

		if (resFile == null) {
			respondData.put("state", "500");
			respondData.put("message", "数据交换超时，请稍后再试！");
			pw.print(respondData);
		} else {

			PreCarRegister preCarRegister = getPreCarRegister(resFile);

			this.dataExchangeManager.register(preCarRegister);

			createCode(preCarRegister);
			respondData.put("state", "200");
			pw.print(respondData);
		}

		// DataReq req =
		// this.dataExchangeManager.queryReqByCode(dataReq.getReqMethod(),
		// dataReq.getQueryCode());
		// if("save".equals(dataReq.getMethodType())||req==null){
		// dataReq.setState(0);
		// dataReq.setCreateDate(new Date());
		// this.dataExchangeManager.saveDataReq(dataReq);
		// req=dataReq;
		// }

	}

	private PreCarRegister getPreCarRegister(File resFile) {
		String message = readFileByChars(resFile);

		PreCarRegister preCarRegister = (PreCarRegister) JSONObject.toBean(JSONObject.fromObject(message),
				PreCarRegister.class);
		return preCarRegister;
	}

	private File getFile(String queryCode) {

		File file = new File("D:\\Res", queryCode + ".res");

		if (file.exists()) {
			return file;
		} else {
			return null;
		}

	}

	public void queryRes() {
		DataRes res = this.dataExchangeManager.queryResByCode(dataReq.getMethodType(), dataReq.getQueryCode());
		if (res == null) {
			respondData.put("state", "400");
			pw.print(respondData);
			return;
		}
		if (res.getState() == 0) {
			if (res.getReqMethod().equals("ylrbc")) {
				String context = res.getResContext();
				PreCarRegister pcr = (PreCarRegister) JSONObject.toBean(JSONObject.fromObject(context),
						PreCarRegister.class);
				this.dataExchangeManager.register(pcr);
				createCode(pcr);
				res.setState(1);
				this.dataExchangeManager.updateRes(res);
			}
		}

		respondData.put("state", "200");
		respondData.put("data", res);
		pw.print(respondData);
	}

	private void createCode(PreCarRegister bcr) {

		Map userMap = (Map) ServletActionContext.getRequest().getSession().getAttribute("user");
		String stationCode = (String) userMap.get("StationCode");

		bcr.setStationCode(stationCode);

		StringBuilder sb = new StringBuilder("");

		sb.append(bcr.getClxh());
		sb.append("|");
		sb.append(bcr.getClsbdh());
		sb.append("|");
		sb.append(bcr.getHdzk());
		sb.append("|");
		sb.append(bcr.getCsys());
		sb.append("|");
		sb.append(bcr.getCllx());
		sb.append("|");
		sb.append(bcr.getHpzl());
		sb.append("|");
		sb.append(bcr.getYwlx());
		sb.append("|");
		sb.append(bcr.getGgbh());
		sb.append("|");
		sb.append(bcr.getSyxz());
		sb.append("|");
		sb.append(bcr.getFdjh());
		sb.append("|");
		sb.append(bcr.getQlj());
		sb.append("|");
		sb.append(bcr.getHlj());
		sb.append("|");
		sb.append(bcr.getZj());
		sb.append("|");
		sb.append(bcr.getLsh());
		sb.append("|");
		sb.append(bcr.getHphm());
		sb.append("|");

		if (null == bcr.getDpid() || "".equals(bcr.getDpid().trim())) {
			bcr.setDpid(null);
		}

		sb.append(bcr.getDpid());

		String path = System.getProperty("2code");
		create2Code(path, sb.toString(), bcr.getId());
		if ("A".equals(bcr.getYwlx())) {
			createLSHCode(bcr.getId(), bcr.getLsh());
		}
	}

	private void createLSHCode(String fileName, String lsh) {
		try {

			JBarcode localJBarcode = new JBarcode(Code39Encoder.getInstance(), WideRatioCodedPainter.getInstance(),
					BaseLineTextPainter.getInstance());

			localJBarcode.setShowCheckDigit(false);
			BufferedImage localBufferedImage = localJBarcode.createBarcode(lsh);
			OneBarcodeUtil.saveToJPEG(localBufferedImage, fileName + "code39.jpg");
		} catch (InvalidAtributeException e) {
			e.printStackTrace();
		}
	}

	private void create2Code(String path, String content, String fileName) {
		MultiFormatWriter multiFormatWriter = new MultiFormatWriter();
		Map hints = new HashMap();
		hints.put(EncodeHintType.CHARACTER_SET, "UTF-8");
		hints.put(EncodeHintType.MARGIN, 0);
		BitMatrix bitMatrix;
		try {
			bitMatrix = multiFormatWriter.encode(content, BarcodeFormat.QR_CODE, 200, 200, hints);
			File file1 = new File(path, fileName + ".jpg");
			MatrixToImageWriter.writeToFile(bitMatrix, "jpg", file1);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public void createFile(DataReq dataReq) throws IOException {
		String message = JSONObject.fromObject(dataReq).toString();
		File file1 = new File("D:\\Req", dataReq.getQueryCode() + ".raq");
		if (!file1.exists()) {
			file1.createNewFile();
		}
		byte[] bytes = message.getBytes();
		FileOutputStream fos = new FileOutputStream(file1);
		fos.write(bytes, 0, bytes.length);
		fos.close();
	}

	/**
	 * 以字符为单位读取文件，常用于读文本，数字等类型的文件
	 */
	public static String readFileByChars(File file) {

		StringBuffer sb = new StringBuffer();

		Reader reader = null;
		try {
			// 一次读多个字符
			char[] tempchars = new char[30];
			int charread = 0;
			reader = new InputStreamReader(new FileInputStream(file));
			while ((charread = reader.read(tempchars)) != -1) {
				// 同样屏蔽掉\r不显示
				if ((charread == tempchars.length) && (tempchars[tempchars.length - 1] != '\r')) {

					sb.append(tempchars);
				} else {
					for (int i = 0; i < charread; i++) {
						if (tempchars[i] == '\r') {
							continue;
						} else {
							sb.append(tempchars);
						}
					}
				}
			}

		} catch (Exception e1) {
			e1.printStackTrace();
		} finally {
			if (reader != null) {
				try {
					reader.close();
				} catch (IOException e1) {
					e1.printStackTrace();
				}
			}
		}
		return sb.toString();
	}

}
