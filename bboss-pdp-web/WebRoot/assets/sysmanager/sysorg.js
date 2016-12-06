var SysOrg = function(){
	var usercontextpath;
	var validateDepart = function(){
		return Sysmanager.validateDepart();
	}
	var $modal;
	 var initModal = function(){
		 if($modal == null){
			 $modal = $('#ajax-org-action-extend');
			 $modal.draggable({
		            handle: ".modal-header"
		        });
		 }
	 }
	var initAddOrgModalExtend = function(){
		 
		initModal();
		 $('#button_sys_add_org').on('click', function(){
           // create the backdrop and wait for next modal to be triggered
          
           var el = $(this);
           var vr = validateDepart();
          
            if(!vr)
           {
          	  return;
           }	  
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
              
             });
           
         });
		
	   	
 	 
	}
	
	
	
	var closeOrgActionModel = function(){
		//var $modal = $('#ajax-user-add').modal('hide');
		
		 $('#ajax-org-action-extend').modal('hide');
		
	}
	var initAddOrgButtonAction = function(){
  	 $("#sys_addOrg_button").bind('click',function(){
  		saveOrg("#form_sys_addorg");
  	 });
   }
	
	var initModifyOrgButtonAction = function(){
	   	 $("#sys_modifyOrg_button").bind('click',function(){
	   		saveOrg("#form_sys_modifyorg");
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
    
    
   var viewOrg = function(orgId){
   	initModal();
   	 // https://github.com/jschr/bootstrap-modal
       $modal.load(usercontextpath+"/sysmanager/user/getSmOrganization.page", {
      	 "orgId":orgId
       }, function(){
      	 
           $modal.modal({
          	 backdrop:"static",
          	 width :"900px"
           });
          
         });
   }
   var tomodifyOrg = function(orgId){
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
         
        });
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
		                    	 SysOrg.viewOrg(orgId)
		                     }
		                   },
		                   {
		                     class: 'btn  btn-xs btn-default',
		                     icon: 'fa fa-pencil',
		                     label:'修改',
		                     onClick: function() {

		                    	 var orgId = $(this).attr("orgId");
		                    	 SysOrg.tomodifyOrg(orgId)
		                     }
		                   },
		                   {
		                     class: 'btn  btn-xs btn-default',
		                     icon: 'fa fa-pencil',
		                     label:'授权',
		                     onClick: function() {
		                    	 var orgId = $(this).attr("orgId");
		                    	 SysOrg.toauthOrg(orgId)
		                     }
		                   },
		                   {
		                     class: 'btn  btn-xs btn-default',
		                     icon: 'fa fa-pencil',
		                     label:'主管设置' ,
		                     onClick: function() {
		                    	 var orgId = $(this).attr("orgId");
		                    	 SysOrg.setOrgleader(orgId)
			                  }
		                   }
		                   ,
		                   {
		                     class: 'btn  btn-xs btn-default',
		                     icon: 'fa fa-pencil',
		                     label:'管理员设置' ,
		                     onClick: function() {
		                    	 var orgId = $(this).attr("orgId");
		                    	 SysOrg.setOrgManager(orgId)
			                 }
		                   },
		                   {
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
			               },
		                  
		                   {
		                	   class: 'btn  btn-xs btn-default',
		                	      icon: 'glyphicon glyphicon-remove',
		                	      cancel: true
			                   }
		                 ];
		return content_;
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
	                  header: true,
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
	         	 "language": { // language settings
	                  // metronic spesific
	                  "metronicGroupActions": "_TOTAL_ 条记录被选中:  ",
	                  "metronicAjaxRequestGeneralError": "请求提交失败. 请检查服务是否启动或者网络是否断连！",
	
	                  // data tables spesific
	                  "lengthMenu": "每页 _MENU_ 条记录",
	                  "info": "共 _TOTAL_ 条记录",
	                  "infoEmpty": "没有找到记录",
	                  "emptyTable": "没有找到记录",
	                  "zeroRecords": "没有匹配的记录",
	                  "paginate": {
	                      "previous": "上一页",
	                      "next": "下一页",
	                      "last": "尾页",
	                      "first": "首页",
	                      "page": "第",
	                      "pageOf": "页/"
	                  }
	              },
	               // save datatable state(pagination, sort, etc) in cookie.
	             "bStateSave": false, 
	             "processing": false,
	             "serverSide": true,
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
	                        	 var ops = "<button orgId=\""+data+"\" class=\"btn btn-outline btn-xs green-sharp  uppercase\" data-toggle=\"org_ops_confirmation\"  data-singleton=\"true\" data-placement=\"left\">操作</button>";
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
	 
	 
	 var $modal;
	 var initModal = function(){
		 if($modal == null){
			 $modal = $('#ajax-org-action-extend');
			 $modal.draggable({
		            handle: ".modal-header"
		        });
		 }
	 }
	 var afterSaveOrg = function()
	{
		showOrgs(Sysmanager.getDepartId());
	}
	var initAddOrgModalExtend = function(){
		 
		initModal();
		 $('#button_sys_add_org').on('click', function(){
            // create the backdrop and wait for next modal to be triggered
           
           
            	  
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
               
              });
            
          });
		
	   	
  	 
	}
	
	
	
	var closeOrgActionModel = function(){
		//var $modal = $('#ajax-user-add').modal('hide');
		
		 $('#ajax-org-action-extend').modal('hide');
		
	}
	var init = function(relativepath){
   	 usercontextpath = relativepath;
   	 initAddOrgModalExtend();
   	 //initAddUserModalExtend();
   	 //initDelUsers();
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
    	viewOrg:function(userId){
    		viewOrg(userId);
    	},
    	tomodifyOrg:function(userId){
    		tomodifyOrg(userId);
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
	      }
	}
}();