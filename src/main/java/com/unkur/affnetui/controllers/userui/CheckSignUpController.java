package com.unkur.affnetui.controllers.userui;

import java.io.IOException;
import java.sql.Connection;
import java.sql.SQLException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.log4j.Logger;
import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;

import com.google.common.base.Throwables;
import com.unkur.affnetui.config.Config;
import com.unkur.affnetui.config.HibernateUtil;
import com.unkur.affnetui.config.Links;
import com.unkur.affnetui.config.Urls;
import com.unkur.affnetui.controllers.StatusEndpoint;
import com.unkur.affnetui.dao.exceptions.DbAccessException;
import com.unkur.affnetui.dao.exceptions.UniqueConstraintViolationException;
import com.unkur.affnetui.dao.impl.ShopDaoImpl;
import com.unkur.affnetui.dao.impl.UserDaoImpl;
import com.unkur.affnetui.entity.Shop;
import com.unkur.affnetui.entity.ShopSource;
import com.unkur.affnetui.entity.User;

/**
 * Servlet implementation class CheckSignUpController
 */
@WebServlet("/checkSignUp")
public class CheckSignUpController extends HttpServlet {
	private static final long serialVersionUID = 1L;
    
	private static Logger logger = Logger.getLogger(CheckSignUpController.class.getName());
	
    /**
     * @see HttpServlet#HttpServlet()
     */
    public CheckSignUpController() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.getRequestDispatcher(Urls.ERROR_PAGE_URL).forward(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		String shopName = request.getParameter(Links.SHOP_NAME_PARAM_NAME);
		String shopUrl = request.getParameter(Links.SHOP_URL_PARAM_NAME);
		String email = request.getParameter(Links.EMAIL_PARAM_NAME);
		String pass = request.getParameter(Links.PASSWORD_PARAM_NAME);
		
		if(shopName == null || shopUrl == null || email == null || pass == null ||
		   shopName.isEmpty() || shopUrl.isEmpty() || email.isEmpty() || pass.isEmpty()) {
			response.sendRedirect(Urls.SIGNUP_PAGE_URL + Links.createQueryString(Links.ERROR_PARAM_NAME));
			return;
		}
		
		String resourceFileFormat = request.getParameter(Links.SHOP_RESOURCE_FILE_FORMAT_PARAM_NAME);
		String resourceDownloadUrl = request.getParameter(Links.SHOP_RESOURCE_DOWNLOAD_URL_PARAM_NAME);
		String resourceAuthRequired = request.getParameter(Links.SHOP_RESOURCE_AUTH_REQUIRED_PARAM_NAME);
		String resourceAuthUsername = request.getParameter(Links.SHOP_RESOURCE_AUTH_USERNAME_PARAM_NAME);
		String resourceAuthPassword = request.getParameter(Links.SHOP_RESOURCE_AUTH_PASSWORD_PARAM_NAME);
		
		//for testing
		resourceFileFormat = ".csv";
		resourceDownloadUrl = "http://help.yandex.ru/help.yandex.ru/partnermarket/docs/primer-csv-formata-dlya-proizvolnogo-tovara.csv";
		resourceAuthRequired = "false";
		resourceAuthUsername = null;
		resourceAuthPassword = null;
		
		if(resourceDownloadUrl != null && !resourceDownloadUrl.isEmpty()) {
			if(resourceFileFormat == null || resourceFileFormat.isEmpty() ||
			 resourceAuthRequired == null || resourceAuthRequired.isEmpty()	) {
				response.sendRedirect(Urls.SIGNUP_PAGE_URL + Links.createQueryString(Links.ERROR_PARAM_NAME));
				return;
			}
		}
		
		User freshUser;
		Shop freshShop;
		String redirectIfDuplicateUrl = Urls.SIGNUP_PAGE_URL + Links.createQueryString(Links.ERROR_PARAM_NAME);
		
		Session session = HibernateUtil.getSessionFactory().openSession();
		Transaction tx = session.beginTransaction();
		try {
			
			freshShop = new Shop(request.getParameter(Links.SHOP_NAME_PARAM_NAME), request.getParameter(Links.SHOP_URL_PARAM_NAME));
			int sid = (Integer) session.save(freshShop);
			freshShop.setId(sid);
			
			if(resourceDownloadUrl != null && !resourceDownloadUrl.isEmpty()) {
				ShopSource source = new ShopSource(freshShop.getId(), resourceFileFormat, resourceDownloadUrl,
						Boolean.valueOf(resourceAuthRequired), resourceAuthUsername, resourceAuthPassword, false, 0);
				source.setIs_active(true);
				int id = (Integer) session.save(source);
				source.setId(id);
			}
			
			freshUser = new User(request.getParameter(Links.EMAIL_PARAM_NAME), request.getParameter(Links.PASSWORD_PARAM_NAME), 
					request.getParameter(Links.FIRST_NAME_PARAM_NAME), request.getParameter(Links.LAST_NAME_PARAM_NAME),
					freshShop.getId(), request.getParameter(Links.LANGUAGE_PARAM_NAME));
			
			int uid = (Integer) session.save(freshUser);
			freshUser.setId(uid);
			
			tx.commit();
		}catch (Exception e) {
	         if (tx!=null) tx.rollback();
	         StatusEndpoint.incrementErrors();
	         logger.error("Problem while inserting to DB, not Shop nor User were created, " + Throwables.getStackTraceAsString(e));
	         if(e.getCause() != null && e.getMessage().contains("ERROR: duplicate key value violates unique constraint")) {
	        	 response.sendRedirect(Urls.SIGNUP_PAGE_URL + Links.createQueryString(Links.DUPLICATE_SHOP_PARAM_NAME));
	         } else {
	        	 response.sendRedirect(Urls.ERROR_PAGE_URL);
	         }
	         return;
		}finally {
			session.close(); 
		}
		
		
/*		catch (DbAccessException e) {
			StatusEndpoint.incrementErrors();
			logger.error("Problem while inserting to DB, not Shop nor User were created, " + Throwables.getStackTraceAsString(e));
			response.sendRedirect(Urls.ERROR_PAGE_URL);
			return;
		} catch (UniqueConstraintViolationException e) {
			logger.debug("Problem while inserting to DB, not Shop nor User were created, " + Throwables.getStackTraceAsString(e));
			response.sendRedirect(redirectIfDuplicateUrl);
			return;
		}*/
		
		logger.info("Successfull webshop user registration shop URL = \"" + shopUrl + "\" email=\"" + email + "\"");
		
		//register OK, create new Session and attach this user to it
		HttpSession httpSession = (HttpSession) request.getSession(true);
		httpSession.setAttribute(Links.SESSION_USER_ATTR_NAME, freshUser);
		
		String language = request.getParameter(Links.LANGUAGE_PARAM_NAME) == null ? "en" : request.getParameter(Links.LANGUAGE_PARAM_NAME);
		httpSession.setAttribute("language", language);
		//request.setAttribute("language", request.getParameter(Links.LANGUAGE_PARAM_NAME) == null ? "en" : request.getParameter(Links.LANGUAGE_PARAM_NAME));
		httpSession.setAttribute("bundleBasename", Config.BUNDLE_BASENAME);

		//redirect to upload page
		response.sendRedirect(Urls.USER_UI_MAIN_PAGE_URL);
	}

}
