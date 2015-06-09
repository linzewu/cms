package com.lzw.work.common;

import org.apache.struts2.ServletActionContext;

import net.sf.json.JSONArray;
import net.sf.json.JSONObject;

public class UnitUtil {
	
	public static String getUnitNameById(JSONArray units,String id){
		
		String text="";
		for(int i=0;i<units.size();i++){
			
			JSONObject temp = units.getJSONObject(i);
			
			
			if(temp.getString("id").equals(id)){
				 text=temp.getString("text");
				 break;
			}
			
			if(temp.get("children")!=null){
				text = getUnitNameById(temp.getJSONArray("children"),id);
			}
		}
		return text;
	}
	
	public static String getUnitNameById(String id){
		
		
		String strJa = ServletActionContext.getServletContext().getAttribute("units").toString();
		
		JSONArray units=JSONArray.fromObject(strJa);
		
		String text="";
		for(int i=0;i<units.size();i++){
			
			JSONObject temp = units.getJSONObject(i);
			
			
			if(temp.getString("id").equals(id)){
				 text=temp.getString("text");
				 break;
			}
			
			if(temp.get("children")!=null){
				text = getUnitNameById(temp.getJSONArray("children"),id);
			}
		}
		return text;
	}


}
