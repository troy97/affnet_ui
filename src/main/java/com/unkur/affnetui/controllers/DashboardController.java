package com.unkur.affnetui.controllers;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Date;
import java.util.LinkedHashSet;
import java.util.List;
import java.util.Random;
import java.util.Set;
import java.util.concurrent.TimeUnit;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.hibernate.Session;
import org.hibernate.Transaction;

import com.unkur.affnetui.config.Config;
import com.unkur.affnetui.config.HibernateUtil;
import com.unkur.affnetui.config.Links;
import com.unkur.affnetui.config.Urls;
import com.unkur.affnetui.entity.Click;
import com.unkur.affnetui.entity.Order;
import com.unkur.affnetui.entity.Product;
import com.unkur.affnetui.entity.User;

/**
 * Servlet implementation class DashboardController
 */
@WebServlet("/user/dash")
public class DashboardController extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public DashboardController() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		//get session and user object (this request has passed Auth filter)
		HttpSession httpsession = (HttpSession) request.getSession();
		User user = (User) httpsession.getAttribute(Links.SESSION_USER_ATTR_NAME);
		request.setAttribute("user", user);
		
		
		long week = TimeUnit.DAYS.toMillis(7);
		long month = TimeUnit.DAYS.toMillis(30);
		
		//read data from DB
		Session session = HibernateUtil.getCurrentSession();
		Transaction tx = session.beginTransaction();
		
		Order firstOrder = (Order) session.createQuery("FROM Order order ORDER BY order.created_at ASC").setMaxResults(1).uniqueResult();
		System.out.println("First order = " + new Date(firstOrder.getCreated_at()));
		Order latestOrder = (Order) session.createQuery("FROM Order order ORDER BY order.created_at DESC").setMaxResults(1).uniqueResult(); 
		System.out.println("Latest order = " + new Date(latestOrder.getCreated_at()));
		
		Click firstClick = (Click) session.createQuery("FROM Click order ORDER BY order.clickTime ASC").setMaxResults(1).uniqueResult();
		System.out.println("First click = " + new Date(firstClick.getClickTime()));
		Click lastClick = (Click) session.createQuery("FROM Click order ORDER BY order.clickTime DESC").setMaxResults(1).uniqueResult();
		System.out.println("Last click = " + new Date(lastClick.getClickTime()));
		
		long clicksLastWeek = getClicksNumber(session, lastClick.getClickTime()-week, lastClick.getClickTime());
		System.out.println("Clicks last week: " + clicksLastWeek);
		long ordersLastWeek = getOrdersNumber(session, latestOrder.getCreated_at()-week, latestOrder.getCreated_at());
		System.out.println("Orders last week: " + ordersLastWeek);
		long confirmedOrdersLastWeek = ((Long) session.createQuery("SELECT count(*) FROM Order o WHERE o.created_at > " + (latestOrder.getCreated_at()-week) + "AND o.status = \'Confirmed\'").uniqueResult()).intValue();
		System.out.println("Confirmed orders last week: " + confirmedOrdersLastWeek);
		long cancelledOrdersLastWeek = ((Long) session.createQuery("SELECT count(*) FROM Order o WHERE o.created_at > " + (latestOrder.getCreated_at()-week) + "AND o.status = \'Cancelled\'").uniqueResult()).intValue();
		System.out.println("Canceled orders last week: " + cancelledOrdersLastWeek);
		
		List<Order> lastTenOrders = session.createQuery("FROM Order o ORDER BY o.updated_at DESC").setMaxResults(10).list();
		System.out.println("Last 10 orders: " + lastTenOrders);
		request.setAttribute("lastTenOrders", lastTenOrders);
		
		Set<Product> topGrowingProducts = getTopGrowing(session);
		System.out.println("Top growing products: " + topGrowingProducts);
		request.setAttribute("topGrowingProducts", topGrowingProducts);
		
		List<Product> topProductsVisited = getMostVisited(session);
		request.setAttribute("topProductsVisited", topProductsVisited);
		List<Integer> visitsDay = getVisitsDay();
		request.setAttribute("visitsDay", visitsDay);
		List<Integer> visitsWeek = getVisitsWeek(visitsDay);
		request.setAttribute("visitsWeek", visitsWeek);
		

		
		tx.commit();
		
		//set attributes
		httpsession.setAttribute("logoutPage", Urls.LOGOUT_PAGE_URL);
		httpsession.setAttribute("dashPage", Urls.USER_DASHBOARD_PAGE_URL);
		httpsession.setAttribute("name", user.getFirstName());
		httpsession.setAttribute("updateProfilePage", Urls.UPDATE_USER_PROFILE_PAGE_URL);
		httpsession.setAttribute("userUploadPage", Urls.USER_UPLOAD_PAGE_URL);
		
		String language = request.getParameter(Links.LANGUAGE_PARAM_NAME);
		if(language != null) {
			httpsession.setAttribute("language", language);
		}
		//request.setAttribute("language", request.getParameter(Links.LANGUAGE_PARAM_NAME) == null ? "en" : request.getParameter(Links.LANGUAGE_PARAM_NAME));
		//request.setAttribute("bundleBasename", Config.BUNDLE_BASENAME);
		
		//render page
		request.getRequestDispatcher("/dash.jsp").forward(request, response);
	}
	
	
	
	
	
	
	
	
	
	private List<Integer> getVisitsDay() {
		List<Integer> result = new ArrayList<Integer>();
		Random r = new Random(34);
		for(int i = 0; i<10; i++) {
			result.add(7 + r.nextInt(15));
		}
		Collections.sort(result);
		Collections.reverse(result);
		return result;
	}
	
	private List<Integer> getVisitsWeek(List<Integer> day) {
		List<Integer> result = new ArrayList<Integer>();
		Random r = new Random(34);
		for(int i = 0; i<10; i++) {
			result.add(day.get(i)*7 + r.nextInt(14));
		}
		return result;
	}
	
	/**
	 * Returns list of 7 integers representing click spread
	 * @param clicksForAllWeek
	 * @return
	 */
	private List<Long> getClicksLastWeek(long clicksForAllWeek) {
		List<Long> result = new ArrayList<Long>();
		Random r = new Random();
		long clicksPerDay = clicksForAllWeek/7;
		for(int i=0; i<7; i++) {
			result.add(new Long(clicksPerDay + r.nextInt(15)));
		}
		return result;
	}
	
	private List<Long> getOrdersLastWeek(long orderForAllWeek) {
		List<Long> result = new ArrayList<Long>();
		Random r = new Random();
		long ordersPerDay = orderForAllWeek/7;
		for(int i=0; i<7; i++) {
			result.add(new Long(ordersPerDay + r.nextInt(2)));
		}
		return result;
	}
	
	private List<Click> getCliksByProduct(Session s, Product product) {
		List<Click> result = new ArrayList<Click>();
		result = s.createQuery("FROM Click c ORDER BY c.clickTime DESC WHERE c.product_id = " + product.getId()).list();
		return result;
	}
	
	private List<Product> getMostVisited(Session s) {
		List<Product> result = new ArrayList<>();
		long[] ids = new long[]{108, 93, 243, 237, 194, 297, 315, 7, 297, 251};
		for(int i = 0; i<10; i++) {
			result.add( (Product) s.get(Product.class, ids[i]) );
		}
		return result;
	}

	private Set<Product> getTopGrowing(Session s) {
		Set<Product> result = new LinkedHashSet<>();
		Random r = new Random();
		for(int i = 0; i<10; i++) {
			try {
				long id = 1+r.nextInt(350);
				result.add( (Product) s.get(Product.class, id) );
			} catch (Exception e) {
				System.out.println("Top growing orders exception: " + e);
			}
		}
		return result;
	}

	private long getOrdersNumber(Session session, long from, long to) {
		return ((Long) session.createQuery("SELECT count(*) FROM Order o WHERE o.created_at > " + from + " AND o.created_at < " + to).uniqueResult()).intValue();
	}
	
	private long getClicksNumber(Session session, long from, long to) {
		return ((Long) session.createQuery("SELECT count(*) FROM Click c WHERE c.clickTime > " + from + " AND c.clickTime < " + to).uniqueResult()).intValue();
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
	}

}
