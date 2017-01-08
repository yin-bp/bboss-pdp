/**
 * 
 */
package com.frameworkset.platform.admin.action;

import java.util.List;

import org.frameworkset.util.annotations.PagerParam;
import org.frameworkset.web.servlet.ModelMap;

import com.frameworkset.platform.admin.entity.Log;
import com.frameworkset.platform.admin.entity.LogCondition;
import com.frameworkset.platform.admin.entity.LogModule;
import com.frameworkset.platform.admin.service.LogException;
import com.frameworkset.platform.admin.service.LogManager;
import com.frameworkset.util.ListInfo;

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

}
