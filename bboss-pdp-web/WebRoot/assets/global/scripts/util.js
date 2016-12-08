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

var ModelDialog = function(){
	var $modal;
	var $iframe;
	var setting ;
	 var getIframe = function(){
		 return $iframe;
	 }
	 var initModal = function(){
		 if($modal == null){
			 var fatherBody = $(window.top.document.body); 
			 $modal = $("<div  class=\"modal container  fade draggable-modal  modal-scroll \" tabindex=\"-1\"> </div>");
			 $modal.appendTo(fatherBody);
			 //$modal = $('#ajax-dialogmodal-extend');
			 $modal.draggable({
		            handle: ".modal-header"
		        });
			 
		 }
		 $modal.bind("onresize",function(){  
      		changeModelFrameHeight();  
      	});
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
    			$iframe = $modal.children("iframe")[0];
    		}
    	}
    	var h = setting.height;
    	var w = setting.width;
    	$iframe.height(h);
    	// $("#"+id).height(height);
    	$iframe.width(w);
    } 
	var dialog = function(options){
		initModal();
		setting = $.extend(true,{
						 width :"900px",
						 height:"600px"
					},options);
		var iframecontent = "<div class=\"modal-header\">"+
		"<button type=\"button\" class=\"close\" data-dismiss=\"modal\""+
		"	aria-hidden=\"true\"></button>"+
		"<h4 class=\"modal-title\">"+setting.title+"</h4>"+
		"</div>"+
		"<div class=\"modal-body\">"+
		"<iframe allowfullscreen "+
		"frameborder=\"0\"  "+
		"src=\""+setting.url+"\" "+
		"scrolling=\"auto\" "+
		
		"onLoad=\"javascript:ModelDialog.changeModelFrameHeight(this);\">"+
		"</iframe>"+
		"</div>"+
		
		"<div class=\"modal-footer\">"+
		"  <button type=\"button\" class=\"btn blue ok\"  >确定</button>"+
		"    <button type=\"button\" class=\"btn default\" data-dismiss=\"modal\">取消</button>"+
		"</div>" ;
		$modal.html(iframecontent);
		$('.ok', $modal).bind("click",function (e) {
			 var r = false;
			if(setting.closeCallBask)
				r = setting.closeCallBask();
			//$modal.remove();
			//$modal = null;
			if(r)
				$modal.modal('hide');
		  })  
		$modal.on('hidden.bs.modal', function (e) {
			
			
			$modal.remove();
			$modal = null;
			$iframe = null;
			setting = null;
		  });
	  	  
		$modal.modal({
        	 backdrop:"static",
        	 "width" :setting.width,
        	 "height":setting.height
         });
          // https://github.com/jschr/bootstrap-modal
       
          
       
		
	}
	var getModal=function()
	{
		return $modal;
	}
	return {
		dialog : function(options){
			dialog(options);
		},
		changeModelFrameHeight:function(iframeid,modelwindow){
			changeModelFrameHeight(iframeid,modelwindow);
		},
		getModal:function(){
			return getModal();
		},
		getIframe:function(){
			return getIframe();
		}
	}
}();