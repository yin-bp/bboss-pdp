<%@ page import="org.frameworkset.elasticsearch.ElasticSearchHelper" %>
<%@ page import="org.frameworkset.elasticsearch.client.ClientInterface" %>
<%@ page import="org.frameworkset.elasticsearch.entity.ESDatas" %>
<%@ page import="org.frameworkset.elasticsearch.scroll.ScrollHandler" %>
<%@ page import="java.util.List" %>
<%@ page import="java.util.Map" %>
<%@ page import="com.frameworkset.common.poolman.SQLExecutor" %>
<%@ page language="java" pageEncoding="UTF-8"%>

<%
	ClientInterface clientUtil = ElasticSearchHelper.getRestClientUtil();
	//get elasticsearch cluster state
	String result = clientUtil.executeHttp("_cluster/state?pretty",ClientInterface.HTTP_GET);

	//check indice twitter and index type tweet exist or not.
	boolean exist1 = clientUtil.existIndiceType("twitter","tweet");
	out.println("twitter  tweet type exist:"+exist1);
	//check indice twitter exist or not
	exist1 = clientUtil.existIndice("twitter");
	out.println("twitter exist:"+exist1);
	//count documents in indice twitter
	long count = clientUtil.countAll("demo");
	out.println(count);

	//Get All documents of indice twitter,DEFAULT_FETCHSIZE is 5000
	ESDatas<Map> esDatas = clientUtil.searchAll("demo", Map.class);

	//Get All documents of indice twitter,Set fetchsize to 10000, Using ScrollHandler to process each batch of datas.
	clientUtil.searchAll("demo",10000,new ScrollHandler<Map>() {
		public void handle(ESDatas<Map> esDatas) throws Exception {
			List<Map> dataList = esDatas.getDatas();
			System.out.println("TotalSize:"+esDatas.getTotalSize());
			if(dataList != null) {
				System.out.println("dataList.size:" + dataList.size());
			}
			else
			{
				System.out.println("dataList.size:0");
			}
			//do something other such as do a db query.
			SQLExecutor.queryList(Map.class,"select * from td_sm_user");
		}
	},Map.class);
    //Use slice parallel scoll query all documents of indice  twitter by 2 thread tasks. DEFAULT_FETCHSIZE is 5000
	//You can also use ScrollHandler to process each batch of datas on your own.
	clientUtil.searchAllParallel("demo", Map.class,2);
	out.println("searchAllParallel:ok");
%>
