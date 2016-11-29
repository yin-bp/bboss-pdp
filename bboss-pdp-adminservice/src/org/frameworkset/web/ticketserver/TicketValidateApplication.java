/**
 * 
 */
package org.frameworkset.web.ticketserver;

import org.frameworkset.platform.application.service.ApplicationException;
import org.frameworkset.platform.application.service.ApplicationService;
import org.frameworkset.platform.security.authentication.EncrpyPwd;
import org.frameworkset.web.token.AppValidateResult;
import org.frameworkset.web.token.Application;
import org.frameworkset.web.token.BaseValidateApplication;
import org.frameworkset.web.token.ValidateApplicationException;

/**
 * @author yinbp
 *
 * @Date:2016-11-13 12:37:37
 */
public class TicketValidateApplication extends BaseValidateApplication {
	private ApplicationService applicationService;
	/**
	 * 
	 */
	public TicketValidateApplication() {
		// TODO Auto-generated constructor stub
	}

	/* (non-Javadoc)
	 * @see org.frameworkset.web.token.BaseValidateApplication#validateApp(java.lang.String, java.lang.String)
	 */
	@Override
	public AppValidateResult validateApp(String appid, String secret) throws ValidateApplicationException {
		AppValidateResult result = new AppValidateResult();
		try
		{
			org.frameworkset.platform.application.entity.Application _app = applicationService.getApplicationByAppcode(appid);
			if(_app == null )
			{
				result.setResult(false);
				result.setError("应用["+appid+"]不存在");
				
			}
			else if(!EncrpyPwd.encodePassword(secret).equals(_app.getAppSecret()))
			{
				result.setResult(false);
				result.setError("应用["+appid+"]口令错误"); 
			}
			else
			{
				Application app = new Application();
				app.setAppid(appid);
				app.setSecret(secret);
				app.setSign(true);
				
				app.setTicketlivetime(_app.getTicketlivetimes());
				result.setApplication(app);
				result.setResult(true);
			}
				 
			 
		}
		 
		catch(ApplicationException e)
		{
			result.setResult(false);
			result.setError("获取应用[appid="+appid+"]失败:"+e.getMessage());
//			throw new ValidateApplicationException("获取应用[appid="+appid+"]失败",e);
		}
		catch(Exception e)
		{
			result.setError("获取应用[appid="+appid+"]失败:"+e.getMessage());
		}
		return result;
		
	}

}
