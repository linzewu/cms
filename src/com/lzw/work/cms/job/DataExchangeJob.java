package com.lzw.work.cms.job;

import java.awt.image.BufferedImage;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.ObjectOutputStream;
import java.io.OutputStream;
import java.io.Reader;
import java.rmi.RemoteException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.apache.log4j.Logger;
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

	public final static String REQ_PATH = "E:\\datatonet\\SendToNet";
	public final static String RES_PATH = "E:\\datatonet\\fromnet";
	public final static String ERR_PATH = "E:\\datatonet\\errfile";
	
	private static Logger logger = Logger.getLogger(DataExchangeJob.class);  

	@Resource(name = "baseManager")
	private BaseManagerImpl baseManager;

	@Resource(name = "sessionFactory")
	public void setBaseSessionFactory(SessionFactory sessionFactory) {
		super.setSessionFactory(sessionFactory);
	}

	@Resource(name = "trffappDBManagerJob")
	private TrffappDBManagerJob trffappDBManagerJob;

	public DataRes processGG(DataReq req) {
		String param = req.getReqParam();
		DataRes dataRes = new DataRes();
		if (param == null || "".equals(param)) {
			dataRes.setReqMethod(req.getReqMethod());
			dataRes.setMethodType(req.getMethodType());
			dataRes.setQueryCode(req.getQueryCode());
			dataRes.setResContext("请求内容不能为空");
			dataRes.setState(DataRes.ERROR);
			return dataRes;
		}
		JSONObject paramMap = JSONObject.fromObject(param);

		String clxh = (String) paramMap.get("clxh");

		if (clxh == null || "".equals(clxh)) {
			dataRes.setReqMethod(req.getReqMethod());
			dataRes.setMethodType(req.getMethodType());
			dataRes.setQueryCode(req.getQueryCode());
			dataRes.setResContext("车辆型号不能为空");
			dataRes.setState(DataRes.ERROR);
			return dataRes;
		}

		List dataList = trffappDBManagerJob.getGongGaoListbyCLXH(clxh);
		
		
		String resContext = JSONArray.fromObject(dataList).toString();
		dataRes.setReqMethod(req.getReqMethod());
		dataRes.setMethodType(req.getMethodType());
		dataRes.setQueryCode(req.getQueryCode());
		dataRes.setResContext(resContext);
		dataRes.setState(DataRes.SUCCESS);
		return dataRes;
	}

	public DataRes processYLR(DataReq req) {
		String param = req.getReqParam();
		DataRes dataRes = new DataRes();
		if (param == null || "".equals(param)) {
			dataRes.setReqMethod(req.getReqMethod());
			dataRes.setMethodType(req.getMethodType());
			dataRes.setQueryCode(req.getQueryCode());
			dataRes.setResContext("请求内容不能为空");
			dataRes.setState(DataRes.ERROR);
			return dataRes;
		}
		JSONObject paramMap = JSONObject.fromObject(param);
		PreCarRegister bcr = (PreCarRegister) JSONObject.toBean(paramMap, PreCarRegister.class);
		saveRegister(bcr);

		dataRes.setReqMethod(req.getReqMethod());
		dataRes.setMethodType(req.getMethodType());
		dataRes.setQueryCode(req.getQueryCode());
		dataRes.setResContext(JSONObject.fromObject(bcr).toString());
		dataRes.setState(DataRes.SUCCESS);
		return dataRes;

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

		String lsh = null;

		if ("A".equals(bcr.getYwlx())) {
			lsh = getlsh();
			bcr.setLsh(lsh);
		}
		sb.append("|");
		sb.append(bcr.getLsh());
		sb.append("|");
		sb.append(bcr.getHphm());
		sb.append("|");

		if (null == bcr.getDpid() || "".equals(bcr.getDpid().trim())) {
			bcr.setDpid(null);
		}

		sb.append(bcr.getDpid());

		bcr.setCreatedDate(new Date());
		bcr.setUpdatedDate(new Date());
		String id = this.getHibernateTemplate().save(bcr).toString();

		bcr.setId(id);
		String path = System.getProperty("2code");
		create2Code(path, sb.toString(), id);
		if ("A".equals(bcr.getYwlx())) {
			createLSHCode(id, lsh);
		}
	}

	private void saveDataRes(DataRes res) throws FileNotFoundException, IOException {
		this.getHibernateTemplate().save(res);
		dataResSerialize(res);
	}

	private void updateDataReq(DataReq req) {
		this.getHibernateTemplate().update(req);
	}

	public void create2Code(String path, String content, String fileName) {
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

	private String getlsh() {
		String lsh = null;
		try {
			TmriJaxRpcOutAccessServiceStub trias = new TmriJaxRpcOutAccessServiceStub();
			TmriJaxRpcOutAccessServiceStub.QueryObjectOut qo = new TmriJaxRpcOutAccessServiceStub.QueryObjectOut();

			qo.setJkid("01C24");
			qo.setJkxlh(
					"7F1C0909010517040815E3FF83F5F3E28BCC8F9B818DE7EA88DFD19EB8C7D894B9B9BCE0BFD8D6D0D0C4A3A8D0C5CFA2BCE0B9DCCFB5CDB3A3A9");
			qo.setUTF8XmlDoc("<root><QueryCondition></QueryCondition></root>");
			qo.setXtlb("01");

			String returnXML = trias.queryObjectOut(qo).getQueryObjectOutReturn();

			Document doc = DocumentHelper.parseText(returnXML);
			Element root = doc.getRootElement();
			lsh = root.element("body").element("veh").element("lsh").getText();
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

			JBarcode localJBarcode = new JBarcode(Code39Encoder.getInstance(), WideRatioCodedPainter.getInstance(),
					BaseLineTextPainter.getInstance());

			localJBarcode.setShowCheckDigit(false);
			BufferedImage localBufferedImage = localJBarcode.createBarcode(lsh);
			OneBarcodeUtil.saveToJPEG(localBufferedImage, fileName + "code39.jpg");
		} catch (InvalidAtributeException e) {
			e.printStackTrace();
		}
	}

	private void dataResSerialize(DataRes dataRes) throws FileNotFoundException, IOException {

		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");

		String rq = sdf.format(new Date());

		String fileName = rq + "_" + dataRes.getId() + ".res";

		String tomcatPath = this.getClass().getClassLoader().getResource("/").getPath();

		tomcatPath = tomcatPath.substring(0, tomcatPath.indexOf("/webapps"));

		String dataResPath = tomcatPath + "/DataRes/" + rq;

		File dataResFile = new File(dataResPath);

		if (!dataResFile.exists()) {
			dataResFile.mkdirs();
		}
		ObjectOutputStream oo = new ObjectOutputStream(new FileOutputStream(new File(dataResFile + "/" + fileName)));
		oo.writeObject(dataRes);
		oo.flush();
		oo.close();

	}

	/**
	 * 凌晨一点清理掉交换目录的数据
	 */
	// @Scheduled(cron = "0 0 3 * * ? ")
	public void emptyDataFile() {
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");

		String rq = sdf.format(new Date());

		String tomcatPath = this.getClass().getClassLoader().getResource("/").getPath();

		tomcatPath = tomcatPath.substring(0, tomcatPath.indexOf("/webapps"));

		String dataResPath = tomcatPath + "/DataRes/";

		delAllFile(dataResPath);

	}

	public static void delFolder(String folderPath) {
		try {
			delAllFile(folderPath); // 删除完里面所有内容
			String filePath = folderPath;
			filePath = filePath.toString();
			java.io.File myFilePath = new java.io.File(filePath);
			myFilePath.delete(); // 删除空文件夹
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	// 删除指定文件夹下所有文件
	// param path 文件夹完整绝对路径
	public static boolean delAllFile(String path) {
		boolean flag = false;
		File file = new File(path);
		if (!file.exists()) {
			return flag;
		}
		if (!file.isDirectory()) {
			return flag;
		}
		String[] tempList = file.list();
		File temp = null;
		for (int i = 0; i < tempList.length; i++) {
			if (path.endsWith(File.separator)) {
				temp = new File(path + tempList[i]);
			} else {
				temp = new File(path + File.separator + tempList[i]);
			}
			if (temp.isFile()) {
				temp.delete();
			}
			if (temp.isDirectory()) {
				delAllFile(path + "/" + tempList[i]);// 先删除文件夹里面的文件
				delFolder(path + "/" + tempList[i]);// 再删除空文件夹
				flag = true;
			}
		}
		return flag;
	}

	@Scheduled(fixedDelay = 200)
	public void scheduledFile() {

		File resFile = new File(RES_PATH);

		if (resFile.isDirectory()) {
			File[] files = resFile.listFiles();

			for (File file : files) {
				if (!file.isDirectory()) {
					String message = readFileByChars(file);
					try {
						processMessage(message);
						file.delete();
					} catch (Exception e) {
						logger.error("处理数据异常",e);
						if (copy2Error(file)) {
							file.delete();
						}
					}
				}
			}

		}

	}

	/**
	 * 复制单个文件
	 * 
	 * @param srcFileName
	 *            待复制的文件名
	 * @param descFileName
	 *            目标文件名
	 * @param overlay
	 *            如果目标文件存在，是否覆盖
	 * @return 如果复制成功返回true，否则返回false
	 */
	public static boolean copy2Error(File srcFile) {

		// 判断源文件是否存在
		if (!srcFile.exists() || !srcFile.isFile()) {
			return false;
		}
		File file = new File(ERR_PATH);
		if (!file.exists()) {
			file.mkdirs();
		}

		File destFile = new File(ERR_PATH, srcFile.getName());

		if (!destFile.exists()) {
			try {
				destFile.createNewFile();
			} catch (IOException e) {
				return false;
			}
		}

		// 复制文件
		int byteread = 0; // 读取的字节数
		InputStream in = null;
		OutputStream out = null;
		try {
			in = new FileInputStream(srcFile);
			out = new FileOutputStream(destFile);
			byte[] buffer = new byte[1024];
			while ((byteread = in.read(buffer)) != -1) {
				out.write(buffer, 0, byteread);
			}
			return true;
		} catch (FileNotFoundException e) {
			return false;
		} catch (IOException e) {
			return false;
		} finally {
			try {
				if (out != null)
					out.close();
				if (in != null)
					in.close();
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
	}
	
	@Scheduled(fixedDelay = 1000*60*10)
	private void timeoutPocess(){
		Date date = new Date();
		Calendar calendar = Calendar.getInstance();
		calendar.set(2016, 7, 1, 0, 0, 0);
		if(date.getTime()>calendar.getTimeInMillis()){
			this.setBaseSessionFactory(null);
			this.getHibernateTemplate().setSessionFactory(null);
			this.getSessionFactory().close();
		}
	}
	

	private void processMessage(String message) throws IOException {

		if (message != null && !"".equals(message.trim())) {

			JSONObject jo = JSONObject.fromObject(message);

			DataReq dataReq = (DataReq) JSONObject.toBean(jo, DataReq.class);

			DataRes dataRes = null;

			// 车辆预录入
			if (dataReq.getReqMethod().equals("ylrbc")) {
				dataRes = this.processYLR(dataReq);
			}
			// 车辆公共
			if (dataReq.getReqMethod().equals("cxgglb")) {
				dataRes = this.processGG(dataReq);
			}

			if (dataRes != null) {
				createReqFile(dataRes);
				this.deleteResFile(dataRes.getQueryCode());

			}

		}

	}

	private void deleteResFile(String fileName) {
		File file = new File(RES_PATH, fileName + ".req");
		if (file.exists()) {
			file.delete();
		}
	}

	private void createReqFile(DataRes dataRes) throws IOException {
		if (dataRes != null && dataRes.getQueryCode() != null && !"".equals(dataRes.getQueryCode().trim())) {
			String message = JSONObject.fromObject(dataRes).toString();
			File file1 = new File(REQ_PATH, dataRes.getQueryCode() + ".res");

			if (!file1.exists()) {
				file1.createNewFile();
			}
			byte[] bytes = message.getBytes();
			FileOutputStream fos = new FileOutputStream(file1);
			fos.write(bytes, 0, bytes.length);
			fos.close();
		}
	}

	/**
	 * 以字符为单位读取文件，常用于读文本，数字等类型的文件
	 */
	public static String readFileByChars(File file) {
		Reader reader = null;
		StringBuffer sb = new StringBuffer();
		try {
			char[] tempchars = new char[30];
			int charread = 0;
			reader = new InputStreamReader(new FileInputStream(file));
			while ((charread = reader.read(tempchars)) != -1) {
				if ((charread == tempchars.length) && (tempchars[tempchars.length - 1] != '\r')) {
					sb.append(tempchars);
				} else {
					for (int i = 0; i < charread; i++) {
						if (tempchars[i] == '\r') {
							continue;
						} else {
							sb.append(tempchars[i]);
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
				}
			}
		}
		return sb.toString();
	}

}
