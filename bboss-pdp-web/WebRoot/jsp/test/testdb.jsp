<%@ page import="com.frameworkset.common.poolman.PreparedDBUtil" %><%
PreparedDBUtil db = new PreparedDBUtil();

db.executeSelect("select * from td_sm_user where user_name='sss|aa'");
	db.executeSelect("select * from td_sm_user where user_name='(O.CREATE_TIME,YYYY-MM-DD HH24:MI:SS)'");


%>