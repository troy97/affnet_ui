package com.unkur.affnetui.dao.impl;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.unkur.affnetui.dao.DistributorDao;
import com.unkur.affnetui.dao.Extractor;
import com.unkur.affnetui.dao.exceptions.DbAccessException;
import com.unkur.affnetui.dao.exceptions.NoSuchEntityException;
import com.unkur.affnetui.dao.exceptions.UniqueConstraintViolationException;
import com.unkur.affnetui.entity.Distributor;

public class DistributorDaoMock extends Extractor<Distributor> implements DistributorDao {

	private List<Distributor> db = new ArrayList<>();
	
	public DistributorDaoMock() {
		Distributor distrib = new Distributor("first@first.net", "1111", "first", "last");
		distrib.setId(1);
		db.add(distrib);
	}

	@Override
	public Distributor selectById(int id) throws DbAccessException,
			NoSuchEntityException {
		return db.get(0);
	}

	@Override
	public int insertOne(Distributor distributor) throws DbAccessException, UniqueConstraintViolationException {
		db.add(distributor);
		return db.indexOf(distributor);
	}

	@Override
	protected Distributor extractOne(ResultSet rs) throws SQLException {
		return null;
	}

}
