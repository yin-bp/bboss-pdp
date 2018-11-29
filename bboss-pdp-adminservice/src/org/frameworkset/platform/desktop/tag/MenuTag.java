package org.frameworkset.platform.desktop.tag;

import com.frameworkset.common.tag.BaseTag;
import com.frameworkset.util.StringUtil;
import org.frameworkset.platform.framework.*;
import org.frameworkset.platform.security.AccessControl;

import javax.servlet.jsp.JspException;
import java.io.IOException;

/**
 *
 * @author yinbp
 *
 */
public class MenuTag extends BaseTag {

	private static String dashboard3_header = "<ul class=\"page-sidebar-menu  page-header-fixed page-sidebar-menu-hover-submenu \" data-keep-expanded=\"false\" data-auto-scroll=\"true\" data-slide-speed=\"200\" style=\"padding-top: 0px\">"; 
	private static String dashboard1_header = "<ul class=\"page-sidebar-menu  \" data-keep-expanded=\"false\" data-auto-scroll=\"true\" data-slide-speed=\"200\" style=\"padding-top: 0px\">"; 
	
	public static final String personcenter_name = "personal_center";
	
	
	
	private boolean enableindex = true;
	private int level = 3;
	
	private void header(AccessControl control,MenuHelper menuHelper,StringBuilder datas,String theme )
	{
		 
		datas.append(theme == null || theme.equals("admin_3")?dashboard3_header:dashboard1_header);	
		datas.append("<!-- DOC: To remove the sidebar toggler from the sidebar you just need to completely remove the below \"sidebar-toggler-wrapper\" LI element -->");
		datas.append("<li class=\"sidebar-toggler-wrapper hide\">")	;
		datas.append("    <!-- BEGIN SIDEBAR TOGGLER BUTTON -->");
		datas.append("    <div class=\"sidebar-toggler\">");
		datas.append("        <span></span>");
		datas.append("    </div>");
		datas.append("    <!-- END SIDEBAR TOGGLER BUTTON -->");
		datas.append("</li>");
//		datas.append("<!-- DOC: To remove the search box from the sidebar you just need to completely remove the below \"sidebar-search-wrapper\" LI element -->");
//		datas.append("<li class=\"sidebar-search-wrapper\">");
//		datas.append("    <!-- BEGIN RESPONSIVE QUICK SEARCH FORM -->");
//		datas.append("    <!-- DOC: Apply \"sidebar-search-bordered\" class the below search form to have bordered search box -->");
//		datas.append("    <!-- DOC: Apply \"sidebar-search-bordered sidebar-search-solid\" class the below search form to have bordered & solid search box -->");
//		datas.append("    <form class=\"sidebar-search  \" action=\"page_general_search_3.html\" method=\"POST\">");
//		datas.append("        <a href=\"javascript:;\" class=\"remove\">");
//		datas.append("             <i class=\"icon-close\"></i>");
//		datas.append("         </a>");
//		datas.append("         <div class=\"input-group\">");
//		datas.append("            <input type=\"text\" class=\"form-control\" placeholder=\"Search...\">");
//		datas.append("            <span class=\"input-group-btn\">");
//		datas.append("                 <a href=\"javascript:;\" class=\"btn submit\">");
//		datas.append("                      <i class=\"icon-magnifier\"></i>");
//		datas.append("               </a>");
//		datas.append("           </span>");
//		datas.append("        </div>");
//		datas.append("    </form>");
//		datas.append("    <!-- END RESPONSIVE QUICK SEARCH FORM -->");
//		datas.append(" </li>"); 
	}
	
