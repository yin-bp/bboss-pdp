<%@ page session="false" language="java"
	contentType="text/html; charset=utf-8"%>

<%@ taglib uri="/WEB-INF/tld/pager-taglib.tld" prefix="pg"%>

<div class="portlet light bordered">
	<div class="portlet-title">
		<div class="caption">
			<i class="fa fa-gift"></i>菜单:${systemname}>>${menuname}
		</div>
		<div class="tools">
			<a href="javascript:;" class="collapse"> </a> <a href=""
				class="fullscreen"> </a>

		</div>
	</div>
	<div class="portlet-body  ">
		<div class="clearfix">
                                            
                                            <div class="panel panel-primary">
                                                <div class="panel-heading">
                                                    <h3 class="panel-title">菜单基本信息</h3>
                                                </div>
                                                <div class="panel-body form">
                                                    <!-- BEGIN FORM-->
                                                    <form class="form-horizontal" role="form">
                                                        <div class="form-body">  
                                                           <div class="row">
                                                                <div class="col-md-12">                                                     
		                                                            <div class="row">
		                                                                <div class="col-md-12">
		                                                                    <div class="form-group">
		                                                                        <label class="control-label col-md-3">菜单编码:</label>
		                                                                        <div class="col-md-9">
		                                                                            <p class="form-control-static"> 
		                                                                            	${menuid} </p>
		                                                                        </div>
		                                                                    </div>
		                                                                </div>
		                                                               
		                                                            </div>
		                                                             <div class="row">
		                                                                <div class="col-md-12">
		                                                                    <div class="form-group">
		                                                                        <label class="control-label col-md-3">name:</label>
		                                                                        <div class="col-md-9">
		                                                                            <p class="form-control-static"> 
		                                                                            <pg:map actual="${menu.localeNames}">
																						<pg:notequal expression="{rowid}" value="0"><br></pg:notequal><pg:mapkey/>:<pg:cell/>&nbsp;
																					</pg:map></p>
		                                                                        </div>
		                                                                    </div>
		                                                                </div>
		                                                               
		                                                            </div>
		                                                             <div class="row">
		                                                                <div class="col-md-12">
		                                                                    <div class="form-group">
		                                                                        <label class="control-label col-md-3">菜单URL:</label>
		                                                                        <div class="col-md-9">
		                                                                            <p class="form-control-static"> ${menu.url} 
		                                                                            </p>
		                                                                        </div>
		                                                                    </div>
		                                                                </div>
		                                                               
		                                                            </div>
		                                                             <div class="row">
		                                                                <div class="col-md-12">
		                                                                    <div class="form-group">
		                                                                        <label class="control-label col-md-3">top url:</label>
		                                                                        <div class="col-md-9">
		                                                                            <p class="form-control-static"> ${publicItem.top} </p>
		                                                                        </div>
		                                                                    </div>
		                                                                </div>
		                                                               
		                                                            </div>
		                                                            <div class="row">
		                                                                <div class="col-md-12">
		                                                                    <div class="form-group">
		                                                                        <label class="control-label col-md-3">菜单标识path:</label>
		                                                                        <div class="col-md-9">
		                                                                            <p class="form-control-static"> ${menu.path} 
		                                                                            </p>
		                                                                        </div>
		                                                                    </div>
		                                                                </div>
		                                                               
		                                                            </div>
		                                                             <div class="row">
		                                                                <div class="col-md-12">
		                                                                    <div class="form-group">
		                                                                        <label class="control-label col-md-3">权限关联URL:</label>
		                                                                        <div class="col-md-9">
		                                                                            <p class="form-control-static"> 
		                                                                            <pg:list actual="${menu.authorResources}">
																						<pg:notequal expression="{rowid}" value="0"><br></pg:notequal><pg:cell/>
																					</pg:list> </p>
		                                                                        </div>
		                                                                    </div>
		                                                                </div>
		                                                               
		                                                            </div>
		                                                            <div class="row">
		                                                                <div class="col-md-12">
		                                                                    <div class="form-group">
		                                                                        <label class="control-label col-md-3">Option:</label>
		                                                                        <div class="col-md-9">
		                                                                            <p class="form-control-static"> ${menu.option} </p>
		                                                                        </div>
		                                                                    </div>
		                                                                </div>
		                                                               
		                                                            </div>
		                                                             <div class="row">
		                                                                <div class="col-md-12">
		                                                                    <div class="form-group">
		                                                                        <label class="control-label col-md-3">Used:</label>
		                                                                        <div class="col-md-9">
		                                                                            <p class="form-control-static"> ${menu.used} </p>
		                                                                        </div>
		                                                                    </div>
		                                                                </div>
		                                                               
		                                                            </div>
		                                                            
		                                                            <div class="row">
		                                                                <div class="col-md-12">
		                                                                    <div class="form-group">
		                                                                        <label class="control-label col-md-3">扩展属性:</label>
		                                                                        <div class="col-md-9">
		                                                                            <p class="form-control-static"> 
		                                                                            <pg:map actual="${menu.extendAttributes}">
																						<pg:notequal expression="{rowid}" value="0"><br></pg:notequal><pg:mapkey/>=<pg:cell/>
																					</pg:map> </p>
		                                                                        </div>
		                                                                    </div>
		                                                                </div>
		                                                               
		                                                            </div>
	                                                            </div>
	                                                            <pg:notempty actual="${rootsystem }">
		                                                            <div class="col-md-6">                                                     
			                                                            <div class="row">
			                                                                <div class="col-md-12">
			                                                                    <div class="form-group">
			                                                                        <label class="control-label col-md-3">id:</label>
			                                                                        <div class="col-md-9">
			                                                                            <p class="form-control-static"> 
			                                                                            	${rootsystem.id } </p>
			                                                                        </div>
			                                                                    </div>
			                                                                </div>
			                                                               
			                                                            </div>
			                                                             <div class="row">
			                                                                <div class="col-md-12">
			                                                                    <div class="form-group">
			                                                                        <label class="control-label col-md-3">配置文件:</label>
			                                                                        <div class="col-md-9">
			                                                                            <p class="form-control-static"> ${rootsystem.module} </p>
			                                                                        </div>
			                                                                    </div>
			                                                                </div>
			                                                               
			                                                            </div>
			                                                             <div class="row">
			                                                                <div class="col-md-12">
			                                                                    <div class="form-group">
			                                                                        <label class="control-label col-md-3">successRedirect:</label>
			                                                                        <div class="col-md-9">
			                                                                            <p class="form-control-static"> ${rootsystem.successRedirect} </p>
			                                                                        </div>
			                                                                    </div>
			                                                                </div>
			                                                               
			                                                            </div>
			                                                            <div class="row">
			                                                                <div class="col-md-12">
			                                                                    <div class="form-group">
			                                                                        <label class="control-label col-md-3">logoutredirect:</label>
			                                                                        <div class="col-md-9">
			                                                                            <p class="form-control-static"> ${rootsystem.logoutredirect} </p>
			                                                                        </div>
			                                                                    </div>
			                                                                </div>
			                                                               
			                                                            </div>
			                                                            
		                                                            </div>
		                                                        </pg:notempty>
		                                                     </div>
                                                        </div>
                                                        
                                                    </form> </div>
                                            </div>
                                            <div class="panel panel-success">
                                                <div class="panel-heading">
                                                    <h3 class="panel-title">菜单图标设置</h3>
                                                </div>
                                                <div class="panel-bodyform">
                                                    <!-- BEGIN FORM-->
                                                    <form class="form-horizontal" role="form">
                                                        <div class="form-body">                                                          
                                                            
                                                             
                                                             <div class="row">
                                                                <div class="col-md-12">
                                                                    <div class="form-group">
                                                                        <label class="control-label col-md-3">mouseupimg:</label>
                                                                        <div class="col-md-9">
                                                                            <p class="form-control-static"> <pg:map actual="${menu.localeMouseupimgs}">
																				<pg:notequal expression="{rowid}" value="0"><br></pg:notequal><pg:mapkey/>:<pg:cell/>&nbsp;
																			</pg:map> </p>
                                                                        </div>
                                                                    </div>
                                                                </div>
                                                               
                                                            </div>
                                                            <div class="row">
                                                                <div class="col-md-12">
                                                                    <div class="form-group">
                                                                        <label class="control-label col-md-3">mouseoverimg:</label>
                                                                         <div class="col-md-9">
                                                                            <p class="form-control-static"> <pg:map actual="${menu.localeMouseoverimgs}">
																				<pg:notequal expression="{rowid}" value="0"><br></pg:notequal><pg:mapkey/>:<pg:cell/>&nbsp;
																			</pg:map> </p>
                                                                        </div>
                                                                    </div>
                                                                </div>
                                                               
                                                            </div>
                                                             <div class="row">
                                                                <div class="col-md-12">
                                                                    <div class="form-group">
                                                                        <label class="control-label col-md-3">mouseclickimg:</label>
                                                                        <div class="col-md-9">
                                                                            <p class="form-control-static"> <pg:map actual="${menu.localeMouseclickimgs}">
																				<pg:notequal expression="{rowid}" value="0"><br></pg:notequal><pg:mapkey/>:<pg:cell/>&nbsp;
																			</pg:map> </p>
                                                                        </div>
                                                                    </div>
                                                                </div>
                                                               
                                                            </div>
                                                            <div class="row">
                                                                <div class="col-md-12">
                                                                    <div class="form-group">
                                                                        <label class="control-label col-md-3">mouseoutimg:</label>
                                                                        <div class="col-md-9">
                                                                            <p class="form-control-static"> <pg:map actual="${menu.localeMouseoutimgs}">
																				<pg:notequal expression="{rowid}" value="0"><br></pg:notequal><pg:mapkey/>:<pg:cell/>&nbsp;
																			</pg:map> </p>
                                                                        </div>
                                                                    </div>
                                                                </div>
                                                               
                                                            </div>
                                                             <div class="row">
                                                                <div class="col-md-12">
                                                                    <div class="form-group">
                                                                        <label class="control-label col-md-3">headimg:</label>
                                                                        <div class="col-md-9">
                                                                            <p class="form-control-static"> <pg:map actual="${menu.localeHeadimgs}">
																				<pg:notequal expression="{rowid}" value="0"><br></pg:notequal><pg:mapkey/>:<pg:cell/>&nbsp;
																			</pg:map> </p>
                                                                        </div>
                                                                    </div>
                                                                </div>
                                                               
                                                            </div>
                                                           
                                                             
                                                            
                                                        </div>
                                                        
                                                    </form> </div>
                                            </div>
                                            
                                        </div>
                                         
                                        
	</div>
</div>
