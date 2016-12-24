var SysResource = function(){
	var usercontextpath ;
	var resourceType;
	var resourceName;
	var init = function(path){
		usercontextpath = path;
		$('[data-toggle="tab"]','.resourcetypelist').bind('shown.bs.tab',function(){			
			loadResourceMaintain($(this).attr('resourceType'));
		});
	}
	var loadResourceMaintain = function(_resourceType,_resourceName){
		$("#resource_tab_1_1").load(usercontextpath+"/sysmanager/resource/loadResourceMaintain.page",
				 					{"resourceType":_resourceType},
				 					function(){
				 						 Layout.fixContentHeight(); // fix content height
				 			             App.initAjax(); // initialize core stuff
				 					});	
		resourceType = _resourceType;
		resourceName = _resourceName;
	}
	var getResourceType = function(){
		return resourceType;
	}
	var initAddResourceSaveAction = function(){
		PDP.validateform({
			form:"form",
			messages : {
					resCode : {						
						required : "请输入资源编码"
					},
	
					resName : {
						required : "请输入资源名称"
					}
			},
			rules : {			 
				resCode : {
					
					required : true
				},	
				resName : {
					
					required : true
				}
			},
			submitHandler:addResource
		});
		
		$(".btn-resourceaddsave",ModelDialog.getCurrentModal()).bind("click",function(){
			$("form",ModelDialog.getCurrentModal()).submit();
		});
		 
	}
	var afterSaveResource = function(){
		loadResourceMaintain(resourceType,resourceName);
	}
	var addResource = function(){
		$('form',ModelDialog.getCurrentModal())
		.ajaxSubmit(
				{
					type : 'POST',
					url : usercontextpath+'/sysmanager/resource/addResource.page',
					forceSync : false,
					dataType : 'json',
					beforeSubmit : function() {
						 App.startPageLoading({message: '保存中...'});				           
					},
					error : function(xhr, ajaxOptions,
							thrownError) {
						PlatformCommonUtils.warn(thrownError) ;
					},

					success : function(responseText,
							statusText, xhr, $form) {
						 
						 window.setTimeout(function() {
				                App.stopPageLoading();
				            }, 2000);
						var msg = responseText;
						var title = '增加资源';
						var tiptype = "success";
						if (msg == 'success') {
							msg = "增加资源完毕"
							PlatformCommonUtils.success(msg,function(){
								ModelDialog.getCurrentModal().modal('hide');
								afterSaveResource();
							}) ;
						} else {							 
							PlatformCommonUtils.warn(msg) ;
						}
						
						

					}

				});
	}
	var initResourceMaintainAction = function(){
		$(".btn-addresource").bind("click",function(){
			 ModelDialog.dialog({
					title:"新增资源-资源类型["+getResourceType()+"]",
					showfooter:false,
					url:usercontextpath+"/sysmanager/resource/toAddResource.page",
					params:{restypeId:getResourceType()},
					width:"600px",
					height:"300px"

	         });
		});
		
		$(".btn-batchdelresource").bind("click",function(){
	   		  
            var chk_value =""; 
            $('.table-resourcelist input[name="resId"]:checked').each(function(){ 
            	if(chk_value == "")
            		chk_value = $(this).val(); 
            	else
            		chk_value += ","+$(this).val(); 
            }); 
            if(chk_value == "")
            {
            	PDP.warn("请选择要删除的资源!");
	           	 return;
            }
             PDP.confirm("确定要删除选中的资源吗?",function(isConfirm){
	           	 	if(isConfirm)
	           	 	{        	 		
	           	 		
		           	 	$.ajax({
		          		   type: "POST",
		          			url : usercontextpath+"/sysmanager/resource/deleteBatchResource.page",
		          			data :{"resIds":chk_value},
		          			dataType : 'json',
		          			async:false,
		          			beforeSend: function(XMLHttpRequest){ 					
		          				 	
		          				},
		          			success : function(responseText){
		          				
		          				if(responseText=="success"){
		          					
		          					PDP.success("资源删除成功!");
		          					afterSaveResource();
		          				}else{
		          					PDP.showError('.alert-resourcemanager',"资源删除失败:"+responseText);
		          					 
		          				}
		          			}
		          		  });
	           	 	}
			        	
				});
   		
		});
	}
	return {	
		init:function(usercontextpath){
			init(usercontextpath);
		},
		loadResourceMaintain:function(resourceType){
			loadResourceMaintain(resourceType);
		},
		getResourceType:function(){
			return getResourceType();
		},
		initResourceMaintainAction:function(){
			initResourceMaintainAction();
		},
		initAddResourceSaveAction:function(){
			initAddResourceSaveAction();
		}
	}
}();