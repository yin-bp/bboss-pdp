
<%@page import="org.frameworkset.mq.JMSTemplate"%>
<%@page import="org.frameworkset.spi.DefaultApplicationContext"%>
<%@page import="org.frameworkset.spi.remote.dubbo.TestProviderInf"%>
<%@page import="org.frameworkset.spi.BaseApplicationContext"%>
<%@ page language="java" pageEncoding="UTF-8"%>

<%
BaseApplicationContext context = DefaultApplicationContext.getApplicationContext("org/frameworkset/mq/manager-jmstemplate-test.xml");
JMSTemplate template = context.getTBeanObject("test.jmstemplate",JMSTemplate.class);
try
{
	for(int i =0 ; i < 10; i ++)
		template.send("atest", "ahello "+ i);
    
}
catch (Exception e)
{
	// TODO Auto-generated catch block
	e.printStackTrace();
}
	finally
{
   // template.stop();
}
%>
<div>
<p>服务执行结果:发送成功</p>
</div>