package com.foodrun.excelparser.domain;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

import org.hibernate.annotations.GenericGenerator;

@Entity
@Table(name="menuitem")
public class MenuItem {
	private String id;
	private String name;
	private String description;
	private String menusectionId;
	private String servingId;
	
	public MenuItem(String name, String description,
			String menusectionId, String servingId) {
		super();
		this.name = name;
		this.description = description;
		this.menusectionId = menusectionId;
		this.servingId = servingId;
	}
	
	public MenuItem(){}
	
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
	
	@Column(name="menusection_id")
	public String getMenusectionId() {
		return menusectionId;
	}
	
	public void setMenusectionId(String menusectionId) {
		this.menusectionId = menusectionId;
	}
	
	@Column(name="serving_id")
	public String getServingId() {
		return servingId;
	}

	public void setServingId(String servingId) {
		this.servingId = servingId;
	}
	
}
