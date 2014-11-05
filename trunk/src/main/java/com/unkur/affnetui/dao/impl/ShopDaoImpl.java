package com.unkur.affnetui.dao.impl;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.List;

import org.apache.log4j.Logger;

import com.unkur.affnetui.dao.Extractor;
import com.unkur.affnetui.dao.ShopDao;
import com.unkur.affnetui.dao.exceptions.DbAccessException;
import com.unkur.affnetui.dao.exceptions.NoSuchEntityException;
import com.unkur.affnetui.dao.exceptions.UniqueConstraintViolationException;
import com.unkur.affnetui.dao.utils.DbConnectionPool;
import com.unkur.affnetui.dao.utils.JdbcUtils;
import com.unkur.affnetui.entity.Shop;

/**
 * Provides DB access methods for entities of
 * class Shop
 * @author Anton Lukashchuk
 *
 */
public class ShopDaoImpl extends Extractor<Shop> implements ShopDao{
	
	private static Logger log = Logger.getLogger(ShopDaoImpl.class.getName());
	
	private DbConnectionPool connectionPool = null;
	
	/**
	 *Public constructor
	 */
	public ShopDaoImpl() {
		this.connectionPool = DbConnectionPool.getInstance();
	}
	
	/**
	 * Extracts all entities of class Shop, stored in DB
	 * @return List of Shop objects, empty list if no objects found
	 * @throws DbAccessException
	 */
	@Override
	public List<Shop> getAllShops() throws DbAccessException {
		Connection conn = null;
		Statement stm=null;
		ResultSet rs = null;	
		try{
			conn=connectionPool.getConnection();
			stm = conn.createStatement();
			String sql = "SELECT * FROM tbl_shops";
			rs = stm.executeQuery(sql);
			return extractAll(rs);
		}
		catch(SQLException e){
			log.error("getAllShops() SQLException");
			throw new DbAccessException("Error accessing DB", e);	
		}
		finally{
			JdbcUtils.close(rs);
			JdbcUtils.close(stm);
			JdbcUtils.close(conn);
		}
	}
	

	/**
	 * Extracts Shop object with given dbId
	 * @return Shop object
	 * @throws NoSuchEntityException if no Shop with given dbId found
	 * @throws DbAccessException
	 */
	@Override
	public Shop selectById(int dbId) throws DbAccessException, NoSuchEntityException {
		Connection conn = null;
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
		}
	}

	@Override
	public int insertOne(Shop shop) throws DbAccessException, UniqueConstraintViolationException {
		Statement stm = null;
		ResultSet rs = null;
		Connection conn = null;
		try{
			conn = connectionPool.getConnection();
			stm = conn.createStatement();
			String sql = "INSERT INTO tbl_shops (name, url, price_list_url) ";
			sql+="VALUES (";
			sql+="\'"+ shop.getName() +"\', ";
			sql+="\'"+ shop.getUrl() +"\', ";
			sql+="\'"+ shop.getPriceListUrl() +"\' ";
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
		}
	}
	
	@Override
	public int insertOne(Shop shop, Connection conn) throws DbAccessException, UniqueConstraintViolationException {
		Statement stm = null;
		ResultSet rs = null;
		try{
			stm = conn.createStatement();
			String sql = "INSERT INTO tbl_shops (name, url, price_list_url) ";
			sql+="VALUES (";
			sql+="\'"+ shop.getName() +"\', ";
			sql+="\'"+ shop.getUrl() +"\', ";
			sql+="\'"+ shop.getPriceListUrl() +"\' ";
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
		}
	}

	@Override
	public void updateShop(Shop shop) throws DbAccessException, UniqueConstraintViolationException {
		Statement stm = null;
		Connection conn = null;
		try{
			conn = connectionPool.getConnection();
			stm = conn.createStatement();
			String sql = "UPDATE tbl_shops SET ";
			sql+="name=\'"+shop.getName()+"\', ";
			sql+="url=\'"+shop.getUrl()+"\', ";
			sql+="price_list_url=\'"+shop.getPriceListUrl()+"\' ";
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
		}
	}
	
	@Override
	public void updateShop(Shop shop, Connection conn) throws DbAccessException, UniqueConstraintViolationException {
		Statement stm = null;
		try{
			conn = connectionPool.getConnection();
			stm = conn.createStatement();
			String sql = "UPDATE tbl_shops SET ";
			sql+="name=\'"+shop.getName()+"\', ";
			sql+="url=\'"+shop.getUrl()+"\', ";
			sql+="price_list_url=\'"+shop.getPriceListUrl()+"\' ";
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
		}
	}

	@Override
	protected Shop extractOne(ResultSet rs) throws SQLException {
		return new Shop(rs.getInt("id"), rs.getString("name"), rs.getString("url"), rs.getString("price_list_url"));
	}	

}
