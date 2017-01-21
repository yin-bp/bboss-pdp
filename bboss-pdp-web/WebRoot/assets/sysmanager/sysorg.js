var SysOrg = function(){
	var usercontextpath;
	var validateDepart = function(){
		return Sysmanager.validateDepart();
	}
	var $currentmodal = null;
	var $modal = null;
	 var initModal = function(){
		 if($modal == null){
			 $modal = $('#ajax-org-action-extend');
			 $modal.draggable({
		            handle: ".modal-header"
		        });
		 }
	 }
	 var buildTreeLevel = function()
	 {
		 $.ajax({
	 		   type: "POST",
	 			url : usercontextpath+"/sysmanager/org/buildTreeLevel.page",
	 			data :{},
	 			dataType : 'json',
	 			async:false,
	 			beforeSend: function(XMLHttpRequest){ 					
	 				 	
	 				},
	 			success : function(responseText){
	 				
	 				if(responseText=="success"){
	 					
	 					PlatformCommonUtils.success("更新部门层级关系成功!");
	 					
	 				}else{
	 					PlatformCommonUtils.warn("更新部门层级关系失败:"+responseText);
	 				}
	 			}
	 		  });
	 }
	
	
	
	
	var initAddOrgButtonAction = function(){
  	 $("#sys_addOrg_button").bind('click',function(){
  		saveOrg("#form_sys_addorg");
  	 });
  	 $("#sys_addOrg_setleader_button").bind('click',function(){
  		ModelDialog.dialog({
  							title:"选择部门主管",
  							iframe:true,
  							url:usercontextpath+"/jsp/sysmanager/common/main.jsp",
  							width:"1260px",
  							height:"500px",
  							okCallBack:function(modal){
  								var $iframe = ModelDialog.getIframe();
  								var selectUsers = $iframe.contentWindow.SysChooseUser.getSelectUser();
  								if(selectUsers == null)
  								{
  									PlatformCommonUtils.warn("没有选择部门主管！");
  	  								return false;
  								}
  								else
  								{
  									//PlatformCommonUtils.success("部门主管:"+selectUsers);
  									//userId+":"+userRealname+":"+userName+":"+userWorknumber+":"+userMobiletel1
  									var user = selectUsers.split(":");
  									$('input[name="orgleaderName"]',$currentmodal).val(user[1]);
  									$('input[name="orgleader"]',$currentmodal).val(user[0]);
  									
  									return true;
  								}
  								
  							},
  							closeCallBack:undefined,
  							urlLoadCallBack:undefined
  			
  			});
   	 });
  	 
  	
   }
	
	var initModifyOrgButtonAction = function(){
	   	 $("#sys_modifyOrg_button").bind('click',function(){
	   		saveOrg("#form_sys_modifyorg");
	   	 });
	   	 
	   	$("#sys_modifyOrg_setleader_button").bind('click',function(){
	  		ModelDialog.dialog({
	  							title:"选择部门主管",
	  							iframe:true,
	  							url:usercontextpath+"/jsp/sysmanager/common/main.jsp",
	  							width:"1260px",
	  							height:"500px",
	  							okCallBack:function(modal){
	  								var $iframe = ModelDialog.getIframe();
	  								var selectUsers = $iframe.contentWindow.SysChooseUser.getSelectUser();
	  								if(selectUsers == null)
	  								{
	  									PlatformCommonUtils.warn("没有选择部门主管！");
	  	  								return false;
	  								}
	  								else
	  								{
	  									//PlatformCommonUtils.success("部门主管:"+selectUsers);
	  									//userId+":"+userRealname+":"+userName+":"+userWorknumber+":"+userMobiletel1
	  									var user = selectUsers.split(":");
	  									
	  									$('input[name="orgleaderName"]',$currentmodal ).val(user[1]);
	  									$('input[name="orgleader"]',$currentmodal ).val(user[0]);
	  									
	  									return true;
	  								}
	  								
	  							}
	  			
	  			});
	   	 });
	}
   
   var saveOrg = function (formid){
   	
		  
		
		   $(formid).submit();
		   
		   return false;
	   }
   
   var iniModifyOrgValidateform = function()
	{
		var form2 = $("#form_sys_modifyorg");		
		form2.validate({
					focusInvalid : false, // do not focus the last invalid
											// input
					errorElement: 'span', //default input error message container
		            errorClass: 'help-block help-block-error', // default input error message class
					ignore : "", // validate all fields including form hidden
									// input
					messages : {
						
						code : {
								minlength : jQuery.validator.format("部门编码不能小于{0}个字符"),
								required : "请输入部门编码"
							},

							orgName : {
								minlength : jQuery.validator.format("部门名称不能小于{0}个字符"),
								required : "请输入部门名称"
							}
					},
					rules : {
						code : {
							minlength : 2,
							required : true
						},

						orgName : {
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
						modifyorg()
						
					}
				});
	}
   var initAddOrgValidateform = function()
	{
		var form2 = $("#form_sys_addorg");		
		form2.validate({
					focusInvalid : false, // do not focus the last invalid
											// input
					errorElement: 'span', //default input error message container
		            errorClass: 'help-block help-block-error', // default input error message class
					ignore : "", // validate all fields including form hidden
									// input
					messages : {
						
						code : {
								minlength : jQuery.validator.format("部门编码不能小于{0}个字符"),
								required : "请输入部门编码"
							},

							orgName : {
								minlength : jQuery.validator.format("部门名称不能小于{0}个字符"),
								required : "请输入部门名称"
							}
					},
					rules : {
						code : {
							minlength : 2,
							required : true
						},

						orgName : {
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
						addorg()
						
					}
				});
	}
   var addorg = function()
	{
		$("#form_sys_addorg")
		.ajaxSubmit(
				{
					type : 'POST',
					url : usercontextpath+'/sysmanager/org/addSmOrganization.page',
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
						var title = '创建部门';
						var tiptype = "success";
						if (msg == 'success') {
							msg = "创建部门成功!"
						} else {
							 
							
						}
						PlatformCommonUtils.success(msg,function(){
							closeOrgActionModel();
							Sysmanager.refreshOrgTree(Sysmanager.getDepartId());  
						}) ;
						/**swal({
							  title: title,
							  text: msg,
							  type:tiptype,
							  confirmButtonClass: "btn-success",
							  confirmButtonText: "确定",
							},
							function(){
								closeOrgActionModel();
							});*/
						/**
						 bootbox.alert(msg, function() {
			                    alert("Hello world callback");
			                }); */

					}

				});
	}
	var modifyorg = function()
	{
		$("#form_sys_modifyorg")
		.ajaxSubmit(
				{
					type : 'POST',
					url : usercontextpath+'/sysmanager/org/updateSmOrganization.page',
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
						var title = '修改部门';
						var tiptype = "success";
						if (msg == 'success') {
							msg = "部门修改成功！"
						} else {
							 
							
						}
						PlatformCommonUtils.success(msg,function(){
							closeOrgActionModel();
						}) ;
						/**swal({
							  title: title,
							  text: msg,
							  type:tiptype,
							  confirmButtonClass: "btn-success",
							  confirmButtonText: "确定",
							},
							function(){
								closeOrgActionModel();
							});*/
						/**
						 bootbox.alert(msg, function() {
			                    alert("Hello world callback");
			                }); */

					}

				});
	}
    
    
   var viewOrg = function(orgId,orgName){
   	/**initModal();
   	 // https://github.com/jschr/bootstrap-modal
       $modal.load(usercontextpath+"/sysmanager/user/getSmOrganization.page", {
      	 "orgId":orgId
       }, function(){
      	 
           $modal.modal({
          	 backdrop:"static",
          	 width :"900px"
           });
          
         });*/
	   $currentmodal = ModelDialog.dialog({
			title:"查看机构-<span class=\"label label-sm label-success\">"+orgName+"</span>",
			
						
			disablecancelbutton:true,
			okbuttonText:"关闭",
			url:usercontextpath+"/sysmanager/org/getSmOrganization.page",
			params:{
		     	 "orgId":orgId
		      },
			width:"900px"

       });	
       
   }
   var tomodifyOrg = function(orgId,orgName){
	   $currentmodal = ModelDialog.dialog({
				title:"修改机构-<span class=\"label label-sm label-success\">"+orgName+"</span>",
				showfooter:false,
				url:usercontextpath+"/sysmanager/org/toUpdateSmOrganization.page",
				params:{
			     	 "orgId":orgId
			      },
				width:"900px"

	   });
	  
	   /**
   	initModal();
  	 // https://github.com/jschr/bootstrap-modal
      $modal.load(usercontextpath+"/sysmanager/org/toUpdateSmOrganization.page", {
     	 "orgId":orgId
      }, function(){
   	   $modal.on('hidden.bs.modal', function () {
        		 afterSaveOrg();
   			 });
          $modal.modal({
         	 backdrop:"static",
         	 width :"900px"
          });
         
        });*/
   }
	/**
	设置部门操作按钮
	 */
	var orgButtonMethods = function()
	{
		
		 var content_ = [
		                 {
		                     class: 'btn btn-xs btn-default',
		                     icon: 'fa fa-pencil',
		                     label:'查看',
		                     onClick: function() {		                    	
		                    	 var orgId = $(this).attr("orgId");
		                    	 var orgName = $(this).attr("orgName");
		                    	 SysOrg.viewOrg(orgId,orgName)
		                     }
		                   },
		                   {
		                     class: 'btn  btn-xs btn-default',
		                     icon: 'fa fa-pencil',
		                     label:'修改',
		                     onClick: function() {

		                    	 var orgId = $(this).attr("orgId");
		                    	 var orgName = $(this).attr("orgName");
		                    	 SysOrg.tomodifyOrg(orgId,orgName)
		                     }
		                   },
		                   /** {
		                     class: 'btn  btn-xs btn-default',
		                     icon: 'fa fa-pencil',
		                     label:'授权',
		                     onClick: function() {
		                    	 var orgId = $(this).attr("orgId");
		                    	 SysOrg.toauthOrg(orgId)
		                     }
		                   }*/
		                   ,
		                   {
		                     class: 'btn  btn-xs btn-default',
		                     icon: 'fa fa-pencil',
		                     label:'管理员设置' ,
		                     onClick: function() {
		                    	 var orgId = $(this).attr("orgId");
		                    	 var orgName = $(this).attr("orgName");
		                    	 orgmanagerset(orgId,orgName);
			                 }
		                   },
		                   /**   {
			                     class: 'btn  btn-xs btn-default',
			                     icon: 'fa fa-pencil',
			                     label:'岗位设置',
			                     onClick: function() {
			                    	 var orgId = $(this).attr("orgId");
			                    	 SysOrg.setOrgJob(userId)
				                 }
			                     
			                   },
		                  {
			                     class: 'btn btn-xs btn-danger',
			                     icon: 'fa fa-ban',
			                     label:'停用' ,
			                     onClick: function() {
			                    	 var orgId = $(this).attr("orgId");
			                    	 SysOrg.stopOrg(orgId)
				                 }
			               },*/
		                  
		                   {
		                	   class: 'btn  btn-xs btn-default',
		                	      icon: 'glyphicon glyphicon-remove',
		                	      cancel: true
			               }
		                 ];
		return content_;
	}
	var orgmanagerset = function(orgId,orgName){
		ModelDialog.dialog({
			title:"设置部门管理员-<span class=\"label label-sm label-success\">"+orgName+"</span>",
			showfooter:false,
			url:usercontextpath+"/sysmanager/org/orgmanagerset.page",
			params:{
		     	 "orgId":orgId
		      },
			width:"900px"

		});
	}
	var getOrgList = function (departId){
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
	         src: $("#datatable_orglist"),
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
	                  header: false,
	                  headerOffset: fixedHeaderOffset
	              },
	              "drawCallback": function( settings ) {
	             	 $('[data-toggle=org_ops_confirmation]').confirmation({
	             		  rootSelector: '[data-toggle=org_ops_confirmation]',
	             		  singleton:true,
	             		  
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
	   				      buttons:orgButtonMethods()
	             		});
	             	
	             	 
	                  //var api = this.api();
	           
	                  // Output the data for the visible rows to the browser's console
	                  //console.log( api.rows( {page:'current'} ).data() );
	              },
	              "pagingType": "bootstrap_extended",
	         	 "ordering": false,  "searching": false,
	         	 
	               // save datatable state(pagination, sort, etc) in cookie.
	             
	              // save custom filters to the state
	             "fnStateSaveParams":    function ( oSettings, sValue ) {
	                 $("#datatable_orglist tr.filter .form-control").each(function() {
	                     sValue[$(this).attr('name')] = $(this).val();
	                 });
	                
	                 return sValue;
	             },
	
	             // read the custom filters from saved state and populate the filter inputs
	             "fnStateLoadParams" : function ( oSettings, oData ) {
	                 //Load custom filters
	                 $("#datatable_orglist tr.filter .form-control").each(function() {
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
	                 "url": "../sysmanager/org/getDeparts.page", // ajax source
	                 "type": "POST",
	                 "data":function ( d ) {
	                     d.departId = departId;
	                     $("#datatable_orglist tr.filter .form-control").each(function() {
	                     	var v = $(this).val();
	                     	var n = $(this).attr('name');
	                     	if(v && v != '')
	                     		d[n] = v;
	                     });
	                 }
	             },
	             "columns": [
	                         { "data": "orgId","render": function ( data, type, full, meta ) {
	                        	 var chbx = "<label class=\"mt-checkbox mt-checkbox-single mt-checkbox-outline\"><input name=\"orgId\" type=\"checkbox\" class=\"checkboxes\" value=\""+
	     						data+"\""+"/><span></span></label>";
	                             return chbx;
	                            } 
	                         },
	                         { "data": "orgName" },
	                         { "data": "code" },
	                         { "data": "remark3","render": function ( data, type, full, meta ) {
	                             return data == '0'?"<span class=\"label label-sm label-danger\">禁用</span>":"<span class=\"label label-sm label-success\">有效</span>";
	                           	} 
	                         },	 
	                         { "data": "orgleaderRealName" },	 
	                         
	                         { "data": "orgId","render": function ( data, type, full, meta ) {
	                        	 var ops = "<button orgName=\""+full.orgName+"\" orgId=\""+data+"\" class=\"btn btn-outline btn-xs green-sharp  uppercase\" data-toggle=\"org_ops_confirmation\"  data-singleton=\"true\" data-placement=\"left\">操作</button>";
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
	
	var queryOrgList = function(departId,doquery){
		 if(departId)
			 Sysmanager.setDepartid(departId);
		 else
			 departId = Sysmanager.getDepartId();
		if(!doquery){
			$(".form-queryorgs .reset").click();
			$(".form-queryorgs input[name='departId']").val(departId);
		}
		else
		{
			
		}
	 
		$(".portlet_orglists").load(usercontextpath+"/sysmanager/org/queryListSmOrganizations.page",doquery?$(".form-queryorgs").serialize():{"departId":departId})
	}

	 var showOrgs = function(departId){
		 if(departId)
			 Sysmanager.setDepartid(departId);
		 else
			 departId = Sysmanager.getDepartId();
	   	   table = $( "#datatable_orglist" ).DataTable()
	   	   $("#datatable_orglist tr.filter .form-control").each(function() {
	             $(this).val('');
	      });
	   	   table.ajax.url( "../sysmanager/org/getDeparts.page?departId="+departId ).load();
      }
	 
	 
	
	
	var initAddOrgModalExtend = function(){
		 
		//initModal();
		 $('#button_sys_add_org').on('click', function(){
            // create the backdrop and wait for next modal to be triggered
           
			 $currentmodal = ModelDialog.dialog({
					title:"新增机构",
					showfooter:false,
					url:usercontextpath+"/sysmanager/org/toAddSmOrganization.page",
					params:{
						"departId":Sysmanager.getDepartId()
				      },
					width:"900px"

			 });
           /** 	  
            // https://github.com/jschr/bootstrap-modal
            $modal.load(usercontextpath+"/sysmanager/org/toAddSmOrganization.page", {
           	 "departId":Sysmanager.getDepartId()
            }, function(){
           	 $modal.on('hidden.bs.modal', function () {
           		 afterSaveOrg();
      			 });
                $modal.modal({
               	 backdrop:"static",
               	 width :"900px"
                });
               
              });*/
            
          });
		
	   	
  	 
	}
	
	
	
	var closeOrgActionModel = function(){
		//var $modal = $('#ajax-user-add').modal('hide');
		$currentmodal.modal('hide');
		 //$('#ajax-org-action-extend').modal('hide');
		queryOrgList();
	}
	var init = function(relativepath){
   	 usercontextpath = relativepath;
   	 initAddOrgModalExtend();
   	 $("#button_sys_delete_org").bind("click",function(){
   		 
   		var chk_value =""; 
        $('input[name="orgId"]:checked').each(function(){ 
        	if(chk_value == "")
        		chk_value+= ($(this).val()); 
        	else
        		chk_value+= ","+($(this).val()); 
        }); 
        if(chk_value == "")
        {
       	 	PlatformCommonUtils.warn("请选择要删除的部门!");
       	 	return;
        }
        
        PDP.confirm("确定要永久删除选中的部门吗?部门删除后，下属用户将变为待岗用户!拥有下级部门的部门不会被删除！",function(isConfirm){
	       	 	if(isConfirm)
	       	 	{       	 		 
	       	 		delorgs(chk_value);
	       	 	}
		        	
			});
   	 });
   	 //initAddUserModalExtend();
   	 //initDelUsers();
    }
	var delorgs = function(orgIds){
		$.ajax({
	 		   type: "POST",
	 			url : usercontextpath+"/sysmanager/org/deleteBatchSmOrganization.page",
	 			data :{"orgIds":orgIds},
	 			dataType : 'json',
	 			async:false,
	 			beforeSend: function(XMLHttpRequest){ 					
	 				 	
	 				},
	 			success : function(responseText){
	 				
	 				if(responseText.result=="success"){
	 					
	 					PDP.success(responseText.message);
	 					queryOrgList();
	 				}else{
	 					PDP.warn(responseText.message);
	 				}
	 			}
	 		  });
	}
	var initAddOrg = function(){
   	 initAddOrgButtonAction();
   	 initAddOrgValidateform();
    }
	var initModifyOrg = function(){
    	initModifyOrgButtonAction();
   	    iniModifyOrgValidateform();
    }
	return {
		init:function(relativepath){
			init(relativepath);
		},
		initAddOrg:function(){
			initAddOrg();
    	},
    	saveOrg:function(){
    		saveOrg();
    	},
    	viewOrg:function(departId,orgName){
    		viewOrg(departId,orgName);
    	},
    	tomodifyOrg:function(departId,orgName){
    		tomodifyOrg(departId,orgName);
    	},
    	initModifyOrg:function(){
    		initModifyOrg();
    	},
    	initDelOrgs:function(){
    		initDelOrgs();
    	},
		getOrgList:function(departId){
			getOrgList(departId);
		},
		showOrgs: function(departId){
			showOrgs(departId)
	      },
	      buildTreeLevel:function(){
	    	  buildTreeLevel();
	      },
	      queryOrgList:function(departId,doquery){
	    	  queryOrgList(departId,doquery);
	      },
	      orgmanagerset:function(orgId,orgName){
	    	  orgmanagerset(orgId,orgName);
	      }
	}
}();