package com.lzw.work.dwf.entity;

import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.Table;

import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

@Scope("prototype")@Component("group")
@Entity @Table(name="tb_group")
public class Group extends BaseEntity {
	
	private static final long serialVersionUID = 1L;

	@ManyToMany
	@JoinTable(name = "tb_group_role", joinColumns = { @JoinColumn(name = "c_group_id") },
			inverseJoinColumns = { @JoinColumn(name = "c_role_id") })
	private List<Role> Role;
	
	@Column(name="c_remark",length=2000)
	private String remark;

	public List<Role> getRole() {
		return Role;
	}

	public void setRole(List<Role> role) {
		Role = role;
	}

	public String getRemark() {
		return remark;
	}

	public void setRemark(String remark) {
		this.remark = remark;
	}
	
}
