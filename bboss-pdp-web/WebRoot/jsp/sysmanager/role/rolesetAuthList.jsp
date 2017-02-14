<%@ page session="false" language="java"
	contentType="text/html; charset=utf-8"%>
<%@ taglib uri="/WEB-INF/tld/pager-taglib.tld" prefix="pg"%>
<%@ taglib uri="/WEB-INF/tld/admin-taglib.tld" prefix="admin"%>
 

<div class="row">
	<div class="col-md-12">		 
		<div class="alert alert-danger display-hide  alert-userauth">
			<button class="close" data-close="alert"></button>
			<span class="msg"> 提示信息区 </span>
		</div>
	</div>
</div>		 
		 
<div class="row ">
	<div class="col-md-12">
		
		<div class="row">
		<form role="form" class="form-horizontal form-queryroles">
			<input name="fromAuthmain" value="true" type="hidden"/>
			<div class="form-body">
				<div class="col-md-8">
					<div class="form-group form-md-line-input">									
						<div class="col-md-12">
							<div class="input-icon right">
								<input type="text" class="form-control  input-xs"
									placeholder="" name="roleAttr" >
								<div class="form-control-focus"></div>
								<span class="help-block">输入角色名称/角色中文名称</span>
							</div>
						</div>
					</div>
				</div>
				<div class="col-md-4">
					<div class="form-group form-md-line-input">
						<div class="col-md-12">
							<div class="input-group">

								<span class="input-group-btn btn-right">

									<button type="button"
										class="btn btn-xs green-haze btn-query "
										aria-expanded="false">查询</button>
									<button type="reset" class="btn btn-xs default reset"
										aria-expanded="false">重置</button>	
								</span>
							</div>
						</div>
					</div>
				</div>
			</div>
		</form>	
		</div>
		<div class="row">&nbsp;</div>
		<div class="row">
			<div class="col-md-12">
				<div class="portlet light bordered">
					<div class="portlet-title tabbable-line">
						<div class="caption">
							<i class="icon-pin font-yellow-crusta"></i> <span
								class="caption-subject bold font-yellow-crusta uppercase">
								待选角色 </span>

						</div>
						<div class="actions">

							<a href="javascript:;"
								class="btn btn-circle blue btn-xs btn-saveselectedauth"> <i
								class="fa fa-plus"></i> 添加选中角色
							</a>

						</div>
					</div>
					<div class="portlet-body select_cangrantedroles">
					
					</div>
				</div>
			</div>
		</div>

	</div>
</div>			 

<script type="text/javascript">
	jQuery(document).ready(function() {		
		var getSelectRoles = function(doquery){
			$(".select_cangrantedroles",ModelDialog.getCurrentModal()).load("${pageContext.request.contextPath}/sysmanager/role/queryListInfoRoles.page",
					doquery?$('.form-queryroles',ModelDialog.getCurrentModal()).serialize():{fromAuthmain:true,cangrantedRole:true},
						function(){
						
						});
		}
		getSelectRoles(false);
		
		$(".btn-saveselectedauth",ModelDialog.getCurrentModal()).bind("click",function(){
			var selectroles = "";
			var roleNames = "";
			 $('input[name="roleId"]:checked',$(".table-cangrantedroles",ModelDialog.getCurrentModal())).each(function(){ 
				 	var tr = $(this).closest('tr');
				 	// console.log(tr);
				 	// console.log($(".selected_users_movein"));
				 	var roleName = $(this).attr("roleName")+"("+$(this).attr("remark1")+")"
				 	if(selectroles == ""){
				 		selectroles = $(this).val();
				 		roleNames = roleName;
				 	}
				 	else{
				 		selectroles =selectroles+","+ $(this).val();
				 		roleNames =roleNames+","+ roleName;
				 	}
				 	 
			 	});
			 if(selectroles == ""){
				 PDP.info("没有选择角色");
				 return 
			 }
			 
			 
		 		$.ajax({
	       		   type: "POST",
	       			url : "${pageContext.request.contextPath}/sysmanager/role/saveRoleResourceAuths.page",
	       			data :{"opCode":"roleset","resCodes":selectroles,"resNames":roleNames,"resourceType":"role",roleId:'${roleId}',roleType:'${roleType}'},
	       			dataType : 'json',
	       			async:false,
	       			error : function(xhr, ajaxOptions,
							thrownError) {
						PDP.warn(thrownError) ;
					},
	       			beforeSend: function(XMLHttpRequest){ 					
	       				 	
	       				},
	       			success : function(responseText){
	       				
	       				if(responseText=="success"){
	       					
	       					PDP.success("角色授权成功!");
	       					ModelDialog.getCurrentModal().modal('hide');
	       					loadauto_resourcesauthsource();
	       				}else{
	       					PDP.warn("角色授权失败:"+responseText);
	       				}
	       			}
	       		  });
			 
			 
	     });
		
		 $(".btn-query",ModelDialog.getCurrentModal()).bind("click",function(){
			 getSelectRoles(true);
	     }); 
	});
	
</script>

<!-- END CONTENT BODY -->