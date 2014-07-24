package com.mybatis.user;

import java.util.List;

public interface UserDao {
	public User findById(int id);
	
	public List<User> findAll(String userName);
}
