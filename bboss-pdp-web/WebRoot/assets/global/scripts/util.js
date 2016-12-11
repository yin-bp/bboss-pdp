var PlatformCommonUtils = function(){
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
		}
	}
}();