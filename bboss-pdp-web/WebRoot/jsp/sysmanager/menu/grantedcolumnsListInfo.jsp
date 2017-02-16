<%@ page session="false" language="java"
	contentType="text/html; charset=utf-8"%>
<%@ taglib uri="/WEB-INF/tld/pager-taglib.tld" prefix="pg"%>
<%@ taglib uri="/WEB-INF/tld/admin-taglib.tld" prefix="admin"%>

 <pg:pager scope="request"  data="grantedcolumns" desc="false" isList="false" containerid=".portlet-grantcolumnslist">	
	<pg:param name="resourceAttr"/>
	<pg:param name="roleId"/>
	<pg:param name="roleType"/>
	<pg:param name="resourceType"/>

<div class="table-scrollable">
<table class="table table-bordered table-striped table-condensed table-grantedcolumns">
     <thead>
         <tr>
        <th >
				<input type="checkbox" class="checkboxall" onClick="checkAll('.table-grantedcolumns .checkboxall','.table-grantedcolumns .checkone')"/>
		</th>              
             <th  > 编码/名称</th>
             <th  >  关联url</th>
         </tr>
     </thead> 
     <tbody>
		<pg:list>
	         <tr>
	         	<td><input
					name="menuid" type="checkbox" 					
					class="checkone" onClick="checkOne('.table-grantedcolumns .checkboxall','.table-grantedcolumns .checkone')" value="<pg:cell colName="resCode" defaultValue="" />" />
				</td>             
	             <td>
	                <pg:cell colName="resCode" defaultValue="" /> <pg:cell colName="resName" defaultValue="" />
	             </td>
	              <td>
	                 <pg:cell colName="urls" defaultValue="" />
	             </td>
	         </tr>
		</pg:list>
   		
     </tbody>
 </table>
 </div>
 <div class="pages"><pg:index tagnumber="10" sizescope="5,10,20,50,100"/></div>
</pg:pager> 

