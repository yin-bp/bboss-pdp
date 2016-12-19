<%@ page session="false" language="java"
	contentType="text/html; charset=utf-8"%>
<%@ taglib uri="/WEB-INF/tld/pager-taglib.tld" prefix="pg" %>

<!-- 
	描述:角色管理管理列表界面。
	作者:yinbp
	版权:bboss
	版本:v1.0 
	日期:2016-12-15 17:06:09
-->

<pg:pager scope="request"  data="roles" desc="false" isList="false" containerid=".portlet-dictitemlist">	
	
	
<table class="table table-striped table-hover table-bordered table-dictitemlist">
      <thead>
          <tr>
              <th> name </th>
              <th> value </th>
             
              <th> 编辑 </th>
              <th> 删除 </th>
          </tr>
      </thead>
      <tbody>
          <tr>
              <td> alex </td>
              <td> Alex Nilson </td>
              
              <td>
                  <a class="edit" href="javascript:;"> Edit </a>
              </td>
              <td>
                  <a class="delete" href="javascript:;"> Delete </a>
              </td>
          </tr>
          <tr>
              <td> lisa </td>
              <td> Lisa Wong </td>
               
              <td>
                  <a class="edit" href="javascript:;"> Edit </a>
              </td>
              <td>
                  <a class="delete" href="javascript:;"> Delete </a>
              </td>
          </tr>
         
      </tbody>
  </table>
<div class="pages"><input type="hidden" value="<pg:querystring/>" /><pg:index tagnumber="10" sizescope="5,10,20,50,100"/></div>
</pg:pager>

