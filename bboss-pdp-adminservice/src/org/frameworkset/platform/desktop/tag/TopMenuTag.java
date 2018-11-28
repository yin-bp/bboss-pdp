package org.frameworkset.platform.desktop.tag;
/**
 * Copyright 2008 biaoping.yin
 * <p>
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 * <p>
 * http://www.apache.org/licenses/LICENSE-2.0
 * <p>
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

import com.frameworkset.common.tag.BaseTag;
import com.frameworkset.util.StringUtil;
import org.frameworkset.platform.framework.*;
import org.frameworkset.platform.security.AccessControl;

import javax.servlet.jsp.JspException;
import java.io.IOException;

/**
 * <p>Description: </p>
 * <p></p>
 * <p>Copyright (c) 2018</p>
 * @Date 2018/11/25 21:47
 * @author biaoping.yin
 * @version 1.0
 */
public class TopMenuTag  extends BaseTag {
	private static String menu_header = " <div class=\"hor-menu hor-menu-light  hidden-sm hidden-xs\"> <ul class=\"nav navbar-nav\">";
	private static String menu_booter = "</ul></div>";
	private int level;
	private boolean enableindex = true;


	private void renderIndex(StringBuilder content,String contextpath,AccessControl control, MenuHelper menuHelper, Item item){
		String mname = item.getName(request);

		content.append("<li class=\"classic-menu-dropdown active\" aria-haspopup=\"true\">")
				.append("<a href=\"javascript:void(0);\" onclick=\"javascript:DesktopMenus.gotomenu('").append(item.getId()).append("',this,event)\"> ").append(mname)
				.append("<span class=\"selected\"> </span>")
				.append("                </a>")
				.append("            </li>");




	}
	private void renderItem(String contextpath, AccessControl control, MenuHelper menuHelper, Item item, boolean selected, StringBuilder datas, boolean isfirst)
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
		if(fullpageload.equals("true"))
		{
			datas.append("<li ").append(selectedclass).append(">")
					.append("<a id=\"left__").append(item.getId()).append("\"  href=\"javascript:void(0);\" onclick=\"javascript:DesktopMenus.gotomenu('").append(item.getId()).append("',this,event)\" class=\"nav-link \">");

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
				url =  MenuHelper.getRealUrl(contextpath, MenuHelper.iframeurl,MenuHelper.selecturl, StringUtil.urlencode(url,"UTF-8"));
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

	private void renderNosonModule(String contextpath, AccessControl control, MenuHelper menuHelper, Module item,   StringBuilder datas )
	{
		String mname = item.getName(request);

		datas.append("<li class=\"classic-menu-dropdown\" aria-haspopup=\"true\">")
				.append("<a href=\"javascript:void(0);\" onclick=\"javascript:DesktopMenus.gotomenu('").append(item.getId()).append("',this,event)\"> ").append(mname)
				.append("<span class=\"selected\"> </span>")
				.append("                </a>")
				.append("            </li>");
	}



	private void renderModule(String contextpath,AccessControl control,MenuHelper menuHelper,Module item, StringBuilder datas, int current_level)
	{
		String selectedclass = "";


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
//				if(theme == null || theme.equals("admin_3"))
					datas.append("<a href=\"javascript:void(0);\" onclick=\"DesktopMenus.gotoworkspace('','").append(url)
							.append("','").append(item.getId()).append("',false,this)\" class=\"nav-link nav-toggle\">");
//				else
//				{
//					//ondblclick
//					datas.append("<a href=\"javascript:void(0);\" onclick=\"DesktopMenus.gotoworkspace('','").append(url)
//							.append("','").append(item.getId()).append("',false,this)\" class=\"nav-link nav-toggle\">");
//
//				}
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
//				if(theme == null || theme.equals("admin_3"))
					datas.append("<a id=\"left__").append(item.getId()).append("\"  href=\"javascript:void(0);\" onclick=\"javascript:DesktopMenus.gotomenu('").append(item.getId()).append("',this,event)\" class=\"nav-link nav-toggle\">");
//				else
//				{
//					//ondblclick
//					datas.append("<a id=\"left__").append(item.getId()).append("\"  href=\"javascript:void(0);\" onclick=\"javascript:DesktopMenus.gotomenu('").append(item.getId()).append("',this,event)\" class=\"nav-link nav-toggle\">");
//
//				}
			}
		}

//		if(icon != null && !icon.equals(""))
//		{
//			datas.append("    <i class=\"").append(icon).append("\"></i>");
//		}
//		datas.append("    <span class=\"title\">").append(mname).append("</span>");
//		if(selected)
//		{
//			datas.append("   <span class=\"selected\"></span>").append("    <span class=\"arrow open\"></span>");
//		}
//		else
//		{
//			datas.append("    <span class=\"arrow  \"></span>");
//		}


		datas.append("</a>")
				.append("<ul class=\"sub-menu\">");

		for(int i = 0; menus != null && i < menus.size() ; i ++)
		{
			MenuItem mi = menus.getMenuItem(i);
			if(!mi.isUsed())
				continue;
			if(mi instanceof Item)
			{
				this.renderItem(contextpath, control, menuHelper, (Item)mi, false, datas, false);
			}
			else
			{
				Module module = (Module)mi;
				if(module.getMenus() != null && module.getMenus().size() > 0)
					renderModule(  contextpath,  control,  menuHelper,module,datas,0);
				else
				{
					renderNosonModule(  contextpath,  control,  menuHelper,module,datas);

				}

			}

		}

		datas.append("   </ul>")
				.append(" </li>  ");

	}

	@Override
	public int doStartTag() throws JspException {
		int ret = super.doStartTag();
		AccessControl control = AccessControl.getAccessControl();
		MenuHelper menuHelper =  MenuHelper.getMenuHelper(request);
		StringBuilder datas = new StringBuilder();
		String theme = control.getUserAttribute("theme");
		datas.append(menu_header);

		//添加首页

//		String personcenter = Framework.getInstance(control.getCurrentSystemID()).getMessage("sany.pdp.module.personcenter", RequestContextUtils.getRequestContextLocal(request));

		String selectedmenuid = request.getParameter(MenuHelper.selectedmodule);//查找选择的菜单项path,待处理


		try{


			String contextpath = request.getContextPath();
			Item publicitem = menuHelper.getPublicItem();

			if(this.enableindex && publicitem != null && publicitem.isMain())
			{

//				datas.append(" <li class=\"heading\">")
//				.append("    <h3 class=\"uppercase\">Features</h3>")
//				.append("</li>");

				renderIndex(  datas,contextpath,  control,   menuHelper, publicitem);

			}


			MenuQueue menus = menuHelper.getMenus();
			for (int i = 0; menus != null && i < menus.size(); i++) {
				MenuItem mi = menus.getMenuItem(i);
				if (!mi.isUsed()) {
					continue;
				}

				if(mi instanceof Item)
				{
					renderIndex(  datas,contextpath,  control,   menuHelper, (Item)mi);
				}
				else
				{
					Module module = (Module) mi;
					if(module.getMenus() != null && module.getMenus().size() > 0)
						renderModule(  contextpath,  control,  menuHelper,module,datas,0);
					else
					{
						renderNosonModule(  contextpath,  control,  menuHelper,module,datas);

					}
				}



			}

		}
		catch(Exception e)
		{
			e.printStackTrace();
		}




		datas.append(menu_booter);

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






	@Override
	public void doFinally() {
		// TODO Auto-generated method stub
		super.doFinally();
		this.level = 0;
		enableindex = true;
	}


	public int getLevel() {
		return level;
	}

	public void setLevel(int level) {
		this.level = level;
	}
}
