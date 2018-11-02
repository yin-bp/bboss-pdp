package org.frameworkset.platform.security.service.action;


import com.frameworkset.platform.admin.entity.SmOrganization;
import com.frameworkset.platform.admin.entity.SmUser;
import org.frameworkset.platform.security.service.CommonUserManagerInf;
import org.frameworkset.platform.security.service.entity.Result;
import org.frameworkset.util.annotations.ResponseBody;

import javax.jws.WebService;

@WebService(name = "commonuserService", targetNamespace = "com.frameworkset.platform.security.service.CommonuserService")
public class CommonUserControl implements CommonUserManagerInf {
	private CommonUserManagerInf commonUserManager;
	public CommonUserControl() {
		// TODO Auto-generated constructor stub
	}

	@Override
	public @ResponseBody
				Result createUser(SmUser user) {
					// TODO Auto-generated method stub
					return commonUserManager.createUser(user);
				}

	public @ResponseBody Result createTempUser(SmUser user) {
		return commonUserManager.createTempUser(user);
	}

	@Override
	public @ResponseBody Result updateUser(SmUser user) {
		return commonUserManager.updateUser(user);
	}

	@Override
	public @ResponseBody Result deleteUser(String useraccount) {
		return commonUserManager.deleteUser(useraccount);
	}

	@Override
	public @ResponseBody Result deleteUserByID(String userid) {
		return commonUserManager.deleteUserByID(userid);
	}

	@Override
	public @ResponseBody Result deleteUserByWorknumber(String worknumber) {
		return commonUserManager.deleteUserByWorknumber(worknumber);
	}

	@Override
	public @ResponseBody Result disableUser(String useraccount) {
		return commonUserManager.disableUser(useraccount);
	}

	@Override
	public@ResponseBody  Result disableUserByID(String userid) {
		return commonUserManager.disableUserByID(userid);
	}

	@Override
	public @ResponseBody Result disableUserByWorknumber(String worknumber) {
		return commonUserManager.disableUserByWorknumber(worknumber);
	}

	@Override
	public @ResponseBody Result updatePassword(String user_id, String password,String newPasswordSecond,String oldPassword) {
		return commonUserManager.updatePassword(  user_id,   password,newPasswordSecond,oldPassword);
	}

	@Override
	public @ResponseBody Result getUserByUserAccount(String user_account) {
		return commonUserManager.getUserByUserAccount(user_account);
	}

	@Override
	public @ResponseBody Result getUserById(String user_id) {
		return commonUserManager.getUserById(user_id);
	}

	@Override
	public @ResponseBody Result getUserByWorknumber(String user_worknumber) {
		return commonUserManager.getUserByWorknumber(user_worknumber);
	}

	@Override
	public @ResponseBody boolean exist(String useraccount) throws Exception {
		return commonUserManager.exist(useraccount);
	}

	@Override
	public @ResponseBody Result openUser(String useraccount) {
		return commonUserManager.openUser(useraccount);
	}

	@Override
	public @ResponseBody Result openUserByID(String userid) {
		return commonUserManager.openUserByID(userid);
	}

	@Override
	public @ResponseBody Result openUserByWorknumber(String worknumber) {
		return commonUserManager.openUserByWorknumber(worknumber);
	}

	@Override
	public Result buildUserOrgRelation(String userid, String orgid) {

		return commonUserManager.buildUserOrgRelation(  userid,   orgid);
	}

	@Override
	public Result addOrganization(SmOrganization org) {
		// TODO Auto-generated method stub
		return commonUserManager.addOrganization(  org);
	}

	@Override
	public Result addOrganizationWithEventTrigger(SmOrganization org, boolean triggerEvent) {
		// TODO Auto-generated method stub
		return commonUserManager.addOrganizationWithEventTrigger(  org,triggerEvent);
	}

	@Override
	public Result buildUserOrgRelationWithEventTrigger(String userid, String orgid, boolean broadcastevent ) {
		// TODO Auto-generated method stub
		return commonUserManager.buildUserOrgRelationWithEventTrigger(  userid,   orgid,   broadcastevent ) ;
	}


	@Override
	public Result updateOrganization(SmOrganization org, boolean broadcastevent) {
		// TODO Auto-generated method stub
		return commonUserManager.updateOrganization(  org,   broadcastevent);
	}

	@Override
	public Result disableOrganization(String orgid, boolean broadcastevent) {
		return commonUserManager.disableOrganization(  orgid,   broadcastevent);
	}

	@Override
	public Result enableOrganization(String orgid, boolean broadcastevent) {
		return commonUserManager.enableOrganization(  orgid,   broadcastevent);
	}

	@Override
	public Result deleteOrganization(String orgid, boolean triggerEvent) {
		// TODO Auto-generated method stub
		return commonUserManager.deleteOrganization(  orgid,   triggerEvent);
	}





}
