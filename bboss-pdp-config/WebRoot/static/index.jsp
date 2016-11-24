<%@page session="false" contentType="text/html;charset=UTF-8"%><!DOCTYPE html>
<html>

<head>
    <meta charset="utf-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <meta name="renderer" content="webkit">
    <meta http-equiv="Cache-Control" content="no-siteapp"/>
    <title>sdp_portal_admin</title>

    <!--[if lt IE 8]>
    <meta http-equiv="refresh" content="0;ie.html"/>
    <![endif]-->

    <!-- bootstrap基础框架样式 -->
    <link href="${pageContext.request.contextPath}/static/css/bootstrap.min.css?v=3.3.5" rel="stylesheet">
    <!-- 字体图标样式 -->
    <link href="${pageContext.request.contextPath}/static/css/font-awesome.min.css?v=4.4.0" rel="stylesheet">
    <!-- css动画样式 -->
    <link href="${pageContext.request.contextPath}/static/css/animate.min.css" rel="stylesheet">
    <!-- 自定义样式 -->
    <link href="${pageContext.request.contextPath}/static/css/style.css" rel="stylesheet">
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
                        <div class="sdp-logo-title">重庆有线</div>
                        <div class="sdp-logo-sub-title">增值业务平台</div>
                    </div>
                    <div class="logo-element">SDP</div>
                </li>
                <!-- 菜单栏开始 -->
                <li>
                    <a class="J_menuItem" href="${pageContext.request.contextPath}/static/workbench.html">
                        <i class="fa fa-dashboard"></i>
                        <span class="nav-label">工作台</span>
                    </a>
                </li>
                <li>
                    <a href="#">
                        <i class="fa fa-cogs"></i>
                        <span class="nav-label">系统管理</span>
                        <span class="fa arrow"></span>
                    </a>
                    <ul class="nav nav-second-level">
                        <li>
                            <a class="J_menuItem" href="${pageContext.request.contextPath}/static/operator.html">操作员管理</a>
                        </li>
                        <li>
                            <a class="J_menuItem" href="${pageContext.request.contextPath}/static/role.html">角色管理</a>
                        </li>
                        <li>
                            <a class="J_menuItem" href="${pageContext.request.contextPath}/static/menu.html">菜单管理</a>
                        </li>
                        <li>
                            <a class="J_menuItem" href="${pageContext.request.contextPath}/static/org.html">组织管理</a>
                        </li>
                        <li>
                            <a class="J_menuItem" href="${pageContext.request.contextPath}/static/enum.html">基础数据管理</a>
                        </li>
                        <li>
                            <a class="J_menuItem" href="${pageContext.request.contextPath}/static/app.html">应用子系统</a>
                        </li>
                    </ul>
                </li>
                <li>
                    <a class="J_menuItem" href="${pageContext.request.contextPath}/static/user.html">
                        <i class="fa fa-users"></i>
                        <span class="nav-label">用户管理</span>
                    </a>
                </li>
                <li>
                    <a href="#">
                        <i class="fa fa-sitemap"></i>
                        <span class="nav-label">合作伙伴</span>
                        <span class="fa arrow"></span>
                    </a>
                    <ul class="nav nav-second-level">
                        <li>
                            <a class="J_menuItem" href="${pageContext.request.contextPath}/static/partner_info.html">合作伙伴信息</a>
                        </li>
                        <li>
                            <a class="J_menuItem" href="${pageContext.request.contextPath}/static/partner_operator.html">合作伙伴账号</a>
                        </li>
                        <li>
                            <a class="J_menuItem" href="${pageContext.request.contextPath}/static/partner_verify.html">合作伙伴审核</a>
                        </li>
                        <li>
                            <a class="J_menuItem" href="${pageContext.request.contextPath}/static/partner_platform.html">业务平台管理</a>
                        </li>
                        <li>
                            <a class="J_menuItem" href="${pageContext.request.contextPath}/static/kpi_perform.html">考核评级</a>
                        </li>
                    </ul>
                </li>
                <li>
                    <a href="#">
                        <i class="fa fa-calculator"></i>
                        <span class="nav-label">结算管理</span>
                        <span class="fa arrow"></span>
                    </a>
                    <ul class="nav nav-second-level">
                        <li>
                            <a class="J_menuItem" href="${pageContext.request.contextPath}/static/share_ratio_rule.html">分摊比例管理</a>
                        </li>
                        <li>
                            <a class="J_menuItem" href="${pageContext.request.contextPath}/static/settlement_rule.html">结算规则配置</a>
                        </li>
                        <li>
                            <a class="J_menuItem" href="${pageContext.request.contextPath}/static/settlement_info.html">结算单管理</a>
                        </li>
                    </ul>
                </li>
                <li>
                    <a href="#">
                        <i class="fa fa-inbox"></i>
                        <span class="nav-label">产品中心</span>
                        <span class="fa arrow"></span>
                    </a>
                    <ul class="nav nav-second-level">
                        <li>
                            <a class="J_menuItem" href="${pageContext.request.contextPath}/static/product.html">产品管理</a>
                        </li>
                        <li>
                            <a class="J_menuItem" href="${pageContext.request.contextPath}/static/service.html">服务管理</a>
                        </li>
                        <li>
                            <a class="J_menuItem" href="${pageContext.request.contextPath}/static/check.html">审核管理</a>
                        </li>
                    </ul>
                </li>
                <li>
                    <a href="#">
                        <i class="fa fa-folder"></i>
                        <span class="nav-label">业务受理</span>
                        <span class="fa arrow"></span>
                    </a>
                    <ul class="nav nav-second-level">
                        <li>
                            <a class="J_menuItem" href="${pageContext.request.contextPath}/static/business_accept.html">业务受理纪录</a>
                        </li>
                        <li>
                            <a class="J_menuItem" href="${pageContext.request.contextPath}/static/order.html">订单管理</a>
                        </li>
                    </ul>
                </li>
                <li>
                    <a href="#">
                        <i class="fa fa-tags"></i>
                        <span class="nav-label">优惠管理</span>
                        <span class="fa arrow"></span>
                    </a>
                    <ul class="nav nav-second-level">
                        <li>
                            <a class="J_menuItem" href="${pageContext.request.contextPath}/static/discount.html">优惠策略</a>
                        </li>
                    </ul>
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
                            <i class="fa fa-user"></i> 系统管理员 <span class="caret"></span>
                        </a>
                        <ul class="dropdown-menu animated fadeInRight m-t-xs">
                            <li><a href="#">个人资料</a></li>
                            <li><a href="#">修改密码</a></li>
                            <li class="divider"></li>
                            <li><a href="#">退出系统</a></li>
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
                    <a href="javascript:void(0);" class="active J_menuTab" data-id="${pageContext.request.contextPath}/static/workbench.html">工作台</a>
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
            <a href="#" class="roll-nav roll-right J_tabExit"><i class="fa fa fa-sign-out"></i> 退出</a>
        </div>
        <!-- tab页结束 -->
        <!-- 操作页内容开始 -->
        <div class="row J_mainContent" id="content-main">
            <iframe class="J_iframe" name="iframe0" width="100%" height="100%" src="${pageContext.request.contextPath}/static/workbench.html" frameborder="0" data-id="${pageContext.request.contextPath}/static/workbench.html" seamless></iframe>
        </div>
        <!-- 操作页内容结束 -->
        <!-- 页脚开始 -->
        <div class="footer">
            <div class="pull-right">&copy; 2016-2017 重庆增值业务平台</div>
        </div>
        <!-- 页脚结束 -->
    </div>
    <!--右侧部分结束-->
</div>
<script src="${pageContext.request.contextPath}/static/js/jquery.min.js?v=2.1.4"></script>
<script src="${pageContext.request.contextPath}/static/js/bootstrap.min.js?v=3.3.5"></script>
<script src="${pageContext.request.contextPath}/static/js/contabs.min.js"></script>
<script src="${pageContext.request.contextPath}/static/js/plugins/metisMenu/jquery.metisMenu.js"></script>
<script src="${pageContext.request.contextPath}/static/js/plugins/slimscroll/jquery.slimscroll.min.js"></script>
<script src="${pageContext.request.contextPath}/static/js/plugins/layer/layer.min.js"></script>
<script src="${pageContext.request.contextPath}/static/js/plugins/pace/pace.min.js"></script>
<script src="${pageContext.request.contextPath}/static/js/sdp.js"></script>
</body>
</html>