package com.lzw.work.cms.job;

import java.awt.image.BufferedImage;
import java.io.File;
import java.rmi.RemoteException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.apache.struts2.ServletActionContext;
import org.dom4j.Document;
import org.dom4j.DocumentException;
import org.dom4j.DocumentHelper;
import org.dom4j.Element;
import org.hibernate.SessionFactory;
import org.jbarcode.JBarcode;
import org.jbarcode.encode.Code39Encoder;
import org.jbarcode.encode.InvalidAtributeException;
import org.jbarcode.paint.BaseLineTextPainter;
import org.jbarcode.paint.WideRatioCodedPainter;
import org.springframework.orm.hibernate4.support.HibernateDaoSupport;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import com.google.zxing.BarcodeFormat;
import com.google.zxing.EncodeHintType;
import com.google.zxing.MultiFormatWriter;
import com.google.zxing.common.BitMatrix;
import com.lzw.work.cms.entity.DataReq;
import com.lzw.work.cms.entity.DataRes;
import com.lzw.work.cms.entity.PreCarRegister;
import com.lzw.work.common.MatrixToImageWriter;
import com.lzw.work.common.OneBarcodeUtil;
import com.lzw.work.dwf.manager.BaseManagerImpl;
import com.yc.anjian.service.client.TmriJaxRpcOutAccessServiceStub;

import net.sf.json.JSONArray;
import net.sf.json.JSONObject;

@Component("dataExchangeJob")
public class DataExchangeJob extends HibernateDaoSupport {
	
	@Resource(name = "baseManager")
	private BaseManagerImpl baseManager;

	@Resource(name = "sessionFactory")
	public void setBaseSessionFactory(SessionFactory sessionFactory) {
		super.setSessionFactory(sessionFactory);
	}
	
	@Resource(name="trffappDBManagerJob")
	private TrffappDBManagerJob trffappDBManagerJob;
	
	

	@Scheduled(fixedDelay = 1000)
	public void scanGG() {
		
		List<DataReq> datas =  getDataReqList("query","cxgglb",0);
		
		for(DataReq req : datas){
			
			String param = req.getReqParam();
			
			if(param==null||"".equals(param)){
				return;
			}
			
			JSONObject paramMap=JSONObject.fromObject(param);
			
			String clxh=(String)paramMap.get("clxh");
			
			if(clxh==null||"".equals(clxh)){
				return ;
			}
			
			List dataList  = trffappDBManagerJob.getGongGaoListbyCLXH(clxh);
			String resContext =  JSONArray.fromObject(dataList).toString();
			DataRes dataRes =new DataRes();
			
			dataRes.setReqMethod(req.getReqMethod());
			dataRes.setMethodType(req.getMethodType());
			dataRes.setQueryCode(req.getQueryCode());
			dataRes.setResContext(resContext);
			dataRes.setState(0);
			saveDataRes(dataRes);
			
			req.setState(1);
			updateDataReq(req);
			
			
		}
	}
	
	
	@Scheduled(fixedDelay = 1000)
	public void scanYLR() {
		
		List<DataReq> datas =  getDataReqList("save","ylrbc",0);
		
		for(DataReq req : datas){
			
			String param = req.getReqParam();
			
			if(param==null||"".equals(param)){
				return;
			}
			
			JSONObject paramMap=JSONObject.fromObject(param);
			
			PreCarRegister bcr = (PreCarRegister)JSONObject.toBean(paramMap, PreCarRegister.class);
			saveRegister(bcr);
			
			
			
			DataRes dataRes =new DataRes();
			
			dataRes.setReqMethod(req.getReqMethod());
			dataRes.setMethodType(req.getMethodType());
			dataRes.setQueryCode(req.getQueryCode());
			dataRes.setResContext(JSONObject.fromObject(bcr).toString());
			dataRes.setState(0);
			saveDataRes(dataRes);
			
			req.setState(1);
			
			updateDataReq(req);
			
		}
	}

	

	private List<DataReq> getDataReqList(String methodType, String reqMethod, int state) {
		List<DataReq> datas = (List<DataReq>) this.getHibernateTemplate()
				.find("From DataReq where methodType=? and reqMethod=? and state =? ", methodType, reqMethod, state);

		return datas;
	}
	
	
	
	
	
	private void saveRegister(PreCarRegister bcr) {
		
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
		
		String lsh=null;
		
		if("A".equals(bcr.getYwlx())){
			lsh= getlsh();
			bcr.setLsh(lsh);
		}
		sb.append("|");
		sb.append(bcr.getLsh());
		sb.append("|");
		sb.append(bcr.getHphm());
		sb.append("|");
		
		if(null==bcr.getDpid()||"".equals(bcr.getDpid().trim())){
			bcr.setDpid(null);
		}
		
		sb.append(bcr.getDpid());
		
		String id = this.baseManager.addBaseEntity(bcr);
		bcr.setId(id);
		String path = System.getProperty("2code");
		create2Code(path,sb.toString(), id);
		if("A".equals(bcr.getYwlx())){
			createLSHCode(id,lsh);
		}
	}

	
	
	private void saveDataRes(DataRes res){
		this.getHibernateTemplate().save(res);
	}
	
	private void updateDataReq(DataReq req){
		this.getHibernateTemplate().update(req);
	}
	
	public void create2Code(String path,String content, String fileName) {
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
	
	private String getlsh() {
		String lsh =null;
		try {
			TmriJaxRpcOutAccessServiceStub trias = new TmriJaxRpcOutAccessServiceStub();
			TmriJaxRpcOutAccessServiceStub.QueryObjectOut qo = new TmriJaxRpcOutAccessServiceStub.QueryObjectOut();

			qo.setJkid("01C24");
			qo.setJkxlh("7F1C0909010517040815E3FF83F5F3E28BCC8F9B818DE7EA88DFD19EB8C7D894B9B9BCE0BFD8D6D0D0C4A3A8D0C5CFA2BCE0B9DCCFB5CDB3A3A9");
			qo.setUTF8XmlDoc("<root><QueryCondition></QueryCondition></root>");
			qo.setXtlb("01");

			String returnXML = trias.queryObjectOut(qo)
					.getQueryObjectOutReturn();

			Document doc = DocumentHelper.parseText(returnXML);
			Element root = doc.getRootElement();
			lsh= root.element("body").element("veh").element("lsh")
					.getText();
		} catch (RemoteException e) {
			e.printStackTrace();
		} catch (DocumentException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return lsh;
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

}
