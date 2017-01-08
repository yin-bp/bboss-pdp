package com.frameworkset.platform.admin.service;

import java.sql.SQLException;
import java.sql.Timestamp;
import java.util.Date;
import java.util.List;
import java.util.Map;

import org.apache.log4j.Logger;
import org.apache.log4j.Priority;
import org.frameworkset.platform.security.AccessControl;

import com.frameworkset.common.poolman.ConfigSQLExecutor;
import com.frameworkset.common.poolman.PreparedDBUtil;
import com.frameworkset.orm.transaction.TransactionManager;
import com.frameworkset.platform.admin.entity.Log;
import com.frameworkset.platform.admin.entity.LogCondition;
import com.frameworkset.platform.admin.entity.LogModule;
import com.frameworkset.platform.admin.entity.LogSetting;
import com.frameworkset.platform.admin.entity.LogStatic;
import com.frameworkset.util.ListInfo;

/**
 * <p>
 * 类名称：日志操作接口
 * </p>
 * <p>
 * 类说明：主要包括日志的增删改功能，在地税大集中系统中要求
 * </p>
 * 
 * @author yinbp
 * @version 1.0
 */
public class LogManagerImpl  implements LogManager {
	private ConfigSQLExecutor executor;
	 
	/* 序列化id */
	private static final long serialVersionUID = 1L;
	private static Logger logger = Logger.getLogger(LogManagerImpl.class);

	/* 日志主表的序列 */
	// public static final String LOG_SEQ_NAME = "SEQ_LOG" ;
	public static final String LOG_SEQ_NAME = "TD_SM_LOG";
	/* 日志明细表的序列 */
	// public static final String LOG_DETAIL_SEQ_NAME = "SEQ_LOG_DETAIL" ;
	public static final String LOG_DETAIL_SEQ_NAME = "TD_SM_LOGDETAIL";

	/*
	 * private static final String LOG_TABEL_NAME = "TD_SM_LOG"; private static
	 * final String LOG_DETAIL_TABEL_NAME = "TD_SM_LOGDETAIL"; private static
	 * final String LOG_HIS_TABEL_NAME = "TD_SM_LOG_HIS"; private static final
	 * String LOG_HIS_DETAIL_TABEL_NAME = "TD_SM_LOGDETAIL_HIS"; private static
	 * final String LOG_MODULE_TABEL_NAME = "TD_SM_LOGMODULE";
	 */

	/* 保存模块信息的全局变量 */
	static Map map = new java.util.HashMap();


 

	/**
	 * <p>
	 * 根据id删除日志，新老日志表中都删除
	 * </p>
	 * 
	 * @param logId
	 * @return boolean
	 * @throws LogException
	 */
	public boolean deleteLog(String logId) throws LogException {
		boolean r = false;
		if (logId != null) {
			String[] sqls = new String[4];
			sqls[0] = "delete from TD_SM_LOGDETAIL where log_id=?";
			sqls[1] = "delete from TD_SM_LOG where log_id=?";
			sqls[2] = "delete from TD_SM_LOGDETAIL_HIS where log_id=?";
			sqls[3] = "delete from TD_SM_LOG_HIS where log_id=?";
			executeBatch(sqls,logId);
			r = true;
		}
		return r;
	}

	/**
	 * <p>
	 * 批量执行数据库方法
	 * </p>
	 * 
	 * @param sqls
	 * @return boolean
	 * @throws LogException
	 */
	public void executeBatch(String[] sqls) throws LogException {
		if (sqls != null && sqls.length > 0) {
			PreparedDBUtil dbUtil = new PreparedDBUtil();
			// dbUtil.setAutoCommit(false);
			try {
				for (int loop = 0; loop < sqls.length; loop++) {
					dbUtil.preparedDelete(sqls[loop]);
					dbUtil.addPreparedBatch();
				}
				  dbUtil.executePreparedBatch();
			} catch (Exception e) {
				 
				logger.error("executeBatch() -> \n", e);
			} finally {
				 
			}
		}
		 
	}
	
	/**
	 * <p>
	 * 批量执行数据库方法
	 * </p>
	 * 
	 * @param sqls
	 * @return boolean
	 * @throws LogException
	 */
	public void executeBatch(String[] sqls,String logid) throws LogException {
		if (sqls != null && sqls.length > 0) {
			PreparedDBUtil dbUtil = new PreparedDBUtil();
			// dbUtil.setAutoCommit(false);
			try {
				for (int loop = 0; loop < sqls.length; loop++) {
					dbUtil.preparedDelete(sqls[loop]);
					dbUtil.setString(1, logid);
					dbUtil.addPreparedBatch();
				}
				  dbUtil.executePreparedBatch();
			} catch (Exception e) {
				 
				logger.error("executeBatch() -> \n", e);
			} finally {
				 
			}
		}
		 
	}

