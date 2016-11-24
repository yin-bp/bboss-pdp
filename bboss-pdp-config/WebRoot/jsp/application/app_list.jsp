<%@ page contentType="text/html; charset=utf-8"%>
<%@ include file="/jsp/inc/tld.inc"%>
<!DOCTYPE html>
<html>

<head>
    <meta charset="utf-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>应用接入管理</title>
    <%@ include file="/jsp/inc/css-link.inc"%>
	
	
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
                    <h5>查询条件</h5>
                </div>
                <div class="ibox-content">
                    <form method="post" class="form-horizontal" id="searchAppSystemForm" action="searchAppSystem">
                        <div class="form-group">
                            <label class="col-sm-1 control-label">应用编号</label>
                            <div class="col-sm-3">
                                <input type="text" class="form-control input-sm" id="appCode" name="appCode">
                            </div>
                            <label class="col-sm-1 control-label">应用名称</label>
                            <div class="col-sm-3">
                                <input type="text" class="form-control input-sm" id="appName" name="appName">
                            </div>
                        </div>
                        <div class="form-group">
                            <div class="col-sm-offset-1 col-sm-11">
                                <a class="btn btn-xs btn-primary" type="button" href="javascript:searchAppSystem()">查 询</a>
                                <a class="btn btn-xs btn-white" type="button" href="${pageContext.request.contextPath}/application/toAddApplication.page">新 增</a>
                            </div>
                        </div>
                    </form>
                </div>
            </div>
        </div>
    </div>
    <div class="row">
        <div class="col-sm-12">
            <div class="ibox">
                <div class="ibox-title">
                    <h5>查询结果</h5>
                </div>
                <div class="ibox-content">
                    <table class="table table-bordered" id="appSystem_table">
                    
                    </table>
                    
                    <div class="sdp-tool">
                        <div class="sdp-left"></div>
                        <div class="sdp-right">
                            <div class="sdp-page-before">
                              <div id="pageBean"></div>
                            </div>
                            
                        </div>
                    </div>
                </div>
            </div>
        </div>
    </div>
</div>


<%@ include file="/jsp/inc/js-link.inc"%>
<script type="text/javascript">
	$(function(){
		$("#appSystem_table").datagrid({
			url: "${pageContext.request.contextPath}/application/queryListInfoApplications.page",
            type: "post",
            data: {},
            table:{ //表格相关配置
            	id: "appSystem_table",
                head:[{
                    item: "appCode",
                    name: "应用编号"
                },{
                    item: "appName",
                    name: "应用名称"
                },{
                    item: "appSecret",
                    name: "口令明文"
                },{
                    item: "appSecretText",
                    name: "口令密文"
                },{
                    item: "ticketlivetimes",
                    name: "有效周期"
                }
                ],
                showBtns: true,
                btns:[{
                    name: "查看",
                    todo: function(row,index){
                    	var appId=row.appId;
            			window.location.href = "${pageContext.request.contextPath}/application/getApplication.page?appId="+appId;
                    }
                },{
                    name: "编辑",
                    todo: function(row,index){
                    	var appId=row.appId;
            			window.location.href = "${pageContext.request.contextPath}/application/toUpdateApplication.page?appId="+appId;
                    }

                }]
            },
            page:{ //分页工具
                enable: true,
                id: "pageBean",
                pageSize: 10
            }
        });
	});
	function addApp()
	{
		window.location.href = "${pageContext.request.contextPath}/application/getApplication.page?appId="+appId;
	}
	function searchAppSystem(){
		var appName = $("#appName").val();
		var appCode = $("#appCode").val();
		$("#appSystem_table").datagrid({
			url: "${pageContext.request.contextPath}/application/queryListInfoApplications.page",
            type: "post",
            data: {"appName":appName,"appCode":appCode},
            table:{ //表格相关配置
            	id: "appSystem_table",
                head:[{
                    item: "appCode",
                    name: "应用编号"
                },{
                    item: "appName",
                    name: "应用名称"
                },{
                    item: "appSecret",
                    name: "口令明文"
                },{
                    item: "appSecretText",
                    name: "口令密文"
                },{
                    item: "ticketlivetimes",
                    name: "有效周期"
                }
                ],
                showBtns: true,
                btns:[{
                    name: "查看",
                    todo: function(row,index){
                    	var appId=row.appId;
            			window.location.href = "${pageContext.request.contextPath}/application/getApplication.page?appId="+appId;
                    }
                },{
                    name: "编辑",
                    todo: function(row,index){
                    	var appId=row.appId;
            			window.location.href = "${pageContext.request.contextPath}/application/toUpdateApplication.page?appId="+appId;
                    }

                }]
            },
            page:{ //分页工具
                enable: true,
                id: "pageBean",
                pageSize: 10
            }
		});
	}
</script>
</body>
</html>