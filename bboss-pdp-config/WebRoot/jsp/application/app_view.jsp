<%@ page contentType="text/html; charset=utf-8"%>
<%@ include file="/WEB-INF/jsp/inc/tld.inc"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="utf-8">
<meta name="viewport" content="width=device-width, initial-scale=1.0">
<%@ include file="/WEB-INF/jsp/inc/css-link.inc"%>
<%@ include file="/WEB-INF/jsp/inc/js-link.inc"%>
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
								<label class="control-label" >${appSystem.systemId}</label>
							</div>
                        </div>
						<div class="form-group">
						    <label class="col-sm-2 control-label">应用名称:</label>
						    <div class="col-sm-4">
								<label class="control-label" style="">${appSystem.systemName}</label>	
							</div>				   
                        </div>
                        <div class="form-group">
						    <label class="col-sm-2 control-label">口令明文:</label>
						    <div class="col-sm-4">
								<label class="control-label" style="">${appSystem.systemSecret}</label>	
							</div>					   
                        </div> 

                        <div class="form-group">
                            <label class="col-sm-2 control-label" >口令密文:</label>
                            <div class="col-sm-4">
								<label class="control-label" >${appSystem.systemSecretText}</label>
							</div>	
                        </div>   
						<div class="form-group">	
							<label class="col-sm-2 control-label">失效周期:</label>
							<div class="col-sm-4">
                            	<label class="control-label">${appSystem.tickettime}</label>
                            </div>	
                        </div> 
                        <div class="form-group">	
							<label class="col-sm-2 control-label">票据签名:</label>
							<div class="col-sm-4">
                            	<label class="control-label">${appSystem.needsign}</label>
                            </div>	
                        </div>
                       <%--  <div class="form-group">	
							<label class="col-sm-2 control-label">签名公钥:</label>
							<div class="col-sm-4">
                            	<label class="control-label">${appSystem.publicKey}</label>
                            </div>	
                        </div>
                        <div class="form-group">	
							<label class="col-sm-2 control-label">签名私钥:</label>
							<div class="col-sm-4">
                            	<label class="control-label">${appSystem.privateKey}</label>
                            </div>	
                        </div> --%>
						
                    </div>
                    <div>
                    	<button class="btn btn-primary " type="button" onclick="javascript:downloadCredentials();">下载证书</button>	
                    	<button class="btn btn-primary " type="button" onclick="javascript:history.go(-1);">返回</button>
                    </div>
                </div>
            </div>
        </div>
    </div>   
</div>
<script type="text/javascript">
	function downloadCredentials(){
		$.ajax({
			type: "POST",  
            url: "/user/update",  
            contentType: "application/json;charset=UTF-8",  
            data: getJsonFromInputs(["userId", "nickname", "realname", "email", "password"]),  
            dataType: "json",  
            success: function (data) {  
                $("#feedback").html("信息更新成功，点击<a href='/user/list'>此处</a>返回。");  
            }  
		})
	}
</script>
</body>
</html>