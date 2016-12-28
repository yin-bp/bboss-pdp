<%@ page session="false" language="java"
	contentType="text/html; charset=utf-8"%>
<%@ taglib uri="/WEB-INF/tld/pager-taglib.tld" prefix="pg"%>
<%@ taglib uri="/WEB-INF/tld/admin-taglib.tld" prefix="admin"%>
<link
	href="${pageContext.request.contextPath}/assets/global/plugins/jstree/dist/themes/default/style.min.css"
	rel="stylesheet" type="text/css" />
<div class="scroller" style="height: 450px">
	<div class="column-authtree">
		 						
	</div>
	 
	<p></p>
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
                      return '${pageContext.request.contextPath}/menu/getChildrens.page';
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
                "cascading " : "down",
                "three_state":false
              },
        });
	 
		
	});
</script>	