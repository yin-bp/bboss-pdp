<%@ page session="false" language="java"
	contentType="text/html; charset=utf-8"%>
<%@ taglib uri="/WEB-INF/tld/pager-taglib.tld" prefix="pg" %>
<%@ taglib uri="/WEB-INF/tld/dictionary.tld" prefix="dict" %>
<pg:pager scope="request"  data="smUsers" desc="false" isList="false" containerid=".portlet_userlists">

	
	<pg:param name="userAttr"/>
	<pg:param name="departId"/>
	<pg:param name="chooseuser"/>
	<pg:param name="userIsvalid"/>
	<pg:param name="userSex"/>
	<pg:param name="recursive"/>
	<pg:param name="userType"/>
	
	
<table	class="table table-bordered table-striped table-condensed table-userlist" style="height:50px;">
	<thead >
		<tr>
			 <th width="2%">
					<input type="checkbox" class="checkboxall" onClick="checkAll('.table-userlist .checkboxall','.table-userlist .checkone')"/>
			</th>
			 
			<th width="20%">账号名称</th>
			 
			<th width="10%">工号</th>
			<th width="10%">电话</th>
			<th width="10%">性别</th>
			<th width="10%">类别</th>
			<th width="10%">状态</th>
			<th width="15%">组织</th>
			<th width="15%">操作</th>
		</tr>
	</thead>
	<tbody>
		<pg:list >
		  	 <tr>
		  	 	 
				<td>
				<pg:false colName="defaultAdmin">
				<input
						name="userId" type="checkbox" class="checkone" onClick="checkOne('.table-userlist .checkboxall','.table-userlist .checkone')" value="<pg:cell colName="userId"/>" 
						<pg:equal colName="roleType" value="1">disabled</pg:equal>
						/>
				</pg:false>		
				</td>
		  	 	
	            <td> <pg:cell colName="userName"/> <pg:cell colName="userRealname"/></td>
	            <td> <pg:cell colName="userWorknumber"/> </td>
	            <td> <pg:cell colName="userMobiletel1"/> </td>
	             <td> <dict:itemname type="sex"  colName="userSex"/> </td>
	            <td> <dict:itemname type="userType"  colName="userType"/> </td>
	            <td> <dict:itemname type="userIsvalid"  colName="userIsvalid"/></td>
	            <td><pg:empty colName="departName" evalbody="true"><pg:yes>待岗用户</pg:yes><pg:no> <pg:cell colName="departName"/></pg:no></pg:empty> </td>
	            <td><button userName="<pg:cell colName="userRealname"/>" userAccount="<pg:cell colName="userName"/>" defaultAdmin="<pg:cell colName="defaultAdmin"/>" 
	            	userId="<pg:cell colName="userId"/>" 
	            	class="btn btn-outline btn-xs green-sharp  uppercase" data-toggle="user_ops_confirmation"  data-singleton="true" data-placement="left">操作</button></td>
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
		var userButtonMethods = function()
		{
			
			
			 var content_ = [
			                 {
			                     class: 'btn btn-xs btn-default',
			                     icon: 'fa fa-pencil',
			                     label:'查看',
			                     onClick: function() {
			                    	 var userName = $(this).attr("userName");
			                    	 var userAccount = $(this).attr("userAccount");
			                    	 var userId = $(this).attr("userId");
			                    	 SysUser.viewUser(userId,userName+"("+userAccount+")")
			                     }
			                   },
			                   {
			                     class: 'btn  btn-xs btn-default',
			                     icon: 'fa fa-pencil',
			                     label:'修改',
			                     onClick: function() {
			                    	 var userName = $(this).attr("userName");
			                    	 var userAccount = $(this).attr("userAccount");
			                    	 var userId = $(this).attr("userId");
			                    	 SysUser.tomodifyUser(userId,userName+"("+userAccount+")");
			                     }
			                   },
			                  
			                   {
			                     class: 'btn  btn-xs btn-default',
			                     icon: 'fa fa-pencil',
			                     label:'修改密码',
			                     onClick: function() {
			                    	 
			                    	 var userId = $(this).attr("userId");
			                    	 var userName = $(this).attr("userName");
			                    	 var userAccount = $(this).attr("userAccount");
			                    	 SysUser.tomodifyPassword(userId,userName,userAccount)
			                     }
			                   },
			                   {
			                     class: 'btn  btn-xs btn-default',
			                     icon: 'fa fa-pencil',
			                     label:'授权',
			                     onClick: function() {
			                    	 var userId = $(this).attr("userId");
			                    	 var defaultAdmin = $(this).attr("defaultAdmin");
			                    	 var userName = $(this).attr("userName");
			                    	 var userAccount = $(this).attr("userAccount");
			                    	 if(defaultAdmin == 'false')
			                    	 {
			                    		 SysUser.toauthUser(userId,userName,userAccount)
			                    	 }
			                    	 else
		                    		 {
			                    		 PlatformCommonUtils.warn("管理员无需授权!");
		                    		 }
			                     }
			                   },		                   
			                  
			                  
			                   {
			                	   class: 'btn  btn-xs btn-default',
			                	      icon: 'glyphicon glyphicon-remove',
			                	      cancel: true
				                   }
			                 ];
			return content_;
		}
		PDP.popconfirmation({selector:'[data-toggle=user_ops_confirmation]',buttons:userButtonMethods()});
		
	});
</script>