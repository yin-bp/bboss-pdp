<%@ page session="false" language="java"
	contentType="text/html; charset=utf-8"%>

    <div class="table-container">            
            <table class="table table-striped table-bordered table-hover table-checkable  table-header-fixed" id="datatable_userlist">
                <thead>
                    <tr role="row" class="heading">  
                    <th width="10%">  </th>                     
                        <th width="10%"> 账号 </th>
                        <th width="10%"> 名称 </th>
                        <th width="10%"> 工号 </th>
                        <th width="10%"> 电话 </th>                         
                        <th width="10%"> 类型 </th>                         
                        <th width="15%"> 部门</th> 
                        <th width="15%"> 操作</th>                        
                    </tr>
                    <tr role="row" class="filter">
                        <td> <button class="btn btn-xs red btn-outline filter-cancel">
                                <i class="fa fa-times"></i> 重置</button> </td>
                        <td>
                            <input type="text" class="form-control form-filter input-xs" name="userName"> </td>
                        
                        <td>
                            <input type="text" class="form-control form-filter input-xs" name="userRealname"> </td>
                        <td>
                            <input type="text" class="form-control form-filter input-xs" name="userWorknumber"> </td>
                          <td>
                            <input type="text" class="form-control form-filter input-xs" name="userMobiletel1"> </td>
                        
                          <td>
                            <select name="userType" class="form-control form-filter input-xs">
                                <option value=""></option>
                                <option value="0">系统用户</option>
                                <option value="1">域用户</option>
                                <option value="2">第三方用户</option>
                                
                            </select> </td>
                            <td>     
                       
                        <select name="recursive" class="form-control form-filter input-xs">
                                <option value="">查询方式...</option>
                                <option value="0">本机构</option>
                                <option value="1">含子机构</option>
                                <option value="2">所有机构</option>
                                
                            </select> 
                             </td>
                          
                     
                        <td>
                             
                                <button class="btn btn-xs green btn-outline filter-submit margin-bottom">
                                    <i class="fa fa-search"></i> 查询</button>
                             
                           
                        </td>
                    </tr>
                </thead>
                <tbody> </tbody>
            </table>
        </div>
 
	
