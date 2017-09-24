package org.lanqiao.core;

import java.beans.PropertyVetoException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import com.mchange.v2.c3p0.ComboPooledDataSource;
/**
 * 用了C3P0连接池，存在的问题就是不能实时更新资源（比如添加联系人的时候，不能更新出新的联系人）。
 * 注释掉的部分是手写的连接池，用BlockingQueue堵塞队列去初始化连接数量（关关闭时放回队列中）
 * 查询的结果放在cache中。
 * @author qilixiang
 *
 */
public enum JDBCOperationImpl implements JDBCOperation {

	me;
	static ComboPooledDataSource ds;
	static {
		try {
			ds = new ComboPooledDataSource();
			ds.setMaxConnectionAge(5);
			ds.setDriverClass(DBConst.DRIVERNAME);
			ds.setJdbcUrl(DBConst.URL);
			ds.setUser(DBConst.USER);
			ds.setPassword(DBConst.PWD);
		} catch (PropertyVetoException e) {
			e.printStackTrace();
		}
	}

	// final BlockingQueue<Connection> connQueue = new
	// LinkedBlockingDeque<Connection>(2);

	// private JDBCOperationImpl() {
	// init();
	// }

	/**
	 * 初始化连接池
	 */
	// private void init() {
	// for (int i = 0; i < 2; i++) {
	// try {
	// connQueue.add(createConn());
	// } catch (SQLException e) {
	// throw new RuntimeException(e.getMessage(), e);
	// }
	// }
	// }

	/**
	 * 添加用户
	 */
	@Override
	public void insert(User user) {
		String name = user.getName();
		String job = user.getJob();
		String nickName = user.getNickName();
		String email = user.getEmail();
		
		try(Connection conn = ds.getConnection();
				PreparedStatement stmt = conn
						.prepareStatement("insert into user (name,job,nickname,email) values(?,?,?,?);");) {
			
			// email不能重复
			stmt.setString(1, name);
			stmt.setString(2, job);
			stmt.setString(3, nickName);
			stmt.setString(4, email);
			try {
				stmt.executeUpdate();
			} catch (Exception e) {
				throw new RuntimeException(e.getMessage(), e);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	/**
	 * 根据email删除联系人
	 * 
	 */
	@Override
	public void delete(String email) {
		try(Connection conn = ds.getConnection();
			PreparedStatement pstmt = conn.prepareStatement("delete from user where email=?");) {
			pstmt.setString(1, email);
			pstmt.executeUpdate();// executeUpdate
									// 的返回值是一个整数（int），指示受影响的行数（即更新计数）。比如就删除一个联系人的话，就返回
									// 1
									// 对于 CREATE TABLE 或 DROP TABLE
									// 等不操作行的语句，executeUpdate 的返回值总为零
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	/**
	 * message为要需改的联系人的具体信息 newMessage是新的具体信息 i是要需改联系人的信息
	 */
	@Override
	public void update(int i, String email, String newMessage) {
		String sql = "";
		switch (i) {
		case 0:
			sql = "update user set name=? where email=?";
			break;
		case 1:
			sql = "update user set job=? where email=?";
			break;
		case 2:
			sql = "update user set nickName=? where email=?";
			break;
		case 3:
			sql = "update user set email=? where email=?";
			break;
		default:
			break;
		}
		try(Connection conn = ds.getConnection();
			PreparedStatement pstmt = conn.prepareStatement(sql);) {
			pstmt.setString(1, newMessage);
			pstmt.setString(2, email);
			pstmt.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	/**
	 * 查询mysql中的联系人，
	 * 
	 * @return 将返回的联系人存在list中
	 */
	@Override
	public List<User> select() {
		User user = null;
		List<User> list = new ArrayList<User>();
		try(Connection conn = ds.getConnection();
			Statement stmt = conn.createStatement();) {
			ResultSet rs = stmt.executeQuery("select * from User;");
			while (rs.next()) {
				user = new User(rs.getString(1), rs.getString(2), rs.getString(3), rs.getString(4));
				if (null != user) {
					list.add(user);
				}
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return list;
	}

	// private void freeConn(Connection conn) {
	// try {
	// if (null != conn && !conn.isClosed()) {// 不能将空的和关闭的连接还到连接池中，别人拿到的连接不能使用
	// try {
	// connQueue.put(conn);
	// } catch (InterruptedException e) {
	// e.printStackTrace();
	// }
	// }
	// } catch (SQLException e) {
	// e.printStackTrace();
	// }
	// }

	// private Connection getConn() {
	//
	// return connQueue.poll();
	// }
}
