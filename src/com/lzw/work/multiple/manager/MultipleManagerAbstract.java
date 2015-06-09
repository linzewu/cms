package com.lzw.work.multiple.manager;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.Date;
import java.util.List;
import java.util.Map;

import javax.annotation.PostConstruct;
import javax.annotation.Resource;
import javax.servlet.http.HttpServletResponse;

import net.sf.json.JSONObject;

import org.apache.struts2.ServletActionContext;
import org.hibernate.HibernateException;
import org.hibernate.SQLQuery;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.transform.Transformers;
import org.springframework.orm.hibernate4.HibernateCallback;
import org.springframework.web.context.WebApplicationContext;
import org.springframework.web.context.support.WebApplicationContextUtils;

import com.lzw.work.dwf.entity.BaseEntity;
import com.lzw.work.dwf.manager.BaseManagerImpl;

public abstract class MultipleManagerAbstract extends BaseManagerImpl {
	
	@Resource(name = "sessionFactory")
	public void setBaseSessionFactory(SessionFactory sessionFactory) {
		super.setSessionFactory(sessionFactory);
	}
	
	//spring ioc
	protected WebApplicationContext wac = WebApplicationContextUtils
			.getWebApplicationContext(ServletActionContext.getServletContext());
	
	protected PrintWriter pw;
	
	protected JSONObject respondData;
	
	protected BaseEntity bean;
	
	@PostConstruct
	protected void initManager() throws IOException{
		HttpServletResponse response = ServletActionContext.getResponse();
		response.setCharacterEncoding("UTF-8");
		pw =response.getWriter();
		respondData = new JSONObject();
	}
	
	public abstract void setBean(BaseEntity bean) throws Exception;
	
	
	public List<Object> getBaseList(final String sql, final Map be, final Map pageMap) {
		
		return this.getHibernateTemplate().execute(new HibernateCallback<List<Object>>() {
			@Override
			public List<Object> doInHibernate(Session session)
					throws HibernateException {
				
				SQLQuery query = session.createSQLQuery(sql);
				
				if (pageMap != null) {
					Integer pageNumber = (Integer) pageMap
							.get("pageNumber");
					Integer pageSize = (Integer) pageMap
							.get("pageSize");
					if (pageNumber != null && pageSize != null) {
						query.setFirstResult((pageNumber - 1)* pageSize);
						query.setMaxResults(pageSize);
					}
				}
				String[] paramKeys = query.getNamedParameters();
				
				for(String key: paramKeys){
					query.setParameter(key,be.get(key));
				}
				
				query.setResultTransformer(
	                    Transformers.ALIAS_TO_ENTITY_MAP);
				return query.list();
			}
		
		});
	}
}
