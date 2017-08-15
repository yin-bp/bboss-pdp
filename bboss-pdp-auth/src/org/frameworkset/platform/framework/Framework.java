package org.frameworkset.platform.framework;

import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Locale;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Set;

import javax.servlet.ServletContext;
import javax.servlet.http.HttpServletRequest;

import org.frameworkset.platform.config.ConfigManager;
import org.frameworkset.platform.framework.Item.ItemUrlStruction;
import org.frameworkset.platform.framework.Item.Variable;
import org.frameworkset.platform.security.AccessControl;
import org.frameworkset.platform.security.authorization.impl.AppSecurityCollaborator;
import org.frameworkset.platform.security.authorization.impl.PermissionToken;
import org.frameworkset.platform.security.authorization.impl.PermissionTokenMap;
import org.frameworkset.platform.security.authorization.impl.ResourceToken;
import org.frameworkset.security.AccessControlInf;
import org.frameworkset.spi.BaseApplicationContext;
import org.frameworkset.spi.BaseSPIManager2;
import org.frameworkset.spi.support.MessageSource;
import org.frameworkset.spi.support.MessageSourceResolvable;
import org.frameworkset.spi.support.NoSuchMessageException;
import org.frameworkset.web.servlet.i18n.WebMessageSourceUtil;
import org.frameworkset.web.servlet.support.RequestContextUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.frameworkset.util.DaemonThread;
import com.frameworkset.util.ResourceInitial;

/**
 * <p>
 * Title:
 * </p>
 * 
 * <p>
 * Description:
 * </p>
 * 
 * <p>
 * Copyright: Copyright (c) 2005
 * </p>
 * 
 * <p>
 * Company: bbossgroups
 * </p>
 * 
 * @author biaoping.yin
 * @version 1.0
 */
public class Framework implements ResourceInitial,MessageSource {
	static{
		BaseApplicationContext.addShutdownHook(new Runnable(){

			public void run() {
				
				 destroy();
				
			}});
	}
	static void destroy()
	{
		if(listen != null)
		{
			listen.stopped();
			listen = null;
		}
		if(init != null)
		{
			init._destroy();
			init = null;
//			
		}
	}
//	private boolean monitered = false;
	private ServletContext servletContext;
	private static Logger log = LoggerFactory.getLogger(Framework.class);
	 private Map<String,Locale> languages;
	/**
	 * 定义当前的栏目线程,用来保存系统当前栏目的框架属性，如：rows，cols等等
	 */
	// private static final ThreadLocal itemlocal = new ThreadLocal();
	public final static String MENU_TYPE = "menu_type";
	public final static String MENU_PATH = "menu_path";
	public final static String SUBSYSTEM = "subsystem_id";

	/**
	 * 栏目外部参数定义，系统不能覆盖外部参数。
	 */
	public final static String EXTERNAL_PARAMS_KEY = "external_params";
	public static final int ROOT_CONTAINER = 0;
	public static final int MAIN_CONTAINER = 14;
	public static final int MAIN_CONTAINER_MENU = 15;
	public static final int CONTENT_CONTAINER = 1;
	public static final int NAVIGATOR_CONTAINER = 2;
	public static final int ACTION_CONTAINER = 3;
	public static final int PROPERTIES_CONTAINER = 4;
	public static final int STATUS_CONTAINER = 5;
	public static final int LEFTSIDE_CONTAINER = 6;
	public static final int TOPSIDE_CONTAINER = 7;
	public static final int NAVIGATOR_TOOLBAR = 8;
	public static final int NAVIGATOR_CONTENT = 9;
	public static final int PROPERTIES_CONTENT = 10;
	public static final int PROPERTIES_TOOLBAR = 11;
	public static final int STATUS_CONTENT = 12;
	public static final int STATUS_TOOLBAR = 13;
	public static  String menu_folder = BaseSPIManager2.getProperty("menu.folder","");
	public static boolean menu_monitor = BaseSPIManager2.getBooleanProperty("menu_monitor",true);

	/**
	 * 存储每个贞属性
	 */
	public Map framesetAttributeOfItem;

	 


	// public static final String SUPER_MENU = "module::menu://sysmenu$root";
	public static final String COOKIE_NAME = "sysmenuCookie";
	
	/**
	 * 子系统对应的框架模板路经
	 */
	private String template;
	/**
	 * module.xml中的模块索引
	 * 
	 * 缺省系统模块菜单索引, Map<String menuPath,MenuItem>
	 */

	private Map indexs;
	/**
	 * 菜单id索引表
	 */
	private Map indexByIds;
	 private MenuQueue menus; 

	private Root root = null;

	/**
	 * 子系统菜单集合 Map<String subsystemid,SubSystem>
	 */
	private Map<String,SubSystem> subsystems;
	private List<SubSystem> subsystemList; 

	/**
	 * 子系统菜单集合 Map<String subsystemid,Framework>
	 */
	private Map subsystemFrameworks;
	private String description;
	private Map<Locale,String> localeDescriptions;
	private SubSystem frameworkmeta = null;
	private SubSystem rootsystem = null;
	private String messagesourcefiles;
	private MessageSource messagesource;
	private static Framework init;

