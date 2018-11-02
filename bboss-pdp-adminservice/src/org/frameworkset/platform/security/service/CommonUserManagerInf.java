package org.frameworkset.platform.security.service;


import com.frameworkset.platform.admin.entity.SmOrganization;
import com.frameworkset.platform.admin.entity.SmUser;
import org.frameworkset.platform.security.service.entity.Result;

import javax.jws.WebParam;
import javax.jws.WebResult;
import javax.jws.WebService;

@WebService(name = "commonuserService", targetNamespace = "com.frameworkset.platform.security.service.CommonuserService")
public interface CommonUserManagerInf {
	public @WebResult(name = "result", partName = "partResult")
	Result createTempUser(@WebParam(name = "user", partName = "partUser") SmUser user);
	public @WebResult(name = "result", partName = "partResult") Result createUser(@WebParam(name = "user", partName = "partUser") SmUser user);
	public @WebResult(name = "result", partName = "partResult") Result updateUser(@WebParam(name = "user", partName = "partUser") SmUser user);
	public @WebResult(name = "result", partName = "partResult") Result deleteUser(@WebParam(name = "useraccount", partName = "partUseraccount") String useraccount);
	public @WebResult(name = "result", partName = "partResult") Result deleteUserByID(@WebParam(name = "userid", partName = "partUserid") String userid);
	public @WebResult(name = "result", partName = "partResult") Result deleteUserByWorknumber(@WebParam(name = "worknumber", partName = "partWorknumber") String worknumber);
	
	public @WebResult(name = "result", partName = "partResult") Result disableUser(@WebParam(name = "useraccount", partName = "partUseraccount") String useraccount);
	public @WebResult(name = "result", partName = "partResult")  Result disableUserByID(@WebParam(name = "userid", partName = "partUserid") String userid);
	public @WebResult(name = "result", partName = "partResult") Result disableUserByWorknumber(@WebParam(name = "worknumber", partName = "partWorknumber") String worknumber);
	public @WebResult(name = "result", partName = "partResult") Result updatePassword(@WebParam(name = "user_id", partName = "partUser_id") String user_id,
																					  @WebParam(name = "password", partName = "partPassword") String password,
																					  @WebParam(name = "newPasswordSecond", partName = "partNewPasswordSecond") String newPasswordSecond,
																					  @WebParam(name = "oldPassword", partName = "partOldPassword") String oldPassword);
	public @WebResult(name = "result", partName = "partResult") Result getUserByUserAccount(@WebParam(name = "user_account", partName = "partUser_account") String user_account);
	public @WebResult(name = "result", partName = "partResult") Result getUserById(@WebParam(name = "userId", partName = "partUserId") String userId);
	public @WebResult(name = "result", partName = "partResult") Result getUserByWorknumber(@WebParam(name = "user_worknumber", partName = "partUser_worknumber") String user_worknumber);
	public @WebResult(name = "result", partName = "partResult") boolean exist(@WebParam(name = "useraccount", partName = "partUseraccount") String useraccount) throws Exception;

	public @WebResult(name = "result", partName = "partResult") Result openUser(@WebParam(name = "useraccount", partName = "partUseraccount") String useraccount) ;

	public @WebResult(name = "result", partName = "partResult") Result openUserByID(@WebParam(name = "userid", partName = "partUserid") String userid) ;

	public @WebResult(name = "result", partName = "partResult") Result openUserByWorknumber(@WebParam(name = "worknumber", partName = "partWorknumber") String worknumber) ;
	/**
	 *
	 * @param userid
	 * @param orgid
	 * @param broadcastevent 如果broadcastevent 为false则需要检查result.code == success,然后 EventUtil.sendUSER_ROLE_INFO_CHANGEEvent(userid) in your programe
	 * @return
	 */
	public @WebResult(name = "result", partName = "partResult") Result buildUserOrgRelationWithEventTrigger(@WebParam(name = "userid", partName = "partUserid") String userid, @WebParam(name = "orgid", partName = "partOrgid") String orgid, @WebParam(name = "broadcastevent", partName = "partBroadcastevent") boolean broadcastevent
																											);
	public @WebResult(name = "result", partName = "partResult") Result buildUserOrgRelation(@WebParam(name = "userid", partName = "partUserid") String userid, @WebParam(name = "orgid", partName = "partOrgid") String orgid);


