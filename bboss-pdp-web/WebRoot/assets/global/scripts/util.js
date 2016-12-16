var PlatformCommonUtils = function(){
	var validateform = function(options){
		var form2 = $(options.form,ModelDialog.getCurrentModal());		
		form2.validate({
					focusInvalid : false, // do not focus the last invalid
											// input
					ignore : "", // validate all fields including form hidden
									// input
					errorElement: 'span', //default input error message container
		            errorClass: 'help-block help-block-error', // default input error message class
					messages : options.messages,
					rules : options.rules,

					errorPlacement: function(error, element) {
		                if (element.is(':checkbox')) {
		                    error.insertAfter(element.closest(".md-checkbox-list, .md-checkbox-inline, .checkbox-list, .checkbox-inline"));
		                } else if (element.is(':radio')) {
		                    error.insertAfter(element.closest(".md-radio-list, .md-radio-inline, .radio-list,.radio-inline"));
		                } else {
		                    error.insertAfter(element); // for other inputs, just perform default behavior
		                }
		            },

		            highlight: function(element) { // hightlight error inputs
		                $(element)
		                    .closest('.form-group').addClass('has-error'); // set error class to the control group
		            },

		            unhighlight: function(element) { // revert the change done by hightlight
		                $(element)
		                    .closest('.form-group').removeClass('has-error'); // set error class to the control group
		            },

		            success: function(label) {
		                label
		                    .closest('.form-group').removeClass('has-error'); // set success class to the control group
		            },
					

					submitHandler : function(form) {
						options.submitHandler();
						
						
					}
				});
	}
	//初始化日期控件
	var initPickers = function() {
        //init date pickers
        $('.date-picker',ModelDialog.getCurrentModal()).datepicker({
            rtl: App.isRTL(),
            autoclose: true,
            language:'zh-CN',
            format:'yyyy-mm-dd'
        });
    }
	var warn = function(msg,warnfun){
		swal({
			  title: msg,
			  text: "",
			  allowOutsideClick: false,
			  type:"warning",
			  confirmButtonClass: "btn-danger",
			  confirmButtonText: "确定",
			},warnfun);
	}
	var success = function(msg,successfun){		
		if(!successfun)
			swal({
				  title: msg,
				  allowOutsideClick: false,
				  text: "",
				  type:"success",
				  confirmButtonClass: "btn-success",
				  confirmButtonText: "确定",
				});
		else
		{
			swal({
				  title: msg,
				  allowOutsideClick: false,
				  text: "",
				  type:"success",
				  confirmButtonClass: "btn-success",
				  confirmButtonText: "确定",
				},
				successfun
				);
		}
	}
	var confirm = function(msg,confirmfun,extendtext,html){
		if(!html) html = false;
		if(!extendtext) extendtext = "";
		swal({
			  title: msg,
			  text: extendtext,
			  type: "info",
			  allowOutsideClick: false,
			  showConfirmButton: true,
			  showCancelButton: true,
			  confirmButtonClass: "btn-info",
			  cancelButtonClass: "btn-default",
			  closeOnConfirm: false,
			  closeOnCancel: true,
			  confirmButtonText: "确定",
			  cancelButtonText: "取消",
			  "html":html
			},
			confirmfun
			);
	}
	
	var popconfirmation = function(options){
		//'button[data-toggle=role_ops_confirmation]'
		$(options.selector).confirmation({
    		  rootSelector: options.selector,
    		  singleton:true,
    		 
    		  template:'<div class="popover confirmation">' +
    	      '<div class="arrow"></div>' +
    	      
    	      '<div class="popover-content">' +
    	        '<p class="confirmation-content"></p>' +
    	        '<div class="confirmation-buttons">' +
    	          '<div class="btn-group">' +
    	            '<a href="#" class="btn" data-apply="confirmation"></a>' +
    	            '<a href="#" class="btn" data-dismiss="confirmation"></a>' +
    	          '</div>' +
    	        '</div>' +
    	      '</div>' +
    	    '</div>',
			      buttons:options.buttons
    		});
	}
	
	var initSlimScroll = function(el,container,overflow_x) {
        if (!$().slimScroll) {
            return;
        }
        var _el = null;
        if(container){
        	_el = $(el,container);
        }
        else
    	{
        	_el = $(el);
    	}
        if(overflow_x == undefined)
        	overflow_x = false;
        _el.each(function() {
            if ($(this).attr("data-initialized")) {
                return; // exit
            }

            var height;

            if ($(this).attr("data-height")) {
                height = $(this).attr("data-height");
            } else {
                height = $(this).css('height');
            }
            var isRTL = false;
            $(this).slimScroll({
                allowPageScroll: true, // allow page scroll when the element scroll is ended
                size: '7px',
                color: ($(this).attr("data-handle-color") ? $(this).attr("data-handle-color") : '#bbb'),
                wrapperClass: ($(this).attr("data-wrapper-class") ? $(this).attr("data-wrapper-class") : 'slimScrollDiv'),
                railColor: ($(this).attr("data-rail-color") ? $(this).attr("data-rail-color") : '#eaeaea'),
                position: isRTL ? 'left' : 'right',
                height: height,
                alwaysVisible: ($(this).attr("data-always-visible") == "1" ? true : false),
                railVisible: ($(this).attr("data-rail-visible") == "1" ? true : false),
                disableFadeOut: true,
                distance :'1px',
                "overflow_x":overflow_x
            });

            $(this).attr("data-initialized", "1");
        });
        
    }
	return {
		warn:function(msg,warnfun){
			warn(msg,warnfun);
		},
		confirm:function(msg,confirmfun,extendtext,html)
		{
			confirm(msg,confirmfun,extendtext,html);
		},
		success:function(msg,successfun){
			success(msg,successfun);
		}
		,
		initPickers:function(){
			initPickers();
		},
		initSlimScroll:function(el,container,overflow_x){
			initSlimScroll(el,container,overflow_x);
		},
		popconfirmation:function(options){
			popconfirmation(options);
		},
		validateform:function(options){
			validateform (options);
		}
	}
}();
var ModelDialog_Modal = function(options){
	var $setting = $.extend(true,{
						 width :"900px",
						 height:"400px",
						 
						 iframe:false,
						 showfooter:true,
						disableokbutton:false,
						disablecancel:false,
						okbuttonText:"确定",
						cancelbuttonText:"取消"
					},options);
	var $iframe;
	var $modal;
	var isIframe = function(){
		return $setting.iframe;
	}
	
	return {
		getSetting:function(){
			return $setting;
		},
		
		getModal:function(){
			return $modal;
		},
		setModal:function(modal){
			$modal = modal;
			
		},
		getIframe:function(){
			if($iframe == undefined)
				$iframe = $(".modal-body",$modal).children("iframe")[0];
			 return $iframe;
		},
		
		isIframe :function(){
			 return isIframe();
		 },
		 html:function(html){
			 $modal.html(html)
		 }
		 ,
		 load:function(url,callback){
			 $modal.load(url,callback);
		 },
		 hide:function(){
			 $modal.modal('hide');
		 }
		
	}
}
var ModelDialog = function(){
	
	if(!window.top.$modals)
	{
		window.top.$modals = new Array();
	}
	var $modals = window.top.$modals;
	 
	
	 var getIframe = function(){
		 return getCurrentModalDialog().getIframe();
	 }
	 var removeCurrentModal = function(){
		 $modals.pop();
	 }
	 var getCurrentModalDialog = function (){
		 return $modals[$modals.length -1];
	 }
	 var modalSize = function(){
		 return $modals.length;
	 }
	 
	 var initModal = function(options){
		
		
		var fatherBody = $(window.top.document.body); 
		var  $modal = $("<div  class=\"modal container  fade draggable-modal  modal-scroll \" tabindex=\"-1\"> </div>");
		$modal.appendTo(fatherBody);
		
			 //$modal = $('#ajax-dialogmodal-extend');
		$modal.draggable({
		            handle: ".modal-header"
		        });
			 
		  
		$modal.bind("onresize",function(){  
      		changeModelFrameHeight();  
      	});
		 var _modal = new ModelDialog_Modal(options)
		 _modal.setModal($modal)
		
		 
		 $modals.push( _modal);
		 return _modal;
	 }
	var changeModelFrameHeight =function(iframeobject){
		
    	if(iframeobject)
    	{
    		$iframe = $(iframeobject);
    	}
    	else
    	{
    		if($iframe == null)
    		{
    			$iframe = $currentmodal.getIframe();
    		}
    	}
    	var setting = getCurrentModalDialog().getSetting();
    	var h = setting.height;
    	var w = setting.width;
    	$iframe.height(h);
    	// $("#"+id).height(height);
    	$iframe.width(w);
    } 
	// Handles Bootstrap Modals.
    var handleModals = function() {        
        // fix stackable modal issue: when 2 or more modals opened, closing one of modal will remove .modal-open class. 
        $('body').on('hide.bs.modal', function() {
            if ($('.modal:visible').size() > 1 && $('html').hasClass('modal-open') === false) {
                $('html').addClass('modal-open');
            } else if ($('.modal:visible').size() <= 1) {
                $('html').removeClass('modal-open');
            }
        });

        // fix page scrollbars issue
        $('body').on('show.bs.modal', '.modal', function() {
            if ($(this).hasClass("modal-scroll")) {
                $('body').addClass("modal-open-noscroll");
            }
        });

        // fix page scrollbars issue
        $('body').on('hidden.bs.modal', '.modal', function() {
            $('body').removeClass("modal-open-noscroll");
        });

        // remove ajax content and remove cache on modal closed 
        $('body').on('hidden.bs.modal', '.modal:not(.modal-cached)', function () {
            $(this).removeData('bs.modal');
        });
    };
	var dialog = function(options){
		var $dialog = initModal(options);
		var $modal = $dialog.getModal();
		var setting = $dialog.getSetting();
		var modalcontent = "<div class=\"modal-header\">"+
		"<button type=\"button\" class=\"close\" data-dismiss=\"modal\""+
		"	aria-hidden=\"true\"></button>"+
		"<h4 class=\"modal-title\">"+setting.title+"</h4>"+
		"</div>"+
		"<div class=\"modal-body\">";
		if($dialog.isIframe()){
			modalcontent =modalcontent + "<iframe allowfullscreen "+
			"frameborder=\"0\"  "+
			"src=\""+setting.url+"\" "+
			"scrolling=\"auto\" "+
			
			"onLoad=\"javascript:ModelDialog.changeModelFrameHeight(this);\">"+
			"</iframe>";
		}
			
		
		modalcontent =modalcontent + "</div>";
		/**
		 * disableokbutton:false,
						disablecancel:false,
						okbuttonText:"确定",
						cancelbuttonText:"取消"
		 */
		if(setting.showfooter){
			modalcontent +="<div class=\"modal-footer\">";
			if(!setting.disableokbutton)	
				modalcontent +="  <button type=\"button\" class=\"btn blue ok\"  >"+setting.okbuttonText+"</button>";
			
			if(!setting.disablecancelbutton)	
				modalcontent +="    <button type=\"button\" class=\"btn default\" data-dismiss=\"modal\">"+setting.cancelbuttonText+"</button>";
			modalcontent +="</div>" ;
		}
		
		$modal.html(modalcontent);
		if(!$dialog.isIframe()){
			$(".modal-body",$modal).load(setting.url,setting.params,function(){
				if(setting.urlLoadCallBack){
					setting.urlLoadCallBack($modal);
				}
			});
		}
		else
		{
			
		}
		
		$('.ok', $modal).bind("click",function (e) {
			
			if(setting.okCallBack){
				var r = setting.okCallBack($modal);
				if(r)//处理成功，关闭弹窗，否则保持弹窗
					$modal.modal('hide');
			}
			else{
				$modal.modal('hide');
			}
				
				
				
		  })  
		$modal.on('hidden.bs.modal', function (e) {
			if(setting.closeCallBack)
				setting.closeCallBack($modal);
			removeCurrentModal();
			$modal.remove();
			if(modalSize() <= 0 )
				$('body').removeClass("modal-open-noscroll");
			else
			{
				if ($modal.hasClass("modal-scroll")) {
	                $('body').addClass("modal-open-noscroll");
	                $('html').removeClass("modal-open");
	            }
			}
			//$modal = null;
			//$iframe = null;
			//setting = null;
		  });
	  	  
		$modal.modal({
        	 backdrop:"static",
        	 "width" :setting.width,
        	 "height":setting.height
         });
          // https://github.com/jschr/bootstrap-modal
       
         return $modal; 
       
		
	}
	var closeCurrentDialog = function(){
		var modal = getCurrentModalDialog();
		if(modal){
			modal.hide();
		}
			
	}
	
	return {
		dialog : function(options){
			return dialog(options);
		},
		modalSize:function(){
			return modalSize();
		},
		
		changeModelFrameHeight:function(iframeid,modelwindow){
			changeModelFrameHeight(iframeid,modelwindow);
		},
		getCurrentModalDialog:function(){
			return getCurrentModalDialog();
		},
		getCurrentModal:function(){
			return getCurrentModalDialog().getModal();
		},
		getIframe:function(){
			return getIframe();
		},
		closeCurrentDialog:function(){
			closeCurrentDialog();
		},
		warn:function(msg,warnfun){
			PlatformCommonUtils.warn(msg,warnfun);
		},
		confirm:function(msg,confirmfun,extendtext,html)
		{
			PlatformCommonUtils.confirm(msg,confirmfun,extendtext,html);
		},
		success:function(msg,successfun){
			PlatformCommonUtils.success(msg,successfun);
		},
		initPickers:function(){
			PlatformCommonUtils.initPickers();
		}
	}
}();