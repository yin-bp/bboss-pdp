<%@ page session="false" language="java"
	contentType="text/html; charset=utf-8"%>

<%@ taglib uri="/WEB-INF/tld/admin-taglib.tld" prefix="admin"%>
<%@ taglib uri="/WEB-INF/tld/dictionary.tld" prefix="dict"%>
<!-- BEGIN PAGE LEVEL PLUGINS -->

<link
	href="${pageContext.request.contextPath}/assets/global/plugins/jstree/dist/themes/default/style.min.css"
	rel="stylesheet" type="text/css" />



<!-- BEGIN PAGE HEADER-->
<!-- BEGIN PAGE BAR -->
<div class="page-bar">
	<admin:menuposition />

</div>
 <div class="row">
	<div class="col-md-12" >&nbsp;<div class="alert alert-danger  display-hide">
                    <button class="close" data-close="alert"></button>
                    <span  class="msg"> 提示信息区 </span>
                </div></div>
 </div>
 
			<div class="row">
				<div class="col-md-3" >
					<div class="portlet light bordered">
                             <div class="portlet-title tabbable-line">
                                 <div class="caption">
                                     <i class="icon-pin font-yellow-crusta"></i>
                                     <span class="caption-subject bold font-yellow-crusta uppercase"> 系统菜单 </span>
                                    
                                 </div>
                                 
                             </div>      
                            <div class="portlet-body portlet-body-columntree">
                            	 <div  class="scroller" style="height:450px">
                                	<div class="tree-syscolumn"  ></div>
                                	<p></p>
                                </div>	
                                
                            </div>
                        </div>
					
				</div>
				<div class="col-md-9">
					<div class="row">
						 <div class="col-md-12">
						 	请选择左侧菜单
                         </div>
					</div>
				</div>
			</div>
		

 
 

<script
	src="${pageContext.request.contextPath}/assets/global/plugins/jstree/dist/jstree.min.js"
	type="text/javascript"></script>
 
<!-- END PAGE LEVEL PLUGINS -->

<script type="text/javascript">
	
	jQuery(document).ready(function() {
		$(".portlet-body-columntree .tree-syscolumn").jstree({
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
                      return { 'parent' : node.id };
                    }
                }
            },
            "types" : {
                "default" : {
                    "icon" : "fa fa-folder icon-state-warning icon-lg"
                },
                "system":{
                	 "icon" : "fa fa-folder icon-state-warning icon-lg"
                }
            },            
            "plugins" : [   "types" ],
           
        });
		 $('.portlet-body-columntree .tree-syscolumn').bind("activate_node.jstree", function (obj, e) {
			    // 处理代码
			    // 获取当前节点
			    var currentNode = e.node;
			    //console.table(currentNode);
			    //console.table(obj)
			    //console.log(currentNode);
			    //var departid = currentNode.id;
			    
			    if(currentNode.parent == "#"){
			    	
			    	
			    	//departid = '0';
			    }
			    
			  
			}); 
		PDP.initSlimScroll('.scroller',true);
		
	});
</script>

<!-- END CONTENT BODY -->