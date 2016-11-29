var Sysmanager = new function(){
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
	            "state" : { "key" : "demo3" },
	            "plugins" : [ "dnd", "state", "types" ,"massload", "state"]
	        });
	}
	
	return {
		initorg_tree:function(){
			initorg_tree();
		}
		
	}
}();