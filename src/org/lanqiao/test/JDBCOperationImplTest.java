package org.lanqiao.test;

import static org.junit.Assert.*;

import org.junit.Test;
import org.lanqiao.core.JDBCOperationImpl;
import org.lanqiao.core.User;

public class JDBCOperationImplTest {

	@Test
	public void testInsert() {
		User user = new User("a", "a", "a", "b");
		JDBCOperationImpl.me.insert(user);
	}
	
	@Test
	public void testDelete(){
		String email = "b";
		JDBCOperationImpl.me.delete(email);
	}
	@Test
	public void testUpdate(){
		JDBCOperationImpl.me.update(1, "c", "eee");
	}
}
