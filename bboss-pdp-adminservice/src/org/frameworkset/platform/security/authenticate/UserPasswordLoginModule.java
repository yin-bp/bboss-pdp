package org.frameworkset.platform.security.authenticate;

import com.frameworkset.orm.transaction.TransactionManager;
import com.frameworkset.platform.admin.entity.SmUser;
import com.frameworkset.platform.admin.service.SmUserException;
import com.frameworkset.platform.admin.service.SmUserService;
import com.frameworkset.util.StringUtil;
import org.frameworkset.platform.common.Constants;
import org.frameworkset.platform.entity.Leader;
import org.frameworkset.platform.security.authentication.ACLLoginModule;
import org.frameworkset.platform.security.authentication.CheckCallBackWrapper;
import org.frameworkset.platform.security.authentication.EncrpyPwd;
import org.frameworkset.platform.security.authentication.LoginException;
import org.frameworkset.spi.BaseApplicationContext;
import org.frameworkset.spi.SPIException;
import org.frameworkset.web.servlet.support.WebApplicationContextUtils;
import org.slf4j.LoggerFactory;


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
    private static org.slf4j.Logger log = LoggerFactory.getLogger(UserPasswordLoginModule.class);

  
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
    	
    	TransactionManager tm = new TransactionManager();
        try {
        	tm.begin();
        	SmUserService smUserService = this.getSmUserService();
           SmUser user = smUserService.getSmUserByIDNAMECNName(userName);
          if(user == null)
          {
        	  tm.commit();
        	  throw new LoginException("用户["+userName+"]不存在!");
          }
          if(user.getUserIsvalid() != Constants.USER_STATUS_NORMAL)
          {
        	  tm.commit();
        	  throw new LoginException("用户["+userName+"]无效,请联系系统管理员!");
          }
          String fromsso = checkCallBack.getRequest() != null ?(String)checkCallBack.getRequest().getAttribute("fromsso"):null;
          boolean _fromsso = fromsso != null && fromsso.equals("true");
            if(_fromsso || user.getUserPassword().equals(EncrpyPwd.encodePassword(password)))
            {
            	if(user.getLeaderid() == null || user.getLeaderid().equals("")){
            		if(user.getDepartId() != null){
            			Leader leader = smUserService.getLeader(user.getDepartTreeLevel());
            			if(leader != null){
            				checkCallBack.setUserAttribute("userLeaderid", leader.getLeaderId());
            		        checkCallBack.setUserAttribute("userLeaderName", leader.getLeaderName());
            		        checkCallBack.setUserAttribute("userLeaderAccount", leader.getLeaderAccount());
            			}
            		}
            		
            	}
            	else
            	{
            		 checkCallBack.setUserAttribute("userLeaderid", user.getLeaderid());
            	     checkCallBack.setUserAttribute("userLeaderName", user.getLeadername());
            	     checkCallBack.setUserAttribute("userLeaderAccount", user.getLeaderaccount());
            	}
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
               
                 tm.commit();	 
                 return true;
            }
            tm.commit();
            return false;

        }
        catch(LoginException e)
        {
        	 log.debug("",e);
        	throw e;
        }
        catch (SPIException ex) {
        	 log.debug("",ex);
           // ex.printStackTrace();
            throw new LoginException(StringUtil.exceptionToString(ex));
            /** @todo Handle this exception */
        }
        catch(SmUserException e)
        {
        	 log.debug("",e);
        	throw new LoginException("服务端异常："+e.getMessage());
        }
        catch(Throwable e)
        {
            log.debug("服务端异常：",e);
            //e.printStackTrace();
            //throw new LoginException("未知错误:" + e.getClass() + "," + e.getMessage());
            throw new LoginException(StringUtil.exceptionToString(e));
        }
        finally
        {
        	
        	tm.releasenolog();
        }
    }

    
    protected void buildCallback( CheckCallBackWrapper checkCallBack,SmUser user ) throws LoginException
    {
    	checkCallBack.setUserAttribute("userAccount", user.getUserName());
        checkCallBack.setUserAttribute("userID", user.getUserId().toString());
        checkCallBack.setUserAttribute("depart", user.getDepartName());
        checkCallBack.setUserAttribute("departId", user.getDepartId());
        checkCallBack.setUserAttribute("job", user.getUserJob());
        
        checkCallBack.setUserAttribute("title", user.getUserRealname()+"("+user.getUserName()+")");
        checkCallBack.setUserAttribute("userName", user.getUserRealname());
        checkCallBack.setUserAttribute("userSex", user.getUserSex());
        checkCallBack.setUserAttribute("worknumber", user.getUserWorknumber());
        checkCallBack.setUserAttribute("telphone", user.getUserMobiletel1());
         
       
        
        
    }

	@Override
	public void logoutCallback(String userName,
			CheckCallBackWrapper checkCallBackWrapper) {
		System.out.println("我退出了。。。。。");
	}
}
