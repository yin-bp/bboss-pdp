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
		                    	 var roleId = $(this).attr("roleId");
		                    	 var roleName = $(this).attr("roleName");
		                    	 var remark1 = $(this).attr("remark1");
		                    	 ModelDialog.dialog({
			         					title:"查看角色-"+roleName+"("+remark1+")",
			         					showfooter:false,
			         					url:usercontextpath+"/sysmanager/role/getRole.page",
			         					params:{
			         						"roleId":roleId
			         				      },
			         					width:"600px",
			         					height:"500px"
	
			         	         });
		                     }
		                   },
		                   {
		                     class: 'btn  btn-xs btn-default',
		                     icon: 'fa fa-pencil',
		                     label:'修改',
		                     onClick: function() {
		                    	 var roleId = $(this).attr("roleId");
		                    	 var roleName = $(this).attr("roleName");
		                    	 var remark1 = $(this).attr("remark1");
		                    	 ModelDialog.dialog({
			         					title:"修改角色-"+roleName+"("+remark1+")",
			         					showfooter:false,
			         					url:usercontextpath+"/sysmanager/role/toUpdateRole.page",
			         					params:{
			         						"roleId":roleId
			         				      },
			         					width:"600px",
			         					height:"500px"
	
			         	         });
		                    	 
		                     }
		                   },
		                  
		                  
		                   {
		                     class: 'btn  btn-xs btn-default',
		                     icon: 'fa fa-pencil',
		                     label:'授权设置',
		                     onClick: function() {
		                    	 var roleId = $(this).attr("roleId");
		                    	 var roleName = $(this).attr("roleName");
		                    	 var remark1 = $(this).attr("remark1");
		                    	
		                    	toroleauthset(roleId,roleName,remark1);
		                    	 
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
	var toroleauthset = function(roleId,roleName,remark1){
		ModelDialog.dialog({
				title:"角色授权及用户设置-"+roleName+"("+remark1+")",
				showfooter:false,
				url:usercontextpath+"/sysmanager/role/toroleauthset.page",
				params:{
					"roleId":roleId,
					"roleName":roleName,
					"roleType":"role",
					"remark1":remark1 
			      },
				width:"1000px",
				height:"600px"

      });
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
	var modifyRole = function(){
		$('form',ModelDialog.getCurrentModal())
		.ajaxSubmit(
				{
					type : 'POST',
					url : usercontextpath+'/sysmanager/role/updateRole.page',
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
						var title = '修改角色';
						var tiptype = "success";
						if (msg == 'success') {
							msg = "修改角色完毕"
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
	var initModifyRole = function(){
		PlatformCommonUtils.validateform({
			form:"form",
			messages : {
					 
	
					remark1 : {
						required : "请输入角色中文名"
					}
			},
			rules : {			 
				 	
				remark1 : {
					minlength : 2,
					required : true
				}
			},
			submitHandler:modifyRole
		});
		
		$(".btn-rolemodifysave",ModelDialog.getCurrentModal()).bind("click",function(){
			$("form",ModelDialog.getCurrentModal()).submit();
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
		},
		initModifyRole:function(){
			initModifyRole();
		}
	}
}();