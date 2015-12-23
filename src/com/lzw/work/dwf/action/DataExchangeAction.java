package com.lzw.work.dwf.action;

import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

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
public class DataExchangeAction implements  ModelDriven<Object>{
	
	protected static Log log = LogFactory.getLog(DataExchangeAction.class);
	
	private DataReq dataReq;
	
	PrintWriter pw;

	JSONObject respondData;
	
	@Override
	public DataReq getModel() {
		dataReq =new DataReq();
		return dataReq;
	}
	
	@PostConstruct
	private void initAction() throws IOException {
		HttpServletResponse response = ServletActionContext.getResponse();
		response.setCharacterEncoding("UTF-8");
		pw = response.getWriter();
		respondData = new JSONObject();
	}
	
	@Resource(name="dataExchangeManager")
	private DataExchangeManager dataExchangeManager;
	
	

	
	public void saveReq(){
		DataReq req = this.dataExchangeManager.queryReqByCode(dataReq.getReqMethod(), dataReq.getQueryCode());
		
		if("save".equals(dataReq.getMethodType())||req==null){
			dataReq.setState(0);
			dataReq.setCreateDate(new Date());
			this.dataExchangeManager.saveDataReq(dataReq);
			req=dataReq;
		}
		respondData.put("state","200");
		respondData.put("data", req);
		pw.print(respondData);
	}
	
	
	public void queryRes(){
		DataRes res = this.dataExchangeManager.queryResByCode(dataReq.getMethodType(), dataReq.getQueryCode());
		if(res==null){
			respondData.put("state","400");
			pw.print(respondData);
			return;
		}
		if(res.getState()==0){
			if(res.getReqMethod().equals("ylrbc")){
				String context = res.getResContext();
				PreCarRegister pcr =(PreCarRegister)JSONObject.toBean(JSONObject.fromObject(context), PreCarRegister.class);
				this.dataExchangeManager.register(pcr);
				createCode(pcr);
				res.setState(1);
				this.dataExchangeManager.updateRes(res);
			}
		}
		
		respondData.put("state","200");
		respondData.put("data", res);
		pw.print(respondData);
	}
	
	private void createCode(PreCarRegister bcr){
		
		Map userMap = (Map)ServletActionContext.getRequest().getSession().getAttribute("user");
		String stationCode=(String)userMap.get("StationCode");
		
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
		
		if(null==bcr.getDpid()||"".equals(bcr.getDpid().trim())){
			bcr.setDpid(null);
		}
		
		sb.append(bcr.getDpid());
		
		String path = System.getProperty("2code");
		create2Code(path,sb.toString(), bcr.getId());
		if("A".equals(bcr.getYwlx())){
			createLSHCode(bcr.getId(),bcr.getLsh());
		}
	}
	
	private void createLSHCode(String fileName, String lsh) {
		try {

			JBarcode localJBarcode = new JBarcode(Code39Encoder.getInstance(),
					WideRatioCodedPainter.getInstance(),
					BaseLineTextPainter.getInstance());

			localJBarcode.setShowCheckDigit(false);
			BufferedImage localBufferedImage = localJBarcode.createBarcode(lsh);
			OneBarcodeUtil.saveToJPEG(localBufferedImage, fileName
					+ "code39.jpg");
		} catch (InvalidAtributeException e) {
			e.printStackTrace();
		}
	}

	private void create2Code(String path,String content, String fileName) {
		MultiFormatWriter multiFormatWriter = new MultiFormatWriter();

		Map hints = new HashMap();
		hints.put(EncodeHintType.CHARACTER_SET, "UTF-8");
		hints.put(EncodeHintType.MARGIN, 0);
		BitMatrix bitMatrix;
		try {
			bitMatrix = multiFormatWriter.encode(content,
					BarcodeFormat.QR_CODE, 200, 200, hints);
			File file1 = new File(path, fileName + ".jpg");
			MatrixToImageWriter.writeToFile(bitMatrix, "jpg", file1);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}
