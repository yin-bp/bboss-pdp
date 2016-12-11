<%@ page session="false" language="java"
	contentType="text/html; charset=utf-8"%>
<%@ taglib uri="/WEB-INF/tld/pager-taglib.tld" prefix="pg" %>
     
		
		<div class="row">
			<div class=" col-md-12">
		        <form action="#" >
		        	 <div class="form-actions">
                        <div class="row">
							<div class="col-md-offset-4 col-md-8">
								<button type="button"  class="btn green sys_modifyUser_button">保存顺序</button>
								
								<button type="reset" class="btn default" data-dismiss="modal">取消</button>
							</div>
						</div>
                    </div>
                   <div class="form-body">       
		            <table class="table table-striped table-bordered" cellspacing="0" width="100%" >
		                <thead>
		                    <tr >
		                        <th width="10%"> 序号 </th>
		                        <th width="10%"> 账号 </th>
		                        <th width="10%"> 名称 </th>
		                        <th width="10%"> 工号 </th>
		                        <th width="10%"> 电话 </th>
		                        <th width="10%"> 性别 </th>
		                        <th width="10%"> 类别</th>
		                        <th width="10%"> 状态 </th>
		                        <th width="15%"> 组织</th>		                      
		                       
		                    </tr>
		                   
		                </thead>
		                <tfoot>
					        <tr>
					             <th width="10%"> 序号 </th>
		                        <th width="10%"> 账号 </th>
		                        <th width="10%"> 名称 </th>
		                        <th width="10%"> 工号 </th>
		                        <th width="10%"> 电话 </th>
		                        <th width="10%"> 性别 </th>
		                        <th width="10%"> 类别</th>
		                        <th width="10%"> 状态 </th>
		                        <th width="15%"> 组织</th>		
					        </tr>
					    </tfoot>
		                <tbody>
		                	<pg:list actual="${users }">
		                	 <tr>
		                	 	<td> <pg:rowid increament="1" offset="true"/> </td>
                                 <td> <pg:cell colName="userName"/> <input type="hidden" name="userId" value="<pg:cell colName="userId"/>"></td>
                                 <td> <pg:cell colName="userRealname"/> </td>
                                 <td> <pg:cell colName="userWorknumber"/> </td>
                                 <td> <pg:cell colName="userMobiletel1"/> </td>
                                 <td> <pg:cell colName="userSex"/> </td>
                                 <td> <pg:cell colName="userType"/> </td>
                                 <td> <pg:cell colName="userIsvalid"/> </td>
                                 <td> <pg:cell colName="departName"/> </td>
                             </tr>
                             </pg:list>
		                 </tbody>
		            </table>
		            </div>
                    <div class="form-actions">
                        <div class="row">
							<div class="col-md-offset-4 col-md-8">
								<button type="button"  class="btn green sys_modifyUser_button">保存顺序</button>
								
								<button type="reset" class="btn default" data-dismiss="modal">取消</button>
							</div>
						</div>
                    </div>
                </form>
		      </div>
		     
		</div>

	
<script type="text/javascript">
	jQuery(document).ready(function() {
		
		SysUser.initUserOrderTable(<pg:notempty actual="${users }">true</pg:notempty>);
		
	});
</script>
</body>
</html>