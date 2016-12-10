<%@ page session="false" language="java"
	contentType="text/html; charset=utf-8"%>

	<!-- BEGIN FORM-->
	<form action="#" class="form-horizontal" id="form_sys_addorg">
		<div class="form-body">
		<input type="hidden" class="form-control" name="parentId"  id="parentId" value="${departId }">
		
		<div class="row">
			<div class="col-md-6" >
				<div class="form-group form-md-line-input">
					<label class="col-md-3 control-label" for="form_control_1">部门编码
						<span class="required">*</span>
					</label>
					 
					<div class="col-md-9">
						<div class="input-group">
							<div class="input-group-control">
								<input type="text" class="form-control" name="code"
									placeholder=""  autocomplete="off">
								<div class="form-control-focus"></div>
								<span class="help-block">请输入部门编码</span>	
							</div>
							<span class="input-group-btn btn-right">
								<button type="button" class="btn btn-xs green-haze  "
									  aria-expanded="false">
									检查编码
								</button>
								 
							</span>
						</div>
					</div>
				</div>
			</div>
			<div class="col-md-6" >
				<div class="form-group form-md-line-input">
					<label class="col-md-3 control-label" for="form_control_1">部门名称
						<span class="required">*</span>
					</label>
					<div class="col-md-9">
						<input type="text" class="form-control" placeholder="" name="orgName"  autocomplete="off">
						<div class="form-control-focus"></div>
						<span class="help-block">请输入部门名称</span>	
					</div>
				</div>
			</div>
		</div>
		<div class="row">
			<div class="col-md-6" >	
				<div class="form-group form-md-line-input">
					<label class="col-md-3 control-label" for="form_control_1">部门状态</label>
					<div class="col-md-9">
						<select class="form-control" name="remark3">
							<option value="1">启用</option>
                                <option value="0">禁用</option>
                                
						</select>
						<div class="form-control-focus"></div>
						<span class="help-block">请设置部门状态</span>	
					</div>
				</div>
			</div>
			<div class="col-md-6" >
				<div class="form-group form-md-line-input">
					<label class="col-md-3 control-label" for="form_control_1">部门主管
						
					</label>
					<div class="col-md-9">
						<div class="input-group">
							<div class="input-group-control">
								<input type="text" class="form-control" 
									name="orgleaderName"  autocomplete="off" readonly="readonly">
								<input type="hidden" class="form-control" 
									name="orgleader"  autocomplete="off" >
								<div class="form-control-focus"></div>
								<span class="help-block">请设置部门主管</span>	
							</div>
							<span class="input-group-btn btn-right">
								<button type="button" id="sys_addOrg_setleader_button" data-toggle="modal" class="btn btn-xs green-haze  "  
									  aria-expanded="false">
									选择
								</button>
								 
							</span>
						</div>
							
					</div>
				</div>
			</div>
		</div>
		
		
		<div class="form-actions">
			<div class="row">
				<div class="col-md-offset-3 col-md-9">
					<button type="button" id="sys_addOrg_button" class="btn green">创建</button>
					<button type="reset" class="btn default">重置</button>
					<button type="reset" class="btn default" data-dismiss="modal">取消</button>
				</div>
			</div>
		</div>
	</form>

<script type="text/javascript">
	jQuery(document).ready(function() {
		SysOrg.initAddOrg();
		
	});
</script>

