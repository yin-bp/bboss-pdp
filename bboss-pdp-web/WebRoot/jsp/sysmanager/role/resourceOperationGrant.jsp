<%@ page session="false" language="java"
	contentType="text/html; charset=utf-8"%>
<%@ taglib uri="/WEB-INF/tld/pager-taglib.tld" prefix="pg"%>
<%@ taglib uri="/WEB-INF/tld/admin-taglib.tld" prefix="admin"%>

<div class=" col-md-12">
	<pg:notempty actual="${resourceSource }">
		<div class="row">
			<div class="col-md-12 auto-resourcesauthsource">
				 
					<script type="text/javascript">
							jQuery(document).ready(function() {								
								loadauto_resourcesauthsource();
							});
							function loadauto_resourcesauthsource(){
								$(".auto-resourcesauthsource").load("${resourceSource}",{"roleId":"${roleId}","roleType":"${roleType}","resourceType":"${resourceType}"},function(){
									 Layout.fixContentHeight(); // fix content height
						             App.initAjax(); // initialize core stuff
								});
							}
					</script>
				 
			</div>
		</div>
	</pg:notempty>
<form action="#" class="form-horizontal form-roleauthset" >
	<input type="hidden" name="resourceType"  value="${resourceType }">	
	<input type="hidden" name="resourceName"  value="${resourceName }">	
	<input type="hidden" name="roleId"  value="${roleId }">	
	<input type="hidden" name="roleType"  value="${roleType }">	
	<pg:true actual="${maintaindata }">
		<div class="row">
			<div class="col-md-12">
				<div class="portlet light bordered">

					<div class="portlet-title">
						<div class="caption">
							<i class="icon-pin font-yellow-crusta"></i> <span
								class="caption-subject bold font-yellow-crusta uppercase tooltips" data-original-title="数据资源授权：将资源对应的数据操作权限授予角色用户">
								数据资源授权 </span>

						</div>
						
					</div>
					<div class="portlet-body">
						<table class="table table-striped table-hover table-bordered table-resourcelist">
					      <thead>
					          <tr>
					          	 
					              <th width="10%"> 资源编码 </th>
					              <th width="15%"> 资源名称</th>
					               <th> 资源操作</th>
					          </tr>
					      </thead> 
					      <tbody>
							<pg:list actual="${resources }" var="resource">
					          <tr>
					          	  
					              <td> <pg:cell colName="resCode"/>  </td>
					              
					              <td>
					                  <pg:cell colName="resName" />
					              </td>
					                <td>
					                   <pg:list actual="${resource.permissionOPS }">
						                   <input name="res_opcode" type="checkbox"  <pg:true colName="hasPermission">checked</pg:true>
											value="<pg:cell colName="id"  />::<pg:cell colName="resCode" index="0"/>::<pg:cell colName="resName" index="0"/>"/> 
											<pg:cell colName="name"  />
										</pg:list>
					              </td>
					          </tr>
	      
							</pg:list>
						   
					      </tbody>
					  </table>

					</div>
				</div>
			</div>
		</div>
	</pg:true>
	
	<pg:true actual="${hasGlobalresource }">
		 
		<div class="row">
			<div class="col-md-12">
				<div class="portlet light bordered">
					<div class="portlet-title ">
						<div class="caption">
							<i class="icon-pin font-yellow-crusta"></i> <span
								class="caption-subject bold font-yellow-crusta tooltips" data-original-title="全局操作是专门为资源类型定义的一组与资源数据无关的全局操作">
								全局操作授权 -全局资源编码：${globalResourceid }</span>

						</div>
						 
					</div>
					<div class="portlet-body ">
						<table class="table table-striped table-hover table-bordered table-globalresops">
					      <thead>
					          <tr>
					          		<th width="10%">
										<input type="checkbox" class="checkboxall" onClick="checkAll('.table-globalresops .checkboxall','.table-globalresops .checkone')"/>授权
								</th>
					               
					              <th width="40%"> 编码/名称</th>
					              <th> <span class="tooltips" data-original-title="当url与操作关联，则url的访问权限自动与对应的操作权限一致"> 关联url</span></th>
					          </tr>
					      </thead> 
					      <tbody>
							<pg:list actual="${globalOperationQueue }">
								
	     
					          <tr>
					          		<td width="10%"><input
										name="globalopcode" type="checkbox" 
										<pg:true colName="hasPermission">checked</pg:true>
										class="checkone" onClick="checkOne('.table-globalresops .checkboxall','.table-globalresops .checkone')" value="<pg:cell colName="id" defaultValue="" />" />
								</td>
					              
					              
					              <td width="40%">
					                 <pg:cell colName="id" defaultValue="" /> <pg:cell colName="name" defaultValue="" />
					              </td>
					               <td>
					                  <pg:cell colName="authoresouresList" defaultValue="" />
					              </td>
					          </tr>
	      
							</pg:list>
						   
					      </tbody>
					  </table>
					</div>
				</div>
			</div>
		</div>
	</pg:true>	
	<pg:true actual="${maintaindata || hasGlobalresource}">	
			<div class="form-actions">
				<div class="row">
					<div class="col-md-offset-3 col-md-9">
					<pg:true actual="${roleNeedGrantResource }">
						<admin:haspermission resource="globalrole" opcode="roleresauth" resourceType="role">
						<button type="button" class="btn green btn-saveroleauths">提交</button>
						<button type="reset" class="btn default">重置</button>
						</admin:haspermission>
					</pg:true>	
						<button type="button" class="btn default" data-dismiss="modal">关闭</button>
						 
					</div>
				</div>
			</div>
			<div class="row"><div class="col-md-12" >&nbsp;</div></div>
	</pg:true>		
</form>	
		<div class="row">
			<div class="col-md-12">
				<div class="portlet light bordered">
					<div class="portlet-title ">
						<div class="caption">
							<i class="icon-pin font-yellow-crusta"></i> <span
								class="caption-subject bold font-yellow-crusta uppercase tooltips" data-original-title="数据操作是专门为资源类型中的资源定义的一组操作，这些资源可以是在资源维护中管理的录入的资源，也可以是从其他数据源中获取的资源">
								数据操作定义及关联URL </span>

						</div>
						 
					</div>
					<div class="portlet-body ">
					<table class="table table-striped table-hover table-bordered table-dictitemlist">
					      <thead>
					          <tr>
					              <th> 操作编码 </th>
					              <th> 操作名称</th>
					              <th ><span class="tooltips" data-original-title="当url与操作关联，则url的访问权限自动与对应的操作权限一致"> 关联url</span></th>
					          </tr>
					      </thead> 
					      <tbody>
							<pg:list actual="${operationQueue }">
								
	     
					          <tr>
					              <td> <pg:cell colName="id" defaultValue="" />  </td>
					              
					              <td>
					                  <pg:cell colName="name" defaultValue="" />
					              </td>
					               <td>
					                  <pg:cell colName="authoresouresList" defaultValue="" />
					              </td>
					          </tr>
	      
							</pg:list>
						   
					      </tbody>
					  </table>
					</div>
				</div>
			</div>
		</div>
</div>

<script type="text/javascript">
	jQuery(document).ready(function() {
		<pg:notempty actual="${errorMessage}">
			PDP.showError('.alert-roleauthset','${errorMessage}');
		</pg:notempty> 
		SysRoleAuthset.initRoleAuthAction();
	});
</script>
							