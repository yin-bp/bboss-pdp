<%@ page session="false" language="java"
	contentType="text/html; charset=utf-8"%>
<%@ taglib uri="/WEB-INF/tld/pager-taglib.tld" prefix="pg"%>
<%@ taglib uri="/WEB-INF/tld/admin-taglib.tld" prefix="admin"%>
<div class="portlet light bordered">
<div class="portlet-title">
	<div class="caption">
		<i class="icon-pin font-yellow-crusta"></i> <span
			class="caption-subject bold font-yellow-crusta uppercase tooltips" data-original-title="菜单资源授权：将菜单操作权限授予角色">
			设置角色菜单权限 </span>

	</div>
	<pg:true actual="${roleNeedGrantResource }">
	<div class="actions">			
									
								
			<admin:haspermission resource="globalrole" opcode="roleresauth" resourceType="role">
			<a
				class="btn btn-xs blue"  
				id="button_sys_add_authmenu"> 添加菜单 <i class="fa fa-edit"></i>

			</a>
			 <a class="btn btn-xs red" id="button_sys_delete_authmenu">
				<i class="fa fa-times"></i> 移除选中菜单
			</a> 
			</admin:haspermission>
		</div>
	</pg:true>	
</div>
<div class="row">
	<div class="col-md-8">
		<div class="form-group form-md-line-input">
			<label class="col-md-3 control-label" for="form_control_1">  </label>
			<div class="col-md-9">
					<div class="input-icon right">
						<input type="text" class="form-control  input-xs"
							placeholder="" name="resourceAttr">
						<div class="form-control-focus"></div>
						<span class="help-block">请输入查询条件</span>
					</div>															
			</div>
		</div>
	</div>
	<div class="col-md-4">
		<div class="form-group form-md-line-input">

			<div class="col-md-12">
				<div class="input-group">

					<span class="input-group-btn btn-left">

						<button type="button"
							class="btn btn-xs green-haze btn-querygrantedColumns"
							aria-expanded="false">查询</button>
						
					</span>
				</div>

			</div>
		</div>
	</div>
</div>								
	<div class="portlet-body portlet-grantcolumnslist">	 
	</div>
</div>
<script type="text/javascript">
		jQuery(document).ready(function() {								
			var usercontextpath = "<%=request.getContextPath()%>";
			var queryColumns = function(doquery){
				var resourceAttr=$("input[name='resourceAttr']").val();								 
				if(  PDP.containSpecial(resourceAttr) ){
					PDP.warn('查询字符串含有非法字符集,请检查输入条件！');
					return;
				}
			$(".portlet-grantcolumnslist").load(usercontextpath+"/menu/grantedcolumnsListInfo.page",
									doquery?{"resourceAttr":resourceAttr,"roleId":"${roleId}","roleType":"${roleType}","resourceType":"${resourceType}"}:
											{"roleId":"${roleId}","roleType":"${roleType}","resourceType":"${resourceType}"},
									function(){
									});	
			}
			$(".btn-querygrantedColumns").bind("click",function(){
						queryColumns(true);
						});
			queryColumns(false);
			$("#button_sys_add_authmenu").bind("click",function(){
				ModelDialog.dialog({
 					title:"选择菜单",
 					showfooter:false,
 					url:"${pageContext.request.contextPath}/menu/columnAuthTree.page",
 					params:{"roleId":"${roleId}","roleType":"${roleType}","resourceType":"${resourceType}"},
 					width:"600px",
 					height:"500px"

 	         });
			});
			
			$("#button_sys_delete_authmenu").bind("click",function(){
				var chk_value =""; 
	            $('.table-grantedcolumns input[name="menuid"]:checked').each(function(){ 
	            	if(chk_value == "")
	            		chk_value = $(this).val(); 
	            	else
	            		chk_value += ","+$(this).val(); 
	            }); 
	            if(chk_value == "")
	            {
	            	PDP.warn("请选择要删除的菜单!");
		           	 return;
	            }
	             PDP.confirm("确定要删除选中的菜单吗?",function(isConfirm){
		           	 	if(isConfirm)
		           	 	{        	 		
		           	 		
			           	 	$.ajax({
			          		   type: "POST",
			          			url : "${pageContext.request.contextPath}/sysmanager/role/deleteRoleAuthResources.page",
			          			data :{"opCode":"visible","resCodes":chk_value,"roleId":"${roleId}","roleType":"${roleType}","resourceType":"${resourceType}"},
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
			          					
			          					PDP.success("菜单删除成功!");
			          					loadauto_resourcesauthsource();
			          				}else{
			          					PDP.warn("菜单删除失败:"+responseText);
			          				}
			          			}
			          		  });
		           	 	}
				        	
					});
			});
			
			
		});
</script>
