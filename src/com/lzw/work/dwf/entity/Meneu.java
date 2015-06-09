package com.lzw.work.dwf.entity;

import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

@Scope("prototype")@Component("menu")
@Entity@Table(name="tb_meneu")
public class Meneu extends BaseEntity {

	private static final long serialVersionUID = 1L;

	@Column(name="c_title",length=100)
	private String title;
	
	@Column(name="c_url",length=500)
	private String url;

	@Column(name="c_icon",length=100)
	private String icon;
	
	@Column(name="c_level")
	private Integer level;
	
	@ManyToOne(cascade = CascadeType.ALL, fetch = FetchType.EAGER)
	@JoinColumn(name = "PARENT_ID")
	private Meneu parent;
	
	 @OneToMany(cascade = CascadeType.ALL, mappedBy = "parent", fetch = FetchType.EAGER)  
	private List<Meneu> children;

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public String getUrl() {
		return url;
	}

	public void setUrl(String url) {
		this.url = url;
	}

	public String getIcon() {
		return icon;
	}

	public void setIcon(String icon) {
		this.icon = icon;
	}

	public Integer getLevel() {
		return level;
	}

	public void setLevel(Integer level) {
		this.level = level;
	}

	public Meneu getParent() {
		return parent;
	}

	public void setParent(Meneu parent) {
		this.parent = parent;
	}

	public List<Meneu> getChildren() {
		return children;
	}

	public void setChildren(List<Meneu> children) {
		this.children = children;
	}

}