	/**
	 * 删除所有操作日志，包括所有新老日志，且无权限控制
	 * 
	 * @return boolean
	 * @throws LogException
	 */
	public boolean deleteAllLog() throws LogException {
		String[] sqls = new String[4];
		sqls[0] = "delete from TD_SM_LOGDETAIL ";
		sqls[1] = "delete from TD_SM_LOG ";
		sqls[2] = "delete from TD_SM_LOGDETAIL_HIS ";
		sqls[3] = "delete from TD_SM_LOG_HIS ";
		executeBatch(sqls);
		return true;
	}


	 
	 
 

	     

	/**
	 * 记录登陆用户操作信息
	 * 
	 * @param operUser
	 *            操作人
	 * @param operContent
	 *            日志内容
	 * @param operModule
	 *            日志类型
	 * @param operSource
	 *            操作来源（模块，ip）
	 * @param Desc
	 *            日志描述
	 * @return Object 主键值
	 * @throws LogException
	 */
	public String log(String operUser, String operContent, String operModle,
			String operSource, String Desc) throws LogException {
		// 老版系统中没有下面的方法，先注释掉

		AccessControl accessControl = AccessControl.getAccessControl();
		if (accessControl == null || accessControl.getUserID().length() == 0) {
			throw new LogException("无法取得合法的用户信息！");
		}

		return log(operUser, accessControl.getChargeOrgId(), operModle,
				operSource, operContent, Desc, Log.INSERT_OPER_TYPE);
	}

	/**
	 * 记录登陆用户操作信息
	 * 
	 * @param operUser
	 *            操作人
	 * @param operContent
	 *            日志内容
	 * @param operModule
	 *            日志类型
	 * @param operSource
	 *            操作来源（模块，ip）
	 * @throws LogException
	 */
	public String log(String operUser, String operContent, String operModle,
			String operSource) throws LogException {

		AccessControl accessControl = AccessControl.getAccessControl();
		if (accessControl == null || accessControl.getUserID().length() == 0) {
			throw new LogException("无法取得合法的用户信息！");
		}

		return log(operUser, accessControl.getChargeOrgId(), operModle,
				operSource, operContent, "", Log.INSERT_OPER_TYPE);

	}

	/**
	 * 记录用户操作日志，只需传入日志内容与操作模块
	 * 
	 * @param operContent
	 *            日志内容
	 * @param operModule
	 *            日志模块
	 * @throws LogException
	 */
	public String log(String operContent, String operModle)
			throws LogException {

		AccessControl accessControl = AccessControl.getAccessControl();
		if (accessControl == null || accessControl.getUserID().length() == 0) {
			throw new LogException("无法取得合法的用户信息！");
		}

		return log(accessControl.getUserAccount(), accessControl
				.getChargeOrgId(), operModle, accessControl.getMachinedID(),
				operContent, "", Log.INSERT_OPER_TYPE);

	}
	public List<LogModule> getLogModules() throws LogException{
		try {
			List<LogModule> logModules = this.executor.queryList(LogModule.class, "getLogModules");
			return logModules;
		} catch (SQLException e) {
			throw new LogException(e);
		}
	}
	
	public ListInfo queryListInfoLogs(LogCondition conditions, long offset, int pagesize) throws LogException {
		ListInfo datas = null;
		try {
			datas = executor.queryListInfoBean(Log.class, "queryListLog", offset, pagesize, conditions);
		} catch (Exception e) {
			throw new LogException("pagine query Log failed:", e);
		}
		return datas;

	}
	public ListInfo queryhisListInfoLogs(LogCondition conditions, long offset, int pagesize) throws LogException {
		ListInfo datas = null;
		try {
			datas = executor.queryListInfoBean(Log.class, "queryhisListInfoLogs", offset, pagesize, conditions);
		} catch (Exception e) {
			throw new LogException("pagine query his Log failed:", e);
		}
		return datas;

	}
	
	public void logsetting(List<LogSetting> logSettings) throws LogException{
		for(LogSetting logsetting:logSettings){
			if(logsetting.getStatus() == null){
				logsetting.setStatus(1);
			}
		}
		try {
			this.executor.updateBeans("logsetting", logSettings);
		} catch (SQLException e) {
			throw new LogException(e);
		}
	}
	
	public List<LogStatic> logstatic()throws LogException{
		try {
			return this.executor.queryList(LogStatic.class, "logstatic");
		} catch (SQLException e) {
			throw new LogException(e);
		}
	}
	
	public List<LogStatic> hislogstatic()throws LogException{
		try {
			return this.executor.queryList(LogStatic.class, "hislogstatic");
		} catch (SQLException e) {
			throw new LogException(e);
		}
	}

