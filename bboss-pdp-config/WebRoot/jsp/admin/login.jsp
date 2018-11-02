<%@ page contentType="text/html; charset=utf-8"%>
<%@ include file="/jsp/inc/tld.inc" %>
<!DOCTYPE html>
<html lang="en">
  <head>
    <meta charset="utf-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <meta name="description" content="">
    <meta name="author" content="">
    <link rel="shortcut icon" href="${pageContext.request.contextPath}/static/assets/img/favicon.png">

    <title>BBoss认证服务器-配置管理中心-最好的单点登录解决方案</title>

    <!-- Bootstrap -->
    <link href="${pageContext.request.contextPath}/static/css/bootstrap.min.css" rel="stylesheet">

    <!-- siimple style -->
    <link href="${pageContext.request.contextPath}/static/assets/css/style.css" rel="stylesheet">
    <link href="${pageContext.request.contextPath}/static/css/font-awesome.min.css" rel="stylesheet">
    
    
    <!-- HTML5 shim and Respond.js IE8 support of HTML5 elements and media queries -->
    <!--[if lt IE 9]>
      <script src="${pageContext.request.contextPath}/static/assets/js/html5shiv.js"></script>
      <script src="${pageContext.request.contextPath}/static/assets/js/respond.min.js"></script>
    <![endif]-->
  </head>

  <body>

    <!-- Fixed navbar -->
    <div class="navbar navbar-inverse navbar-fixed-top">
      <div class="container">
        <div class="navbar-header">
          <button type="button" class="navbar-toggle" data-toggle="collapse" data-target=".navbar-collapse">
            <span class="icon-bar"></span>
            <span class="icon-bar"></span>
            <span class="icon-bar"></span>
          </button>
          <a class="navbar-brand" href="http://www.bbossgroups.com" target="_blank">BBoss认证服务器</a>
        </div>
        <div class="navbar-collapse collapse">
          <ul class="nav navbar-nav navbar-right">
            <li><a href="https://github.com/bbossgroups/bboss-pdp">下载</a></li>
			<li><a href="#">联系我们</a></li>
          </ul>
        </div><!--/.nav-collapse -->
      </div>
    </div>

	<div id="header">
		<div class="container">
			<div class="row">
				<div class="col-lg-6">
					<h1>配置管理中心</h1>
					<h2 class="subtitle">最好的单点登录解决方案</h2>
					<form class="form-inline signup" id="loginForm" method="post" action="${pageContext.request.contextPath}/login.page" role="form">
					  <input id="flag" name = "flag" type="hidden" value="1">
					  <div class="form-group">
					    <input type="text" class="form-control" id="userName" name="userName" placeholder="输入账号">
					  </div>
					   <div class="form-group">
					    <input type="password" class="form-control" id="password" name="password" placeholder="输入口令">
					  </div>
					   <input type="button" class="btn btn-theme" value="登陆" onclick="Admin.login()"/>  
					  
					</form>					
				</div>
				<div class="col-lg-4 col-lg-offset-2">
					<div id="carousel-example-generic" class="carousel slide" data-ride="carousel">
					  <ol class="carousel-indicators">
						<li data-target="#carousel-example-generic" data-slide-to="0" class="active"></li>
						<li data-target="#carousel-example-generic" data-slide-to="1"></li>
						<li data-target="#carousel-example-generic" data-slide-to="2"></li>
					  </ol>					
					  <!-- slides -->
					  <div class="carousel-inner">
						<div class="item active">
						  <img src="${pageContext.request.contextPath}/static/assets/img/slide1.png" alt="">
						</div>
						<div class="item">
						  <img src="${pageContext.request.contextPath}/static/assets/img/slide2.png" alt="">
						</div>
						<div class="item">
						  <img src="${pageContext.request.contextPath}/static/assets/img/slide3.png" alt="">
						</div>
					  </div>
					</div>		
				</div>
				
			</div>
		</div>
	</div>
	<div id="footer">
		<div class="container">
			<div class="row">
				<div class="col-lg-6 col-lg-offset-3">
						<p class="copyright">Copyright &copy; 2016 - bbossgroups.com</p>
				</div>
			</div>
		</div>
	</div>

    <script src="${pageContext.request.contextPath}/static/js/jquery.min.js"></script>
    <script src="${pageContext.request.contextPath}/static/js/bootstrap.min.js"></script>
    <script type="text/javascript" src="${pageContext.request.contextPath}/static/plugins/jquery-validation/jquery.validate.js"></script>
	<script src="${pageContext.request.contextPath}/static/plugins/jquery-validation/additional-methods.min.js" type="text/javascript"></script>
	<script src="${pageContext.request.contextPath}/static/plugins/bootstrap-toastr/toastr.min.js" type="text/javascript"></script>
	<script src="${pageContext.request.contextPath}/static/plugins/jquery.form.js" type="text/javascript"></script>
  </body>
  <script type="text/javascript" src="${pageContext.request.contextPath}/jsp/admin/admin.js"></script>
<script type="text/javascript">
	$(function(){
		Admin.initloginForm();
	});
	
	
</script>
</html>
