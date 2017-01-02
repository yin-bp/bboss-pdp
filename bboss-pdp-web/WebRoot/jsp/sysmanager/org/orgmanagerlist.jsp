<%@ page session="false" language="java"
	contentType="text/html; charset=utf-8"%>
<%@ taglib uri="/WEB-INF/tld/pager-taglib.tld" prefix="pg" %>
<%@ taglib uri="/WEB-INF/tld/dictionary.tld" prefix="dict" %>

<!-- 
	描述:部门管理员列表。
	作者:yinbp
	版权:bboss
	版本:v1.0 
	日期:2016-12-15 17:06:09
-->

 
	
<table	class="table table-bordered table-striped table-condensed table-orgmanagerlist" >
	<thead >
		<tr>
			<th width="2%">
				<input type="checkbox" class="checkboxall" onClick="checkAll('.table-orgmanagerlist .checkboxall','.table-orgmanagerlist .checkone')"/>
			</th>				
			<th width="20%">账号名称</th>
			 
			<th width="10%">工号</th>
			<th width="10%">电话</th>
			<th width="10%">性别</th>
			<th width="10%">类别</th>
			<th width="10%">状态</th>
			<th width="15%">组织</th>    	
		</tr>
	</thead>
	<tbody>
		<pg:list actual="${orgmanagers }">
		  	 <tr>
		  	 	<td><input
						name="userId" type="checkbox" class="checkone" onClick="checkOne('.table-orgmanagerlist .checkboxall','.table-orgmanagerlist .checkone')" value="<pg:cell colName="userId"/>" 
					
						/>
				</td>
				 
	                <td> <pg:cell colName="userName"/> <pg:cell colName="userRealname"/></td>
	            <td> <pg:cell colName="userWorknumber"/> </td>
	            <td> <pg:cell colName="userMobiletel1"/> </td>
	            <td> <dict:itemname type="sex"  colName="userSex"/> </td>
	            <td> <dict:itemname type="userType"  colName="userType"/> </td>
	            <td> <dict:itemname type="userIsvalid"  colName="userIsvalid"/></td>
	            <td> <pg:cell colName="departName"/> </td>
	        </tr>
	    </pg:list>
		
	</tbody>
</table>
 