	/**
	 *  常用字段：
 *
 * orgId,
 * orgName,
 * parentId,
 * code,
 * creatingtime,
 * orgnumber,
 * orgdesc,
 * remark5, 显示名称
 * orgTreeLevel,部门层级，自动运算
 * orgleader 部门主管
	 * @param org
	 * @return
	 */
	public @WebResult(name = "result", partName = "partResult") Result addOrganization(@WebParam(name = "org", partName = "partOrg") SmOrganization org);



	/**
	 *  更新字段：update td_sm_organization set org_name=#[orgName], code=#[code],  orgnumber=#[orgnumber],orgdesc=#[orgdesc] ,remark3=#[remark3], remark5=#[remark5]
  ORGLEADER=#[orgleader] where org_id=#[orgId]
 *
 * orgId,
 * orgName,
 * parentId,
 * code,
 * creatingtime,
 * orgnumber,
 * orgdesc,
 * remark5, 显示名称
 * orgTreeLevel,部门层级，自动运算
 * orgleader 部门主管
	 * @param org
	 * @return
	 */
	public @WebResult(name = "result", partName = "partResult") Result updateOrganization(@WebParam(name = "org", partName = "partOrg") SmOrganization org, @WebParam(name = "broadcastevent", partName = "partBroadcastevent") boolean broadcastevent);


	/**

 * 如果broadcastevent 为false则需要检查result.code == success,然后
			please execute send event yourself:
				EventUtil.sendORGUNIT_INFO_UPDATE(orgid);

	 * @param orgid
	 * @return
	 */
	public @WebResult(name = "result", partName = "partResult") Result disableOrganization(@WebParam(name = "org", partName = "partOrg") String orgid, @WebParam(name = "broadcastevent", partName = "partBroadcastevent") boolean broadcastevent);

	/**


 * 如果broadcastevent 为false则需要检查result.code == success,然后
			please execute send event yourself:
				EventUtil.sendORGUNIT_INFO_UPDATE(orgid);

	 * @param orgid
	 * @return
	 */
	public @WebResult(name = "result", partName = "partResult") Result enableOrganization(@WebParam(name = "org", partName = "partOrg") String orgid, @WebParam(name = "broadcastevent", partName = "partBroadcastevent") boolean broadcastevent);
	/**
	 *  常用字段：
 *
 * orgId,
 * orgName,
 * parentId,
 * code,
 * creatingtime,
 * orgnumber,
 * orgdesc,
 * remark5, 显示名称
 * orgTreeLevel,部门层级，自动运算
 * orgleader 部门主管
 * 如果broadcastevent 为false则需要检查result.code == success,然后
 *
			{
				EventUtil.sendORGUNIT_INFO_ADD(org.getOrgId());
			}
	 * @param org
	 * @return
	 */
	public @WebResult(name = "result", partName = "partResult") Result addOrganizationWithEventTrigger(@WebParam(name = "org", partName = "partOrg") SmOrganization org, @WebParam(name = "triggerEvent", partName = "partTriggerEvent") boolean triggerEvent);

	/**
	 * 物理删除机构
	 * @param orgid
	 * @param triggerEvent
	 *如果broadcastevent 为false则需要检查result.code == success,然后
 *
			{
				EventUtil.sendORGUNIT_DELETEEVENT(result.getOperationData(), orgid);
			}
	 * @return
	 */
	public @WebResult(name = "result", partName = "partResult") Result deleteOrganization(@WebParam(name = "org", partName = "partOrg") String orgid, @WebParam(name = "triggerEvent", partName = "partTriggerEvent") boolean triggerEvent);
	 
}
