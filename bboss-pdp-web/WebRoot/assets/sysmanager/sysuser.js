var SysUser = function(){
	//main function to initiate the module
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
    }
   
     var init = function(){
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
     
     var initAdduserAction = function(){
    	 
     }
     
     var saveAppSystem = function (formid){
		  if($("#userPassword").val() != $("#userPasswordSecond").val()){
				
				$(".passwordhelp").append("两次口令不一致");
				
				return;
			}else{
				$(".passwordhelp").html("");	
			}

		   $(formid).submit();
		   
		   
	   }
	
	var adduser = function(form)
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
     
     
    
    return {
    	init:function(){
    		init();
    	}
    	
    }


}();

