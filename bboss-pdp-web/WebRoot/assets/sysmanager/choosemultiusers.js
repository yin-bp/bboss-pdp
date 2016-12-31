var SysChoosemultiUsers = function(){
	var usercontextpath;
	var init = function(relativepath){
   	    usercontextpath = relativepath;
	}
	var loadMoveUsers = function(departId){
		$(".reset",ModelDialog.getCurrentModal()).trigger("click");
		$(".select_users_movein",ModelDialog.getCurrentModal()).load(usercontextpath+'/sysmanager/user/moveinuserlist.page',
				{'fromDepartId':departId,'recursive':'0'},function(){
					
		});
	}
	var initMoveUserInAction = function() {
		$(".tree-org-moveuserin",ModelDialog.getCurrentModal()).jstree({
            "core" : {
                "themes" : {
                    "responsive": true
                }, 
                // so that create works
                "check_callback" : true,
                'data' : {
                    'url' : function (node) {
                      return usercontextpath+'/sysmanager/org/getChildrens.page';
                    },
                    'data' : function (node) {
                      return { 'parent' : node.id,'isuser':true };
                    }
                }
            },
            "types" : {
                "default" : {
                    "icon" : "fa fa-folder icon-state-warning icon-lg"
                },
                "lisan":{
                	"icon" : "fa fa-list icon-state-warning icon-lg"
                }
                
            },
            
            "plugins" : [   "types" ]
        });
	 
		 $(".tree-org-moveuserin",ModelDialog.getCurrentModal()).bind("activate_node.jstree", function (obj, e) {
			    // 处理代码
			    // 获取当前节点
			    var currentNode = e.node;
			    //console.table(currentNode);
			    //console.table(obj)
			   // console.dir(currentNode);
			    var departid = currentNode.id;
			    
			    if(currentNode.parent == "#" && departid != 'lisan'){			    	
			    	departid = '0';
			    }
			   
			    $("input[name='fromDepartId']",ModelDialog.getCurrentModal()).val(departid)	;
			    loadMoveUsers(departid);
			    
			    
			    
			});
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
			 var recursive = $("select[name='recursive']",ModelDialog.getCurrentModal()).val();
			 
			 if(recursive == '0' || recursive == '1')
			 {
				 var fromDepartId = $("input[name='fromDepartId']",ModelDialog.getCurrentModal()).val();
				 //console.log(fromDepartId);
				 if(fromDepartId == null || fromDepartId == ''){
					 ModelDialog.warn('请选择要查询的部门!');
					 return;
				 }
					 
			 }	 
			 $(".select_users_movein",ModelDialog.getCurrentModal()).load(usercontextpath+'/sysmanager/user/moveinuserlist.page',
						$('.form-querymoveusers',ModelDialog.getCurrentModal()).serialize(),function(){
							
				});
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
		 
	
	}
	var getSelectUsers = function(){
		 var userIds = "";
		 $('.table-selected-users input[name="userId"]:checked',ModelDialog.getCurrentModal()).each(function(){ 
			    var u = $(this).val()+":"+$(this).attr("userName")+":"+$(this).attr("userWorknumber")+":"+$(this).attr("userRealname");
			  	if(userIds == "")
			  		userIds += u;
			  	else
			  		userIds += ","+u;
			 	
			 	
		 	});
		 return userIds;		 
	}
	 
	 
	return {		 
		 initMoveUserInAction:function(){
			 initMoveUserInAction();
		 },
		 init:function(contextpath){
			 init(contextpath);
		 },
		 getSelectUsers:function(){
			 return getSelectUsers();
		 }		 
	}
	
}();