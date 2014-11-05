package com.unkur.affnetui.dao.impl;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

import com.unkur.affnetui.dao.AdminDao;
import com.unkur.affnetui.dao.exceptions.DbAccessException;
import com.unkur.affnetui.dao.exceptions.NoSuchEntityException;
import com.unkur.affnetui.entity.Admin;
import com.unkur.affnetui.utils.Encrypter;

public class AdminDaoMock implements AdminDao {
	
	private Set<Admin> users = new HashSet<Admin>();

	public AdminDaoMock() {
		users.add(new Admin("ebay", "ebay@gmail.com", Encrypter.encrypt("1111"), 1));
		users.add(new Admin("amazon", "amazon@gmail.com", Encrypter.encrypt("1111"), 2));
	}

	@Override
	public Admin selectAdmin(String login, String password) throws DbAccessException, NoSuchEntityException {
		for(Admin u : this.users) {
			if(u.getEmail().equals(login) && u.getEncryptedPassword().equals(password)){
				return u;
			}
		}
		throw new NoSuchEntityException();
	}

	@Override
	public List<Admin> selectAllAdmins() throws DbAccessException {
		return null;
	}

	@Override
	public int insertAdmin(Admin user) throws DbAccessException {
		users.add(user);
		return 0;
	}

}
