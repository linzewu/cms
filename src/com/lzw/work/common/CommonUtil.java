package com.lzw.work.common;

import java.util.List;
import java.util.Map;

import org.apache.struts2.ServletActionContext;

public class CommonUtil {

	public static String convertCode(String key, String value) {
		Map codeMap = (Map) ServletActionContext.getServletContext()
				.getAttribute("CardCodes");
		if (codeMap == null) {
			return value;
		}
		// System.out.println("key  "+codeMap);
		List<Map> codeList = (List) codeMap.get(key);

		if (codeList == null) {
			return value;
		}
		String[] vs = value.split(",");
		StringBuffer ss = new StringBuffer();
		for (String s : vs) {
			for (Map map : codeList) {
				if (map.get("DMZ").equals(s)) {
					ss.append((String) map.get("DMSM1"));
					ss.append(",");
				}
			}
		}
		if(ss.length()>0){
			return ss.substring(0,ss.length()-1);
		}else{
			return value;
		}
		
	}

}
