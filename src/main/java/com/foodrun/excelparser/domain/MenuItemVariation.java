package com.foodrun.excelparser.domain;

import java.math.BigDecimal;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

import org.hibernate.annotations.GenericGenerator;

@Entity
@Table(name="menuitemvariation")
public class MenuItemVariation {
	private String id;
	private String menuItemId;
	private String text;
	private BigDecimal cost;
	
	public MenuItemVariation(String menuItemId, String text,
			BigDecimal cost) {
		super();
		this.menuItemId = menuItemId;
		this.text = text;
		this.cost = cost;
	}
	
	public MenuItemVariation(){}
	
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
	
	@Column(name="menuitem_id")
	public String getMenuItemId() {
		return menuItemId;
	}

	public void setMenuItemId(String menuItemId) {
		this.menuItemId = menuItemId;
	}
	
	@Column(name="text")
	public String getText() {
		return text;
	}

	public void setText(String text) {
		this.text = text;
	}
	
	@Column(name="cost", nullable=false)
	public BigDecimal getCost() {
		return cost;
	}

	public void setCost(BigDecimal cost) {
		this.cost = cost;
	}
	
}
