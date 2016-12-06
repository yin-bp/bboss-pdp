<%@ page session="false" language="java"
	contentType="text/html; charset=utf-8"%>

<%@ taglib uri="/WEB-INF/tld/admin-taglib.tld" prefix="admin"%>
<!-- BEGIN PAGE LEVEL PLUGINS -->

<link
	href="${pageContext.request.contextPath}/assets/global/plugins/jstree/dist/themes/default/style.min.css"
	rel="stylesheet" type="text/css" />

<!-- END PAGE LEVEL PLUGINS -->

	
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
				<div class="col-md-2" style="overflow-x:auto;overflow-y:hidden;">
					<div id="org_tree" class="tree-demo"></div>
				</div>
				<div class="col-md-10">
					<div class="tabbable-line tabbable-full-width">
						<ul class="nav nav-tabs">
							<li class="active"><a href="#tab_1_1_1" data-toggle="tab" onclick="javascript:Sysmanager.showUsers(Sysmanager.getDepartId());">
									 用户 </a></li>
							 <li><a href="#tab_1_1_2" data-toggle="tab"  onclick="javascript:Sysmanager.showOrgs(Sysmanager.getDepartId());"> 子机构 </a></li>
							 
						</ul>
						<div class="tab-content">
							<div class="tab-pane active" id="tab_1_1_1">
								<div class="row">
									 <div class="col-md-12">
                                
                                <!-- Begin: UserList Datatable  -->
                                	<%@ include file="/jsp/sysmanager/user/userlist.jsp"%>
                                <!-- End: UserList Datatable  -->
                                
                            		</div>
								</div>
							</div>
							<!--tab_1_2-->
							<div class="tab-pane" id="tab_1_1_2">
								<div class="row  "><div class="col-md-12">
   								<!-- Begin: UserList Datatable  -->
                                	<%@ include file="/jsp/sysmanager/org/orglist.jsp"%>
                                <!-- End: UserList Datatable  -->
								</div></div>
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
 

<script
	src="${pageContext.request.contextPath}/assets/global/plugins/jstree/dist/jstree.min.js"
	type="text/javascript"></script>
<!-- END PAGE LEVEL PLUGINS -->

<script type="text/javascript">
	jQuery(document).ready(function() {
		Sysmanager.initorg_tree(); //  initorg_tree core componets
		Sysmanager.initPickers();
		Sysmanager.getUserList('0');
		Sysmanager.getOrgList('0');
		
	});
</script>

<!-- END CONTENT BODY -->