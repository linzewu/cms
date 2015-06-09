package com.lzw.work.dwf.criteria;

import org.hibernate.criterion.DetachedCriteria;

public abstract class CriteriaAbstract {
	
	public abstract DetachedCriteria getCriteria(Object bean);

}
