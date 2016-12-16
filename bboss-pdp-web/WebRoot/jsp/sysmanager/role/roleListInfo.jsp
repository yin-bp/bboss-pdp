<%@ page session="false" language="java"
	contentType="text/html; charset=utf-8"%>
<%@ taglib uri="/WEB-INF/tld/pager-taglib.tld" prefix="pg" %>

<!-- 
	描述:角色管理管理列表界面。
	作者:yinbp
	版权:bboss
	版本:v1.0 
	日期:2016-12-15 17:06:09
-->

<pg:pager scope="request"  data="roles" desc="false" isList="false" containerid=".portlet-rolelist">	
	<pg:param name="roleType"/>
	<pg:param name="roleAttr"/>
	
<table	class="table table-bordered table-striped table-condensed table-rolelist" >
	<thead >
		<tr>
			<th width="2%">
					<input type="checkbox" class="checkboxall" onClick="checkAll('.table-rolelist .checkboxall','.table-rolelist .checkone')"/>
			</th>				
			<th width="10%">名称</th> 
			<th width="10%">中文名</th>      		
	       	<th width="10%">类型</th>
	       	<th width="10%">创建人</th>
			<th width="30%">描述</th>      		
	       		
			<th width="5%">操作</th>
		</tr>
	</thead>
	<tbody>
		<pg:list >
		  	 <tr>
		  	 	<td><input
						name="roleId" type="checkbox" class="checkone" onClick="checkOne('.table-rolelist .checkboxall','.table-rolelist .checkone')" value="<pg:cell colName="roleId"/>" 
						<pg:equal colName="roleType" value="1">disabled</pg:equal>
						/>
				</td>
				 
	                <td><pg:cell colName="roleName"  /></td>
	                <td><pg:cell colName="remark1"  /></td>
	                 <td><pg:cell colName="typeName"  /></td>
	                <td><pg:cell colName="ownerName"  />(<pg:cell colName="ownerAccount"  />)</td>
	                 <td><pg:cell colName="roleDesc"  /></td>
	                <td><button roleName="<pg:cell colName="roleName"  />" userAccount="<pg:cell colName="roleName"  />"  roleId="<pg:cell colName="roleId"  />" class="btn btn-outline btn-xs green-sharp  uppercase" data-toggle="role_ops_confirmation"  data-singleton="true" data-placement="left">操作</button></td>
	        </tr>
	    </pg:list>
		
	</tbody>
</table>
<div class="pages"><input type="hidden" value="<pg:querystring/>" /><pg:index tagnumber="10" sizescope="5,10,20,50,100"/></div>
</pg:pager>

