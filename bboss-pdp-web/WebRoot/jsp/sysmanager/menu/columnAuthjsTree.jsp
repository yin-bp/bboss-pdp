<%@ page session="false" language="java"
	contentType="text/html; charset=utf-8"%>
<%@ taglib uri="/WEB-INF/tld/pager-taglib.tld" prefix="pg"%>
<%@ taglib uri="/WEB-INF/tld/admin-taglib.tld" prefix="admin"%>
<link
	href="${pageContext.request.contextPath}/assets/global/plugins/jstree/dist/themes/default/style.min.css"
	rel="stylesheet" type="text/css" />
<div class="scroller" style="height: 400px">
	<div class="column-authtree">
		 						
	</div>
	 
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
	src="${pageContext.request.contextPath}/assets/global/plugins/jstree/dist/jstree.min.js"
	type="text/javascript"></script>
	
<script type="text/javascript">
jQuery(document).ready(function() {
	
		$(".column-authtree",ModelDialog.getCurrentModal()).jstree({
            "core" : {
                "themes" : {
                    "responsive": true
                }, 
                // so that create works
                "check_callback" : true,
                'data' : {
                    'url' : function (node) {
                      return '${pageContext.request.contextPath}/menu/getzkChildrens.page';
                    },
                    'data' : function (node) {
                      return { 'parent' : node.id ,roleId:'${roleId}',roleType:'${roleType}'};
                    }
                }
            },
            "types" : {
                "default" : {
                    "icon" : "fa fa-folder icon-state-warning icon-lg"
                }
            },            
            "plugins" : [   "types","checkbox" ],
            "checkbox" : {
                "cascade " : "undetermined",
                "three_state":false
              },
        });
	 	$(".btn-savecolumnauths",ModelDialog.getCurrentModal()).bind("click",function(){
	 		
	 		$("#xxxxxxmenuid",ModelDialog.getParentModal()).val("ddddd");
	 		ModelDialog.getCurrentModal().modal('hide');
	 		console.log($("#xxxxxxmenuid",ModelDialog.getParentModal()).val())
	 		loadauto_resourcesauthsource();

	 	});
		PDP.initSlimScroll('.scroller',ModelDialog.getCurrentModal(),true);
	});
</script>	