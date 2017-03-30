
<%@page import="org.frameworkset.spi.remote.dubbo.TestProviderInf"%>
<%@page import="org.frameworkset.spi.BaseApplicationContext"%>
<%@page import="org.frameworkset.web.servlet.support.WebApplicationContextUtils"%>
<%@ page language="java" pageEncoding="UTF-8"%>

<%
BaseApplicationContext context = WebApplicationContextUtils.getWebApplicationContext();
TestProviderInf testProviderInf = context.getTBeanObject("testdubboservice", TestProviderInf.class);
String result = (testProviderInf.test("hi,dubbo!"));
%>
<div>
<p>dubbo服务执行结果:<%=result %></p>
</div>