var PlatformCommonUtils = function(){
	var warn = function(msg){
		swal({
			  title: msg,
			  text: "",
			  allowOutsideClick: false,
			  type:"warning",
			  confirmButtonClass: "btn-danger",
			  confirmButtonText: "确定",
			});
	}
	var success = function(msg){
		swal({
			  title: msg,
			  allowOutsideClick: false,
			  text: "",
			  type:"success",
			  confirmButtonClass: "btn-success",
			  confirmButtonText: "确定",
			});
	}
	var confirm = function(msg,confirmfun){
		swal({
			  title: msg,
			  text: "",
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
			},
			function(isConfirm){	
				confirmfun(isConfirm);
		         
			});
	}
	return {
		warn:function(msg){
			warn(msg);
		},
		confirm:function(msg,confirmfun)
		{
			confirm(msg,confirmfun);
		},
		success:function(msg){
			success(msg);
		}
	}
}();