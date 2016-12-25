var SysAuthmain = function(){
	var usercontextpath ;
	var initAuthmain = function(path){
		usercontextpath = path;
		getSelectRoles(false);
	}
	var getSelectRoles = function(doquery){
		$(".select_roles",ModelDialog.getCurrentModal()).load((usercontextpath+"/sysmanager/role/queryListInfoRoles.page",
				doquery?$('.form-queryrole').serialize():{},
					function(){
					
					});
	}
	
	$(".dotempadd_btn",ModelDialog.getCurrentModal()).bind("click",function(){
		 $('input[name="userId"]:checked',$(".table-moveinuserlist")).each(function(){ 
			 	var tr = $(this).closest('tr');
			 	//console.log(tr);
			 	//console.log($(".selected_users_movein"));
			 	var selectedtr = $(".table-selected-users input[value='"+$(this).val()+"']");
			 	//console.log(selectedtr);
			 	if(selectedtr.length == 0){
			 		
			 		
			 		tr.clone().insertAfter(".table-selected-users tr:last");//插入到已选表格的最后一行
			 		$(".table-selected-users").find('input[name="userId"]').each(function(){
			 			$(this).bind('click',function(){
				 			checkOne('.table-selected-users .checkboxall','.table-selected-users .checkone');
				 		});
			 		});
			 		
			 	}		 		
			 	
			 	//$(".selected_users_movein").append(tr);
			 	tr.remove();
		 	});
        }); 
	 $(".btn-query",ModelDialog.getCurrentModal()).bind("click",function(){
		 getSelectRoles(true);
     }); 
	 $(".clearselecteduser_btn",ModelDialog.getCurrentModal()).bind("click",function(){
		 $('input[name="userId"]:checked',$(".table-selected-users")).each(function(){ 
			 	var tr = $(this).closest('tr');
			 	//console.log(tr);
			 	//console.log($(".selected_users_movein"));
			 	var selectedtr = $(".table-moveinuserlist input[value='"+$(this).val()+"']");
			 	//console.log(selectedtr);
			 	if(selectedtr.length == 0){
			 		
			 		tr.clone().insertAfter(".table-moveinuserlist tr:last");//插入到已选表格的最后一行
			 		$(".table-moveinuserlist").find('input[name="userId"]').each(function(){
			 			$(this).bind('click',function(){
				 			checkOne('.table-moveinuserlist .checkboxall','.table-moveinuserlist .checkone');
				 		});
			 		});
			 	}		 		
			 	
			 	//$(".selected_users_movein").append(tr);
			 	tr.remove();
		 	});
    }); 
	 $(".btn-submitmoveinusers",ModelDialog.getCurrentModal()).bind("click",function(){
		 var fromDepartId = $("input[name='fromDepartId']",ModelDialog.getCurrentModal()).val();
		 var toDepartId = $("input[name='toDepartId']",ModelDialog.getCurrentModal()).val();
		 if(fromDepartId == toDepartId){
			 ModelDialog.warn('原部门和目的部门不能是同一个单位!');
			 return;
		 }
		 var userIds = "";
		 $('input[name="userId"]:checked',$(".table-selected-users")).each(function(){ 
			  	if(userIds == "")
			  		userIds += $(this).val();
			  	else
			  		userIds += ","+$(this).val();
			 	
			 	
		 	});
		 if(userIds == "")
		{
			 ModelDialog.warn('请选择要调入的用户!');
			 return;
		}	 
		
		 $.ajax({
	 		   type: "POST",
	 			url : usercontextpath+'/sysmanager/user/saveMoveusers.page',
	 			data :{"userIds":userIds,"fromDepartId":fromDepartId,"toDepartId":toDepartId},
	 			dataType : 'json',
	 			async:false,
	 			beforeSend: function(XMLHttpRequest){ 					
	 				 	
	 			},
	 			success : function(responseText){
	 				
	 				if(responseText=="success"){
	 					
	 					PlatformCommonUtils.success("调入用户成功!");
	 					ModelDialog.getCurrentModal().modal('hide');
	 					SysUser.afterSaveUser();
	 				}else{
	 					PlatformCommonUtils.warn("调入用户失败:"+responseText);
	 				}
	 			}
	 		  });	 
		
     }); 
}();