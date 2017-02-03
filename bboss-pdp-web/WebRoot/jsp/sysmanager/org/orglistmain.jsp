<%@ page session="false" language="java"
	contentType="text/html; charset=utf-8"%>
<div class="row>
	<div class="col-md-12">
		
		
		<form role="form" class="form-horizontal form-queryorgs">
			 <input type="hidden" name="departId" >
			<div class="form-body">
				<div class="row">
					<div class="col-md-4">
						<div class="form-group form-md-line-input">		
							<label class="col-md-3 control-label" for="form_control_1">名称
	
							</label>							
							<div class="col-md-9">
								<div class="input-icon right">
									<input type="text" class="form-control  input-xs"
										placeholder="" name="orgName" >
									<div class="form-control-focus"></div>
									<span class="help-block">部门名称</span>
								</div>
							</div>
						</div>
					</div>
					<div class="col-md-4">
						<div class="form-group form-md-line-input">		
							<label class="col-md-3 control-label" for="form_control_1">编码
	
							</label>							
							<div class="col-md-9">
								<div class="input-icon right">
									<input type="text" class="form-control  input-xs"
										placeholder="" name="code" >
									<div class="form-control-focus"></div>
									<span class="help-block">部门编码</span>
								</div>
							</div>
						</div>
					</div>
					<div class="col-md-4">
						<div class="form-group form-md-line-input">
							<label class="col-md-3 control-label" for="form_control_1">状态
	
							</label>
							<div class="col-md-9">
								<div class="input-icon right">
									<select name="remark3" class="form-control form-filter input-xs">
		                                <option value="">全部</option>
		                                
		                                <option value="1">启用</option>
		                               
		                                <option value="0">禁用</option>
		                            </select> 
									<div class="form-control-focus"></div>
	
								</div>
							</div>
						</div>
					</div>
					 
				</div>
			</div>
			<div class="row">
					<div class="col-md-4">
						 
					</div>
					<div class="col-md-4">
						 
					</div>
					<div class="col-md-4">
						<div class="form-group form-md-line-input">
							<div class="col-md-12">
								<div class="input-group">
	
									<span class="input-group-btn btn-right">
	
										<button type="button"
											class="btn btn-xs green-haze btn-queryorgs "
											aria-expanded="false">查询</button>
										<button type="reset" class="btn btn-xs default reset"
											aria-expanded="false">重置</button>	
									</span>
								</div>
							</div>
						</div>
					</div>
				</div>
			</div>
		</form>	
		
		<div class="row">&nbsp;</div>
		<div class="row">
			<div class="col-md-12">
				<div class="portlet light bordered">
					<div class="portlet-title">
						<div class="caption">
							<i class="icon-settings font-dark"></i> <span
								class="caption-subject font-dark sbold uppercase">机构列表</span>
						</div>
						<div class="actions">	
							<admin:haspermission resource="orgunit" opcode="orgmanager" resourceType="orgunit">
							<a
								class="btn btn-xs blue"  
								data-toggle="modal" data-backdrop="static" id="button_sys_add_org"> 新增 <i class="fa fa-edit"></i>
				
							</a>
							 <a class="btn btn-xs red" id="button_sys_delete_org">
								<i class="fa fa-times"></i> 删除
							</a>
							 
							<a href="javascript:;" onclick="javascript:SysOrg.buildTreeLevel();" class="btn btn-xs purple"> <i
								class="fa fa-file-o"></i> 更新机构层级关系
							</a>
							</admin:haspermission>
						</div>
				
					</div>
					<div class="portlet-body portlet_orglists">
						
					</div>
				</div>
			</div>
		</div>

	</div>
	
</div>	

	
<script type="text/javascript">
	jQuery(document).ready(function() {
		
	});
</script>
