package com.unkur.affnetui.dao;

import java.util.List;

import com.unkur.affnetui.dao.exceptions.DbAccessException;
import com.unkur.affnetui.dao.exceptions.NoSuchEntityException;
import com.unkur.affnetui.entity.Click;

public interface ClickDao extends DAO<Click> {

	public List<Click> selectByShopId(int shopId) throws DbAccessException;
	
	public List<Click> selectByDistributorId(int distributorId)  throws DbAccessException;
	
	public List<Click> selectByProductId(int productId) throws DbAccessException;
	
}
