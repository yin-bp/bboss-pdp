package org.frameworkset.platform.entity;

public class UserUtil {
	private static String[][] users = new String[][]{
		{"yinbp","123456","尹标平"},
		{"zhangsan","123456","张三"},
		{"lisi","123456","李四"},
		{"admin","123456","超级管理员"},
		{"wangwu","123456","王五"}
	};
	public UserUtil() {
		// TODO Auto-generated constructor stub
	}
	
	public static User getUser(String userAccount)
	{
		for(int i = 0; i < users.length; i ++)
		{
			String[] user_ = users[i];
			if(!user_[0].equals(userAccount))
				continue;
			User user = new User();
			user.setUserAccount(userAccount);
			user.setLeader("jackson");
			user.setUserSex("female");
			user.setUserName(user_[2]);
			user.setPassword("123456");
			user.setUserId(userAccount);
			return user;
		}
		return null;
	}
	
	 

}
