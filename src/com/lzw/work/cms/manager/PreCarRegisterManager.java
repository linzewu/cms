package com.lzw.work.cms.manager;

import java.awt.image.BufferedImage;
import java.io.File;
import java.net.URLEncoder;
import java.rmi.RemoteException;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;
import javax.persistence.ParameterMode;
import javax.servlet.http.HttpServletRequest;

import org.apache.struts2.ServletActionContext;
import org.dom4j.Document;
import org.dom4j.DocumentException;
import org.dom4j.DocumentHelper;
import org.dom4j.Element;
import org.hibernate.Session;
import org.hibernate.criterion.DetachedCriteria;
import org.hibernate.criterion.Order;
import org.hibernate.criterion.Projections;
import org.hibernate.criterion.Restrictions;
import org.hibernate.procedure.ProcedureCall;
import org.jbarcode.JBarcode;
import org.jbarcode.encode.Code39Encoder;
import org.jbarcode.encode.InvalidAtributeException;
import org.jbarcode.paint.BaseLineTextPainter;
import org.jbarcode.paint.WideRatioCodedPainter;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Service;

import com.google.zxing.BarcodeFormat;
import com.google.zxing.EncodeHintType;
import com.google.zxing.MultiFormatWriter;
import com.google.zxing.common.BitMatrix;
import com.lzw.work.cms.entity.PreCarRegister;
import com.lzw.work.common.CommonUtil;
import com.lzw.work.common.MatrixToImageWriter;
import com.lzw.work.common.OneBarcodeUtil;
import com.lzw.work.common.URLCodeUtil;
import com.lzw.work.dwf.action.BaseManagerAction;
import com.lzw.work.dwf.entity.BaseEntity;
import com.lzw.work.multiple.manager.MultipleManagerAbstract;
import com.yc.anjian.newservice.client.TmriJaxRpcOutNewAccessServiceStub;

import net.sf.json.JSONObject;

@Scope("prototype")
@Service("preCarRegisterManager")
public class PreCarRegisterManager extends MultipleManagerAbstract {

	@Override
	public void setBean(BaseEntity bean) throws Exception {
		this.bean = bean;
	}

	@Resource(name = "trafficDBManager")
	private TrafficDBManager trafficDBManager;

	String jkxlh = "7F1C0909010517040815E3FF83F5F3E28BCC8F9B818DE7EA88DFD19EB8C7D894B9B9BCE0BFD8D6D0D0C4A3A8D0C5CFA2BCE0B9DCCFB5CDB3A3A9";

