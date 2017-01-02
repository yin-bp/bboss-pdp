<%@ page session="false" language="java"
	contentType="text/html; charset=utf-8"%>
<%@ taglib uri="/WEB-INF/tld/pager-taglib.tld" prefix="pg"%>
<%@ taglib uri="/WEB-INF/tld/admin-taglib.tld" prefix="admin"%>
 

<div class="row">
	<div class="col-md-12">		 
		<div class="alert alert-danger display-hide  alert-userauth">
			<button class="close" data-close="alert"></button>
			<span class="msg"> 提示信息区 </span>
		</div>
	</div>
</div>

<div class="row">
	<div class="col-md-12">
		<div class="portlet light bordered">
			<div class="portlet-title tabbable-line">
				<div class="caption">
					<i class="icon-pin font-yellow-crusta"></i> <span
						class="caption-subject bold font-yellow-crusta uppercase">
						管理员列表 </span>

				</div>
				<div class="actions">

					<a href="javascript:;"
						class="btn btn-circle blue btn-xs btn-selectorgmanager"> <i
						class="fa fa-plus"></i> 选择管理员
					</a>
					<a href="javascript:;"
						class="btn btn-circle red btn-xs btn-removeorgmanager"> <i
						class="fa fa-times"></i> 移除管理员
					</a>

				</div>
			</div>
			<div class="portlet-body portlet-orgmanagers">
				
			</div>
		</div>
	</div>
</div>

<script type="text/javascript">
	jQuery(document).ready(function() {
		var loadorgmanagerlist = function(modal){
			$(".portlet-orgmanagers",modal?modal:ModelDialog.getCurrentModal()).load("${pageContext.request.contextPath}/sysmanager/org/orgmanagerlist.page",{
				orgId:'${orgId}'
			});
		}
		loadorgmanagerlist();
		$(".btn-selectorgmanager",ModelDialog.getCurrentModal()).bind("click",function(){
			ModelDialog.dialog({
					title:"选择待部门管理",
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
					 			url : '${pageContext.request.contextPath}/sysmanager/org/saveorgmanagers.page',
					 			data :{"userIds":userIds,"orgId":'${orgId }'},
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
					 					PDP.success("设置管理员成功!");
					 					loadorgmanagerlist(ModelDialog.getParentModal());//因为方法在选择用户窗口执行，所以需要设置其父窗口的来刷新管理员列表
					 				}else{
					 					PDP.warn("设置管理员失败:"+responseText);
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
		
		
		$(".btn-removeorgmanager",ModelDialog.getCurrentModal()).bind("click",function(){
			var chk_value =""; 
            $('.table-orgmanagerlist input[name="userId"]:checked').each(function(){ 
            	if(chk_value == "")
            		chk_value = $(this).val(); 
            	else
            		chk_value += ","+$(this).val(); 
            }); 
            if(chk_value == "")
            {
            	PDP.warn("请选择要移除的管理员!");
	           	 return;
            }
             PDP.confirm("确定要移除选中的管理员吗?",function(isConfirm){
	           	 	if(isConfirm)
	           	 	{        	 		
	           	 		
		           	 	$.ajax({
		          		   type: "POST",
		          			url : "${pageContext.request.contextPath}/sysmanager/org/removeorgmanager.page",
		          			data :{"userIds":chk_value,"orgId":'${orgId }'},
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
		          					
		          					PDP.success("管理员移除成功!");
		          					loadorgmanagerlist();
		          				}else{
		          					PDP.warn("管理员移除失败:"+responseText);
		          				}
		          			}
		          		  });
	           	 	}
			        	
				});
		});
		
	});
	
</script>

