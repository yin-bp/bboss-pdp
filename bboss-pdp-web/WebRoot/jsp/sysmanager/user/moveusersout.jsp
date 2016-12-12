<%@ page session="false" language="java"
	contentType="text/html; charset=utf-8"%>
<div class="portlet light portlet-fit portlet-datatable bordered">
	

    <div class="portlet-body">
			<div class="row">
				<div class="col-md-offset-2 col-md-10" style="overflow-x:auto;overflow-y:hidden;">
					<div class="tree-org-moveuserout"></div>
					<input type="hidden" name="selectdepart" />
				</div>
				
			</div>
    </div>
</div>

	
<script type="text/javascript">

	jQuery(document).ready(function() {	
		SysUser.initMoveUserAction();
	});
</script>