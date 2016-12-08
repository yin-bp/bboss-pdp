var SysChooseUser = function(){
	
	var contextpath;
	var init = function(path ){
		contextpath = path;
	}
	//初始化组织机构树
	var initorg_tree = function(isuser){
		
		 $("#org_tree").jstree({
	            "core" : {
	                "themes" : {
	                    "responsive": false
	                }, 
	                // so that create works
	                "check_callback" : true,
	                'data' : {
	                    'url' : function (node) {
	                      return contextpath+'/sysmanager/org/getChildrens.page';
	                    },
	                    'data' : function (node) {
	                      return { 'parent' : node.id,'isuser':isuser };
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
		 
		 $('#org_tree').bind("activate_node.jstree", function (obj, e) {
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
			    	
			   
			    showUsers(departid);
			  
			    //alert('处理代码');
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
	   	   table.ajax.url( contextpath+"/sysmanager/user/getDepartUsers.page?departId="+departId ).load();
     }
	 
	//初始化用户列表
		var getUserList = function (departId) {
			Sysmanager.setDepartid(departId);
			
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
	                
	                 "pagingType": "bootstrap_extended",
	            	 "ordering": false,  "searching": false,
	            	 "language": { // language settings
	                     // metronic spesific
	                     "metronicGroupActions": "_TOTAL_ 条记录被选中:  ",
	                     "metronicAjaxRequestGeneralError": "请求提交失败. 请检查服务是否启动或者网络是否断连！",

	                     // data tables spesific
	                     "lengthMenu": "每页 _MENU_ 条记录",
	                     "info": "共 _TOTAL_ 条记录",
	                     "infoEmpty": "",
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
	                    [5,10, 20, 50, 100, 150, -1],
	                    [5,10, 20, 50, 100, 150, "All"] // change per page values here
	                ],
	                "pageLength": 20, // default record count per page
	                "ajax": {
	                    "url": contextpath+"/sysmanager/user/getDepartUsers.page", // ajax source
	                    "type": "POST",
	                    "data":function ( d ) {
	                        d.departId = departId;
	                        d.ischooseUsers = true;
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
	                            	
		                        	 var users = data+":"+full.userRealname+":"+full.userName+":"+full.userWorknumber+":"+full.userMobiletel1;
		                        	 var chbx = 
                                     "<input type=\"radio\"  name=\"userId\" class=\"md-radiobtn\"  value=\""+
                                     users+"\">";
			                             return chbx;
	                            	
			                      }
	                            },
	                            { "data": "userName" },
	                            { "data": "userRealname","render": function ( userRealname, type, full, meta ) {
		                        	 var userSex = full.userSex;
	                            	if(userSex == null || userSex==("-1"))
	                    				return userRealname;
	                    			else if( userSex==("F"))
	                    				return userRealname+"(女)";
	                    			else if(  userSex==("M"))
	                    				return userRealname+"(男)";
	                    			else 
	                    				return userRealname;
		                        	}
	                            },
	                            { "data": "userWorknumber" },
	                             { "data": "userMobiletel1" },
	                            
	                          
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
	                           
	                            { "data": "departName" }
	                            ,
	                            { "data": "departName","render": function ( userType, type, full, meta ) {
		                        	  
	                        	    return ""
		                          
		                        	}
	                            }
	                             
	                           
	                        ],
	                "order": [
	                    [1, "asc"]
	                ]// set first column as a default sort by asc
	            }
	        });
	     
	    }
		var getSelectUser = function(){			
			var selectUsers = $("input[name='userId']:checked").val();
			return selectUsers;
		}
		return {
			initorg_tree:function(isuser){
				initorg_tree(isuser);
			},
			getUserList:function (departId){
				getUserList(departId);
			},
			init:function(path){
				init(path);
			},
			getSelectUser:function(){
				return getSelectUser();
			}
			
			
		}
	
}();