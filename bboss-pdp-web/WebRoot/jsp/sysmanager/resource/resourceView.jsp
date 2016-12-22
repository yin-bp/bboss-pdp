<%@ page language="java" pageEncoding="UTF-8"%>
<%@ include file="/common/jsp/importtaglib.jsp"%>
<!-- 
	描述:查看资源管理界面。
	作者:yinbp
	版权:bboss
	版本:v1.0 
	日期:2016-12-21 00:46:37
-->

<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<title>查看资源管理</title>
<%@ include file="/common/jsp/css.jsp"%>
</head>
<body>
		<div class="mcontent">
			<div id="searchblock">
				<pg:beaninfo requestKey="resource">
				<form id="editForm" name="editForm">
				  						<table border="0" cellpadding="0" cellspacing="0"
						class="table4">
																								<tr>
												<th width=85px >
							序号：
						</th>
						<td width=140px>
							<input id="resId" name="resId" type="text" value="<pg:cell colName="resId"    />" readonly/>
						</td>
																							<th width=85px >
							：
						</th>
						<td width=140px>
							<input id="attr1" name="attr1" type="text" value="<pg:cell colName="attr1"    />" readonly/>
						</td>
																							<th width=85px >
							：
						</th>
						<td width=140px>
							<input id="attr10" name="attr10" type="text" value="<pg:cell colName="attr10"    />" readonly/>
						</td>
																								</tr>						<tr>
												<th width=85px >
							：
						</th>
						<td width=140px>
							<input id="attr11" name="attr11" type="text" value="<pg:cell colName="attr11"    />" readonly/>
						</td>
																							<th width=85px >
							：
						</th>
						<td width=140px>
							<input id="attr12" name="attr12" type="text" value="<pg:cell colName="attr12"    />" readonly/>
						</td>
																							<th width=85px >
							：
						</th>
						<td width=140px>
							<input id="attr13" name="attr13" type="text" value="<pg:cell colName="attr13"    />" readonly/>
						</td>
																								</tr>						<tr>
												<th width=85px >
							：
						</th>
						<td width=140px>
							<input id="attr14" name="attr14" type="text" value="<pg:cell colName="attr14"    />" readonly/>
						</td>
																							<th width=85px >
							：
						</th>
						<td width=140px>
							<input id="attr15" name="attr15" type="text" value="<pg:cell colName="attr15"    />" readonly/>
						</td>
																							<th width=85px >
							：
						</th>
						<td width=140px>
							<input id="attr16" name="attr16" type="text" value="<pg:cell colName="attr16"    />" readonly/>
						</td>
																								</tr>						<tr>
												<th width=85px >
							：
						</th>
						<td width=140px>
							<input id="attr17" name="attr17" type="text" value="<pg:cell colName="attr17"    />" readonly/>
						</td>
																							<th width=85px >
							：
						</th>
						<td width=140px>
							<input id="attr18" name="attr18" type="text" value="<pg:cell colName="attr18"    />" readonly/>
						</td>
																							<th width=85px >
							：
						</th>
						<td width=140px>
							<input id="attr19" name="attr19" type="text" value="<pg:cell colName="attr19"    />" readonly/>
						</td>
																								</tr>						<tr>
												<th width=85px >
							：
						</th>
						<td width=140px>
							<input id="attr2" name="attr2" type="text" value="<pg:cell colName="attr2"    />" readonly/>
						</td>
																							<th width=85px >
							：
						</th>
						<td width=140px>
							<input id="attr20" name="attr20" type="text" value="<pg:cell colName="attr20"    />" readonly/>
						</td>
																							<th width=85px >
							：
						</th>
						<td width=140px>
							<input id="attr21" name="attr21" type="text" value="<pg:cell colName="attr21"    />" readonly/>
						</td>
																								</tr>						<tr>
												<th width=85px >
							：
						</th>
						<td width=140px>
							<input id="attr22" name="attr22" type="text" value="<pg:cell colName="attr22"    />" readonly/>
						</td>
																							<th width=85px >
							：
						</th>
						<td width=140px>
							<input id="attr23" name="attr23" type="text" value="<pg:cell colName="attr23"    />" readonly/>
						</td>
																							<th width=85px >
							：
						</th>
						<td width=140px>
							<input id="attr24" name="attr24" type="text" value="<pg:cell colName="attr24"    />" readonly/>
						</td>
																								</tr>						<tr>
												<th width=85px >
							：
						</th>
						<td width=140px>
							<input id="attr25" name="attr25" type="text" value="<pg:cell colName="attr25"    />" readonly/>
						</td>
																							<th width=85px >
							：
						</th>
						<td width=140px>
							<input id="attr26" name="attr26" type="text" value="<pg:cell colName="attr26"    />" readonly/>
						</td>
																							<th width=85px >
							描述：
						</th>
						<td width=140px>
							<input id="attr27" name="attr27" type="text" value="<pg:cell colName="attr27"    />" readonly/>
						</td>
																								</tr>						<tr>
												<th width=85px >
							：
						</th>
						<td width=140px>
							<input id="attr3" name="attr3" type="text" value="<pg:cell colName="attr3"    />" readonly/>
						</td>
																							<th width=85px >
							：
						</th>
						<td width=140px>
							<input id="attr4" name="attr4" type="text" value="<pg:cell colName="attr4"    />" readonly/>
						</td>
																							<th width=85px >
							：
						</th>
						<td width=140px>
							<input id="attr5" name="attr5" type="text" value="<pg:cell colName="attr5"    />" readonly/>
						</td>
																								</tr>						<tr>
												<th width=85px >
							：
						</th>
						<td width=140px>
							<input id="attr6" name="attr6" type="text" value="<pg:cell colName="attr6"    />" readonly/>
						</td>
																							<th width=85px >
							：
						</th>
						<td width=140px>
							<input id="attr7" name="attr7" type="text" value="<pg:cell colName="attr7"    />" readonly/>
						</td>
																							<th width=85px >
							：
						</th>
						<td width=140px>
							<input id="attr8" name="attr8" type="text" value="<pg:cell colName="attr8"    />" readonly/>
						</td>
																								</tr>						<tr>
												<th width=85px >
							：
						</th>
						<td width=140px>
							<input id="attr9" name="attr9" type="text" value="<pg:cell colName="attr9"    />" readonly/>
						</td>
																							<th width=85px >
							：
						</th>
						<td width=140px>
							<input id="marker" name="marker" type="text" value="<pg:cell colName="marker"    />" readonly/>
						</td>
																							<th width=85px >
							：
						</th>
						<td width=140px>
							<input id="parentId" name="parentId" type="text" value="<pg:cell colName="parentId"    />" readonly/>
						</td>
																								</tr>						<tr>
												<th width=85px >
							：
						</th>
						<td width=140px>
							<input id="path" name="path" type="text" value="<pg:cell colName="path"    />" readonly/>
						</td>
																							<th width=85px >
							资源编码：
						</th>
						<td width=140px>
							<input id="resCode" name="resCode" type="text" value="<pg:cell colName="resCode"    />" readonly/>
						</td>
																							<th width=85px >
							资源名称：
						</th>
						<td width=140px>
							<input id="resName" name="resName" type="text" value="<pg:cell colName="resName"    />" readonly/>
						</td>
																								</tr>						<tr>
												<th width=85px >
							：
						</th>
						<td width=140px>
							<input id="reserved1" name="reserved1" type="text" value="<pg:cell colName="reserved1"    />" readonly/>
						</td>
																							<th width=85px >
							：
						</th>
						<td width=140px>
							<input id="reserved3" name="reserved3" type="text" value="<pg:cell colName="reserved3"    />" readonly/>
						</td>
																							<th width=85px >
							：
						</th>
						<td width=140px>
							<input id="reserved4" name="reserved4" type="text" value="<pg:cell colName="reserved4"    />" readonly/>
						</td>
																								</tr>						<tr>
												<th width=85px >
							：
						</th>
						<td width=140px>
							<input id="reserved5" name="reserved5" type="text" value="<pg:cell colName="reserved5"    />" readonly/>
						</td>
																							<th width=85px >
							资源类型：
						</th>
						<td width=140px>
							<input id="restypeId" name="restypeId" type="text" value="<pg:cell colName="restypeId"    />" readonly/>
						</td>
																							<th width=85px >
							类型名称：
						</th>
						<td width=140px>
							<input id="restypeName" name="restypeName" type="text" value="<pg:cell colName="restypeName"    />" readonly/>
						</td>
																								</tr>						<tr>
												<th width=85px >
							：
						</th>
						<td width=140px>
							<input id="roleUsage" name="roleUsage" type="text" value="<pg:cell colName="roleUsage"    />" readonly/>
						</td>
																							<th width=85px >
							：
						</th>
						<td width=140px>
							<input id="title" name="title" type="text" value="<pg:cell colName="title"    />" readonly/>
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