<%@page import="org.frameworkset.security.session.SessionUtil"%>
<%@ page session="false" language="java"
	contentType="text/html; charset=utf-8"%>
<%
String sessionId = request.getRequestedSessionId();
String sid = SessionUtil.sign(sessionId, true);
out.print(sid);
%>	
