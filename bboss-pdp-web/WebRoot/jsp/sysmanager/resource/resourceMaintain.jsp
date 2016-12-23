<%@ page session="false" language="java"
	contentType="text/html; charset=utf-8"%>
<%@ taglib uri="/WEB-INF/tld/pager-taglib.tld" prefix="pg"%>
<div class=" col-md-12">
	<pg:true actual="${maintaindata }">
		<div class="row">
			<div class="col-md-12">
				<div class="portlet light bordered">

					<div class="portlet-title">
						<div class="caption">
							<i class="icon-pin font-yellow-crusta"></i> <span
								class="caption-subject bold font-yellow-crusta uppercase">
								资源维护 </span>

						</div>
						<div class="actions roleactions">
							<a class="btn btn-xs blue btn-addrole"  > 新增 <i class="fa fa-edit"></i></a>
							 <a class="btn btn-xs red btn-batchdelrole" >
								<i class="fa fa-times"></i> 删除
							</a> 
						</div>
					</div>
					<div class="portlet-body form">
						

					</div>
				</div>
			</div>
		</div>
	</pg:true>
		<div class="row">
			<div class="col-md-12">
				<div class="portlet light bordered">
					<div class="portlet-title ">
						<div class="caption">
							<i class="icon-pin font-yellow-crusta"></i> <span
								class="caption-subject bold font-yellow-crusta uppercase">
								全局操作列表 </span>

						</div>
						<div class="actions roleactions">
							<a class="btn btn-xs blue btn-addrole"  > 新增 <i class="fa fa-edit"></i></a>
							 <a class="btn btn-xs red btn-batchdelrole" >
								<i class="fa fa-times"></i> 批量删除
							</a> <a href="javascript:;" class="btn btn-xs green"> 批量授权 <i
								class="fa fa-font"></i>
							</a> <a href="javascript:;" class="btn btn-xs yellow"> 批量权限回收 <i
								class="fa fa-search"></i>
							</a>
						</div>
					</div>
					<div class="portlet-body portlet-rolelist"></div>
				</div>
			</div>
		</div>
		<div class="row">
			<div class="col-md-12">
				<div class="portlet light bordered">
					<div class="portlet-title ">
						<div class="caption">
							<i class="icon-pin font-yellow-crusta"></i> <span
								class="caption-subject bold font-yellow-crusta uppercase">
								数据操作列表 </span>

						</div>
						<div class="actions roleactions">
							<a class="btn btn-xs blue btn-addrole"  > 新增 <i class="fa fa-edit"></i></a>
							 <a class="btn btn-xs red btn-batchdelrole" >
								<i class="fa fa-times"></i> 批量删除
							</a> <a href="javascript:;" class="btn btn-xs green"> 批量授权 <i
								class="fa fa-font"></i>
							</a> <a href="javascript:;" class="btn btn-xs yellow"> 批量权限回收 <i
								class="fa fa-search"></i>
							</a>
						</div>
					</div>
					<div class="portlet-body portlet-rolelist"></div>
				</div>
			</div>
		</div>
</div>
							