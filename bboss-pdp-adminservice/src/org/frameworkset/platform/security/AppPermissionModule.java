package org.frameworkset.platform.security;

import javax.servlet.http.HttpServletRequest;

import org.frameworkset.platform.security.authorization.AccessException;

import com.frameworkset.platform.admin.service.SmOrganizationService;
import com.frameworkset.platform.admin.service.SmUserService;

public class AppPermissionModule extends DefaultPermissionModule {
	private SmOrganizationService organizationService;
	private SmUserService userService;
	public AppPermissionModule() {
		// TODO Auto-generated constructor stub
	}

	/**
	 * 图形校验码校验
	 * HttpServletRequest request
	 * String rand = request.getParameter("rand");
	 *  String session_rand = (String) session
                                    .getAttribute(com.google.code.kaptcha.Constants.KAPTCHA_SESSION_KEY);
                            session.removeAttribute(com.google.code.kaptcha.Constants.KAPTCHA_SESSION_KEY);
                            if (session_rand == null || (!session_rand.equalsIgnoreCase(rand))) {
                                throw new AccessException("验证码错误!");
                            }
	 * @param code
	 * @param session
	 * @return
	 */
	public boolean validatecode(HttpServletRequest request) throws AccessException
	{
		String rand = request.getParameter("verifyCode");
		
		String session_rand = (String) request.getSession()
	                                    .getAttribute("verifyCode");
		request.getSession().removeAttribute("verifyCode");
        if (session_rand == null || (!session_rand.equalsIgnoreCase(rand))) {
            throw new AccessException("验证码错误!");
        }
        return true;
	}

	@Override
	public boolean isOrgManager(String userAccount) {
		// TODO Auto-generated method stub
		return organizationService.isOrgManager(userAccount);
	}

	@Override
	public boolean isOrganizationManager(String userAccount, String orgId) {
		// TODO Auto-generated method stub
		return organizationService.isOrganizationManager(userAccount, orgId);
	}

	@Override
	public boolean isSubOrgManager(String userAccount, String orgId) {
		// TODO Auto-generated method stub
		return organizationService.isSubOrgManager(userAccount, orgId);
	}

	@Override
	public String getChargeOrgId(String userAccount) {
		// TODO Auto-generated method stub
		return userService.getChargeOrgId(userAccount);
	}

	@Override
	public String getOrgLeader(String org) {
		// TODO Auto-generated method stub
		return organizationService.getOrgLeader(org);
	}

}
