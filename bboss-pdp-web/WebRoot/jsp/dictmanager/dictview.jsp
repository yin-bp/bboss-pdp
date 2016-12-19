<%@ page session="false" language="java"
	contentType="text/html; charset=utf-8"%>
<%@ taglib uri="/WEB-INF/tld/pager-taglib.tld" prefix="pg"%>
<%@ taglib uri="/WEB-INF/tld/admin-taglib.tld" prefix="admin"%>

<form action="#" class="form-horizontal" >
<pg:beaninfo actual="${dict }">
	<div class="form-body">
		<div class="row">
			<div class="col-md-12">
				<div class="portlet light bordered">		
					<div class="portlet-title">
						<div class="caption">
							<i class="icon-pin font-yellow-crusta"></i> <span
								class="caption-subject bold font-yellow-crusta uppercase">
								字典基本信息 </span>
		
						</div>
						 
					</div>
					<div class="portlet-body form">
						<form role="form" class="form-horizontal form-queryrole">
		
							<div class="form-body">
								<div class="row">
									<div class="col-md-4">
										<div class="form-group form-md-line-input">
											<label class="col-md-3 control-label" for="form_control_1">名称
		
											</label>
											<div class="col-md-9">
												<p class="form-control-static"> <pg:cell colName="dictName"/> </p>
											</div>
											 
										</div>
									</div>
									<div class="col-md-4">
										<div class="form-group form-md-line-input">
											<label class="col-md-3 control-label" for="form_control_1">编码
		
											</label>
											<div class="col-md-9">
												<p class="form-control-static"> <pg:cell colName="dictCode"/> </p>
											</div>
											 
											 
										</div>
									</div>
									<div class="col-md-4">
										<div class="form-group form-md-line-input">
											<label class="col-md-3 control-label" for="form_control_1">类型
		
											</label>
											<div class="col-md-9">
												<p class="form-control-static"> <pg:cell colName="handler"/> </p>
											</div>
											
										</div>
									</div>
									
								</div>
								
								<div class="row">
									<div class="col-md-4">
										<div class="form-group form-md-line-input">
											<label class="col-md-3 control-label" for="form_control_1">缓存		
											</label>
											<div class="col-md-9">
												<p class="form-control-static"> <pg:equal colName="cacheEnable" value="1" evalbody="true">
												<pg:yes>
													启用
												</pg:yes>
												<pg:no>
													禁用
												</pg:no>
												</pg:equal> </p>
											</div>
											 
										</div>
									</div>
									<div class="col-md-4">
										<div class="form-group form-md-line-input">
											<label class="col-md-3 control-label" for="form_control_1">结构
		
											</label>
											<div class="col-md-9">
												<p class="form-control-static"> <pg:equal colName="isTree" value="1" evalbody="true">
												<pg:yes>
													树
												</pg:yes>
												<pg:no>
													列表
												</pg:no>
												</pg:equal> </p>
											</div>
											 
											 
										</div>
									</div>
									 <div class="col-md-4" >
										<div class="form-group form-md-line-input">
											<label class="col-md-3 control-label" for="form_control_1">创建人
												 
											</label>
											<div class="col-md-9">
												<p class="form-control-static"> <pg:cell colName="ownerName" /> </p>
											</div>
										</div>
									</div>
									
								</div>
								
								<div class="row">
									<div class="col-md-6">
										<div class="form-group form-md-line-input">
											<label class="col-md-2 control-label" for="form_control_1">状态
		
											</label>
											<div class="col-md-10">
												<p class="form-control-static"> <pg:equal colName="dictStatus" value="1" evalbody="true"><pg:yes>正常</pg:yes><pg:no>删除</pg:no></pg:equal> </p>
											</div>
											 
										</div>
									</div>
									<div class="col-md-6">
										<div class="form-group form-md-line-input">
											<label class="col-md-2 control-label" for="form_control_1">描述
		
											</label>
											<div class="col-md-10">
												<p class="form-control-static"> <pg:cell colName="dictDesc" htmlEncode="true"/> </p>
											</div>
											 
										</div>
									</div>
									
								</div>
								 
		
							</div>
						</form>
		
					</div>
				</div>
			</div>
		</div>
				
		<div class="row">
			<div class="col-md-12">
				<div class="portlet light bordered">
					<div class="portlet-title tabbable-line">
						<div class="caption">
							<i class="icon-pin font-yellow-crusta"></i> <span
								class="caption-subject bold font-yellow-crusta uppercase">
								字典数据项 </span>
		
						</div>
						 
					</div>
					<div class="portlet-body portlet-dictitemlist">
					</div>
				</div>
			</div>
		</div>
	</div>
</form>	
<script type="text/javascript">
	jQuery(document).ready(function() {
		$(".portlet-dictitemlist",ModelDialog.getCurrentModal()).load("${pageContext.request.contextPath}/dictmanager/dictitemlist.page",{
			dictId:"<pg:cell colName="dictId"/>",
			handler:"<pg:cell colName="handler"/>",
			actiontype:"view"
		},function(){
			
		});
	});
</script>	
</pg:beaninfo>

