//Source file: D:\\environment\\eclipse\\workspace\\cjxerpsecurity\\src\\com\\westerasoft\\common\\security\\websphere\\authorization\\impl\\AppAuthorizationTable.java

package org.frameworkset.platform.security.authorization;

import java.util.List;

import org.apache.log4j.Logger;
import org.frameworkset.platform.security.AdminServiceUtil;
import org.frameworkset.platform.security.authorization.impl.BaseAuthorizationTable;

import com.frameworkset.orm.transaction.TransactionManager;
import com.frameworkset.platform.admin.entity.Role;
import com.frameworkset.platform.admin.entity.SmUser;
import com.frameworkset.platform.admin.entity.UserRole;

/**
 * 
 * 应用角色/用户/用户组关系表
 * 
 * @author biaoping.yin
 * @version 1.0
 */
public class AppAuthorizationTable extends BaseAuthorizationTable{
	private static final Logger log = Logger
			.getLogger(AppAuthorizationTable.class);
//	private String[][] roles = new String[][]{
//		{"1","administrator",AuthRole.TYPE_ROLE},
//		{"3","manager",AuthRole.TYPE_ROLE},
//		{"5","leader",AuthRole.TYPE_ROLE}
//	};
	public AuthRole[] getAllRoleOfPrincipal(String userName){
		
		
//		boolean enableuserrole = ConfigManager.getInstance()
//				.getConfigBooleanValue("enableuserrole", true);
		TransactionManager tm = new TransactionManager();
		try{
			tm.begin();
			SmUser user = AdminServiceUtil.getUserService().getSmUserByName(userName);
			if (user == null) {
				log.debug(userName + " not exist");
				throw new SecurityException("Error: user[userAccount=" + userName + "] not exist.");
			}
			List<UserRole> userroles = AdminServiceUtil.getRoleService().getUserRoles(user.getUserId());
			AuthRole[] roles = null;
			if(userroles == null || userroles.size() == 0){
			 		
				roles = new AuthRole[1];		
				AuthRole authrole = new AuthRole();
				authrole.setRoleName(userName);
				authrole.setRoleId(user.getUserId() + "");
				authrole.setRoleType(AuthRole.TYPE_USER);
				roles[0] = authrole;
			}
			else
			{
				AuthRole authrole = null;
				 
				int size = userroles.size() + 1;
				roles = new AuthRole[size];
				authrole = new AuthRole();
				authrole.setRoleName(userName);
				authrole.setRoleId(user.getUserId() + "");
				authrole.setRoleType(AuthRole.TYPE_USER);
				roles[0] = authrole;
				for (int i = 0; i < userroles.size() ; i++) {
					Role role =   userroles.get(i);
					authrole = new AuthRole();
					authrole.setRoleName(role.getRoleName());
					authrole.setRoleId(role.getRoleId());
					authrole.setRoleType(AuthRole.TYPE_ROLE);
					roles[i+1] = authrole;
				}
				 
			}
			tm.commit();
			return roles;
		}
		catch(SecurityException e){
			throw e;
		}
		catch(Exception e){
			log.error("", e);
			throw new SecurityException(e);
		}
		finally
		{
			tm.releasenolog();
		}
			
		 
	}
	

	public AuthUser[] getAllPermissionUsersOfResource(String resourceid, String operation, String resourceType) throws SecurityException {		
//		try {
//
//			RoleManager roleMgr = SecurityDatabase.getRoleManager(super
//					.getProviderType());
//
//			List list = roleMgr.getAllUserOfHasPermission(resourceid, operation, resourceType);
//			if(list == null || list.size() == 0)
//				return null;
//			AuthUser[] authUsers = new AuthUser[list.size()];
//			for(int i = 0; i < authUsers.length; i ++)
//			{
//				AuthUser authUser = new AuthUser();
//				User user = (User)list.get(i);
//				authUser.setUserAccount(user.getUserName());
//				authUser.setUserID(user.getUserId() + "");
//				authUser.setUserName(user.getUserRealname());
//				authUsers[i] = authUser;
//				
//				
//			}
//			return authUsers;
//			
//
//		} catch (Exception e) {
//			//add by 20080721 gao.tang 添加异常输出信息e.printStackTrace();
//			e.printStackTrace();
//			throw new SecurityException(e.getMessage());
//		}
		throw new java.lang.UnsupportedOperationException();


	}

}
