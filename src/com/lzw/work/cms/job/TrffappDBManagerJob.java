package com.lzw.work.cms.job;

import java.util.List;

import javax.annotation.Resource;

import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.transform.Transformers;
import org.springframework.orm.hibernate4.HibernateCallback;
import org.springframework.orm.hibernate4.support.HibernateDaoSupport;
import org.springframework.stereotype.Service;

@Service("trffappDBManagerJob")
public class TrffappDBManagerJob extends HibernateDaoSupport {
	
	@Resource(name = "trafficeSessionFactory")
	public void setBaseSessionFactory(SessionFactory sessionFactory) {
		super.setSessionFactory(sessionFactory);
	}

	protected List getGongGaoListbyCLXH(final String clxh) {

		return this.getHibernateTemplate().execute(new HibernateCallback<List>() {

			@Override
			public List doInHibernate(Session session) throws HibernateException {
				String sql = "SELECT PSV.BH,PSV.CLXH,TO_CHAR(PSV.GGRQ,'YYYY-MM-DD') ||' '|| PSV.CLXH  as GGRQ  FROM trff_app.PCB_ST_VEHICLE PSV"
						+ " WHERE PSV.CLXH LIKE :clxh order by PSV.GGRQ desc";

				return session.createSQLQuery(sql).setParameter("clxh", clxh)
						.setResultTransformer(Transformers.ALIAS_TO_ENTITY_MAP).list();
			}

		});

	}

}
