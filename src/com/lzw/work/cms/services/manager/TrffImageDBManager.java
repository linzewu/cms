package com.lzw.work.cms.services.manager;

import java.util.List;

import javax.annotation.Resource;

import net.sf.ehcache.hibernate.HibernateUtil;

import org.hibernate.Hibernate;
import org.hibernate.SQLQuery;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.jpa.HibernateQuery;
import org.hibernate.metamodel.binding.HibernateTypeDescriptor;
import org.hibernate.type.BlobType;
import org.springframework.stereotype.Service;

import com.lzw.work.dwf.manager.BaseManagerImpl;

@Service("trffImageDBManager")
public class TrffImageDBManager extends BaseManagerImpl {
	
	@Resource(name = "sessionFactory")
	public void setBaseSessionFactory(SessionFactory sessionFactory) {
		super.setSessionFactory(sessionFactory);
	}
	
	
	public List getGongGaoImageList(String[] zpbh){
	 	String sql = "SELECT ZP  FROM trff_zjk.PCB_ST_PHOTO WHERE ZPBH IN (:zpbh)";
	 	
		Session session = this.getHibernateTemplate().getSessionFactory().openSession();
		try{
			SQLQuery  query = session.createSQLQuery(sql);
			query.addScalar("ZP",BlobType.INSTANCE);
			query.setParameterList("zpbh", zpbh);
			return query.list();
		}finally{
			session.clear();
			session.close();
		}
		
	}
	


}