	public void updateRegister() {
		try {
			PreCarRegister bcr = (PreCarRegister) bean;
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
			// if(null==bcr.getHphm()||"".equals(bcr.getHphm().trim())){
			// bcr.setHphm(null);
			// }
			sb.append(bcr.getHphm());
			sb.append("|");
			if (null == bcr.getDpid() || "".equals(bcr.getDpid().trim())) {
				bcr.setDpid(null);
			}

			sb.append(bcr.getDpid());

			this.updateBaseEntity(bcr);
			System.out.println(sb.toString());

			String path = System.getProperty("2code");
			create2Code(path, sb.toString(), bcr.getId());
			respondData.put(BaseManagerAction.SID, bcr.getId());
			respondData.put(BaseManagerAction.STATE, BaseManagerAction.STATE_SUCCESS);
			pw.print(respondData);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public void saveRegister() {
		try {
			PreCarRegister bcr = (PreCarRegister) bean;

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

			String id = this.addBaseEntity(bcr);

			System.out.println(sb);
			String path = System.getProperty("2code");
			create2Code(path, sb.toString(), id);
			if ("A".equals(bcr.getYwlx())) {
				createLSHCode(id, lsh);
			}

			respondData.put(BaseManagerAction.SID, id);
			respondData.put(BaseManagerAction.STATE, BaseManagerAction.STATE_SUCCESS);
			pw.print(respondData);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	private void setQueryObjectOut(TmriJaxRpcOutNewAccessServiceStub.QueryObjectOut qo) {

		if (ServletActionContext.getRequest() != null) {
			Map userMap = (Map) ServletActionContext.getRequest().getSession().getAttribute("user");

			if (userMap != null) {
				String ip = getRemoteHost(ServletActionContext.getRequest());
				String idcard = (String) userMap.get("IDCard");
				String realName = (String) userMap.get("RealName");
				qo.setYhbz(idcard);
				qo.setYhxm(realName);
				qo.setZdbs(ip);
			} else {
				qo.setYhbz("");
				qo.setYhxm("");
				qo.setZdbs("10.39.147.6");
			}

		} else {
			qo.setYhbz("");
			qo.setYhxm("");
			qo.setZdbs("10.39.147.6");
		}
		qo.setDwjgdm("");
		qo.setDwmc("");

	}

	public String getRemoteHost(HttpServletRequest request) {
		String ip = request.getHeader("x-forwarded-for");
		if (ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)) {
			ip = request.getHeader("Proxy-Client-IP");
		}
		if (ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)) {
			ip = request.getHeader("WL-Proxy-Client-IP");
		}
		if (ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)) {
			ip = request.getRemoteAddr();
		}
		return ip.equals("0:0:0:0:0:0:0:1") ? "127.0.0.1" : ip;
	}

	private String getlsh() {
		String lsh = null;
		try {
			TmriJaxRpcOutNewAccessServiceStub trias = new TmriJaxRpcOutNewAccessServiceStub();
			TmriJaxRpcOutNewAccessServiceStub.QueryObjectOut qo = new TmriJaxRpcOutNewAccessServiceStub.QueryObjectOut();

			qo.setJkid("01C24");
			qo.setJkxlh(jkxlh);
			qo.setUTF8XmlDoc("<root><QueryCondition></QueryCondition></root>");
			qo.setXtlb("01");
			setQueryObjectOut(qo);

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

	public void checkPower() {
		HttpServletRequest request = ServletActionContext.getRequest();
		String hphm = request.getParameter("hphm");
		String sql = "select * from T_PoliceCheckYC where datediff(day,Time,getdate())<=60 and rst=2 and (power!=1 or power is null) and License=:License";
		Map map = new HashMap();
		map.put("License", hphm);
		List list = this.getBaseList(sql, map, null);
		pw.print(list.size());

	}

	public void getCarInfoByCarNumber() {
		try {
			TmriJaxRpcOutNewAccessServiceStub trias = new TmriJaxRpcOutNewAccessServiceStub();
			TmriJaxRpcOutNewAccessServiceStub.QueryObjectOut qo = new TmriJaxRpcOutNewAccessServiceStub.QueryObjectOut();

			HttpServletRequest request = ServletActionContext.getRequest();
			String hpzl = request.getParameter("hpzl");
			String hphm = request.getParameter("hphm");

			if (hpzl == null || "".equals(hpzl.trim()) || hphm == null || "".equals(hphm.trim())) {
				return;
			}

			qo.setJkid("01C21");
			qo.setJkxlh(jkxlh);
			qo.setUTF8XmlDoc(
					"<root><QueryCondition><hphm>" + hphm + "</hphm><hpzl>" + hpzl + "</hpzl></QueryCondition></root>");
			qo.setXtlb("01");
			setQueryObjectOut(qo);

			String returnXML = trias.queryObjectOut(qo).getQueryObjectOutReturn();
			String xml = URLCodeUtil.urlDecode(returnXML);
			Document doc = DocumentHelper.parseText(xml);
			Element root = doc.getRootElement();
			Element dataElecmet = root.element("body").element("veh");

			if (dataElecmet != null) {
				Map<String, String> dataMap = new HashMap<String, String>();
				for (Object o : dataElecmet.elements()) {
					Element element = (Element) o;
					String key = element.getName();
					String value = element.getText();
					dataMap.put(key, value);
				}
				pw.print(JSONObject.fromObject(dataMap));
			}

		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public void getInfoOfJCZ() {
		final String sql = "select * from tm_StationInfo";
		List<Object> list = this.getBaseList(sql, null, null);
		Map map = new HashMap();
		map.put("data", list);
		map.put("state", "200");
		JSONObject jo = JSONObject.fromObject(map);
		System.out.println(jo);
		pw.print(jo);
	}

	public void getCarInfoByCarNumberConvert() {
		try {
			TmriJaxRpcOutNewAccessServiceStub trias = new TmriJaxRpcOutNewAccessServiceStub();
			TmriJaxRpcOutNewAccessServiceStub.QueryObjectOut qo = new TmriJaxRpcOutNewAccessServiceStub.QueryObjectOut();

			HttpServletRequest request = ServletActionContext.getRequest();

			String hpzl = request.getParameter("hpzl");
			String hphm = request.getParameter("hphm");

			if (hpzl == null || "".equals(hpzl.trim()) || hphm == null || "".equals(hphm.trim())) {
				return;
			}

			qo.setJkid("01C21");
			qo.setJkxlh(jkxlh);
			qo.setUTF8XmlDoc(
					"<root><QueryCondition><hphm>" + hphm + "</hphm><hpzl>" + hpzl + "</hpzl></QueryCondition></root>");
			qo.setXtlb("01");
			setQueryObjectOut(qo);

			String returnXML = trias.queryObjectOut(qo).getQueryObjectOutReturn();
			String xml = URLCodeUtil.urlDecode(returnXML);
			Document doc = DocumentHelper.parseText(xml);
			Element root = doc.getRootElement();
			Element dataElecmet = root.element("body").element("veh");

			if (dataElecmet != null) {
				Map<String, String> dataMap = new HashMap<String, String>();
				for (Object o : dataElecmet.elements()) {
					Element element = (Element) o;
					String key = element.getName();
					String value = element.getText();

					dataMap.put(key, CommonUtil.convertCode(key, value));
				}
				String clxh = (String) dataMap.get("clxh");
				if (clxh != null) {
					String ggbh = trafficDBManager.getFirstGGBH(clxh);
					dataMap.put("ggbh", ggbh);
				}

				pw.print(JSONObject.fromObject(dataMap));
			}

		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public void getCarInfo2ByCarNumber() {
		try {
			TmriJaxRpcOutNewAccessServiceStub trias = new TmriJaxRpcOutNewAccessServiceStub();
			TmriJaxRpcOutNewAccessServiceStub.QueryObjectOut qo = new TmriJaxRpcOutNewAccessServiceStub.QueryObjectOut();

			HttpServletRequest request = ServletActionContext.getRequest();

			String hpzl = request.getParameter("hpzl");
			String hphm = request.getParameter("hphm");
			String sf = request.getParameter("sf");
			if (sf != null) {
				sf = URLEncoder.encode(sf, "UTF-8");
			}

			if ((hpzl == null) || ("".equals(hpzl.trim())) || (hphm == null) || ("".equals(hphm.trim()))) {
				return;
			}

			qo.setJkid("01C49");
			qo.setJkxlh(jkxlh);
			qo.setUTF8XmlDoc("<root><QueryCondition><hphm>" + hphm + "</hphm><hpzl>" + hpzl + "</hpzl><sf>" + sf
					+ "</sf></QueryCondition></root>");
			qo.setXtlb("01");
			
			setQueryObjectOut(qo);

			String returnXML = trias.queryObjectOut(qo).getQueryObjectOutReturn();
			String xml = URLCodeUtil.urlDecode(returnXML);
			Document doc = DocumentHelper.parseText(xml);
			Element root = doc.getRootElement();
			Element dataElecmet = root.element("body").element("veh");
			if (dataElecmet != null) {
				Map dataMap = new HashMap();
				for (Iterator localIterator = dataElecmet.elements().iterator(); localIterator.hasNext();) {
					Object o = localIterator.next();
					Element element = (Element) o;
					String key = element.getName();
					String value = element.getText();
					dataMap.put(key, CommonUtil.convertCode(key, value));
				}
				String clxh = (String) dataMap.get("clxh");
				if (clxh != null) {
					String ggbh = this.trafficDBManager.getFirstGGBH(clxh);
					dataMap.put("ggbh", ggbh);
				}
				this.pw.print(JSONObject.fromObject(dataMap));
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public void getSeq() {
		final String sql = "EXEC Sequences  'ycscgs',''";
		Session session = null;
		String code = "";
		try {
			session = this.getHibernateTemplate().getSessionFactory().openSession();
			ProcedureCall pc = session.createStoredProcedureCall("Sequences");
			pc.registerParameter("Table_Name", String.class, ParameterMode.IN).bindValue("ycscgs");
			pc.registerParameter("Seq_Type", String.class, ParameterMode.IN).bindValue("");
			pc.registerParameter("MaxId", String.class, ParameterMode.OUT);

			// System.out.println(pc.getOutputs().getCurrent());
			code = (String) pc.getOutputs().getOutputParameterValue("MaxId");
			System.out.println(code);
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			if (session != null) {
				session.clear();
				session.close();
			}
		}

		// List<Object> list = this.getBaseList(sql, null, null);
		// if(list==null||list.size()==0){
		// return;
		// }
		// String code =(String) ((Map)list.get(0)).get("MaxDicId");
		code = code.trim();
		String prm = "盐城市车管所专用防伪二维码|";
		String path = System.getProperty("temp2code");
		create2Code(path, prm + code, code);
		Map map = new HashMap();
		map.put("data", code);
		map.put("state", "200");
		JSONObject jo = JSONObject.fromObject(map);
		pw.print(jo);
	}

	/**
	 * 
	 */
	public void getCarList() {

		Map userMap = (Map) ServletActionContext.getRequest().getSession().getAttribute("user");

		String stationCode = (String) userMap.get("StationCode");

		String userName = (String) userMap.get("UserName");

		String isAdmin = (String) userMap.get("IsAdmin");

		String clsbdh = ServletActionContext.getRequest().getParameter("clsbdh");

		Integer page = Integer.parseInt(ServletActionContext.getRequest().getParameter("page"));

		Integer rows = Integer.parseInt(ServletActionContext.getRequest().getParameter("rows"));

		DetachedCriteria dc = DetachedCriteria.forClass(bean.getClass());

		if (!"1".equals(isAdmin)) {
			dc.add(Restrictions.eq("stationCode", stationCode));
		}

		if (clsbdh != null && !"".equals(clsbdh)) {
			dc.add(Restrictions.like("clsbdh", "%" + clsbdh));
		}

		int first = (page - 1) * rows;

		dc.setProjection(Projections.rowCount());
		Long count = (Long) this.getHibernateTemplate().findByCriteria(dc).get(0);

		dc.setProjection(null);

		dc.addOrder(Order.desc("createdDate"));
		List list = this.getHibernateTemplate().findByCriteria(dc, first, rows);

		Map map = new HashMap();

		map.put("rows", list);
		map.put("total", count.intValue());

		JSONObject jo = JSONObject.fromObject(map);
		pw.print(jo);
	}

}
