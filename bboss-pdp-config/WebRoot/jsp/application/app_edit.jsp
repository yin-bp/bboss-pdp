<%@ page contentType="text/html; charset=utf-8"%>
<%@ include file="/jsp/inc/tld.inc"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="utf-8">
<meta name="viewport" content="width=device-width, initial-scale=1.0">
<%@ include file="/jsp/inc/css-link.inc"%>
</head>
<body class="gray-bg">
<div class="row wrapper border-bottom white-bg sdp-head">
    <div class="col-sm-12">
        <ol class="breadcrumb">
            <li>位置: 系统管理</li>
            <li>应用接入管理></li>
            <li>编辑</li>
        </ol>
    </div>
</div>

<div class="wrapper wrapper-content animated fadeInRight">
    <div class="row">
        <div class="col-sm-12">
            <div class="ibox">
                <div class="ibox-title">
                    <h5>应用接入编辑</h5>
                </div>
              <div class="ibox-content" >
                    <form id = "editAppSystem" method="post" action="saveAppSystem?flag=2" class="form-horizontal" >
                      <div class="form-group" style = "display:none">
						    <label class="col-sm-2 control-label">ID:</label>
						    <div class="col-sm-4">
								<input type="text" class="form-control" name = "appId" value ="${application.appId}" >
							</div>					   
                        </div> 
					     <div class="form-group">
						    <label class="col-sm-2 control-label">应用编码:</label>
						    <div class="col-sm-4">
								<input type="text" class="form-control" name = "appCode" readonly value ="${application.appCode}">
							</div>
                        </div>
						<div class="form-group">
						    <label class="col-sm-2 control-label">应用名称:</label>
						    <div class="col-sm-4">
								<input type="text" class="form-control" name = "appName"  readonly value ="${application.appName}">
							</div>				   
                        </div>
                        <div class="form-group">
						    <label class="col-sm-2 control-label">访问口令:</label>
						    <div class="col-sm-4">
								<input type="text" class="form-control" name = "appSecretText" id="appSecret" value = "${application.appSecretText}">
								<a class="btn btn-sm btn-primary" type="button" href="javascript:Application.getSystemSecret()">生成口令</a>
							</div>					   
                        </div> 
                      <div class="form-group">
								<label class="col-sm-2 control-label">确认口令:</label>
								<div class="col-sm-4">
									<input type="text" class="form-control" name = "appSecretAck" id="appSecretAck" value = "${application.appSecretText}"><span
										class="help-block m-b-none"><font id="re_secret_font" color="red">*</font> </span>
								</div>
							</div>
                        
                        <div class="form-group">	
							<label class="col-sm-2 control-label">有效时长（毫秒）:</label>
							<div class="col-sm-4">
                            	<input type="text" class="form-control" name = "ticketlivetimes" value ="${application.ticketlivetimes}">
                            </div>								                       
                        </div>
						<div class="form-group">
								<label class="col-sm-2 control-label">证书算法:</label>
								<div class="col-sm-4">
									<select class="form-control input-sm" name = "certAlgorithm" >
									<option value="RSA" <pg:equal actual="${application.certAlgorithm}" value="RSA">selected</pg:equal>>RSA</option>
									<option value="HS256" <pg:equal actual="${application.certAlgorithm}" value="HS256">selected</pg:equal>>HS256</option> 
									</select>
									<span
										class="help-block m-b-none"></span>
								</div>
							</div>
							 
                        <pg:equal actual="${application.certAlgorithm}" value="RSA" evalbody="true">
	                        <pg:yes>
		                         <div class="form-group">	
									<label class="col-sm-2 control-label">签名公钥:</label>
									<div class="col-sm-4">
		                            	<textarea cols="100" class="control-label">${application.publicKey}</textarea>
		                            </div>	
		                        </div>
		                        <div class="form-group">	
									<label class="col-sm-2 control-label">签名私钥:</label>
									<div class="col-sm-4">
		                            	<textarea cols="100" class="control-label">${application.privateKey}</textarea>
		                            </div>	
		                        </div> 
	                        </pg:yes>
	                        <pg:no>
	                        	<div class="form-group">	
									<label class="col-sm-2 control-label">签名证书:</label>
									<div class="col-sm-4">
		                            	<textarea cols="100" class="control-label">${application.publicKey}</textarea>
		                            </div>	
		                        </div>
	                        </pg:no>
                        </pg:equal>
                        <div class="form-group">
							<div class="col-sm-offset-2 col-sm-10">
								<a class="btn btn-sm btn-primary" type="button" href="javascript:Application.saveAppSystem('#editAppSystem')">保 存</a> 
								<a class="btn btn-sm btn-white" type="button" href="javascript:history.go(-1);">返 回</a>
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
	   Application.initform("#editAppSystem","update");
	
});
</script>
</body>
</html>