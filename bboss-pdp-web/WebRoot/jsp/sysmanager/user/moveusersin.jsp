<%@ page session="false" language="java"
	contentType="text/html; charset=utf-8"%>

			<div class="row">
				<div class="col-md-4" >
					   <div class="portlet light bordered">
                             <div class="portlet-title tabbable-line">
                                 <div class="caption">
                                     <i class="icon-pin font-yellow-crusta"></i>
                                     <span class="caption-subject bold font-yellow-crusta uppercase"> 组织架构 </span>
                                    
                                 </div>
                                 
                             </div>      
                            <div class="portlet-body">
                            	<div  class="scroller" style="height:450px">
                                	<div class="tree-org-moveuserin" ></div>
                                	<p></p>
                                </div>
                                
                            </div>
                        </div>
					
					
				</div>
				<div class=" col-md-8">
					<div class="row">
						<div class="col-md-12" >
							<div class="portlet light bordered">        
									   
                                    <div class="portlet-title tabbable-line">
		                                <div class="caption">
                                            <i class="icon-pin font-yellow-crusta"></i>
                                            <span class="caption-subject bold font-yellow-crusta uppercase"> 查询条件 </span>
                                           	 
                                        </div>
		                                 
		                             </div>                            
		                            <div class="portlet-body form">
                                        <form role="form" class="form-horizontal">
                                            <div class="form-body">
                                            	<div class="row">
													<div class="col-md-6" >		
														<div class="form-group form-md-line-input">
															<label class="col-md-3 control-label" for="form_control_1">输入条件
																 
															</label>
															<div class="col-md-9">
																<div class="input-icon right">
																	<input type="text" class="form-control" placeholder="请输入账号/工号/姓名/电话号码"
																		name="userAttr">
																	<div class="form-control-focus"></div>
																	
																</div>
															</div>
														</div>
													</div>
													<div class="col-md-6" >		
														<div class="form-group form-md-line-input">
															<label class="col-md-3 control-label" for="form_control_1">部门设置
																
															</label>
															<div class="col-md-9">
																<div class="input-icon right">
																	<select class="form-control" name="recursive">		                                                              		
												                                <option value="0">本机构</option>
												                                <option value="1">含子机构</option>
												                                <option value="2">所有机构</option>	                                                            
			                                                        </select>
																	<div class="form-control-focus"></div>
																	 
																</div>
															</div>
														</div>
													</div>
												</div>
												<div class="row">
													<div class="col-md-6" >	</div>
													<div class="col-md-6" >		
														<div class="form-group form-md-line-input">
															<label class="col-md-3 control-label" for="form_control_2">显示</label>
															
															<div class="col-md-9">
																<div class="input-group">
																	<div class="input-group-control">
																		<select class="form-control" name="records">
		                                                                		<option value="all">全部</option>
					                                                            <option value="10">10条记录</option>
					                                                            <option value="20">20条记录</option>
					                                                            <option value="30">30条记录</option>
					                                                            <option value="50">50条记录</option>
					                                                            <option value="100">100条记录</option>			                                                            
					                                                        </select>
																		<div class="form-control-focus"></div>
																			
																	</div>
																	<span class="input-group-btn btn-right">
																		
																		<button type="button" class="btn btn-xs green-haze  "
																			  aria-expanded="false">
																			查询
																		</button>
																		 
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
						<div class="col-md-12" >
							<div class="portlet light bordered">        
									<div class="portlet-title tabbable-line">
                                        <div class="caption">
                                            <i class="icon-pin font-yellow-crusta"></i>
                                            <span class="caption-subject bold font-yellow-crusta uppercase"> 待选用户 </span>
                                           	 
                                        </div>
                                        <div class="actions">
                                            
                                            <a href="javascript:;" class="btn btn-circle blue btn-xs">
                                                <i class="fa fa-plus"></i> 添加 </a>
                                            
                                        </div>
                                    </div>                           
		                            <div class="portlet-body">
		                                
										<div class="row">
											<div class=" col-md-12" >
												<div class="table-container">
            
										            <table class="table table-striped table-bordered table-hover table-checkable  table-header-fixed to_select_users_movein" >
										                <thead>
										                    <tr role="row" class="heading">
										                        <th width="2%">
										                            <label class="mt-checkbox mt-checkbox-single mt-checkbox-outline">
										                                <input type="checkbox" class="group-checkable" data-set=".to_select_users_movein .checkboxes" />
										                                <span></span>
										                            </label>
										                        </th>
										                        <th width="10%"> 账号 </th>
										                        <th width="10%"> 名称 </th>
										                        <th width="10%"> 工号 </th>
										                        <th width="10%"> 电话 </th>
										                        <th width="10%"> 性别 </th>
										                        <th width="10%"> 类别</th>
										                        <th width="10%"> 状态 </th>
										                        <th width="15%"> 组织</th>										                      
										                      
										                    </tr>
										                   
										                </thead>
										                <tbody> </tbody>
										            </table>
										        </div>
											</div>
											
										</div>
																	
		                            </div>
		                        </div>
		                   </div>
		             </div>
					<div class="row">
						<div class="col-md-12" >
							<div class="portlet light bordered"> 
									<div class="portlet-title tabbable-line">
                                        <div class="caption">
                                            <i class="icon-pin font-yellow-crusta"></i>
                                            <span class="caption-subject bold font-yellow-crusta uppercase"> 已选用户 </span>
                                           
                                        </div>
                                         <div class="actions">
                                            <a href="javascript:;" class="btn btn-circle blue btn-xs">
                                                <i class="fa fa-plus"></i> 提交调入记录 </a>
                                            <a href="javascript:;" class="btn btn-circle default btn-xs">
                                                <i class="fa fa-plus"></i> 清空重置 </a>
                                            
                                        </div>
                                    </div>                                   
		                            <div class="portlet-body">
		                                
										<div class="row">
											<div class=" col-md-12" >
												<div class="table-container">
            
										            <table class="table table-striped table-bordered table-hover table-checkable  table-header-fixed selected_users_movein" >
										                <thead>
										                    <tr role="row" class="heading">
										                        <th width="2%">
										                            <label class="mt-checkbox mt-checkbox-single mt-checkbox-outline">
										                                <input type="checkbox" class="group-checkable" data-set=".selected_users_movein .checkboxes" />
										                                <span></span>
										                            </label>
										                        </th>
										                        <th width="10%"> 账号 </th>
										                        <th width="10%"> 名称 </th>
										                        <th width="10%"> 工号 </th>
										                        <th width="10%"> 电话 </th>
										                        <th width="10%"> 性别 </th>
										                        <th width="10%"> 类别</th>
										                        <th width="10%"> 状态 </th>
										                        <th width="15%"> 组织</th>										                      
										                      
										                    </tr>
										                   
										                </thead>
										                <tbody> </tbody>
										            </table>
										        </div>
											</div>
											
										</div>
																	
		                            </div>
		                        </div>
		                   </div>
		             </div>
					
				</div>
				
			</div>


	
<script type="text/javascript">

	jQuery(document).ready(function() {	
		SysUser.initMoveUserInAction();
		PlatformCommonUtils.initSlimScroll('.scroller',ModelDialog.getCurrentModal());
	});
</script>