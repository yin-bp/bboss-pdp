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