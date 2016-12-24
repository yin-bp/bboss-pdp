<%@ page session="false" language="java"
	contentType="text/html; charset=utf-8"%>
<%@ taglib uri="/WEB-INF/tld/pager-taglib.tld" prefix="pg" %>
<pg:beaninfo actual="${smUser }">		

	<!-- BEGIN FORM-->
	<form action="#" class="form-horizontal form-modifypassword" >
		<div class="form-body">
		<input type="hidden" class="form-control" name="userId"  value="<pg:cell colName="userId"/>">
		<pg:true actual="${frompersonal }">
			<div class="row">
				<div class="col-md-12" >
					<div class="form-group form-md-line-input">
						<label class="col-md-3 control-label" for="form_control_1">旧密码
							<span class="required">*</span>
						</label>
						<div class="col-md-9">
							<input type="password" class="form-control" placeholder="" name="oldPassword"  autocomplete="off">
							<div class="form-control-focus"></div>
							<span class="help-block">请输入旧密码</span>	
						</div>
					</div>
				</div>
			</div>
		</pg:true>	
		
		<div class="row">
				<div class="col-md-12" >
					<div class="form-group form-md-line-input">
						<label class="col-md-3 control-label" for="form_control_1">新密码
							<span class="required">*</span>
						</label>
						<div class="col-md-9">
							<input type="password" class="form-control" placeholder="" name="newPassword"  autocomplete="off">
							<div class="form-control-focus"></div>
							<span class="help-block">请输入新密码</span>	
						</div>
					</div>
				</div>
		</div>
		<div class="row">
				<div class="col-md-12" >
					<div class="form-group form-md-line-input">
						<label class="col-md-3 control-label" for="form_control_1">新密码确认
							<span class="required">*</span>
						</label>
						<div class="col-md-9">
							<input type="password" class="form-control" placeholder="" name="newPasswordSecond"  autocomplete="off">
							<div class="form-control-focus"></div>
							<span class="help-block">请确认新密码</span>	
						</div>
					</div>
				</div>
		</div>
		<div class="row">
				<div class="col-md-12" >&nbsp;
				</div>
		</div>
		
		<div class="form-actions">
			<div class="row">
				<div class="col-md-offset-3 col-md-9">
					<button type="button"  class="btn green passwordsave">保存</button>	
					<button type="button" class="btn default passwordreset">密码重置</button>				
					<button type="reset" class="btn default" data-dismiss="modal">取消</button>
				</div>
			</div>
		</div>
	</form>
<pg:true actual="${frompersonal }">
<script
	src="${pageContext.request.contextPath}/assets/sysmanager/sysuser.js" type="text/javascript"></script>
	<script type="text/javascript">
	jQuery(document).ready(function() {
		SysUser.initusercontextpath('${pageContext.request.contextPath}');
	});
</script>
</pg:true>	

<script type="text/javascript">
	jQuery(document).ready(function() {
		SysUser.initModifyUserPasswordAction('<pg:cell colName="userId"/>','<pg:cell colName="userRealname"/>','${frompersonal }');
		
	});
</script>
</pg:beaninfo> 
