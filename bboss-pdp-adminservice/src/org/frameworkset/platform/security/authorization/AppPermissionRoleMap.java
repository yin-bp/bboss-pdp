//Source file: D:\\environment\\eclipse\\workspace\\cjxerpsecurity\\src\\com\\westerasoft\\common\\security\\websphere\\authorization\\impl\\AppPermissionRoleMap.java

package org.frameworkset.platform.security.authorization;

import org.frameworkset.platform.security.AdminServiceUtil;
import org.frameworkset.platform.security.authorization.impl.PermissionRoleMap;

/**
 *
 * 获取相应资源许可角色；
 *
 * @author biaoping.yin
 * @version 1.0
 */
public class AppPermissionRoleMap extends PermissionRoleMap {
//    private static TraceComponent tc;

 
//    static {
//        tc = Tr.register(AppPermissionRoleMap.class, null,
//                         "com.ibm.ejs.resources.security");
//    }
 
    /**
     * @since 2004.12.15
     */
    public AppPermissionRoleMap() {

    }

    /**
     * @param arg0
     * @param arg1
     * @param arg2
     * @return Role[]
     */
    protected AuthRole[] getRequiredRoles(
                                      java.lang.String resource,
                                      java.lang.String action,
                                      String resourceType) throws SecurityException{
     

    	AuthRole[] asecurityroles = null;
        try {
        	 asecurityroles = AdminServiceUtil.getRoleService().getRequiredRoles(
                     resource,
                     action,
                    resourceType);
        }
         
         catch (Exception e) {
        	 
        
        	throw new SecurityException(e);
        }
        return asecurityroles;
    }
    
    /**
     * 判断特定类型的资源是否授过任何角色
     * true:标识已授过权限
     * false:标识没有受过权限
     * @param resourceType
     * @param resource
     * @return
     * @throws SecurityException
     * add by 20080721 gao.tang 方法抛出SecurityException异常
     */
    public boolean hasGrantedRoles(String resource,String resourceType) throws SecurityException
    {
    	
    	boolean hasGranted = AdminServiceUtil.getRoleService().hasGrantedRoles( resource, resourceType);
		
    	return hasGranted;
    }

    /**
     * 判断资源有没有操作被授予role角色（要匹配角色名称和角色类型）
     *
     */
	public boolean hasGrantRole(AuthRole role, String resource, String resourceType) throws SecurityException
	{
		boolean hasGrantRole = AdminServiceUtil.getRoleService().hasGrantRole(role, resource, resourceType);
		
    	return hasGrantRole;
	}

}
