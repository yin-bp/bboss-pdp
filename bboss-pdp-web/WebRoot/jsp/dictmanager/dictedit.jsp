<%@ page session="false" language="java"
	contentType="text/html; charset=utf-8"%>
<%@ taglib uri="/WEB-INF/tld/pager-taglib.tld" prefix="pg"%>
<%@ taglib uri="/WEB-INF/tld/admin-taglib.tld" prefix="admin"%>
<table class="table-dictitemlist-template">
	<tr style="display: none">
		<td ><input type="checkbox" name="paramId" class="checkone"
			onClick="checkOne('.table-dictitemlist .checkall','.table-dictitemlist .checkone')" /></td>
		<td><div class="form-group form-md-line-input">
				<div class="col-md-12">
					<div class="input-icon right">
						<input type="text" class="form-control  input-xs" placeholder="名称"
							name="name">
						<div class="form-control-focus"></div>

					</div>
				</div>
			</div></td>
		<td><div class="form-group form-md-line-input">
				<div class="col-md-12">

					<div class="input-icon right">
						<input type="text" class="form-control  input-xs" placeholder="值"
							name="value">
						<div class="form-control-focus"></div>

					</div>
				</div>

			</div></td>

	</tr>
</table>
<pg:beaninfo actual="${dict }">
<form action="#" class="form-horizontal" >
	<input type="hidden" name="ownerId"  value="<pg:cell colName="ownerId"/>">
	<input type="hidden" name="dictId"  value="<pg:cell colName="dictId"/>">
	<div class="form-body">
		<div class="row">
			<div class="col-md-12">
				<div class="portlet light bordered">		
					<div class="portlet-title">
						<div class="caption">
							<i class="icon-pin font-yellow-crusta"></i> <span
								class="caption-subject bold font-yellow-crusta uppercase">
								字典基本信息 </span>
		
						</div>
						<div class="actions dictactions">
							<a class="btn btn-xs blue btn-dicteditsave"> 保存 <i
								class="fa fa-edit"></i></a> 
								
							</a> 							
						</div>
					</div>
					<div class="portlet-body form">
						<form role="form" class="form-horizontal form-queryrole">
		
							<div class="form-body">
								<div class="row">
									<div class="col-md-4">
										<div class="form-group form-md-line-input">
											<label class="col-md-3 control-label" for="form_control_1">名称
		
											</label>
											<div class="col-md-9">
												<div class="input-icon right">
													<input type="text" class="form-control  input-xs"
														placeholder="字典名称" name="dictName" value="<pg:cell colName="dictName"/>">
													<div class="form-control-focus"></div>
													<span class="help-block">请输入字典名称</span>
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
														placeholder="字典编码" name="dictCode" value="<pg:cell colName="dictCode"/>">
													<div class="form-control-focus"></div>
													<span class="help-block">请输入字典编码</span>
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
													<select class="form-control input-xs" name="handler">
														 
														<pg:empty actual="${dictTypes }" evalbody="true">
															<pg:yes><option value="default">默认类型</option></pg:yes>
															<pg:no>
																<pg:list actual="${dictTypes }">
																	<option value="<pg:cell colName="handler"/>"><pg:cell colName="handlerName" /></option>
																</pg:list>
															</pg:no>
															
														</pg:empty>	
		
													</select>
													<div class="form-control-focus"></div>
		
												</div>
											</div>
										</div>
									</div>
									
								</div>
								
								<div class="row">
									<div class="col-md-4">
										<div class="form-group form-md-line-input">
											<label class="col-md-3 control-label" for="form_control_1">缓存		
											</label>
											<div class="col-md-9">
												<div class="input-icon right">
													<select class="form-control input-xs" name="cacheEnable">
															<option value="1" <pg:equal colName="cacheEnable" value="1">selected</pg:equal>>启用</option>
															<option value="0" <pg:equal colName="cacheEnable" value="0">selected</pg:equal>>禁用</option>
													</select>
													<div class="form-control-focus"></div>		
												</div>
											</div>
										</div>
									</div>
									<div class="col-md-4">
										<div class="form-group form-md-line-input">
											<label class="col-md-3 control-label" for="form_control_1">结构
		
											</label>
											<div class="col-md-9">
												<div class="input-icon right">
													<select class="form-control input-xs" name="isTree">
															<option value="0" <pg:equal colName="isTree" value="0">selected</pg:equal>>列表</option>
															<option value="1" <pg:equal colName="isTree" value="1">selected</pg:equal>>树</option>
															
													</select>
													<div class="form-control-focus"></div>		
												</div>
											</div>
											 
										</div>
									</div>
									 <div class="col-md-4" >
										<div class="form-group form-md-line-input">
											<label class="col-md-3 control-label" for="form_control_1">创建人
												 
											</label>
											<div class="col-md-9">
												<p class="form-control-static"> <pg:cell colName="ownerName" /> </p>
											</div>
										</div>
									</div>
									
								</div>
								<div class="row">
									<div class="col-md-6">
										<div class="form-group form-md-line-input">
											<label class="col-md-2 control-label" for="form_control_1">状态
		
											</label>
											<div class="col-md-10">
												<div class="input-icon right">
													<select class="form-control input-xs" name="dictStatus">
															<option value="1" <pg:equal colName="dictStatus" value="1">selected</pg:equal>>正常</option>
															<option value="0" <pg:equal colName="dictStatus" value="0">selected</pg:equal>>删除</option>
															
													</select>
													<div class="form-control-focus"></div>		
												</div>
												
											</div>
											 
										</div>
									</div>
									<div class="col-md-6">
										<div class="form-group form-md-line-input">
											<label class="col-md-2 control-label" for="form_control_1">描述
		
											</label>
											<div class="col-md-10">
												<div class="input-icon right">
													<textarea class="form-control" placeholder="字典描述" name="dictDesc" rows="3"><pg:cell colName="dictDesc" htmlEncode="true"/></textarea>
													 
													<div class="form-control-focus"></div>
													<span class="help-block">请输入字典描述</span>
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
								字典数据项 </span>
		
						</div>
						<div class="actions roleactions">
							<a class="btn btn-xs blue btn-adddictitem" > 新增 <i
								class="fa fa-edit"></i></a> <a class="btn btn-xs red btn-batchdeldictitem">
								<i class="fa fa-times"></i> 删除
							</a> 
							
						</div>
					</div>
					<div class="portlet-body portlet-dictitemlist">
					</div>
				</div>
			</div>
		</div>
	</div>
</form>	
<script type="text/javascript">
	jQuery(document).ready(function() {
		SysDict.initModifyDict();
		$(".portlet-dictitemlist",ModelDialog.getCurrentModal()).load("${pageContext.request.contextPath}/dictmanager/dictitemlist.page",{
			dictId:"<pg:cell colName="dictId"/>",
			handler:"<pg:cell colName="handler"/>",
			actiontype:"edit"
		},function(){
			
		});
	});
</script>	
</pg:beaninfo>