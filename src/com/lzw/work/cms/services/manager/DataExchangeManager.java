package com.lzw.work.cms.services.manager;

import java.util.List;

import javax.annotation.Resource;

import org.hibernate.SessionFactory;
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

}
