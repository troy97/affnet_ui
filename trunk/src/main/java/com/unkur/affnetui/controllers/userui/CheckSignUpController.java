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

import com.unkur.affnetui.config.Links;
import com.unkur.affnetui.config.Urls;
import com.unkur.affnetui.controllers.StatusEndpoint;
import com.unkur.affnetui.dao.exceptions.DbAccessException;
import com.unkur.affnetui.dao.exceptions.UniqueConstraintViolationException;
import com.unkur.affnetui.dao.impl.ShopDaoImpl;
import com.unkur.affnetui.dao.impl.UserDaoImpl;
import com.unkur.affnetui.dao.utils.DbConnectionPool;
import com.unkur.affnetui.dao.utils.JdbcUtils;
import com.unkur.affnetui.entity.Shop;
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
		
		User freshUser;
		Shop freshShop;
		Connection conn = null; //############### transaction manager crutch
		String redirectIfDuplicateUrl = Urls.fullURL(Urls.SIGNUP_PAGE_URL) + Links.createQueryString(Links.ERROR_PARAM_NAME);
		try {
			
			conn = getConnection(); //############### transaction manager crutch
			
			freshShop = new Shop(request.getParameter(Links.SHOP_NAME_PARAM_NAME), request.getParameter(Links.SHOP_URL_PARAM_NAME), "empty");
			try {
				int shopId = new ShopDaoImpl().insertOne(freshShop, conn);
				freshShop.setId(shopId);
			} catch (UniqueConstraintViolationException e) {
				redirectIfDuplicateUrl = Urls.SIGNUP_PAGE_URL + Links.createQueryString(Links.DUPLICATE_SHOP_PARAM_NAME);
				throw e;
			}
			
			freshUser = new User(request.getParameter(Links.EMAIL_PARAM_NAME), request.getParameter(Links.PASSWORD_PARAM_NAME), 
					request.getParameter(Links.FIRST_NAME_PARAM_NAME), request.getParameter(Links.LAST_NAME_PARAM_NAME), freshShop.getId());
			try {
				int userId = new UserDaoImpl().insertOne(freshUser, conn);
				freshUser.setId(userId);
			} catch (UniqueConstraintViolationException e) {
				redirectIfDuplicateUrl = Urls.SIGNUP_PAGE_URL + Links.createQueryString(Links.DUPLICATE_USER_PARAM_NAME);
				throw e;
			}
			commitAndClose(conn); //############### transaction manager crutch
		} catch (DbAccessException e) {
			rollbackAndClose(conn); //############### transaction manager crutch
			StatusEndpoint.incrementErrors();
			logger.error("Problem while inserting to DB, not Shop nor User were created, " + e.getClass().getName());
			response.sendRedirect(Urls.ERROR_PAGE_URL);
			return;
		} catch (UniqueConstraintViolationException e) {
			rollbackAndClose(conn); //############### transaction manager crutch
			logger.debug("Problem while inserting to DB, not Shop nor User were created, " + e.getClass().getName());
			response.sendRedirect(redirectIfDuplicateUrl);
			return;
		}
		
		logger.info("Successfull webshop user registration shop URL = \"" + shopUrl + "\" email=\"" + email + "\"");
		
		//register OK, create new Session and attach this user to it
		//login OK, create new Session and attach this admin to it
		HttpSession session = (HttpSession) request.getSession(true);
		session.setAttribute(Links.SESSION_USER_ATTR_NAME, freshUser);

		//redirect to upload page
		response.sendRedirect(Urls.USER_UI_MAIN_PAGE_URL);
	}

	//############### transaction manager crutch
	private Connection getConnection() {
		Connection result = null;
		try {
			result = DbConnectionPool.getInstance().getConnection();
			result.setTransactionIsolation(Connection.TRANSACTION_SERIALIZABLE);
			result.setAutoCommit(false);
		} catch (SQLException e) {
			logger.debug("Unable create connection");
		}
		return result;
	}
	
	//############### transaction manager crutch
	private void commitAndClose(Connection conn) {
		JdbcUtils.commit(conn);
		try {
			conn.setAutoCommit(true);
		} catch (SQLException ignore) {
			ignore.printStackTrace();
		}
		JdbcUtils.close(conn);
	}
	
	//############### transaction manager crutch
	private void rollbackAndClose(Connection conn) {
		JdbcUtils.rollback(conn);
		try {
			conn.setAutoCommit(true);
		} catch (SQLException ignore) {
			ignore.printStackTrace();
		}
		JdbcUtils.close(conn);
	}
}
