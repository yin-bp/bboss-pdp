/**
 * 
 */
package com.frameworkset.platform.admin.entity;

/**
 * @author yinbp
 *
 * @Date:2016-12-28 23:52:18
 */
public class MenuOPS extends AuthOPS{
	 
	private String menupath ;
	private String menuName ;
	 
	/**
	 * 
	 */
	public MenuOPS() {
		// TODO Auto-generated constructor stub
	}

	public String getMenupath() {
		return menupath;
	}

	public void setMenupath(String menupath) {
		this.menupath = menupath;
	}

	public String getMenuName() {
		return menuName;
	}

	public void setMenuName(String menuName) {
		this.menuName = menuName;
	}
	
}
