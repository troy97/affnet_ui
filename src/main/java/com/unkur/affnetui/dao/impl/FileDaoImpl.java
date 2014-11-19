package com.unkur.affnetui.dao.impl;

import java.util.List;

import org.apache.log4j.Logger;
import org.hibernate.Query;
import org.hibernate.Session;

import com.unkur.affnetui.config.HibernateUtil;
import com.unkur.affnetui.dao.FileDao;
import com.unkur.affnetui.dao.exceptions.DbAccessException;
import com.unkur.affnetui.dao.exceptions.UniqueConstraintViolationException;
import com.unkur.affnetui.entity.UploadedFile;

public class FileDaoImpl implements FileDao{

	private static Logger log = Logger.getLogger(FileDaoImpl.class.getName());
	
	
	/**
	 * @return List of objects obtained from DB
	 * @throws DbAccessException
	 */
	@Override
	public List<UploadedFile> getAllFiles() {
		Session s =HibernateUtil.getSessionFactory().getCurrentSession();
		Query q = s.createQuery("FROM UploadedFile");
		List<UploadedFile> result = q.list();
		return result;
	}
	
	
	@Override
	public List<UploadedFile> getLastNfiles(int n, int shopId) throws DbAccessException {
		Session s = HibernateUtil.getSessionFactory().getCurrentSession();
		Query q = s.createQuery("FROM UploadedFile file WHERE file.shopId = ? ORDER BY file.uploadTime DESC");
		q.setInteger(0, shopId);
		q.setMaxResults(n);
		return q.list();
		
/*		Connection conn = null;
		Statement stm=null;
		ResultSet rs = null;	
		try{
			conn=connectionPool.getConnection();
			stm = conn.createStatement();
			String sql = "SELECT * FROM tbl_files WHERE shop_id=" + shopId + " ORDER BY upload_time DESC LIMIT " + n + ";";
			rs = stm.executeQuery(sql);
			return extractAll(rs);
		}
		catch(SQLException e){
			log.debug("SQL exception");
			throw new DbAccessException("Error accessing DB", e);	
		}
		finally{
			JdbcUtils.close(rs);
			JdbcUtils.close(stm);
			JdbcUtils.close(conn);
		}*/
	}
	

	/**
	 * Inserts uploaded file into DB
	 * If given file is active (file.isActive == true) then
	 * currently active file in DB is deactivated before INSERT operation
	 * @return auto-generated index assigned to this entry by DBMS
	 * @throws UniqueConstraintViolationException if insert statement violates unique constraint
	 * @throws DbAccessException if other error occurred during attempt to write to DB 
	 */
	@Override
	public int insertOne(UploadedFile file) {
		Session s  = HibernateUtil.getSessionFactory().getCurrentSession();
		int id = (Integer) s.save(file);
		return id;
		
/*		Connection conn = null;
		Statement stm = null;
		ResultSet rs = null;
		try{
			conn = connectionPool.getConnection();
			conn.setTransactionIsolation(Connection.TRANSACTION_SERIALIZABLE);
			conn.setAutoCommit(false);
			stm = conn.createStatement();
			//deactivate active file for current shop in DB if given file is active 
			if(file.isActive()) {
				String sql = "UPDATE tbl_files SET is_active = false WHERE is_active = true AND shop_id = \'" + file.getShopId() + "\';";
				stm.executeUpdate(sql);
			}
			String sql = "INSERT INTO tbl_files (name, fs_path, upload_time, shop_id,"
					+ " file_size, is_active, is_valid, products_count, validation_message, is_processed) ";
			sql+="VALUES (";
			sql+="\'"+ file.getName() +"\', ";
			sql+="\'"+ file.getFsPath() +"\', ";
			sql+="\'"+ file.getUploadTime() +"\', ";
			sql+="\'"+ file.getShopId() +"\', ";
			sql+="\'"+ file.getSize() +"\', ";
			sql+="\'"+ file.isActive() +"\', ";
			sql+="\'"+ file.isValid() +"\', ";
			sql+="\'"+ file.getProductsCount() +"\', ";
			sql+="\'"+ file.getValidationMessage() +"\', ";
			sql+="\'"+ file.isProcessed() +"\' ";
			sql+=");";
			stm.executeUpdate(sql, Statement.RETURN_GENERATED_KEYS);
			rs=stm.getGeneratedKeys();
			rs.next();	
			int idColumnNumber = 1;
			int generatedIndex = rs.getInt(idColumnNumber);
			JdbcUtils.commit(conn);
			return generatedIndex;
		}
		catch(SQLException e){
			JdbcUtils.rollback(conn);
			if(e.getMessage().contains("ERROR: duplicate key value violates unique constraint")) {
				log.debug("Duplicate unique constraint");
				throw new UniqueConstraintViolationException();
			} else {
				log.debug("DB access error");
				throw new DbAccessException("Error accessing DB", e);
			}
		}
		finally{
			try {
				conn.setAutoCommit(true);
			} catch (SQLException e) {
				log.debug("Exception: unable to resume AutoCommit");
			}
			JdbcUtils.close(rs);
			JdbcUtils.close(stm);
			JdbcUtils.close(conn);
		}*/
	}	
	
