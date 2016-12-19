<%@ page session="false" language="java"
	contentType="text/html; charset=utf-8"%>
<%@ taglib uri="/WEB-INF/tld/pager-taglib.tld" prefix="pg" %>


<pg:beaninfo actual="${role}">
	<!-- BEGIN FORM-->
	<form action="#" class="form-horizontal" >
		<div class="form-body">
		
		
			<div class="row">
				<div class="col-md-12" >
					<div class="form-group form-md-line-input">
						<input type="hidden" name="roleId"  value="<pg:cell colName="roleId"/>">
						<input type="hidden" name="ownerId"  value="<pg:cell colName="ownerId"/>">
						<input type="hidden" name="roleName"  value="<pg:cell colName="roleName"/>">
						<label class="col-md-3 control-label" for="form_control_1">角色名称
							
						</label>
						 <div class="col-md-9">
							<p class="form-control-static"> <pg:cell colName="roleName"/> </p>
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
							<p class="form-control-static"> <pg:cell colName="remark1" htmlEncode="true"/> </p>
							 
						</div>
					</div>
				</div>
			</div>
		 	
			<div class="row">
				<div class="col-md-12" >	
					<div class="form-group form-md-line-input">
						<label class="col-md-3 control-label" for="form_control_1">角色类型</label>
						<div class="col-md-9">
						 
								<p class="form-control-static"> <pg:cell colName="typeName"/> </p>
							 		
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
							<p class="form-control-static"> <pg:cell colName="roleDesc" htmlEncode="true"/> </p>
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
							<p class="form-control-static"> <pg:cell colName="ownerName"/>(<pg:cell colName="ownerAccount"/>) </p>
						</div>
					</div>
				</div>
			</div>
			<div class="row"><div class="col-md-12" >&nbsp;</div></div>
			
			<div class="form-actions">
			<div class="row">
				<div class="col-md-offset-3 col-md-9">					
					<button type="button" class="btn green" data-dismiss="modal">关闭</button>
				</div>
			</div>
		</div>
		</div>
	</form>
</pg:beaninfo>

