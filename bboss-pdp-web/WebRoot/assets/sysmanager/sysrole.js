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
		
		$(".portlet-rolelist").load(usercontextpath+"/sysmanager/role/queryListInfoRoles.page",
									doquery?$('.form-queryrole').serialize():{},
									function(){
										PlatformCommonUtils.popconfirmation({selector:'[data-toggle=role_ops_confirmation]',buttons:SysRole.roleButtonMethods()});
										
									});	
	}
	var usercontextpath;
	var init = function(path){
		usercontextpath = path;
		queryRoles(false);
		
		$(".form-queryrole .btn-query").bind('click',function(){
			queryRoles(true);
		});
		$(".btn-addrole").bind('click',function(){
			 ModelDialog.dialog({
					title:"新增角色",
					showfooter:false,
					url:usercontextpath+"/sysmanager/role/toAddRole.page",
					
					width:"600px",
					height:"500px"

	         });
		});
		$(".btn-batchdelrole").bind("click",function(){
   		  
            var chk_value =""; 
            $('.portlet-rolelist input[name="roleId"]:checked').each(function(){ 
            	if(chk_value == "")
            		chk_value = $(this).val(); 
            	else
            		chk_value += ","+$(this).val(); 
            }); 
            if(chk_value == "")
            {
	           	 PlatformCommonUtils.warn("请选择要删除的角色!");
	           	 return;
            }
             PlatformCommonUtils.confirm("确定要删除选中的角色吗?",function(isConfirm){
	           	 	if(isConfirm)
	           	 	{        	 		
	           	 		
		           	 	$.ajax({
		          		   type: "POST",
		          			url : usercontextpath+"/sysmanager/role/deleteBatchRole.page",
		          			data :{"roleIds":chk_value},
		          			dataType : 'json',
		          			async:false,
		          			beforeSend: function(XMLHttpRequest){ 					
		          				 	
		          				},
		          			success : function(responseText){
		          				
		          				if(responseText=="success"){
		          					
		          					PlatformCommonUtils.success("角色删除成功!");
		          					afterSaveRole();
		          				}else{
		          					PlatformCommonUtils.warn("角色删除失败:"+responseText);
		          				}
		          			}
		          		  });
	           	 	}
			        	
				});
   		
		});
		
	}
	var initAddRole = function(){
		PlatformCommonUtils.validateform({
			form:"form",
			messages : {
					roleName : {
						minlength : jQuery.validator.format("角色名称不能小于{0}个字符"),
						required : "请输入角色名称"
					},
	
					remark1 : {
						required : "请输入角色中文名"
					}
			},
			rules : {			 
				roleName : {
					minlength : 2,
					required : true
				},	
				remark1 : {
					minlength : 2,
					required : true
				}
			},
			submitHandler:addRole
		});
		
		$(".btn-roleaddsave",ModelDialog.getCurrentModal()).bind("click",function(){
			$("form",ModelDialog.getCurrentModal()).submit();
		});
	}
	//新增修改角色操作，刷新角色列表
	var afterSaveRole = function(){
		queryRoles(false);
	}
	var addRole = function(){
		$('form',ModelDialog.getCurrentModal())
		.ajaxSubmit(
				{
					type : 'POST',
					url : usercontextpath+'/sysmanager/role/addRole.page',
					forceSync : false,
					dataType : 'json',
					beforeSubmit : function() {
						 App.startPageLoading({message: '保存中...'});				           
					},
					error : function(xhr, ajaxOptions,
							thrownError) {
						PlatformCommonUtils.warn(thrownError) ;
					},

					success : function(responseText,
							statusText, xhr, $form) {
						 
						 window.setTimeout(function() {
				                App.stopPageLoading();
				            }, 2000);
						var msg = responseText;
						var title = '增加角色';
						var tiptype = "success";
						if (msg == 'success') {
							msg = "增加角色完毕"
							PlatformCommonUtils.success(msg,function(){
								ModelDialog.getCurrentModal().modal('hide');
								afterSaveRole();
							}) ;
						} else {							 
							PlatformCommonUtils.warn(msg) ;
						}
						
						

					}

				});
	}
	
	return {
		roleButtonMethods:function(){
			return roleButtonMethods();
		},
		init:function(contextpath){
			init(contextpath);
		},
		initAddRole:function(){
			initAddRole();
		}
	}
}();