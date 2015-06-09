package com.lzw.work.dwf.manager;

import java.util.List;
import java.util.Map;

public interface ISQLManager {
	
	public List<Object> getBaseList(String sql,Map be,Map pageMap);
	
	public Integer getBaseCount(String sql,Map be);
	
	public Object  getUniqueResult(String sql,Map be);

}
