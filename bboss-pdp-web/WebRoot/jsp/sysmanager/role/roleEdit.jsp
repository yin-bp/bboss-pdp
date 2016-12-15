<%@ page language="java" pageEncoding="utf-8"%>
<%@ include file="/common/jsp/importtaglib.jsp"%>

<!-- 
	描述:编辑角色管理界面。
	作者:yinbp
	版权:bboss
	版本:v1.0 
	日期:2016-12-15 17:06:09
-->
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
	<head>
		<title>修改角色管理</title>
		<%@ include file="/common/jsp/css.jsp"%>
	</head>
	<%	String path = request.getContextPath();%>
	<body>
		<div class="form_box">
			
			<pg:beaninfo requestKey="role">
				<form id="editForm" name="editForm">
										<table  border="0" cellpadding="0" cellspacing="0"
						class="table4">
																								<tr>
												<th width=85px >
							序号：
						</th>
						<td width=140px>
																					<input id="roleId" name="roleId" type="text" value="<pg:cell colName="roleId"  />"
								/>																				
						</td>
																							<th width=85px >
							创建人：
						</th>
						<td width=140px>
																					<input id="ownerId" name="ownerId" type="text" value="<pg:cell colName="ownerId"  />"
								/>																				
						</td>
																							<th width=85px >
							备注1：
						</th>
						<td width=140px>
																					<input id="remark1" name="remark1" type="text" value="<pg:cell colName="remark1"  />"
								/>																				
						</td>
																								</tr>						<tr>
												<th width=85px >
							备注2：
						</th>
						<td width=140px>
																					<input id="remark2" name="remark2" type="text" value="<pg:cell colName="remark2"  />"
								/>																				
						</td>
																							<th width=85px >
							备注3：
						</th>
						<td width=140px>
																					<input id="remark3" name="remark3" type="text" value="<pg:cell colName="remark3"  />"
								/>																				
						</td>
																							<th width=85px >
							描述：
						</th>
						<td width=140px>
																					<input id="roleDesc" name="roleDesc" type="text" value="<pg:cell colName="roleDesc"  />"
								/>																				
						</td>
																								</tr>						<tr>
												<th width=85px >
							名称：
						</th>
						<td width=140px>
																					<input id="roleName" name="roleName" type="text" value="<pg:cell colName="roleName"  />"
								/>																				
						</td>
																							<th width=85px >
							类型：
						</th>
						<td width=140px>
																					<input id="roleType" name="roleType" type="text" value="<pg:cell colName="roleType"  />"
								/>																				
						</td>
																							<th width=85px >
							用途：
						</th>
						<td width=140px>
																					<input id="roleUsage" name="roleUsage" type="text" value="<pg:cell colName="roleUsage"  />"
								/>																				
						</td>
											</tr>	
																</table>
				
					<div class="btnarea" >
							<a href="javascript:void(0)" class="bt_1" id="editButton"
									onclick="dosubmit()"><span>保存</span> </a>
							<a href="javascript:void(0)" class="bt_2" id="closeButton"
									onclick="closeDlg()"><span>退出</span> </a>
					</div>
			
				</form>
			</pg:beaninfo>
		</div>
	</body>
	<script language="javascript">
	var api = frameElement.api, W = api.opener;
 
   function dosubmit()
   {
   		
		$.ajax({
		   type: "POST",
			url : "updateRole.page",
			data :formToJson("#editForm"),
			dataType : 'json',
			async:false,
			beforeSend: function(XMLHttpRequest){
					var validated = $("#editForm").form('validate');
			      	if (validated){
			      		blockUI();	
			      		XMLHttpRequest.setRequestHeader("RequestType", "ajax");
			      	}
			      	else
			      	{
			      		return false;
			      	}
				 	
				},
			success : function(responseText){
				//去掉遮罩	
				unblockUI();
				if(responseText=="success"){
					W.$.dialog.alert("修改记录成功",function(){	
							W.modifyQueryData();
							api.close();
							
					},api);	
					
				}else{
					alert("修改出错");
				}
			}
		  });
   }
</script>