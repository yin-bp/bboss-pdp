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
	<div class="col-md-12">&nbsp;</div>
</div>

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
				<form role="form" class="form-horizontal form-querydict">

					<div class="form-body">
						<div class="row">
							<div class="col-md-3">
								<div class="form-group form-md-line-input">
									<label class="col-md-3 control-label" for="form_control_1">条件

									</label>
									<div class="col-md-9">
										<div class="input-icon right">
											<input type="text" class="form-control  input-xs"
												placeholder="字典名称/字典编码" name="dictAttr">
											<div class="form-control-focus"></div>
											<span class="help-block">请输入字典名称/字典编码</span>
										</div>
									</div>
								</div>
							</div>
							<div class="col-md-3">
								<div class="form-group form-md-line-input">
									<label class="col-md-3 control-label" for="form_control_1">状态

									</label>
									<div class="col-md-9">
										<div class="input-icon right">
											<select class="form-control input-xs" name="dictStatus">
												<option value="-1">全部</option>
												 <option value="1">正常</option>
												 <option value="0">删除</option>
													
											</select>
											<div class="form-control-focus"></div>

										</div>
									</div>
								</div>
							</div>
							<div class="col-md-3">
								<div class="form-group form-md-line-input">
									<label class="col-md-3 control-label" for="form_control_1">类型

									</label>
									<div class="col-md-9">
										<div class="input-icon right">
											<select class="form-control input-xs" name="handler">
												<option value="-1">全部</option>
												<pg:empty actual="${dictTypes }" evalbody="true">
													<pg:yes><option value="default">默认类型</option></pg:yes>
													<pg:no><pg:list actual="${dictTypes }">
														<option value="<pg:cell colName="handler"/>"><pg:cell
																colName="handlerName" /></option>
													</pg:list></pg:no>
													
												</pg:empty>	

											</select>
											<div class="form-control-focus"></div>

										</div>
									</div>
								</div>
							</div>
							<div class="col-md-3">
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
						字典列表 </span>

				</div>
				<div class="actions roleactions">
					<a class="btn btn-xs blue btn-adddict"> 新增 <i
						class="fa fa-edit"></i></a> <a class="btn btn-xs red btn-batchdeldict">
						<i class="fa fa-times"></i> 删除
					</a> 
					
				</div>
			</div>
			<div class="portlet-body portlet-dictlist"></div>
		</div>
	</div>
</div>



<script
	src="${pageContext.request.contextPath}/assets/dictmanager/sysdict.js"
	type="text/javascript"></script>

<script type="text/javascript">
	jQuery(document).ready(function() {
		SysDict.init('${pageContext.request.contextPath}');

	});
</script>

<!-- END CONTENT BODY -->