var SysAuthmain = function(){
	var usercontextpath ;
	var initAuthmain = function(path,userId){
		usercontextpath = path;
		getSelectRoles(false);
		initAuthmainAction(userId);
	}
	var getSelectRoles = function(doquery){
		$(".select_roles",ModelDialog.getCurrentModal()).load(usercontextpath+"/sysmanager/role/queryListInfoRoles.page",
				doquery?$('.form-queryroles').serialize():{fromAuthmain:true},
					function(){
					
					});
	}
	
	var initAuthmainAction = function(userId){
		$(".dotempadd_btn",ModelDialog.getCurrentModal()).bind("click",function(){
			 $('input[name="roleId"]:checked',$(".table-unselectrole")).each(function(){ 
				 	var tr = $(this).closest('tr');
				 	// console.log(tr);
				 	// console.log($(".selected_users_movein"));
				 	var selectedtr = $(".table-selected-roles input[value='"+$(this).val()+"']");
				 	// console.log(selectedtr);
				 	if(selectedtr.length == 0){
				 		
				 		
				 		tr.clone().insertAfter(".table-selected-roles tr:last");// 插入到已选表格的最后一行
				 		$(".table-selected-roles").find('input[name="roleId"]').each(function(){
				 			$(this).bind('click',function(){
					 			checkOne('.table-selected-roles .checkboxall','.table-selected-roles .checkone');
					 		});
				 		});
				 		
				 	}		 		
				 	
				 	// $(".selected_users_movein").append(tr);
				 	tr.remove();
			 	});
	        }); 
		 $(".btn-query",ModelDialog.getCurrentModal()).bind("click",function(){
			 getSelectRoles(true);
	     }); 
		 $(".clearselectedrole_btn",ModelDialog.getCurrentModal()).bind("click",function(){
			 $('input[name="roleId"]:checked',$(".table-selected-roles")).each(function(){ 
				 	var tr = $(this).closest('tr');
				 	// console.log(tr);
				 	// console.log($(".selected_users_movein"));
				 	var selectedtr = $(".table-unselectrole input[value='"+$(this).val()+"']");
				 	// console.log(selectedtr);
				 	if(selectedtr.length == 0){
				 		
				 		tr.clone().insertAfter(".table-unselectrole tr:last");// 插入到已选表格的最后一行
				 		$(".table-unselectrole").find('input[name="roleId"]').each(function(){
				 			$(this).bind('click',function(){
					 			checkOne('.table-unselectrolet .checkboxall','.table-unselectrole .checkone');
					 		});
				 		});
				 	}		 		
				 	
				 	// $(".selected_users_movein").append(tr);
				 	tr.remove();
			 	});
	    }); 
		 $(".btn-submituserroles",ModelDialog.getCurrentModal()).bind("click",function(){
			 
			 var roleIds = "";
			 $('input[name="roleId"]:checked',$(".table-selected-roles")).each(function(){ 
				  	if(roleIds == "")
				  		roleIds += $(this).val();
				  	else
				  		roleIds += ","+$(this).val();
				 	
				 	
			 	});
//			 if(roleIds == "")
//			{
//				 ModelDialog.warn('请选择设置的角色!');
//				 return;
//			}	 
			
			 $.ajax({
		 		   type: "POST",
		 			url : usercontextpath+'/sysmanager/user/saveUserRoles.page',
		 			data :{"userId":userId,"roleIds":roleIds},
		 			dataType : 'json',
		 			async:false,
		 			beforeSend: function(XMLHttpRequest){ 					
		 				 	
		 			},
		 			success : function(responseText){
		 				
		 				if(responseText=="success"){
		 					
		 					PlatformCommonUtils.success("为用户添加角色成功!");
		 					ModelDialog.getCurrentModal().modal('hide');
		 					SysUser.afterSaveUser();
		 				}else{
		 					PlatformCommonUtils.warn("为用户添加角色成功:"+responseText);
		 				}
		 			}
		 		  });	 
			
	     });
	}
	 return {
		 initAuthmain:function(path,userId){
			 initAuthmain(path,userId);
		 }
	 }
}();