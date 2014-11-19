package com.unkur.affnetui.controllers;

import java.io.IOException;
import java.util.Random;
import java.util.concurrent.TimeUnit;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.hibernate.Session;
import org.hibernate.Transaction;

import com.google.common.base.Throwables;
import com.unkur.affnetui.config.HibernateUtil;
import com.unkur.affnetui.dao.ProductDaoImpl;
import com.unkur.affnetui.dao.impl.ClickDaoImpl;
import com.unkur.affnetui.entity.Click;
import com.unkur.affnetui.entity.Product;

/**
 * Servlet implementation class FillDbController
 */
@WebServlet("/fillDb")
public class FillDbController extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public FillDbController() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
/*		//read data from DB
		Session session = HibernateUtil.getCurrentSession();
		Transaction tx = session.beginTransaction();
		try{
		
			long twoMonth = TimeUnit.DAYS.toMillis(60);
			int weeks = 8;
			long timeStart = System.currentTimeMillis()-twoMonth-TimeUnit.DAYS.toMillis(10); // 2 month ago
			long createdAt = timeStart;


			//fill clicks
			ClickDaoImpl clickDao = new ClickDaoImpl();
			ProductDaoImpl productDao = new ProductDaoImpl();
			Random r = new Random();
			long clickTime = timeStart;
			for(int i = 0; i<3000; i++) {
				int productId = 1+r.nextInt(400);
				try {
					Product product = productDao.selectById(productId);
					Click click = new Click(product.getId(), product.getShopId(), 1, r.nextInt(10000),
							product.getName(), product.getPrice(), product.getShippingPrice());
					clickTime += twoMonth/3000;
					click.setClickTime(clickTime);
					clickDao.insertOne(click);
				} catch (Exception e) {
					System.out.println(productId + " " + e);
				}
			}

			System.out.println("################################################################################");

			//fill orders
			int count = 0;
			for(int w = 0; w<weeks; w++) {
				for(int i = 1; i<=7; i++) {
					try {
						createdAt+=TimeUnit.DAYS.toMillis(1);
						ordersDay(createdAt, i, count++);
					} catch (Exception e) {
						System.out.println(e);
					}
				}
			}

			System.out.println("########## Mocking Finished ############");
		
			tx.commit();
		} catch (Exception e) {
			System.out.println(Throwables.getStackTraceAsString(e));
			tx.rollback();
		} */
	}

/*	*//**
	 * 
	 * @param dayStartTime
	 * @param dayNumber
	 * @param count
	 * @throws DbAccessException
	 * @throws NoSuchEntityException
	 *//*
	private void ordersDay(long dayStartTime, int dayNumber, int count) throws DbAccessException, NoSuchEntityException {
		ProductDaoImpl productDao = new ProductDaoImpl();
		OrderDaoImpl orderDao = new OrderDaoImpl();
		int currencyId = 1;
		int distributorId = 1;
		
		Random r = new Random();
		long day = TimeUnit.DAYS.toMillis(1)/2;
		
		int orders = 5 + r.nextInt(8-dayNumber);
		
		long buyInterval = day/orders;
		
		long createdAt = dayStartTime;
		
		
		for(int i = 0; i<orders; i++) {
			
			int productId = 1+r.nextInt(400);
			Product product = productDao.selectById(productId);
			String status;
			int s = r.nextInt(3);
			switch(s){
			case 0: status="Open";
			break;
			case 1: status="Cancelled";
			break;
			case 2: status="Confirmed";
			break;
			default: status = "Open";
			}
			
			
			//System.out.println(count + " " + new Timestamp(createdAt));
			createdAt += buyInterval;
			
			orderDao.insertOne(product.getId(),
					distributorId,
					r.nextInt(10000),
					r.nextInt(3000),
					status,
					product.getPrice(),
					currencyId,
					product.getPrice(),
					product.getName(),
					createdAt,
					createdAt
			);
		}
	}*/
	
	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
	}

}
