<%@ page session="false" language="java"
	contentType="text/html; charset=utf-8"%>
<%@ taglib uri="/WEB-INF/tld/pager-taglib.tld" prefix="pg"%>
<%@ taglib uri="/WEB-INF/tld/admin-taglib.tld" prefix="admin"%>
<link
	href="${pageContext.request.contextPath}/assets/pages/css/profile-2.min.css"
	rel="stylesheet" type="text/css" />


<div class="row">
	<div class="col-md-12">		 
		<div class="alert alert-danger display-hide  alert-roleauthset">
			<button class="close" data-close="alert"></button>
			<span class="msg"> 提示信息区 </span>
		</div>
	</div>
</div>
<div class="profile">
	<div class="tabbable-line tabbable-full-width">
		<ul class="nav nav-tabs">

			<li class="active"><a href="#tab_roleauth" data-toggle="tab">
					授权 </a></li>
			<li><a href="#tab_roleuserset" data-toggle="tab"> 授予用户 </a></li>
		</ul>
		<div class="tab-content">
			<!--tab_1_2-->
			<div class="tab-pane  active" id="tab_roleauth">
				<div class="row profile-account">
					<div class="col-md-3">
						<ul class="ver-inline-menu tabbable margin-bottom-10 resourcetypelist">
						<pg:list actual="${resourceTypes }">
							<li <pg:equal expression="{rowid }" value="0">class="active"</pg:equal>>
								<a data-toggle="tab" href="#resource_tab_1_1" resourceType="<pg:cell colName="id"/>" resourceName="<pg:cell colName="name"/>">
										<i class="fa fa-cog"></i> <pg:cell colName="name"/> <pg:cell colName="id"/>
								</a> <span class="after"> </span>
							</li>
						</pg:list>	
							 
						</ul>
					</div>
					<div class="col-md-9">
						<div class="tab-content">
							<div id="resource_operations" class="tab-pane active">
							<p>请选择一个资源进行操作</p>
							</div>
							 
							 
						</div>
					</div>
					<!--end col-md-9-->
				</div>
			</div>
			
			<div class="tab-pane " id="tab_roleuserset">
				<div class="row profile-account">
					<div class="col-md-12">
						
						<div class="row">
						<form role="form" class="form-horizontal form-queryroleusers">
							<input name="roleName" value="${roleName }" type="hidden"/>
							<div class="form-body">
								<div class="col-md-8">
									<div class="form-group form-md-line-input">									
										<div class="col-md-12">
											<div class="input-icon right">
												<input type="text" class="form-control  input-xs"
													placeholder="" name="userAttr" >
												<div class="form-control-focus"></div>
												<span class="help-block">输入账号/中文名称/工号/电话号码</span>
											</div>
										</div>
									</div>
								</div>
								<div class="col-md-4">
									<div class="form-group form-md-line-input">
										<div class="col-md-12">
											<div class="input-group">
	
												<span class="input-group-btn btn-right">
	
													<button type="button"
														class="btn btn-xs green-haze btn-query "
														aria-expanded="false">查询</button>
													<button type="reset" class="btn btn-xs default reset"
														aria-expanded="false">重置</button>	
												</span>
											</div>
										</div>
									</div>
								</div>
							</div>
						</form>	
						</div>
						<div class="row">&nbsp;</div>
						<div class="row">
							<div class="col-md-12">
								<div class="portlet light bordered">
									<div class="portlet-title tabbable-line">
										<div class="caption">
											<i class="icon-pin font-yellow-crusta"></i> <span
												class="caption-subject bold font-yellow-crusta uppercase">
												已授予用户列表 </span>
				
										</div>
										<div class="actions">
				
											<a href="javascript:;"
												class="btn btn-circle blue btn-xs btn-addroleusers"> <i
												class="fa fa-plus"></i> 添加用户
											</a>
											<a href="javascript:;"
												class="btn btn-circle red btn-xs btn-removeroleusers"> <i
												class="fa fa-times"></i> 移除用户
											</a>
										</div>
									</div>
									<div class="portlet-body portlet_roleusers">
										
									</div>
								</div>
							</div>
						</div>
		
					</div>
					
				</div>
			</div>
			<!--end tab-pane-->
		</div>
	</div>
</div>
<script
	src="${pageContext.request.contextPath}/assets/sysmanager/sysroleauthset.js" type="text/javascript"></script>
	 
