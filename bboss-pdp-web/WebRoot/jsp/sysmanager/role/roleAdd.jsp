
<%@ taglib uri="/WEB-INF/tld/pager-taglib.tld" prefix="pg" %>

	<%@ page session="false" language="java"
	contentType="text/html; charset=utf-8"%>

	<!-- BEGIN FORM-->
	<form action="#" class="form-horizontal" >
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
									<button type="button" class="btn btn-xs green-haze  "
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
							<span class="required">*</span>
						</label>
						<div class="col-md-9">
							<input type="text" class="form-control" placeholder="" name="roleDesc"  autocomplete="off">
							<div class="form-control-focus"></div>
							<span class="help-block">请输入角色描述</span>	
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
		
	});
</script>