package com.lzw.work.cms.services.manager;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.apache.struts2.ServletActionContext;
import org.hibernate.SessionFactory;
import org.hibernate.criterion.DetachedCriteria;
import org.hibernate.criterion.Order;
import org.hibernate.criterion.Projections;
import org.hibernate.criterion.Restrictions;
import org.springframework.context.annotation.Scope;
import org.springframework.orm.hibernate4.support.HibernateDaoSupport;
import org.springframework.stereotype.Service;

import com.lzw.work.cms.entity.DataReq;
import com.lzw.work.cms.entity.DataRes;
import com.lzw.work.cms.entity.PreCarRegister;

@Scope("prototype")
@Service("dataExchangeManager")
public class DataExchangeManager extends HibernateDaoSupport {

	@Resource(name = "sessionFactory")
	public void setBaseSessionFactory(SessionFactory sessionFactory) {
		super.setSessionFactory(sessionFactory);
	}

	public void saveDataReq(DataReq data) {
		this.getHibernateTemplate().saveOrUpdate(data);
	}

	public DataReq queryReqByCode(String method, String queryCode) {

		List<DataReq> list = (List<DataReq>) this.getHibernateTemplate()
				.find("From DataReq where reqMethod=? and queryCode=? ", method, queryCode);

		if (list == null || list.isEmpty()) {
			return null;
		} else {
			return list.get(0);
		}
	}
	
	public DataRes queryResByCode(String method, String queryCode) {

		List<DataRes> list = (List<DataRes>) this.getHibernateTemplate()
				.find("From DataRes where reqMethod=? and queryCode=? ", method, queryCode);

		if (list == null || list.isEmpty()) {
			return null;
		} else {
			return list.get(0);
		}
	}
	
	public void register(PreCarRegister pcr){
		this.getHibernateTemplate().save(pcr);
	}
	
	public void updateRes(DataRes res){
		this.getHibernateTemplate().save(res);
	}
	
	
	public Map getCarList(){
		
		Map userMap = (Map) ServletActionContext.getRequest().getSession().getAttribute("user");

		String stationCode = (String) userMap.get("StationCode");

		String userName = (String) userMap.get("UserName");

		String isAdmin = (String) userMap.get("IsAdmin");

		String clsbdh = ServletActionContext.getRequest().getParameter("clsbdh");

		Integer page = Integer.parseInt(ServletActionContext.getRequest().getParameter("page"));

		Integer rows = Integer.parseInt(ServletActionContext.getRequest().getParameter("rows"));
		
		DetachedCriteria dc = DetachedCriteria.forClass(PreCarRegister.class);

		if (!"1".equals(isAdmin)) {
			dc.add(Restrictions.eq("stationCode", stationCode));
		}

		if (clsbdh != null && !"".equals(clsbdh)) {
			dc.add(Restrictions.like("clsbdh", "%" + clsbdh));
		}

		int first = (page - 1) * rows;

		dc.setProjection(Projections.rowCount());
		Long count = (Long) this.getHibernateTemplate().findByCriteria(dc).get(0);

		dc.setProjection(null);

		dc.addOrder(Order.desc("createdDate"));
		List list = this.getHibernateTemplate().findByCriteria(dc, first, rows);
		
		Map map = new HashMap();

		map.put("rows", list);
		map.put("total", count.intValue());
		
		return map;
		
		
	}
	

}
