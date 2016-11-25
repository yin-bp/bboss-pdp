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
            <li>应用接入管理</li>
            <li>详情</li>
        </ol>
    </div>
</div>

<div class="wrapper wrapper-content animated fadeInRight">
    <div class="row">
        <div class="col-sm-12">
            <div class="ibox">
                <div class="ibox-title">
                    <h5>应用详情</h5>
                </div>
                   <div class="ibox-content">
                    <div  class="form-horizontal" >	
					    <div class="form-group">
						    <label class="col-sm-2 control-label">应用编号:</label>
						    <div class="col-sm-4">
								<label class="control-label" >${application.appCode}</label>
							</div>
                        </div>
						<div class="form-group">
						    <label class="col-sm-2 control-label">应用名称:</label>
						    <div class="col-sm-4">
								<label class="control-label" style="">${application.appName}</label>	
							</div>				   
                        </div>
                        <div class="form-group">
						    <label class="col-sm-2 control-label">口令明文:</label>
						    <div class="col-sm-4">
								<label class="control-label" style="">${application.appSecretText}</label>	
							</div>					   
                        </div> 

                       
						<div class="form-group">	
							<label class="col-sm-2 control-label">有效时长（毫秒）:</label>
							<div class="col-sm-4">
                            	<label class="control-label">${application.ticketlivetimes}</label>
                            </div>	
                        </div> 
                        
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
						
                    </div>
                    <div><a href="${pageContext.request.contextPath}/application/downcafile.page?appCode=${application.appCode}" id="downcafile" target="_downcafile"></a>
                    	<button class="btn btn-primary " type="button" id="dowcabutton" onclick="javascript:downloadCredentials();">下载证书</button>	
                    	<button class="btn btn-second " type="button" onclick="javascript:history.go(-1);">返回</button>
                    </div>
                </div>
            </div>
        </div>
    </div>   
</div>
<%@ include file="/jsp/inc/js-link.inc"%>
<script type="text/javascript">
	 
	
	function downloadCredentials(){
		var downcafile = document.getElementById('downcafile');
		downcafile.click();
	  
	}
</script>
</body>
</html>