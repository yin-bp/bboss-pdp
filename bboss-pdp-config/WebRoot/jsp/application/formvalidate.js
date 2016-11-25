var Application = function(){
	var getSystemSecret = function(){
		   $.ajax({
				type : "POST",
				url : "../application/getSystemSecret.page",
				async : false,
				success : function(responseText) {
					$("#appSecret").val(responseText);
					$("#appSecretAck").val(responseText);
				}
			});
	}
	var saveAppSystem = function (formid){
		  if($("#appSecret").val() != $("#appSecretAck").val()){
				
				$("#re_secret_font").append("两次口令不一致");
				
				return;
			}else{
				$("#re_secret_font").html("*");	
			}

		   $(formid).submit();
		   
		   
	   }
	
	var addapp = function(form)
	{
		$(form)
		.ajaxSubmit(
				{
					type : 'POST',
					url : '../application/addApplication.page',
					forceSync : false,
					dataType : 'json',
					beforeSubmit : function() {
						 
					},
					error : function(xhr, ajaxOptions,
							thrownError) {
						 
					},

					success : function(responseText,
							statusText, xhr, $form) {
						 
						
						var msg = responseText;
						var title = '添加应用';
						var tiptype = "success";
						if (msg == 'success') {
							msg = "添加应用完毕"
						} else {
							 
							
						}

						toastr.options = {
							"closeButton" : true,
							"debug" : false,
							"positionClass" : "toast-top-center",
							"onclick" : null,
							"showDuration" : "0",
							"hideDuration" : "0",
							"timeOut" : "20000",
							"extendedTimeOut" : "0",
							"showEasing" : "swing",
							"hideEasing" : "linear",
							"showMethod" : "fadeIn",
							"hideMethod" : "fadeOut"
						};

						toastr[tiptype](msg, title); // Wire
														// up
														// an
														// event
														// handler
														// to a
														// button
														// in
														// the
														// toast,
														// if
														// it
														// exists

					}

				});
	}
	
	
	var updateapp = function(form)
	{
		$(form)
		.ajaxSubmit(
				{
					type : 'POST',
					url : '../application/updateApplication.page',
					forceSync : false,
					dataType : 'json',
					beforeSubmit : function() {
						 
					},
					error : function(xhr, ajaxOptions,
							thrownError) {
						 
					},

					success : function(responseText,
							statusText, xhr, $form) {
						 
						
						var msg = responseText;
						var title = '更新应用';
						var tiptype = "success";
						if (msg == 'success') {
							msg = "更新应用完毕"
						} else {
							 
							
						}

						toastr.options = {
							"closeButton" : true,
							"debug" : false,
							"positionClass" : "toast-top-center",
							"onclick" : null,
							"showDuration" : "0",
							"hideDuration" : "0",
							"timeOut" : "20000",
							"extendedTimeOut" : "0",
							"showEasing" : "swing",
							"hideEasing" : "linear",
							"showMethod" : "fadeIn",
							"hideMethod" : "fadeOut"
						};

						toastr[tiptype](msg, title); // Wire
														// up
														// an
														// event
														// handler
														// to a
														// button
														// in
														// the
														// toast,
														// if
														// it
														// exists

					}

				});
	}
	

	var deleteapp = function(appId)
	{
		
		$.ajax({
		    url:'../application/deleteApplication.page',
		    type:'POST', //GET
		    async:true,    //或false,是否异步
		    data:{
		        "appId":appId
		    },
		     
		    dataType:'json',    //返回的数据格式：json/xml/html/script/jsonp/text
		    beforeSend:function(xhr){
		         
		    },
		    success:function(responseText,textStatus,jqXHR){
		    	var msg = responseText;
				var title = '删除应用';
				var tiptype = "success";
				if (msg == 'success') {
					msg = "删除应用完毕"
				} else {
					 
					
				}
				 
				toastr.options = {
					"closeButton" : true,
					"debug" : false,
					"positionClass" : "toast-top-center",
					
					"showDuration" : "1000",
					"hideDuration" : "1000",
					"timeOut" : "20000000",
					"extendedTimeOut" : "1000",
					"onclose":function () {
	                    searchAppSystem(true);
	                },
					"onclick":function () {
	                    searchAppSystem(true);
	                }
				};

				toastr[tiptype](msg, title);
				
		    },
		    error:function(xhr,textStatus){
		       
		    },
		    complete:function(){
		        
		    }
		});
		
	}
	
	var searchAppSystem = function (issearch){
		var appName = issearch ? $("#appName").val():null;
		var appCode = issearch ? $("#appCode").val():null;
		$("#appSystem_table").datagrid({
			url: "../application/queryListInfoApplications.page",
            type: "post",
            data: {"appName":appName,"appCode":appCode},
            table:{ //表格相关配置
            	id: "appSystem_table",
                head:[{
                    item: "appCode",
                    name: "应用编号"
                },{
                    item: "appName",
                    name: "应用名称"
                },{
                    item: "appSecret",
                    name: "口令明文"
                },{
                    item: "appSecretText",
                    name: "口令密文"
                },{
                    item: "ticketlivetimes",
                    name: "有效周期"
                }
                ],
                showBtns: true,
                btns:[{
                    name: "查看",
                    todo: function(row,index){
                    	var appId=row.appId;
            			window.location.href = "../application/getApplication.page?appId="+appId;
                    }
                },{
                    name: "编辑",
                    todo: function(row,index){
                    	var appId=row.appId;
            			window.location.href = "../application/toUpdateApplication.page?appId="+appId;
                    }

                },{
                    name: "删除",
                    todo: function(row,index){
                    	var appId=row.appId;
                    	 if(confirm("确定要清空数据吗？")){
                    		 Application.deleteapp(appId);
                    	 }
                    		
            			
                    }

                }
                ]
            },
            page:{ //分页工具
                enable: true,
                id: "pageBean",
                pageSize: 10
            }
		});
	}
	var initform = function(formid,actiontype)
	{
		var form2 = $(formid);		
		form2.validate({
					focusInvalid : false, // do not focus the last invalid
											// input
					ignore : "", // validate all fields including form hidden
									// input
					messages : {
						
						   appSecret : {
								minlength : jQuery.validator.format("应用口令不能小于{0}个字符"),
								required : "请输入应用口令"
							},

							appCode : {
								minlength : jQuery.validator.format("应用编码不能小于{0}个字符"),
								required : "请输入应用编码"
							},

							appName : {
								minlength : jQuery.validator.format("应用名称不能小于{0}个字符"),
								required : "请输入应用名称"
							},

							appSecretAck : {
								minlength : jQuery.validator.format("二次输入口令不能小于{0}个字符"),
								required : "请二次输入口令"
							} 
					},
					rules : {
						appSecret : {
							minlength : 1,
							required : true
						},

						appCode : {
							minlength : 1,
							required : true
						},

						appName : {
							minlength : 1,
							required : true
						},

						appSecretAck : {
							minlength : 1,
							required : true
						} 
					},

					

					submitHandler : function(form) {
						// success1.show();
						if(actiontype == 'add')
							addapp(form)
						else 
							updateapp(form)
						
					}
				});
	}
	return {
		initform:function(formid,actiontype)
		{
			initform(formid,actiontype);
		},
		getSystemSecret:function()
		{
			getSystemSecret();
		},
		saveAppSystem:function(formid)
		{
			saveAppSystem(formid);
		},
		searchAppSystem : function (issearch){
			searchAppSystem(issearch);
		},
		deleteapp: function(appId)
		{
			deleteapp(appId)
		}
		
	}
}();