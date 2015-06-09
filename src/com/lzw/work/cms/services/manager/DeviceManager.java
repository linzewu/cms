package com.lzw.work.cms.services.manager;

import java.io.Serializable;
import java.util.List;

import javax.annotation.Resource;

import org.hibernate.SessionFactory;
import org.hibernate.criterion.DetachedCriteria;
import org.hibernate.criterion.Restrictions;
import org.springframework.context.annotation.Scope;
import org.springframework.orm.hibernate4.support.HibernateDaoSupport;
import org.springframework.stereotype.Service;

import com.lzw.work.cms.entity.Device;

@Scope("prototype")
@Service("deviceManager")
public class DeviceManager extends HibernateDaoSupport {
	
	@Resource(name = "sessionFactory")
	public void setBaseSessionFactory(SessionFactory sessionFactory) {
		super.setSessionFactory(sessionFactory);
	}
	
	public Device getDevicebyImei(String imei){
		return  this.getHibernateTemplate().get(Device.class, imei);
	}
	
	public void saveDevice(Device device){
		 this.getHibernateTemplate().saveOrUpdate(device);
	}
	
	public List<Device> getDevices(Device device,int firstResult,int maxResults){
		
		DetachedCriteria dc = DetachedCriteria.forClass(Device.class);
		
		if(device.getStationCode()!=null&&!"".equals(device.getStationCode().trim())){
			dc.add(Restrictions.eq("stationCode", device.getStationCode()));
		}
		
		if(device.getState()!=null){
			dc.add(Restrictions.eq("state", device.getState()));
		}
		
		return  (List<Device>) this.getHibernateTemplate().findByCriteria(dc, firstResult, maxResults);
	}

}