	private void renderItem(String contextpath,AccessControl control,
							MenuHelper menuHelper,Item item,boolean selected,StringBuilder datas,boolean isfirst,String selectedmenuid)
	{ 
		String selectedclass = "";
		if(selected)
		{
			if(!isfirst)
				selectedclass = "class=\"nav-item active open\"";
			else
			{
				selectedclass = "class=\"nav-item start active open\"";
			}
			 
		}	
		else
		{
			if(!isfirst) {
				if(selectedmenuid != null && selectedmenuid.equals(item.getId())){
					selectedclass = "class=\"nav-item active \"";
				}
				else{
					selectedclass = "class=\"nav-item \"";
				}

			}

			else
			{
				if(selectedmenuid != null && selectedmenuid.equals(item.getId())){
					selectedclass = "class=\"nav-item start active \"";
				}
				else{
					selectedclass = "class=\"nav-item start \"";
				}
			}

			 
		} 
	
		String mname = item.getName(request);
		String icon = item.getStringExtendAttribute("icon","icon-settings");
		String fullpageload = item.getStringExtendAttribute("fullpageload","false");
		if(fullpageload.equals("true"))
		{
			datas.append("<li ").append(selectedclass).append(">")
			 .append("<a id=\"left__").append(item.getId()).append("\"  href=\"javascript:void(0);\" onclick=\"javascript:DesktopMenus.gotomenu('")
					.append(item.getId()).append("',this,event,false)\" class=\"nav-link \">");

		}
		else
		{
			String iframe = item.getStringExtendAttribute("iframe");
			String url =  null;
			if(iframe == null || !iframe.equals("true"))
			{
				url =  MenuHelper.getRealUrl(contextpath, Framework.getWorkspaceContent(item,control),MenuHelper.menupath_menuid,item.getId());
			}
			else
			{
			
				url =  MenuHelper.getRealUrl(contextpath, Framework.getWorkspaceContent(item,control),MenuHelper.menupath_menuid,item.getId());
				url =  MenuHelper.getRealUrl(contextpath, MenuHelper.iframeurl,MenuHelper.selecturl,StringUtil.urlencode(url,"UTF-8"));
			}
			datas.append("<li ").append(selectedclass).append(">")
				 .append("<a href=\"javascript:void(0);\" onclick=\"DesktopMenus.gotoworkspace('','").append(url)
				 .append("','").append(item.getId()).append("',false,this)\" class=\"nav-link \">");
		}		 
		if(icon != null && !icon.equals(""))
		{
			 datas.append("    <i class=\"").append(icon).append("\"></i>");
		}
		datas.append("    <span class=\"title\">").append(mname).append("</span>");
		if(selected)
		{
			 datas.append("   <span class=\"selected\"></span>");			 
		}
		
		datas.append("</a>")
			 .append("</li>");
	}
	
	private void renderNosonModule(String contextpath,AccessControl control,MenuHelper menuHelper,Module item,boolean selected,StringBuilder datas,boolean isfirst)
	{ 
		String selectedclass = "";
		if(selected)
		{
			if(!isfirst)
				selectedclass = "class=\"nav-item active open\"";
			else
			{
				selectedclass = "class=\"nav-item start active open\"";
			}
			 
		}	
		else
		{
			if(!isfirst)
				selectedclass = "class=\"nav-item \"";
			else
			{
				selectedclass = "class=\"nav-item start \"";
			}
			 
		} 
	
		String mname = item.getName(request);
		String icon = item.getStringExtendAttribute("icon","icon-settings");
		String fullpageload = item.getStringExtendAttribute("fullpageload","false");
		if(fullpageload.equals("true")){
			//		String iframe = item.getStringExtendAttribute("iframe");
			String url =  item.getUrl();
	//		if(iframe == null || !iframe.equals("true"))
	//		{
	//			url =  MenuHelper.getModuleUrl(item, contextpath,  control);
	//		}
	//		else
	//		{
	//			url = MenuHelper.getModuleUrl(item, contextpath,  control);
	//			url =  MenuHelper.getRealUrl(contextpath, MenuHelper.iframeurl,MenuHelper.selecturl,StringUtil.urlencode(url,"UTF-8"));
	//		}
			if(url != null && !item.isUsesubpermission())
				datas.append("<li ").append(selectedclass).append(">")
					 .append("<a id=\"left__").append(item.getId())
						.append("\" href=\"javascript:void(0);\" onclick=\"javascript:DesktopMenus.gotomenu('").append(item.getId())
						.append("',this,event,false)\" class=\"nav-link \">");
			else
			{
				datas.append("<li ").append(selectedclass).append(">")
				 .append("<a id=\"left__").append(item.getId()).append("\"  href=\"javascript:void(0);\" hasurl=\"false\" class=\"nav-link \">");
			}
		}
		else
		{
			String iframe = item.getStringExtendAttribute("iframe");
			String url =  null;
			if(iframe == null || !iframe.equals("true"))
			{
				url =  MenuHelper.getModuleUrl(item, contextpath,  control);
			}
			else
			{
				url = MenuHelper.getModuleUrl(item, contextpath,  control);
				url =  MenuHelper.getRealUrl(contextpath, MenuHelper.iframeurl,MenuHelper.selecturl,StringUtil.urlencode(url,"UTF-8"));
			}
			if(url != null && !item.isUsesubpermission())
				datas.append("<li ").append(selectedclass).append(">")
					 .append("<a href=\"javascript:void(0);\" onclick=\"DesktopMenus.gotoworkspace('','").append(url)
					 .append("','").append(item.getId()).append("',false,this)\" class=\"nav-link \">");
			else
			{
				datas.append("<li ").append(selectedclass).append(">")
				 .append("<a href=\"javascript:void(0);\" hasurl=\"false\" class=\"nav-link \">");
			}
		}
		if(icon != null && !icon.equals(""))
		{
			 datas.append("    <i class=\"").append(icon).append("\"></i>");
		}
		datas.append("    <span class=\"title\">").append(mname).append("</span>");
		if(selected)
		{
			 datas.append("   <span class=\"selected\"></span>");
			 
		}
		
		datas.append("</a>")
			 .append("</li>");
	}
	
	 
	
