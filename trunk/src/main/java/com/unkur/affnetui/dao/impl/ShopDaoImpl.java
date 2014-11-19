package com.unkur.affnetui.dao.impl;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.List;

import org.apache.log4j.Logger;
import org.hibernate.Query;
import org.hibernate.Session;

import com.google.common.base.Throwables;
import com.unkur.affnetui.config.HibernateUtil;
import com.unkur.affnetui.dao.Extractor;
import com.unkur.affnetui.dao.ShopDao;
import com.unkur.affnetui.dao.exceptions.DbAccessException;
import com.unkur.affnetui.dao.exceptions.NoSuchEntityException;
import com.unkur.affnetui.dao.exceptions.UniqueConstraintViolationException;
import com.unkur.affnetui.entity.Shop;

/**
 * Provides DB access methods for entities of
 * class Shop
 * @author Anton Lukashchuk
 *
 */
public class ShopDaoImpl implements ShopDao{
	
	private static Logger logger = Logger.getLogger(ShopDaoImpl.class.getName());
	
	
	/**
	 * Extracts all entities of class Shop, stored in DB
	 * @return List of Shop objects, empty list if no objects found
	 * @throws DbAccessException
	 */
	@Override
	public List<Shop> getAllShops() throws DbAccessException {
		Session s = HibernateUtil.getCurrentSession();
		Query q = s.createQuery("FROM Shop");
		return q.list();
	}
	

	/**
	 * Extracts Shop object with given dbId
	 * @return Shop object
	 * @throws NoSuchEntityException if no Shop with given dbId found
	 * @throws DbAccessException
	 */
	@Override
	public Shop selectById(int dbId) throws DbAccessException, NoSuchEntityException {
		Session s = HibernateUtil.getCurrentSession();
		return (Shop) s.get(Shop.class, dbId);
/*		Connection conn = null;
		Statement stm=null;
		ResultSet rs = null;	
		try{
			conn=connectionPool.getConnection();
			stm = conn.createStatement();
			String sql = "SELECT * FROM tbl_shops WHERE id=" + dbId;
			rs = stm.executeQuery(sql);
			if(rs.next()) {
				return extractOne(rs);
			} else {
				throw new NoSuchEntityException();
			}
		}
		catch(SQLException e){
			throw new DbAccessException("Error accessing DB", e);	
		}
		finally{
			JdbcUtils.close(rs);
			JdbcUtils.close(stm);
			JdbcUtils.close(conn);
		}*/
	}

	@Override
	public int insertOne(Shop shop) throws DbAccessException, UniqueConstraintViolationException {
		Session s = HibernateUtil.getCurrentSession();
		return (Integer) s.save(shop);
/*		Statement stm = null;
		ResultSet rs = null;
		Connection conn = null;
		try{
			conn = connectionPool.getConnection();
			stm = conn.createStatement();
			String sql = "INSERT INTO tbl_shops (name, url, price_list_url) ";
			sql+="VALUES (";
			sql+="\'"+ shop.getName() +"\', ";
			sql+="\'"+ shop.getUrl() +"\' ";
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
	
	@Override
	public int insertOne(Shop shop, Connection conn) throws DbAccessException, UniqueConstraintViolationException {
/*		Statement stm = null;
		ResultSet rs = null;
		try{
			stm = conn.createStatement();
			String sql = "INSERT INTO tbl_shops (name, url) ";
			sql+="VALUES (";
			sql+="\'"+ shop.getName() +"\', ";
			sql+="\'"+ shop.getUrl() +"\' ";
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
		logger.error("#################################unsupported");
		return 0;
	}

	@Override
	public void updateShop(Shop shop) throws DbAccessException, UniqueConstraintViolationException {
		Session s = HibernateUtil.getCurrentSession();
		s.update(shop);
		
/*		Statement stm = null;
		Connection conn = null;
		try{
			conn = connectionPool.getConnection();
			stm = conn.createStatement();
			String sql = "UPDATE tbl_shops SET ";
			sql+="name=\'"+shop.getName()+"\', ";
			sql+="url=\'"+shop.getUrl()+"\' ";
			sql+="WHERE id=" + shop.getId() + ";";
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
	public void updateShop(Shop shop, Connection conn) throws DbAccessException, UniqueConstraintViolationException {
/*		Statement stm = null;
		try{
			stm = conn.createStatement();
			String sql = "UPDATE tbl_shops SET ";
			sql+="name=\'"+shop.getName()+"\', ";
			sql+="url=\'"+shop.getUrl()+"\' ";
			sql+="WHERE id=" + shop.getId() + ";";
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


	public int insertResource(int shop_id, String resourceFileFormat,
			String resourceDownloadUrl, String resourceAuthRequired,
			String resourceAuthUsername, String resourceAuthPassword, Connection conn) throws DbAccessException {
/*		Statement stm = null;
		ResultSet rs = null;
		try{
			stm = conn.createStatement();
			String sql = "INSERT INTO tbl_shop_sources (shop_id, file_format, download_url, basic_http_auth_required, basic_http_auth_username, basic_http_auth_password) ";
			sql+="VALUES (";
			sql+="\'"+ shop_id +"\', ";
			sql+="\'"+ resourceFileFormat +"\', ";
			sql+="\'"+ resourceDownloadUrl +"\', ";
			sql+="\'"+ resourceAuthRequired +"\', ";
			sql+="\'"+ resourceAuthUsername +"\', ";
			sql+="\'"+ resourceAuthPassword +"\' ";
			sql+=");";
			stm.executeUpdate(sql, Statement.RETURN_GENERATED_KEYS);
			rs=stm.getGeneratedKeys();
			rs.next();	
			int idColumnNumber = 1;
			return rs.getInt(idColumnNumber);
		}
		catch(SQLException e){
			logger.debug("Error accessing DB " + Throwables.getStackTraceAsString(e));
			throw new DbAccessException();
		}
		finally{
			JdbcUtils.close(rs);
			JdbcUtils.close(stm);
		}*/
		return 0;
	}	
	
	public void setSourceActive(int id, Connection conn) throws DbAccessException {
/*		Statement stm = null;
		try{
			stm = conn.createStatement();
			String sql = "UPDATE tbl_shop_sources SET ";
			sql+="is_active=true ";
			sql+="WHERE id=" + id + ";";
			stm.executeUpdate(sql);
		}
		catch(SQLException e){
			throw new DbAccessException("Error accessing DB", e);
		}
		finally{
			JdbcUtils.close(stm);
		}*/
	}	

}
