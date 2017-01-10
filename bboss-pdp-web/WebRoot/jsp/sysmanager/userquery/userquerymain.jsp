<%@ page session="false" language="java"
	contentType="text/html; charset=utf-8"%>
<%@ taglib uri="/WEB-INF/tld/pager-taglib.tld" prefix="pg"%>
 <%@ taglib uri="/WEB-INF/tld/admin-taglib.tld" prefix="admin"%>
 <div class="page-bar">
	<admin:menuposition />
</div>
<div class="row>
	<div class="col-md-12">
		
		<div class="row">
		<form role="form" class="form-horizontal form-queryusers">
			 
			<div class="form-body">
				<div class="col-md-4">
					<div class="form-group form-md-line-input">									
						<div class="col-md-12">
							<div class="input-icon right">
								<input type="text" class="form-control  input-xs"
									placeholder="" name="userAttr" >
								<div class="form-control-focus"></div>
								<span class="help-block">输入账号/中文名称/工号/电话号码</span>
							</div>
						</div>
					</div>
				</div>
				<div class="col-md-4">
					<div class="form-group form-md-line-input">
						<label class="col-md-3 control-label" for="form_control_1">用户状态

						</label>
						<div class="col-md-9">
							<div class="input-icon right">
								<select name="userIsvalid" class="form-control input-xs">
									<option value="-1">全部</option>
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
		</form>	
		</div>
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
					</div>
					<div class="portlet-body portlet_userlists">
						
					</div>
				</div>
			</div>
		</div>

	</div>
	
</div>
	
 
	 
<script type="text/javascript">
	jQuery(document).ready(function() {
		 
		var queryUsers = function(doquery){
			
			$(".portlet_userlists").load("${pageContext.request.contextPath}/sysmanager/userquery/queryUsers.page",
					doquery?$('.form-queryusers').serialize():{},
						function(){
						
						});
		}
		queryUsers();

		$(".btn-queryusers").bind("click",function(){
			queryUsers(true);
		});
		
		
	});
	
</script>

<!-- END CONTENT BODY -->