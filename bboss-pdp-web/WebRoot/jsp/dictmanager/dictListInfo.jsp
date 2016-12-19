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

<pg:pager scope="request"  data="dicts" desc="false" isList="false" containerid=".portlet-dictlist">	
	<pg:param name="dictType"/>
	<pg:param name="dictAttr"/>
	
<table	class="table table-bordered table-striped table-condensed table-dictlist" >
	<thead >
		<tr>
			<th width="2%">
					<input type="checkbox" class="checkboxall" onClick="checkAll('.table-dictlist .checkboxall','.table-dictlist .checkone')"/>
			</th>				
			<th width="10%">字典名称</th> 
			<th width="10%">字典编码</th>      		
	       	<th width="10%">字典类型</th>
	       	<th width="10%">字典状态</th>
	       	<th width="10%">创建人</th>	     		
	       		
			<th width="15%">操作</th>
		</tr>
	</thead>
	<tbody>
		<pg:list >
		  	 <tr>
		  	 	<td><input
						name="dictId" type="checkbox" class="checkone" onClick="checkOne('.table-dictlist .checkboxall','.table-dictlist .checkone')" value="<pg:cell colName="dictId"/>" 
						/>
				</td>
				 
	                <td><pg:cell colName="dictName"  /></td>
	                <td><pg:cell colName="dictCode"  /></td>
	                 <td><pg:cell colName="handler"  /></td>
	                 <td><pg:case colName="dictStatus"  >
		                 <pg:equal value="1"><span class="label label-sm label-success">正常</span></pg:equal>
		                 <pg:equal value="0"><span class="label label-sm label-danger">删除</span></pg:equal>
		                 <pg:other><span class="label label-sm label-success">正常</span></pg:other>
	                 </pg:case></td>
	                <td><pg:cell colName="ownerName"  />(<pg:cell colName="ownerAccount"  />)</td>
	                 
	                <td>
	                <button class="btn btn-outline btn-xs green-sharp  uppercase" onclick="javascript:SysDict.viewDict('<pg:cell colName="dictId"/>','<pg:cell colName="dictName"  />','<pg:cell colName="dictCode"  />')">查看</button>
	                <button class="btn btn-outline btn-xs green-sharp  uppercase"  onclick="javascript:SysDict.editDict('<pg:cell colName="dictId"/>','<pg:cell colName="dictName"  />','<pg:cell colName="dictCode"  />')">修改</button>
	                <button class="btn btn-outline btn-xs green-sharp  uppercase"  onclick="javascript:SysDict.mainDictData('<pg:cell colName="dictId"/>','<pg:cell colName="dictName"  />','<pg:cell colName="dictCode"  />')">维护数据</button>
	                </td>
	        </tr>
	    </pg:list>
		
	</tbody>
</table>
<div class="pages"><input type="hidden" value="<pg:querystring/>" /><pg:index tagnumber="10" sizescope="5,10,20,50,100"/></div>
</pg:pager>

