package org.lanqiao.core;

import java.util.List;

/**
 * 工具类
 * @author qilixiang
 *
 */
public class Utils {
	public static boolean isExistent(String email){
		boolean isExistent = false;
		List<User> list = JDBCOperationImpl.me.select();
		for (User user : list) {
			if((email.equals(user.getEmail()))){
				isExistent = true;
			}
		}
		return isExistent;
	}
}
