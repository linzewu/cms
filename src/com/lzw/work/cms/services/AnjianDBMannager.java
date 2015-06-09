package com.lzw.work.cms.services;

import java.util.List;

import javax.annotation.Resource;

import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.transform.ResultTransformer;
import org.hibernate.transform.Transformers;
import org.springframework.stereotype.Service;

import com.lzw.work.dwf.manager.BaseManagerImpl;

@Service("anjianDBMannager")
public class AnjianDBMannager extends BaseManagerImpl {
	
	@Resource(name = "sessionFactory")
	public void setBaseSessionFactory(SessionFactory sessionFactory) {
		super.setSessionFactory(sessionFactory);
	}
	
	public List getUserListBy(String stationCode){
		Session session =this.getHibernateTemplate().getSessionFactory().openSession();
		String sql="select * from t_user where username<>'admin' and [check]=1 and StationCode=:StationCode";
		try{
			Query query = session.createSQLQuery(sql);
			query.setParameter("StationCode", stationCode);
			query.setResultTransformer(Transformers.ALIAS_TO_ENTITY_MAP);
			return query.list();
		}finally{
			session.clear();
			session.close();
		}
	}
	
	public List getStationList(){
		Session session =this.getHibernateTemplate().getSessionFactory().openSession();
		 String sql="select * from tm_StationInfo";
			try{
				Query query = session.createSQLQuery(sql);
				query.setResultTransformer(Transformers.ALIAS_TO_ENTITY_MAP);
				return query.list();
			}finally{
				session.clear();
				session.close();
			}
	}
	
	public Integer userLogin(String userName,String password){
		 String sql="select count(*) from t_user where userName = :userName and password = :password";
		 Session session =this.getHibernateTemplate().getSessionFactory().openSession();
		 try{
				Query query = session.createSQLQuery(sql);
				query.setResultTransformer(Transformers.ALIAS_TO_ENTITY_MAP);
				Long c= (Long)query.uniqueResult();
				return c.intValue();
			}finally{
				session.clear();
				session.close();
			}
	}
	
	public List getTemplets(){
		 String sql="from DefaultItemTempletConfig";
		 Session session =this.getHibernateTemplate().getSessionFactory().openSession();
		 try{
				Query query = session.createQuery(sql);
				return query.list();
			}finally{
				session.clear();
				session.close();
			}
	}
	
	public Integer getMaxNumber(){
		
		String sql="select isnull(Max(Times),0)+1  from T_PoliceCheckYC b where (b.License=:licence) and (b.LicType=:licType) ";
		Session session =this.getHibernateTemplate().getSessionFactory().openSession();
		 try{
				Query query = session.createQuery(sql);
				Long number = (Long)query.uniqueResult();
				return number.intValue();
			}finally{
				session.clear();
				session.close();
			}
	}

}
