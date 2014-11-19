package com.unkur.affnetui.dao.impl;

import java.util.List;

import org.apache.log4j.Logger;
import org.hibernate.Query;
import org.hibernate.Session;

import com.unkur.affnetui.config.HibernateUtil;
import com.unkur.affnetui.dao.AdminDao;
import com.unkur.affnetui.dao.exceptions.DbAccessException;
import com.unkur.affnetui.dao.exceptions.NoSuchEntityException;
import com.unkur.affnetui.dao.exceptions.UniqueConstraintViolationException;
import com.unkur.affnetui.entity.Admin;

/**
 * Provides DB access methods for entities of
 * class User
 * @author Anton Lukashchuk
 *
 */
public class AdminDaoImpl implements AdminDao{

	private static Logger logger = Logger.getLogger(AdminDaoImpl.class.getName());
	
	/**
	 * Search user in DB by email and password
	 * @return User object if found matching login and password
	 * @throws NoSuchEntityException if user wasn't found
	 * @throws DbAccessException 
	 */
	@Override
	public Admin selectAdmin(String email, String encryptedUserPassword) throws DbAccessException, NoSuchEntityException{
		Session s = HibernateUtil.getCurrentSession();
		Query q = s.createQuery("FROM Admin admin WHERE admin.email = ? AND admin.encryptedPassword = ?");
		q.setString(0, email);
		q.setString(1, encryptedUserPassword);
		return ((List<Admin>) q.list()).get(0);
/*		Statement stm=null;
		ResultSet rs=null;
		Connection conn=null;
		try{
			conn = connectionPool.getConnection();
			stm = conn.createStatement();
			rs = stm.executeQuery("SELECT * "
					+ "FROM tbl_admins "
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
	public List<Admin> selectAllAdmins() throws DbAccessException {
		return null;
	}
	
	/**
	 * Inserts new user into DB
	 * @return id of the user assigned by database
	 */
	@Override
	public int insertAdmin(Admin user) throws DbAccessException, UniqueConstraintViolationException {
/*		Statement stm = null;
		ResultSet rs = null;
		Connection conn = null;
		try{
			conn = connectionPool.getConnection();
			stm = conn.createStatement();
			String sql = "INSERT INTO tbl_admins (password_ssha256_hex, email, name) ";
			sql+="VALUES (";
			sql+="\'"+user.getEncryptedPassword()+"\', ";
			sql+="\'"+user.getEmail()+"\', ";
			sql+="\'"+user.getName()+"\'";
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
		return 0;
	}
	
}
