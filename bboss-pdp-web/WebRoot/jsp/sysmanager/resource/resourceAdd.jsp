<%@ taglib uri="/WEB-INF/tld/pager-taglib.tld" prefix="pg" %>
<%@ taglib uri="/WEB-INF/tld/admin-taglib.tld" prefix="admin"%>
	<%@ page session="false" language="java"
	contentType="text/html; charset=utf-8"%>

	<!-- BEGIN FORM-->
	<form action="#" class="form-horizontal" >
		<input type="hidden" name="restypeId"  value="${restypeId }">
		<div class="form-body">
		
		
			<div class="row">
				<div class="col-md-12" >
					<div class="form-group form-md-line-input">
						<label class="col-md-3 control-label" for="form_control_1">资源编码
							<span class="required">*</span>
						</label>
						 
						<div class="col-md-9">
							<div class="input-group">
								<div class="input-group-control">
									<input type="text" class="form-control" name="resCode"
										placeholder=""  autocomplete="off">
									<div class="form-control-focus"></div>
									<span class="help-block">请输入资源编码</span>	
								</div>
								<span class="input-group-btn btn-right">
									<button type="button" class="btn btn-xs green-haze  "
										  aria-expanded="false">
										检查资源编码
									</button>
									 
								</span>
							</div>
						</div>
					</div>
				</div>
			</div>
			<div class="row">	
				<div class="col-md-12" >
					<div class="form-group form-md-line-input">
						<label class="col-md-3 control-label" for="form_control_1">资源名称
							<span class="required">*</span>
						</label>
						<div class="col-md-9">
							<input type="text" class="form-control" placeholder="" name="resName"  autocomplete="off">
							<div class="form-control-focus"></div>
							<span class="help-block">请输入资源名称</span>	
						</div>
					</div>
				</div>
			</div>
		 	
			
			<div class="row"><div class="col-md-12" >&nbsp;</div></div>
			
			<div class="form-actions">
			<div class="row">
				<div class="col-md-offset-3 col-md-9">
					<button type="button" class="btn green btn-resourceaddsave">创建</button>
					<button type="reset" class="btn default">重置</button>
					<button type="button" class="btn default" data-dismiss="modal">取消</button>
				</div>
			</div>
		</div>
		</div>
	</form>

<script type="text/javascript">
	jQuery(document).ready(function() {
		SysResource.initAddResourceSaveAction();
		
	});
</script>