package com.lzw.work.dwf.manager;

import java.util.Date;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.apache.struts2.ServletActionContext;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.criterion.DetachedCriteria;
import org.hibernate.criterion.Order;
import org.hibernate.criterion.Projections;
import org.springframework.orm.hibernate4.HibernateCallback;
import org.springframework.orm.hibernate4.support.HibernateDaoSupport;
import org.springframework.stereotype.Service;

import com.lzw.work.dwf.entity.BaseEntity;

@Service("baseManager")
public class BaseManagerImpl extends HibernateDaoSupport implements
		IBaseManager {

	@Resource(name = "sessionFactory")
	public void setBaseSessionFactory(SessionFactory sessionFactory) {
		super.setSessionFactory(sessionFactory);
	}

	@Override
	public String addBaseEntity(BaseEntity object) {
		
		object.setUpdatedDate(new Date());
		object.setCreatedDate(new Date());
		object.setCreatedUser(getUserName());
		object.setUpdatedUser(getUserName());
		
		return this.getHibernateTemplate().save(object).toString();
	}

	@Override
	public void updateBaseEntity(BaseEntity object) {
		object.setUpdatedDate(new Date());
		object.setUpdatedUser(getUserName());
		this.getHibernateTemplate().update(object);
	}

	@Override
	public void deleteBaseEntity(BaseEntity object) {
		this.getHibernateTemplate().delete(object);
	}

	@Override
	public Object getBaseEntity(Class<?> entityClass, String id) {
		return this.getHibernateTemplate().get(entityClass, id);
	}

	@Override
	public List<?> getBaseEntityList(DetachedCriteria criteria, Map pageMap) {

		if (pageMap != null) {
			String orders = (String) pageMap.get("orders");
			if (orders != null) {
				String[] os = orders.split(",");
				for (String strOrder : os) {
					String[] array = strOrder.split("|");
					if (array.length == 2) {
						if (DESC.equalsIgnoreCase(array[1])) {
							criteria.addOrder(Order.desc(array[0]));
						}
						if (ASC.equalsIgnoreCase(array[1])) {
							criteria.addOrder(Order.asc(array[0]));
						}
					}
				}
			}else{
				criteria.addOrder(Order.desc("createdDate"));
			}
			Integer pageNumber = (Integer) pageMap.get("pageNumber");
			Integer pageSize = (Integer) pageMap.get("pageSize");
			
			if (pageNumber != null && pageSize != null) {
				return this.getHibernateTemplate().findByCriteria(criteria,
						(pageNumber - 1) * pageSize, pageSize);
			}
		}
		return this.getHibernateTemplate().findByCriteria(criteria);
	}

	@Override
	public Long getBaseEntityCount(final DetachedCriteria criteria) {
		
		Session  session = this.getSessionFactory().openSession();
		criteria.setProjection(Projections.rowCount());
		Long tatol = (Long) criteria.getExecutableCriteria(session)
			.uniqueResult();
		 session.close();
		return tatol;
	}

	@Override
	public Object getBaseUniqueResult(final DetachedCriteria criteria) {

		return this.getHibernateTemplate().execute(
				new HibernateCallback<Object>() {
					@Override
					public Object doInHibernate(Session session) {
						return criteria.getExecutableCriteria(session)
								.uniqueResult();
					}
				});
	}

	@Override
	public List<Map> getBaseMapList(DetachedCriteria criteria) {
		criteria.setResultTransformer(DetachedCriteria.ALIAS_TO_ENTITY_MAP);
		List<Map> baseList = (List<Map>) this.getHibernateTemplate()
				.findByCriteria(criteria);
		return baseList;
	}
	
	public String getUserName() {

		Map map = (Map) ServletActionContext.getRequest().getSession()
				.getAttribute("user");

		if (map != null) {
			return (String) map.get("UserName");
		}

		return null;
	}
	
}