	/**
	 * 定义切换系统框架常数，控制对应的窗口的最大化和还原
	 */
	public static final int SWITCH_LEFT = 1;
	public static final int SWITCH_NAVIGATOR = 2;
	public static final int SWITCH_WORKSPACE = 3;
	public static final int SWITCH_STATUS = 4;
	public static final int SWITCH_SCOPE_PERSPECTIVECONTENT = 1;
	public static final int SWITCH_SCOPE_PERSPECTIVEMAIN = 2;

	/**
	 * 主框架行参数定义
	 */
	public static final String FRAMEWORKSET_ROWS = "0,38,*";

	/**
	 * 主框架列参数定义
	 * 
	 */
	public static final String FRAMEWORKSET_COLS = "30,12,*";
	public static final String PERSPECTIVE_MAIN_ROWS = "20%,*";
	public static final String PERSPECTIVE_CONTENT_COLS = "20%,*";
	public static final String NAVIGATOR_CONTAINER_ROWS = "30,*";
	public static final String ACTIONS_CONTAINER_ROWS = "75%,*";
	public static final String PROPERTIES_CONTAINER_ROWS = "30,*";
	public static final String STATUS_CONTAINER_ROWS = "30,*";
	private ModuleQueue modules;
	private ItemQueue items;
	private Item defaultItem;
	private Item publicItem;
	private String configFile = "module.xml";
	private String top_height = null;
	private String left_width = null;
	private String navigator_width = null;
	private String workspace_height = null;
	private String global_target = "perspective_content";
	private boolean inited = false;
	private String showhidden = "true";
	private String showhidden_width = "15";

	/**
	 * 保存配置文件路径
	 */
	private String parentPath;

	private static DaemonThread listen;
	public static final String defaultSystemID = "module";
	private String systemid;

	// private HttpServletRequest request;

	private Framework() {
		framesetAttributeOfItem = new HashMap();
		this.root = new Root();
	}

	public static Framework getInstance() {
		return getInstanceWithContext(null);
	}
	
	public static Framework getInstanceWithContext(ServletContext servletContext) {
		if (init != null) {
			return init;
		} else {
			Framework init_ = new Framework();
			init_.setServletContext(servletContext);
			init_.init((String) null);
			return init = init_;
		}
	}

	public static Framework getInstance(String subsystem,ServletContext servletContext) {

		if (init == null) {

//			init = new Framework();
//			init.init((String) null);
			getInstanceWithContext(servletContext);

		}
		if (subsystem == null || subsystem.equals("")
				|| subsystem.equals("module")) {
			return init;
		} else {
			return init.getSubFramework(subsystem);
		}

	}
	
	public static Framework getInstance(String subsystem) {

		if (init == null) {

			init = new Framework();
			init.init((String) null);

		}
		if (subsystem == null || subsystem.equals("")
				|| subsystem.equals("module")) {
			return init;
		} else {
			return init.getSubFramework(subsystem);
		}

	}

	// public static Framework getInstance(String subsystem)
	// {
	// return (Framework)init.subsystemFrameworks.get(subsystem);
	// }

	public static Framework getSubFramework(String subsystem) {

		if (init.subsystemFrameworks == null
				|| init.subsystemFrameworks.size() == 0)
			return init;
		Framework ret = (Framework) init.subsystemFrameworks.get(subsystem);
		if (ret == null)
			return init;
		return (Framework) init.subsystemFrameworks.get(subsystem);
	}

	public static String getSubFrameworkName(String subsystem,HttpServletRequest request) {
		Framework framework = getSubFramework(subsystem);
		return framework != null ? framework.getDescription(request) : "";
	}

	/**
	 * 自加载子系统模块
	 */
	private void initSubSystems() {
		if (this.subsystems == null || subsystems.isEmpty()) {
			return;
		}
		subsystemFrameworks = new HashMap();
		Set set = this.subsystems.entrySet();
		for (Iterator subs = set.iterator(); subs.hasNext();) {
			Map.Entry sub = (Map.Entry) subs.next();
			SubSystem subsystem = (SubSystem) sub.getValue();
			subsystem.setParent(this.frameworkmeta);
			Framework subframework = new Framework();
			subframework.setServletContext(servletContext);
			subframework.setLanguages(this.languages);
			subframework.init(subsystem);
			this.subsystemFrameworks.put(subsystem.getId(), subframework);

		}
	}

