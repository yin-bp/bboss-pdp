package org.frameworkset.platform.security.authenticate;

import org.apache.log4j.Logger;
import org.frameworkset.platform.common.Constants;
import org.frameworkset.platform.security.authentication.ACLLoginModule;
import org.frameworkset.platform.security.authentication.CheckCallBackWrapper;
import org.frameworkset.platform.security.authentication.EncrpyPwd;
import org.frameworkset.platform.security.authentication.LoginException;
import org.frameworkset.spi.BaseApplicationContext;
import org.frameworkset.spi.SPIException;
import org.frameworkset.web.servlet.support.WebApplicationContextUtils;

import com.frameworkset.platform.admin.entity.SmUser;
import com.frameworkset.platform.admin.service.SmUserException;
import com.frameworkset.platform.admin.service.SmUserService;
import com.frameworkset.util.StringUtil;


/**
 *
 * <p>Title: UserPasswordLoginModule</p>
 *
 * <p>Description: </p>
 *
 * <p>Copyright: Copyright (c) 2006</p>
 *
 * <p>Company: bbossgroup</p>
 *
 * @author biaoping.yin
 * @version 1.0
 */

public class UserPasswordLoginModule extends ACLLoginModule
{
    private static Logger log = Logger.getLogger(UserPasswordLoginModule.class);

  
   public UserPasswordLoginModule()
   {

   }
   public SmUserService getSmUserService(){
	   BaseApplicationContext context = WebApplicationContextUtils.getWebApplicationContext();
	   return context.getTBeanObject("user.smUserService", SmUserService.class);
   }

    protected boolean check(String userName,
                            String password,
                            CheckCallBackWrapper checkCallBack) throws
            LoginException
    {
    	
    	
        try {
        	SmUserService smUserService = this.getSmUserService();
           SmUser user = smUserService.getSmUserByIDNAMECNName(userName);
          if(user == null)
          {
        	  throw new LoginException("用户["+userName+"]不存在!");
          }
          if(user.getUserIsvalid() != Constants.USER_STATUS_NORMAL)
          {
        	  throw new LoginException("用户["+userName+"]无效,请联系系统管理员!");
          }
            if(user.getUserPassword().equals(EncrpyPwd.encodePassword(password)))
            {
                 buildCallback( checkCallBack,user);
                 String theme = checkCallBack.getRequest().getParameter("theme");
                 if(StringUtil.isEmpty(theme))
                 {
                	 theme = "admin_1_darkblue";
                 }
                 if(theme != null)
                 {
                	 int i = theme.lastIndexOf('_');
                	 String theme_style = theme.substring(i+1);
                	 theme = theme.substring(0, i);
                	 checkCallBack.setUserAttribute("theme", theme);
                	 checkCallBack.setUserAttribute("theme_style", theme_style);
                 }
               
                	 
                 return true;
            }
            
            return false;

        }
        catch(LoginException e)
        {
        	throw e;
        }
        catch (SPIException ex) {
        	
           // ex.printStackTrace();
            throw new LoginException(StringUtil.exceptionToString(ex));
            /** @todo Handle this exception */
        }
        catch(SmUserException e)
        {
        	throw new LoginException("服务端异常："+e.getMessage());
        }
        catch(Throwable e)
        {
            log.debug("未知错误:" + e.getClass() + "," + e.getMessage());
            //e.printStackTrace();
            //throw new LoginException("未知错误:" + e.getClass() + "," + e.getMessage());
            throw new LoginException(StringUtil.exceptionToString(e));
        }
        finally
        {
//        	tm.releasenolog();
        }
    }

    
    protected void buildCallback( CheckCallBackWrapper checkCallBack,SmUser user ) throws LoginException
    {
    	checkCallBack.setUserAttribute("userAccount", user.getUserName());
        checkCallBack.setUserAttribute("userID", user.getUserId().toString());
        checkCallBack.setUserAttribute("depart", user.getDepartName());
        checkCallBack.setUserAttribute("job", "架构师");
        
        checkCallBack.setUserAttribute("title", user.getUserRealname()+"("+user.getUserName()+")");
        checkCallBack.setUserAttribute("userName", user.getUserRealname());
        checkCallBack.setUserAttribute("userSex", user.getUserSex());
         
        checkCallBack.setUserAttribute("userLeaderid", user.getLeaderid());
        checkCallBack.setUserAttribute("userLeaderName", user.getLeadername());
        checkCallBack.setUserAttribute("userLeaderAccount", user.getLeaderaccount());
        
        
    }

	@Override
	public void logoutCallback(String userName,
			CheckCallBackWrapper checkCallBackWrapper) {
		System.out.println("我退出了。。。。。");
	}
}
