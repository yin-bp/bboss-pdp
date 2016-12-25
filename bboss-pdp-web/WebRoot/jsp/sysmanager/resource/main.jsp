<%@ page session="false" language="java"
	contentType="text/html; charset=utf-8"%>
<%@ taglib uri="/WEB-INF/tld/pager-taglib.tld" prefix="pg"%>
<%@ taglib uri="/WEB-INF/tld/admin-taglib.tld" prefix="admin"%>
<link
	href="${pageContext.request.contextPath}/assets/pages/css/profile-2.min.css"
	rel="stylesheet" type="text/css" />

<!-- BEGIN PAGE HEADER-->
<!-- BEGIN PAGE BAR -->
<div class="page-bar">
	<admin:menuposition />

</div>
<!-- END PAGE BAR -->
<!-- BEGIN PAGE TITLE-->

<!-- END PAGE TITLE-->
<!-- END PAGE HEADER-->
<div class="row">
	<div class="col-md-12">&nbsp;		 
		<div class="alert alert-danger display-hide  alert-resourcemanager">
			<button class="close" data-close="alert"></button>
			<span class="msg"> 提示信息区 </span>
		</div>
	</div>
</div>
<div class="profile">
	<div class="tabbable-line tabbable-full-width">
		<ul class="nav nav-tabs">

			<li class="active"><a href="#tab_resource" data-toggle="tab">
					资源管理 </a></li>
			<li><a href="#tab_permissionquery" data-toggle="tab"> 授权查询 </a></li>
		</ul>
		<div class="tab-content">
			<!--tab_1_2-->
			<div class="tab-pane  active" id="tab_resource">
				<div class="row profile-account">
					<div class="col-md-3">
						<ul class="ver-inline-menu tabbable margin-bottom-10 resourcetypelist">
						<pg:list actual="${resourceTypes }">
							<li <pg:equal expression="{rowid }" value="0">class="active"</pg:equal>>
								<a data-toggle="tab" href="#resource_tab_1_1" resourceType="<pg:cell colName="id"/>">
										<i class="fa fa-cog"></i> <pg:cell colName="name"/> <pg:cell colName="id"/>
								</a> <span class="after"> </span>
							</li>
						</pg:list>	
							 
						</ul>
					</div>
					<div class="col-md-9">
						<div class="tab-content">
							<div id="resource_tab_1_1" class="tab-pane active">
							<p>请选择一个资源进行操作</p>
							</div>
							 
							 
						</div>
					</div>
					<!--end col-md-9-->
				</div>
			</div>
			<!--end tab-pane-->
			<div class="tab-pane" id="tab_permissionquery">
				<div class="row">
					<div class="col-md-2">
						<ul class="ver-inline-menu tabbable margin-bottom-10">
							<li class="active"><a data-toggle="tab" href="#tab_1"> <i
									class="fa fa-briefcase"></i> General Questions
							</a> <span class="after"> </span></li>
							 
						</ul>
					</div>
					<div class="col-md-10">
						<div class="tab-content">
							<div id="tab_1" class="tab-pane active">
								<div id="accordion1" class="panel-group">
									<div class="panel panel-default">
										<div class="panel-heading">
											<h4 class="panel-title">
												<a class="accordion-toggle" data-toggle="collapse"
													data-parent="#accordion1" href="#accordion1_1"> 1. Anim
													pariatur cliche reprehenderit, enim eiusmod high life
													accusamus terry ? </a>
											</h4>
										</div>
										<div id="accordion1_1" class="panel-collapse collapse in">
											<div class="panel-body">Anim pariatur cliche
												reprehenderit, enim eiusmod high life accusamus terry
												richardson ad squid. 3 wolf moon officia aute, non cupidatat
												skateboard dolor brunch. Food truck quinoa nesciunt laborum
												eiusmod. Brunch 3 wolf moon tempor, sunt aliqua put a bird
												on it squid single-origin coffee nulla assumenda shoreditch
												et. Nihil anim keffiyeh helvetica, craft beer labore wes
												anderson cred nesciunt sapiente ea proident. Ad vegan
												excepteur butcher vice lomo. Leggings occaecat craft beer
												farm-to-table, raw denim aesthetic synth nesciunt you
												probably haven't heard of them accusamus labore sustainable
												VHS.</div>
										</div>
									</div>
									
								</div>
							</div>
						</div>
					</div>
				</div>
			</div>
			<!--end tab-pane-->
		</div>
	</div>
</div>
<script
	src="${pageContext.request.contextPath}/assets/sysmanager/sysresource.js"
	type="text/javascript"></script>
<script src="${pageContext.request.contextPath}/assets/global/plugins/layer/layer.js"></script>
<script type="text/javascript">
	jQuery(document).ready(function() {
		SysResource.init('${pageContext.request.contextPath}');
		<pg:notnull actual="${resourceType}">
			SysResource.loadResourceMaintain('${resourceType}');
		</pg:notnull>
		
	});
	
</script>

<!-- END CONTENT BODY -->