	private void init(SubSystem subsystem) {
		if (subsystem == null) {
			return;
		}
		String temp = subsystem.getModule();
//		configFile = getRealpath(temp);
		configFile = temp;
		log.debug("Load subsystem[" + configFile + "]");
		boolean isFile = false;
		if(!menu_folder.equals(""))
		{
		    isFile = true;
		    configFile =this.getFilePath(configFile);  
		}
		this.systemid = subsystem.getId();
		FrameworkConfiguration config = new FrameworkConfiguration(configFile,isFile,systemid);
		config.setLanguages(this.languages);
		config.setOwnersubsystem(subsystem);
		this.root.setSubSystem(subsystem);
		try {
//			config.loadConfiguration();
//			modules = config.getModules();
//			indexs = config.getIndexs();
//			this.indexByIds = config.getIndexByIds();
////			framesetAttributeOfItem = Collections
////					.synchronizedMap(new HashMap());
//			framesetAttributeOfItem = new HashMap();
//			this.items = config.getItems();
//			this.defaultItem = config.getDefaultItem();
//			description = config.getDescription();
//			this.top_height = config.getTop_height();
//			this.left_width = config.getLeft_width();
//			this.navigator_width = config.getNavigator_width();
//			this.workspace_height = config.getWorkspace_height();
//			this.showhidden = config.getShowhidden();
//			showhidden_width = config.getShowhidden_width();
//			publicItem = config.getPublicItem();
//			
//			this.global_target = config.getGlobal_target();
//			this.frameworkmeta = subsystem;
//			this.showrootleftmenu = config.isShowrootleftmenu();
//			
//			this.messagesourcefiles = config.getMessagesourcefiles();
//			initMessageSource();
			this.frameworkmeta = subsystem;
			this.buildFramework(config);
			this.subsystems = config.getSubsystems();
			this.subsystemList = config.getSubsystemList(); 
			subsystem.setFramework(this);
			initSubSystems();
//			this.inited = true;
		} catch (Exception ex) {
			log.error(ex.getMessage(), ex);
		}
		monitor(configFile);

	}

	private String getRealpath(String path) {
		if (init.parentPath == null || init.parentPath.equals("")
				|| path.charAt(1) == ':' || path.charAt(0) == '/')
			return path;
		return init.parentPath + "/" + path;
	}
	private static Object mlock = new Object();
	public void monitor(String configFile) {
		if (menu_monitor ) {
			if(listen == null)
			{
				synchronized(mlock)
				{
					if(listen == null)
					{
						listen = new DaemonThread(5000,"sysmenu refresh thread");
						listen.start();
						
					}
					
				}
			}
			listen.addFile(configFile, this);			
			
		}

	}
	
	
	
	public String getFilePath(String configfile)
	{
	    return this.menu_folder + "/" + configfile; 
	}
	 /**
     * 一级items是否显示左侧菜单，true显示，false不显示
     */
    private boolean showrootleftmenu = false;
	public boolean isShowrootleftmenu() {
		return showrootleftmenu;
	}
	private void initMessageSource()
	{
//		if(this.messagesource != null)
//		{
//			((HotDeployResourceBundleMessageSource) messagesource).destroy();
//			messagesource = null;
//		}
//		if(this.messagesourcefiles != null && !this.messagesourcefiles.trim().equals(""))
//		{
//			try {
//				HotDeployResourceBundleMessageSource messagesource_ = new HotDeployResourceBundleMessageSource( );   
//				//如果有多个配置文件，可以在数组中追加，注意属性文件名不需要带语言信息和文件后缀   
//				//HotDeployResourceBundleMessageSource会自动扫描并加载对应语言的属性文件，后文同理。   
//				String[] messagefiles = this.messagesourcefiles.split("\\,");
//				messagesource_.setBasenames(messagefiles);   
//				//如果没有找到代码对应的配置项，直接输出code   
//				messagesource_.setUseCodeAsDefaultMessage(true);  
//				this.messagesource = messagesource_;
//			} catch (Exception e) {
//				log.error(e);
//			}
//
//		}
		if(this.messagesourcefiles != null && !this.messagesourcefiles.trim().equals(""))
		{
			if(messagesourcefiles.startsWith("web::"))//web资源配置文件标识开头
			{
				messagesource = WebMessageSourceUtil.getMessageSource(messagesourcefiles.substring("web::".length()), servletContext);
			}
			else
			{
				messagesource = WebMessageSourceUtil.getMessageSource(messagesourcefiles);
			}
				
		}
		
		
	}
	
