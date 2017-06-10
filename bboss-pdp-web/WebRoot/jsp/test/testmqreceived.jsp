
<%@page import="javax.jms.Message"%>
<%@page import="javax.jms.MessageListener"%>
<%@page import="org.frameworkset.mq.JMSReceiveTemplate"%>
<%@page import="org.frameworkset.mq.JMSTemplate"%>
<%@page import="org.frameworkset.spi.DefaultApplicationContext"%>
<%@page import="org.frameworkset.spi.remote.dubbo.TestProviderInf"%>
<%@page import="org.frameworkset.spi.BaseApplicationContext"%>
<%@ page language="java" pageEncoding="UTF-8"%>

<%
BaseApplicationContext context = DefaultApplicationContext.getApplicationContext("org/frameworkset/mq/manager-jmstemplate-test.xml");
JMSReceiveTemplate template = context.getTBeanObject("test.jms.receive.template",JMSReceiveTemplate.class);
try
{
    template.setMessageListener("atest",new MessageListener() {

        public void onMessage(Message arg0)
        {
            System.out.println("msg comming:"+arg0);
        }                
    });
    
}
catch (Exception e)
{
    
    template.stop();
}
finally
{

}
%>
<div>
<p>启动ok</p>
</div>