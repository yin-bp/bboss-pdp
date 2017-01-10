<%@ page session="false" language="java"
	contentType="text/html; charset=utf-8"%>
<%@ taglib uri="/WEB-INF/tld/pager-taglib.tld" prefix="pg" %>
<%@ taglib uri="/WEB-INF/tld/dictionary.tld" prefix="dict" %>
<pg:pager scope="request"  data="users" desc="false" isList="false" containerid=".portlet_userlists">

	<pg:param name="userIsvalid"/>
	<pg:param name="userAttr"/>
	
<table	class="table table-bordered table-striped table-condensed " style="height:50px;">
	<thead >
		<tr>
			 
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
		  	 	 
				<td> <pg:rowid increament="1" /> </td>
		  	 	
	            <td> <pg:cell colName="userName"/> <pg:cell colName="userRealname"/></td>
	            <td> <pg:cell colName="userWorknumber"/> </td>
	            <td> <pg:cell colName="userMobiletel1"/> </td>
	             <td> <dict:itemname type="sex"  colName="userSex"/> </td>
	            <td> <dict:itemname type="userType"  colName="userType"/> </td>
	            <td> <dict:itemname type="userIsvalid"  colName="userIsvalid"/></td>
	            <td><pg:empty colName="departName" evalbody="true"><pg:yes>待岗用户</pg:yes><pg:no> <pg:cell colName="departName"/></pg:no></pg:empty> </td>
	        </tr>
	    </pg:list>
		
	</tbody>
</table>
<div class="pages"><input type="hidden" value="<pg:querystring/>" /><pg:index tagnumber="10" sizescope="5,10,20,50,100"/></div>
</pg:pager>