<script type="text/javascript">
	jQuery(document).ready(function() {
		SysRoleAuthset.initRoleAuthset('${pageContext.request.contextPath}','${roleId}','${roleType}','${resourceType}','${resourceName}');
		var queryRoleUsers = function(doquery,modeldialog){
			if(!modeldialog )
				modeldialog = ModelDialog.getCurrentModal();
			$(".portlet_roleusers",modeldialog).load("${pageContext.request.contextPath}/sysmanager/role/queryRoleUsers.page",
					doquery?$('.form-queryroleusers',modeldialog).serialize():{roleName:"${roleName}"},
						function(){
						
						});
		}
		queryRoleUsers();

		$(".btn-query",ModelDialog.getCurrentModal()).bind("click",function(){
			queryRoleUsers(true);
		});
		$(".btn-addroleusers",ModelDialog.getCurrentModal()).bind('click',function(){
	  		ModelDialog.dialog({
	  							title:"选择待授予的用户",
	  							iframe:false,
	  							url:"${pageContext.request.contextPath}/jsp/sysmanager/common/choosemultiusers.jsp",
	  							width:"1260px",
	  							height:"500px",
	  							okCallBack:function(modal){
	  								 
	  								var selectUsers = SysChoosemultiUsers.getSelectUsers();
	  								
	  								if(selectUsers == null || selectUsers == "")
	  								{
	  									PDP.warn("没有选择用户！");
	  	  								return false;
	  								}
	  								else
	  								{
	  									//PlatformCommonUtils.success("部门主管:"+selectUsers);
	  									//userId+":"+userRealname+":"+userName+":"+userWorknumber+":"+userMobiletel1
	  									var users = selectUsers.split(",");
	  									var userIds="";
	  									for(i =0 ; i < users.length; i++){
	  										
	  										var user = users[i];
	  										var _user = user.split(":");
	  										if(userIds == ""){
	  											userIds = _user[0] ;
	  										}
	  										else
  											{
	  											userIds = userIds +","+_user[0] ;
  											}
	  									}
	  									
	  									 $.ajax({
	  							 		   type: "POST",
	  							 			url : '${pageContext.request.contextPath}/sysmanager/user/saveRoleUsers.page',
	  							 			data :{"userIds":userIds,"roleId":'${roleName }'},
	  							 			dataType : 'json',
	  							 			async:false,
	  							 			beforeSend: function(XMLHttpRequest){ 					
	  							 				 	
	  							 			},
	  							 			success : function(responseText){
	  							 				
	  							 				if(responseText=="success"){	  							 					
	  							 					PDP.success("为角色设置用户成功!");
	  							 					queryRoleUsers(false,ModelDialog.getParentModal());
	  							 				}else{
	  							 					PDP.warn("为角色设置用户失败:"+responseText);
	  							 				}
	  							 			}
	  							 		  });	
	  									return true;
	  								}
	  								
	  							},
	  							closeCallBack:undefined,
	  							urlLoadCallBack:undefined
	  			
	  			});
	   	 });
		
		
		$(".btn-removeroleusers",ModelDialog.getCurrentModal()).bind("click",function(){
			var chk_value =""; 
            $('.table-roleusers input[name="userId"]:checked').each(function(){ 
            	if(chk_value == "")
            		chk_value = $(this).val(); 
            	else
            		chk_value += ","+$(this).val(); 
            }); 
            if(chk_value == "")
            {
            	PDP.warn("请选择要移除的用户!");
	           	 return;
            }
             PDP.confirm("确定要移除选中的用户吗?",function(isConfirm){
	           	 	if(isConfirm)
	           	 	{        	 		
	           	 		
		           	 	$.ajax({
		          		   type: "POST",
		          			url : "${pageContext.request.contextPath}/sysmanager/role/deleteRoleUsers.page",
		          			data :{"users":chk_value,"roleName":"${roleName}"},
		          			dataType : 'json',
		          			async:false,
		          			error : function(xhr, ajaxOptions,
									thrownError) {
								PDP.warn(thrownError) ;
							},
		          			beforeSend: function(XMLHttpRequest){ 					
		          				 	
		          				},
		          			success : function(responseText){
		          				
		          				if(responseText=="success"){
		          					
		          					PDP.success("用户移除成功!");
		          					loadauto_resourcesauthsource();
		          				}else{
		          					PDP.warn("用户移除失败:"+responseText);
		          				}
		          			}
		          		  });
	           	 	}
			        	
				});
		});
		
		
		
		
	});
	
</script>

<!-- END CONTENT BODY -->