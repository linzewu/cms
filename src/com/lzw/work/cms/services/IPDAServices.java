package com.lzw.work.cms.services;

import java.io.IOException;

public interface IPDAServices {
	
	public String getVersion() throws IOException;
	
	public byte[] downloadAPP() throws Exception;
	
	public String getUserList(String stationCode);
	
	public String getStationList();
	
	public String login(String userName,String password);
	
	public String getCarInfo(String hphm,String hpzl);
	
	public String getCheckTempletList();
	
	public String getGongGaoList(String cllx);
	
	public String getGongGaoInfo(String bh);
	
	public String uploadCheckInfo(String bo);
	
	public String getUnqualified();
	
	public String getDPGGList(String dpid);
	
	public String getDPGG(String bh);
	
	public String saveDevice(String json);
	
	public String getDevices(String imei);

}
