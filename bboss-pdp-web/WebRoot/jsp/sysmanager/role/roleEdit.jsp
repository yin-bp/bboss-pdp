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
							<input type="text" class="form-control" placeholder="" name="remark1"  value="<pg:cell colName="remark1"/>">
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
						<pg:equal colName="roleType" value="1" evalbody="true">
							<pg:yes>
								<input type="hidden" name="roleType"  value="<pg:cell colName="roleType"/>">
								<p class="form-control-static"> <pg:cell colName="typeName"/> </p>
							</pg:yes>
							<pg:no>
								<select class="form-control" name="roleType">							
									<pg:list actual="${roleTypes }">
										<pg:notequal colName="typeId" value="1">
											<option value="<pg:cell colName="typeId"/>" <pg:equal colName="typeId" expressionValue="{0.roleType}">selected</pg:equal>>
											<pg:cell colName="typeName"/>
											</option>
										</pg:notequal>
									</pg:list>																						
								</select>
								<div class="form-control-focus"></div>
								<span class="help-block">请设置角色类型</span>	
							</pg:no>
						</pg:equal>							
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
							<textarea class="form-control" name="roleDesc" rows="3"><pg:cell colName="roleDesc" htmlEncode="true"/></textarea>
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
							<p class="form-control-static"> <pg:cell colName="ownerName"/>(<pg:cell colName="ownerAccount"/>) </p>
						</div>
					</div>
				</div>
			</div>
			<div class="row"><div class="col-md-12" >&nbsp;</div></div>
			
			<div class="form-actions">
			<div class="row">
				<div class="col-md-offset-3 col-md-9">
					<button type="button" class="btn green btn-rolemodifysave">保存</button>
					<button type="reset" class="btn default">重置</button>
					<button type="button" class="btn default" data-dismiss="modal">取消</button>
				</div>
			</div>
		</div>
		</div>
	</form>
</pg:beaninfo>
<script type="text/javascript">
	jQuery(document).ready(function() {
		SysRole.initModifyRole();
		
	});
</script>
