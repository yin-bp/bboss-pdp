<%@ page session="false" language="java"
	contentType="text/html; charset=utf-8"%>
<%@ taglib uri="/WEB-INF/tld/pager-taglib.tld" prefix="pg" %>

<!-- 
	描述:日志管理管理列表界面。
	作者:yinbp
	版权:bboss
	版本:v1.0 
	日期:2016-12-15 17:06:09
-->

<pg:pager scope="request"  data="logs" desc="false" isList="false" containerid=".portlet-hisloglist">	
	<pg:param name="logOperuser"/>
	<pg:param name="logVisitorial"/>
	<pg:param name="logOpertime_start"/>
	<pg:param name="logOpertime_end"/>
	<pg:param name="operModule"/>
<table	class="table table-bordered table-striped table-condensed table-hisloglist" >
	<thead >
		<tr>
			<th width="4%">
					序号
			</th>				
			<th width="10%">模块</th> 
			<th width="10%">操作人</th>      		
	       	<th width="25%">内容</th>
	       	<th width="15%">操作时间</th>
	       	<th width="15%">归档时间</th>
			<th width="10%">来源</th>
			<th width="6%">类型</th>
		</tr>
	</thead>
	<tbody>
		<pg:list >
		  	 <tr>
		  	  
				 
	               <td width="6%"><pg:rowid increament="1" offset="false"/></td>  
	               <td  width="10%"><pg:cell colName="operModule"  /></td>  
	               <td width="10%"><pg:cell colName="logOperuser"  /></td>
	                
	                <td  width="25%"><pg:cell colName="logContent" htmlEncode="true" /></td>
	                <td width="15%"><pg:cell colName="logOpertime"  dateformat="yyyy-MM-dd HH:mm:ss"  /></td>
	                <td width="15%"><pg:cell colName="logArchtime"  dateformat="yyyy-MM-dd HH:mm:ss"  /></td>
	                
	                <td width="10%"><pg:cell colName="logVisitorial"  /></td>
	                 
	                <td  width="6%">
	                <pg:convert convertData="${optypeMap }" colName="operType"/>
	                </td>
	                </tr>
	    </pg:list>
		
	</tbody>
</table>
<div class="pages"><input type="hidden" value="<pg:querystring/>" /><pg:index tagnumber="10" sizescope="5,10,20,50,100"/></div>
</pg:pager>

