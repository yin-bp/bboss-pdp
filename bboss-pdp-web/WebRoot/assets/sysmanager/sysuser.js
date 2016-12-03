var SysUser = function(){
	//main function to initiate the module
	/**
    var initModal = function () {
    
        // general settings
        $.fn.modal.defaults.spinner = $.fn.modalmanager.defaults.spinner = 
          '<div class="loading-spinner" style="width: 200px; margin-left: -100px;">' +
            '<div class="progress progress-striped active">' +
              '<div class="progress-bar" style="width: 100%;"></div>' +
            '</div>' +
          '</div>';

        $.fn.modalmanager.defaults.resize = true;
       
        initAddUser();

        
    }
    
    var initModalAddUser = function(){
    	//ajax demo:
        var $modal = $('#ajax-modal-user');

        $('#ajax-user-add-modal').on('click', function(){
        	 
          // create the backdrop and wait for next modal to be triggered
        //  $('body').modalmanager('加载中....');
          var el = $(this);
          
          
              $modal.load(el.attr('data-url'), '', function(){
              $modal.modal();
            });
           
        });

        $modal.on('click', '.update', function(){
          //$modal.modal('加载中.....');
            $modal
              
              .find('.modal-body')
                .prepend('<div class="alert alert-info fade in">' +
                  'Updated!<button type="button" class="close" data-dismiss="alert">&times;</button>' +
                '</div>');
           
        });
    }*/
	var usercontextpath;
	var initModal = function(){
		var $modal = $('#ajax-user-add');
	   	 $modal.on('click', '.update', function(){
	            //$modal.modal('加载中.....');
	              $modal
	                
	                .find('.modal-body')
	                  .prepend('<div class="alert alert-info fade in">' +
	                    'Updated!<button type="button" class="close" data-dismiss="alert">&times;</button>' +
	                  '</div>');
	             
	          });
	   	 
	   	 $modal.draggable({
	            handle: ".modal-header"
	        });
   	 
	}
	var initAddusersButtonAction = function(){
   	 $("#sys_addUser").on('click',function(){
   		 saveAppSystem();
   	 })
    }
    
    var saveAppSystem = function (){
		  if($("#userPassword").val() != $("#userPasswordSecond").val()){				
				$(".passwordhelp").append("两次口令不一致");				
				return;
			}else{
				$(".passwordhelp").html("");	
			}

		   $("#form_sys_adduser").submit();
		   
		   
	   }
    var initAdduserValidateform = function()
	{
		var form2 = $("#form_sys_adduser");		
		form2.validate({
					focusInvalid : false, // do not focus the last invalid
											// input
					ignore : "", // validate all fields including form hidden
									// input
					messages : {
						
						userName : {
								minlength : jQuery.validator.format("应用口令不能小于{0}个字符"),
								required : "请输入账号"
							},

							userRealname : {
								minlength : jQuery.validator.format("应用编码不能小于{0}个字符"),
								required : "请输入中文名称"
							},

							userPassword : {
								minlength : jQuery.validator.format("应用名称不能小于{0}个字符"),
								required : "请输入6位以上口令"
							},

							userPasswordSecond : {
								minlength : jQuery.validator.format("二次输入口令不能小于{0}个字符"),
								required : "请二次输入口令"
							} ,
							userIdcard : {
								minlength : jQuery.validator.format("应用口令不能小于{0}个字符"),
								required : "请输入身份证"
							},

							userWorknumber : {
								minlength : jQuery.validator.format("应用编码不能小于{0}个字符"),
								required : "请输入工号"
							}
					},
					rules : {
						userName : {
							minlength : 2,
							required : true
						},

						userRealname : {
							minlength : 1,
							required : true
						},

						userPassword : {
							minlength : 1,
							required : true
						},

						userPasswordSecond : {
							minlength : 1,
							required : true
						},

						userIdcard : {
							minlength : 1,
							required : true
						},

						userWorknumber : {
							minlength : 1,
							required : true
						}  
					},

					

					submitHandler : function(form) {
						// success1.show();
						adduser()
						
					}
				});
	}
     var init = function(relativepath){
    	 usercontextpath = relativepath;
    	 initModal();
    	
    	 
     }
     
     var initAddUser = function(){
    	 initAddusersButtonAction();
    	 initAdduserValidateform();
     }
     
     
	
	var adduser = function()
	{
		$("#form_sys_adduser")
		.ajaxSubmit(
				{
					type : 'POST',
					url : usercontextpath+'/sysmanager/user/addSmUser.page',
					forceSync : false,
					dataType : 'json',
					beforeSubmit : function() {
						 App.startPageLoading({message: '保存中...'});

				           
					},
					error : function(xhr, ajaxOptions,
							thrownError) {
						 
					},

					success : function(responseText,
							statusText, xhr, $form) {
						 
						 window.setTimeout(function() {
				                App.stopPageLoading();
				            }, 2000);
						var msg = responseText;
						var title = '添加用户';
						var tiptype = "success";
						if (msg == 'success') {
							msg = "添加用户完毕"
						} else {
							 
							
						}
						swal({
							  title: title,
							  text: msg,
							  type:tiptype,
							  
							},
							function(){
							  swal("Deleted!", "Your imaginary file has been deleted.", "success");
							});
						/**
						 bootbox.alert(msg, function() {
			                    alert("Hello world callback");
			                }); */

					}

				});
	}
     
     
    
    return {
    	init:function(usercontextpath){
    		init(usercontextpath);
    	},
    	initAddUser:function(usercontextpath){
    		initAddUser(usercontextpath);
    	}
    	
    	
    }


}();

