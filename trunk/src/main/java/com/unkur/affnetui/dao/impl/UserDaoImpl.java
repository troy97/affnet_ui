package com.unkur.affnetui.dao.impl;

import java.sql.Connection;
import java.util.List;

import org.apache.log4j.Logger;
import org.hibernate.Query;
import org.hibernate.Session;

import com.unkur.affnetui.config.HibernateUtil;
import com.unkur.affnetui.dao.UserDao;
import com.unkur.affnetui.dao.exceptions.DbAccessException;
import com.unkur.affnetui.dao.exceptions.NoSuchEntityException;
import com.unkur.affnetui.dao.exceptions.UniqueConstraintViolationException;
import com.unkur.affnetui.entity.User;

public class UserDaoImpl implements UserDao {
	private static Logger logger = Logger.getLogger(UserDaoImpl.class.getName());
	
	/**
	 * Search user in DB by email and password
	 * @return User object if found matching login and password
	 * @throws NoSuchEntityException if user wasn't found
	 * @throws DbAccessException 
	 */
	@Override
	public User selectOne(String email, String encryptedUserPassword) {
		
		Session s = HibernateUtil.getCurrentSession();
		Query q = s.createQuery("FROM User U WHERE U.email = ? AND U.encryptedPassword = ?");
		q.setString(0, email);
		q.setString(1, encryptedUserPassword);
		return ((List<User>) q.list()).get(0);
		
/*		Connection conn=null;
		Statement stm=null;
		ResultSet rs=null;
		try{
			conn = connectionPool.getConnection();
			stm = conn.createStatement();
			rs = stm.executeQuery("SELECT * "
					+ "FROM tbl_shop_users "
					+ "WHERE email = \'" + email + "\' AND password_ssha256_hex = \'" + encryptedUserPassword + "\'");
			if(rs.next()) {
				return extractOne(rs);
			} else {
				throw new NoSuchEntityException();
			}
		} catch(SQLException e){
			log.debug("Signin SQL error");
			throw new DbAccessException("Error accessing DB", e);
		}
		finally{
			JdbcUtils.close(rs);
			JdbcUtils.close(stm);
			JdbcUtils.close(conn);
		}*/
	}
	
	/**
	 * Get list of all Users in DB
	 */
	@Override
	public List<User> selectAllUsers() throws DbAccessException {
		return null;
	}
	
	/**
	 * Inserts new user into DB
	 * @return id of the user assigned by database
	 */
	@Override
	public int insertOne(User user) {
		Session s = HibernateUtil.getCurrentSession();
		return (Integer) s.save(user);
		
/*		Statement stm = null;
		ResultSet rs = null;
		Connection conn = null;
		try{
			conn = connectionPool.getConnection();
			stm = conn.createStatement();
			String sql = "INSERT INTO tbl_shop_users (email, password_ssha256_hex, created_at, name_first, name_last, is_active, shop_id) ";
			sql+="VALUES (";
			sql+="\'"+user.getEmail()+"\', ";
			sql+="\'"+user.getEncryptedPassword()+"\', ";
			sql+="NOW(), ";
			sql+="\'"+user.getFirstName()+"\',";
			sql+="\'"+user.getLastName()+"\',";
			sql+="\'"+user.isActive()+"\',";
			sql+="\'"+user.getShopId()+"\'";
			sql+=");";
			stm.executeUpdate(sql, Statement.RETURN_GENERATED_KEYS);
			rs=stm.getGeneratedKeys();
			rs.next();	
			int idColumnNumber = 1;
			return rs.getInt(idColumnNumber);
		}
		catch(SQLException e){
			if(e.getMessage().contains("ERROR: duplicate key")) {
				throw new UniqueConstraintViolationException();
			} else {
				throw new DbAccessException("Error accessing DB", e);
			}
		}
		finally{
			JdbcUtils.close(rs);
			JdbcUtils.close(stm);
			JdbcUtils.close(conn);
		}*/
	}
	
	/**
	 * Inserts new user into DB
	 * @return id of the user assigned by database
	 */
	@Override
	public int insertOne(User user, Connection conn) throws DbAccessException, UniqueConstraintViolationException {
/*		Statement stm = null;
		ResultSet rs = null;
		try{
			stm = conn.createStatement();
			String sql = "INSERT INTO tbl_shop_users (email, password_ssha256_hex, created_at, name_first, name_last, is_active, shop_id) ";
			sql+="VALUES (";
			sql+="\'"+user.getEmail()+"\', ";
			sql+="\'"+user.getEncryptedPassword()+"\', ";
			sql+="NOW(), ";
			sql+="\'"+user.getFirstName()+"\',";
			sql+="\'"+user.getLastName()+"\',";
			sql+="\'"+user.isActive()+"\',";
			sql+="\'"+user.getShopId()+"\'";
			sql+=");";
			stm.executeUpdate(sql, Statement.RETURN_GENERATED_KEYS);
			rs=stm.getGeneratedKeys();
			rs.next();	
			int idColumnNumber = 1;
			return rs.getInt(idColumnNumber);
		}
		catch(SQLException e){
			if(e.getMessage().contains("ERROR: duplicate key")) {
				throw new UniqueConstraintViolationException();
			} else {
				throw new DbAccessException("Error accessing DB", e);
			}
		}
		finally{
			JdbcUtils.close(rs);
			JdbcUtils.close(stm);
		}*/
		logger.error("##########################unsup");
		return 0;
	}
	

	@Override
	public void setActive(String email, boolean isActive)
			throws DbAccessException {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void updateUser(User user) throws DbAccessException, UniqueConstraintViolationException {
		Session s = HibernateUtil.getCurrentSession();
		s.update(user);
		
/*		Statement stm = null;
		Connection conn = null;
		try{
			conn = connectionPool.getConnection();
			stm = conn.createStatement();
			String sql = "UPDATE tbl_shop_users SET ";
			sql+="email=\'"+user.getEmail()+"\', ";
			sql+="password_ssha256_hex=\'"+user.getEncryptedPassword()+"\', ";
			sql+="name_first=\'"+user.getFirstName()+"\', ";
			sql+="name_last=\'"+user.getLastName()+"\' ";
			sql+="WHERE id=" + user.getId() + ";";
			stm.executeUpdate(sql);
		}
		catch(SQLException e){
			if(e.getMessage().contains("ERROR: duplicate key")) {
				throw new UniqueConstraintViolationException();
			} else {
				throw new DbAccessException("Error accessing DB", e);
			}
		}
		finally{
			JdbcUtils.close(stm);
			JdbcUtils.close(conn);
		}*/
	}	
	
	@Override
	public void updateUser(User user, Connection conn) throws DbAccessException, UniqueConstraintViolationException {
/*		Statement stm = null;
		try{
			stm = conn.createStatement();
			String sql = "UPDATE tbl_shop_users SET ";
			sql+="email=\'"+user.getEmail()+"\', ";
			sql+="password_ssha256_hex=\'"+user.getEncryptedPassword()+"\', ";
			sql+="name_first=\'"+user.getFirstName()+"\', ";
			sql+="name_last=\'"+user.getLastName()+"\' ";
			sql+="WHERE id=" + user.getId() + ";";
			stm.executeUpdate(sql);
		}
		catch(SQLException e){
			if(e.getMessage().contains("ERROR: duplicate key")) {
				throw new UniqueConstraintViolationException();
			} else {
				throw new DbAccessException("Error accessing DB", e);
			}
		}
		finally{
			JdbcUtils.close(stm);
		}*/
	}

}
