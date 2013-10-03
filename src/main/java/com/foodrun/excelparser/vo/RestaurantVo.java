package com.foodrun.excelparser.vo;

import java.util.List;

public class RestaurantVo {
	private String name;
	private String id;
	List<MenuPageVo> menuPages;
	List<String> variationTitleList;
	
	
	public RestaurantVo(String name, String id, List<MenuPageVo> menuPages, List<String> variationTitleList) {
		this.name = name;
		this.id = id;
		this.menuPages = menuPages;
		this.variationTitleList = variationTitleList;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public List<MenuPageVo> getMenuPages() {
		return menuPages;
	}

	public void setMenuPages(List<MenuPageVo> menuPages) {
		this.menuPages = menuPages;
	}

	public List<String> getVariationTitleList() {
		return variationTitleList;
	}

	public void setVariationTitleList(List<String> variationTitleList) {
		this.variationTitleList = variationTitleList;
	}
	
}
