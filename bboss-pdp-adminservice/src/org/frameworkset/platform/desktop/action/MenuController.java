package org.frameworkset.platform.desktop.action;

import java.util.ArrayList;
import java.util.List;

import org.frameworkset.platform.common.JSTreeNode;
import org.frameworkset.platform.common.TreeNodeStage;
import org.frameworkset.platform.common.ZKTreeNode;
import org.frameworkset.platform.config.model.ResourceInfo;
import org.frameworkset.platform.framework.Framework;
import org.frameworkset.platform.framework.MenuItem;
import org.frameworkset.platform.framework.MenuQueue;
import org.frameworkset.platform.framework.Module;
import org.frameworkset.platform.resource.ResourceManager;
import org.frameworkset.platform.security.AccessControl;
import org.frameworkset.platform.security.AuthorResource;
import org.frameworkset.util.annotations.ResponseBody;
import org.frameworkset.web.servlet.ModelMap;

import com.frameworkset.common.poolman.Record;
import com.frameworkset.common.poolman.handle.RowHandler;
import com.frameworkset.platform.admin.entity.MenuOPS;
import com.frameworkset.platform.admin.entity.Role;
import com.frameworkset.platform.admin.service.RoleService;
import com.frameworkset.platform.admin.util.AdminUtil;

public class MenuController{
	private ResourceManager resourceManager = new ResourceManager();
	private RoleService roleService;
	public String grantedcolumns(String resourceType,String roleId,String roleType,ModelMap model){
		if(resourceType == null)
			resourceType = "column";
		String currentSystem = AccessControl.getAccessControl().getCurrentSystemID();
		final Framework framework = Framework.getInstance(currentSystem);		
		model.addAttribute("roleId", roleId);
		model.addAttribute("roleType", roleType);
		model.addAttribute("resourceType", resourceType);
		ResourceInfo resourceInfo = resourceManager.getResourceInfoByType(resourceType);
		if(roleType.equals("role")){
			Role role = null;
			  role = this.roleService.getRole(roleId);				
			model.addAttribute("roleNeedGrantResource", AdminUtil.roleNeedGrantResource(role.getRoleName()));
				
		}
		@SuppressWarnings("unchecked")
		List<MenuOPS> grantedcolumns = this.roleService.getGrantedOperations("visible",resourceType, roleId, roleType, resourceInfo.getPermissionTable(), new RowHandler<MenuOPS>(){
			
			@Override
			public void handleRow(MenuOPS menu,Record origine) throws Exception {
				String op_id = origine.getString("op_id");
				menu.setOpcode(op_id);
				menu.setResCode(origine.getString("RES_ID"));
				menu.setResName(origine.getString("RES_NAME"));
				menu.setResourceType(origine.getString("RESTYPE_ID"));					
				menu.setAUTHORIZATION_ETIME(origine.getDate("AUTHORIZATION_ETIME"));
				menu.setAUTHORIZATION_STIME(origine.getDate("AUTHORIZATION_STIME"));
				MenuItem menuItem = framework.getMenuByID(menu.getResCode());
				menu.setMenuName(menuItem.getName());
				menu.setMenupath(menuItem.getPath());
				AuthorResource ar = (AuthorResource)menuItem;
				menu.setUrls(ar.toString("<br>"));
				
			}
			
		},MenuOPS.class);
		model.addAttribute("grantedcolumns", grantedcolumns);
		return "path:grantedcolumns";
	}
	
	private ZKTreeNode buildZKTreeNode(MenuItem menu)
	{
		ZKTreeNode JSTreeNode = new ZKTreeNode();
		JSTreeNode.setId(menu.getId());
//		JSTreeNode.setText(new StringBuilder().append("<a href=\"#\" onclick=\"javascript:Sysmanager.showOrgUsers('").append(org.getOrgId()).append("');\">").append(org.getOrgName()).append("</a>").toString());
		JSTreeNode.setName(menu.getName());
		JSTreeNode.setIsParent(true);
		return JSTreeNode;
	}
	private JSTreeNode buildJSTreeNode(MenuItem menu)
	{
		JSTreeNode JSTreeNode = new JSTreeNode();
		JSTreeNode.setId(menu.getId());
//		JSTreeNode.setText(new StringBuilder().append("<a href=\"#\" onclick=\"javascript:Sysmanager.showOrgUsers('").append(org.getOrgId()).append("');\">").append(org.getOrgName()).append("</a>").toString());
		JSTreeNode.setText(menu.getName());
		JSTreeNode.setIcon(null);
		TreeNodeStage state = new TreeNodeStage();
		state.setDisabled(false);
		state.setOpened(false);
		state.setSelected(false);
		JSTreeNode.setState(state);
		JSTreeNode.setChildren(true);
		return JSTreeNode;
	}
	public @ResponseBody List<JSTreeNode> getChildrens(String parent,String roleId,String roleType){
		String currentSystem = AccessControl.getAccessControl().getCurrentSystemID();
		Framework framework = Framework.getInstance(currentSystem);
		if(parent == null || parent.equals("#")){
			MenuQueue menus = framework.getMenus();
			if(menus != null && menus.size() > 0){
				List<JSTreeNode> treeNodes = new ArrayList<JSTreeNode>();
				for(MenuItem menu:menus.getList())
				{
					treeNodes.add(this.buildJSTreeNode(menu));
				}
				return treeNodes;
			}
				
			
		}
		else
		{
			MenuItem menuItem = framework.getMenuByID(parent);
			if(menuItem instanceof Module){
				MenuQueue menus = ((Module)menuItem).getMenus();
				if(menus != null && menus.size() > 0){
					List<JSTreeNode> treeNodes = new ArrayList<JSTreeNode>();
					for(MenuItem menu:menus.getList())
					{
						treeNodes.add(this.buildJSTreeNode(menu));
					}
					return treeNodes;
				}
			}
			
		}
		 
		return null;
		 
			
	}
	
	public @ResponseBody List<ZKTreeNode> getzkChildrens(String id,String roleId,String roleType,ModelMap model){
		String currentSystem = AccessControl.getAccessControl().getCurrentSystemID();
		model.addAttribute("roleId", roleId);
		model.addAttribute("roleType", roleType);
		 
		Framework framework = Framework.getInstance(currentSystem);
		if(id == null || id.equals("#")){
			MenuQueue menus = framework.getMenus();
			if(menus != null && menus.size() > 0){
				List<ZKTreeNode> treeNodes = new ArrayList<ZKTreeNode>();
				for(MenuItem menu:menus.getList())
				{
					treeNodes.add(this.buildZKTreeNode(menu));
				}
				return treeNodes;
			}
				
			
		}
		else
		{
			
			MenuItem menuItem = framework.getMenuByID(id);
			if(menuItem instanceof Module){
				MenuQueue menus = ((Module)menuItem).getMenus();
				if(menus != null && menus.size() > 0){
					List<ZKTreeNode> treeNodes = new ArrayList<ZKTreeNode>();
					for(MenuItem menu:menus.getList())
					{
						treeNodes.add(this.buildZKTreeNode(menu));
					}
					return treeNodes;
				}
			}
			
		}
		 
		return null;
		 
			
	}
	public String columnAuthTree(String roleId,String roleType,ModelMap model){
		model.addAttribute("roleId", roleId);
		model.addAttribute("roleType", roleType);
		return "path:columnAuthTree";
	}


}