	private void renderModule(String contextpath,AccessControl control,MenuHelper menuHelper,
							  Module item,boolean selected,StringBuilder datas,boolean isfirst,
							  int current_level,String theme,String selectedmenuid)
	{ 
		String selectedclass = "";
		if(selected)
		{
			if(!isfirst)
				selectedclass = "class=\"nav-item active open\"";
			else
			{
				selectedclass = "class=\"nav-item start active open\"";
			}
			 
		}	
		else
		{
			if(!isfirst)
				selectedclass = "class=\"nav-item \"";
			else
			{
				selectedclass = "class=\"nav-item start \"";
			}
			 
		} 
		String icon = item.getStringExtendAttribute("icon","icon-diamond");
		
		String mname = item.getName(request);
		datas.append("<li ").append(selectedclass).append(">");
		MenuQueue menus = item.getMenus();
		if(item.getUrl() == null || item.getUrl().equals("") || item.isUsesubpermission())
			datas.append("<a id=\"left__").append(item.getId()).append("\" href=\"javascript:;\" hasurl=\"false\" class=\"nav-link nav-toggle\">");
		else
		{
//			String url = MenuHelper.getModuleUrl(item, contextpath,  control);
			String fullpageload = item.getStringExtendAttribute("fullpageload","false");
			if(!fullpageload.equals("true")){
				String iframe = item.getStringExtendAttribute("iframe");
				String url =  null;
				if(iframe == null || !iframe.equals("true"))
				{
					url =  MenuHelper.getModuleUrl(item, contextpath,  control);
				}
				else
				{
					url = MenuHelper.getModuleUrl(item, contextpath,  control);
					url =  MenuHelper.getRealUrl(contextpath, MenuHelper.iframeurl,MenuHelper.selecturl,StringUtil.urlencode(url,"UTF-8"));
				}
				if(theme == null || theme.equals("admin_3"))
					datas.append("<a href=\"javascript:void(0);\" onclick=\"DesktopMenus.gotoworkspace('','").append(url)
					 .append("','").append(item.getId()).append("',false,this)\" class=\"nav-link nav-toggle\">");
				else
				{
					//ondblclick
					datas.append("<a href=\"javascript:void(0);\" onclick=\"DesktopMenus.gotoworkspace('','").append(url)
					 .append("','").append(item.getId()).append("',false,this)\" class=\"nav-link nav-toggle\">");
				 
				}
			}
			else
			{
				//String iframe = item.getStringExtendAttribute("iframe");
				String url =  item.getUrl();
	//			if(iframe == null || !iframe.equals("true"))
	//			{
	//				url =  MenuHelper.getModuleUrl(item, contextpath,  control);
	//			}
	//			else
	//			{
	//				url = MenuHelper.getModuleUrl(item, contextpath,  control);
	//				url =  MenuHelper.getRealUrl(contextpath, MenuHelper.iframeurl,MenuHelper.selecturl,StringUtil.urlencode(url,"UTF-8"));
	//			}
				if(theme == null || theme.equals("admin_3"))
					datas.append("<a id=\"left__").append(item.getId())
							.append("\"  href=\"javascript:void(0);\" onclick=\"javascript:DesktopMenus.gotomenu('")
							.append(item.getId()).append("',this,event,false)\" class=\"nav-link nav-toggle\">");
				else
				{
					//ondblclick
					datas.append("<a id=\"left__").append(item.getId()).append("\"  href=\"javascript:void(0);\" onclick=\"javascript:DesktopMenus.gotomenu('")
							.append(item.getId()).append("',this,event,false)\" class=\"nav-link nav-toggle\">");
				 
				}
			}
		}
		
		if(icon != null && !icon.equals(""))
		{
			 datas.append("    <i class=\"").append(icon).append("\"></i>");
		}
		datas.append("    <span class=\"title\">").append(mname).append("</span>"); 
		if(selected)
		{
			 datas.append("   <span class=\"selected\"></span>").append("    <span class=\"arrow open\"></span>");
		}
		else
		{
			datas.append("    <span class=\"arrow  \"></span>");
		}
			
		
		datas.append("</a>")
			 .append("<ul class=\"sub-menu\">");
		
		for(int i = 0; menus != null && i < menus.size() ; i ++)
		{
			MenuItem mi = menus.getMenuItem(i);
			if(!mi.isUsed())
				continue;
			if(mi instanceof Item)
			{
				this.renderItem(contextpath, control, menuHelper, (Item)mi, false, datas, false,selectedmenuid);
			}
			else
			{
				Module module = (Module)mi;
				if(module.getMenus() != null && module.getMenus().size() > 0)
					renderModule(  contextpath,  control,  menuHelper,module,false,datas,false,0, theme,selectedmenuid);
				else
				{
					renderNosonModule(  contextpath,  control,  menuHelper,module,false,datas,false);
					
				}
				
			}

		}
			 
		datas.append("   </ul>")
			 .append(" </li>  ");
		
	}

