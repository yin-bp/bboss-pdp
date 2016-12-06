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
			<div class="row">
				<div class="col-md-2" style="overflow-x:auto;overflow-y:hidden;">
					<div id="org_tree" class="tree-demo"></div>
				</div>
				<div class="col-md-10">
					<div class="row">
						 <div class="col-md-12">
                             
                             <!-- Begin: UserList Datatable  -->
                             	<%@ include file="/jsp/sysmanager/org/orglist.jsp"%>
                             <!-- End: UserList Datatable  -->
                             
                         		</div>
					</div>
				</div>
			</div>
		


</div>
 

<script
	src="${pageContext.request.contextPath}/assets/global/plugins/jstree/dist/jstree.min.js"
	type="text/javascript"></script>
 <script
	src="${pageContext.request.contextPath}/assets/sysmanager/sysmanager.js"
	type="text/javascript"></script>	
<!-- END PAGE LEVEL PLUGINS -->

<script type="text/javascript">
	jQuery(document).ready(function() {
		Sysmanager.initorg_tree(false); //  initorg_tree core componets
		
		SysOrg.getOrgList('0');
		
		
	});
</script>

<!-- END CONTENT BODY -->