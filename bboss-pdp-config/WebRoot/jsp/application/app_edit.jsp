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
								<input type="text" class="form-control" name = "id" value ="${appSystem.id}" >
							</div>					   
                        </div> 
					     <div class="form-group">
						    <label class="col-sm-2 control-label">应用编号:</label>
						    <div class="col-sm-4">
								<input type="text" class="form-control" name = "systemId" readonly value ="${appSystem.systemId}">
							</div>
                        </div>
						<div class="form-group">
						    <label class="col-sm-2 control-label">应用名称:</label>
						    <div class="col-sm-4">
								<input type="text" class="form-control" name = "systemName"  readonly value ="${appSystem.systemName}">
							</div>				   
                        </div>
                        <div class="form-group">
						    <label class="col-sm-2 control-label">口令明文:</label>
						    <div class="col-sm-4">
								<input type="text" class="form-control" name = "systemSecret" id="systemSecret" value = "${appSystem.systemSecret}">
								<a class="btn btn-sm btn-primary" type="button" href="javascript:getSystemSecret()">生成口令</a>
							</div>					   
                        </div> 
                        <div class="form-group">
                            <label class="col-sm-2 control-label" >口令密文:</label>
                            <div class="col-sm-4">
								<input type="text" class="form-control input-sm"  name = "systemSecretText" id="systemSecretText" value = "${appSystem.systemSecretText}">
							</div>	
                        </div>   
                        
                        <div class="form-group">	
							<label class="col-sm-2 control-label">失效周期:</label>
							<div class="col-sm-4">
                            	<input type="text" class="form-control" name = "tickettime" value ="${appSystem.tickettime}">
                            </div>								                       
                        </div>
						
                        <div class="form-group">	
							<label class="col-sm-2 control-label">状态:</label>
							<div class="col-sm-4">
                            	<input type="text" class="form-control" name = "state" value ="${appSystem.state}">
                            </div>								                       
                        </div>
                        <div class="form-group">	
							<label class="col-sm-2 control-label">票据签名:</label>
							<div class="col-sm-4">
                            	<input type="text" class="form-control" name = "needsign" value ="${appSystem.needsign}">
                            </div>								                       
                        </div> 
                        <%-- <div class="form-group">	
							<label class="col-sm-2 control-label">签名公钥:</label>
							<div class="col-sm-4">
                            	<input type="text" class="form-control" name = "publicKey" value ="${appSystem.publicKey}">
                            </div>								                       
                        </div> 
                        <div class="form-group">	
							<label class="col-sm-2 control-label">签名私钥:</label>
							<div class="col-sm-4">
                            	<input type="text" class="form-control" name = "privateKey" value ="${appSystem.privateKey}">
                            </div>								                       
                        </div> --%>  
                        <div class="form-group">
							<div class="col-sm-offset-2 col-sm-10">
								<a class="btn btn-sm btn-primary" type="button" href="javascript:addAppSystem()">保 存</a> 
								<a class="btn btn-sm btn-white" type="button" href="javascript:history.go(-1);">返 回</a>
							</div>
						</div> 
                    </form>					  
                </div>
            </div>
        </div>
    </div>   
</div>
<script type="text/javascript">
function addAppSystem(){
	   $("#editAppSystem").submit();
}

function getSystemSecret(){
	   $.ajax({
			type : "POST",
			url : "getSystemSecret",
			async : false,
			success : function(responseText) {
				$("#systemSecret").val(responseText);
				$("#systemSecretText").val(responseText);
			}
		});
}
</script>
</body>
</html>