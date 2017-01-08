<%@ page session="false" language="java"
	contentType="text/html; charset=utf-8"%>
<div class="row profile-account">	
    <div class=" col-md-12">
		<div class="row">
			<div class="col-md-12">
				
						<form role="form" class="form-horizontal form-querylog">
						 
							<div class="form-body">
								<div class="row">
									<div class="col-md-6">
										<div class="form-group form-md-line-input">
											<label class="col-md-3 control-label" for="form_control_1">账号

											</label>
											<div class="col-md-9">
												<div class="input-icon right">
													<input type="text" class="form-control  input-xs"
														  name="logOperuser">
													<div class="form-control-focus"></div>
													<span class="help-block">请输入账号</span>
												</div>
											</div>
										</div>
									</div>
									<div class="col-md-6">
										<div class="form-group form-md-line-input">
											<label class="col-md-3 control-label" for="form_control_1">来源

											</label>
											<div class="col-md-9">
												<div class="input-icon right">
													<input type="text" class="form-control  input-xs"
														  name="logVisitorial">
													<div class="form-control-focus"></div>
													<span class="help-block">请输入来源</span>
												</div>
											</div>
										</div>
									</div>
								</div>	
								<div class="row">
									<div class="col-md-6">
										<div class="form-group form-md-line-input">
											<label class="col-md-3 control-label" for="form_control_1">时间

											</label>
											<div class="col-md-9">
												 <div class="input-group date-picker input-daterange" >
                                                           <input type="text" class="form-control  input-xs" name="logOpertime_start">
                                                           <span class="input-group-addon  input-group-xs"> 到 </span>
                                                           <input type="text" class="form-control  input-xs  " name="logOpertime_end"> </div>
                                                       <!-- /input-group -->
                                                        
												 																		
											</div>
										</div>
									</div>
									<div class="col-md-6">
										<div class="form-group form-md-line-input">
											<label class="col-md-3 control-label" for="form_control_1">模块

											</label>
											<div class="col-md-9">
												<div class="input-icon right">
													<select class="form-control input-xs" name="operModule">
														<option value="-1">全部</option>
														<pg:list actual="${logModules }">
															<option value="<pg:cell colName="logmodule"/>"><pg:cell colName="logmodule"/></option>
														</pg:list>
														
													</select>
													<div class="form-control-focus"></div>

												</div>
											</div>
										</div>
									</div>
									 
								</div>
								<div class="row">
									<div class="col-md-6">&nbsp;</div>
									<div class="col-md-6">
										<div class="form-group form-md-line-input">

											<div class="col-md-12">
												<div class="input-group">

													<span class="input-group-btn btn-left">

														<button type="button"
															class="btn btn-xs green-haze btn-querylog "
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
		<div class="row">
			<div class="col-md-12">
				<div class="portlet light bordered">
					<div class="portlet-title tabbable-line">
						<div class="caption">
							<i class="icon-pin font-yellow-crusta"></i> <span
								class="caption-subject bold font-yellow-crusta uppercase">
								日志列表 </span>

						</div>
						<div class="actions roleactions">
							<a class="btn btn-xs blue btn-addrole"  > 归档日志 <i class="fa fa-edit"></i></a>
							<a class="btn btn-xs blue btn-addrole"  > 日志统计 <i class="fa fa-edit"></i></a>
							
							 <a class="btn btn-xs red btn-batchdelrole" >
								<i class="fa fa-times"></i> 清空日志
							</a> 
						</div>
					</div>
					<div class="portlet-body portlet-loglist"></div>
				</div>
			</div>
		</div>
	</div>
</div>
	<script type="text/javascript">
	jQuery(document).ready(function() {
		 $('.date-picker').datepicker({
	            rtl: App.isRTL(),
	            autoclose: true,
	            language:'zh-CN',
	            format:'yyyy-mm-dd'
	        });
		 
		 var querylog = function(doquery){
			 $(".portlet-loglist").load("${pageContext.request.contextPath}/logmanager/queryListInfoLogs.page",
						doquery?$('.form-querylog').serialize():{},
								function(){
								
								});
		 }
		 querylog();
		$(".btn-querylog").bind("click",function(){
			querylog(true);
		})
	});
</script>	                            
	                              