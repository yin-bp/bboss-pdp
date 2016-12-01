var Sysmanager = new function(){
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
	}
	//初始化日期控件
	var initPickers = function () {
        //init date pickers
        $('.date-picker').datepicker({
            rtl: App.isRTL(),
            autoclose: true
        });
    }
	//初始化用户列表
	var getUserList = function (departId) {
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
                 "pagingType": "bootstrap_extended",
            	 "ordering": false,  "searching": false,
            	 "language": { // language settings
                     // metronic spesific
                     "metronicGroupActions": "_TOTAL_ 条记录被选中:  ",
                     "metronicAjaxRequestGeneralError": "Could not complete request. Please check your internet connection",

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

        // handle group actionsubmit button click
        grid.getTableWrapper().on('click', '.table-group-action-submit', function (e) {
            e.preventDefault();
            var action = $(".table-group-action-input", grid.getTableWrapper());
            if (action.val() != "" && grid.getSelectedRowsCount() > 0) {
                grid.setAjaxParam("customActionType", "group_action");
                grid.setAjaxParam("customActionName", action.val());
                grid.setAjaxParam("id", grid.getSelectedRows());
                grid.getDataTable().ajax.reload();
                grid.clearAjaxParams();
            } else if (action.val() == "") {
                App.alert({
                    type: 'danger',
                    icon: 'warning',
                    message: 'Please select an action',
                    container: grid.getTableWrapper(),
                    place: 'prepend'
                });
            } else if (grid.getSelectedRowsCount() === 0) {
                App.alert({
                    type: 'danger',
                    icon: 'warning',
                    message: 'No record selected',
                    container: grid.getTableWrapper(),
                    place: 'prepend'
                });
            }
        });
        
      
       

        //grid.setAjaxParam("customActionType", "group_action");
        //grid.getDataTable().ajax.reload();
        //grid.clearAjaxParams();
    }
	
	 var showOrgUsers = function(departId){
   	  var table = $( "#datatable_userlist" ).DataTable()
   	   table.ajax.url( "../sysmanager/user/getDepartUsers.page?departId="+departId ).load();
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
		}
		
		
	}
}();