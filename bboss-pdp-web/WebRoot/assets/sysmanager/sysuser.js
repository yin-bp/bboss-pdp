var SysUser = function(){
	var usercontextpath;
	var $currentmodal;
	var validateDepart = function(){
		return Sysmanager.validateDepart();
	}
	
	/**
	设置用户操作按钮
	 */
	var userButtonMethods = function()
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
		                     label:'修改密码',
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
	var tomodifyPassword = function(userId,userName,userAccount){
		 $currentmodal = ModelDialog.dialog({
				title:"修改用户口令-<span class=\"label label-sm label-success\">"+userName+"("+userAccount+")</span>",
				showfooter:false,
				url:usercontextpath+"/sysmanager/user/tomodifyPassword.page",
				params:{
					"userId":userId
			      },
				width:"400px",
				height:"300px"

		 });
	}
	var iniModifyPasswordValidateform = function()
	{
		var form2 = $("form",$currentmodal);		
		form2.validate({
					focusInvalid : false, // do not focus the last invalid
											// input
					ignore : "", // validate all fields including form hidden
									// input
					errorElement: 'span', //default input error message container
		            errorClass: 'help-block help-block-error', // default input error message class
					messages : {
						
					 
							oldPassword : {								
								required : "请输入旧口令"
							},

							newPassword : {
								minlength : jQuery.validator.format("应用名称不能小于{0}个字符"),
								required : "请输入6位以上口令"
							},

							newPasswordSecond : {
								minlength : jQuery.validator.format("二次输入口令不能小于{0}个字符"),
								required : "请二次输入口令"
							} ,
							
					},
					rules : {
					 
						oldPassword : {
							minlength : 1,
							required : true
						},

						newPassword : {
							minlength : 6,
							required : true
						},

						newPasswordSecond : {
							minlength : 6,
							required : true
						},

						
					},

					errorPlacement: function(error, element) {
		                if (element.is(':checkbox')) {
		                    error.insertAfter(element.closest(".md-checkbox-list, .md-checkbox-inline, .checkbox-list, .checkbox-inline"));
		                } else if (element.is(':radio')) {
		                    error.insertAfter(element.closest(".md-radio-list, .md-radio-inline, .radio-list,.radio-inline"));
		                } else {
		                    error.insertAfter(element); // for other inputs, just perform default behavior
		                }
		            },

		            highlight: function(element) { // hightlight error inputs
		                $(element)
		                    .closest('.form-group').addClass('has-error'); // set error class to the control group
		            },

		            unhighlight: function(element) { // revert the change done by hightlight
		                $(element)
		                    .closest('.form-group').removeClass('has-error'); // set error class to the control group
		            },

		            success: function(label) {
		                label
		                    .closest('.form-group').removeClass('has-error'); // set success class to the control group
		            },
					

					submitHandler : function(form) {
						// success1.show();
						modifypassword()
						
					}
				});
	}
	var modifypassword = function(){
		$("form",$currentmodal)
		.ajaxSubmit(
				{
					type : 'POST',
					url : usercontextpath+'/sysmanager/user/modifypassword.page',
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
						var title = '修改口令';
						var tiptype = "success";
						if (msg == 'success') {
							msg = "修改口令成功";
							PlatformCommonUtils.success(msg,function(){
								$currentmodal.modal('hide');
							}) ;
						} else {
							 
							PlatformCommonUtils.warn(msg) ;
						}
						
						

					}

				});
		
	}
	var initModifyUserPasswordAction = function(userId,userName){
		iniModifyPasswordValidateform();
		$(".passwordsave",$currentmodal).bind('click',function(){
			 if($("#newPassword",$currentmodal).val() != $("#newPasswordSecond",$currentmodal).val()){				
	   			PlatformCommonUtils.warn("两次口令不一致,请重新输入!") ;
	   			return false;
	   		}			  
			$("form",$currentmodal).submit();
			return false;
		});
		$(".passwordreset",$currentmodal).bind('click',function(){
			PlatformCommonUtils.confirm('确定要重置为默认口令123456吗？重置后请修改默认口令!',function(){
				$.ajax({
			 		   type: "POST",
			 			url : usercontextpath+"/sysmanager/user/resetpassword.page",
			 			data :{"userId":userId},
			 			dataType : 'json',
			 			async:false,
			 			beforeSend: function(XMLHttpRequest){ 					
			 				 	
			 				},
			 			success : function(responseText){
			 				
			 				if(responseText=="success"){
			 					
			 					PlatformCommonUtils.success("重置用户"+userName+"口令成功!");
			 					afterSaveUser();
			 				}else{
			 					PlatformCommonUtils.warn("重置用户"+userName+"口令失败:"+responseText);
			 				}
			 				$currentmodal.modal('hide');
			 			}
			 			
			 		  });
			});
		});
	}
	//初始化用户列表
	var getUserList = function (departId) {
		Sysmanager.setDepartid(departId);
		 var fixedHeaderOffset = 0;
	        if (App.getViewPort().width < App.getResponsiveBreakpoint('md')) {
	            if ($('.page-header').hasClass('page-header-fixed-mobile')) {
	                fixedHeaderOffset = $('.page-header').outerHeight(true);
	            } 
	        } else if ($('.page-header').hasClass('navbar-fixed-top')) {
	            fixedHeaderOffset = $('.page-header').outerHeight(true);
	        } else if ($('body').hasClass('page-header-fixed')) {
	            fixedHeaderOffset = 64; // admin 5 fixed height
	        }
        var grid = new Datatable();
       
        grid.init({
            src: $("#datatable_userlist"),
            onSuccess: function (grid, response) {
                // grid:        grid object
                // response:    json object of server side ajax response
                // execute some code after table records loaded
            },
            onError: function (grid) {
                // execute some code on network or other general error  
            },
            onDataLoad: function(grid) {
                // execute some code on ajax data load
            },
            loadingMessage: '加载中...',
            dataTable: { // here you can define a typical datatable settings from http://datatables.net/usage/options 

                // Uncomment below line("dom" parameter) to fix the dropdown overflow issue in the datatable cells. The default datatable layout
                // setup uses scrollable div(table-scrollable) with overflow:auto to enable vertical scroll(see: assets/global/scripts/datatable.js). 
                // So when dropdowns used the scrollable div should be removed. 
                //"dom": "<'row'<'col-md-8 col-sm-12'pli><'col-md-4 col-sm-12'<'table-group-actions pull-right'>>r>t<'row'<'col-md-8 col-sm-12'pli><'col-md-4 col-sm-12'>>",
            	 "dom": "<'row'<'col-md-4 col-sm-12'<'table-group-actions pull-right'>>r><'table-responsive't><'row'<'col-md-8 col-sm-12'lip><'col-md-4 col-sm-12'>>", // datatable layout
            	 // setup rowreorder extension: http://datatables.net/extensions/fixedheader/
                 fixedHeader: {
                     header: true,
                     headerOffset: fixedHeaderOffset
                 },
                 "drawCallback": function( settings ) {
                	 $('[data-toggle=user_ops_confirmation]').confirmation({
                		  rootSelector: '[data-toggle=user_ops_confirmation]',
                		  singleton:true,
                		  copyAttributes:"userId ops",
                		  template:'<div class="popover confirmation">' +
                	      '<div class="arrow"></div>' +
                	      
                	      '<div class="popover-content">' +
                	        '<p class="confirmation-content"></p>' +
                	        '<div class="confirmation-buttons">' +
                	          '<div class="btn-group">' +
                	            '<a href="#" class="btn" data-apply="confirmation"></a>' +
                	            '<a href="#" class="btn" data-dismiss="confirmation"></a>' +
                	          '</div>' +
                	        '</div>' +
                	      '</div>' +
                	    '</div>',
      				      buttons:userButtonMethods()
                		});
                	 
                	 
                 },
                 "pagingType": "bootstrap_extended",
            	 "ordering": false,  "searching": false,
            	 
                 // save custom filters to the state
                "fnStateSaveParams":    function ( oSettings, sValue ) {
                    $("#datatable_userlist tr.filter .form-control").each(function() {
                        sValue[$(this).attr('name')] = $(this).val();
                    });
                   
                    return sValue;
                },

                // read the custom filters from saved state and populate the filter inputs
                "fnStateLoadParams" : function ( oSettings, oData ) {
                    //Load custom filters
                    $("#datatable_userlist tr.filter .form-control").each(function() {
                        var element = $(this);
                        if (oData[element.attr('name')]) {
                            element.val( oData[element.attr('name')] );
                        }
                    });
                    
                    return true;
                },

                "lengthMenu": [
                    [5,10, 20, 50, 100, 150, -1],
                    [5,10, 20, 50, 100, 150, "All"] // change per page values here
                ],
                "pageLength": 20, // default record count per page
                "ajax": {
                    "url": "../sysmanager/user/getDepartUsers.page", // ajax source
                    "type": "POST",
                    "data":function ( d ) {
                        d.departId = departId;
                        $("#datatable_userlist tr.filter .form-control").each(function() {
                        	var v = $(this).val();
                        	var n = $(this).attr('name');
                        	if(v && v != '')
                        		d[n] = v;
                        });
                    }
                },
                "columns": [
                            { "data": "userId","render": function ( data, type, full, meta ) {
                            	if(!(full.defaultAdmin)){
	                        	 var chbx = "<label class=\"mt-checkbox mt-checkbox-single mt-checkbox-outline\"><input name=\"userId\" type=\"checkbox\" class=\"checkboxes\" value=\""+
		     						data+"\""+"/><span></span></label>";
		                             return chbx;
                            	}
                            	else
                            		return "";
		                      }
                            },
                            { "data": "userName" },
                            { "data": "userRealname" },
                            { "data": "userWorknumber" },
                             { "data": "userMobiletel1" },
                            
                            { "data": "userSex","render": function ( sexCode, type, full, meta ) {
	                        	  
                            	if(sexCode == null || sexCode==("-1"))
                    				return "未知";
                    			else if( sexCode==("F"))
                    				return "女";
                    			else if(  sexCode==("M"))
                    				return "男";
                    			else 
                    				return "未知";
	                          
	                        	}
                             },
                            { "data": "userType","render": function ( userType, type, full, meta ) {
	                        	  
	                        	    if(userType == null || userType == ("0"))
		                 				return "系统用户";
		                 			else if(  userType == ("1"))
		                 				return "域用户";
		                 			else if( userType == ("2"))
		                 				return "第三方用户";
		                 			else 
		                 				return "第三方用户";
		                          
		                        }
                            },
                            
                            { "data": "userIsvalid","render": function ( state, type, full, meta ) {
	                        	 var chbx = "";
	                        	 if(state == 2)
	                        		 chbx = "<span class=\"label label-sm label-success\">开通</span>";
	                     		else if(state == 3)
	                     			chbx = "<span class=\"label label-sm label-warning\">停用</span>";
	                     		else if(state == 0)
	                     			chbx = "<span class=\"label label-sm label-danger\">删除</span>";
	                     		else if(state == 1)
	                     			chbx = "<span class=\"label label-sm label-info\">申请</span>";
	                     		else
	                     			chbx = "<span class=\"label label-sm label-warning\">未知</span>";
		                         return chbx;
		                        }
                            },
                            { "data": "departName","render": function ( departName, type, full, meta ) {
                            	
                            	if(departName == null || departName == '')
                            		return "待岗"
                            	else
                            		return departName;
	                        	 
		                        }
                            },
                            { "data": "userId","render": function ( data, type, full, meta ) {
	                        	 var ops = "<button userName=\""+full.userRealname+"\" userAccount=\""+full.userName+"\" defaultAdmin=\""+full.defaultAdmin+"\" userId=\""+data+"\" class=\"btn btn-outline btn-xs green-sharp  uppercase\" data-toggle=\"user_ops_confirmation\"  data-singleton=\"true\" data-placement=\"left\">操作</button>";
	                             return ops;
	                           	} 
                            }
                        ],
                "order": [
                    [1, "asc"]
                ]// set first column as a default sort by asc
            }
        });
     
    }
	var afterSaveUser = function()
	{
		showUsers(Sysmanager.getDepartId());
	}
	
	var initAddUserModalExtend = function(){
		 
		//initModal();
		 $('#button_sys_add_user').on('click', function(){
            // create the backdrop and wait for next modal to be triggered
           
            var el = $(this);
            var vr = validateDepart();
           
             if(!vr)
            {
           	  return;
            }	
             
             $currentmodal = ModelDialog.dialog({
 				title:"新增用户",
 				showfooter:false,
 				url:usercontextpath+"/sysmanager/user/toAddSmUser.page",
 				params:{
 					"departId":Sysmanager.getDepartId()
 			      },
 				width:"900px",
 				height:"600px"

             });
            // https://github.com/jschr/bootstrap-modal
          
            
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
		    	
		    handleMultiSelect();
		   // SysUser.showMoveInUsers(departid);
		    
		    
		});
	}
	var handleMultiSelect = function () {
        $('#my_multi_select1').multiSelect();
       
    }
	var initMoveUserAction = function() {	 
			$(".tree-org-moveuserout",ModelDialog.getCurrentModal()).jstree({
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
		 
		 $(".tree-org-moveuserout",ModelDialog.getCurrentModal()).bind("activate_node.jstree", function (obj, e) {
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
			    	
			    $("input[name='selectdepart']",ModelDialog.getCurrentModal()).val(departid);
			    
			});
		 
		 $(".moveok",ModelDialog.getCurrentModal()).bind('click',function(){
			 var todepartId = $("input[name='selectdepart']",ModelDialog.getCurrentModal()).val();
			 var fromdepartId = Sysmanager.getDepartId();
			 if(todepartId == null || todepartId == '' || todepartId == '0'){
				 ModelDialog.warn('请选择要调入的部门');
				 return;
			 }
			 if(fromdepartId == todepartId){
				 ModelDialog.warn('调入的部门和原部门不能是一个部门!');
				 return;
			 }
			 var userIds;
	         $('input[name="userId"]:checked',$("#datatable_userlist")).each(function(){ 
	        	 if(userIds != null)
	    				userIds += ","+$(this).val();
	    			else
	    				userIds = $(this).val();
	        	 
	         }); 
			 
			 $.ajax({
		 		   type: "POST",
		 			url : usercontextpath+'/sysmanager/user/saveMoveusers.page',
		 			data :{"userIds":userIds,"fromdepartId":Sysmanager.getDepartId(),"todepartId":todepartId},
		 			dataType : 'json',
		 			async:false,
		 			beforeSend: function(XMLHttpRequest){ 					
		 				 	
		 				},
		 			success : function(responseText){
		 				
		 				if(responseText=="success"){
		 					
		 					PlatformCommonUtils.success("调出用户成功!");
		 					ModelDialog.getCurrentModal().modal('hide');
		 					afterSaveUser();
		 				}else{
		 					PlatformCommonUtils.warn("调出用户失败:"+responseText);
		 				}
		 			}
		 		  });
			 
		 });
	
			
	}
	
	var initMoveUserModalExtend = function(){
		 
		//initModal();
		 $('#button_sys_moveout_user').on('click', function(){
            // create the backdrop and wait for next modal to be triggered
           
          
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
            	 PlatformCommonUtils.warn("请选择要调出的用户!");
            	 return;
             }
             //调出用户
             $currentmodal = ModelDialog.dialog({
 				title:"选择调出部门&nbsp;&nbsp;<a class=\"btn btn-sm blue moveok\"> 确定 <i class=\"fa fa-edit\"></i></a>",
 				showfooter:false,
 				url:usercontextpath+"/sysmanager/user/toMoveOutSmUser.page",
 				params:{
 					"fromdepartId":Sysmanager.getDepartId()
 			      },
 				width:"500px",
 				height:"400px"

             });
            // https://github.com/jschr/bootstrap-modal
          
            
          });
		 //调入用户
		 $('#button_sys_movein_user').on('click', function(){
	            // create the backdrop and wait for next modal to be triggered
	           
	          
	            var vr = validateDepart();
	           
	             if(!vr)
	            {
	           	  return;
	            }	
	             
	             $currentmodal = ModelDialog.dialog({
	 				title:"用户调入",
	 				showfooter:false,
	 				url:usercontextpath+"/sysmanager/user/toMoveInSmUser.page",
	 				params:{
	 					"toDepartId":Sysmanager.getDepartId()
	 			      },
	 				width:"1024px",
	 				height:"600px"

	             });
	            // https://github.com/jschr/bootstrap-modal
	          
	            
	          });
		
	   	
  	 
	}
	//绑定用户排序按钮打开排序页面功能
	var initUserOrderModalExtend = function(){
		 
		//initModal();
		 $('#button_sys_order_user').on('click', function(){
            // create the backdrop and wait for next modal to be triggered
           
            var el = $(this);
            var vr = validateDepart();
           
             if(!vr)
            {
           	  return;
            }	
             
             $currentmodal = ModelDialog.dialog({
 				title:"用户排序-通过鼠标拖拽表格记录行来实现排序-点击保存排序即可保存最新的顺序",
 				showfooter:false,
 				iframe:false,
 				url:usercontextpath+"/sysmanager/user/toOrderSmUser.page?departId="+Sysmanager.getDepartId(),
 				params:{
 					"departId":Sysmanager.getDepartId()
 			      },
 				width:"900px",
 				height:"600px"

             });
            // https://github.com/jschr/bootstrap-modal
          
            
          });
		
	   	
  	 
	}
	
	var initUserOrderTable = function (hasrecords) {
        $('table',ModelDialog.getCurrentModal()).DataTable( {
        	paging: false,
    		rowReorder: { selector: 'tr'},
    		/**columnDefs: [
    		             { targets: 0, visible: false }
    		         ],*/
	         "ordering": true,  "searching": false,
	          
	               "columns": [{
	                   "orderable": false,
	                   "visible": true 
	               }, {
	                   "orderable": false
	               }, {
	                   "orderable": false
	               }, {
	                   "orderable": false
	               }, {
	                   "orderable": false
	               }, {
	                   "orderable": false
	               }, {
	                   "orderable": false
	               }, {
	                   "orderable": false
	               }, {
	                   "orderable": false
	               }],
	         "lengthMenu": [
	                        [10, 20, 50, 100, 150, -1],
	                        [10, 20, 50, 100, 150, "All"] // change per page values here
	                    ],
	                    "pageLength": 20 // default record count per page
        	          
    	} );
        $('.sys_modifyUser_button',ModelDialog.getCurrentModal()).bind('click',function(){
        	if(!hasrecords){
        		PlatformCommonUtils.warn("没有需要排序的记录") ;
        	}
        	else
    		{
        		$('form',ModelDialog.getCurrentModal())
        		.ajaxSubmit(
        				{
        					type : 'POST',
        					url : usercontextpath+'/sysmanager/user/saveSmUsersOrder.page',
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
        						var title = '用户排序';
        						var tiptype = "success";
        						if (msg == 'success') {
        							msg = "用户排序完毕"
        							PlatformCommonUtils.success(msg,function(){
        								closeUserActionModel();
        								afterSaveUser();
        							}) ;
        						} else {
        							 
        							PlatformCommonUtils.warn(msg) ;
        						}
        						
        						

        					}

        				});
    		}
        });
    }
	
	var closeUserActionModel = function(){
		//var $modal = $('#ajax-user-add').modal('hide');
		ModelDialog.getCurrentModal().modal('hide');
		 //$('#ajax-user-action-extend').modal('hide');
		
	}
	var initAddusersButtonAction = function(){
   	 $("#sys_addUser_button").bind('click',function(){
   		 saveUser("#form_sys_adduser",false);
   	 });
   	/**$("input[name='userBirthday']",$("#form_sys_adduser")).bind('click',function(){
   		WdatePicker();
  	 });*/
   	
    }
	
	var initModifyUserButtonAction = function(){
	   	 $("#sys_modifyUser_button").bind('click',function(){
	   		saveUser("#form_sys_modifyuser",true);
	   	 });
	    }
    
    var saveUser = function (formid,update){
    	
    	if(!update)
    	{
    		 if($("#userPassword").val() != $("#userPasswordSecond").val()){				
   			  PlatformCommonUtils.warn("两次口令不一致") ;
   			
   				return false;
   			}
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
					errorElement: 'span', //default input error message container
		            errorClass: 'help-block help-block-error', // default input error message class
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

					errorPlacement: function(error, element) {
		                if (element.is(':checkbox')) {
		                    error.insertAfter(element.closest(".md-checkbox-list, .md-checkbox-inline, .checkbox-list, .checkbox-inline"));
		                } else if (element.is(':radio')) {
		                    error.insertAfter(element.closest(".md-radio-list, .md-radio-inline, .radio-list,.radio-inline"));
		                } else {
		                    error.insertAfter(element); // for other inputs, just perform default behavior
		                }
		            },

		            highlight: function(element) { // hightlight error inputs
		                $(element)
		                    .closest('.form-group').addClass('has-error'); // set error class to the control group
		            },

		            unhighlight: function(element) { // revert the change done by hightlight
		                $(element)
		                    .closest('.form-group').removeClass('has-error'); // set error class to the control group
		            },

		            success: function(label) {
		                label
		                    .closest('.form-group').removeClass('has-error'); // set success class to the control group
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
					errorElement: 'span', //default input error message container
		            errorClass: 'help-block help-block-error', // default input error message class
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

					errorPlacement: function(error, element) {
		                if (element.is(':checkbox')) {
		                    error.insertAfter(element.closest(".md-checkbox-list, .md-checkbox-inline, .checkbox-list, .checkbox-inline"));
		                } else if (element.is(':radio')) {
		                    error.insertAfter(element.closest(".md-radio-list, .md-radio-inline, .radio-list,.radio-inline"));
		                } else {
		                    error.insertAfter(element); // for other inputs, just perform default behavior
		                }
		            },

		            highlight: function(element) { // hightlight error inputs
		                $(element)
		                    .closest('.form-group').addClass('has-error'); // set error class to the control group
		            },

		            unhighlight: function(element) { // revert the change done by hightlight
		                $(element)
		                    .closest('.form-group').removeClass('has-error'); // set error class to the control group
		            },

		            success: function(label) {
		                label
		                    .closest('.form-group').removeClass('has-error'); // set success class to the control group
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
    	 initUserOrderModalExtend();
    	 initMoveUserModalExtend();
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
						PlatformCommonUtils.warn(thrownError) ;
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
							PlatformCommonUtils.success(msg,function(){
								closeUserActionModel();
								afterSaveUser();
							}) ;
						} else {
							 
							PlatformCommonUtils.warn(msg) ;
						}
						
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
						PlatformCommonUtils.warn(thrownError) ;
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
							PlatformCommonUtils.success(msg,function(){
								closeUserActionModel();
								afterSaveUser();
							}) ;
						} else {
							PlatformCommonUtils.warn(msg) ;
							
						}
						
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
     
     
    var viewUser = function(userId,userAccount){
    	//initModal();
    	 // https://github.com/jschr/bootstrap-modal
       /** $modal.load(usercontextpath+"/sysmanager/user/getSmUser.page", {
       	 "userId":userId
        }, function(){
       	 
            $modal.modal({
           	 backdrop:"static",
           	 width :"900px"
            });
           
          });*/
        
        $currentmodal = ModelDialog.dialog({
				title:"查看用户<span class=\"label label-sm label-success\">"+userAccount+"</span>",
				showfooter:false,
				url:usercontextpath+"/sysmanager/user/getSmUser.page",
				params:{
					"userId":userId
			      },
				width:"900px",
				height:"400px"

         });
    }
    var tomodifyUser = function(userId,userAccount){
    	//initModal();
   	 // https://github.com/jschr/bootstrap-modal
      /** $modal.load(usercontextpath+"/sysmanager/user/toUpdateSmUser.page", {
      	 "userId":userId
       }, function(){
    	   $modal.on('hidden.bs.modal', function () {
         		 afterSaveUser();
    			 });
           $modal.modal({
          	 backdrop:"static",
          	 width :"900px"
           });
          
         });*/
       
       $currentmodal = ModelDialog.dialog({
			title:"修改用户<span class=\"label label-sm label-success\">"+userAccount+"</span>",
			showfooter:false,
			url:usercontextpath+"/sysmanager/user/toUpdateSmUser.page",
			params:{
				"userId":userId
		      },
			width:"900px",
			height:"500px"
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
    /**
     * user_updatetype:3 -停用 0 - 逻辑删除 1 - 物理删除  2- 启用用户
     */
    var updateUserStatus = function(userId,user_updatetype){
    	$.ajax({
  		   type: "POST",
  			url : usercontextpath+"/sysmanager/user/updateUserStatus.page",
  			data :{"userId":userId,"user_updatetype":user_updatetype},
  			dataType : 'json',
  			async:false,
  			beforeSend: function(XMLHttpRequest){ 					
  				 	
  				},
  			success : function(responseText){
  				
  				if(responseText=="success"){
  					var msg =  ""
  					if(user_updatetype == '3')
  					{
  						msg = "停用用户成功!";
  					}
  					else if(user_updatetype == '0' || user_updatetype == '1')
  					{
  						msg = "删除用户成功!";
  					}
  					else if(user_updatetype == '2' )
  					{
  						msg = "启用用户成功!";
  					}
  					PlatformCommonUtils.success(msg);
  					afterSaveUser();
  				}else{
  					var msg =  ""
  	  					if(user_updatetype == '3')
  	  					{
  	  						msg = "停用用户失败：";
  	  					}
  	  					else if(user_updatetype == '0' || user_updatetype == '1')
  	  					{
  	  						msg = "删除用户失败：!";
  	  					}
  	  					else if(user_updatetype == '2' )
  	  					{
  	  						msg = "启用用户失败：";
  	  					}
  					PlatformCommonUtils.warn(msg+responseText);
  				}
  			}
  		  });
    }
    var showUsers = function(departId){
		 if(departId)
			 Sysmanager.setDepartid(departId);
		 else
			 departId = Sysmanager.getDepartId();
		 var table = $( "#datatable_userlist" ).DataTable()
	   	   $("#datatable_userlist tr.filter .form-control").each(function() {
	             $(this).val('');
	      });
	   	   table.ajax.url( "../sysmanager/user/getDepartUsers.page?departId="+departId ).load();
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
    	viewUser:function(userId,userName){
    		viewUser(userId,userName);
    	},
    	tomodifyUser:function(userId,userAccount){
    		tomodifyUser(userId,userAccount);
    	},
    	initModifyUser:function(){
    		initModifyUser();
    	},
    	initDelUsers:function(){
    		initDelUsers();
    	},
    	stopordelUser:function(userId){
    		stopordelUser(userId);
    	},
    	getUserList:function(departId){
			getUserList(departId);
		},		 
		 showUsers:function(departId){
			 showUsers(departId);
		 },
		 tomodifyPassword:function(userId,userName,userAccount){
			 tomodifyPassword(userId,userName,userAccount);
		 },
		 initModifyUserPasswordAction:function(userId,userName){
			 initModifyUserPasswordAction(userId,userName);
		 },
		 initUserOrderModalExtend:function(){
			 initUserOrderModalExtend();
		 },
		 initUserOrderTable:function(hasrecords){
			 initUserOrderTable(hasrecords);
		 },
		 initMoveUserAction:function(){
			 initMoveUserAction();
		 },
		 initMoveUserInAction:function(){
			 initMoveUserInAction();
		 }
    	
    	
    	
    }


}();

