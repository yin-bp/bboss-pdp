package com.frameworkset.platform.admin.service;

import java.io.Serializable;
import java.util.List;

import org.frameworkset.spi.Provider;

import com.frameworkset.platform.admin.entity.Log;
import com.frameworkset.platform.admin.entity.LogCondition;
import com.frameworkset.platform.admin.entity.LogDetail;
import com.frameworkset.platform.admin.entity.LogModule;
import com.frameworkset.platform.admin.entity.LogSetting;
import com.frameworkset.util.ListInfo;


/**
 * 项目：SysMgrCore <br>
 * 描述：日志管理接口 <br>
 * 版本：1.0 <br>
 * 
 * @author yinbp
 */
public interface LogManager extends Serializable {
	public void backuplog() throws LogException;
	/**
	 * 存储日志对象实例
	 * 
	 * @param log
	 *         需要存储到数据源中的日志实体对象，由于无法确定数据源所以传入该对象时请尽可能保证该对象的完整性也就是它的所有属性都有相应的值
	 * @return 如果存储成功则返回 true 否则返回 false
	 * @throws LogException
	 *             在处理当前方法的过程中如果遇到问题将抛出 LogException 异常
	 */
	//public boolean storeLog(Log log) throws LogException;

	public ListInfo queryListInfoLogs(LogCondition conditions, long offset, int pagesize) throws LogException ;
	public ListInfo queryhisListInfoLogs(LogCondition conditions, long offset, int pagesize) throws LogException ;
	
	  

	/**
	 * 根据日志ID删除指定的日志
	 * 
	 * @param jobId
	 *         需要删除的日志实体对象的日志ID
	 * @return 删除成功则返回 true 否则返回 false
	 * @throws LogException
	 *             在处理当前方法的过程中如果遇到问题将抛出 LogException 异常
	 */
	public boolean deleteLog(String logId) throws LogException;


//	
//	 /**
//	 * 返回数据分页的配置类
//	 * 
//	 * @return 可以设置数据分页对象，如：<br>
//	 *         PageConfig pageConfig = jobManager.getPageConfig();
//	 *         pageConfig.setPageSize(当前页面中需要显示的数据大小);
//	 *         pageConfig.setStartIndex(当前页面中显示数据的起始索引值); <br>
//	 *         int recordCount = pageConfig.getTotalSize();
//	 * @throws LogException
//	 *             在处理当前方法的过程中如果遇到问题将抛出 LogException 异常
//	 */
//	public PageConfig getPageConfig() throws LogException;

	 
	/**
	 * 记录登陆用户操作信息
	 * @param operUser	   操作人
	 * @param operContent  日志内容
	 * @param operModule     日志类型
	 * @param operSource   操作来源（模块，ip）
	 * @param Desc		   日志描述
	 * @throws LogException
	 */
	public String log(String operUser,String operContent,String operModule,String operSource,String Desc) throws LogException;
	
	/**
	 * 记录登陆用户操作信息
	 * @param operUser	   操作人
	 * @param operContent  日志内容
	 * @param operModule     日志类型
	 * @param operSource   操作来源（模块，ip）	 
	 * @throws LogException
	 */
	public String log(String operUser,String operContent,String operModule,String operSource) throws LogException;
	
	/**
	 * <p>记录一条不带明细的日志</p>
	 * @param operUser
	 * @param operOrg
	 * @param logModule
	 * @param visitorial
	 * @param oper
	 * @param remark1
	 * @param operType
	 * @return
	 * @throws LogException
	 */
	public String log(String operUser,String operOrg,String logModule, String visitorial,
			String oper ,String remark1, int operType)throws LogException ;
	
	/**
	 * <p>不带入用户信息记录日志，只要求带入业务参数</p>
	 * @param logModule
	 * @param oper
	 * @param remark1
	 * @param operType
	 * @return String 主键
	 * @throws LogException
	 */
	public String log(String logModule,String oper ,String remark1,int operType) throws LogException;
	
	 
	 
			
	/**
	 * 判断要填写日志的模块是否存在
	 * @param logmodule
	 * @return
	 * @throws LogException
	 */ 
	public boolean enabledlog(String logmodule)throws LogException;
	/**
	 * 修改日志是否记录状态
	 * @param logid
	 * @return
	 * @throws LogException
	 */
	public boolean updatelogModuleStatus(String status,String logmoduleid) throws LogException;
	
	 
	
	 	
	
	/**
	 * 记录用户操作日志，只需传入日志内容与操作模块
	 * @param operContent  日志内容
	 * @param operModule   日志类型
	 * @throws LogException
	 */	
	public String log(String operContent, String operModle) throws LogException;





	/**
	 * @return
	 */
	public List<LogModule> getLogModules() throws LogException;
	/**
	 * @param logSetting
	 */
	public void logsetting(List<LogSetting> logSetting) throws LogException;
	
}
