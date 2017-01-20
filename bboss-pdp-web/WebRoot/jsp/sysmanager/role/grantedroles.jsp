<%@ page session="false" language="java"
	contentType="text/html; charset=utf-8"%>
<%@ taglib uri="/WEB-INF/tld/pager-taglib.tld" prefix="pg"%>
<%@ taglib uri="/WEB-INF/tld/admin-taglib.tld" prefix="admin"%>
<div class="portlet light bordered">
<div class="portlet-title">
	<div class="caption">
		<i class="icon-pin font-yellow-crusta"></i> <span
			class="caption-subject bold font-yellow-crusta uppercase tooltips" data-original-title="角色资源授权：将角色授予权限授予角色，以便实现分级授权">
			设置角色授予权限 </span>

	</div>
	<pg:true actual="${roleNeedGrantResource }">
	<div class="actions">
			<admin:haspermission resource="globalrole" opcode="roleresauth" resourceType="role">
			<a
				class="btn btn-xs blue"  
				id="button_sys_add_authrole"> 添加角色 <i class="fa fa-edit"></i>

			</a>
			 <a class="btn btn-xs red" id="button_sys_delete_authrole">
				<i class="fa fa-times"></i> 移除选中角色
			</a> 
			</admin:haspermission>
		</div>
	</pg:true>	
</div>
<div class="portlet-body">
 
<table class="table table-striped table-bordered table-hover order-column table-grantedroles">
     <thead>
         <tr>
        	<th  >
				<input type="checkbox" class="checkboxall" onClick="checkAll('.table-grantedroles .checkboxall','.table-grantedroles .checkone')"/>
			</th>
              
              
             <th > 角色</th>
         </tr>
     </thead> 
     <tbody>
		<pg:list actual="${grantedroles }">
	         <tr>
	         	<td  ><input
					name="roleId" type="checkbox" 					
					class="checkone" onClick="checkOne('.table-grantedroles .checkboxall','.table-grantedroles .checkone')" value="<pg:cell colName="resCode"  />" />
				</td>             
	           
	              <td>
	                  <pg:cell colName="resName"   />
	             </td>
	         </tr>
		</pg:list>
   		 
     </tbody>
 </table>
 
</div>
</div>
<script type="text/javascript">
		jQuery(document).ready(function() {								
		
			$("#button_sys_add_authrole").bind("click",function(){
				ModelDialog.dialog({
 					title:"选择待授予角色",
 					showfooter:false,
 					url:"${pageContext.request.contextPath}/sysmanager/role/rolesetAuthList.page",
 					params:{"roleId":"${roleId}","roleType":"${roleType}","resourceType":"${resourceType}"},
 					width:"600px",
 					height:"500px"

 	         });
			});
			
			$("#button_sys_delete_authrole").bind("click",function(){
				var chk_value =""; 
	            $('.table-grantedroles input[name="roleId"]:checked').each(function(){ 
	            	if(chk_value == "")
	            		chk_value = $(this).val(); 
	            	else
	            		chk_value += ","+$(this).val(); 
	            }); 
	            if(chk_value == "")
	            {
	            	PDP.warn("请选择要删除的角色!");
		           	 return;
	            }
	             PDP.confirm("确定要删除选中的角色吗?",function(isConfirm){
		           	 	if(isConfirm)
		           	 	{        	 		
		           	 		
			           	 	$.ajax({
			          		   type: "POST",
			          			url : "${pageContext.request.contextPath}/sysmanager/role/deleteRoleAuthResources.page",
			          			data :{"opCode":"roleset","resCodes":chk_value,"roleId":"${roleId}","roleType":"${roleType}","resourceType":"${resourceType}"},
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
			          					
			          					PDP.success("角色删除成功!");
			          					loadauto_resourcesauthsource();
			          				}else{
			          					PDP.warn("角色删除失败:"+responseText);
			          				}
			          			}
			          		  });
		           	 	}
				        	
					});
			});
			
			var initTable4 = function () {
		        var table = $('.table-grantedroles');

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
		            scrollY:        400,
		            deferRender:    true,
		            scroller:       true,
		            deferRender:    true,
		            scrollX:        true,
		            scrollCollapse: true,      
		            "columns": [
		                        { "width": "5%", 'orderable': false,
		                             "searchable": false },
		                        { "width": "25%", 'orderable': true,
			                             "searchable": true }
			                  
		                      ],
                     "order": [
                               [1, "asc"]
                           ],
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
			<pg:notempty actual="${grantedroles}">
			initTable4();
			</pg:notempty>
		});
</script>