	private PermissionTokenMap buildPermissionTokenMap()
	{
		PermissionTokenMap permissionTokenMap = AppSecurityCollaborator.getInstance().getPermissionTokenMap();
		if(this.indexByIds != null && this.indexByIds.size() > 0)
		{
			permissionTokenMap.resetPermissionByRegion("column", this.getSystemid());
			Iterator entrySet = indexByIds.entrySet().iterator();
			while(entrySet.hasNext())
			{
				Object item = ((Entry)entrySet.next()).getValue();
			
				if(item instanceof Item)
				{
					
					Item it = (Item) item;
					PermissionToken token = null;
					if(it.getWorkspaceContent() != null && !it.getWorkspaceContent().equals(""))
					{
						token = new PermissionToken("column", it.getId(),
								"visible");
						permissionTokenMap.addPermissionToken(PermissionTokenMap.buildResourceToken(it.getWorkspaceContent()),this.getSystemid(), token);
					}
					if(it.getAuthorResources() != null)
					{
						
						List<ResourceToken> authorResources = it.getAuthorResources();
						for(ResourceToken authorResource:authorResources)
						{
							token = new PermissionToken("column", it.getId(),
									"visible");
							permissionTokenMap.addPermissionToken(authorResource,this.getSystemid(), token);
						}
					}
				}
				else if(item instanceof Module)
				{
					Module it = (Module) item;
					PermissionToken token = null;
					if(it.getUrl() != null && !it.getUrl().equals(""))
					{
						token = new PermissionToken("column", it.getId(),
								"visible");
						permissionTokenMap.addPermissionToken(PermissionTokenMap.buildResourceToken(it.getUrl()),this.getSystemid(), token);
					}
					if(it.getAuthorResources() != null)
					{
						List<ResourceToken> authorResources = it.getAuthorResources();
						for(ResourceToken authorResource:authorResources)
						{
							token = new PermissionToken("column", it.getId(),
									"visible");
							permissionTokenMap.addPermissionToken(authorResource,this.getSystemid(), token);
						}
					}
				}
					
			}
			if(this.publicItem != null)
			{
				PermissionToken token = new PermissionToken("column", "publicItem",
						"visible");
				
				permissionTokenMap.addUnprotectedPermissionToken(PermissionTokenMap.buildResourceToken(publicItem.getWorkspaceContent()), this.getSystemid(), token);
				String isanypage = publicItem.getWorkspacecontentExtendAttribute("isany");
				if(isanypage == null)
					isanypage = "jf.jsp";
				permissionTokenMap.addUnprotectedPermissionToken(PermissionTokenMap.buildResourceToken(isanypage) , this.getSystemid(), token);
				if(publicItem.getAuthorResources() != null)
				{
					List<ResourceToken> authorResources = publicItem.getAuthorResources();
					for(ResourceToken authorResource:authorResources)
					{
						token = new PermissionToken("column", "publicItem",
								"visible");
						permissionTokenMap.addUnprotectedPermissionToken(authorResource,this.getSystemid(), token);
					}
				}
			}
		}
		return permissionTokenMap ;
	}
	private void buildFramework(FrameworkConfiguration config) throws Exception
	{	
			
			
			config.loadConfiguration();
			this.messagesourcefiles = config.getMessagesourcefiles();
			initMessageSource();
			modules = config.getModules();
			indexs = config.getIndexs();
			this.menus = config.getMenus();
			this.indexByIds = config.getIndexByIds();
//			framesetAttributeOfItem = Collections
//					.synchronizedMap(new HashMap());
			framesetAttributeOfItem = new HashMap();
			this.items = config.getItems();
			this.defaultItem = config.getDefaultItem();
			description = config.getDescription();
			this.top_height = config.getTop_height();
			this.left_width = config.getLeft_width();
			this.navigator_width = config.getNavigator_width();
			this.workspace_height = config.getWorkspace_height();
			this.showhidden = config.getShowhidden();
			showhidden_width = config.getShowhidden_width();
			publicItem = config.getPublicItem();
			global_target = config.getGlobal_target();
			this.languages = config.getLanguages();
			this.localeDescriptions = config.getLocaleDescriptions();
			inited = true;
			buildPermissionTokenMap();
			this.showrootleftmenu = config.isShowrootleftmenu();
			
			

		
	}
	
	/**
	 * reinit
	 * 
	 */
	public synchronized void reinit() {
		reset();
		
		FrameworkConfiguration config = new FrameworkConfiguration(configFile,!menu_folder.equals(""),this.systemid );
		try {
			if (this.frameworkmeta != null)
				config.setOwnersubsystem(frameworkmeta);
			config.setLanguages(languages);
			
			buildFramework( config);
			if(this.systemid == null || this.systemid.equals("module"))
			{
				this.rootsystem = new SubSystem();
				rootsystem.setSuccessRedirect(config.getSuccessRedirect());
				rootsystem.setLogoutredirect(config.getLogoutredirect());
				rootsystem.setId("module");
				rootsystem.setModule("module.xml");
			}
			Map temp_sys = config.getSubsystems();
			this.subsystemList = config.getSubsystemList();
			synSubsystems(temp_sys);
			this.monitor(this.configFile);
		} catch (Exception ex) {
			log.error(ex.getMessage(), ex);
		}
	}
	public void init()
	{
		init((String)null);
	}
	public void init(String configFile) {
		if (!inited) {
			this.systemid = defaultSystemID;
			log.debug("Load subsystem[" + configFile + "]");
			if (configFile == null)
				configFile = ConfigManager.getInstance().getMenus();
			this.configFile = configFile;
			setParentPath(configFile);
			boolean isFile = false;
	                if(!menu_folder.equals(""))
	                {
	                    isFile = true;
	                    configFile = this.getFilePath(configFile);
	                    this.configFile = configFile;
	                }
			FrameworkConfiguration config = new FrameworkConfiguration(configFile,isFile,systemid);

			try {
//				config.loadConfiguration();
//				this.messagesourcefiles = config.getMessagesourcefiles();
//				initMessageSource();
//				modules = config.getModules();
//				indexs = config.getIndexs();
//				this.indexByIds = config.getIndexByIds();
////				framesetAttributeOfItem = Collections
////						.synchronizedMap(new HashMap());
//				framesetAttributeOfItem = new HashMap();
//				this.items = config.getItems();
//				this.defaultItem = config.getDefaultItem();
//				description = config.getDescription();
//				this.top_height = config.getTop_height();
//				this.left_width = config.getLeft_width();
//				this.navigator_width = config.getNavigator_width();
//				this.workspace_height = config.getWorkspace_height();
//				this.showhidden = config.getShowhidden();
//				showhidden_width = config.getShowhidden_width();
//				publicItem = config.getPublicItem();
//				global_target = config.getGlobal_target();
//				inited = true;
//				this.subsystems = config.getSubsystems();
//				this.showrootleftmenu = config.isShowrootleftmenu();
//				
				buildFramework( config);
				this.rootsystem = new SubSystem();
				rootsystem.setSuccessRedirect(config.getSuccessRedirect());
				rootsystem.setLogoutredirect(config.getLogoutredirect());
				rootsystem.setId("module");
				rootsystem.setModule(this.configFile);
				this.subsystems = config.getSubsystems();
				
				
				this.subsystemList = config.getSubsystemList();
				initSubSystems();
				this.monitor(configFile);
			} catch (Exception ex) {
				log.error(ex.getMessage(), ex);
			}
			
		}
	}

