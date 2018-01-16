<%@ page session="false" language="java"
	contentType="text/html; charset=utf-8"%>
<div class="row>
	<div class="col-md-12">
		
		
		<form role="form" class="form-horizontal form-queryusers">
			 <input type="hidden" name="departId" >
			<div class="form-body">
				<div class="row">
					<div class="col-md-4">
						<div class="form-group form-md-line-input">		
							<label class="col-md-3 control-label" for="form_control_1">条件
	
							</label>							
							<div class="col-md-9">
								<div class="input-icon right">
									<input type="text" class="form-control  input-xs"
										placeholder="" name="userAttr" >
									<div class="form-control-focus"></div>
									<span class="help-block">账号/中文名/工号/电话/证件</span>
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
									<select name="userIsvalid" class="form-control input-xs">
										<option value="">全部</option>
										<option value="2">开通</option>
										<option value="1">申请</option>
										<option value="3">停用</option>
										<option value="0">删除</option>
									</select>
									<div class="form-control-focus"></div>
	
								</div>
							</div>
						</div>
					</div>
					<div class="col-md-4">
						<div class="form-group form-md-line-input">
							<label class="col-md-3 control-label" for="form_control_1">性别
	
							</label>
							<div class="col-md-9">
								<div class="input-icon right">
									<dict:select  type="sex" name="userSex" textValue="全部" textNAN="" extend="class=\"form-control input-xs\"" />
                           
									<div class="form-control-focus"></div>
	
								</div>
							</div>
						</div>
					</div>
				</div>
			</div>
			<div class="row">
					<div class="col-md-4">
						<div class="form-group form-md-line-input">
							<label class="col-md-3 control-label" for="form_control_1">部门
	
							</label>
							<div class="col-md-9">
								<div class="input-icon right">
									<select name="recursive" class="form-control   input-xs">
                              
		                                <option value="0">本机构</option>
		                                <option value="1">含子机构</option>
										<pg:true actual="${isAdmin}">
											<option value="2">所有机构</option>
											<option value="3">所有用户</option>
										</pg:true>
		                            </select>  
									<div class="form-control-focus"></div>
	
								</div>
							</div>
						</div>
					</div>
					<div class="col-md-4">
						<div class="form-group form-md-line-input">
							<label class="col-md-3 control-label" for="form_control_1">类型
	
							</label>
							<div class="col-md-9">
								<div class="input-icon right">
									<select name="userType" class="form-control   input-xs">
		                                <option value="">全部</option>
		                                <option value="0">系统用户</option>
		                                <option value="1">域用户</option>
		                                <option value="2">第三方用户</option>
		                                
		                            </select> 
									<div class="form-control-focus"></div>
	
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
											class="btn btn-xs green-haze btn-queryusers "
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
					<div class="portlet-title tabbable-line">
						<div class="caption">
							<i class="icon-pin font-yellow-crusta"></i> <span
								class="caption-subject bold font-yellow-crusta uppercase">
								用户列表</span>

						</div>
						<div class="actions">
						<admin:haspermission resource="orgunit" opcode="usermanager" resourceType="orgunit">
							<a
								class="btn btn-xs blue"  
								data-toggle="modal" data-backdrop="static" id="button_sys_add_user"> 新增 <i class="fa fa-edit"></i>
				
							</a>
							 <a class="btn btn-xs red" id="button_sys_delete_user">
								<i class="fa fa-times"></i> 删除
							</a> 
							<!-- 
							<a href="javascript:;" class="btn btn-xs green"> 批量授权 <i
								class="fa fa-font"></i>
							</a> 
							<a href="javascript:;" class="btn btn-xs yellow"> 批量角色设置 <i
								class="fa fa-search"></i> -->
							
							</a> <a href="javascript:;" class="btn btn-xs purple" id="button_sys_order_user"> <i
								class="fa fa-file-o"></i> 用户排序
							</a> <a href="javascript:;" class="btn btn-xs green" id="button_sys_moveout_user"> 用户调出 <i
								class="fa fa-plus"></i>
							</a> <a href="javascript:;" class="btn btn-xs grey-cascade" id="button_sys_movein_user"> 用户调入 <i
								class="fa fa-link"></i>
							</a>
							</admin:haspermission>
						</div>						
					</div>
					<div class="portlet-body portlet_userlists">
						
					</div>
				</div>
			</div>
		</div>

	</div>
	
</div>	

<script
	src="${pageContext.request.contextPath}/assets/sysmanager/sysuser.js" type="text/javascript"></script>
<script
	src="${pageContext.request.contextPath}/assets/sysmanager/moveuser.js" type="text/javascript"></script>
	
<script type="text/javascript">
	jQuery(document).ready(function() {
		SysUser.init('${pageContext.request.contextPath}');
		SysMoveUsers.init('${pageContext.request.contextPath}');
		$(".btn-queryusers").bind("click",function(){
			var vr = Sysmanager.validateDepart();
           
             if(!vr)
            {
           	  return;
            }	
			 $(".form-queryusers input[name='departId']").val(Sysmanager.getDepartId());
			SysUser.queryUserList(Sysmanager.getDepartId(),true);
		});
		
	});
</script>