
var SysDict = function(){
	var usercontextpath ;
	var dictButtonMethods = function(){
		return {};
	}
	var queryDicts = function(doquery){
		
		$(".portlet-dictlist").load(usercontextpath+"/dictmanager/queryListInfoDicts.page",
									doquery?$('.form-querydict').serialize():{},
									function(){
										//PlatformCommonUtils.popconfirmation({selector:'[data-toggle=dict_ops_confirmation]',buttons:dictButtonMethods()});
										
									});	
	}
	var init = function(contextpath){
		usercontextpath = contextpath;
		queryDicts(false);
		
		$(".form-querydict .btn-query").bind('click',function(){
			queryDicts(true);
		});
		$(".btn-adddict").bind('click',function(){
			 ModelDialog.dialog({
					title:"新增字典",
					showfooter:false,
					url:usercontextpath+"/dictmanager/toAddDict.page",
					
					width:"900px",
					height:"500px"

	         });
		});
		
		$(".btn-batchdeldict").bind("click",function(){
   		  
            var chk_value =""; 
            $('.portlet-dictlist input[name="dictId"]:checked').each(function(){ 
            	if(chk_value == "")
            		chk_value = $(this).val(); 
            	else
            		chk_value += ","+$(this).val(); 
            }); 
            if(chk_value == "")
            {
	           	 PlatformCommonUtils.warn("请选择要删除的字典!");
	           	 return;
            }
             PlatformCommonUtils.confirm("确定要删除选中的字典吗?",function(isConfirm){
	           	 	if(isConfirm)
	           	 	{        	 		
	           	 		
		           	 	$.ajax({
		          		   type: "POST",
		          			url : usercontextpath+"/dictmanager/deleteBatchDict.page",
		          			data :{"dictIds":chk_value},
		          			dataType : 'json',
		          			async:false,
		          			beforeSend: function(XMLHttpRequest){ 					
		          				 	
		          				},
		          			success : function(responseText){
		          				
		          				if(responseText=="success"){
		          					
		          					PlatformCommonUtils.success("字典删除成功!");
		          					afterSaveDict();
		          				}else{
		          					PlatformCommonUtils.warn("字典删除失败:"+responseText);
		          				}
		          			}
		          		  });
	           	 	}
			        	
				});
   		
		});
	}
	var initAddDict = function(){
		PlatformCommonUtils.validateform({
			form:"form",
			messages : {
					dictName : {
						minlength : jQuery.validator.format("字典名称不能小于{0}个字符"),
						required : "请输入字典名称"
					},	
					dictCode : {
						required : "请输入字典中文名"
					}
			},
			rules : {			 
				dictName : {
					minlength : 2,
					required : true
				},	
				dictCode : {
					minlength : 2,
					required : true
				}
			},
			submitHandler:addDict
		});
		
		$(".btn-dictaddsave",ModelDialog.getCurrentModal()).bind("click",function(){
			$("form",ModelDialog.getCurrentModal()).submit();
		});
		
		$(".portlet-dictitemlist",ModelDialog.getCurrentModal()).load(usercontextpath+"/dictmanager/dictitemlist.page",{},function(){
			
		});
		$(".btn-adddictitem",ModelDialog.getCurrentModal()).bind("click",function(){
			addRow();
		});
		$(".btn-batchdeldictitem",ModelDialog.getCurrentModal()).bind("click",function(){
			deleteRow();
		});
		
	}
	
	addRow = function() {
		 var tr = $(".table-dictitemlist-template tr",ModelDialog.getCurrentModal()).eq(0).clone();
		
		 tr.show();
         tr.appendTo(".table-dictitemlist",ModelDialog.getCurrentModal());
	}
	
	//删除被指定的行
	deleteRow = function () {
		var codes="";		
	   	$(".table-dictitemlist tr:gt(0)",ModelDialog.getCurrentModal()).each(function() {
			 
	   	if ($(this).find(".checkone").get(0).checked == true) {
	   			$(this).remove();
	   		} 
	  		 });
	    
	}
	//新增修改角色操作，刷新角色列表
	var afterSaveDict = function(){
		queryDicts(false);
	}
	var addDict = function(){
		$('form',ModelDialog.getCurrentModal())
		.ajaxSubmit(
				{
					type : 'POST',
					url : usercontextpath+'/dictmanager/addDict.page',
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
						var title = '增加字典';
						var tiptype = "success";
						if (msg == 'success') {
							msg = "增加字典完毕"
							PlatformCommonUtils.success(msg,function(){
								ModelDialog.getCurrentModal().modal('hide');
								afterSaveDict();
							}) ;
						} else {							 
							PlatformCommonUtils.warn(msg) ;
						}
						
						

					}

				});
	}
	var modifyDict = function(){
		$('form',ModelDialog.getCurrentModal())
		.ajaxSubmit(
				{
					type : 'POST',
					url : usercontextpath+'/dictmanager/updateDict.page',
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
						var title = '修改字典';
						var tiptype = "success";
						if (msg == 'success') {
							msg = "修改字典完毕"
							PlatformCommonUtils.success(msg,function(){
								ModelDialog.getCurrentModal().modal('hide');
								afterSaveDict();
							}) ;
						} else {							 
							PlatformCommonUtils.warn(msg) ;
						}
						
						

					}

				});
	}
	var initModifyDict = function(actiontype){
		if(actiontype  == null){
			PlatformCommonUtils.validateform({
				form:"form",
				messages : {
						dictName : {
							minlength : jQuery.validator.format("字典名称不能小于{0}个字符"),
							required : "请输入字典名称"
						},	
						dictCode : {
							required : "请输入字典中文名"
						}
				},
				rules : {			 
					dictName : {
						minlength : 2,
						required : true
					},	
					dictCode : {
						minlength : 2,
						required : true
					}
				},
				submitHandler:modifyDict
			});
		}
		
		if(actiontype  == null){
			$(".btn-dicteditsave",ModelDialog.getCurrentModal()).bind("click",function(){
				$("form",ModelDialog.getCurrentModal()).submit();
			});
		}
		else
		{
			$(".btn-dicteditsave",ModelDialog.getCurrentModal()).bind("click",function(){
				modifyDict();
			});
		}
		
		 
		$(".btn-adddictitem",ModelDialog.getCurrentModal()).bind("click",function(){
			addRow();
		});
		$(".btn-batchdeldictitem",ModelDialog.getCurrentModal()).bind("click",function(){
			deleteRow();
		});
		 
	}
	
	var initDictItemOrderTable = function(){
		$('.table-dictitemlist',ModelDialog.getCurrentModal()).DataTable( {
        	paging: false,
        	 "dom": "<'row'<'col-md-8 col-sm-12'><'col-md-4 col-sm-12'<'table-group-actions pull-right'>>r><'table-responsive't><'row'<'col-md-8 col-sm-12'><'col-md-4 col-sm-12'>>",
    		//rowReorder: { selector: 'tr'},
        	 rowReorder: true,
    		/**columnDefs: [
    		             { targets: 0, visible: false }
    		         ],*/
	         "ordering": true,  "searching": false,
	          
	               "columns": [{
	                   "orderable": false,
	                   "visible": true 
	               }, {
	                   "orderable": false
	               }, {
	                   "orderable": false
	               }],
	               "order": [
                             [1, "asc"]
                         ]
        	          
    	} );
	}
	return {
		init:function(contextpath){
			init(contextpath);
		},
		initAddDict:function(){
			initAddDict();
		},
		initModifyDict:function(){
			initModifyDict();
		},
		viewDict:function(dictId,dictName,dictCode){
			ModelDialog.dialog({
				title:"查看字典",
				showfooter:false,
				url:usercontextpath+"/dictmanager/getDict.page",
				params:{
					"dictId":dictId
				},
				width:"900px",
				height:"500px"

			});
		},
		mainDictData:function(dictId,dictName,dictCode){
			ModelDialog.dialog({
				title:"维护字典数据-"+dictName+"("+dictCode+")",
				showfooter:false,
				url:usercontextpath+"/dictmanager/toUpdateDict.page",
				params:{
					"dictId":dictId,
					"actiontype":"maintaindata"
				},
				width:"900px",
				height:"500px"

			});
		},
		editDict:function(dictId,dictName,dictCode){
			ModelDialog.dialog({
				title:"修改字典",
				showfooter:false,
				url:usercontextpath+"/dictmanager/toUpdateDict.page",
				params:{
					"dictId":dictId
				},
				width:"900px",
				height:"500px"

			});
		},
		initDictItemOrderTable:function(){
			initDictItemOrderTable();
		}
	}	
}();