	private void setParentPath(String configFile) {
		if (configFile == null || configFile.equals(""))
			return;
		int idx = configFile.lastIndexOf('/');
		if (idx == -1)
			idx = configFile.lastIndexOf('\\');
		if (idx == -1)
			return;
		parentPath = configFile.substring(0, idx);

	}

	public Map getIndexs() {
		init((String) null);

		return indexs;
	}
	
	public Map getIndexByIds() {
		init((String) null);
		return indexByIds;
	}

	public ModuleQueue getModules() {
		init((String) null);

		return modules;
	}
	
	public MenuItem getCurrentSystemMenu(String path) {
		
		
		Map map = getIndexs();
		if (map.isEmpty()) {
			return null;
		} else {
			Object object = map.get(path);
			MenuItem menuItem = (MenuItem) object;
			return menuItem;
		}
		// return (MenuItem)(getSubFramework(subsystem).getIndexs().get(path));
		// return (MenuItem) init.indexs.get(path);
	}

	public static MenuItem getMenu(String path) {
		String subsystem = getSubsystemFromPath(path);
		if (subsystem != null && subsystem.equals("module"))
			return (MenuItem) (init.getIndexs().get(path));
		Framework framework = getSubFramework(subsystem);
		Map map = framework.getIndexs();
		if (map.isEmpty()) {
			return null;
		} else {
			Object object = map.get(path);
			MenuItem menuItem = (MenuItem) object;
			return menuItem;
		}
		// return (MenuItem)(getSubFramework(subsystem).getIndexs().get(path));
		// return (MenuItem) init.indexs.get(path);
	}
	
	public MenuItem getMenuByPath(String path) {
		return (MenuItem)this.indexs.get(path);
		// return (MenuItem)(getSubFramework(subsystem).getIndexs().get(path));
		// return (MenuItem) init.indexs.get(path);
	}
	public MenuItem getMenuByID(String id) {
		return (MenuItem)this.indexByIds.get(id);
		// return (MenuItem)(getSubFramework(subsystem).getIndexs().get(path));
		// return (MenuItem) init.indexs.get(path);
	}

	public Item getItem(String path) {

		return (Item) getMenu(path);
	}

	public Module getModule(String path) {
		MenuItem menuItem = this.getMenu(path);
		Module module = (Module) menuItem;
		return module;
	}

	public Item getDefaultItem() {
		return this.defaultItem;
	}

	 

	 
	
	
	public static String getNavigatorToolbar(Item item,AccessControl control){
		if(!item.hasNavigatorToolbarVariables()){
			return item.getNavigatorToolbar();
		}else{
			return combinationItemUrlStruction(item.getNavigatorToolbarItemUrlStruction(), control);
		}
	}
	
	public static String getNavigatorContent(Item item,AccessControl control){
		if(!item.hasNavigatorContentVariables()){
			return item.getNavigatorContent();
		}else{
			return combinationItemUrlStruction(item.getNavigatorContentItemUrlStruction(), control);
		}
	}
	
	public static String getStatusContent(Item item,AccessControl control){
		if(!item.hasStatusContentVariables())
			return item.getStatusContent();
		else{
			return combinationItemUrlStruction(item.getStatusContentItemUrlStruction(), control);
		}
	}
	
	public static String getStatusToolbar(Item item,AccessControl control){
		if(!item.hasStatusToolbarVariables()){
			return item.getStatusToolbar();
		}else{
			return combinationItemUrlStruction(item.getStatusToolbarItemUrlStruction(), control);
		}
	}
	
	public static String getWorkspaceContent(Item item,AccessControlInf control) {
		if(!item.hasWorkspaceContentVariables())
			return item.getWorkspaceContent();
		else
		{
			return combinationItemUrlStruction(item.getWorkspaceContentItemUrlStruction(), control);
		}
	}
	
