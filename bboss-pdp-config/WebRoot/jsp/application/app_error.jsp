<%@ page contentType="text/html; charset=utf-8"%>
<%@ include file="/WEB-INF/jsp/inc/tld.inc"%>
<!DOCTYPE html>
<html>

<head>
    <meta charset="utf-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>应用接入错误页面</title>
    <%@ include file="/WEB-INF/jsp/inc/css-link.inc"%>
	<%@ include file="/WEB-INF/jsp/inc/js-link.inc"%>
	
</head>

<body class="gray-bg">
<div class="row wrapper border-bottom white-bg sdp-head">
    <div class="col-sm-12">
        <ol class="breadcrumb">
            <li>位置: 系统管理</li>
            <li>应用接入管理</li>
        </ol>
    </div>
</div>
<div class="wrapper wrapper-content animated fadeInRight">
    <div class="row">
        <div class="col-sm-12">
            <div class="ibox">
                <div class="ibox-title">

                </div>
                <div class="ibox-content">
                    <form class="form-horizontal">
                   		<div>
	                        <input type="text" readonly="readonly" value="该应用在系统中已存在">
                   		</div>
                        <div class="form-group">
                            <div class="col-sm-offset-1 col-sm-11">
                                <a class="btn btn-sm btn-white" type="button" onclick="javascript:history.go(-2);">返 回</a>
                            </div>
                        </div>
                    </form>
                </div>
            </div>
        </div>
    </div>

</div>

<script src="js/jquery.min.js?v=2.1.4"></script>
<script src="js/bootstrap.min.js?v=3.3.5"></script>
</body>
</html>