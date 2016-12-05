var SysOrg = function(){
	var usercontextpath;
	var validateDepart = function(){
		return Sysmanager.validateDepart();
	}
	var init = function(relativepath){
   	 usercontextpath = relativepath;
   	
   	 //initAddUserModalExtend();
   	 //initDelUsers();
    }
	return {
		init:function(relativepath){
			init(relativepath);
		}
	}
}();