<%@ page contentType="text/html; charset=utf-8"%>
<%@ include file="/jsp/inc/tld.inc" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="utf-8">
<meta name="viewport" content="width=device-width, initial-scale=1.0">
<%@ include file="/jsp/inc/css-link.inc"%>
<%@ include file="/jsp/inc/js-link.inc"%>
</head>
<body class="fixed-sidebar full-height-layout gray-bg" style="overflow: hidden">
			
	<form id="loginForm" name="loginForm" action="${pageContext.request.contextPath}/login.page"  method="post">  
	     <input id="userName" name = "userName" type="text" placeholder="用户名"><br/>
	     <input id="password" name = "password" type="password" placeholder="密码"><br/>
	     <input id="flag" name = "flag" type="hidden" >
	     <input type="button" value="提交" onclick="login()"/>  
	</form>  
	
	<script type="text/javascript">
	function login(){
		var loginName = $("#userName").val();
		var password = $("#password").val();
		if(loginName == ""){
			alert("用户名为空");
			return false;
		}
		if(password == ""){
			alert("密码为空");
			return false;
		}
		$("#loginForm").submit();
	}
	</script>
</body>
</html>