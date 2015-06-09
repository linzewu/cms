package com.lzw.work.cms.services;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileReader;
import java.io.IOException;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

import net.sf.json.JSONArray;
import net.sf.json.JSONObject;

import org.apache.struts2.ServletActionContext;
import org.dom4j.Document;
import org.dom4j.DocumentHelper;
import org.dom4j.Element;
import org.springframework.stereotype.Service;

import com.lzw.work.cms.entity.Device;
import com.lzw.work.cms.entity.PoliceCheck;
import com.lzw.work.cms.services.manager.DeviceManager;
import com.lzw.work.cms.services.manager.TrffappDBManager;
import com.lzw.work.common.URLCodeUtil;
import com.yc.anjian.service.client.TmriJaxRpcOutAccessServiceStub;

@Service("PDAServices")
public class PDAServicesImpl implements IPDAServices {
	
	@Resource(name="anjianDBMannager")
	private AnjianDBMannager adm;
	
	@Resource(name="trffappDBManager")
	private TrffappDBManager  tdm;
	
	@Resource(name="deviceManager")
	private DeviceManager deviceManager;
	

	@Override
	public String getVersion() throws IOException {
		String tmp = System.getProperty("tomcat.dir");

		if (tmp == null) {
			return null;
		}
		File f = new File(tmp + "/pdafile/version.json");
		BufferedReader br = null;
		try {
			br = new BufferedReader(new FileReader(f));
			String tempString = null;
			StringBuilder sb = new StringBuilder();
			while ((tempString = br.readLine()) != null) {
				sb.append(tempString);
			}
			JSONObject jo = JSONObject.fromObject(sb.toString());
			return (String) jo.get("version");
		} catch (IOException e) {
			throw e;
		} finally {
			if (br != null) {
				br.close();
			}
		}
	}

	@Override
	public byte[] downloadAPP() throws Exception {

		String tmp = System.getProperty("tomcat.dir");
		if (tmp == null) {
			return null;
		}
		FileInputStream in = null;
		byte bytes[] = null;
		try {
			in = new FileInputStream(tmp + "/pdafile/motorvechicle.apk");
			bytes = new byte[in.available()];
			in.read(bytes);
		} catch (Exception e) {
			throw e;
		} finally {
			if (in != null) {
				try {
					in.close();
				} catch (IOException e) {
					throw e;
				}
			}
		}
		return bytes;
	}

	public String getUserList(String stationCode) {
		
		List list = adm.getUserListBy(stationCode);
		JSONArray ja = JSONArray.fromObject(list);
		
		return ja.toString();
	}

	@Override
	public String getStationList() {
		List list = adm.getStationList();
		if(list==null){
			return null;
		}
		return JSONArray.fromObject(list).toString();
		
	}

	@Override
	public String login(String userName, String password) {
		
		Integer c = adm.userLogin(userName, password);
		if(c==null||c==0){
			return "0";
		}else{
			return "1";
		}
	}

	@Override
	public String getCarInfo(String hphm, String hpzl) {
		try {
			TmriJaxRpcOutAccessServiceStub trias = new TmriJaxRpcOutAccessServiceStub();
			TmriJaxRpcOutAccessServiceStub.QueryObjectOut qo = new TmriJaxRpcOutAccessServiceStub.QueryObjectOut();

			HttpServletRequest request = ServletActionContext.getRequest();
			if (hpzl == null || "".equals(hpzl.trim()) || hphm == null
					|| "".equals(hphm.trim())) {
				return null;
			}
			qo.setJkid("01C21");
			qo.setJkxlh("7F1C0909010517040815E3FF83F5F3E28BCC8F9B818DE7EA88DFD19EB8C7D894B9B9BCE0BFD8D6D0D0C4A3A8D0C5CFA2BCE0B9DCCFB5CDB3A3A9");
			qo.setUTF8XmlDoc("<root><QueryCondition><hphm>" + hphm
					+ "</hphm><hpzl>" + hpzl
					+ "</hpzl></QueryCondition></root>");
			qo.setXtlb("01");

			String returnXML = trias.queryObjectOut(qo)
					.getQueryObjectOutReturn();
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
				return JSONObject.fromObject(dataMap).toString();
			}

		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}

	@Override
	public String getCheckTempletList() {
		
		List templets = adm.getTemplets();
		
		if(templets==null||templets.size()==0){
			return null;
		}
		
		return JSONArray.fromObject(templets).toString();
	}

	@Override
	public String getGongGaoList(String clxh) {
		List gonggaoList= tdm.getGonggaoListByClxh(clxh);
		
		if(gonggaoList==null||gonggaoList.size()==0){
			return null;
		}
		return JSONArray.fromObject(gonggaoList).toString();
		
	}

	@Override
	public String getGongGaoInfo(String bh) {
		Map map =  tdm.getGonggaoByBh(bh);
		if(map==null){
			return null;
		}
		return JSONObject.fromObject(map).toString();
	}

	@Override
	public String uploadCheckInfo(String bo) {
		
		if(bo==null){
			return "0";
		}
		JSONObject boObject = JSONObject.fromObject(bo);
		//获取图片集合
		JSONArray ja = boObject.getJSONArray("photos");
		boObject.remove("photos");
		PoliceCheck pc = (PoliceCheck) JSONObject.toBean(boObject,
				PoliceCheck.class);
		pc.setTime(new Date());
		
		pc.setTimes(adm.getMaxNumber());
		
		
		
		
		return null;
	}

	@Override
	public String getUnqualified() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public String getDPGGList(String dpid) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public String getDPGG(String bh) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public String saveDevice(String json) {
		Map rest=new HashMap();
		try{
			JSONObject jo = JSONObject.fromObject(json);
			Device device =(Device)JSONObject.toBean(jo, Device.class);
			this.deviceManager.saveDevice(device);
			rest.put("state", 200);
		}catch(Exception e){
			rest.put("state", 500);
			rest.put("message",e.getMessage());
		}
		JSONObject.fromObject(rest).toString();
		return JSONObject.fromObject(rest).toString();
	}
	

	@Override
	public String getDevices(String imei) {
		Map rest=new HashMap();
		
		try{
			Device device = this.deviceManager.getDevicebyImei(imei);
			JSONObject jo  = JSONObject.fromObject(device);
			rest.put("state", "200");
			rest.put("data", jo);
		}catch(Exception e){
			rest.put("state", "500");
			rest.put("message", e.getMessage());
		}
		
		return JSONObject.fromObject(rest).toString();
	}

}
