package com.lzw.work.dwf.entity;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.MappedSuperclass;

import org.hibernate.annotations.GenericGenerator;
import org.springframework.stereotype.Component;

@MappedSuperclass
public class BaseEntity implements Serializable {

	private static final long serialVersionUID = 1L;

	@Id
	@GenericGenerator(name = "idGenerator", strategy = "uuid")
	@GeneratedValue(generator = "idGenerator")
	@Column(name = "C_ID", length = 128)
	private String id;

	@Column(name = "C_CREATEUSER", length = 64,updatable=false)
	private String createdUser;

	@Column(name = "C_UPDATEDUSER", length = 64)
	private String updatedUser;

	@Column(name = "C_CREATEDDATE",updatable=false)
	private Date createdDate;

	@Column(name = "C_UPDATEDDATE")
	private Date updatedDate;

	@Column(name = "C_DATASTATE")
	private int dataState;

	@Override
	public int hashCode() {
		
		if(id!=null){
			return id.hashCode();
		}else{
			return super.hashCode();
		}
		
		
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj) {
			return true;
		}
		if (null != obj && obj instanceof BaseEntity) {
			BaseEntity b = (BaseEntity) obj;
			if (id.equals(b.id)) {
				return true;
			}
			return false;
		}else{
			return super.equals(obj);
		}
	}

	public String toString() {
		if(id!=null){
			return id;
		}else{
			return super.toString();
		}
		
	}

	public String getId() {
		return id;
	}

	public String getCreatedUser() {
		return createdUser;
	}

	public String getUpdatedUser() {
		return updatedUser;
	}

	public Date getCreatedDate() {
		return createdDate;
	}

	public Date getUpdatedDate() {
		return updatedDate;
	}

	public int getDataState() {
		return dataState;
	}

	public void setId(String id) {
		this.id = id;
	}

	public void setUpdatedUser(String updatedUser) {
		this.updatedUser = updatedUser;
	}

	public void setCreatedDate(Date createdDate) {
		this.createdDate = createdDate;
	}

	public void setCreatedUser(String createdUser) {
		this.createdUser = createdUser;
	}

	public void setUpdatedDate(Date updatedDate) {
		this.updatedDate = updatedDate;
	}

	public void setDataState(int dataState) {
		this.dataState = dataState;
	}

}
