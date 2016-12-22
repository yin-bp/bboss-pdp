<%@ page session="false" language="java"
	contentType="text/html; charset=utf-8"%>
<%@ taglib uri="/WEB-INF/tld/pager-taglib.tld" prefix="pg" %>
<pg:pager scope="request"  data="users" desc="false" isList="false" containerid=".select_users_movein">
	<pg:param name="fromDepartId"/>
	<pg:param name="recursive"/>
	<pg:param name="userAttr"/>
	
<table	class="table table-bordered table-striped table-condensed table-moveinuserlist scroller" style="height:50px;">
	<thead >
		<tr>
			<th width="2%">
					<input type="checkbox" class="checkboxall" onClick="checkAll('.table-moveinuserlist .checkboxall','.table-moveinuserlist .checkone')"
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
		<pg:list >
		  	 <tr>
		  	 	<td><input
						name="userId" type="checkbox" class="checkone" onClick="checkOne('.table-moveinuserlist .checkboxall','.table-moveinuserlist .checkone')" value="<pg:cell colName="userId"/>" />
				</td>
				<td> <pg:rowid increament="1" /> </td>
		  	 	
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
<div class="pages"><input type="hidden" value="<pg:querystring/>" /><pg:index tagnumber="10" sizescope="5,10,20,50,100"/></div>
</pg:pager>