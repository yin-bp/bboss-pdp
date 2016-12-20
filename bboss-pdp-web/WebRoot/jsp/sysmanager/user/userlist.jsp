<%@ page session="false" language="java"
	contentType="text/html; charset=utf-8"%>
<div class="portlet light portlet-fit portlet-datatable bordered">
	<div class="portlet-title">
		<div class="caption">
			<i class="icon-settings font-dark"></i> <span
				class="caption-subject font-dark sbold uppercase">用户列表</span>
		</div>
		<div class="actions">

			

			<!-- <a
				href="${pageContext.request.contextPath}/sysmanager/user/toAddSmUser.page"
				class="btn btn-sm red" data-target="#ajax-user-add"
				data-toggle="modal" data-backdrop="static" id="button_sys_add_user"> 新增 <i class="fa fa-edit"></i>

			</a> -->
			<a
				class="btn btn-xs blue"  
				data-toggle="modal" data-backdrop="static" id="button_sys_add_user"> 新增 <i class="fa fa-edit"></i>

			</a>
			 <a class="btn btn-xs red" id="button_sys_delete_user">
				<i class="fa fa-times"></i> 删除
			</a> <a href="javascript:;" class="btn btn-xs green"> 批量授权 <i
				class="fa fa-font"></i>
			</a> <a href="javascript:;" class="btn btn-xs yellow"> 批量角色设置 <i
				class="fa fa-search"></i>
			</a> <a href="javascript:;" class="btn btn-xs purple" id="button_sys_order_user"> <i
				class="fa fa-file-o"></i> 用户排序
			</a> <a href="javascript:;" class="btn btn-xs green" id="button_sys_moveout_user"> 用户调出 <i
				class="fa fa-plus"></i>
			</a> <a href="javascript:;" class="btn btn-xs grey-cascade" id="button_sys_movein_user"> 用户调入 <i
				class="fa fa-link"></i>
			</a>
		</div>

	</div>

    <div class="portlet-body">

        <div class="table-container">
            
            <table class="table table-striped table-bordered table-hover table-checkable  table-header-fixed" id="datatable_userlist">
                <thead>
                    <tr role="row" class="heading">
                        <th width="2%">
                            <label class="mt-checkbox mt-checkbox-single mt-checkbox-outline">
                                <input type="checkbox" class="group-checkable" data-set="#datatable_userlist .checkboxes" />
                                <span></span>
                            </label>
                        </th>
                        <th width="10%"> 账号 </th>
                        <th width="10%"> 名称 </th>
                        <th width="10%"> 工号 </th>
                        <th width="10%"> 电话 </th>
                        <th width="10%"> 性别 </th>
                        <th width="10%"> 类别</th>
                        <th width="10%"> 状态 </th>
                        <th width="15%"> 组织</th>
                      
                       
                        <th width="10%"> 操作 </th>
                    </tr>
                    <tr role="row" class="filter">
                        <td> <button class="btn btn-xs red btn-outline filter-cancel">
                                <i class="fa fa-times"></i> 重置</button> </td>
                        <td>
                            <input type="text" class="form-control form-filter input-xs" name="userName"> </td>
                        
                        <td>
                            <input type="text" class="form-control form-filter input-xs" name="userRealname"> </td>
                        <td>
                            <input type="text" class="form-control form-filter input-xs" name="userWorknumber"> </td>
                          <td>
                            <input type="text" class="form-control form-filter input-xs" name="userMobiletel1"> </td>
                         <td>
                         		<dict:select  type="sex" name="userSex" textValue="全部" textNAN="-1" extend="class=\"form-control form-filter input-xs\"" />
                            
                            </td>
                          <td>
                          	<select name="userType" class="form-control form-filter input-xs">
                                <option value="">全部</option>
                                <option value="0">系统用户</option>
                                <option value="1">域用户</option>
                                <option value="2">第三方用户</option>
                                
                            </select> </td>
                                 
                        <td>
                              <select name="userIsvalid" class="form-control form-filter input-xs">
                                <option value="">全部</option>
                                <option value="2">开通</option>
                                <option value="1">申请</option>
                                <option value="3">停用</option>
                                <option value="0">删除</option>
                            </select> 
                         </td>
                        <td>
                        <select name="recursive" class="form-control form-filter input-xs">
                              
                                <option value="0">本机构</option>
                                <option value="1">含子机构</option>
                                <option value="2">所有机构</option>
                                <option value="3">所有用户</option>
                            </select> 
                             </td>
                          
                     
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
<script
	src="${pageContext.request.contextPath}/assets/sysmanager/sysuser.js" type="text/javascript"></script>
<script
	src="${pageContext.request.contextPath}/assets/sysmanager/moveuser.js" type="text/javascript"></script>
	
<script type="text/javascript">
	jQuery(document).ready(function() {
		SysUser.init('${pageContext.request.contextPath}');
		SysMoveUsers.init('${pageContext.request.contextPath}');
	});
</script>