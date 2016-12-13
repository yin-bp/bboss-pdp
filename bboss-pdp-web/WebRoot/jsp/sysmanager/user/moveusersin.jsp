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
                                <div class="tree-org-moveuserin"></div>
                            </div>
                        </div>
					
					
				</div>
				<div class=" col-md-8">
					<div class="row">
						<div class="col-md-12" >
							<div class="portlet light bordered">        
									 <div class="caption">
                                            <i class="icon-pin font-yellow-crusta"></i>
                                            <span class="caption-subject bold font-yellow-crusta uppercase"> 查询条件 </span>
                                           	 
                                        </div>                       
		                            <div class="portlet-body form">
                                        <form role="form" class="form-horizontal">
                                            <div class="form-body">
	                                            <div class="form-group form-md-line-input">
	                                                    <label class="col-md-2 control-label" for="form_control_1">输入查询条件</label>
	                                                    <div class="col-md-10">
	                                                        <input type="text" class="form-control input-xs" placeholder="请输入账号/工号/姓名/电话号码">	                                                                
	                                                                <div class="form-control-focus"> </div>
	                                                                <span class="help-block">请输入账号/工号/姓名/电话号码</span>	
	                                                    </div>
	                                                    
	                                                    
	                                             </div>
	                                             <div class="form-group form-md-line-input">
                                                    <label class="col-md-2 control-label" for="form_control_1">显示</label>
                                                    <div class="col-md-10">
                                                        <div class="input-group input-group-xs">
                                                            
                                                            <div class="input-group-control">
                                                                <select class="form-control" id="form_control_1">
                                                                		<option value="all">全部</option>
			                                                            <option value="10">10条记录</option>
			                                                            <option value="20">20条记录</option>
			                                                            <option value="30">30条记录</option>
			                                                            <option value="50">50条记录</option>
			                                                            <option value="100">100条记录</option>			                                                            
			                                                        </select>
                                                              
                                                                <div class="form-control-focus"> </div>
                                                            </div>
                                                            <span class="input-group-btn btn-right ">
                                                                	<button class="btn green-haze btn-xs" type="button">查询</button>
                                                            </span>
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
												用户列表
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
                                                <i class="fa fa-plus"></i> 保存调入记录 </a>
                                            <a href="javascript:;" class="btn btn-circle default btn-xs">
                                                <i class="fa fa-plus"></i> 清空重置 </a>
                                            
                                        </div>
                                    </div>                                   
		                            <div class="portlet-body">
		                                
										<div class="row">
											<div class=" col-md-12" >
												用户列表
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
	});
</script>