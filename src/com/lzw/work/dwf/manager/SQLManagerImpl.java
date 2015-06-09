package com.lzw.work.dwf.manager;

import java.math.BigInteger;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.hibernate.Query;
import org.hibernate.SQLQuery;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.transform.Transformers;
import org.springframework.orm.hibernate4.HibernateCallback;
import org.springframework.orm.hibernate4.support.HibernateDaoSupport;
import org.springframework.stereotype.Repository;

@Repository("sqlManager")
public class SQLManagerImpl extends HibernateDaoSupport implements ISQLManager {
	
	@Resource(name = "sessionFactory")
	public void setBaseSessionFactory(SessionFactory sessionFactory) {
		super.setSessionFactory(sessionFactory);
	}

	@Override
	public List<Object> getBaseList(final String sql, final Map be, final Map pageMap) {
		return this.getHibernateTemplate().execute(new HibernateCallback<List<Object>>() {
			@Override
			public List<Object> doInHibernate(Session session){
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

	@Override
	public Integer getBaseCount(final String sql, final Map be) {
		return this.getHibernateTemplate().execute(new HibernateCallback<Integer>() {
			@Override
			public Integer doInHibernate(Session session){
				
				Query query = session.createSQLQuery(sql);
				String[] paramKeys = query.getNamedParameters();
				for(String key: paramKeys){
					query.setParameter(key,be.get(key));
				}
				BigInteger count = (BigInteger)query.uniqueResult();
				
				if(count!=null){
					return count.intValue();
				}
				return null;
			}
		});
	}

	@Override
	public Object getUniqueResult(final String sql, final Map be) {
		return this.getHibernateTemplate().execute(new HibernateCallback<Object>() {
			@Override
			public Object doInHibernate(Session session){
				Query query = session.createSQLQuery(sql);
				String[] paramKeys = query.getNamedParameters();
				for(String key: paramKeys){
					query.setParameter(key,be.get(key));
				}
				Object o = (Object)query.uniqueResult();
				return o;
			}
		});
	}

}
