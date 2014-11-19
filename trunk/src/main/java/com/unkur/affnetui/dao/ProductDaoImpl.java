package com.unkur.affnetui.dao;

import org.hibernate.Session;

import com.unkur.affnetui.config.HibernateUtil;
import com.unkur.affnetui.entity.Product;


public class ProductDaoImpl {

	public Product selectById(long productId) {
		Session s = HibernateUtil.getCurrentSession();
		return (Product) s.get(Product.class, productId);
	}
	

}
