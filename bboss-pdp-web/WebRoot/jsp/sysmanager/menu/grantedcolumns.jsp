<%@ page session="false" language="java"
	contentType="text/html; charset=utf-8"%>
<%@ taglib uri="/WEB-INF/tld/pager-taglib.tld" prefix="pg"%>
<%@ taglib uri="/WEB-INF/tld/admin-taglib.tld" prefix="admin"%>

<div class="portlet-title">
	<div class="caption">
		<i class="icon-pin font-yellow-crusta"></i> <span
			class="caption-subject bold font-yellow-crusta uppercase tooltips" data-original-title="菜单资源授权：将菜单操作权限授予角色">
			设置角色菜单权限 </span>

	</div>
	<pg:true actual="${roleNeedGrantResource }">
	<div class="actions">
			<a
				class="btn btn-xs blue"  
				id="button_sys_add_authmenu"> 添加菜单 <i class="fa fa-edit"></i>

			</a>
			 <a class="btn btn-xs red" id="button_sys_delete_authmenu">
				<i class="fa fa-times"></i> 移除选中菜单
			</a> 
		</div>
	</pg:true>	
</div>
<div class="portlet-body">
 
<table class="table table-striped table-bordered table-hover order-column table-grantedcolumns">
     <thead>
         <tr>
         		<th  >
				<input type="checkbox" class="checkboxall" onClick="checkAll('.table-grantedcolumns .checkboxall','.table-grantedcolumns .checkone')"/>
		</th>
              
             <th  > 编码/名称</th>
             <th  > <span class="tooltips" data-original-title="当url与操作关联，则url的访问权限自动与对应的操作权限一致"> 关联url</span></th>
         </tr>
     </thead> 
     <tbody>
		<pg:list actual="${grantedcolumns }">
	         <tr>
	         	<td ><input
					name="menuid" type="checkbox" 					
					class="checkone" onClick="checkOne('.table-grantedcolumns .checkboxall','.table-grantedcolumns .checkone')" value="<pg:cell colName="resCode" defaultValue="" />" />
				</td>             
	             <td >
	                <pg:cell colName="resCode" defaultValue="" /> <pg:cell colName="resName" defaultValue="" />
	             </td>
	              <td >
	                 <pg:cell colName="urls" defaultValue="" />
	             </td>
	         </tr>
		</pg:list>
   		 
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
			});
			
			$("#button_sys_delete_authmenu").bind("click",function(){
				var chk_value =""; 
	            $('.table-grantedcolumns input[name="menuid"]:checked').each(function(){ 
	            	if(chk_value == "")
	            		chk_value = $(this).val(); 
	            	else
	            		chk_value += ","+$(this).val(); 
	            }); 
	            if(chk_value == "")
	            {
	            	PDP.warn("请选择要删除的菜单!");
		           	 return;
	            }
	             PDP.confirm("确定要删除选中的菜单吗?",function(isConfirm){
		           	 	if(isConfirm)
		           	 	{        	 		
		           	 		
			           	 	$.ajax({
			          		   type: "POST",
			          			url : "${pageContext.request.contextPath}/sysmanager/role/deleteRoleAuthResources.page",
			          			data :{"opCode":"visible","resCodes":chk_value,"roleId":"${roleId}","roleType":"${roleType}","resourceType":"${resourceType}"},
			          			dataType : 'json',
			          			async:false,
			          			error : function(xhr, ajaxOptions,
										thrownError) {
									PDP.warn(thrownError) ;
								},
			          			beforeSend: function(XMLHttpRequest){ 					
			          				 	
			          				},
			          			success : function(responseText){
			          				
			          				if(responseText=="success"){
			          					
			          					PDP.success("菜单删除成功!");
			          					loadauto_resourcesauthsource();
			          				}else{
			          					PDP.warn("菜单删除失败:"+responseText);
			          				}
			          			}
			          		  });
		           	 	}
				        	
					});
			});
			
			var initTable4 = function () {
		        var table = $('.table-grantedcolumns');

		        var oTable = table.dataTable({

		            // Internationalisation. For more info refer to http://datatables.net/manual/i18n
		            "language": {
		                "aria": {
		                    "sortAscending": ": activate to sort column ascending",
		                    "sortDescending": ": activate to sort column descending"
		                },
		                "emptyTable": "",
		                "info": " 第_START_ - _END_条 共 _TOTAL_ 条记录",
		                "infoEmpty": "",
		                "infoFiltered": "(filtered1 from _MAX_ total entries)",
		                "lengthMenu": "_MENU_ 条记录",
		                "search": "查询:",
		                "zeroRecords": ""
		            },

		            // Or you can use remote translation file
		            //"language": {
		            //   url: '//cdn.datatables.net/plug-ins/3cfcc339e89/i18n/Portuguese.json'
		            //},

		             

		            // scroller extension: http://datatables.net/extensions/scroller/
		            scrollY:        300,
		            deferRender:    true,
		            scroller:       true,
		            deferRender:    true,
		            scrollX:        true,
		            scrollCollapse: true,      
		            "columns": [
		                        { "width": "5%", 'orderable': false,
		                             "searchable": false },
		                        { "width": "25%", 'orderable': true,
			                             "searchable": true },
			                    { "width": "70%", 'orderable': true,
				                             "searchable": true }
		                      ],
                     "order": [
                               [1, "asc"]
                           ], // set first column as a default sort by asc
		            
		            "lengthMenu": [
		                [10, 15, 20, -1],
		                [10, 15, 20, "All"] // change per page values here
		            ],
		            // set the initial value
		            "pageLength": 10,

		            "dom": "<'row' <'col-md-12'>><'row'<'col-md-6 col-sm-12'l><'col-md-6 col-sm-12'f>r><'table-scrollable't><'row'<'col-md-5 col-sm-12'i><'col-md-7 col-sm-12'p>>", // horizobtal scrollable datatable

		            // Uncomment below line("dom" parameter) to fix the dropdown overflow issue in the datatable cells. The default datatable layout
		            // setup uses scrollable div(table-scrollable) with overflow:auto to enable vertical scroll(see: assets/global/plugins/datatables/plugins/bootstrap/dataTables.bootstrap.js). 
		            // So when dropdowns used the scrollable div should be removed. 
		            //"dom": "<'row' <'col-md-12'T>><'row'<'col-md-6 col-sm-12'l><'col-md-6 col-sm-12'f>r>t<'row'<'col-md-5 col-sm-12'i><'col-md-7 col-sm-12'p>>",
		        });
		        
			}
			initTable4();
		});
</script>
