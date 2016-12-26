var SysRoleAuthset = function(){
	var usercontextpath ;
	var resourceType;
	var resourceName;
	var roleId;
	var roleType;
	var initRoleAuthset = function(path,roleId,roleType,resourceType,resourceName){
		usercontextpath = path;
		$('[data-toggle="tab"]','.resourcetypelist').bind('shown.bs.tab',function(){			
			loadResourceOperations($(this).attr('resourceType'),$(this).attr('resourceName'),roleId,roleType);
		});
		//getSelectRoles(false);
		//initAuthmainAction(userId);
		loadResourceOperations(resourceType,resourceName,roleId,roleType)
	}
	var loadResourceOperations = function(_resourceType,_resourceName,_roleId,_roleType){
		roleId = _roleId;
		roleType = _roleType;
		resourceType = _resourceType;
		resourceName = _resourceName;
		$("#resource_operations").load(usercontextpath+"/sysmanager/role/loadResourceOperations.page",
					{"resourceType":_resourceType,"roleId":roleId,"roleType":roleType},
					function(){
						 Layout.fixContentHeight(); // fix content height
			             App.initAjax(); // initialize core stuff
					});	
			
	}
	var initRoleAuthAction = function(){
		
	}	 
	return {
		 
		initRoleAuthset:function(path, roleId , roleType , resourceType , resourceName ){
			initRoleAuthset(path,roleId , roleType , resourceType , resourceName );
		},
		initRoleAuthAction:function(){
			initRoleAuthAction();
		}
	
	}
}();