<%@ page session="false" language="java"
	contentType="text/html; charset=utf-8"%>
<%@ taglib uri="/WEB-INF/tld/pager-taglib.tld" prefix="pg" %>
<pg:pager scope="request"  data="roles" desc="false" isList="false" containerid=".select_roles">
	 
	<pg:param name="role'Attr"/>
	
<table	class="table table-bordered table-striped table-condensed table-unselectrole" style="height:50px;">
	<thead >
		<tr>
			<th width="2%">
					<input type="checkbox" class="checkboxall" onClick="checkAll('.table-unselectrole .checkboxall','.table-unselectrole .checkone')"
					/></th>
			
			<th width="10%">名称</th>
			 
			<th width="10%">中文名</th>
			
		</tr>
	</thead>
	<tbody>
		<pg:list >
		  	 <tr>
		  	 	<td>
		  	 	
		  	 	<input
						name="roleId" type="checkbox" class="checkone" onClick="checkOne('.table-unselectrole .checkboxall','.table-unselectrole .checkone')" value="<pg:cell colName="roleId"/>" />
				</td>			
		  	 	
	            <td> <pg:cell colName="roleName"/> </td>
	            <td> <pg:cell colName="remark1"/> </td>
	            
	        </tr>
	    </pg:list>
		
	</tbody>
</table>
<div class="pages"><input type="hidden" value="<pg:querystring/>" /><pg:index tagnumber="10" sizescope="5,10,20,50,100"/></div>
</pg:pager>