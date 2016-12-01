<%@ page session="false" language="java"
	contentType="text/html; charset=utf-8"%>

<%@ taglib uri="/WEB-INF/tld/admin-taglib.tld" prefix="admin"%>
<!-- BEGIN PAGE LEVEL PLUGINS -->
<link
	href="${pageContext.request.contextPath}/assets/global/plugins/bootstrap-fileinput/bootstrap-fileinput.css"
	rel="stylesheet" type="text/css" />
<link
	href="${pageContext.request.contextPath}/assets/global/plugins/jstree/dist/themes/default/style.min.css"
	rel="stylesheet" type="text/css" />

<!-- END PAGE LEVEL PLUGINS -->
<!-- BEGIN PAGE LEVEL STYLES -->
<link
	href="${pageContext.request.contextPath}/assets/pages/css/profile-2.min.css"
	rel="stylesheet" type="text/css" />
 <link href="${pageContext.request.contextPath}/assets/global/plugins/datatables/datatables.min.css" rel="stylesheet" type="text/css" />
 <link href="${pageContext.request.contextPath}/assets/global/plugins/datatables/plugins/bootstrap/datatables.bootstrap.css" rel="stylesheet" type="text/css" />
 <link href="${pageContext.request.contextPath}/assets/global/plugins/bootstrap-datepicker/css/bootstrap-datepicker3.min.css" rel="stylesheet" type="text/css" />
       
<!-- END PAGE LEVEL STYLES -->



<!-- BEGIN PAGE HEADER-->
<!-- BEGIN PAGE BAR -->
<div class="page-bar">
	<admin:menuposition />

</div>
<!-- END PAGE BAR -->
<!-- BEGIN PAGE TITLE-->

<!-- END PAGE TITLE-->
<!-- END PAGE HEADER-->