	/**
	 * @param uploadedFile
	 * @throws DbAccessException
	 */
	@Override
	public void update(UploadedFile file) throws DbAccessException {
		Session s = HibernateUtil.getSessionFactory().getCurrentSession();
		s.update(file);
		
/*		Connection conn = null;
		Statement stm = null;
		ResultSet rs = null;
		try{
			conn = connectionPool.getConnection();
			conn.setTransactionIsolation(Connection.TRANSACTION_SERIALIZABLE);
			conn.setAutoCommit(false);
			stm = conn.createStatement();
			//deactivate active file for current shop in DB if given file is active 
			if(file.isActive()) {
				String sql = "UPDATE tbl_files SET is_active = false WHERE is_active = true AND shop_id = \'" + file.getShopId() + "\';";
				stm.executeUpdate(sql);
			}
			String sql = "UPDATE tbl_files SET "
					+ "is_active="+ file.isActive() +", "
					+ "is_valid="+ file.isValid() +", "
					+ "is_processed="+ file.isProcessed() +", "
					+ "products_count="+ file.getProductsCount() +", "
					+ "validation_message=\'"+ file.getValidationMessage() +"\' "
					+ "WHERE id=" + file.getId() + ";";
			stm.executeUpdate(sql);
			JdbcUtils.commit(conn);
		}
		catch(SQLException e){
			JdbcUtils.rollback(conn);
			log.error("Error updating file entry: " + e);
			throw new DbAccessException(e.getMessage());
		}
		finally{
			try {
				conn.setAutoCommit(true);
			} catch (SQLException e) {
				log.debug("Exception: unable to resume AutoCommit");
			}
			JdbcUtils.close(rs);
			JdbcUtils.close(stm);
			JdbcUtils.close(conn);
		}*/
	}


	@Override
	public List<UploadedFile> getAllActive() throws DbAccessException {
/*		Connection conn = null;
		Statement stm=null;
		ResultSet rs = null;	
		try{
			conn=connectionPool.getConnection();
			stm = conn.createStatement();
			String sql = "SELECT * FROM tbl_files WHERE is_active = true ORDER BY upload_time DESC;";
			rs = stm.executeQuery(sql);
			return extractAll(rs);
		}
		catch(SQLException e){
			log.debug("SQL exception");
			throw new DbAccessException("Error accessing DB", e);	
		}
		finally{
			JdbcUtils.close(rs);
			JdbcUtils.close(stm);
			JdbcUtils.close(conn);
		}*/
		return null;
	}

	@Override
	public List<UploadedFile> selectActiveOlderThan(long age) throws DbAccessException {
/*		Connection conn = null;
		Statement stm=null;
		ResultSet rs = null;	
		try{
			conn=connectionPool.getConnection();
			stm = conn.createStatement();
			long currentTime = System.currentTimeMillis();
			String sql = "SELECT * FROM tbl_files WHERE is_active = true AND upload_time < " + (currentTime - age) + " ;";
			rs = stm.executeQuery(sql);
			return extractAll(rs);
		}
		catch(SQLException e){
			log.debug("SQL exception");
			throw new DbAccessException("Error accessing DB", e);	
		}
		finally{
			JdbcUtils.close(rs);
			JdbcUtils.close(stm);
			JdbcUtils.close(conn);
		}*/
		return null;
	}


	@Override
	public List<UploadedFile> selectUnprocessed() throws DbAccessException {
		// TODO Auto-generated method stub
		return null;
	}




}
