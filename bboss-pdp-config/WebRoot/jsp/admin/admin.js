var Admin = function(){
	var login = function(){
		
		
		$("#loginForm").submit();
	}
	
	var initloginForm = function()
	{
		var form2 = $("#loginForm");		
		form2.validate({
					focusInvalid : false, // do not focus the last invalid
											// input
					ignore : "", // validate all fields including form hidden
									// input
					messages : {
						
						userName : {
								minlength : jQuery.validator.format("用户账号不能小于{0}个字符"),
								required : "请输入用户账号!"
							},

							password : {
								minlength : jQuery.validator.format("用户口令不能小于{0}个字符"),
								required : "请输入用户口令!"
							}
					},
					rules : {
						userName : {
							minlength : 1,
							required : true
						},

						password : {
							minlength : 1,
							required : true
						}
					}
				});
	}
	
	return {
		
		initloginForm:function()
		{
			initloginForm();
		},
		login:function(){
			login();
		}
	}
	
}();