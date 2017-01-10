<%@ page session="false" language="java"
	contentType="text/html; charset=utf-8"%>
<div class="row profile-account">	
    <div class=" col-md-12">
		<div class="panel panel-primary">
             <div class="panel-heading">
                 <h3 class="panel-title">日志模块-日志模块启用后，系统才会记录该模块的日志，否则忽略对应模块日志的记录</h3>
             </div>
             <div class="panel-body form">
             <form action="#" class="form-horizontal form-logsetting" >
             	<div class="form-body"><table class="table table-striped table-hover table-bordered table-logmodules">
					      <thead>
					          <tr>
					          	 
					              <th width="20%"> 模块 </th>
					              <th width="80%"> 设置（选中代表启用）</th>
					               
					          </tr>
					      </thead> 
					      <tbody>
							<pg:list actual="${logModules }">
					          <tr>
					          	  
					              <td> <input name="id" type="hidden" value="<pg:cell colName="id"/>" /> <pg:cell colName="logmodule"/>  </td>
					              <td><input name="status_<pg:cell colName="id"/>" type="checkbox"  <pg:equal colName="status" value="0">checked</pg:equal>
											value="0"/> 
					              </td>
					          </tr>
	      
							</pg:list>
						   
					      </tbody>
					  </table></div>
					  <div class="form-actions">
                          <button type="button" class="btn green-haze btn-sm btn-logsetting">保存</button>
                          
                      </div>
				</form>	  
              </div>
         
	</div>
</div>
	<script type="text/javascript">
	jQuery(document).ready(function() {
		$(".btn-logsetting").bind("click",function(){
			$('.form-logsetting')
    		.ajaxSubmit(
    				{
    					type : 'POST',
    					url : '${pageContext.request.contextPath}/logmanager/logsetting.page',
    					forceSync : false,
    					dataType : 'json',
    					beforeSubmit : function() {
    						 App.startPageLoading({message: '保存中...'});

    				           
    					},
    					error : function(xhr, ajaxOptions,
    							thrownError) {
    						PDP.warn(thrownError) ;
    					},

    					success : function(responseText,
    							statusText, xhr, $form) {
    						 
    						 window.setTimeout(function() {
    				                App.stopPageLoading();
    				            }, 2000);
    						var msg = responseText;
    						var title = '日志设置';
    						var tiptype = "success";
    						if (msg == 'success') {
    							msg = "日志设置保存完毕"
    							PDP.success(msg,function(){
    								 
    							}) ;
    						} else {
    							 
    							PDP.warn(msg) ;
    						}
    						
    						

    					}

    				});
		});
	});
</script>	                            
	                              