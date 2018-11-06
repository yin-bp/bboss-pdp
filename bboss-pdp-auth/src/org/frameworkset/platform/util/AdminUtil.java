/**
 * 
 */
package org.frameworkset.platform.util;

import com.frameworkset.util.StringUtil;
import org.frameworkset.platform.config.ConfigManager;
import org.frameworkset.platform.security.AccessControl;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.servlet.http.HttpServletRequest;
import java.util.Calendar;
import java.util.Date;

/**
 * @author yinbp
 *
 * @Date:2017-01-01 14:39:31
 */
public class AdminUtil {
	private static Logger logger = LoggerFactory.getLogger(AdminUtil.class);
	public static final String role_orgmanager = "orgmanager";
	public static final String role_orgmanagerroletemplate = "orgmanagerroletemplate";
	private static String[] realIpHeaders = null;
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

	public static String getClientIP(HttpServletRequest request)
	{
		if(realIpHeaders == null) {
			synchronized (AdminUtil.class) {
				if(realIpHeaders == null) {
					String realipheader = ConfigManager.getInstance().getConfigValue("realipheader",
							"X-Forwarded-For,x-forwarded-for,Proxy-Client-IP,WL-Proxy-Client-IP");
					realIpHeaders = realipheader.split(",");
				}
			}
		}
		return StringUtil.getClientIP(realIpHeaders,request);

	}
	public static int getDefaultPasswordDualTime()
	{
		return ConfigManager.getInstance().getConfigIntValue("password_dualtime", -1);
	}
	public static boolean isUserScopePasswordExpiredDays()
	{
		return ConfigManager.getInstance().getConfigBooleanValue("enableUserScopePasswordExpiredDays", false);
	}
	/**
	 * 获取密码过期时间，如果返回为null,表示密码永不过期
	 * @param passwordupdatetime
	 * @return
	 */
	public static Date getPasswordExpiredTime(Date passwordupdatetime, int expiredays)
	{
		if(passwordupdatetime == null)
			return null;
		if(!isUserScopePasswordExpiredDays())
			expiredays = getDefaultPasswordDualTime();
		if(expiredays <= 0)
			return null;
		Calendar calendar=Calendar.getInstance();
		calendar.setTime(passwordupdatetime);
		calendar.set(Calendar.DAY_OF_MONTH,calendar.get(Calendar.DAY_OF_MONTH)+expiredays);
		return new Date(calendar.getTime().getTime());
	}
	

}
