package com.lzw.work.dwf.entity;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashSet;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.Table;
import javax.persistence.Transient;

import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

@Scope("prototype")
@Component("user")
@Entity
@Table(name = "tb_user")
public class User extends BaseEntity {

	private static final long serialVersionUID = 1L;

	@Column(name = "C_USERNAME", length = 128, unique = true)
	private String userName;

	@Column(name = "C_PASSWORD", length = 128)
	private String password;

	@Column(name = "C_LOGIN_IP", length = 64)
	private String loginIp;

	@Column(name = "C_LOGIN_DATE")
	private Date loginDate;

	@Column(name = "C_CURRENTROLE_ID")
	private String currentRoleId;

	@ManyToMany
	@JoinTable(name = "tb_user_role", joinColumns = { @JoinColumn(name = "c_user_id") },
			inverseJoinColumns = { @JoinColumn(name = "c_role_id") })
	private List<Role> roles;
	
	@ManyToMany
	@JoinTable(name = "tb_user_group", joinColumns = { @JoinColumn(name = "c_user_id") },
			inverseJoinColumns = { @JoinColumn(name = "c_group_id") })
	private List<Group> groups;
	
	/////////////////////////非持久化//////////////////////////////////
	@Transient
	private List<Role> allRoles;
	

	public List<Group> getGroups() {
		return groups;
	}

	public void setGroups(List<Group> groups) {
		this.groups = groups;
	}

	/**
	 * 获取user的所有角色，包含组里面的角色
	 * @return
	 */
	public List<Role> getAllRoles() {
		
		if(this.allRoles==null){
			HashSet<Role> h  =   new  HashSet<Role>(); 
			if(roles!=null){
				h.addAll(roles);
			}
			if(groups!=null){
				for(Group group : groups){
					List<Role> groleList = group.getRole();
					if(groleList!=null){
						h.addAll(groleList);
					}
				}
			}
			List<Role> allRoles=new ArrayList<Role>(h);
			this.allRoles=allRoles;
		}
		return this.allRoles;
	}

	public String getUserName() {
		return userName;
	}

	public void setUserName(String userName) {
		this.userName = userName;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getLoginIp() {
		return loginIp;
	}

	public void setLoginIp(String loginIp) {
		this.loginIp = loginIp;
	}

	public Date getLoginDate() {
		return loginDate;
	}

	public void setLoginDate(Date loginDate) {
		this.loginDate = loginDate;
	}

	public String getCurrentRoleId() {
		return currentRoleId;
	}

	public void setCurrentRoleId(String currentRoleId) {
		this.currentRoleId = currentRoleId;
	}

	public List<Role> getRoles() {
		return roles;
	}

	public void setRoles(List<Role> roles) {
		this.roles = roles;
	}
}
