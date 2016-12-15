var SysRole = function()
{
	/**
	设置角色操作按钮
	 */
	var roleButtonMethods = function()
	{
		
		
		 var content_ = [
		                 {
		                     class: 'btn btn-xs btn-default',
		                     icon: 'fa fa-pencil',
		                     label:'查看',
		                     onClick: function() {
		                    	 var userName = $(this).attr("userName");
		                    	 var userAccount = $(this).attr("userAccount");
		                    	 var userId = $(this).attr("userId");
		                    	 SysUser.viewUser(userId,userName+"("+userAccount+")")
		                     }
		                   },
		                   {
		                     class: 'btn  btn-xs btn-default',
		                     icon: 'fa fa-pencil',
		                     label:'修改',
		                     onClick: function() {
		                    	 var userName = $(this).attr("userName");
		                    	 var userAccount = $(this).attr("userAccount");
		                    	 var userId = $(this).attr("userId");
		                    	 SysUser.tomodifyUser(userId,userName+"("+userAccount+")");
		                     }
		                   },
		                  
		                   {
		                     class: 'btn  btn-xs btn-default',
		                     icon: 'fa fa-pencil',
		                     label:'设置用户',
		                     onClick: function() {
		                    	 
		                    	 var userId = $(this).attr("userId");
		                    	 var userName = $(this).attr("userName");
		                    	 var userAccount = $(this).attr("userAccount");
		                    	 SysUser.tomodifyPassword(userId,userName,userAccount)
		                     }
		                   },
		                   {
		                     class: 'btn  btn-xs btn-default',
		                     icon: 'fa fa-pencil',
		                     label:'授权',
		                     onClick: function() {
		                    	 var userId = $(this).attr("userId");
		                    	 var defaultAdmin = $(this).attr("defaultAdmin");
		                    	 if(!defaultAdmin)
		                    	 {
		                    		 SysUser.toauthUser(userId)
		                    	 }
		                    	 else
	                    		 {
		                    		 PlatformCommonUtils.warn("管理员无需授权!");
	                    		 }
		                     }
		                   },		                   
		                  
		                  
		                   {
		                	   class: 'btn  btn-xs btn-default',
		                	      icon: 'glyphicon glyphicon-remove',
		                	      cancel: true
			                   }
		                 ];
		return content_;
	}
	var queryRoles = function(doquery){
		
		$(".portlet-rolelist").load(contextpath+"/sysmanager/role/queryListInfoRoles.page",
									doquery?$('.form-queryrole').serialize():{},
									function(){
										PlatformCommonUtils.popconfirmation({selector:'[data-toggle=role_ops_confirmation]',buttons:SysRole.roleButtonMethods()});
										
									});	
	}
	var contextpath;
	var init = function(path){
		contextpath = path;
		queryRoles(false);
		
		$(".form-queryrole .btn-query").bind('click',function(){
			queryRoles(true);
		});
	}
	return {
		roleButtonMethods:function(){
			return roleButtonMethods();
		},
		init:function(contextpath){
			init(contextpath);
		}
	}
}();