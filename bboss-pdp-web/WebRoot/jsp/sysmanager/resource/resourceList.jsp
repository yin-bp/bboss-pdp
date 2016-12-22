<%@ page language="java"  pageEncoding="utf-8"%>
<%@ include file="/common/jsp/importtaglib.jsp"%>

<!-- 
	描述:资源管理管理列表界面。
	作者:yinbp
	版权:bboss
	版本:v1.0 
	日期:2016-12-21 00:46:37
-->

<div id="customContent">
<pg:empty actual="${resources}" evalbody="true">
    <pg:yes>
	<div class="nodata">
	<img src="${pageContext.request.contextPath}/html/images/no_data.jpg" />
	</div>
	</pg:yes>
	<pg:no>
		
		<!-- 加入 class="tableOutline" 可控制表格宽度，滚动条展示 -->
		<div id="changeColor" >
		 <table width="100%" border="0" cellpadding="0" cellspacing="0" class="stable" id="tb">
	       <tr>
	            <th align=center><input id="CKA" name="CKA" type="checkbox" 
								onClick="checkAll('CKA','CK')">
																</th>
				<th>序号</th>							
	       		<th>序号</th>       		
	       		<th></th>       		
	       		<th></th>       		
	       		<th></th>       		
	       		<th></th>       		
	       		<th></th>       		
	       		<th></th>       		
	       		<th></th>       		
	       		<th></th>       		
	       		<th></th>       		
	       		<th></th>       		
	       		<th></th>       		
	       		<th></th>       		
	       		<th></th>       		
	       		<th></th>       		
	       		<th></th>       		
	       		<th></th>       		
	       		<th></th>       		
	       		<th></th>       		
	       		<th></th>       		
	       		<th>描述</th>       		
	       		<th></th>       		
	       		<th></th>       		
	       		<th></th>       		
	       		<th></th>       		
	       		<th></th>       		
	       		<th></th>       		
	       		<th></th>       		
	       		<th></th>       		
	       		<th></th>       		
	       		<th></th>       		
	       		<th>资源编码</th>       		
	       		<th>资源名称</th>       		
	       		<th></th>       		
	       		<th></th>       		
	       		<th></th>       		
	       		<th></th>       		
	       		<th>资源类型</th>       		
	       		<th>类型名称</th>       		
	       		<th></th>       		
	       		<th></th>       		
				<th>操作</th>
	       	</tr>	
	      <pg:list requestKey="resources">
	
	   		<tr onDblClick="viewResource('<pg:cell colName="resId"   />')">
	   		        <td class="td_center">
	                    <input id="CK" type="checkbox" name="CK" onClick="checkOne('CKA','CK')" value="<pg:cell colName="resId"   />"/>
	                    <input id="resId" type="hidden" name="resId" value="<pg:cell colName="resId"   />"/></td>
	                <td><pg:rowid increament="1" offset="false"/></td>    
	                <td><pg:cell colName="resId"  /></td>
	                <td><pg:cell colName="attr1"  /></td>
	                <td><pg:cell colName="attr10"  /></td>
	                <td><pg:cell colName="attr11"  /></td>
	                <td><pg:cell colName="attr12"  /></td>
	                <td><pg:cell colName="attr13"  /></td>
	                <td><pg:cell colName="attr14"  /></td>
	                <td><pg:cell colName="attr15"  /></td>
	                <td><pg:cell colName="attr16"  /></td>
	                <td><pg:cell colName="attr17"  /></td>
	                <td><pg:cell colName="attr18"  /></td>
	                <td><pg:cell colName="attr19"  /></td>
	                <td><pg:cell colName="attr2"  /></td>
	                <td><pg:cell colName="attr20"  /></td>
	                <td><pg:cell colName="attr21"  /></td>
	                <td><pg:cell colName="attr22"  /></td>
	                <td><pg:cell colName="attr23"  /></td>
	                <td><pg:cell colName="attr24"  /></td>
	                <td><pg:cell colName="attr25"  /></td>
	                <td><pg:cell colName="attr26"  /></td>
	                <td><pg:cell colName="attr27"  /></td>
	                <td><pg:cell colName="attr3"  /></td>
	                <td><pg:cell colName="attr4"  /></td>
	                <td><pg:cell colName="attr5"  /></td>
	                <td><pg:cell colName="attr6"  /></td>
	                <td><pg:cell colName="attr7"  /></td>
	                <td><pg:cell colName="attr8"  /></td>
	                <td><pg:cell colName="attr9"  /></td>
	                <td><pg:cell colName="marker"  /></td>
	                <td><pg:cell colName="parentId"  /></td>
	                <td><pg:cell colName="path"  /></td>
	                <td><pg:cell colName="resCode"  /></td>
	                <td><pg:cell colName="resName"  /></td>
	                <td><pg:cell colName="reserved1"  /></td>
	                <td><pg:cell colName="reserved3"  /></td>
	                <td><pg:cell colName="reserved4"  /></td>
	                <td><pg:cell colName="reserved5"  /></td>
	                <td><pg:cell colName="restypeId"  /></td>
	                <td><pg:cell colName="restypeName"  /></td>
	                <td><pg:cell colName="roleUsage"  /></td>
	                <td><pg:cell colName="title"  /></td>
	        		
	                <td class="td_center"><a href="javascript:void(0)" id="readResource" onclick="viewResource('<pg:cell colName="resId"  />')">查看</a><a href="javascript:void(0)" id="editResource" onclick="editResource('<pg:cell colName="resId"  />')">编辑</a><a href="javascript:void(0)" id="delResource" onclick="delResource('<pg:cell colName="resId"  />')">删除</a></td>    
	                 
	        </tr>
		 </pg:list>
	    </table>
	    </div>
		
	</pg:no>
</pg:empty> 
</div>		
