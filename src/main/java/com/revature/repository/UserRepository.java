package com.revature.repository;

import java.util.List;

import com.revature.model.User;

public interface UserRepository {
	
	public boolean createUserAccount(User user);
	public List<User> getUsers();
	public int updateAccount(User user);
	public boolean isMatchUsername(String input);
	public boolean isMatchPassword(String inputPassword, String inputUser);
}

