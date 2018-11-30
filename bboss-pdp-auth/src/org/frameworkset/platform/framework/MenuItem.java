package org.frameworkset.platform.framework;

import javax.servlet.http.HttpServletRequest;
import java.util.Map;


/**
 * <p>Title: </p>
 *
 * <p>Description: </p>
 *
 * <p>Copyright: Copyright (c) 2005</p>
 *
 * <p>Company: bbossgroups</p>
 *
 * @author biaoping.yin
 * @version 1.0
 */
public interface MenuItem  {
    public String getParentPath();
    public String getId() ;
    public MenuItem getParent();
    public String getUrl();

    public String getName(HttpServletRequest request) ;
    public String getName();

    public String getMouseclickimg(HttpServletRequest request) ;

    public String getMouseoutimg(HttpServletRequest request) ;

    public String getMouseoverimg(HttpServletRequest request) ;
    public String getHeadimg(HttpServletRequest request) ;

	public void setHeadimg(String headimg) ;
	 public String getMouseupimg(HttpServletRequest request) ;
//	 public String getName(HttpServletRequest pageContext);

    public String getTitle(HttpServletRequest request) ;

    public String getPath();
    
    /**获取节点编码*/
    public int getCode();

   
    public boolean isUsed();
    public boolean isMain();
    public SubSystem getSubSystem();
    public String getTarget();
    /**
     * 判断界面是否直接显示item中的workspace-content地址,true显示,false不显示,缺省为false
     * @return
     */
    public boolean isShowpage();
    
    public String getArea();
    
    public boolean isShowleftmenu() ;

	public void setShowleftmenu(boolean showleftmenu) ;
	public Map<String, String> getExtendAttributes() ;
	public void setExtendAttributes(Map<String, String> extendAttributes) ;
	
	public String getOption();
	public void setOption(String option);

	String getStringExtendAttribute(String icon, String s);
	public boolean isRoot();
	public boolean isTopLevel();
}
