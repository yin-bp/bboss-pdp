/**
 * 
 */
package com.frameworkset.platform.admin.action;

import org.frameworkset.util.annotations.PagerParam;
import org.frameworkset.web.servlet.ModelMap;

import com.frameworkset.platform.admin.entity.QueryUserCondition;
import com.frameworkset.platform.admin.service.SmUserService;
import com.frameworkset.util.ListInfo;

/**
 * @author yinbp
 *
 * @Date:2017-01-10 23:24:17
 */
public class QueryUserController {
	private SmUserService smUserService;
	/**
	 * 
	 */
	public QueryUserController() {
		// TODO Auto-generated constructor stub
	}
	public String queryUsers(QueryUserCondition condition,@PagerParam(name = PagerParam.SORT, defaultvalue = "USER_NAME") String sortKey,
			@PagerParam(name = PagerParam.DESC, defaultvalue = "false") boolean desc,
			@PagerParam(name = PagerParam.OFFSET) long offset,
			@PagerParam(name = PagerParam.PAGE_SIZE, defaultvalue = "10") int pagesize,ModelMap model){
		if(condition.getUserAttr() != null && !condition.getUserAttr().equals("")){
			condition.setUserAttr("%"+condition.getUserAttr()  + "%");
		}
		ListInfo users = smUserService.queryUsers(  condition,  offset,pagesize);
		model.addAttribute("users", users);
		return "path:queryUsers";
	}
	
	
	public String index(){
		return "path:index";
	}

}
