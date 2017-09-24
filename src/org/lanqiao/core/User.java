package org.lanqiao.core;

public class User {
	private String name;
	private String job;
	private String nickName;
	private String email;
	
	public User() {
	}
	/**
	 * 有参构造方法
	 * @param name 名字
	 * @param job 职务
	 * @param nickName 昵称
	 * @param email 邮箱
	 */
	public User(String name, String job, String nickName, String email) {
		super();
		this.name = name;
		this.job = job;
		this.nickName = nickName;
		this.email = email;
	}

	/**
	 * get|set
	 * 
	 * @return
	 */
	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getJob() {
		return job;
	}

	public void setJob(String job) {
		this.job = job;
	}

	public String getNickName() {
		return nickName;
	}

	public void setNickName(String nickName) {
		this.nickName = nickName;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	@Override
	public String toString() {
		return "User [name=" + name + ", job=" + job + ", nickName=" + nickName
				+ ", email=" + email + "]";
	}

}
