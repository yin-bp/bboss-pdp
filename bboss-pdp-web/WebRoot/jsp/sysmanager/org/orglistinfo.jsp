<%@ page session="false" language="java"
	contentType="text/html; charset=utf-8"%>
<%@ taglib uri="/WEB-INF/tld/pager-taglib.tld" prefix="pg" %>
<%@ taglib uri="/WEB-INF/tld/dictionary.tld" prefix="dict" %>
<pg:pager scope="request"  data="smOrganizations" desc="false" isList="false" containerid=".portlet_orglists">
	<pg:param name="orgName"/>
	<pg:param name="code"/>
	<pg:param name="remark3"/>
	<pg:param name="departId"/>
	
	
<table	class="table table-bordered table-striped table-condensed table-orglist" style="height:50px;">
	<thead >
		<tr role="row" class="heading">
             <th width="2%">
					<input type="checkbox" class="checkboxall" onClick="checkAll('.table-orglist .checkboxall','.table-orglist .checkone')"/>
			</th>
             <th width="15%"> 部门名称 </th>
             <th width="15%"> 部门编码 </th>
            
             <th width="10%"> 状态 </th>
             <th width="10%"> 部门主管 </th>
             <th width="10%"> 操作 </th>
         </tr>
	</thead>
	<tbody>
		<pg:list >
		  	 <tr>
		  	 	 
				<td>
				
				<input name="orgId" type="checkbox" class="checkone" onClick="checkOne('.table-orglist .checkboxall','.table-orglist .checkone')" value="<pg:cell colName="orgId"/>"/>
				
				</td>
		  	 	
	            <td> <pg:cell colName="orgName"/></td>
	            <td> <pg:cell colName="code"/> </td>
	            <td> 
	            <pg:equal colName="remark3" value="0" evalbody="true">
	            <pg:yes><span class="label label-sm label-danger">禁用</span></pg:yes>
	            <pg:no><span class="label label-sm label-success">有效</span></pg:no>
	            </pg:equal> 
	            </td>
	             <td> <pg:cell colName="orgleaderRealName"/> </td>
	           
	            <td><button orgName="<pg:cell colName="orgName"/>" orgId="<pg:cell colName="orgId"/>" class="btn btn-outline btn-xs green-sharp  uppercase" data-toggle="org_ops_confirmation"  data-singleton="true" data-placement="left">操作</button></td>
	        </tr>
	    </pg:list>
		
	</tbody>
</table>
<div class="pages"><input type="hidden" value="<pg:querystring/>" /><pg:index tagnumber="10" sizescope="5,10,20,50,100"/></div>
</pg:pager>
<script type="text/javascript">
	jQuery(document).ready(function() {
		/**
		设置用户操作按钮
		 */
		var orgButtonMethods = function()
		{
			
			
			var content_ = [
                {
                    class: 'btn btn-xs btn-default',
                    icon: 'fa fa-pencil',
                    label:'查看',
                    onClick: function() {		                    	
                   	 var orgId = $(this).attr("orgId");
                   	 var orgName = $(this).attr("orgName");
                   	 SysOrg.viewOrg(orgId,orgName)
                    }
                  },
                  {
                    class: 'btn  btn-xs btn-default',
                    icon: 'fa fa-pencil',
                    label:'修改',
                    onClick: function() {

                   	 var orgId = $(this).attr("orgId");
                   	 var orgName = $(this).attr("orgName");
                   	 SysOrg.tomodifyOrg(orgId,orgName)
                    }
                  },
                  /** {
                    class: 'btn  btn-xs btn-default',
                    icon: 'fa fa-pencil',
                    label:'授权',
                    onClick: function() {
                   	 var orgId = $(this).attr("orgId");
                   	 SysOrg.toauthOrg(orgId)
                    }
                  }*/
                  ,
                  {
                    class: 'btn  btn-xs btn-default',
                    icon: 'fa fa-pencil',
                    label:'管理员设置' ,
                    onClick: function() {
                   	 var orgId = $(this).attr("orgId");
                   	 var orgName = $(this).attr("orgName");
                   	SysOrg.orgmanagerset(orgId,orgName);
	                 }
                  },
                  /**   {
	                     class: 'btn  btn-xs btn-default',
	                     icon: 'fa fa-pencil',
	                     label:'岗位设置',
	                     onClick: function() {
	                    	 var orgId = $(this).attr("orgId");
	                    	 SysOrg.setOrgJob(userId)
		                 }
	                     
	                   },
                 {
	                     class: 'btn btn-xs btn-danger',
	                     icon: 'fa fa-ban',
	                     label:'停用' ,
	                     onClick: function() {
	                    	 var orgId = $(this).attr("orgId");
	                    	 SysOrg.stopOrg(orgId)
		                 }
	               },*/
                 
                  {
               	   class: 'btn  btn-xs btn-default',
               	      icon: 'glyphicon glyphicon-remove',
               	      cancel: true
	               }
                ];
				return content_;
		}
		PDP.popconfirmation({selector:'[data-toggle=org_ops_confirmation]',buttons:orgButtonMethods()});
		
	});
</script>