	public static String getWorkspaceToolbar(Item item,AccessControl control) {
		if(!item.hasWorkspaceToolbarVariables())
			return item.getWorkspaceToolbar();
		else
		{
			return combinationItemUrlStruction(item.getWorkspaceToolbarItemUrlStruction(), control);
		}
	}
	

	public static String combinationItemUrlStruction(
			ItemUrlStruction itemUrlStruction,AccessControlInf control) {

		StringBuffer url = new StringBuffer();
		List<String> tokens = itemUrlStruction.getTokens();

		List<Variable> variables = itemUrlStruction.getVariables();
		// Iterator iterator = map.entrySet().iterator();
		Variable var = null;
		String varvalue = null;
		if(control == null)
			log.debug("combinationItemUrlStruction:control == null");
		for (int i = 0; i < tokens.size(); i++) {
			url.append(tokens.get(i));
			if (i < variables.size()) {
				var = variables.get(i);				
				if(control != null)
					varvalue = control.getUserAttribute(var.getVariableName());
				if (varvalue != null)
					url.append(varvalue);
			}
		}

		return url.toString();

	}
	

	public static String getLeft(Item item,AccessControl control){
		if(!item.hasLeftVaribale()){
			return item.getLeft();
		}else{
			return combinationItemUrlStruction(item.getLeftItemUrlStruction(), control);
		}
	}
	
	public static String getTop(Item item,AccessControl control){
		if(!item.hasTopVaribale()){
			return item.getTop();
		}else{
			return combinationItemUrlStruction(item.getTopItemUrlStruction(), control);
		}
	}
	 

	/**
	 * ;jsessionid=397BB3656E2A12A96CE3F16E0A89C607
	 * @param url
	 * @param sessionid
	 * @return
	 */
	public static String getUrl(String url,String sessionid) {
		if (url == null) {
			return "";
		}
		
		String t_url = url;
		int i = url.indexOf("?");
//		int j = url.indexOf(";jsessionid=");
		int j = 1;
		if(j != -1 || sessionid == null)
		{

			if (i != -1) {
				t_url = url + "&";
			} else {
				t_url = url + "?";
			}
		}
		else
		{
			if (i != -1) {
				StringBuffer sb = new StringBuffer();
				sb.append(t_url);
				sb.insert(i, ";jsessionid=" + sessionid);
				sb.append("&");
				t_url =  sb.toString();
			} else {
				t_url = url + ";jsessionid=" + sessionid;
				t_url = url + "?";
			}
		}

		return t_url;
	}

	
	

	/**
	 * 扫描子系统模块信息的变化，并进行相应的处理 如果只是信息发生变化模块文件和路径没有发生变化，更新相关的信息即可
	 * 如果文件被删除则清除该模块的framework对象，停止该模块的实时监控daemon线程
	 * 
	 * @param temp_sys
	 */
	private void synSubsystems(Map temp_sys) {
		// 如果之前没有子系统模块，则直接加载新增的子系统模块
		if (this.subsystems == null ) {
			if(temp_sys != null && !temp_sys.isEmpty())
			{
				this.subsystems = temp_sys;
				this.initSubSystems();
			}
		} else {
			if(subsystemFrameworks == null)
				subsystemFrameworks = new HashMap();
			// 如果先前加载的子系统模块已经从系统中删除，则卸载这些子系统模块
			if (temp_sys == null || temp_sys.isEmpty() ) {
				this.subsystems.clear();
				if(subsystemFrameworks != null && subsystemFrameworks.size() > 0)
				{
					Set set = this.subsystemFrameworks.entrySet();
					for (Iterator it = set.iterator(); it.hasNext();) {
						Map.Entry entry = (Map.Entry) it.next();
						Framework framework = (Framework) entry.getValue();
						log.debug("uninstalling module[" + framework + "]");
						framework.stop();
						framework = null;
					}
					this.subsystemFrameworks.clear();
				}
			} 
			else // 如果先前加载了一些模块，检测到系统有变动则对比更新变动的部分
			{

				Set o_set = this.subsystems.entrySet();
				Set n_set = temp_sys.entrySet();

				List del = new ArrayList();

				List add = new ArrayList();
				// List update = new ArrayList();
				// 比较模块原来包含的子模块是否包括载新的子模块中，如果包括则判断是否其他信息是否相同（name,module.xml路径，baseUri）
				// 然后再进行相应的处理
				for (Iterator it = o_set.iterator(); it.hasNext();) {
					Map.Entry entry = (Map.Entry) it.next();
					SubSystem o_sub = (SubSystem) entry.getValue();
					// 如果包含
					if (temp_sys.get(o_sub.getId()) != null) {
						SubSystem temp = (SubSystem) temp_sys
								.get(o_sub.getId());
						// 信息一致，直接处理下一个文件
						if (o_sub.equals(temp))
							continue;

						else {
							// 如果模块一致，则更新相关信息
							if (o_sub.getModule().equals(temp.getModule())) {
								o_sub.setBaseuri(temp.getBaseuri());
								o_sub.setName(temp.getName());
								o_sub.setLocaleNames(temp.getLocaleNames());
							}
							// 不一致，更新相关信息，并且重新加载模块菜单信息
							else {
								o_sub.setBaseuri(temp.getBaseuri());
								o_sub.setName(temp.getName());
								o_sub.setLocaleNames(temp.getLocaleNames());
								o_sub.setModule(temp.getModule());
								Framework framework = (Framework) this.subsystemFrameworks
										.get(o_sub.getId());
								framework.setConfigFile(temp.getModule());
								framework.stop();
								framework.reinit();
							}
						}
					}
					// 否则将对应的模块加入删除队列，等待下一步处理
					else {
						del.add(o_sub);
					}
				}

				// 判断哪些是新增的模块
				for (Iterator it = n_set.iterator(); it.hasNext();) {
					Map.Entry entry = (Map.Entry) it.next();
					SubSystem n_sub = (SubSystem) entry.getValue();
					if (subsystems.get(n_sub.getId()) != null)
						continue;
					else
						add.add(n_sub);
				}

				for (int i = 0; i < del.size(); i++) {
					SubSystem n_sub = (SubSystem) del.get(i);
					this.subsystems.remove(n_sub.getId());
					Framework framework = (Framework) this.subsystemFrameworks
							.remove(n_sub.getId());
					framework.stop();
				}

				for (int i = 0; i < add.size(); i++) {
					SubSystem n_sub = (SubSystem) add.get(i);
					this.subsystems.put(n_sub.getId(), n_sub);
					Framework subframework = new Framework();
					subframework.setLanguages(this.languages);
					subframework.init(n_sub);
					this.subsystemFrameworks.put(n_sub.getId(), subframework);
				}
			}
		}

	}

