var SysResource = function(){
	var contextpath ;
	var init = function(path){
		contextpath = path;
		$('[data-toggle="tab"]').bind('shown.bs.tab',function(){
			
			loadResourceMaintain($(this).attr('resourceType'));
		});
	}
	var loadResourceMaintain = function(resourceType){
		$("#resource_tab_1_1").load(contextpath+"/sysmanager/resource/loadResourceMaintain.page",
				 					{"resourceType":resourceType},
				 					function(){
				 					});	
	}
	return {	
		init:function(contextpath){
			init(contextpath);
		},
		loadResourceMaintain:function(resourceType){
			loadResourceMaintain(resourceType);
		}
	}
}();