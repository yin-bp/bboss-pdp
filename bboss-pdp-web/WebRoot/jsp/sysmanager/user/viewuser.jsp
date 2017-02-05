<%@ page session="false" language="java"
	contentType="text/html; charset=utf-8"%>
<%@ taglib uri="/WEB-INF/tld/pager-taglib.tld" prefix="pg" %>
<pg:beaninfo actual="${smUser }">	

	<!-- BEGIN FORM-->
	<form action="#" class="form-horizontal" >
		<div class="form-body">
	
			<div class="row">
				<div class="col-md-6" >
					<div class="form-group ">
						<label class="col-md-3 control-label"">账号:				 
						</label>
						 
						<div class="col-md-9">
							<p class="form-control-static"> <pg:cell colName="userName"/> </p>
						</div>
					</div>
				</div>
				<div class="col-md-6" >
					<div class="form-group">
						<label class="col-md-3 control-label" >中文名:</label>
						<div class="col-md-9"><p class="form-control-static"> <pg:cell colName="userRealname"/> </p>						
						</div>
					</div>
				</div>
			</div>
			
			<div class="row">
				<div class="col-md-6" >
					<div class="form-group">
						<label class="col-md-3 control-label" >口令有效期(天):</label>
						<div class="col-md-9"><p class="form-control-static"> <pg:cell colName="passwordDualtime"/> </p>						
						</div>
					</div>
					
				</div>
				<div class="col-md-6" >
					<div class="form-group">
						<label class="col-md-3 control-label" >身份证:</label>
						<div class="col-md-9"><p class="form-control-static"> <pg:cell colName="userIdcard"/> </p>						
						</div>
					</div>
					
				</div>
			</div>
			<div class="row">
				<div class="col-md-6" >		
					<div class="form-group">
						<label class="col-md-3 control-label" >手机号码:</label>
						<div class="col-md-9"><p class="form-control-static"> <pg:cell colName="userMobiletel1"/> </p>						
						</div>
					</div>
					
				</div>
				<div class="col-md-6" >	
					<div class="form-group">
						<label class="col-md-3 control-label" >用户状态:</label>
						<div class="col-md-9"><p class="form-control-static"> 
						<pg:case colName="userIsvalid"> 
							<pg:equal value="2">开通</pg:equal>
							<pg:equal value="1">申请</pg:equal>
							<pg:equal value="3">停用</pg:equal>
							<pg:equal value="0">删除</pg:equal>
							<pg:other>未知</pg:other>
						</pg:case></p>						
						</div>
					</div>	
					
				</div>
			</div>
			<div class="row">
				<div class="col-md-6" >			
					<div class="form-group">
						<label class="col-md-3 control-label" >邮箱:</label>
						<div class="col-md-9"><p class="form-control-static"> <pg:cell colName="userEmail"/> </p>						
						</div>
					</div>				
					
				</div>
				<div class="col-md-6" >			
					<div class="form-group">
						<label class="col-md-3 control-label" >住址:</label>
						<div class="col-md-9"><p class="form-control-static"> <pg:cell colName="userAddress"/> </p>						
						</div>
					</div>				
					
				</div>
				
			</div>	
			<div class="row">
				<div class="col-md-6" >			
					<div class="form-group">
						<label class="col-md-3 control-label" >工号:</label>
						<div class="col-md-9"><p class="form-control-static"> <pg:cell colName="userWorknumber"/> </p>						
						</div>
					</div>				
					
				</div>
				<div class="col-md-6" >			
					<div class="form-group">
						<label class="col-md-3 control-label" >性别:</label>
						<div class="col-md-9"><p class="form-control-static"> <pg:case colName="userSex"> 
							<pg:equal value="M">男</pg:equal>
							<pg:equal value="F">女</pg:equal>
							<pg:equal value="-1">未知</pg:equal>
							 
							<pg:other>未知</pg:other>
						</pg:case></p>						
						</div>
					</div>				
					
				</div>
				
			</div>	
			<div class="row">
				<div class="col-md-6" >			
					<div class="form-group">
						<label class="col-md-3 control-label" >用户类型:</label>
						<div class="col-md-9"><p class="form-control-static"> <pg:case colName="userType"> 
							<pg:equal value="0">系统用户</pg:equal>
							<pg:equal value="1">域用户</pg:equal>
							<pg:equal value="2">第三方用户</pg:equal>
							 
							<pg:other>未知</pg:other>
						</pg:case></p>						
						</div>
					</div>				
					
				</div>
				
				<div class="col-md-6" >			
					<div class="form-group">
						<label class="col-md-3 control-label" >出生日期:</label>
						<div class="col-md-9"><p class="form-control-static"> <pg:cell colName="userBirthday" dateformat="yyyy-MM-dd"/> </p>						
						</div>
					</div>				
					
				</div>
			
				
			</div>
			<div class="row">
				<div class="col-md-6" >		
					<div class="form-group">
						<label class="col-md-3 control-label" >岗位</label>
						<div class="col-md-9"><p class="form-control-static"> <pg:cell colName="userJob"/> </p>						
						</div>
					</div>
				</div>
				<div class="col-md-6" >			
					<div class="form-group">
						<label class="col-md-3 control-label" >备注:</label>
						<div class="col-md-9"><p class="form-control-static"> <pg:cell colName="remark1" htmlEncode="true"/> </p>						
						</div>
					</div>				
					
				</div>
				
			</div>
		</div>
		<div class="form-actions">
			<div class="row">
				<div class="col-md-offset-3 col-md-9">					
					<button type="button" class="btn green" data-dismiss="modal">关闭</button>
				</div>
			</div>
		</div>
		
	</form>

</pg:beaninfo> 