	public String toString() {
		return "";
	}

	/**
	 * 停止对系统模块的模块文件和该模块中包含的子模块文件进行监控
	 * 
	 */
	private void stop() {
//		this.listen.stopped();
		listen.removeFile(this.configFile);
		//listen.interrupt();
//		listen.stop();
		if(log != null)
			log.debug("uninstalling module[" + this + "]");
		
		this.messagesource = null;
		this.messagesourcefiles = null;
		if (this.subsystemFrameworks != null && !subsystemFrameworks.isEmpty()) {
			if(subsystems != null)
				this.subsystems.clear();
			Set set = this.subsystemFrameworks.entrySet();
			for (Iterator it = set.iterator(); it.hasNext();) {

				Map.Entry entry = (Map.Entry) it.next();
				Framework framework = (Framework) entry.getValue();
				if(log != null)
					log.debug("uninstalling module[" + framework + "]");
				framework.stop();
			}
			this.subsystemFrameworks.clear();
			this.subsystemList.clear();
		}
	}

	public void reset() {
		if(modules != null)
			this.modules.clear();
		if(framesetAttributeOfItem != null)
			framesetAttributeOfItem.clear();

		this.modules = null;
		if(indexs != null)
			indexs.clear();
		indexs = null;
		if(indexByIds != null)
			this.indexByIds.clear();
		this.indexByIds = null;
		this.defaultItem = null;
		this.publicItem = null;
		if(root != null)
			this.root.setSubSystem(null);
		this.messagesource = null;
		this.messagesourcefiles = null;
		// this.frameworkmeta = null;
		// subsystemFrameworks.clear();
		// this.subsystems.clear();
	}

	/**
	 * @return Returns the publicItem.
	 */
	public Item getPublicItem() {
		return publicItem;
	}

	public ItemQueue getItems() {
		return items;
	}

	public static String getSuperMenu(String subsystem) {
		if (subsystem == null || subsystem.trim().equals(""))
			return "module::menu://sysmenu$root";
		else
			return subsystem + "::menu://sysmenu$root";
	}

	public static String getSubsystemFromPath(String path) {
		String ret = "module";
		if (path == null || path.equals(""))
			return ret;
		else {
			int idx = path.indexOf("::");
			if (idx == -1)
				return ret;
			else
				return path.substring(0, idx);
		}
	}

	public ItemQueue getSubItems(String parentPath) {
		String subsystem = getSubsystemFromPath(parentPath);
		if ((parentPath == null) || parentPath.equals(getSuperMenu(subsystem))) {
			return this.getItems();
		} else {
			MenuItem module = this.getMenu(parentPath);

			if ((module != null) && module instanceof Module) {
				return ((Module) module).getItems();
			} else {
				return new ItemQueue();
			}
		}
	}

	public ModuleQueue getSubModules(String parentPath) {
		String subsystem = getSubsystemFromPath(parentPath);
		if ((parentPath == null) || parentPath.equals(getSuperMenu(subsystem))) {
			return this.getModules();
		} else {
			MenuItem module = this.getMenu(parentPath);

			if ((module != null) && module instanceof Module) {
				return ((Module) module).getSubModules();
			} else {
				return new ModuleQueue();
			}
		}
	}

	  

	public String getDescription() {
		return description;
	}
	
