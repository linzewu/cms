package com.lzw.work.dwf.manager;

import java.util.List;
import java.util.Map;

import org.hibernate.criterion.DetachedCriteria;

import com.lzw.work.dwf.entity.BaseEntity;

public interface IBaseManager {
	
	public static final String DESC="desc";
	
	public static final String ASC="ASC";
	
	public String addBaseEntity(BaseEntity object);
	
	public void updateBaseEntity(BaseEntity object);
	
	public void deleteBaseEntity(BaseEntity object);
	
	public Object getBaseEntity(Class<?> entityClass,String id);
	
	public List<?> getBaseEntityList(DetachedCriteria criteria,Map pageMap);
	
	public Long  getBaseEntityCount(DetachedCriteria criteria);
	
	public Object  getBaseUniqueResult(DetachedCriteria criteria);
	
	public List<Map> getBaseMapList(DetachedCriteria criteria);
	
}
