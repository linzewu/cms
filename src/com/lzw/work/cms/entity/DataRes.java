package com.lzw.work.cms.entity;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import org.hibernate.annotations.Type;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

@Scope("prototype")
@Component("dataRes")
@Entity
@Table(name = "TM_DataRes")
public class DataRes implements Serializable {
	
	/**
	 * 序列id
	 */
	private static final long serialVersionUID = 6134901902533047555L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "id", length = 128)
	private Integer id;

	@Column(length = 128)
	private String reqMethod;

	@Column(length = 128)
	private String queryCode;

	@Column
	@Type(type = "text")
	private String resContext;

	@Column
	private int state;

	@Column
	private Date createDate;

	private String methodType;


	public String getReqMethod() {
		return reqMethod;
	}

	public String getQueryCode() {
		return queryCode;
	}

	public String getResContext() {
		return resContext;
	}

	public int getState() {
		return state;
	}

	public Date getCreateDate() {
		return createDate;
	}

	public String getMethodType() {
		return methodType;
	}


	public void setReqMethod(String reqMethod) {
		this.reqMethod = reqMethod;
	}

	public void setQueryCode(String queryCode) {
		this.queryCode = queryCode;
	}

	public void setResContext(String resContext) {
		this.resContext = resContext;
	}

	public void setState(int state) {
		this.state = state;
	}

	public void setCreateDate(Date createDate) {
		this.createDate = createDate;
	}

	public void setMethodType(String methodType) {
		this.methodType = methodType;
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	
	
}
