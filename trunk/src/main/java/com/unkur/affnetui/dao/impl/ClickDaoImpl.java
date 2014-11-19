package com.unkur.affnetui.dao.impl;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Collection;
import java.util.List;

import com.unkur.affnetui.dao.ClickDao;
import com.unkur.affnetui.dao.Extractor;
import com.unkur.affnetui.dao.exceptions.DbAccessException;
import com.unkur.affnetui.dao.exceptions.NoSuchEntityException;
import com.unkur.affnetui.dao.exceptions.UniqueConstraintViolationException;
import com.unkur.affnetui.entity.Click;

public class ClickDaoImpl implements ClickDao {

	@Override
	public long insertOne(Click entity) throws DbAccessException,
			UniqueConstraintViolationException {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public void insertAll(Collection<Click> entities) throws DbAccessException,
			UniqueConstraintViolationException {
		// TODO Auto-generated method stub
		
	}

	@Override
	public Click selectById(long id) throws DbAccessException,
			NoSuchEntityException {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Collection<Click> selectAll(int limit) throws DbAccessException {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void update(Click entity) throws DbAccessException,
			NoSuchEntityException {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void deleteById(long id) throws DbAccessException,
			NoSuchEntityException {
		// TODO Auto-generated method stub
		
	}

	@Override
	public List<Click> selectByShopId(int shopId) throws DbAccessException {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<Click> selectByDistributorId(int distributorId)
			throws DbAccessException {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<Click> selectByProductId(int productId)
			throws DbAccessException {
		// TODO Auto-generated method stub
		return null;
	}


/*	@Override
	public long insertOne(Click entity) throws DbAccessException,
			UniqueConstraintViolationException {
		Statement stm = null;
		ResultSet rs = null;
		Connection conn = null;
		try{
			conn = connectionPool.getConnection();
			stm = conn.createStatement();
			String sql = INSERT_SQL;
			sql+="VALUES (";
			sql+="\'"+entity.getShopId()+"\', ";
			sql+="\'"+entity.getProductId()+"\', ";
			sql+="\'"+entity.getDistributorId()+"\', ";
			if(entity.getSubId() < 0) {
				sql+="NULL";
			} else {
				sql+="\'"+entity.getSubId()+"\' ";
			}
			sql+=");";
			stm.executeUpdate(sql, Statement.RETURN_GENERATED_KEYS);
			rs=stm.getGeneratedKeys();
			rs.next();	
			int idColumnNumber = 1;
			return rs.getLong(idColumnNumber);
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
	public void insertAll(Collection<Click> entities) throws DbAccessException,
			UniqueConstraintViolationException {
		
		throw new UnsupportedOperationException();
		
	}

	@Override
	public Click selectById(long id) throws DbAccessException,
			NoSuchEntityException {
		
		throw new UnsupportedOperationException();
	}

	@Override
	public Collection<Click> selectAll(int limit) throws DbAccessException {
		Connection conn = null;
		Statement stm=null;
		ResultSet rs = null;	
		try{
			conn=connectionPool.getConnection();
			stm = conn.createStatement();
			String sql = SELECT_SQL;
			rs = stm.executeQuery(sql);
			return extractAll(rs);
		}
		catch(SQLException e){
			logger.debug("SQL exception");
			throw new DbAccessException("Error accessing DB", e);	
		}
		finally{
			JdbcUtils.close(rs);
			JdbcUtils.close(stm);
			JdbcUtils.close(conn);
		}
	}

	@Override
	public void update(Click entity) throws DbAccessException,
			NoSuchEntityException {
		
		throw new UnsupportedOperationException();
		
	}

	@Override
	public void deleteById(long id) throws DbAccessException,
			NoSuchEntityException {
		
		throw new UnsupportedOperationException();
		
	}

	@Override
	public List<Click> selectByShopId(int id) throws DbAccessException {
		Connection conn = null;
		Statement stm=null;
		ResultSet rs = null;	
		try{
			conn=connectionPool.getConnection();
			stm = conn.createStatement();
			String sql = SELECT_SQL + "WHERE webshop_id=" + id + ";";
			rs = stm.executeQuery(sql);
			return extractAll(rs);
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
	public List<Click> selectByDistributorId(int id) throws DbAccessException {
		Connection conn = null;
		Statement stm=null;
		ResultSet rs = null;	
		try{
			conn=connectionPool.getConnection();
			stm = conn.createStatement();
			String sql = SELECT_SQL + "WHERE distributor_id=" + id + ";";
			rs = stm.executeQuery(sql);
			return extractAll(rs);
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
	public List<Click> selectByProductId(int id) throws DbAccessException {
		Connection conn = null;
		Statement stm=null;
		ResultSet rs = null;	
		try{
			conn=connectionPool.getConnection();
			stm = conn.createStatement();
			String sql = SELECT_SQL + "WHERE product_id=" + id + ";";
			rs = stm.executeQuery(sql);
			return extractAll(rs);
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
*/




}
