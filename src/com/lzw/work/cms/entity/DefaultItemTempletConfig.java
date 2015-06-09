package com.lzw.work.cms.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;

import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

import com.lzw.work.dwf.entity.BaseEntity;

@Scope("prototype")
@Component("defaultItemTempletConfig")
@Entity
@Table(name = "TM_DefaultItemConfig")
public class DefaultItemTempletConfig extends BaseEntity {
	
	@Column(name = "C_TEMPLETNAME", length = 64)
	private String templetName;
	
	@Column(name = "C_TEMPLET", length = 64)
	private String templet;
	
	@Column(name = "C_REMARK", length = 2000)
	private String remark;
	

	public String getRemark() {
		return remark;
	}


	public void setRemark(String remark) {
		this.remark = remark;
	}


	public String getTempletName() {
		return templetName;
	}


	public void setTempletName(String templetName) {
		this.templetName = templetName;
	}


	public String getTemplet() {
		return templet;
	}


	public void setTemplet(String templet) {
		this.templet = templet;
	}

}
