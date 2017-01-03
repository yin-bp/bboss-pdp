<%@ page session="false" language="java"
	contentType="text/html; charset=utf-8"%>
<%@ taglib uri="/WEB-INF/tld/pager-taglib.tld" prefix="pg"%>
<%@ taglib uri="/WEB-INF/tld/admin-taglib.tld" prefix="admin"%>
<link
	href="${pageContext.request.contextPath}/assets/pages/css/profile-2.min.css"
	rel="stylesheet" type="text/css" />


<div class="row">
	<div class="col-md-12">		 
		<div class="alert alert-danger display-hide  alert-userauth">
			<button class="close" data-close="alert"></button>
			<span class="msg"> 提示信息区 </span>
		</div>
	</div>
</div>
<div class="profile">
	<div class="tabbable-line tabbable-full-width">
		<ul class="nav nav-tabs">

			<li class="active"><a href="#tab_userroleset" data-toggle="tab">
					角色设置 </a></li>
			<li><a href="#tab_userauthset" data-toggle="tab"> 资源授权 </a></li>
		</ul>
		<div class="tab-content">
			<!--tab_1_2-->
			<div class="tab-pane  active" id="tab_userroleset">
				<div class="row profile-account">
					<div class="col-md-6">
						
						<div class="row">
						<form role="form" class="form-horizontal form-queryroles">
							<input name="fromAuthmain" value="true" type="hidden"/>
							<div class="form-body">
								<div class="col-md-8">
									<div class="form-group form-md-line-input">									
										<div class="col-md-12">
											<div class="input-icon right">
												<input type="text" class="form-control  input-xs"
													placeholder="" name="roleAttr" >
												<div class="form-control-focus"></div>
												<span class="help-block">输入角色名称/角色中文名称</span>
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
														class="btn btn-xs green-haze btn-query "
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
												待选角色 </span>
				
										</div>
										<div class="actions">
				
											<a href="javascript:;"
												class="btn btn-circle blue btn-xs dotempadd_btn"> <i
												class="fa fa-plus"></i> 添加选中角色
											</a>
				
										</div>
									</div>
									<div class="portlet-body select_roles">
									
									</div>
								</div>
							</div>
						</div>
		
					</div>
					<div class="col-md-6">
						<div class="row">
							<div class="col-md-12">
								<div class="portlet light bordered">
									<div class="portlet-title tabbable-line">
										<div class="caption">
											<i class="icon-pin font-yellow-crusta"></i> <span
												class="caption-subject bold font-green ">
												已授予角色 -roleofeveryone为所以用户默认拥有角色，无需设置给用户</span>
				
										</div>
										<div class="actions">
											
											<a href="javascript:;"
												class="btn btn-circle blue btn-xs btn-submituserroles"> <i
												class="fa fa-plus"></i> 保存
											</a> <a href="javascript:;"
												class="btn btn-circle default btn-xs clearselectedrole_btn">
												<i class="fa fa-plus"></i> 移除
											</a>
				
										</div>
									</div>
									<div class="portlet-body ">
										<table
											class="table table-bordered table-striped table-condensed  table-selected-roles">
				
											<thead>
												<tr>
													<th width="2%"><input type="checkbox" class="checkboxall"
														onClick="checkAll('.table-selected-roles .checkboxall','.table-selected-roles .checkone')" /></th>
													
													<th width="10%">角色</th>
													<th width="20%">中文名</th>
													 
												</tr>
											</thead>
											<tbody>
												<pg:list actual="${userroles }">
													 <tr>
												  	 	<td><input
																name="roleId" type="checkbox" class="checkone" onClick="checkOne('.table-selected-roles .checkboxall','.table-selected-roles .checkone')" 
																value="<pg:cell colName="roleId"/>" 
																roleName="<pg:cell colName="roleName"/>" 
																remark1="<pg:cell colName="remark1"/>"/>
														</td>			
												  	 	
											            <td> <pg:cell colName="roleName"/> </td>
											            <td> <pg:cell colName="remark1"/><pg:cell colName="startDate" dateformat="yyyy-MM-dd mm:HH:ss"/> <pg:cell colName="endDate" dateformat="yyyy-MM-dd mm:HH:ss"/> </td>
											            
											        </tr>
												</pg:list>
												
				
											</tbody>
										</table>
				
									</div>
				
								</div>
							</div>
						</div>
					
					</div>
					<!--end col-md-9-->
				</div>
			</div>
			<!--end tab-pane-->
			<div class="tab-pane" id="tab_userauthset">
				<div class="row profile-account">
					<div class="col-md-3">
						<ul class="ver-inline-menu tabbable margin-bottom-10 resourcetypelist">
						<pg:list actual="${resourceTypes }">
							<li <pg:equal expression="{rowid }" value="0">class="active"</pg:equal>>
								<a data-toggle="tab" href="#resource_tab_1_1" resourceType="<pg:cell colName="id"/>" resourceName="<pg:cell colName="name"/>">
										<i class="fa fa-cog"></i> <pg:cell colName="name"/> <pg:cell colName="id"/>
								</a> <span class="after"> </span>
							</li>
						</pg:list>	
							 
						</ul>
					</div>
					<div class="col-md-9">
						<div class="tab-content">
							<div id="resource_operations" class="tab-pane active">
							<p>请选择一个资源进行操作</p>
							</div>
							 
							 
						</div>
					</div>
					<!--end col-md-9-->
				</div>
			</div>
			<!--end tab-pane-->
		</div>
	</div>
</div>
<script
	src="${pageContext.request.contextPath}/assets/sysmanager/authmain.js" type="text/javascript"></script>
<script
	src="${pageContext.request.contextPath}/assets/sysmanager/sysroleauthset.js" type="text/javascript"></script>	
	 
<script type="text/javascript">
	jQuery(document).ready(function() {
		 
		
		SysAuthmain.initAuthmain('${pageContext.request.contextPath}','${userId}');
		SysRoleAuthset.initRoleAuthset('${pageContext.request.contextPath}','${roleId}','${roleType}','${resourceType}','${resourceName}');
		
	});
	
</script>

<!-- END CONTENT BODY -->