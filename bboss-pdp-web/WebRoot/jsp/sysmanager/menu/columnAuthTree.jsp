<%@ page session="false" language="java"
	contentType="text/html; charset=utf-8"%>
<%@ taglib uri="/WEB-INF/tld/pager-taglib.tld" prefix="pg"%>
<%@ taglib uri="/WEB-INF/tld/admin-taglib.tld" prefix="admin"%>
<link rel="stylesheet" href="${pageContext.request.contextPath}/assets/global/plugins/zTree_v3/css/zTreeStyle/zTreeStyle.css" type="text/css">

<div class="scroller" style="height: 400px">
	 
	<ul  class="ztree" id="column-authtree"></ul>
	 
<p></p>
</div>
<p></p>
<div class="row">
	<div class="col-md-offset-3 col-md-9">
		<button type="button" class="btn green btn-savecolumnauths">提交</button>		 
		<button type="button" class="btn default" data-dismiss="modal">取消</button>		 
	</div>
</div>	
<script
	src="${pageContext.request.contextPath}/assets/global/plugins/zTree_v3/js/jquery.ztree.core.js"
	
	type="text/javascript"></script>
		<script type="text/javascript" src="${pageContext.request.contextPath}/assets/global/plugins/zTree_v3/js/jquery.ztree.excheck.js"></script>
	
<script type="text/javascript">
jQuery(document).ready(function() {
	
	var setting = {
			view: {
				selectedMulti: false
			},
			check: {
				enable: true,
				chkboxType: { "Y" : "", "N" : "" }
			},
			async: {
				enable: true,
				url:"${pageContext.request.contextPath}/menu/getzkChildrens.page",
				autoParam:["id", "name=n", "level=lv"],
				otherParam:{roleId:'${roleId}',roleType:'${roleType}'},
				dataFilter: filter
			} 
		};
	

	function filter(treeId, parentNode, childNodes) {
		if (!childNodes) return null;
		for (var i=0, l=childNodes.length; i<l; i++) {
			childNodes[i].name = childNodes[i].name.replace(/\.n/g, '.');
		}
		return childNodes;
	}

	 
	var culumn_authtree =	$.fn.zTree.init($("#column-authtree",ModelDialog.getCurrentModal()), setting);
 
	 	$(".btn-savecolumnauths",ModelDialog.getCurrentModal()).bind("click",function(){
	 		var nodes = culumn_authtree.getCheckedNodes(true);
	 		
	 		$("#xxxxxxmenuid",ModelDialog.getParentModal()).val("ddddd");
	 		ModelDialog.getCurrentModal().modal('hide');
	 		console.log($("#xxxxxxmenuid",ModelDialog.getParentModal()).val())
	 		loadauto_resourcesauthsource();

	 	});
		PDP.initSlimScroll('.scroller',ModelDialog.getCurrentModal(),true);
	});
</script>	