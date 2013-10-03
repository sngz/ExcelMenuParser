package com.foodrun.excelparser.domain;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

import org.hibernate.annotations.GenericGenerator;

@Entity
@Table(name="menusection")
public class MenuSection {
	private String id;
	private String name;
	private String description;
	private String menuPageId;
	
	public MenuSection(String name, String description,
			String menuPageId) {
		super();
		this.name = name;
		this.description = description;
		this.menuPageId = menuPageId;
	}
	
	public MenuSection(){}
	
	@Id
	@GeneratedValue(generator = "uuid")
	@GenericGenerator(name = "uuid", strategy = "uuid2")
	@Column(name="id")
	public String getId() {
		return id;
	}
	
	public void setId(String id) {
		this.id = id;
	}
	
	@Column(name="name", nullable=false)
	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}
	
	@Column(name="description")
	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}
	
	@Column(name="menupage_id")
	public String getMenuPageId() {
		return menuPageId;
	}

	public void setMenuPageId(String menuPageId) {
		this.menuPageId = menuPageId;
	}
	
	
	
}
