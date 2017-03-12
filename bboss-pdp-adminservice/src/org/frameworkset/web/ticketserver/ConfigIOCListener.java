/**
 * 
 */
package org.frameworkset.web.ticketserver;

import java.sql.SQLException;
import java.util.Map;

import org.apache.log4j.Logger;
import org.frameworkset.spi.BaseApplicationContext;
import org.frameworkset.spi.event.IocLifeCycleEventListener;

import com.frameworkset.common.poolman.DBUtil;
import com.frameworkset.common.poolman.SQLExecutor;
import com.frameworkset.orm.adapter.DB;

/**
 * @author yinbp
 *
 * @Date:2016-11-27 12:38:09
 */
public class ConfigIOCListener implements IocLifeCycleEventListener {
	private static Logger log = Logger.getLogger(ConfigIOCListener.class);
	 
	public ConfigIOCListener() {
		// TODO Auto-generated constructor stub
	}

	@Override
	public void afterstart(BaseApplicationContext arg0) {
		// TODO Auto-generated method stub

	}
	public static void initConfgDB()
	{
		
		String exist = "select 1 from auth_application";
		
		try {
			//SQLExecutor.updateWithDBName("gencode","drop table BBOSS_GENCODE");
			
			SQLExecutor.queryObject(int.class, exist);
		} catch (Exception e) {
			DB db = DBUtil.getDBAdapter();
			StringBuilder tsql = new StringBuilder();
			if(db.getDBTYPE().equals("mysql"))
			{
				tsql.append("CREATE TABLE auth_application (")
					.append("app_id VARCHAR(50) NOT NULL,")
					.append("app_code VARCHAR(50) NOT NULL,")
					.append("app_name VARCHAR(50) NULL,")
					.append("certAlgorithm VARCHAR(50) DEFAULT 'RSA',")
					.append("app_secret VARCHAR(100) NULL,")
					.append("app_secret_text VARCHAR(100) NULL,")
					.append("ticketlivetimes DECIMAL(10) NULL,")
					.append("PRIMARY KEY (app_id))")
					.append("ENGINE = InnoDB ")
					.append("DEFAULT CHARACTER SET = utf8 ")
					.append("COLLATE = utf8_unicode_ci ")
					.append("COMMENT = '应用表'");
			}
			else
			{
				tsql.append("CREATE TABLE auth_application (")
				.append("app_id VARCHAR2(50),")
				.append("app_code VARCHAR2(50),")
				.append("app_name VARCHAR2(50) ,")
				.append("certAlgorithm VARCHAR2(50) DEFAULT 'RSA',")
				.append("app_secret VARCHAR2(100) ,")
				.append("app_secret_text VARCHAR2(100) ,")
				.append("ticketlivetimes NUMBER(10) ,")
				.append("PRIMARY KEY (app_id))")
				;
			}
			log.info("BBOSS_GENCODE table 不存在，创建BBOSS_GENCODE表："+tsql+"。");
			try {
				SQLExecutor.update(tsql.toString());
				log.info("创建auth_application表成功："+tsql+"。");
			} catch (SQLException e1) {
				log.info("创建auth_application表失败："+tsql+"。",e1);
				 
			}
		}
		
		
		
	}
	@Override
	public void beforestart() {
		 
		 
		initConfgDB();
		log.info("初始化配置中心数据库完毕.");
		 

	}

	@Override
	public void init(Map<String, String> arg0) {

		 
		
		

	}

	 
}