	public String getDescription(HttpServletRequest request) {
		if(this.localeDescriptions == null)
			return description;
    	Locale locale = RequestContextUtils.getRequestContextLocal(request);
    	String temp = this.localeDescriptions.get(locale);
    	if(temp == null)
    		return description;
    	return temp;
		
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public String getShowhidden() {
		return showhidden;
	}

	public void _destroy() {
		if(this.indexByIds != null)
		{
			indexByIds.clear();
			indexByIds = null;
			
		}
		this.framesetAttributeOfItem = null;
		this.frameworkmeta = null;
		this.indexs = null;
		this.items = null;
		this.languages = null;
		this.localeDescriptions = null;
		this.messagesource = null;
		this.modules =null;
		this.publicItem = null;
		this.root = null;
		this.servletContext = null;
		this.subsystemFrameworks = null;
		this.subsystems = null;
		this.subsystemList = null;
	}

	public String getConfigFile() {
		return configFile;
	}

	public void setConfigFile(String configFile) {
		this.configFile = configFile;
	}

	public Root getRoot() {
		return root;
	}

	public Map<String,SubSystem> getSubsystems() {
		return subsystems;
	}

	// public void setRequest(HttpServletRequest request)
	// {
	// this.request = request;
	//		
	// }

	public String getGlobal_target() {
		return global_target;
	}

	public void setRequest(HttpServletRequest req) {
		// TODO Auto-generated method stub

	}

	public void evaluateFrameWork(Item publicItem2, int type, PrintWriter out) {
		// TODO Auto-generated method stub

	}
	
	public String getTemplate() {
		return template;
	}
	public void setTemplate(String template) {
		if(template == null || template.equals(""))
			this.template = this.frameworkmeta.getId();
		else
			this.template = template;
	}
  

	public MessageSource getMessagesource() {
		return messagesource;
	}

	public void setMessagesource(MessageSource messagesource) {
		this.messagesource = messagesource;
	}
//	private String messagesourcefiles;
	public String getMessagesourcefiles() {
		return messagesourcefiles;
	}

	public void setMessagesourcefiles(String messagesourcefiles) {
		this.messagesourcefiles = messagesourcefiles;
	}

	@Override
	public String getMessage(String code, Object[] args, String defaultMessage,
			Locale locale) {
		if(this.messagesource != null)
			return this.messagesource.getMessage(code,args,defaultMessage,locale);
		return code;
	}

	@Override
	public String getMessage(String code, Object[] args, Locale locale)
			throws NoSuchMessageException {
		if(this.messagesource != null)
			return this.messagesource.getMessage(code,args,locale);
		return code;
	}

	@Override
	public String getMessage(String code) throws NoSuchMessageException {
		
		if(this.messagesource != null)
			return this.messagesource.getMessage(code);
		return code;
	}

	@Override
	public String getMessage(String code, Locale locale)
			throws NoSuchMessageException {
		if(this.messagesource != null)
			return this.messagesource.getMessage(code,locale);
		return code;
	}

	@Override
	public String getMessage(MessageSourceResolvable resolvable, Locale locale)
			throws NoSuchMessageException {
		if(this.messagesource != null)
			return this.messagesource.getMessage(resolvable,locale);
		
		return resolvable.getDefaultMessage();
	}
	public String getSystemid() {
		return systemid;
	}
	
	public static String getSystemName(String systemid)
	{
		return Framework.getSubFramework(systemid).getDescription();
	}
	
	public static String getSystemName(String systemid,HttpServletRequest request)
	{
		return Framework.getSubFramework(systemid).getDescription(request);
	}
	
	public static SubSystem getSubSystem(String systemid)
	{
		
		return Framework.getInstance()._getSubSystem( systemid);
	}
	private SubSystem _getSubSystem(String systemid) {
		if(systemid == null || systemid.equals("module"))
			return this.rootsystem;
		if(this.subsystems != null)
		{
			return subsystems.get(systemid);
		}
		
		return null;
		
	}

	public Map<Locale, String> getLocaleDescriptions() {
		return localeDescriptions;
	}

	public void setLocaleDescriptions(Map<Locale, String> localeDescriptions) {
		this.localeDescriptions = localeDescriptions;
	}
	public Map<String,Locale> getLanguages() {
		return languages;
	}
	public void setLanguages(Map<String, Locale> languages) {
		this.languages = languages;
	}

	public ServletContext getServletContext() {
		return servletContext;
	}

	public void setServletContext(ServletContext servletContext) {
		this.servletContext = servletContext;
	}

	public SubSystem getSubsystem(String systemid) {
//		return this.subsystems != null ?(SubSystem)this.subsystems.get(systemid):null;
		return getSubSystem( systemid);
	}

	public String getLeft_width() {
		return left_width;
	}

	public String getTop_height() {
		return top_height;
	}

	public String getShowhidden_width() {
		return showhidden_width;
	}

	public MenuQueue getMenus() {
		return menus;
	}

	 

	public List<SubSystem> getSubsystemList() {
		return subsystemList;
	}

	public SubSystem getRootsystem() {
		return rootsystem;
	}

	public SubSystem getFrameworkmeta() {
		return frameworkmeta;
	}

	

	
	

	

}