	/**
	 * <p>
	 * 记录一条不带明细的日志
	 * </p>
	 * <p>
	 * 可不带入用户信息记录日志，只要求带入业务参数
	 * </p>
	 * 
	 * @param operUser
	 * @param operOrg
	 * @param logModule
	 * @param visitorial
	 * @param oper
	 * @param remark1
	 * @param operType
	 * @return String 主键
	 * @throws LogException
	 */
	public String log(String operUser, String operOrg, String logModule,
			String visitorial, String operContent, String remark1, int operType)
			throws LogException {
		// 检测是否有主键信息
		if (logModule == null || logModule.trim().equals(""))
			throw new LogException("不是一个合法的日志！logModule属性不能为空。");
		logModule = logModule.trim();
		if (!enabledlog(logModule)) {
			if(logger.isDebugEnabled())
				logger.debug("模块[" + logModule + "]被配置为不可记录日志！");
			return null;
		}
		 

		try {
			 
			Log log = new Log();
			log.setLogContent(operContent);
			log.setLogOperuser(operUser);
			log.setOpOrgid(operOrg);
			log.setOperModule(logModule);
			log.setLogVisitorial(visitorial);
			log.setLogOpertime(new Timestamp(new Date().getTime()));
			log.setRemark1(remark1);
			log.setOperType(operType);
			this.executor.insertBean("addLog", log);
			return log.getLogId();
		} catch (Exception e1) {
			 if(logger.isEnabledFor(Priority.ERROR))
				 logger.error("记录日志失败",e1);
			 
		}
		 
		return null;

	}


	/**
	 * <p>
	 * 不带入用户信息记录日志，只要求带入业务参数
	 * </p>
	 * 
	 * @param logModule
	 * @param oper
	 * @param remark1
	 * @param operType
	 * @return String 主键
	 * @throws LogException
	 */
	public String log(String logModule, String oper, String remark1,
			int operType) throws LogException {

		// 在j2ee环境下取得用户信息

		AccessControl accessControl = AccessControl.getAccessControl();
		if (accessControl == null || accessControl.getUserID().length() == 0) {
			throw new LogException("无法取得合法的用户信息！");
		}

		// 新版系统中才有取mac地址的方法 accessControl.getMacAddr() ，所以暂时用 000000000000
		return log(accessControl.getUserAccount(), accessControl
				.getChargeOrgId(), logModule, accessControl.getMachinedID(),
				oper, remark1, Log.INSERT_OPER_TYPE);
	}

	 

	 

	/**
	 * 判断是否允许记日志
	 * 判断module是否存在于logmodule表中，如果存在继续下一步工作，如果不存在就往logmodule表中插入module记录
	 * 
	 * @param logmodule
	 * @return
	 */
	public boolean enabledlog(String logmodule) {
		if (logmodule == null || logmodule.trim().length() == 0)
			return false;
		String temp = logmodule.trim();
		Integer o_status = (Integer) map.get(temp);
		if (o_status == null) {
			try {
				LogModule logModule = this.executor.queryObject(LogModule.class, "getmodule", logmodule);
				if (logModule != null) {
					int status = logModule.getStatus();
					map.put(temp, new Integer(status));
					return status == 0;
					// return true;
				} else {
					 
					logModule = new LogModule();
					logModule.setLogmodule(temp);
					logModule.setStatus(0);
					this.executor.insertBean("addlogModule",logModule);
				 
					map.put(temp, new Integer(0));
					return true;
				}

			} catch (SQLException e) {
				return false;
			}
		} else {
			int status = o_status.intValue();
			return status == 0;
		}
	}

	//
	// /**
	// * 记录系统登录、退出时得日志
	// */
	//
	// public void log(String operUser, String operContent, String operType,
	// String operSource, String Desc) throws LogException {
	//			
	// log(operUser,operContent,operType,operSource,operSource,Desc);
	// }
	
	public void backuplog() throws LogException{
		TransactionManager tm = new TransactionManager();
		try {
			tm.begin();
			this.executor.insert("backuplog",new Date());
			this.executor.delete("deletealllog");
			tm.commit();
		} catch (Exception e) {
			throw new LogException("backuplog failed:",e);
		}
		finally
		{
			tm.release();
		}
	}
	/**
	 * 更新日志模块状态
	 * status：0 启用，1禁用
	 * @see com.frameworkset.platform.admin.service.LogManager#updatelogModuleStatus(java.lang.String, java.lang.String)
	 */
	public boolean updatelogModuleStatus(String status, String moduleId)
			throws LogException {
		boolean b = false;
		int _status = 0;
		// 0是记录日志
		if (status.equals("0")) {
			_status = 0;
		} else {
			_status = 1;
		}
		try {
			this.executor.update("updatelogModuleStatus", _status,moduleId);
			String logmodule = this.executor.queryObject(String.class, "getModuleCode", moduleId);
			 
			if ("0".equals(status)) {
				map.put(logmodule, new Integer(0));
			} else {
				map.put(logmodule, new Integer(1));
			}
			b = true;
		} catch (SQLException e) {

			throw new LogException(e);
		}
		return b;

	}

	 

	public boolean isNotNull(String str) {
		boolean flag = true;
		if (str == null || str.trim().length() == 0
				|| "null".equalsIgnoreCase(str)) {
			flag = false;
		}
		return flag;
	}

	 

	public static void main(String[] args) {
		LogManagerImpl logImpl = new LogManagerImpl();
	 
		try {
		 
			logImpl.log("admin", "登陆系统", "认证管理", "后台系统");
		} catch (LogException e) {
			e.printStackTrace();
		}

	}
	
	 

}
