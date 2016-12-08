<%@ page session="false" language="java"
	contentType="text/html; charset=utf-8"%>
<div class="portlet light portlet-fit portlet-datatable bordered">
	<div class="portlet-title">
		<div class="caption">
			<i class="icon-settings font-dark"></i> <span
				class="caption-subject font-dark sbold uppercase">机构列表</span>
		</div>
		<div class="actions">

			

			<!-- <a
				href="${pageContext.request.contextPath}/sysmanager/user/toAddSmUser.page"
				class="btn btn-sm red" data-target="#ajax-user-add"
				data-toggle="modal" data-backdrop="static" id="button_sys_add_user"> 新增 <i class="fa fa-edit"></i>

			</a> -->
			<a
				class="btn btn-sm red"  
				data-toggle="modal" data-backdrop="static" id="button_sys_add_org"> 新增 <i class="fa fa-edit"></i>

			</a>
			 <a class="btn btn-sm blue" id="button_sys_delete_org">
				<i class="fa fa-times"></i> 删除
			</a>
			<a href="javascript:;" class="btn btn-sm purple"> <i
				class="fa fa-file-o"></i> 机构排序
			</a>
			<a href="javascript:;" onclick="javascript:SysOrg.buildTreeLevel();" class="btn btn-sm purple"> <i
				class="fa fa-file-o"></i> 更新机构层级关系
			</a>
		</div>

	</div>

    <div class="portlet-body">
    <!-- bootstrap native modal
		 <div class="modal fade draggable-modal  modal-scroll " id="ajax-user-add" role="basic" aria-hidden="true">
		       <div class="modal-dialog  modal-lg" >
		           <div class="modal-content">
		               <div class="modal-body">
		                   <img src="${pageContext.request.contextPath}/assets/global/img/loading-spinner-grey.gif" alt="" class="loading">
		                   <span> &nbsp;&nbsp;加载中... </span>
		               </div>
		           </div>
		       </div>
		</div> -->
		 <!-- bootstrap extend modal -->
		<div id="ajax-org-action-extend" class="modal container  fade draggable-modal  modal-scroll " tabindex="-1"> </div>
        <div class="table-container">
            
            <table class="table table-striped table-bordered table-hover table-checkable  table-header-fixed" id="datatable_orglist">
                <thead>
                    <tr role="row" class="heading">
                        <th width="2%">
                            <label class="mt-checkbox mt-checkbox-single mt-checkbox-outline">
                                <input type="checkbox" class="group-checkable" data-set="#datatable_orglist .checkboxes" />
                                <span></span>
                            </label>
                        </th>
                        <th width="15%"> 部门名称 </th>
                        <th width="15%"> 部门编码 </th>
                       
                        <th width="10%"> 状态 </th>
                        <th width="10%"> 部门主管 </th>
                        <th width="10%"> 操作 </th>
                    </tr>
                    <tr role="row" class="filter">
                        <td> <button class="btn btn-xs red btn-outline filter-cancel">
                                <i class="fa fa-times"></i> 重置</button> </td>
                        <td>
                            <input type="text" class="form-control form-filter input-xs" name="orgName"> </td>
                         <td>
                            <input type="text" class="form-control form-filter input-xs" name="code"> </td>
                         <td>
                              <select name="remark3" class="form-control form-filter input-xs">
                                <option value=""></option>
                                
                                <option value="1">启用</option>
                               
                                <option value="0">禁用</option>
                            </select> 
                         </td>
                        <td></td>
                            
                        <td>
                             
                                <button class="btn btn-xs green btn-outline filter-submit margin-bottom">
                                    <i class="fa fa-search"></i> 查询</button>
                             
                           
                        </td>
                    </tr>
                </thead>
                <tbody> </tbody>
            </table>
        </div>
    </div>
</div>
<script	src="${pageContext.request.contextPath}/assets/sysmanager/sysorg.js" type="text/javascript"></script>	
<script type="text/javascript">
	jQuery(document).ready(function() {
		SysOrg.init('${pageContext.request.contextPath}');
		
	});
</script>