<%@ page session="false" language="java"
	contentType="text/html; charset=utf-8"%>

<%@ taglib uri="/WEB-INF/tld/pager-taglib.tld" prefix="pg"%>

<div class="portlet light bordered">
	<div class="portlet-title">
		<div class="caption">
			<i class="fa fa-gift"></i>${systemname}
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
                                                    <h3 class="panel-title">系统基本信息</h3>
                                                </div>
                                                <div class="panel-body form">
                                                    <!-- BEGIN FORM-->
                                                    <form class="form-horizontal" role="form">
                                                        <div class="form-body">  
                                                           <div class="row">
                                                                <div class="col-md-6">                                                     
		                                                            <div class="row">
		                                                                <div class="col-md-12">
		                                                                    <div class="form-group">
		                                                                        <label class="control-label col-md-3">系统名称:</label>
		                                                                        <div class="col-md-9">
		                                                                            <p class="form-control-static"> 
		                                                                            	<pg:map actual="${framework.localeDescriptions}">
																							<pg:notequal expression="{rowid}" value="0"><br></pg:notequal><pg:mapkey/>:<pg:cell/>&nbsp;
																						</pg:map> </p>
		                                                                        </div>
		                                                                    </div>
		                                                                </div>
		                                                               
		                                                            </div>
		                                                             <div class="row">
		                                                                <div class="col-md-12">
		                                                                    <div class="form-group">
		                                                                        <label class="control-label col-md-3">left_width:</label>
		                                                                        <div class="col-md-9">
		                                                                            <p class="form-control-static"> ${framework.left_width} </p>
		                                                                        </div>
		                                                                    </div>
		                                                                </div>
		                                                               
		                                                            </div>
		                                                             <div class="row">
		                                                                <div class="col-md-12">
		                                                                    <div class="form-group">
		                                                                        <label class="control-label col-md-3">top_height:</label>
		                                                                        <div class="col-md-9">
		                                                                            <p class="form-control-static"> ${framework.top_height} </p>
		                                                                        </div>
		                                                                    </div>
		                                                                </div>
		                                                               
		                                                            </div>
		                                                            <div class="row">
		                                                                <div class="col-md-12">
		                                                                    <div class="form-group">
		                                                                        <label class="control-label col-md-3">showleftmenu:</label>
		                                                                        <div class="col-md-9">
		                                                                            <p class="form-control-static"> ${framework.showrootleftmenu} </p>
		                                                                        </div>
		                                                                    </div>
		                                                                </div>
		                                                               
		                                                            </div>
		                                                             <div class="row">
		                                                                <div class="col-md-12">
		                                                                    <div class="form-group">
		                                                                        <label class="control-label col-md-3">messagesource:</label>
		                                                                        <div class="col-md-9">
		                                                                            <p class="form-control-static"> ${framework.messagesourcefiles} </p>
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
                                            <div class="panel panel-info">
                                                <div class="panel-heading">
                                                    <h3 class="panel-title">系统首页</h3>
                                                </div>
                                                <div class="panel-bodyform">
                                                    <!-- BEGIN FORM-->
                                                    <form class="form-horizontal" role="form">
                                                        <div class="form-body">                                                          
                                                            <div class="row">
                                                                <div class="col-md-12">
                                                                    <div class="form-group">
                                                                        <label class="control-label col-md-3">id:</label>
                                                                        <div class="col-md-9">
                                                                            <p class="form-control-static"> 
                                                                            	${publicItem.id} </p>
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
                                                                            <pg:map actual="${publicItem.localeNames}">
																				<pg:notequal expression="{rowid}" value="0"><br></pg:notequal><pg:mapkey/>:<pg:cell/>&nbsp;
																			</pg:map></p>
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
                                                                        <label class="control-label col-md-3">首页地址:</label>
                                                                        <div class="col-md-9">
                                                                            <p class="form-control-static"> ${publicItem.workspaceContent} </p>
                                                                        </div>
                                                                    </div>
                                                                </div>
                                                               
                                                            </div>
                                                             <div class="row">
                                                                <div class="col-md-12">
                                                                    <div class="form-group">
                                                                        <label class="control-label col-md-3">普通用户可访问url资源:</label>
                                                                        <div class="col-md-9">
                                                                            <p class="form-control-static"> 
                                                                            <pg:list actual="${publicItem.authorResources}">
																				<pg:notequal expression="{rowid}" value="0"><br></pg:notequal><pg:cell/>
																			</pg:list> </p>
                                                                        </div>
                                                                    </div>
                                                                </div>
                                                               
                                                            </div>
                                                             <div class="row">
                                                                <div class="col-md-12">
                                                                    <div class="form-group">
                                                                        <label class="control-label col-md-3">mouseupimg:</label>
                                                                        <div class="col-md-9">
                                                                            <p class="form-control-static"> <pg:map actual="${publicItem.localeMouseupimgs}">
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
                                                                            <p class="form-control-static"> <pg:map actual="${publicItem.localeMouseoverimgs}">
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
                                                                            <p class="form-control-static"> <pg:map actual="${publicItem.localeMouseclickimgs}">
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
                                                                            <p class="form-control-static"> <pg:map actual="${publicItem.localeMouseoutimgs}">
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
                                                                            <p class="form-control-static"> <pg:map actual="${publicItem.localeHeadimgs}">
																				<pg:notequal expression="{rowid}" value="0"><br></pg:notequal><pg:mapkey/>:<pg:cell/>&nbsp;
																			</pg:map> </p>
                                                                        </div>
                                                                    </div>
                                                                </div>
                                                               
                                                            </div>
                                                           
                                                            <pg:map actual="${publicItem.workspacecontentExtendAttribute}">
                                                            	<div class="row">
	                                                                <div class="col-md-12">
	                                                                    <div class="form-group">
	                                                                        <label class="control-label col-md-3"><pg:mapkey />:</label>
	                                                                        <div class="col-md-9">
	                                                                            <p class="form-control-static"> <pg:cell/> </p>
	                                                                        </div>
	                                                                    </div>
	                                                                </div>
	                                                               
	                                                            </div>
																
															</pg:map>
                                                            
                                                        </div>
                                                        
                                                    </form> </div>
                                            </div>
                                            
                                        </div>
                                         <pg:notempty actual="${subSystems }">
                                          <div class="panel panel-success">
                                              <!-- Default panel contents -->
                                              <div class="panel-heading">
                                                  <h3 class="panel-title">子系统</h3>
                                              </div>
                                              <div class="panel-body">
                                                  <p> 开发平台支持多个子系统功能,各个子系统菜单配置文件在主系统配置文件module.xml中配置和加载<br>子系统中不能配置其他子系统</p>
                                              </div>
                                              <!-- Table -->
                                              <table class="table">
                                                  <thead>
                                                      <tr>
                                                      	<th> id </th>
                                                          <th> name </th>                                                            
                                                          
                                                          <th> 配置文件 </th>
                                                          <th> successRedirect </th>
                                                          <th> logoutredirect </th>
                                                      </tr>
                                                  </thead>
                                                  <tbody>
                                                  	<pg:map actual="${subSystems}">
                                                  	 <tr>
                                                          <td> <pg:cell colName="id"/> </td>
                                                          <td> <pg:map colName="localeNames">
																	<pg:notequal expression="{rowid}" value="0"><br></pg:notequal><pg:mapkey/>:<pg:cell/>&nbsp;
																</pg:map> 
														 </td>
                                                          <td> <pg:cell colName="module"/> </td>
                                                          <td> <pg:cell colName="successRedirect"/> </td>
                                                          <td><pg:cell colName="logoutredirect"/> </td>
                                                      </tr>
					 
						
												</pg:map>
                                                     
                                                  </tbody>
                                              </table>
                                          </div>
                                          </pg:notempty>
                                        
	</div>
</div>
