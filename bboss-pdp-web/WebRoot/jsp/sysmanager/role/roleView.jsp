<%@ page language="java" pageEncoding="UTF-8"%>
<%@ include file="/common/jsp/importtaglib.jsp"%>
<!-- 
	描述:查看角色管理界面。
	作者:yinbp
	版权:bboss
	版本:v1.0 
	日期:2016-12-15 17:06:09
-->

<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<title>查看角色管理</title>
<%@ include file="/common/jsp/css.jsp"%>
</head>
<body>
		<div class="mcontent">
			<div id="searchblock">
				<pg:beaninfo requestKey="role">
				<form id="editForm" name="editForm">
				  						<table border="0" cellpadding="0" cellspacing="0"
						class="table4">
																								<tr>
												<th width=85px >
							序号：
						</th>
						<td width=140px>
							<input id="roleId" name="roleId" type="text" value="<pg:cell colName="roleId"    />" readonly/>
						</td>
																							<th width=85px >
							创建人：
						</th>
						<td width=140px>
							<input id="ownerId" name="ownerId" type="text" value="<pg:cell colName="ownerId"    />" readonly/>
						</td>
																							<th width=85px >
							备注1：
						</th>
						<td width=140px>
							<input id="remark1" name="remark1" type="text" value="<pg:cell colName="remark1"    />" readonly/>
						</td>
																								</tr>						<tr>
												<th width=85px >
							备注2：
						</th>
						<td width=140px>
							<input id="remark2" name="remark2" type="text" value="<pg:cell colName="remark2"    />" readonly/>
						</td>
																							<th width=85px >
							备注3：
						</th>
						<td width=140px>
							<input id="remark3" name="remark3" type="text" value="<pg:cell colName="remark3"    />" readonly/>
						</td>
																							<th width=85px >
							描述：
						</th>
						<td width=140px>
							<input id="roleDesc" name="roleDesc" type="text" value="<pg:cell colName="roleDesc"    />" readonly/>
						</td>
																								</tr>						<tr>
												<th width=85px >
							名称：
						</th>
						<td width=140px>
							<input id="roleName" name="roleName" type="text" value="<pg:cell colName="roleName"    />" readonly/>
						</td>
																							<th width=85px >
							类型：
						</th>
						<td width=140px>
							<input id="roleType" name="roleType" type="text" value="<pg:cell colName="roleType"    />" readonly/>
						</td>
																							<th width=85px >
							用途：
						</th>
						<td width=140px>
							<input id="roleUsage" name="roleUsage" type="text" value="<pg:cell colName="roleUsage"    />" readonly/>
						</td>
											</tr>	
																	
					</table>
				
		
				<div class="btnarea" >
					<a href="javascript:void(0)" class="bt_2" id="closeButton" onclick="closeDlg()"><span>退出</span></a>
				</div>	
				</form>
				</pg:beaninfo>
			</div>
			
  
  	</div>					
</body>