<div class="tabbable-line tabbable-full-width">
	<ul class="nav nav-tabs">
		<li class="active"><a href="#tab_1_1" data-toggle="tab">
				组织用户管理 </a></li>
		<li><a href="#tab_1_3" data-toggle="tab"> 角色管理 </a></li>
		<li><a href="#tab_1_6" data-toggle="tab"> 用户组管理 </a></li>
		<li><a href="#tab_1_7" data-toggle="tab"> 资源管理 </a></li>
	</ul>
	<div class="tab-content">
		<div class="tab-pane active" id="tab_1_1">
			<div class="row">
				<div class="col-md-2">
					<div id="org_tree" class="tree-demo"></div>
				</div>
				<div class="col-md-10">
					<div class="tabbable-line tabbable-full-width">
						<ul class="nav nav-tabs">
							<li class="active"><a href="#tab_1_1_1" data-toggle="tab">
									 用户 </a></li>
							 <li><a href="#tab_1_1_2" data-toggle="tab"> 子机构 </a></li>
							 
						</ul>
						<div class="tab-content">
							<div class="tab-pane active" id="tab_1_1_1">
								<div class="row">
									 <div class="col-md-12">
                                
                                <!-- Begin: Demo Datatable 1 -->
                                <div class="portlet light portlet-fit portlet-datatable bordered">
                                    <div class="portlet-title">
                                        <div class="caption">
                                            <i class="icon-settings font-dark"></i>
                                            <span class="caption-subject font-dark sbold uppercase">用户列表</span>
                                        </div>
                                        <div class="actions">
                                            <div class="btn-group btn-group-devided" data-toggle="buttons">
                                                <label class="btn btn-transparent grey-salsa btn-outline btn-circle btn-sm active">
                                                    <input type="radio" name="options" class="toggle" id="option1">新增</label>
                                                <label class="btn btn-transparent grey-salsa btn-outline btn-circle btn-sm">
                                                    <input type="radio" name="options" class="toggle" id="option2">删除</label>
                                            </div>
                                            <div class="btn-group">
                                                <a class="btn red btn-outline btn-circle" href="javascript:;" data-toggle="dropdown">
                                                    <i class="fa fa-share"></i>
                                                    <span class="hidden-xs"> 工具 </span>
                                                    <i class="fa fa-angle-down"></i>
                                                </a>
                                                <ul class="dropdown-menu pull-right">
                                                    <li>
                                                        <a href="javascript:;"> 导出Excel </a>
                                                    </li>
                                                    <li>
                                                        <a href="javascript:;"> 导出CSV </a>
                                                    </li>
                                                    <li>
                                                        <a href="javascript:;"> 导出XML </a>
                                                    </li>
                                                    <li class="divider"> </li>
                                                    <li>
                                                        <a href="javascript:;"> 打印票据 </a>
                                                    </li>
                                                </ul>
                                            </div>
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
                                                      
                                                       
                                                        <th width="15%"> 操作 </th>
                                                    </tr>
                                                    <tr role="row" class="filter">
                                                        <td> </td>
                                                        <td>
                                                            <input type="text" class="form-control form-filter input-sm" name="userName"> </td>
                                                        
                                                        <td>
                                                            <input type="text" class="form-control form-filter input-sm" name="userRealname"> </td>
                                                        <td>
                                                            <input type="text" class="form-control form-filter input-sm" name="userWorknumber"> </td>
                                                          <td>
                                                            <input type="text" class="form-control form-filter input-sm" name="userMobiletel1"> </td>
                                                         <td>
                                                             <select name="userSex" class="form-control form-filter input-sm">
                                                                <option value=""></option>
                                                                <option value="M">男</option>
                                                                <option value="F">女</option>
                                                                <option value="-1">未知</option>
                                                            </select> </td>
                                                          <td>
                                                            <select name="userType" class="form-control form-filter input-sm">
                                                                <option value=""></option>
                                                                <option value="0">系统用户</option>
                                                                <option value="1">域用户</option>
                                                                <option value="2">第三方用户</option>
                                                                
                                                            </select> </td>
                                                                 
                                                        <td>
                                                              <select name="userIsvalid" class="form-control form-filter input-sm">
                                                                <option value=""></option>
                                                                <option value="2">开通</option>
                                                                <option value="1">申请</option>
                                                                <option value="3">停用</option>
                                                                <option value="0">删除</option>
                                                            </select> 
                                                         </td>
                                                        <td>
                                                        <select name="recursive" class="form-control form-filter input-sm">
                                                                <option value="">查询方式...</option>
                                                                <option value="0">本机构</option>
                                                                <option value="1">含子机构</option>
                                                                <option value="2">所有机构</option>
                                                                
                                                            </select> 
                                                             </td>
                                                          
                                                     
                                                        <td>
                                                            <div class="margin-bottom-5">
                                                                <button class="btn btn-xs green btn-outline filter-submit margin-bottom">
                                                                    <i class="fa fa-search"></i> 查询</button>
                                                            </div>
                                                            <button class="btn btn-xs red btn-outline filter-cancel">
                                                                <i class="fa fa-times"></i> 重置</button>
                                                        </td>
                                                    </tr>
                                                </thead>
                                                <tbody> </tbody>
                                            </table>
                                        </div>
                                    </div>
                                </div>
                                <!-- End: Demo Datatable 1 -->
                                
                            </div>
								</div>
							</div>
							<!--tab_1_2-->
							<div class="tab-pane" id="tab_1_1_2">
								<div class="row  "><div class="col-md-12">ddd</div></div>
							</div>
							 
						</div>

					</div>

				</div>
			</div>
		</div>
		<!--tab_1_2-->
		<div class="tab-pane" id="tab_1_3">
			<div class="row  "></div>
		</div>
		<!--end tab-pane-->
		<div class="tab-pane" id="tab_1_6">
			<div class="row"></div>
		</div>
		<!--end tab-pane-->

		<div class="tab-pane" id="tab_1_7">
			<div class="row">
				<div class="col-md-12">资源管理</div>
			</div>
		</div>
	</div>

</div>
 <!-- BEGIN PAGE LEVEL PLUGINS -->
        <script src="${pageContext.request.contextPath}/assets/global/scripts/datatable.js" type="text/javascript"></script>
        <script src="${pageContext.request.contextPath}/assets/global/plugins/datatables/datatables.min.js" type="text/javascript"></script>
        <script src="${pageContext.request.contextPath}/assets/global/plugins/datatables/plugins/bootstrap/datatables.bootstrap.js" type="text/javascript"></script>
        
        <!-- END PAGE LEVEL PLUGINS -->
<!-- BEGIN PAGE LEVEL PLUGINS -->


<!-- END PAGE LEVEL PLUGINS -->
<!-- BEGIN PAGE LEVEL PLUGINS -->
<script
	src="${pageContext.request.contextPath}/assets/global/plugins/jstree/dist/jstree.min.js"
	type="text/javascript"></script>
<!-- END PAGE LEVEL PLUGINS -->
<script
	src="${pageContext.request.contextPath}/jsp/sysmanager/sysmanager.js"
	type="text/javascript"></script>
<script type="text/javascript">
	jQuery(document).ready(function() {
		Sysmanager.initorg_tree(); //  initorg_tree core componets
		Sysmanager.initPickers();
		Sysmanager.getUserList('1');
		
	});
</script>
<!-- END CONTENT BODY -->