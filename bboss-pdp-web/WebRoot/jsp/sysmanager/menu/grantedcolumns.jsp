<%@ page session="false" language="java"
	contentType="text/html; charset=utf-8"%>
<%@ taglib uri="/WEB-INF/tld/pager-taglib.tld" prefix="pg"%>
<%@ taglib uri="/WEB-INF/tld/admin-taglib.tld" prefix="admin"%>

<div class="portlet-title">
	<div class="caption">
		<i class="icon-pin font-yellow-crusta"></i> <span
			class="caption-subject bold font-yellow-crusta uppercase tooltips" data-original-title="菜单资源授权：将菜单操作权限授予角色">
			菜单授权 </span>

	</div>
	<div class="actions">
			<a
				class="btn btn-xs blue"  
				id="button_sys_add_authmenu"> 添加菜单 <i class="fa fa-edit"></i>

			</a>
			 <a class="btn btn-xs red" id="button_sys_delete_authmenu">
				<i class="fa fa-times"></i> 删除选中菜单
			</a> 
		</div>
</div>
<div class="portlet-body ">
	
<table class="table table-striped table-hover table-bordered table-grantedcolumns">
     <thead>
         <tr>
         		<th width="10%">
				<input type="checkbox" class="checkboxall" onClick="checkAll('.table-grantedcolumns .checkboxall','.table-grantedcolumns .checkone')"/>
		</th>
              
             <th width="40%"> 编码/名称</th>
             <th> <span class="tooltips" data-original-title="当url与操作关联，则url的访问权限自动与对应的操作权限一致"> 关联url</span></th>
         </tr>
     </thead> 
     <tbody>
		<pg:list actual="${grantedcolumns }">
	         <tr>
	         	<td width="10%"><input
					name="menuid" type="checkbox" 					
					class="checkone" onClick="checkOne('.table-grantedcolumns .checkboxall','.table-grantedcolumns .checkone')" value="<pg:cell colName="id" defaultValue="" />" />
				</td>             
	             <td width="40%">
	                <pg:cell colName="id" defaultValue="" /> <pg:cell colName="name" defaultValue="" />
	             </td>
	              <td>
	                 <pg:cell colName="authoresouresList" defaultValue="" />
	             </td>
	         </tr>
		</pg:list>
   		 <tr>
	         	<td colspan="100"><input
					id="xxxxxxmenuid" type="text" 					
					/>
				</td>             
	             
	         </tr>
     </tbody>
 </table>
</div>
<script type="text/javascript">
		jQuery(document).ready(function() {								
		
			$("#button_sys_add_authmenu").bind("click",function(){
				ModelDialog.dialog({
 					title:"选择菜单",
 					showfooter:false,
 					url:"${pageContext.request.contextPath}/menu/columnAuthTree.page",
 					params:{"roleId":"${roleId}","roleType":"${roleType}","resourceType":"${resourceType}"},
 					width:"600px",
 					height:"500px"

 	         });
			})
		});
</script>
