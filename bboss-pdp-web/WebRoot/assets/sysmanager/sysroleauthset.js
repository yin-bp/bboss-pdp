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
		$('.btn-saveroleauths',ModelDialog.getCurrentModal()).bind('click',function(){			
			$('.form-roleauthset',ModelDialog.getCurrentModal())
			.ajaxSubmit(
					{
						type : 'POST',
						url : usercontextpath+'/sysmanager/role/saveRoleAuths.page',
						forceSync : false,
						dataType : 'json',
						beforeSubmit : function() {
							 App.startPageLoading({message: '保存中...'});				           
						},
						error : function(xhr, ajaxOptions,
								thrownError) {
							PDP.warn(thrownError) ;
						},

						success : function(responseText,
								statusText, xhr, $form) {
							 
							 window.setTimeout(function() {
					                App.stopPageLoading();
					            }, 2000);
							var msg = responseText;
							var title = '设置角色权限';
							var tiptype = "success";
							if (msg == 'success') {
								msg = "设置角色权限完毕"
								PDP.success(msg,function(){									
									
								}) ;
							} else {							 
								PDP.warn(msg) ;
							}
							
							

						}

					});
		});
		
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