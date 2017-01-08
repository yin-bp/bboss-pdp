<%@ page session="false" language="java"
	contentType="text/html; charset=utf-8"%>
<%@ taglib uri="/WEB-INF/tld/pager-taglib.tld" prefix="pg" %>	
<div class="row profile-account">	
    <div class=" col-md-12">
		<div class="panel panel-primary">
             <div class="panel-heading">
                 <h3 class="panel-title">历史日志按模块维度统计报表</h3>
             </div>
             <div class="panel-body form">
             	<table class="table table-striped table-hover table-bordered table-hislogstatic">
				      <thead>
				          <tr>
				          	 
				              <th width="20%"> 模块 </th>
				              <th width="80%"> 操作统计</th>
				               
				          </tr>
				      </thead> 
				      <tbody>
						<pg:list actual="${hislogstatic }">
				          <tr>
				          	  
				              <td><pg:cell colName="logmodule"/>  </td>
				              <td><pg:cell colName="totalcount"/> 
				              </td>
				          </tr>
	     
						</pg:list>
					   
				      </tbody>
				  </table>
					  
              </div>
          </div>
         
	</div>
</div>
	<script type="text/javascript">
	jQuery(document).ready(function() {
		
	});
</script>	                            
	                              