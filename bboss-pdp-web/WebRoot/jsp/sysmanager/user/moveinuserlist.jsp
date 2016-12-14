<%@ page session="false" language="java"
	contentType="text/html; charset=utf-8"%>
<%@ taglib uri="/WEB-INF/tld/pager-taglib.tld" prefix="pg" %>

<table	class="table table-bordered table-striped table-condensed table-moveinuserlist scroller" style="height:50px;">
	<thead >
		<tr>
			<th width="2%">
					<input type="checkbox" class="group-checkable"
					/></th>
			<th width="6%">序号</th>
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
		<pg:list actual="${users }">
		  	 <tr>
		  	 	<td><input
						name="userId" type="checkbox" class="checkboxes" value="<pg:cell colName="userId"/>" />
				</td>
				<td> <pg:rowid increament="1" offset="true"/> </td>
		  	 	
	            <td> <pg:cell colName="userName"/> <pg:cell colName="userRealname"/></td>
	            <td> <pg:cell colName="userWorknumber"/> </td>
	            <td> <pg:cell colName="userMobiletel1"/> </td>
	            <td> <pg:cell colName="userSex"/> </td>
	            <td> <pg:cell colName="userType"/> </td>
	            <td> <pg:cell colName="userIsvalid"/> </td>
	            <td> <pg:cell colName="departName"/> </td>
	        </tr>
	    </pg:list>
		
	</tbody>
</table>