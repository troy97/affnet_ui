package com.unkur.affnetui.dao;

import java.util.List;

import com.unkur.affnetui.dao.exceptions.DbAccessException;
import com.unkur.affnetui.dao.exceptions.UniqueConstraintViolationException;
import com.unkur.affnetui.entity.UploadedFile;

public interface FileDao {
	
	public List<UploadedFile> getAllFiles() throws DbAccessException;
	
	public List<UploadedFile> getLastNfiles(int n, int shopId) throws DbAccessException;
	
	public List<UploadedFile> getAllActive() throws DbAccessException;
	
	public int insertOne(UploadedFile file) throws DbAccessException, UniqueConstraintViolationException;
	
	public void update(UploadedFile file)  throws DbAccessException;
	
	public List<UploadedFile> selectActiveOlderThan(long milis) throws DbAccessException;
	
	public List<UploadedFile> selectUnprocessed() throws DbAccessException;


}
