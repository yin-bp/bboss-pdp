<%@ page session="false" language="java"
	contentType="text/html; charset=utf-8"%>
<%@ taglib uri="/WEB-INF/tld/pager-taglib.tld" prefix="pg" %>
<pg:beaninfo actual="${smOrganization }">		

	<!-- BEGIN FORM-->
	<form action="#" class="form-horizontal" id="form_sys_modifyorg">
		<div class="form-body">
		<input type="hidden" class="form-control" name="parentId"  value="<pg:cell colName="parentId"/>">
		<input type="hidden" class="form-control" name="orgSn"   value="<pg:cell colName="orgSn"/>">
		<input type="hidden" class="form-control" name="orgTreeLevel"   value="<pg:cell colName="orgTreeLevel"/>">
		<div class="row">
			
				<div class="col-md-6" >
					<div class="form-group form-md-line-input">
						<label class="col-md-3 control-label" for="form_control_1">部门编码
							<span class="required">*</span>
						</label>
						<div class="col-md-9">
							<p class="form-control-static"> <pg:cell colName="code"/> </p>
						</div>
						
					</div>
				</div>
				<div class="col-md-6" >
					<div class="form-group  form-md-line-input">
						<label class="col-md-3 control-label"">部门名称:				 
						</label>
						 <div class="col-md-9">
							<p class="form-control-static"> <pg:cell colName="orgName"/> </p>
						</div>
						
					</div>
				</div>
			
			
		</div>
		<div class="row">
			<div class="col-md-6" >	
				<div class="form-group form-md-line-input">
					<label class="col-md-3 control-label" for="form_control_1">部门状态</label>
					 <div class="col-md-9">
							<p class="form-control-static"> 
							<pg:case colName="remark3"> 									
								<pg:equal value="1">启用</pg:equal>
                               <pg:equal value="0">禁用</pg:equal>
							</pg:case></p>
						</div>
					
				</div>
			</div>
			<div class="col-md-6" >
				<div class="form-group form-md-line-input">
					<label class="col-md-3 control-label" for="form_control_1">部门主管
						
					</label>
					 <div class="col-md-9">
							<p class="form-control-static"> <pg:cell colName="orgleaderRealName"/> </p>
						</div>
					
				</div>
			</div>
		</div>	
		
	</form>


</pg:beaninfo> 
