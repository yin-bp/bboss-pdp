<%@page session="false" contentType="text/html;charset=UTF-8"%>
<%@ taglib uri="/WEB-INF/tld/pager-taglib.tld" prefix="pg" %>
<%@ taglib uri="/WEB-INF/tld/admin-taglib.tld" prefix="admin" %>
<!DOCTYPE html>
<html>

<head>
    <meta charset="utf-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <meta name="renderer" content="webkit">
    <meta http-equiv="Cache-Control" content="no-siteapp"/>
    <title>BBoss SSO配置管理中心</title>

    <!--[if lt IE 8]>
    <meta http-equiv="refresh" content="0;ie.html"/>
    <![endif]-->
<%@ include file="/jsp/inc/css-link.inc"%>

</head>

<body class="fixed-sidebar full-height-layout gray-bg" style="overflow:hidden">
<div id="wrapper">
    <!--左侧导航开始-->
    <nav class="navbar-default navbar-static-side" role="navigation">
        <div class="nav-close">
            <i class="fa fa-times-circle"></i>
        </div>
        <div class="sidebar-collapse">
            <ul class="nav" id="side-menu">
                <!-- 菜单头部内容 -->
                <li class="nav-header">
                    <div class="dropdown profile-element">
                        <div class="sdp-logo-title">BBoss SSO</div>
                        <div class="sdp-logo-sub-title">配置管理中心</div>
                    </div>
                    <div class="logo-element">SSO</div>
                </li>
                <!-- 菜单栏开始 -->
                <li>
                    <a class="J_menuItem" href="${pageContext.request.contextPath}/application/index.page">
                        <i class="fa fa-dashboard"></i>
                        <span class="nav-label">工作台</span>
                    </a>
                </li>
               
                <li>
                    <a class="J_menuItem" href="${pageContext.request.contextPath}/application/index.page">
                        <i class="fa fa-users"></i>
                        <span class="nav-label">应用配置</span>
                    </a>
                </li>
                 <li>
                    <a class="J_menuItem" href="${pageContext.request.contextPath}/jsp/admin/user/form_template.jsp">
                        <i class="fa fa-users"></i>
                        <span class="nav-label">用户管理</span>
                    </a>
                </li>
                
                
                <!-- 菜单栏结束 -->
            </ul>
        </div>
    </nav>
    <!--左侧导航结束-->
    <!--右侧部分开始-->
    <div id="page-wrapper" class="gray-bg dashbard-1">
        <!-- 头部导航开始 -->
        <div class="row border-bottom">
            <nav class="navbar navbar-static-top" role="navigation" style="margin-bottom: 0">
                <div class="navbar-header">
                    <a class="navbar-minimalize minimalize-styl-2 btn btn-primary" href="#"><i class="fa fa-bars"></i></a>
                </div>
                <ul class="nav navbar-top-links navbar-right">
                    <li class="dropdown">
                        <a class="dropdown-toggle count-info" data-toggle="dropdown" href="#">
                            <i class="fa fa-user"></i> <admin:accesscontrol userattribute="userName"/> <span class="caret"></span>
                        </a>
                        <ul class="dropdown-menu animated fadeInRight m-t-xs">
                            <li><a href="#">个人资料</a></li>
                            <li><a href="#">修改密码</a></li>
                            <li class="divider"></li>
                            <li><a href="${pageContext.request.contextPath}/jsp/logout.jsp">退出系统</a></li>
                        </ul>
                    </li>
                </ul>
            </nav>
        </div>
        <!-- 头部导航结束 -->
        <!-- tab页开始 -->
        <div class="row content-tabs">
            <button class="roll-nav roll-left J_tabLeft"><i class="fa fa-backward"></i></button>
            <nav class="page-tabs J_menuTabs">
                <div class="page-tabs-content">
                    <a href="javascript:void(0);" class="active J_menuTab" data-id="${pageContext.request.contextPath}/application/index.page">工作台</a>
                </div>
            </nav>
            <button class="roll-nav roll-right J_tabRight"><i class="fa fa-forward"></i></button>
            <div class="btn-group roll-nav roll-right">
                <button class="dropdown J_tabClose" data-toggle="dropdown">关闭操作<span class="caret"></span></button>
                <ul role="menu" class="dropdown-menu dropdown-menu-right">
                    <li class="J_tabCloseAll"><a>关闭全部选项卡</a></li>
                    <li class="J_tabCloseOther"><a>关闭其他选项卡</a></li>
                </ul>
            </div>
            <a href="${pageContext.request.contextPath}/jsp/logout.jsp" class="roll-nav roll-right J_tabExit"><i class="fa fa fa-sign-out"></i> 退出</a>
        </div>
        <!-- tab页结束 -->
        <!-- 操作页内容开始 -->
        <div class="row J_mainContent" id="content-main">
            <iframe class="J_iframe" name="iframe0" width="100%" height="100%" src="${pageContext.request.contextPath}/application/index.page" frameborder="0" data-id="${pageContext.request.contextPath}/application/index.page" seamless></iframe>
        </div>
        <!-- 操作页内容结束 -->
        <!-- 页脚开始 -->
        <div class="footer">
            <div class="pull-right">&copy; 2016-2017 bboss统一认证平台</div>
        </div>
        <!-- 页脚结束 -->
    </div>
    <!--右侧部分结束-->
</div>

<%@ include file="/jsp/inc/js-link.inc"%>
</body>
</html>