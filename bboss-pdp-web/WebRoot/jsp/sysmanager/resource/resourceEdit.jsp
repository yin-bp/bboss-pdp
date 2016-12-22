<%@ page language="java" pageEncoding="utf-8"%>
<%@ include file="/common/jsp/importtaglib.jsp"%>

<!-- 
	描述:编辑资源管理界面。
	作者:yinbp
	版权:bboss
	版本:v1.0 
	日期:2016-12-21 00:46:37
-->
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
	<head>
		<title>修改资源管理</title>
		<%@ include file="/common/jsp/css.jsp"%>
	</head>
	<%	String path = request.getContextPath();%>
	<body>
		<div class="form_box">
			
			<pg:beaninfo requestKey="resource">
				<form id="editForm" name="editForm">
										<table  border="0" cellpadding="0" cellspacing="0"
						class="table4">
																								<tr>
												<th width=85px >
							序号：
						</th>
						<td width=140px>
																					<input id="resId" name="resId" type="text" value="<pg:cell colName="resId"  />"
								/>																				
						</td>
																							<th width=85px >
							：
						</th>
						<td width=140px>
																					<input id="attr1" name="attr1" type="text" value="<pg:cell colName="attr1"  />"
								/>																				
						</td>
																							<th width=85px >
							：
						</th>
						<td width=140px>
																					<input id="attr10" name="attr10" type="text" value="<pg:cell colName="attr10"  />"
								/>																				
						</td>
																								</tr>						<tr>
												<th width=85px >
							：
						</th>
						<td width=140px>
																					<input id="attr11" name="attr11" type="text" value="<pg:cell colName="attr11"  />"
								/>																				
						</td>
																							<th width=85px >
							：
						</th>
						<td width=140px>
																					<input id="attr12" name="attr12" type="text" value="<pg:cell colName="attr12"  />"
								/>																				
						</td>
																							<th width=85px >
							：
						</th>
						<td width=140px>
																					<input id="attr13" name="attr13" type="text" value="<pg:cell colName="attr13"  />"
								/>																				
						</td>
																								</tr>						<tr>
												<th width=85px >
							：
						</th>
						<td width=140px>
																					<input id="attr14" name="attr14" type="text" value="<pg:cell colName="attr14"  />"
								/>																				
						</td>
																							<th width=85px >
							：
						</th>
						<td width=140px>
																					<input id="attr15" name="attr15" type="text" value="<pg:cell colName="attr15"  />"
								/>																				
						</td>
																							<th width=85px >
							：
						</th>
						<td width=140px>
																					<input id="attr16" name="attr16" type="text" value="<pg:cell colName="attr16"  />"
								/>																				
						</td>
																								</tr>						<tr>
												<th width=85px >
							：
						</th>
						<td width=140px>
																					<input id="attr17" name="attr17" type="text" value="<pg:cell colName="attr17"  />"
								/>																				
						</td>
																							<th width=85px >
							：
						</th>
						<td width=140px>
																					<input id="attr18" name="attr18" type="text" value="<pg:cell colName="attr18"  />"
								/>																				
						</td>
																							<th width=85px >
							：
						</th>
						<td width=140px>
																					<input id="attr19" name="attr19" type="text" value="<pg:cell colName="attr19"  />"
								/>																				
						</td>
																								</tr>						<tr>
												<th width=85px >
							：
						</th>
						<td width=140px>
																					<input id="attr2" name="attr2" type="text" value="<pg:cell colName="attr2"  />"
								/>																				
						</td>
																							<th width=85px >
							：
						</th>
						<td width=140px>
																					<input id="attr20" name="attr20" type="text" value="<pg:cell colName="attr20"  />"
								/>																				
						</td>
																							<th width=85px >
							：
						</th>
						<td width=140px>
																					<input id="attr21" name="attr21" type="text" value="<pg:cell colName="attr21"  />"
								/>																				
						</td>
																								</tr>						<tr>
												<th width=85px >
							：
						</th>
						<td width=140px>
																					<input id="attr22" name="attr22" type="text" value="<pg:cell colName="attr22"  />"
								/>																				
						</td>
																							<th width=85px >
							：
						</th>
						<td width=140px>
																					<input id="attr23" name="attr23" type="text" value="<pg:cell colName="attr23"  />"
								/>																				
						</td>
																							<th width=85px >
							：
						</th>
						<td width=140px>
																					<input id="attr24" name="attr24" type="text" value="<pg:cell colName="attr24"  />"
								/>																				
						</td>
																								</tr>						<tr>
												<th width=85px >
							：
						</th>
						<td width=140px>
																					<input id="attr25" name="attr25" type="text" value="<pg:cell colName="attr25"  />"
								/>																				
						</td>
																							<th width=85px >
							：
						</th>
						<td width=140px>
																					<input id="attr26" name="attr26" type="text" value="<pg:cell colName="attr26"  />"
								/>																				
						</td>
																							<th width=85px >
							描述：
						</th>
						<td width=140px>
																					<input id="attr27" name="attr27" type="text" value="<pg:cell colName="attr27"  />"
								/>																				
						</td>
																								</tr>						<tr>
												<th width=85px >
							：
						</th>
						<td width=140px>
																					<input id="attr3" name="attr3" type="text" value="<pg:cell colName="attr3"  />"
								/>																				
						</td>
																							<th width=85px >
							：
						</th>
						<td width=140px>
																					<input id="attr4" name="attr4" type="text" value="<pg:cell colName="attr4"  />"
								/>																				
						</td>
																							<th width=85px >
							：
						</th>
						<td width=140px>
																					<input id="attr5" name="attr5" type="text" value="<pg:cell colName="attr5"  />"
								/>																				
						</td>
																								</tr>						<tr>
												<th width=85px >
							：
						</th>
						<td width=140px>
																					<input id="attr6" name="attr6" type="text" value="<pg:cell colName="attr6"  />"
								/>																				
						</td>
																							<th width=85px >
							：
						</th>
						<td width=140px>
																					<input id="attr7" name="attr7" type="text" value="<pg:cell colName="attr7"  />"
								/>																				
						</td>
																							<th width=85px >
							：
						</th>
						<td width=140px>
																					<input id="attr8" name="attr8" type="text" value="<pg:cell colName="attr8"  />"
								/>																				
						</td>
																								</tr>						<tr>
												<th width=85px >
							：
						</th>
						<td width=140px>
																					<input id="attr9" name="attr9" type="text" value="<pg:cell colName="attr9"  />"
								/>																				
						</td>
																							<th width=85px >
							：
						</th>
						<td width=140px>
																					<input id="marker" name="marker" type="text" value="<pg:cell colName="marker"  />"
								/>																				
						</td>
																							<th width=85px >
							：
						</th>
						<td width=140px>
																					<input id="parentId" name="parentId" type="text" value="<pg:cell colName="parentId"  />"
								/>																				
						</td>
																								</tr>						<tr>
												<th width=85px >
							：
						</th>
						<td width=140px>
																					<input id="path" name="path" type="text" value="<pg:cell colName="path"  />"
								/>																				
						</td>
																							<th width=85px >
							资源编码：
						</th>
						<td width=140px>
																					<input id="resCode" name="resCode" type="text" value="<pg:cell colName="resCode"  />"
								/>																				
						</td>
																							<th width=85px >
							资源名称：
						</th>
						<td width=140px>
																					<input id="resName" name="resName" type="text" value="<pg:cell colName="resName"  />"
								/>																				
						</td>
																								</tr>						<tr>
												<th width=85px >
							：
						</th>
						<td width=140px>
																					<input id="reserved1" name="reserved1" type="text" value="<pg:cell colName="reserved1"  />"
								/>																				
						</td>
																							<th width=85px >
							：
						</th>
						<td width=140px>
																					<input id="reserved3" name="reserved3" type="text" value="<pg:cell colName="reserved3"  />"
								/>																				
						</td>
																							<th width=85px >
							：
						</th>
						<td width=140px>
																					<input id="reserved4" name="reserved4" type="text" value="<pg:cell colName="reserved4"  />"
								/>																				
						</td>
																								</tr>						<tr>
												<th width=85px >
							：
						</th>
						<td width=140px>
																					<input id="reserved5" name="reserved5" type="text" value="<pg:cell colName="reserved5"  />"
								/>																				
						</td>
																							<th width=85px >
							资源类型：
						</th>
						<td width=140px>
																					<input id="restypeId" name="restypeId" type="text" value="<pg:cell colName="restypeId"  />"
								/>																				
						</td>
																							<th width=85px >
							类型名称：
						</th>
						<td width=140px>
																					<input id="restypeName" name="restypeName" type="text" value="<pg:cell colName="restypeName"  />"
								/>																				
						</td>
																								</tr>						<tr>
												<th width=85px >
							：
						</th>
						<td width=140px>
																					<input id="roleUsage" name="roleUsage" type="text" value="<pg:cell colName="roleUsage"  />"
								/>																				
						</td>
																							<th width=85px >
							：
						</th>
						<td width=140px>
																					<input id="title" name="title" type="text" value="<pg:cell colName="title"  />"
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
			url : "updateResource.page",
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