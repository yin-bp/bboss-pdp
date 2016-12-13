var Sysmanager = new function(){
	var departid;
	var setDepartid = function (orgid){
		departid = orgid;
	}
	var getDepartId = function(){
		return departid;
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
	                      return '../sysmanager/org/getChildrens.page';
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
			    	
			    //console.dir(obj);
			    if(isuser)
			    	SysUser.showUsers(departid);
			    else
			    	SysOrg.showOrgs(departid);
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
		initorg_tree:function(isuser){
			initorg_tree(isuser);
		},
		initPickers:function(){
			initPickers();
		},
		
		 
		setDepartid:function(departId){
			setDepartid(departId);
		},
		getDepartId:function(){
			return getDepartId();
		},
		validateDepart:function(){
			return validateDepart();
		}
		
		
	}
}();