/**
 * 
 */
package org.frameworkset.platform.security;

import org.frameworkset.spi.BaseApplicationContext;
import org.frameworkset.web.servlet.support.WebApplicationContextUtils;

import com.frameworkset.platform.admin.service.RoleService;
import com.frameworkset.platform.admin.service.SmUserService;

/**
 * 管理服务组件获取工具类
 * @author yinbp
 *
 * @Date:2017-01-03 10:11:39
 */
public abstract class AdminServiceUtil {
	private static SmUserService userService;
	private static RoleService roleService;
	/**
	 * 
	 */
	public AdminServiceUtil() {
		// TODO Auto-generated constructor stub
	}
	public static SmUserService getUserService(){
		if(userService!=null)
			return userService;
		BaseApplicationContext context = WebApplicationContextUtils.getWebApplicationContext();
		userService = context.getTBeanObject("user.smUserService", SmUserService.class);
		return userService;
	}
	
	public static RoleService getRoleService(){
		if(roleService!=null)
			return roleService;
		BaseApplicationContext context = WebApplicationContextUtils.getWebApplicationContext();
		roleService = context.getTBeanObject("role.roleService", RoleService.class);
		return roleService;
	}

}
