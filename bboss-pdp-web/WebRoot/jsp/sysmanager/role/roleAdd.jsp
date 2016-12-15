<%@ page language="java" pageEncoding="UTF-8"%>
<%@ include file="/common/jsp/importtaglib.jsp"%>

<!-- 
	描述:添加角色管理界面。
	作者:yinbp
	版权:bboss
	版本:v1.0 
	日期:2016-12-15 17:06:09
-->
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
	<head>
		<title>新增角色管理</title>
		<%@ include file="/common/jsp/css.jsp"%>
	</head>
	<body>
		<div class="form_box">
			<form id="addForm" name="addForm" method="post">
			    				<table border="0" cellpadding="0" cellspacing="0" class="table4">
																								<tr>
												<th width=85px >
							序号：
						</th>
						<td width=140px>
															<input id="roleId" name="roleId" type="text" 								/>														
							
						</td>
																							<th width=85px >
							创建人：
						</th>
						<td width=140px>
															<input id="ownerId" name="ownerId" type="text" 								/>														
							
						</td>
																							<th width=85px >
							备注1：
						</th>
						<td width=140px>
															<input id="remark1" name="remark1" type="text" 								/>														
							
						</td>
																								</tr>						<tr>
												<th width=85px >
							备注2：
						</th>
						<td width=140px>
															<input id="remark2" name="remark2" type="text" 								/>														
							
						</td>
																							<th width=85px >
							备注3：
						</th>
						<td width=140px>
															<input id="remark3" name="remark3" type="text" 								/>														
							
						</td>
																							<th width=85px >
							描述：
						</th>
						<td width=140px>
															<input id="roleDesc" name="roleDesc" type="text" 								/>														
							
						</td>
																								</tr>						<tr>
												<th width=85px >
							名称：
						</th>
						<td width=140px>
															<input id="roleName" name="roleName" type="text" 								/>														
							
						</td>
																							<th width=85px >
							类型：
						</th>
						<td width=140px>
															<input id="roleType" name="roleType" type="text" 								/>														
							
						</td>
																							<th width=85px >
							用途：
						</th>
						<td width=140px>
															<input id="roleUsage" name="roleUsage" type="text" 								/>														
							
						</td>
											</tr>	
															</table>			
			
			<div class="btnarea" >
				<a href="javascript:void(0)" class="bt_1" id="addButton" onclick="dosubmit()"><span><pg:message code="sany.pdp.common.operation.add"/></span></a>
				<a href="javascript:void(0)" class="bt_2" id="resetButton" onclick="doreset()"><span><pg:message code="sany.pdp.common.operation.reset"/></span></a>
				<a href="javascript:void(0)" class="bt_2" id="closeButton" onclick="closeDlg()"><span><pg:message code="sany.pdp.common.operation.exit"/></span></a>
				<input type="reset" id="reset" style="display: none;" />
			</div>
			</form>
		</div>
	</body>
<script language="javascript">
var api = frameElement.api, W = api.opener;


   function dosubmit()
   {
		
		$.ajax({
		   type: "POST",
			url : "addRole.page",
			data :formToJson("#addForm"),
			dataType : 'json',
			async:false,
			beforeSend: function(XMLHttpRequest){
					var validated = $("#addForm").form('validate');
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
					W.$.dialog.alert("新增记录成功",function(){	
							W.modifyQueryData();
							api.close();
					},api);													
				}else{
					W.$.dialog.alert("新增出错",function(){},api);
				}
			}
		  });
   	 }
function doreset(){
	$("#reset").click();
}
</script>