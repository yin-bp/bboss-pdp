<%@ page session="false" language="java"
	contentType="text/html; charset=utf-8"%>
<%@ taglib uri="/WEB-INF/tld/pager-taglib.tld" prefix="pg"%>
<div class=" col-md-12">
	<pg:true actual="${maintaindata }">
		<div class="row">
			<div class="col-md-12">
				<div class="portlet light bordered">

					<div class="portlet-title">
						<div class="caption">
							<i class="icon-pin font-yellow-crusta"></i> <span
								class="caption-subject bold font-yellow-crusta uppercase tooltips" data-original-title="资源维护可以录入和删除资源，这些资源会有一组数据操作权限与其关联">
								资源维护 </span>

						</div>
						<div class="actions roleactions">
							<a class="btn btn-xs blue btn-addresource"  > 新增 <i class="fa fa-edit"></i></a>
						
							 <a class="btn btn-xs red btn-batchdelresource" >
								<i class="fa fa-times"></i> 删除
							</a> 
						</div>
					</div>
					<div class="portlet-body">
						<table class="table table-striped table-hover table-bordered table-resourcelist">
					      <thead>
					          <tr>
					          	  <th width="2%">
										<input type="checkbox" class="checkboxall" onClick="checkAll('.table-resourcelist .checkboxall','.table-resourcelist .checkone')"/>
								</th>
					              <th> 资源编码 </th>
					              <th> 资源名称</th>
					               
					          </tr>
					      </thead> 
					      <tbody>
							<pg:list actual="${resources }">
					          <tr>
					          	  <td width="2%"><input
										name="resId" type="checkbox" class="checkone" onClick="checkOne('.table-resourcelist .checkboxall','.table-resourcelist .checkone')" value="<pg:cell colName="resId"/>" />
								</td>
					              <td> <pg:cell colName="resCode" defaultValue="" />  </td>
					              
					              <td>
					                  <pg:cell colName="resName" defaultValue="" />
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
								class="caption-subject bold font-yellow-crusta uppercase tooltips" data-original-title="全局操作是专门为资源类型定义的一组与资源数据无关的全局操作">
								全局操作列表 -全局资源编码：${globalResourceid }</span>

						</div>
						 
					</div>
					<div class="portlet-body ">
						<table class="table table-striped table-hover table-bordered table-dictitemlist">
					      <thead>
					          <tr>
					              <th width="10%"> 操作编码 </th>
					              <th width="10%"> 操作名称</th>
					              <th width="60%"> <span class="tooltips" data-original-title="当url与操作关联，则url的访问权限自动与对应的操作权限一致"> 关联url</span></th>
					              <th width="20%"> 说明</th>
					          </tr>
					      </thead> 
					      <tbody>
							<pg:list actual="${globalOperationQueue }">
								
	     
					          <tr>
					              <td width="10%"> <pg:cell colName="id"  />  </td>
					              
					              <td width="10%">
					                  <pg:cell colName="name"  />
					              </td>
					              
					               <td width="60%">
					                  <pg:cell colName="authoresouresList"  />
					              </td>
					               <td width="20%">
					                  <pg:cell colName="description"   />
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
		<div class="row">
			<div class="col-md-12">
				<div class="portlet light bordered">
					<div class="portlet-title ">
						<div class="caption">
							<i class="icon-pin font-yellow-crusta"></i> <span
								class="caption-subject bold font-yellow-crusta uppercase tooltips" data-original-title="数据操作是专门为资源类型中的资源定义的一组操作，这些资源可以是在资源维护中管理的录入的资源，也可以是从其他数据源中获取的资源">
								数据操作列表 </span>

						</div>
						 
					</div>
					<div class="portlet-body ">
					<table class="table table-striped table-hover table-bordered table-dictitemlist">
					      <thead>
					          <tr>
					               <th width="10%"> 操作编码 </th>
					              <th width="10%"> 操作名称</th>
					              <th width="60%"> <span class="tooltips" data-original-title="当url与操作关联，则url的访问权限自动与对应的操作权限一致"> 关联url</span></th>
					              <th width="20%"> 说明</th>
					          </tr>
					      </thead> 
					      <tbody>
							<pg:list actual="${operationQueue }">
								
	     
					          <tr>
					              <td width="10%"> <pg:cell colName="id"  />  </td>
					              
					              <td width="10%">
					                  <pg:cell colName="name"  />
					              </td>
					              
					               <td width="60%">
					                  <pg:cell colName="authoresouresList"  />
					              </td>
					               <td width="20%">
					                  <pg:cell colName="description"   />
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
			PDP.showError('.alert-resourcemanager','${errorMessage}');
		</pg:notempty> 
		SysResource.initResourceMaintainAction();
	});
</script>
							