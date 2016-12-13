package org.frameworkset.platform.security.authenticate;

import org.apache.log4j.Logger;
import org.frameworkset.platform.entity.User;
import org.frameworkset.platform.entity.UserUtil;
import org.frameworkset.platform.security.authentication.ACLLoginModule;
import org.frameworkset.platform.security.authentication.CheckCallBackWrapper;
import org.frameworkset.platform.security.authentication.LoginException;
import org.frameworkset.spi.SPIException;

import com.frameworkset.platform.admin.service.SmUserException;
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

public class ConfigUserPasswordLoginModule extends ACLLoginModule
{
    private static Logger log = Logger.getLogger(ConfigUserPasswordLoginModule.class);

  
   public ConfigUserPasswordLoginModule()
   {

   }

    protected boolean check(String userName,
                            String password,
                            CheckCallBackWrapper checkCallBack) throws
            LoginException
    {
    	
    	
        try {
           User user = UserUtil.getUser(userName);
          if(user == null)
          {
        	  throw new LoginException("用户["+userName+"]不存在!");
          }
         
            if(user.getPassword().equals(password))
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

    
    protected void buildCallback( CheckCallBackWrapper checkCallBack,User user ) throws LoginException
    {
    	checkCallBack.setUserAttribute("userAccount", user.getUserName());
        checkCallBack.setUserAttribute("userID", user.getUserId().toString());
         checkCallBack.setUserAttribute("job", "架构师");
        
        checkCallBack.setUserAttribute("title", user.getUserName()+"("+user.getUserAccount()+")");
        checkCallBack.setUserAttribute("userName", user.getUserName());
        checkCallBack.setUserAttribute("userSex", user.getUserSex());
         
       
        
        
    }

	@Override
	public void logoutCallback(String userName,
			CheckCallBackWrapper checkCallBackWrapper) {
		System.out.println("我退出了。。。。。");
	}
}
