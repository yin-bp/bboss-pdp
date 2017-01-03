/**
 * 
 */
package org.frameworkset.platform.util;

import org.frameworkset.platform.security.AccessControl;

/**
 * @author yinbp
 *
 * @Date:2017-01-01 14:39:31
 */
public class AdminUtil {
	public static final String role_orgmanager = "orgmanager";
	public static final String role_orgmanagerroletemplate = "orgmanagerroletemplate";
	/**
	 * 
	 */
	public AdminUtil() {
		// TODO Auto-generated constructor stub
	}
	public static boolean filterGrantSpecialRole(String roleName)  {
//		String roleName = this.executor.queryObject(String.class, "getrolename", roleId);
		if(roleName.equals("orgmanager") || roleName.equals("orgmanagerroletemplate") || roleName.equals(AccessControl.getEveryonegrantedRoleName())){
			return true;
		}
			
		return false;
	}
	
	public static boolean filterDeleteSpecialRole(String roleName) {
		if(roleName.equals("orgmanager") || roleName.equals("orgmanagerroletemplate") ){
			return true;
		}
			
		return false;
	}
	
	public static boolean roleNeedSetUser(String roleName) {
		if(roleName.equals("orgmanager") || roleName.equals("orgmanagerroletemplate") ||roleName.equals(AccessControl.getEveryonegrantedRoleName()) || roleName.equals("guest")){
			return false;
		}
			
		return true;
	}
	
	public static boolean roleNeedGrantResource(String roleName) {
		if(roleName.equals(AccessControl.getAdministratorRoleName()) || roleName.equals("orgmanager")){
			return false;
		}
			
		return true;
	}
	
	public static String roleNeedSetUserMessage(String roleName) {
		if(roleName.equals("orgmanager")  ){
			return "部门管理员角色为系统固化角色，在设置部门管理时自动设置，无需手动设置给用户";
		}
		else if( roleName.equals("orgmanagerroletemplate") ){
			return "部门管理员模板角色为系统固化角色，在设置部门管理时自动设置，无需手动设置给用户";
		}
		else if( roleName.equals(AccessControl.getEveryonegrantedRoleName())  ){
			return "普通用户角色为系统固化角色，系统用户登录用户默认拥有普通用户角色，无需手动设置给用户";
		}
		else if(  roleName.equals("guest")){
			return "guest角色为系统固化角色，无需手动设置给用户";
		}
		else			
			return "";
	}
	
	public static String roleNeedGrantResourceMessage(String roleName) {
		if(roleName.equals(AccessControl.getAdministratorRoleName()) ){
			return "超级管理员角色，默认拥有系统所有资源操作权限，无需手动进行设置！";
		}
		else if( roleName.equals("orgmanager")){
			return "部门管理员角色为系统固化角色，在设置部门管理时自动设置，资源权限已经固化，不能进行手动设置！";
		}
		else			
			return "";
	}
	

}
