<%@ page session="false" language="java"
	contentType="text/html; charset=utf-8"%>
<%@ taglib uri="/WEB-INF/tld/pager-taglib.tld" prefix="pg"%>
<%@ taglib uri="/WEB-INF/tld/admin-taglib.tld" prefix="admin"%>
<link href="${pageContext.request.contextPath}/assets/pages/css/profile-2.min.css" rel="stylesheet" type="text/css" />

<!-- BEGIN PAGE HEADER-->
<!-- BEGIN PAGE BAR -->
<div class="page-bar">
	<admin:menuposition />

</div>
<!-- END PAGE BAR -->
<!-- BEGIN PAGE TITLE-->

<!-- END PAGE TITLE-->
<!-- END PAGE HEADER-->

<div class="profile">
                            <div class="tabbable-line tabbable-full-width">
                                <ul class="nav nav-tabs">
                                    <li class="active">
                                        <a href="#tab_1_1" data-toggle="tab"> 日志查询 </a>
                                    </li>
                                    <li>
                                        <a href="#tab_1_3" data-toggle="tab"> 历史日志 </a>
                                    </li>
                                    <li>
                                        <a href="#tab_1_6" data-toggle="tab"> 日志设置 </a>
                                    </li>
                                </ul>
                                <div class="tab-content">
                                    <div class="tab-pane active" id="tab_1_1">
                                    	<%@ include file="/jsp/logmanager/logs.jsp"%>
                                    </div>
                                    <!--tab_1_2-->
                                    <div class="tab-pane" id="tab_1_3">
                                     <%@ include file="/jsp/logmanager/hislogs.jsp"%>
                                      </div>
                                    <!--end tab-pane-->
                                    <div class="tab-pane" id="tab_1_6">
                                    <%@ include file="/jsp/logmanager/logsetting.jsp"%>
                                      </div>
                                    <!--end tab-pane-->
                                </div>
                            </div>
                        </div>

<script type="text/javascript">
	jQuery(document).ready(function() {
		 
		
	});
</script>

<!-- END CONTENT BODY -->