	private void allMenus(AccessControl control,MenuHelper menuHelper,String selectRootPath,StringBuilder datas,String theme,String selectedmenuid){

		try{

			String contextpath = request.getContextPath();
			Item publicitem = menuHelper.getPublicItem();
			boolean hasputfirst = false;
			if(this.enableindex && publicitem != null && publicitem.isMain())
			{
//				datas.append(" <li class=\"heading\">")
//				.append("    <h3 class=\"uppercase\">Features</h3>")
//				.append("</li>");
				boolean selected = false;
				if(selectRootPath == null)
				{
					selected = true;
					hasputfirst = true;
				}
				renderItem(  contextpath,  control,  menuHelper,publicitem,  selected,  datas,true,selectedmenuid);

			}


			MenuQueue menus = menuHelper.getMenus();
			for (int i = 0; menus != null && i < menus.size(); i++) {
				MenuItem mi = menus.getMenuItem(i);
				if (!mi.isUsed()) {
					continue;
				}
				boolean selected = selectRootPath != null && selectRootPath.equals(mi.getPath());
				if(!hasputfirst)
				{
					if(mi instanceof Item)
					{
						this.renderItem(contextpath, control, menuHelper, (Item)mi,
								selected,
								datas, true,selectedmenuid);
					}
					else
					{
						Module module = (Module) mi;
						if(module.getMenus() != null && module.getMenus().size() > 0)
							renderModule(  contextpath,  control,  menuHelper,module,
									selected,datas,true,0, theme,selectedmenuid);
						else
						{
							renderNosonModule(  contextpath,  control,  menuHelper,module,
									selected,datas,true);

						}
					}
					hasputfirst = true;
				}
				else
				{

					if(mi instanceof Item)
					{
						this.renderItem(contextpath, control, menuHelper, (Item)mi, selected, datas, false,selectedmenuid);
					}
					else
					{

						Module module = (Module) mi;
						if(module.getMenus() != null && module.getMenus().size() > 0)
							renderModule(  contextpath,  control,  menuHelper,module,
									selected,datas,false,0, theme,selectedmenuid);
						else
						{
							renderNosonModule(  contextpath,  control,  menuHelper,module,
									selected,datas,false);

						}

					}
				}


			}

		}
		catch(Exception e)
		{
			e.printStackTrace();
		}

	}

