<%@ page session="false" language="java"
	contentType="text/html; charset=utf-8"%>
<%@ taglib uri="/WEB-INF/tld/pager-taglib.tld" prefix="pg"%>
<%@ taglib uri="/WEB-INF/tld/admin-taglib.tld" prefix="admin"%>
<%@ taglib uri="/WEB-INF/tld/dictionary.tld" prefix="dict"%>


<pg:pager scope="request"  data="roleusers" desc="false" isList="false" containerid=".portlet_roleusers">	
	<pg:param name="roleName"/>
	<pg:param name="userAttr"/>
 
<table class="table table-striped table-bordered table-hover order-column table-roleusers">
     <thead>
          
          <tr >  
	           	<th width="5%" >
					<input type="checkbox" class="checkboxall" onClick="checkAll('.table-roleusers .checkboxall','.table-roleusers .checkone')"/>
				</th>                 
               <th width="10%"> 账号 </th>
               <th width="10%"> 名称 </th>
               <th width="10%"> 工号 </th>
               <th width="10%"> 电话 </th>                         
               <th width="10%"> 类型 </th>                         
               <th width="15%"> 部门</th> 
                <th width="15%"> 状态</th>                 
           </tr>
     </thead> 
     <tbody>
		<pg:list >
	         <tr>
	         	<td width="5%" ><input
					name="userId" type="checkbox" 					
					class="checkone" onClick="checkOne('.table-roleusers .checkboxall','.table-roleusers .checkone')" value="<pg:cell colName="userId"  />" />
				</td>             
	             <td  >
	                <pg:cell colName="userName"   />
	             </td>
	              <td  >
	                  <pg:cell colName="userRealname"   />
	             </td>
	             <td  >
	                <pg:cell colName="userWorknumber"   />
	             </td>
	              <td  >
	                  <pg:cell colName="userMobiletel1"   />
	             </td>
	             <td  >
	                <dict:itemname type="userType" colName="userType"   />
	             </td>
	              <td  >
	                  <pg:cell colName="departName" defaultValue="待岗"  />
	             </td>
	              <td  >
	              	<dict:itemname type="userIsvalid" colName="userIsvalid"   />
	                  
	             </td>
	             
	         </tr>
		</pg:list>
   		 
     </tbody>
 </table>
 <div class="pages"><pg:index tagnumber="10" sizescope="5,10,20,50,100"/></div>
 
 </pg:pager>
 
</div>
<script type="text/javascript">
		jQuery(document).ready(function() {								
		
			
			
		});
</script>
