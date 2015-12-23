package com.lzw.work.cms.entity;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

import org.hibernate.annotations.GenericGenerator;
import org.hibernate.annotations.Type;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

@Scope("prototype")
@Component("dataReq")
@Entity
@Table(name = "TM_DataReq")
public class DataReq {
	
	@Id
	@GenericGenerator(name = "idGenerator", strategy = "uuid")
	@GeneratedValue(generator = "idGenerator")
	@Column(name = "id", length = 128)
	private String id;
	
	@Column(length=128)
	private String reqMethod;
	
	//查询码
	@Column(length=128)
	private String queryCode;
	
	@Column
	@Type(type="text")
	private String reqParam;
	
	@Column
	private int state;
	
	@Column
	private Date createDate;
	
	@Column(length=128)
	private String methodType;
	
	

	public String getMethodType() {
		return methodType;
	}

	public void setMethodType(String methodType) {
		this.methodType = methodType;
	}

	public String getQueryCode() {
		return queryCode;
	}

	public void setQueryCode(String queryCode) {
		this.queryCode = queryCode;
	}

	public String getId() {
		return id;
	}

	public String getReqMethod() {
		return reqMethod;
	}

	public String getReqParam() {
		return reqParam;
	}


	public int getState() {
		return state;
	}

	public Date getCreateDate() {
		return createDate;
	}

	public void setId(String id) {
		this.id = id;
	}

	public void setReqMethod(String reqMethod) {
		this.reqMethod = reqMethod;
	}

	public void setReqParam(String reqParam) {
		this.reqParam = reqParam;
	}


	public void setState(int state) {
		this.state = state;
	}

	public void setCreateDate(Date createDate) {
		this.createDate = createDate;
	}
	
	

}
