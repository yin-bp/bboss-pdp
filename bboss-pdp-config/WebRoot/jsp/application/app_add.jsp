<%@ page language="java" pageEncoding="utf-8" session="false"%>
<%@ include file="/jsp/inc/tld.inc"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="utf-8">
<meta name="viewport" content="width=device-width, initial-scale=1.0">
<meta http-equiv="Content-type" content="text/html; charset=utf-8">
<%@ include file="/jsp/inc/css-link.inc"%>

</head>
<body class="gray-bg">
	<div class="wrapper wrapper-content animated fadeInRight">
		<div class="row">
			<div class="col-sm-12">
				<div class="ibox">
					<div class="ibox-title">
						<h5>应用新增</h5>
					</div>
					<div class="ibox-content">
						<form id = "addAppSystem" method="post" action="${pageContext.request.contextPath}/application/addApplication.page" class="form-horizontal">
						<input type="hidden" name="flag" value="1"/>
							<div class="form-group">
								<label class="col-sm-2 control-label">应用编码:</label>
								<div class="col-sm-4">
									<input type="text" class="form-control input-sm" name = "appCode"> <span
										class="help-block m-b-none">* 数字和字母的组合</span>
								</div>
							</div>
							<div class="form-group">
								<label class="col-sm-2 control-label">应用名称:</label>
								<div class="col-sm-4">
									<input type="text" class="form-control input-sm" name ="appName">
								</div>
							</div>
							<div class="form-group">
								<label class="col-sm-2 control-label">输入口令:</label>
								<div class="col-sm-4">
									<input type="text" class="form-control input-sm" name="appSecret" id="appSecret"> 
									<a class="btn btn-sm btn-primary" type="button" href="javascript:Application.getSystemSecret()">生成口令</a>
								</div>
							</div>
							<div class="form-group">
								<label class="col-sm-2 control-label">确认口令:</label>
								<div class="col-sm-4">
									<input type="text" class="form-control input-sm" name = "appSecretAck" id="appSecretAck"><span
										class="help-block m-b-none"><font id="re_secret_font" color="red">*</font> </span>
								</div>
							</div>
							<div class="form-group">
								<label class="col-sm-2 control-label">有效时长:</label>
								<div class="col-sm-4">
									<input type="text" class="form-control input-sm" name = "ticketlivetimes" value="-2"> <span
										class="help-block m-b-none">单位：毫秒，-2表示读取tokenconf.xml中的全局配置</span>
								</div>
							</div>
							
							<div class="form-group">
								<label class="col-sm-2 control-label">证书算法:</label>
								<div class="col-sm-4">
									<select class="form-control input-sm" name = "certAlgorithm" ><option value="RSA">RSA</option><option value="HS256">HS256</option> </select>
									<span
										class="help-block m-b-none"></span>
								</div>
							</div>
							 
							
							<div class="form-group">
								<div class="col-sm-offset-2 col-sm-10">
									<a class="btn btn-sm btn-primary" type="button" href="javascript:Application.saveAppSystem('#addAppSystem')">保 存</a> 
									<a class="btn btn-sm btn-white" type="button" onclick="javascript:history.go(-1);">返 回</a>
								</div>
							</div>
						</form>
					</div>
				</div>
			</div>
		</div>
	</div>
	<%@ include file="/jsp/inc/js-link.inc"%>
	<script type="text/javascript" src="${pageContext.request.contextPath}/jsp/application/formvalidate.js"></script>
	
	
	<script type="text/javascript">
	   
	   
	  
	   
	   jQuery(document).ready(function() {
		   Application.initform("#addAppSystem","add");
       	
       });
	</script>
</body>
</html>