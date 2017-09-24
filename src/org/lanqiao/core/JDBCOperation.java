package org.lanqiao.core;

import java.util.List;

/**
 * 增删改查
 * @author qilixiang
 *
 */
public interface JDBCOperation {
	void insert(User user);
	void delete(String email);
	void update(int i,String message,String newMessage);
	/**
	 * 查询所有的联系人的信息，存在数组里面
	 * @return
	 */
	List<User> select();
}
