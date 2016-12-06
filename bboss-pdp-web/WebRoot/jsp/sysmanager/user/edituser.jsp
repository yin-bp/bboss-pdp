<%@ page session="false" language="java"
	contentType="text/html; charset=utf-8"%>
<%@ taglib uri="/WEB-INF/tld/pager-taglib.tld" prefix="pg" %>
<pg:beaninfo actual="${smUser }">		
<div class="modal-header">
	<button type="button" class="close" data-dismiss="modal"
		aria-hidden="true"></button>
	<h4 class="modal-title">修改用户<span class="label label-sm label-success"><pg:cell colName="userName"/></span></h4>
</div>
<div class="modal-body">
	<!-- BEGIN FORM-->
	<form action="#" class="form-horizontal" id="form_sys_modifyuser">
		<div class="form-body">
		<input type="hidden" class="form-control" name="departId"  id="departId" value="<pg:cell colName="departId"/>">
		<input type="hidden" class="form-control" name="userSn"   value="<pg:cell colName="userSn"/>">
		<div class="row">
			<div class="col-md-6" >
				<div class="form-group  form-md-line-input">
					<label class="col-md-3 control-label"">账号:				 
					</label>
					 
					<div class="col-md-9">
						<input type="hidden"  name="userName" value="<pg:cell colName="userName"/>" >
						<input type="hidden"  name="userId" value="<pg:cell colName="userId"/>" >
						<p class="form-control-static"> <pg:cell colName="userName"/> </p>
					</div>
				</div>
			</div>
			<div class="col-md-6" >
				<div class="form-group form-md-line-input">
					<label class="col-md-3 control-label" for="form_control_1">中文名
						<span class="required">*</span>
					</label>
					<div class="col-md-9">
						<input type="text" class="form-control" placeholder="" name="userRealname" value="<pg:cell colName="userRealname"/>" autocomplete="off">
						<div class="form-control-focus"></div>
					</div>
				</div>
			</div>
		</div>
		<div class="row">
			<div class="col-md-6" >
				<div class="form-group form-md-line-input">
					<label class="col-md-3 control-label" for="form_control_1">口令
						<span class="required">*</span>
					</label>
					<div class="col-md-9">
						<input type="password" class="form-control" placeholder="123456" name="userPassword" id="userPassword" autocomplete="off" value="<pg:cell colName="passwordText" defaultValue="123456"/>">
						<div class="form-control-focus"></div>
						<span class="help-block passwordhelp" ></span>	
					</div>
				</div>
			</div>
			<div class="col-md-6" >
				<div class="form-group form-md-line-input">
					<label class="col-md-3 control-label" for="form_control_1">确认口令
						<span class="required">*</span>
					</label>
					<div class="col-md-9">
						<input type="password" class="form-control" placeholder="123456"
							name="userPasswordSecond" id="userPasswordSecond" autocomplete="off"  value="<pg:cell colName="passwordText" defaultValue="123456"/>">
						<div class="form-control-focus"></div>
					</div>
				</div>
			</div>
		</div>
		<div class="row">
			<div class="col-md-6" >
				<div class="form-group form-md-line-input">
					<label class="col-md-3 control-label" for="form_control_1">口令有效期(天)
						<span class="required">*</span>
					</label>
					<div class="col-md-9">
						<input type="text" class="form-control" placeholder="" name="passwordDualtime"  value="<pg:cell colName="passwordDualtime"/>">
						<div class="form-control-focus"></div>
					</div>
				</div>
			</div>
			<div class="col-md-6" >
				<div class="form-group form-md-line-input">
					<label class="col-md-3 control-label" for="form_control_1">身份证
						<span class="required">*</span>
					</label>
					<div class="col-md-9">
						<input type="text" class="form-control" placeholder=""
							name="userIdcard" value="<pg:cell colName="userIdcard"/>">
						<div class="form-control-focus"></div>
					</div>
				</div>
			</div>
		</div>
		<div class="row">
			<div class="col-md-6" >		
				<div class="form-group form-md-line-input">
					<label class="col-md-3 control-label" for="form_control_1">手机号码
						<span class="required">*</span>
					</label>
					<div class="col-md-9">
						<div class="input-icon">
							<input type="text" class="form-control" placeholder=""
								name="userMobiletel1" value="<pg:cell colName="userMobiletel1"/>">
							<div class="form-control-focus"></div>
							<i class="fa fa-bell-o"></i>
						</div>
					</div>
				</div>
			</div>
			<div class="col-md-6" >		
				<div class="form-group form-md-line-input">
					<label class="col-md-3 control-label" for="form_control_1">用户状态
						<span class="required">*</span>
					</label>
					<div class="col-md-9">
						<div class="input-icon right">
							<select class="form-control" name="userIsvalid">
								<pg:case colName="userIsvalid"> 
									
									<option value="2" <pg:equal value="2">selected</pg:equal>>开通</option>
	                                <option value="1" <pg:equal value="1">selected</pg:equal>>申请</option>
	                                <option value="3" <pg:equal value="3">selected</pg:equal>>停用</option>
	                                <option value="0" <pg:equal value="0">selected</pg:equal>>删除</option>
									
									
									
								</pg:case>
								
							</select>
							<div class="form-control-focus"></div>
							 
						</div>
					</div>
				</div>
			</div>
		</div>
		<div class="row">
			<div class="col-md-6" >							
				<div class="form-group form-md-line-input">
					<label class="col-md-3 control-label" for="form_control_1">邮箱</label>
					<div class="col-md-9">
						<div class="input-group">
							<span class="input-group-addon"> <i class="fa fa-envelope"></i>
							</span> <input type="text" class="form-control" name="userEmail"  value="<pg:cell colName="userEmail"/>"
								placeholder="Enter your email">
							<div class="form-control-focus"></div>
						</div>
					</div>
				</div>
			</div>
			<div class="col-md-6" >
				<div class="form-group form-md-line-input">
					<label class="col-md-3 control-label" for="form_control_1">住址</label>
					<div class="col-md-9">
						<div class="input-group">
							<input type="text" class="form-control" name="userAddress"  value="<pg:cell colName="userAddress"/>"
								placeholder=""> <span class="input-group-addon">
								<i class="fa fa-i-cursor"></i>
							</span>
							<div class="form-control-focus"></div>
						</div>
					</div>
				</div>
			</div>
		</div>	
		<div class="row">
			<div class="col-md-6" >		
				<div class="form-group form-md-line-input">
					<label class="col-md-3 control-label" for="form_control_1">工号</label>
					
					<div class="col-md-9">
						<div class="input-group">
							<div class="input-group-control">
								<input type="text" class="form-control" name="userWorknumber"  value="<pg:cell colName="userWorknumber"/>"
									placeholder=""  autocomplete="off">
								<div class="form-control-focus"></div>
								<span class="help-block">请输入工号</span>	
							</div>
							<span class="input-group-btn btn-right">
								<button type="button" class="btn btn-xs green-haze  "
									  aria-expanded="false">
									检查工号
								</button>
								 
							</span>
						</div>
						
					</div>
				</div>
			</div>
			<div class="col-md-6" >	
				<div class="form-group form-md-line-input">
					<label class="col-md-3 control-label" for="form_control_1">性别</label>
					<div class="col-md-9">
						<select class="form-control" name="userSex">
							<pg:case colName="userSex"> 									
									<option value="M" <pg:equal value="M">selected</pg:equal>>男</option>
	                                <option value="F" <pg:equal value="F">selected</pg:equal>>女</option>
	                                <option value="-1" <pg:equal value="-1">selected</pg:equal>>未知</option>
							</pg:case>
							
						</select>
						<div class="form-control-focus"></div>
					</div>
				</div>
			</div>
		</div>	
		<div class="row">
			<div class="col-md-6" >			
				<div class="form-group form-md-line-input">
					<label class="col-md-3 control-label" for="form_control_1">用户类型</label>
					<div class="col-md-9">
						<select class="form-control" name="userType">
							
                               <pg:case colName="userType"> 									
									<option value="0" <pg:equal value="0">selected</pg:equal>>系统用户</option>
	                                <option value="1" <pg:equal value="1">selected</pg:equal>>域用户</option>
	                                <option value="2" <pg:equal value="2">selected</pg:equal>>第三方用户</option>
							</pg:case>
						</select>
						<div class="form-control-focus"></div>
					</div>
				</div>
			</div>
			<div class="col-md-6" >		
				<div class="form-group form-md-line-input">
					<label class="col-md-3 control-label" for="form_control_1">备注</label>
					<div class="col-md-9">
						<textarea class="form-control" name="remark1" rows="3"><pg:cell colName="userWorknumber" htmlEncode="true"/></textarea>
						<div class="form-control-focus"></div>
					</div>
				</div>
			</div>
		</div>
		
		<div class="form-actions">
			<div class="row">
				<div class="col-md-offset-3 col-md-9">
					<button type="button" id="sys_modifyUser_button" class="btn green">保存</button>
					<button type="reset" class="btn default">重置</button>
					<button type="reset" class="btn default" data-dismiss="modal">取消</button>
				</div>
			</div>
		</div>
	</form>
</div>
<script type="text/javascript">
	jQuery(document).ready(function() {
		SysUser.initModifyUser();
		
	});
</script>
</pg:beaninfo> 
