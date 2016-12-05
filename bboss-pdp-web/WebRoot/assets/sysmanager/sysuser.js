var SysUser = function(){
	var usercontextpath;
	var validateDepart = function(){
		return Sysmanager.validateDepart();
	}
	var afterSaveUser = function()
	{
		Sysmanager.showOrgUsers(Sysmanager.getDepartId());
	}
	
	 var $modal;
	 var initModal = function(){
		 if($modal == null){
			 $modal = $('#ajax-user-action-extend');
			 $modal.draggable({
		            handle: ".modal-header"
		        });
		 }
	 }
	var initAddUserModalExtend = function(){
		 
		initModal();
		 $('#button_sys_add_user').on('click', function(){
            // create the backdrop and wait for next modal to be triggered
           
            var el = $(this);
            var vr = validateDepart();
           
             if(!vr)
            {
           	  return;
            }	  
            // https://github.com/jschr/bootstrap-modal
            $modal.load(usercontextpath+"/sysmanager/user/toAddSmUser.page", {
           	 "departId":Sysmanager.getDepartId()
            }, function(){
           	 $modal.on('hidden.bs.modal', function () {
           		 afterSaveUser();
      			 });
                $modal.modal({
               	 backdrop:"static",
               	 width :"900px"
                });
               
              });
            
          });
		
	   	
  	 
	}
	
	
	
	var closeUserActionModel = function(){
		//var $modal = $('#ajax-user-add').modal('hide');
		
		 $('#ajax-user-action-extend').modal('hide');
		
	}
	var initAddusersButtonAction = function(){
   	 $("#sys_addUser_button").bind('click',function(){
   		 saveUser("#form_sys_adduser");
   	 });
    }
	
	var initModifyUserButtonAction = function(){
	   	 $("#sys_modifyUser_button").bind('click',function(){
	   		saveUser("#form_sys_modifyuser");
	   	 });
	    }
    
    var saveUser = function (formid){
    	
		  if($("#userPassword").val() != $("#userPasswordSecond").val()){				
			  PlatformCommonUtils.warn("两次口令不一致") ;
			
				return false;
			}
		  var vr = validateDepart();
          if(!vr)
        	  return;
		   $(formid).submit();
		   
		   return false;
	   }
    
    var iniModifyUserValidateform = function()
	{
		var form2 = $("#form_sys_modifyuser");		
		form2.validate({
					focusInvalid : false, // do not focus the last invalid
											// input
					ignore : "", // validate all fields including form hidden
									// input
					messages : {
						
					 
							userRealname : {
								minlength : jQuery.validator.format("应用编码不能小于{0}个字符"),
								required : "请输入中文名称"
							},

							userPassword : {
								minlength : jQuery.validator.format("应用名称不能小于{0}个字符"),
								required : "请输入6位以上口令"
							},

							userPasswordSecond : {
								minlength : jQuery.validator.format("二次输入口令不能小于{0}个字符"),
								required : "请二次输入口令"
							} ,
							userIdcard : {
								minlength : jQuery.validator.format("应用口令不能小于{0}个字符"),
								required : "请输入身份证"
							},

							userWorknumber : {
								minlength : jQuery.validator.format("应用编码不能小于{0}个字符"),
								required : "请输入工号"
							}
					},
					rules : {
					 
						userRealname : {
							minlength : 1,
							required : true
						},

						userPassword : {
							minlength : 1,
							required : true
						},

						userPasswordSecond : {
							minlength : 1,
							required : true
						},

						userIdcard : {
							minlength : 1,
							required : true
						},

						userWorknumber : {
							minlength : 1,
							required : true
						}  
					},

					

					submitHandler : function(form) {
						// success1.show();
						modifyuser()
						
					}
				});
	}
    var initAdduserValidateform = function()
	{
		var form2 = $("#form_sys_adduser");		
		form2.validate({
					focusInvalid : false, // do not focus the last invalid
											// input
					ignore : "", // validate all fields including form hidden
									// input
					messages : {
						
						userName : {
								minlength : jQuery.validator.format("应用口令不能小于{0}个字符"),
								required : "请输入账号"
							},

							userRealname : {
								minlength : jQuery.validator.format("应用编码不能小于{0}个字符"),
								required : "请输入中文名称"
							},

							userPassword : {
								minlength : jQuery.validator.format("应用名称不能小于{0}个字符"),
								required : "请输入6位以上口令"
							},

							userPasswordSecond : {
								minlength : jQuery.validator.format("二次输入口令不能小于{0}个字符"),
								required : "请二次输入口令"
							} ,
							userIdcard : {
								minlength : jQuery.validator.format("应用口令不能小于{0}个字符"),
								required : "请输入身份证"
							},

							userWorknumber : {
								minlength : jQuery.validator.format("应用编码不能小于{0}个字符"),
								required : "请输入工号"
							}
					},
					rules : {
						userName : {
							minlength : 2,
							required : true
						},

						userRealname : {
							minlength : 1,
							required : true
						},

						userPassword : {
							minlength : 1,
							required : true
						},

						userPasswordSecond : {
							minlength : 1,
							required : true
						},

						userIdcard : {
							minlength : 1,
							required : true
						},

						userWorknumber : {
							minlength : 1,
							required : true
						}  
					},

					

					submitHandler : function(form) {
						// success1.show();
						adduser()
						
					}
				});
	}
     var init = function(relativepath){
    	 usercontextpath = relativepath;
    	 //initAddUserModal();
    	 initAddUserModalExtend();
    	 initDelUsers();
     }
     
     var initAddUser = function(){
    	 initAddusersButtonAction();
    	 initAdduserValidateform();
     }
     
     
	
	var adduser = function()
	{
		$("#form_sys_adduser")
		.ajaxSubmit(
				{
					type : 'POST',
					url : usercontextpath+'/sysmanager/user/addSmUser.page',
					forceSync : false,
					dataType : 'json',
					beforeSubmit : function() {
						 App.startPageLoading({message: '保存中...'});

				           
					},
					error : function(xhr, ajaxOptions,
							thrownError) {
						 
					},

					success : function(responseText,
							statusText, xhr, $form) {
						 
						 window.setTimeout(function() {
				                App.stopPageLoading();
				            }, 2000);
						var msg = responseText;
						var title = '添加用户';
						var tiptype = "success";
						if (msg == 'success') {
							msg = "添加用户完毕"
						} else {
							 
							
						}
						PlatformCommonUtils.success(msg,function(){
							closeUserActionModel();
						}) ;
						/**swal({
							  title: title,
							  text: msg,
							  type:tiptype,
							  confirmButtonClass: "btn-success",
							  confirmButtonText: "确定",
							},
							function(){
								closeUserActionModel();
							});*/
						/**
						 bootbox.alert(msg, function() {
			                    alert("Hello world callback");
			                }); */

					}

				});
	}
	var modifyuser = function()
	{
		$("#form_sys_modifyuser")
		.ajaxSubmit(
				{
					type : 'POST',
					url : usercontextpath+'/sysmanager/user/updateSmUser.page',
					forceSync : false,
					dataType : 'json',
					beforeSubmit : function() {
						 App.startPageLoading({message: '保存中...'});

				           
					},
					error : function(xhr, ajaxOptions,
							thrownError) {
						 
					},

					success : function(responseText,
							statusText, xhr, $form) {
						 
						 window.setTimeout(function() {
				                App.stopPageLoading();
				            }, 2000);
						var msg = responseText;
						var title = '修改用户';
						var tiptype = "success";
						if (msg == 'success') {
							msg = "修改用户完毕"
						} else {
							 
							
						}
						PlatformCommonUtils.success(msg,function(){
							closeUserActionModel();
						}) ;
						/**swal({
							  title: title,
							  text: msg,
							  type:tiptype,
							  confirmButtonClass: "btn-success",
							  confirmButtonText: "确定",
							},
							function(){
								closeUserActionModel();
							});*/
						/**
						 bootbox.alert(msg, function() {
			                    alert("Hello world callback");
			                }); */

					}

				});
	}
     
     
    var viewUser = function(userId){
    	initModal();
    	 // https://github.com/jschr/bootstrap-modal
        $modal.load(usercontextpath+"/sysmanager/user/getSmUser.page", {
       	 "userId":userId
        }, function(){
       	 
            $modal.modal({
           	 backdrop:"static",
           	 width :"900px"
            });
           
          });
    }
    var tomodifyUser = function(userId){
    	initModal();
   	 // https://github.com/jschr/bootstrap-modal
       $modal.load(usercontextpath+"/sysmanager/user/toUpdateSmUser.page", {
      	 "userId":userId
       }, function(){
    	   $modal.on('hidden.bs.modal', function () {
         		 afterSaveUser();
    			 });
           $modal.modal({
          	 backdrop:"static",
          	 width :"900px"
           });
          
         });
    }
    var initModifyUser = function(){
    	initModifyUserButtonAction();
   	    iniModifyUserValidateform();
    }
    var initDelUsers = function(){
    	$("#button_sys_delete_user").bind("click",function(){
    		 var vr = validateDepart();
             if(!vr)
            {
           	  return;
            }
             var chk_value =[]; 
             $('input[name="userId"]:checked').each(function(){ 
            	 chk_value.push($(this).val()); 
             }); 
             if(chk_value.length == 0)
             {
            	 PlatformCommonUtils.warn("请选择要删除的用户!");
            	 return;
             }
             var extendtext = "<input type=\"radio\"  name=\"user_deltype\" value=\"0\" checked>逻辑删除";
             extendtext += "<input type=\"radio\"  name=\"user_deltype\" value=\"1\">物理删除";
             PlatformCommonUtils.confirm("确定要删除选中的用户吗?",function(isConfirm){
            	 	if(isConfirm)
            	 	{
            	 		var user_deltype = '0';
            	 		$('input[name="user_deltype"]:checked').each(function(){ 
            	 			user_deltype = $(this).val(); 
                        }); 
            	 		delusers(chk_value,user_deltype);
            	 	}
			        	
				},extendtext,true);
    		
    	});
    }
    /**
     * user_deltype：0 逻辑删除 1 物理删除
     */
    var delusers = function(users,user_deltype)
    {
    	var userIds;
    	for(var i = 0; i < users.length;i ++)
    		{
    			if(i > 0)
    				userIds += ","+users[i];
    			else
    				userIds = users[i];
    		
    		}
    	$.ajax({
 		   type: "POST",
 			url : usercontextpath+"/sysmanager/user/deleteBatchSmUser.page",
 			data :{"userIds":userIds,"user_deltype":user_deltype},
 			dataType : 'json',
 			async:false,
 			beforeSend: function(XMLHttpRequest){ 					
 				 	
 				},
 			success : function(responseText){
 				
 				if(responseText=="success"){
 					
 					PlatformCommonUtils.success("用户删除成功!");
 					afterSaveUser();
 				}else{
 					PlatformCommonUtils.warn("用户删除失败:"+responseText);
 				}
 			}
 		  });
    }
    
    return {
    	init:function(usercontextpath){
    		init(usercontextpath);
    	},
    	initAddUser:function(){
    		initAddUser();
    	},
    	saveUser:function(){
    		saveUser();
    	},
    	viewUser:function(userId){
    		viewUser(userId);
    	},
    	tomodifyUser:function(userId){
    		tomodifyUser(userId);
    	},
    	initModifyUser:function(){
    		initModifyUser();
    	},
    	initDelUsers:function(){
    		initDelUsers();
    	}
    	
    	
    	
    }


}();

