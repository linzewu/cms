package com.lzw.work.cms.services.manager;

import java.io.BufferedInputStream;
import java.sql.Blob;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.transform.Transformers;
import org.hibernate.type.BlobType;
import org.springframework.stereotype.Service;

import sun.misc.BASE64Decoder;
import sun.misc.BASE64Encoder;

import com.lzw.work.dwf.manager.BaseManagerImpl;

@Service("trffappDBManager")
public class TrffappDBManager extends BaseManagerImpl {
	
	@Resource(name="trffImageDBManager")
	public TrffImageDBManager tidm;
	
	@Resource(name = "sessionFactory")
	public void setBaseSessionFactory(SessionFactory sessionFactory) {
		super.setSessionFactory(sessionFactory);
	}
	
	public List   getGonggaoListByClxh(String clxh){
		String sql = "SELECT PSV.BH,PSV.CLXH,PSV.GGRQ FROM trff_app.PCB_ST_VEHICLE PSV"
				+ " WHERE PSV.CLXH LIKE " + "'" + clxh + "%'";
		Session session = this.getHibernateTemplate().getSessionFactory().openSession();
		try{
			Query query = session.createSQLQuery(sql);
			query.setResultTransformer(Transformers.ALIAS_TO_ENTITY_MAP);
			return query.list();
		}finally{
			session.clear();
			session.close();
		}
		
	}
	
	
	public Map  getGonggaoByBh(String bh){
		String sql = " Select PSV.*  From trff_app.PCB_ST_VEHICLE PSV WHERE PSV.BH=:bh";
		
	    String zpSql="Select PSPS.ZPBH from trff_app.PCB_ST_PHOTODES PSPS WHERE PSPS.BH=:bh";
	    
		Session session = this.getHibernateTemplate().getSessionFactory().openSession();
		try{
			Query query = session.createSQLQuery(sql);
			query.setParameter("bh", bh);
			query.setResultTransformer(Transformers.ALIAS_TO_ENTITY_MAP);
			List<Map> queryList =  query.list();
			Map map=queryList.get(0);
			
			List zpList=new ArrayList();
			
			BASE64Encoder encoder=new BASE64Encoder();
			Query zpQuery = session.createSQLQuery(zpSql);
			List<String> zpbhLists= zpQuery.list();
			
			byte[] b = null;
			Blob blob = null;
			
			if(zpbhLists!=null){
				List list = tidm.getGongGaoImageList((String[])zpbhLists.toArray());
				for(int i=0;i<list.size();i++){
					blob =(Blob)list.get(0);
					b=blobToBytes(blob);
					String strImage = encoder.encode(b);
					zpList.add(strImage);
					
				}
			}
			
			List gglb = getGonggaoListByClxh((String)map.get("CLXH"));
			
			map.put("ZP", zpList);
			map.put("GGLB", gglb);
			return map;
		}finally{
			session.clear();
			session.close();
		}
		
	}
	
	private static byte[] blobToBytes(Blob blob) {
		BufferedInputStream is = null;
		byte[] bytes = null;
		try {
			is = new BufferedInputStream(blob.getBinaryStream());
			bytes = new byte[(int) blob.length()];
			int len = bytes.length;
			int offset = 0;
			int read = 0;
			while (offset < len
					&& (read = is.read(bytes, offset, len - offset)) >= 0) {
				offset += read;
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return bytes;
	}
	

}
