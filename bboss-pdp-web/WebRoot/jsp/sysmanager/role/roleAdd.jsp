<%@ taglib uri="/WEB-INF/tld/pager-taglib.tld" prefix="pg" %>
<%@ taglib uri="/WEB-INF/tld/admin-taglib.tld" prefix="admin"%>
	<%@ page session="false" language="java"
	contentType="text/html; charset=utf-8"%>
<div class="row">
	<div class="col-md-12">		 
		<div class="alert alert-danger display-hide  alert-addroleexist">
			<button class="close close-addroleexist" data-close="alert"></button>
			<span class="msg"> 提示信息区 </span>
		</div>
	</div>
</div>
<div class="row">
	<div class="col-md-12">		 
		<div class="alert alert-success display-hide  alert-addrolenotexist">
			<button class="close close-addrolenotexist" data-close="alert"></button>
			<span class="msg"> 提示信息区 </span>
		</div>
	</div>
</div>
	<!-- BEGIN FORM-->
	<form action="#" class="form-horizontal form_sys_addrole" >
		<input type="hidden" name="ownerId"  value="<admin:accesscontrol userattribute="userID"/>">
		<div class="form-body">
		
		
			<div class="row">
				<div class="col-md-12" >
					<div class="form-group form-md-line-input">
						<label class="col-md-3 control-label" for="form_control_1">角色名称
							<span class="required">*</span>
						</label>
						 
						<div class="col-md-9">
							<div class="input-group">
								<div class="input-group-control">
									<input type="text" class="form-control" name="roleName"
										placeholder=""  autocomplete="off">
									<div class="form-control-focus"></div>
									<span class="help-block">请输入角色名称</span>	
								</div>
								<span class="input-group-btn btn-right">
									<button type="button" class="btn btn-xs green-haze btn-checkrolerexist "
										  aria-expanded="false">
										检查角色名称
									</button>
									 
								</span>
							</div>
						</div>
					</div>
				</div>
			</div>
			<div class="row">	
				<div class="col-md-12" >
					<div class="form-group form-md-line-input">
						<label class="col-md-3 control-label" for="form_control_1">中文名称
							<span class="required">*</span>
						</label>
						<div class="col-md-9">
							<input type="text" class="form-control" placeholder="" name="remark1"  autocomplete="off">
							<div class="form-control-focus"></div>
							<span class="help-block">请输入角色中文名</span>	
						</div>
					</div>
				</div>
			</div>
		 	
			<div class="row">
				<div class="col-md-12" >	
					<div class="form-group form-md-line-input">
						<label class="col-md-3 control-label" for="form_control_1">角色类型</label>
						<div class="col-md-9">
							<select class="form-control" name="roleType">
							
								<pg:list actual="${roleTypes }">
									<pg:notequal colName="typeId" value="1">
										<option value="<pg:cell colName="typeId"/>"><pg:cell colName="typeName"/></option>
									</pg:notequal>
								</pg:list>
																					
							</select>
							<div class="form-control-focus"></div>
							<span class="help-block">请设置角色类型</span>	
						</div>
					</div>
				</div>
			</div>
		 	
			<div class="row">	
				<div class="col-md-12" >
					<div class="form-group form-md-line-input">
						<label class="col-md-3 control-label" for="form_control_1">描述
							
						</label>
						<div class="col-md-9">
							<textarea class="form-control" name="roleDesc" rows="3"></textarea>
							<div class="form-control-focus"></div>
							<span class="help-block">请输入角色描述</span>
						</div>
						
					</div>
				</div>
			</div>
			<div class="row">	
				<div class="col-md-12" >
					<div class="form-group form-md-line-input">
						<label class="col-md-3 control-label" for="form_control_1">创建人
							 
						</label>
						<div class="col-md-9">
							<p class="form-control-static"> <admin:accesscontrol userattribute="userName"/>(<admin:accesscontrol userattribute="userAccount"/>) </p>
						</div>
					</div>
				</div>
			</div>
			<div class="row"><div class="col-md-12" >&nbsp;</div></div>
			
			<div class="form-actions">
			<div class="row">
				<div class="col-md-offset-3 col-md-9">
					<button type="button" class="btn green btn-roleaddsave">创建</button>
					<button type="reset" class="btn default">重置</button>
					<button type="button" class="btn default" data-dismiss="modal">取消</button>
				</div>
			</div>
		</div>
		</div>
	</form>

<script type="text/javascript">
	jQuery(document).ready(function() {
		SysRole.initAddRole();
		$(".btn-checkrolerexist").bind("click",function(){
			var roleName = $(".form_sys_addrole input[name='roleName']").val()
			if(roleName != ""){
				$.ajax({
			 		   type: "POST",
			 			url : "${pageContext.request.contextPath}/sysmanager/role/checkroleexist.page",
			 			data :{"roleName":roleName},
			 			dataType : 'json',
			 			async:false,
			 			beforeSend: function(XMLHttpRequest){ 					
			 				 	
			 				},
			 			success : function(responseText){
			 				
			 				if(responseText=="exist"){		 					
			 					 PDP.showError(".alert-addroleexist","角色"+roleName+"已被占用!");
			 					$(".close-addrolenotexist").trigger("click");
			 				}else{
			 					PDP.showError(".alert-addrolenotexist","角色"+roleName+"没有被占用，可以使用!");
			 					$(".close-addroleexist").trigger("click");
			 				}
			 			}
			 		  });
			}
			
		});
		
	});
</script>