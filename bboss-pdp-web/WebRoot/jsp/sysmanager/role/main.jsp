<%@ page session="false" language="java"
	contentType="text/html; charset=utf-8"%>
<%@ taglib uri="/WEB-INF/tld/pager-taglib.tld" prefix="pg"%>
<%@ taglib uri="/WEB-INF/tld/admin-taglib.tld" prefix="admin"%>


<!-- BEGIN PAGE HEADER-->
<!-- BEGIN PAGE BAR -->
<div class="page-bar">
	<admin:menuposition />

</div>
<!-- END PAGE BAR -->
<!-- BEGIN PAGE TITLE-->

<!-- END PAGE TITLE-->
<!-- END PAGE HEADER-->
<div class="row">
                 <div class="col-md-12">
                     <div class="tabbable-line boxless tabbable-reversed">
                         <ul class="nav nav-tabs">
                             <li class="active">
                                 <a href="#tab_0" data-toggle="tab"> 角色管理 </a>
                             </li>
                             <li>
                                 <a href="#tab_1" data-toggle="tab"> 角色类型管理 </a>
                             </li>
                            
                            
                         </ul>
                         <div class="tab-content">
                             <div class="tab-pane active" id="tab_0">
                             	<div class=" col-md-12">
										<div class="row">
											<div class="col-md-12">
												<div class="portlet light bordered">
								
													<div class="portlet-title">
														<div class="caption">
															<i class="icon-pin font-yellow-crusta"></i> <span
																class="caption-subject bold font-yellow-crusta uppercase">
																查询条件 </span>
								
														</div>
														
													</div>
													<div class="portlet-body form">
														<form role="form" class="form-horizontal form-queryrole">
														 
															<div class="form-body">
																<div class="row">
																	<div class="col-md-5">
																		<div class="form-group form-md-line-input">
																			<label class="col-md-3 control-label" for="form_control_1">输入条件
								
																			</label>
																			<div class="col-md-9">
																				<div class="input-icon right">
																					<input type="text" class="form-control  input-xs"
																						placeholder="角色名称/角色编码" name="roleAttr">
																					<div class="form-control-focus"></div>
																					<span class="help-block">请输入角色名称/角色编码</span>
																				</div>
																			</div>
																		</div>
																	</div>
																	<div class="col-md-5">
																		<div class="form-group form-md-line-input">
																			<label class="col-md-3 control-label" for="form_control_1">角色类型
								
																			</label>
																			<div class="col-md-9">
																				<div class="input-icon right">
																					<select class="form-control input-xs" name="roleType">
																						<option value="-1">全部</option>
																						<pg:list actual="${roleTypes }">
																							<option value="<pg:cell colName="typeId"/>"><pg:cell colName="typeName"/></option>
																						</pg:list>
																						
																					</select>
																					<div class="form-control-focus"></div>
								
																				</div>
																			</div>
																		</div>
																	</div>
																	<div class="col-md-2">
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
																
								
								
															</div>
														</form>
								
													</div>
												</div>
											</div>
										</div>
										<div class="row">
											<div class="col-md-12">
												<div class="portlet light bordered">
													<div class="portlet-title tabbable-line">
														<div class="caption">
															<i class="icon-pin font-yellow-crusta"></i> <span
																class="caption-subject bold font-yellow-crusta uppercase">
																角色列表 </span>
								
														</div>
														<div class="actions">
															<a class="btn btn-xs blue btn-addrole"  > 新增 <i class="fa fa-edit"></i></a>
															 <a class="btn btn-xs red btn-delrole" >
																<i class="fa fa-times"></i> 删除
															</a> <a href="javascript:;" class="btn btn-xs green"> 批量授权 <i
																class="fa fa-font"></i>
															</a> <a href="javascript:;" class="btn btn-xs yellow"> 批量权限回收 <i
																class="fa fa-search"></i>
															</a>
														</div>
													</div>
													<div class="portlet-body portlet-rolelist"></div>
												</div>
											</div>
										</div>
								</div>
                             </div>
                            <div class="tab-pane" id="tab_1">
                             </div>
                     </div>
                 </div>
             </div>

</div>

  <script src="${pageContext.request.contextPath}/assets/sysmanager/sysrole.js" type="text/javascript"></script>	

<script type="text/javascript">
	jQuery(document).ready(function() {
		SysRole.init('${pageContext.request.contextPath}');
		
	});
</script>

<!-- END CONTENT BODY -->