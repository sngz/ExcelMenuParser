package com.foodrun.excelparser.vo;

import java.util.List;

public class MenuItemVo {
	private String serving;
	private String name;
	private String description;
	List<VariationVo> variations;	
	
	public MenuItemVo(String serving, String name, String description,
			List<VariationVo> variations) {
		this.serving = serving;
		this.name = name;
		this.description = description;
		this.variations = variations;
	}

	public String getServing() {
		return serving;
	}
	
	public void setServing(String serving) {
		this.serving = serving;
	}
	
	public String getName() {
		return name;
	}
	
	public void setName(String name) {
		this.name = name;
	}
	
	public String getDescription() {
		return description;
	}
	
	public void setDescription(String description) {
		this.description = description;
	}
	
	public List<VariationVo> getVariations() {
		return variations;
	}
	
	public void setVariations(List<VariationVo> variations) {
		this.variations = variations;
	}
		
}
