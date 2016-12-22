<%@ page session="false" language="java"
	contentType="text/html; charset=utf-8"%>

<%@ taglib uri="/WEB-INF/tld/admin-taglib.tld" prefix="admin"%>
<!-- BEGIN PAGE LEVEL PLUGINS -->

<link
	href="${pageContext.request.contextPath}/assets/global/plugins/jstree/dist/themes/default/style.min.css"
	rel="stylesheet" type="text/css" />

<!-- END PAGE LEVEL PLUGINS -->

	

       
<!-- END PAGE LEVEL STYLES -->



<!-- BEGIN PAGE HEADER-->
<!-- BEGIN PAGE BAR -->
<div class="page-bar">
	<admin:menuposition />

</div>
 <div class="row">
	<div class="col-md-12" >&nbsp;</div>
 </div>
			<div class="row">
				<div class="col-md-3" >
					<div class="portlet light bordered">
                             <div class="portlet-title tabbable-line">
                                 <div class="caption">
                                     <i class="icon-pin font-yellow-crusta"></i>
                                     <span class="caption-subject bold font-yellow-crusta uppercase"> 组织架构 </span>
                                    
                                 </div>
                                 
                             </div>      
                            <div class="portlet-body">
                            	 <div  class="scroller" style="height:450px">
                                	<div id="org_tree"  ></div>
                                	<p></p>
                                </div>	
                                
                            </div>
                        </div>
					
				</div>
				<div class="col-md-9">
					<div class="row">
						 <div class="col-md-12">
                             
                             <!-- Begin: UserList Datatable  -->
                             	<%@ include file="/jsp/sysmanager/org/orglist.jsp"%>
                             <!-- End: UserList Datatable  -->
                             
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
		PlatformCommonUtils.initSlimScroll('.scroller',null,true);
		
	});
</script>

<!-- END CONTENT BODY -->