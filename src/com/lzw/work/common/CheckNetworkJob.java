package com.lzw.work.common;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.Properties;

import org.apache.commons.httpclient.HttpClient;
import org.apache.commons.httpclient.HttpException;
import org.apache.commons.httpclient.methods.GetMethod;
import org.apache.http.params.CoreConnectionPNames;
import org.apache.log4j.Logger;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

@Component("checkNetworkJob")
public class CheckNetworkJob {
	
	private static Logger logger = Logger.getLogger(CheckNetworkJob.class);
	
//	@Scheduled(fixedDelay = 5000)
	public void checkNetWork(){
		FileInputStream fileInputStream=null;
		FileOutputStream fos =null;
		String parh="D:\\apache-tomcat-6.0.26\\webapps\\network.properties";
		
		try{
			Properties p=new Properties();
			fileInputStream=new FileInputStream(parh);
			p.load(fileInputStream);
			fileInputStream.close();
			String cip = p.getProperty("cip");
			int code=senRequest(cip);
			if(code==200){
				logger.debug("当前ip："+cip);
				return ;
			}else{
				for(int i=1;i<=3;i++){
					String ip = p.getProperty("ip"+i);
					code = senRequest(ip);
					if(code==200){
						p.setProperty("cip", ip);
						fos=new FileOutputStream(parh);
						p.store(fos, "Update cip value");
						fos.flush();
						fos.close();
						return;
					}
				}
			}
		}catch(Exception e){
			logger.error("校验网络异常",e);
		}finally{
			if(fileInputStream!=null){
				try {
					fileInputStream.close();
				} catch (IOException e) {
					e.printStackTrace();
				}
			}
			if(fos!=null){
				try {
					fos.flush();
					fos.close();
				} catch (IOException e) {
					e.printStackTrace();
				}
			}
		}
	}
	
	private int senRequest(String ip){
		String url="http://"+ip+":9080/trffweb/services/TmriOutAccess?wsdl";
		HttpClient hc=new HttpClient();
		hc.getParams().setParameter(CoreConnectionPNames.CONNECTION_TIMEOUT, 1000); 
		GetMethod method=new GetMethod(url);
		int code=-100;
		try {
			hc.executeMethod(method);
		} catch (HttpException e) {
			logger.debug("ip "+ip+" code："+code);
			return code;
		} catch (IOException e) {
			logger.debug("ip "+ip+" code："+code);
			return code;
		}
		code=method.getStatusCode();
		logger.debug("ip "+ip+"："+code);
		return code;
	}

}
