package org.frameworkset.platform.log;

import com.frameworkset.platform.admin.service.LogManager;

public class AppLogManager extends DefaultLogManager{
//	private static Logger logger = Logger.getLogger(AppLogManager.class);
	private LogManager logManager;
	public AppLogManager() {
		// TODO Auto-generated constructor stub
	}

	@Override
	public void log(String userName, String operContent, String openModule, String operSource) {
		logManager.log(userName, operContent, openModule, operSource);
	}

	@Override
	public void log(String operUser, String operOrg, String logModule, String visitorial, String oper, String remark1,
			int operType) {
		logManager.log(  operUser,   operOrg,   logModule,   visitorial,   oper,   remark1,
				  operType);
		 
	}

	@Override
	public void log(String userName, String operContent, String openModle, String operSource,String remarks) {
		logManager.log(  userName,   operContent,   openModle,   operSource,   remarks);
	}

}
