package com.lzw.work.cms.entity;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

@Scope("prototype")
@Component("device")
@Entity
@Table(name = "TM_PRE_DEVICE")
public class Device {
	
	@Id
	@Column(name = "imei", length = 128)
	  private String imei;
	  
	@Column(name="stationCode",length=128)
	  private String stationCode;
	  
	@Column(name="x",length=64)
	  private String  x;
	  
	@Column(name="y",length=64)
	  private String y;
	  
	@Column(name="state")
	  private Integer state;
	  
	@Column(name="scope")
	  private Integer scope;
	  
	@Column(name="startTime",length=32)
	  private String startTime;
	  
	@Column(name="endTime",length=32)
	 private String endTime;
	  
	@Column(name="lastDate")
	  private Date lastDate;

	public String getImei() {
		return imei;
	}

	public void setImei(String imei) {
		this.imei = imei;
	}

	public String getStationCode() {
		return stationCode;
	}

	public void setStationCode(String stationCode) {
		this.stationCode = stationCode;
	}

	public String getX() {
		return x;
	}

	public void setX(String x) {
		this.x = x;
	}

	public String getY() {
		return y;
	}

	public void setY(String y) {
		this.y = y;
	}

	public Integer getState() {
		return state;
	}

	public void setState(Integer state) {
		this.state = state;
	}

	public Integer getScope() {
		return scope;
	}

	public void setScope(Integer scope) {
		this.scope = scope;
	}

	public String getStartTime() {
		return startTime;
	}

	public void setStartTime(String startTime) {
		this.startTime = startTime;
	}

	public String getEndTime() {
		return endTime;
	}

	public void setEndTime(String endTime) {
		this.endTime = endTime;
	}

	public Date getLastDate() {
		return lastDate;
	}

	public void setLastDate(Date lastDate) {
		this.lastDate = lastDate;
	}
	  
	  
}
