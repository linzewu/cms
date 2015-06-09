package com.lzw.work.dwf.action;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.annotation.Resource;
import javax.servlet.http.HttpServletResponse;

import net.sf.json.JSONArray;
import net.sf.json.JSONObject;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.apache.struts2.ServletActionContext;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;
import org.springframework.web.context.WebApplicationContext;
import org.springframework.web.context.support.WebApplicationContextUtils;

import com.lzw.work.cms.entity.Device;
import com.lzw.work.cms.services.manager.DeviceManager;
import com.opensymphony.xwork2.ModelDriven;

@Scope("prototype")
@Controller("deviceAction")
public class DeviceAction extends BaseAction implements  ModelDriven<Object>{
	
	protected static Log log = LogFactory.getLog(BaseManagerAction.class);
	
	// spring ioc
	private WebApplicationContext wac = WebApplicationContextUtils
			.getWebApplicationContext(ServletActionContext.getServletContext());
	
	PrintWriter pw;

	JSONObject respondData;
	
	private Device device;
	
	@Resource(name="deviceManager")
	private DeviceManager deviceManager;
	
	@Override
	public Device getModel() {
		device=new Device();
		return device;
	}
	
	@PostConstruct
	private void initAction() throws IOException {
		HttpServletResponse response = ServletActionContext.getResponse();
		response.setCharacterEncoding("UTF-8");
		pw = response.getWriter();
		respondData = new JSONObject();
	}
	
	public void getDeviceByImei(){
		 try{
			Device d = deviceManager.getDevicebyImei(device.getImei());
			respondData.put("state","200"); 
			respondData.put("data", JSONObject.fromObject(d));
			pw.print(respondData);
		 }catch(Exception e){
			 respondData.put("state","500"); 
			 respondData.put("message",e.getMessage());
			 pw.print(respondData);
		 }
	}
	
	public void saveDevice(){
		try{
			this.deviceManager.saveDevice(device);
			respondData.put("state","200");
			pw.print(respondData);
		}catch(Exception e){
			respondData.put("state","500");
			respondData.put("message",e.getMessage());
			pw.print(respondData);
		}
	}
	
	public void getDevices(){
		try{
			int firstResult=(this.getPage()-1)*this.getRows();
			List<Device> devices = this.deviceManager.getDevices(device, firstResult, this.getRows());
			JSONArray ja = JSONArray.fromObject(devices);
			pw.print(ja);
		}catch(Exception e){
			respondData.put("state","500");
			pw.print(respondData);
		}
	}

}
