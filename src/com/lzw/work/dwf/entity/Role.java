package com.lzw.work.dwf.entity;

import java.util.List;

import javax.persistence.CollectionTable;
import javax.persistence.Column;
import javax.persistence.ElementCollection;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.Table;

import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

@Scope("prototype")@Component("role")
@Entity 
@Table(name="tb_role")
public class Role extends BaseEntity {
	
	private static final long serialVersionUID = 1L;

	@Column(name="C_ROLENAME",length=128)
	private String roleName;
	
	@Column(name="C_ROLESTATE")
	private int roleState;
	
	@Column(name="C_REMARK",length=2000)
	private String remark;
	
	@ElementCollection
	@CollectionTable(name = "tb_role_menu", joinColumns = { @JoinColumn(name = "c_role_id")})
	@Column(name="c_menu_id")
	private List<String> menuIds;
	
	@ElementCollection
	@CollectionTable(name = "tb_role_model", joinColumns = { @JoinColumn(name = "c_role_id")})
	@Column(name="c_mode_id")
	private List<String> modelsIds;
	
	
	
	public String getRoleName() {
		return roleName;
	}

	public void setRoleName(String roleName) {
		this.roleName = roleName;
	}

	public int getRoleState() {
		return roleState;
	}

	public void setRoleState(int roleState) {
		this.roleState = roleState;
	}

	public String getRemark() {
		return remark;
	}

	public void setRemark(String remark) {
		this.remark = remark;
	}
	
}