	private void selectMenus(AccessControl control,MenuHelper menuHelper,String selectRootPath,StringBuilder datas,String theme,String selectedmenuid){

		try{

			String contextpath = request.getContextPath();

			boolean hasputfirst = false;
			String selectParentPath = menuHelper.selectParentPath(selectedmenuid);
			MenuItem menuItem = menuHelper.getMenuById(selectedmenuid);
			if(menuItem instanceof Item && menuItem.isTopLevel()){
				this.renderItem(contextpath, control, menuHelper, (Item)menuItem,
						true,
						datas, true,selectedmenuid);
				return;
			}

			ModuleQueue menus = menuHelper.getSubModules(selectRootPath);
			for (int i = 0; menus != null && i < menus.size(); i++) {
				Module module = menus.getModule(i);
				if (!module.isUsed()) {
					continue;
				}
				boolean selected = selectParentPath != null && selectParentPath.equals(module.getPath());
				if(!hasputfirst)
				{


					if(module.getMenus() != null && module.getMenus().size() > 0)
						renderModule(  contextpath,  control,  menuHelper,module,
								selected,datas,true,0, theme,selectedmenuid);
					else
					{
						renderNosonModule(  contextpath,  control,  menuHelper,module,
								selected,datas,true);

					}

					hasputfirst = true;
				}
				else
				{



					if(module.getMenus() != null && module.getMenus().size() > 0)
						renderModule(  contextpath,  control,  menuHelper,module,
								selected,datas,false,0, theme,selectedmenuid);
					else
					{
						renderNosonModule(  contextpath,  control,  menuHelper,module,
								selected,datas,false);

					}


				}


			}

			ItemQueue items = menuHelper.getSubItems(selectRootPath);
			for (int i = 0; items != null && i < items.size(); i++) {
				Item item = items.getItem(i);
				if (!item.isUsed()) {
					continue;
				}
				boolean selected = selectParentPath != null && selectParentPath.equals(item.getPath());
				if(!hasputfirst)
				{

					this.renderItem(contextpath, control, menuHelper, item,
							selected,
							datas, true,selectedmenuid);


					hasputfirst = true;
				}
				else
				{
					this.renderItem(contextpath, control, menuHelper, item, selected, datas, false,selectedmenuid);

				}


			}

		}
		catch(Exception e)
		{
			e.printStackTrace();
		}

	}
	
	@Override
	public int doStartTag() throws JspException {	
		int ret = super.doStartTag();
		AccessControl control = AccessControl.getAccessControl();
		MenuHelper menuHelper =  MenuHelper.getMenuHelper(request);
		StringBuilder datas = new StringBuilder();
		String theme = control.getUserAttribute("theme");
		header(  control,  menuHelper,datas,theme );
		String selectedmenuid = request.getParameter(MenuHelper.menupath_menuid);//查找选择的菜单项path,待处理
		String selectRootPath = menuHelper.selectRootPath(selectedmenuid);
		String fromtop = request.getParameter("fromtop");
		//添加首页
		
//		String personcenter = Framework.getInstance(control.getCurrentSystemID()).getMessage("sany.pdp.module.personcenter", RequestContextUtils.getRequestContextLocal(request));
		
//		String selectedmenuid = request.getParameter(MenuHelper.selectedmodule);//查找选择的菜单项path,待处理

		if(fromtop == null || fromtop.equals("false") || selectRootPath == null) {
			allMenus(control, menuHelper, selectRootPath, datas, theme,selectedmenuid);
		}
		else{
			selectMenus(  control,  menuHelper,  selectRootPath,  datas,  theme,  selectedmenuid);

		}
		
		
		datas.append("</ul>");
		
		try {
//			System.out.println(datas.toString());
			this.out.write(datas.toString());
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		finally
		{
			datas = null;
		}
		return ret;
	}
	
	
	

	public int getLevel() {
		return level;
	}

	public void setLevel(int level) {   
		this.level = level;
	}

	@Override
	public void doFinally() {
		// TODO Auto-generated method stub
		super.doFinally();
		this.level = 3;
		enableindex = true;
	}

	

 



	public boolean isEnableindex() {
		return enableindex;
	}



	public void setEnableindex(boolean enableindex) {
		this.enableindex = enableindex;
	}
	
}
