/**
 * 
 */
package com.frameworkset.platform.admin.action;

import com.frameworkset.platform.admin.entity.*;
import com.frameworkset.platform.admin.service.LogException;
import com.frameworkset.platform.admin.service.LogManager;
import com.frameworkset.util.ListInfo;
import com.frameworkset.util.StringUtil;
import org.frameworkset.platform.config.ConfigManager;
import org.frameworkset.platform.security.AccessControl;
import org.frameworkset.platform.util.LogManagerInf;
import org.frameworkset.util.DataFormatUtil;
import org.frameworkset.util.annotations.PagerParam;
import org.frameworkset.util.annotations.ResponseBody;
import org.frameworkset.web.servlet.ModelMap;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.sql.Timestamp;
import java.text.SimpleDateFormat;
import java.util.List;

/**
 * @author yinbp
 *
 * @Date:2017-01-07 22:34:28
 */
public class LogController {
	private LogManager logManager;
	/**
	 * 
	 */
	public LogController() {
		// TODO Auto-generated constructor stub
	}
	public String index(ModelMap model){
		List<LogModule> logModules = logManager.getLogModules();
		model.addAttribute("logModules", logModules);
		return "path:index";
	}
	
	public String queryhisListInfoLogs(LogCondition conditions, @PagerParam(name = PagerParam.OFFSET) long offset,
			@PagerParam(name = PagerParam.PAGE_SIZE, defaultvalue = "10") int pagesize, ModelMap model)
					throws LogException {
		// Constant.component_type_actionimpl
		try {
			String logOperuser = conditions.getLogOperuser();
			if (logOperuser != null && !logOperuser.equals("")) {
				conditions.setLogOperuser("%" + logOperuser + "%");
			}
			String logVisitorial = conditions.getLogVisitorial();
			if (logVisitorial != null && !logVisitorial.equals("")) {
				conditions.setLogVisitorial("%" + logVisitorial + "%");
			}

			ListInfo logs = logManager.queryhisListInfoLogs(conditions, offset, pagesize);
			model.addAttribute("logs", logs);
			model.addAttribute("optypeMap", Log.optypeMap);
			
			return "path:queryhisListInfoLogs";
		} catch (LogException e) {
			throw e;
		} catch (Exception e) {
			throw new LogException("pagine query his Log failed:", e);
		}

	}
	public String queryListInfoLogs(LogCondition conditions, @PagerParam(name = PagerParam.OFFSET) long offset,
			@PagerParam(name = PagerParam.PAGE_SIZE, defaultvalue = "10") int pagesize, ModelMap model)
					throws LogException {
		// Constant.component_type_actionimpl
		try {
			String logOperuser = conditions.getLogOperuser();
			if (logOperuser != null && !logOperuser.equals("")) {
				conditions.setLogOperuser("%" + logOperuser + "%");
			}
			String logVisitorial = conditions.getLogVisitorial();
			if (logVisitorial != null && !logVisitorial.equals("")) {
				conditions.setLogVisitorial("%" + logVisitorial + "%");
			}

			ListInfo logs = logManager.queryListInfoLogs(conditions, offset, pagesize);
			model.addAttribute("logs", logs);
			model.addAttribute("optypeMap", Log.optypeMap);
			
			return "path:queryListInfoLogs";
		} catch (LogException e) {
			throw e;
		} catch (Exception e) {
			throw new LogException("pagine query Log failed:", e);
		}

	}
	private static Logger log = LoggerFactory.getLogger(LogController.class);
	/**
	 * 日志归档
	 */
	public @ResponseBody String backuplog(){
		
		try {
			long starttime = System.currentTimeMillis();
			
			this.logManager.backuplog();
			long endtime = System.currentTimeMillis();
			LogManagerInf logMgr = ConfigManager.getInstance().getLogManager();
			
			String userrelName = AccessControl.getAccessControl().getUserName();
			String userAccount = AccessControl.getAccessControl().getUserAccount();
			SimpleDateFormat format = DataFormatUtil.getSimpleDateFormat("yyyy-MM-dd HH:mm:ss");
			String operContent =  " 归档日志[开始时间:"
					+ format.format(new Timestamp(starttime)) + ",结束时间"
					+ format.format(new Timestamp(endtime)) + ",耗时"+(endtime - starttime)/1000+"秒]";
			
			String openModle = "日志管理";

			logMgr.log(userAccount + "(" +userrelName + ")", operContent,
					openModle, AccessControl.getAccessControl().getMachinedID());
			return "success";
		}  catch (Exception e) {
			log.error("", e);
			return StringUtil.exceptionToString(e);
		}
	}
	
	public @ResponseBody String logsetting(List<LogSetting> logSetting){
		if(logSetting == null || logSetting.size() == 0)
			return "没有需要设置的日志模块";
		logManager.logsetting( logSetting);
		return "success";
		
	}
	
	public String logstatic(ModelMap model){
		List<LogStatic> logstatics =  this.logManager.logstatic();
		model.addAttribute("logstatics", logstatics);
		return "path:logstatic";
	}
	
	public String hislogstatic(ModelMap model){
		List<LogStatic> logstatics =  this.logManager.hislogstatic();
		model.addAttribute("hislogstatic", logstatics);
		return "path:hislogstatic";
	}

}
