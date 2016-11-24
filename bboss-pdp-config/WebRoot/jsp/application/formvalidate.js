var Application = function(){
	var initform = function()
	{
		var form2 = $('#addAppSystem');		
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
												var title = '生成代码';
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
				});
	}
	return {
		initform:function()
		{
			initform();
		}
	}
}();