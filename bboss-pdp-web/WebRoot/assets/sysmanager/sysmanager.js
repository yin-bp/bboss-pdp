var Sysmanager = new function(){
	var departid;
	var setDepartid = function (orgid){
		departid = orgid;
	}
	var getDepartId = function(){
		return departid;
	}
	//初始化组织机构树
	var initorg_tree = function(){
		 $("#org_tree").jstree({
	            "core" : {
	                "themes" : {
	                    "responsive": false
	                }, 
	                // so that create works
	                "check_callback" : true,
	                'data' : {
	                    'url' : function (node) {
	                      return '../sysmanager/org/getChildrens.page';
	                    },
	                    'data' : function (node) {
	                      return { 'parent' : node.id };
	                    }
	                }
	            },
	            "types" : {
	                "default" : {
	                    "icon" : "fa fa-folder icon-state-warning icon-lg"
	                },
	                "file" : {
	                    "icon" : "fa fa-file icon-state-warning icon-lg"
	                }
	            },
	            
	            "plugins" : [   "types" ]
	        });
		 
		 $('#org_tree').bind("activate_node.jstree", function (obj, e) {
			    // 处理代码
			    // 获取当前节点
			    var currentNode = e.node;
			    //console.table(currentNode);
			    //console.table(obj)
			   // console.dir(currentNode);
			    var departid = currentNode.id;
			    if(currentNode.parent == "#")
			    	departid = '0';
			    //console.dir(obj);
			    Sysmanager.showOrgUsers(departid);
			    //alert('处理代码');
			});
	}
	//初始化日期控件
	var initPickers = function () {
        //init date pickers
        $('.date-picker').datepicker({
            rtl: App.isRTL(),
            autoclose: true
        });
    }
	var ContentMethod = function(content)
	{
		 var content_ =  
		          '<div class="btn-group">'+
		            '<a class="btn btn-xs btn-default" ><i class=\"fa fa-pencil\"></i>查看</a>'+		              
		          '</div>';
		return content_;
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
		                    	
		                    	 var userId = $(this).attr("userId");
		                    	 SysUser.viewUser(userId)
		                     }
		                   },
		                   {
		                     class: 'btn  btn-xs btn-default',
		                     icon: 'fa fa-pencil',
		                     label:'修改',
		                     onClick: function() {

		                    	 var userId = $(this).attr("userId");
		                    	 SysUser.tomodifyUser(userId)
		                     }
		                   },
		                   {
		                     class: 'btn  btn-xs btn-default',
		                     icon: 'fa fa-pencil',
		                     label:'授权',
		                     onClick: function() {
		                    	 var userId = $(this).attr("userId");
		                    	 SysUser.toauthUser(userId)
		                     }
		                   },
		                   {
		                     class: 'btn  btn-xs btn-danger',
		                     icon: 'fa fa-trash-o',
		                     label:'删除' ,
		                     onClick: function() {
		                    	 var userId = $(this).attr("userId");
		                    	 SysUser.delUser(userId)
			                  }
		                   }
		                   ,
		                   {
		                     class: 'btn  btn-xs btn-danger',
		                     icon: 'fa fa-ban',
		                     label:'停用' ,
		                     onClick: function() {
		                    	 var userId = $(this).attr("userId");
		                    	 SysUser.stopUser(userId)
			                 }
		                   },
		                   {
		                     class: 'btn  btn-xs btn-default',
		                     icon: 'fa fa-pencil',
		                     label:'重置口令',
		                     onClick: function() {
		                    	 var userId = $(this).attr("userId");
		                    	 SysUser.tomodifyUser(userId)
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
		setDepartid(departId);
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
	                 [10, 20, 50, 100, 150, -1],
	                 [10, 20, 50, 100, 150, "All"] // change per page values here
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
	                        	 var chbx = "<label class=\"mt-checkbox mt-checkbox-single mt-checkbox-outline\"><input name=\"userId\" type=\"checkbox\" class=\"checkboxes\" value=\""+
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
	//初始化用户列表
	var getUserList = function (departId) {
		setDepartid(departId);
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
                    [10, 20, 50, 100, 150, -1],
                    [10, 20, 50, 100, 150, "All"] // change per page values here
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
                            { "data": "checkbox" },
                            { "data": "userName" },
                            { "data": "userRealname" },
                            { "data": "userWorknumber" },
                             { "data": "userMobiletel1" },
                            
                            { "data": "sexName" },
                            { "data": "userTypeName" },
                          
                            { "data": "userIsvalidName" },
                            { "data": "departId" },
                            { "data": "ops" }
                        ],
                "order": [
                    [1, "asc"]
                ]// set first column as a default sort by asc
            }
        });
     
    }
	
	 var showOrgUsers = function(departId){
		 setDepartid(departId);
		 showUsers();
		 showOrgs();
      }
	 
	 var showOrgs = function(departId){
		 if(departId)
			 setDepartid(departId);
		 else
			 departId = getDepartId();
	   	   table = $( "#datatable_orglist" ).DataTable()
	   	   $("#datatable_orglist tr.filter .form-control").each(function() {
	             $(this).val('');
	      });
	   	   table.ajax.url( "../sysmanager/org/getDeparts.page?departId="+departId ).load();
      }
	 
	 var showUsers = function(departId){
		 if(departId)
			 setDepartid(departId);
		 else
			 departId = getDepartId();
		 var table = $( "#datatable_userlist" ).DataTable()
	   	   $("#datatable_userlist tr.filter .form-control").each(function() {
	             $(this).val('');
	      });
	   	   table.ajax.url( "../sysmanager/user/getDepartUsers.page?departId="+departId ).load();
      }
	var validateDepart = function(){
		 var dptid = Sysmanager.getDepartId();
		  if(!dptid || dptid == '' || dptid == '0')
		  {
			  PlatformCommonUtils.warn("请选择一个机构再操作!");
			  return false;
		  }
		 
		   return true;
	}
	
	return {
		initorg_tree:function(){
			initorg_tree();
		},
		initPickers:function(){
			//initPickers();
		},
		getUserList:function(departId){
			getUserList(departId);
		},
		showOrgUsers:function(departId){
			showOrgUsers(departId);
		},
		getDepartId:function(){
			return getDepartId();
		},
		validateDepart:function(){
			return validateDepart();
		},
		getOrgList:function(departId){
			getOrgList(departId);
		},
		showOrgs: function(departId){
			showOrgs(departId)
	      },
		 
		 showUsers:function(departId){
			 showUsers(departId);
		 }
		
		
	}
}();