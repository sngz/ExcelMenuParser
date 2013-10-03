package com.foodrun.excelparser.vo;

import java.util.List;

public class MenuPageVo {
	private String pageName;
	private List<MenuItemVo> menuItems;

	public MenuPageVo(String pageName, List<MenuItemVo> menuItems) {
		this.pageName = pageName;
		this.menuItems = menuItems;
	}
	
	public String getPageName() {
		return pageName;
	}

	public void setPageName(String pageName) {
		this.pageName = pageName;
	}

	public List<MenuItemVo> getMenuItems() {
		return menuItems;
	}

	public void setMenuItems(List<MenuItemVo> menuItems) {
		this.menuItems = menuItems;
	}
}
