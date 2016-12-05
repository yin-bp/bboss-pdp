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
	 var afterSaveOrg = function()
		{
			Sysmanager.showOrgs(Sysmanager.getDepartId());
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
	return {
		init:function(relativepath){
			init(relativepath);
		}
	}
}();