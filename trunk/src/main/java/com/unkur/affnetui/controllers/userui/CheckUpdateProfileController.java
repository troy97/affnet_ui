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
import com.unkur.affnetui.dao.exceptions.NoSuchEntityException;
import com.unkur.affnetui.dao.exceptions.UniqueConstraintViolationException;
import com.unkur.affnetui.dao.impl.ShopDaoImpl;
import com.unkur.affnetui.dao.impl.UserDaoImpl;
import com.unkur.affnetui.dao.utils.DbConnectionPool;
import com.unkur.affnetui.dao.utils.JdbcUtils;
import com.unkur.affnetui.entity.Shop;
import com.unkur.affnetui.entity.User;
import com.unkur.affnetui.utils.Encrypter;

/**
 * Servlet implementation class CheckUpdateProfileController
 */
@WebServlet("/user/checkUpdateProfile")
public class CheckUpdateProfileController extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
	private static Logger logger = Logger.getLogger(CheckUpdateProfileController.class.getName());
    /**
     * @see HttpServlet#HttpServlet()
     */
    public CheckUpdateProfileController() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		response.sendError(HttpServletResponse.SC_BAD_REQUEST);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		//get session and user object (this request has passed Auth filter)
		HttpSession session = (HttpSession) request.getSession();
		User oldUser = (User) session.getAttribute(Links.SESSION_USER_ATTR_NAME);
		
		Shop oldShop;
		try {
			oldShop = new ShopDaoImpl().selectById(oldUser.getShopId());
		} catch (DbAccessException | NoSuchEntityException e1) {
			StatusEndpoint.incrementErrors();
			logger.debug("Unable to get Shop object associated with given User " + e1);
			response.sendRedirect(Urls.ERROR_PAGE_URL);
			return;
		}
		
		User freshUser = oldUser.clone(); //old User is stored in session, so don't change it, unless changes are saved to DB
		Shop freshShop = oldShop;
		String redirectIfDuplicateUrl = Urls.UPDATE_USER_PROFILE_PAGE_URL + Links.createQueryString(Links.ERROR_PARAM_NAME);
		freshShop.setName(request.getParameter(Links.SHOP_NAME_PARAM_NAME));
		freshShop.setUrl(request.getParameter(Links.SHOP_URL_PARAM_NAME));
		
		freshUser.setEmail(request.getParameter(Links.EMAIL_PARAM_NAME));
		freshUser.setPassword(request.getParameter(Links.PASSWORD_PARAM_NAME));
		freshUser.setFirstName(request.getParameter(Links.FIRST_NAME_PARAM_NAME));
		freshUser.setLastName(request.getParameter(Links.LAST_NAME_PARAM_NAME));
		
		Connection conn = null; //############### transaction manager crutch
		try {
			conn = getConnection(); //############### transaction manager crutch
			
			try {
				new ShopDaoImpl().updateShop(freshShop);
			} catch(UniqueConstraintViolationException e) {
				redirectIfDuplicateUrl = Urls.UPDATE_USER_PROFILE_PAGE_URL + Links.createQueryString(Links.DUPLICATE_SHOP_PARAM_NAME);
				throw e;
			}
			
			try {
				new UserDaoImpl().updateUser(freshUser);
			} catch(UniqueConstraintViolationException e) {
				redirectIfDuplicateUrl = Urls.UPDATE_USER_PROFILE_PAGE_URL + Links.createQueryString(Links.DUPLICATE_USER_PARAM_NAME);
				throw e;
			}
			commitAndClose(conn); //############### transaction manager crutch
			
			//register OK, update user in session attributes
			session.removeAttribute(Links.SESSION_USER_ATTR_NAME);
			session.setAttribute(Links.SESSION_USER_ATTR_NAME, freshUser); // update user in session
		} catch (DbAccessException e) {
			rollbackAndClose(conn); //############### transaction manager crutch
			StatusEndpoint.incrementErrors();
			logger.debug("Problem while inserting to DB " + e);
			response.sendRedirect(Urls.ERROR_PAGE_URL);
			return;
		} catch (UniqueConstraintViolationException e) {
			rollbackAndClose(conn); //############### transaction manager crutch
			logger.debug("Profile update failure: " + e);
			response.sendRedirect(redirectIfDuplicateUrl);
			return;
		}
		
		logger.info("Profile updated successfully for user email=\"" + freshUser.getEmail() + "\"");

		//create OK page
		request.setAttribute("name", freshUser.getEmail());
		request.setAttribute("cabinetPage", Urls.fullURL(Urls.USER_CABINET_PAGE_URL));
		request.setAttribute("logoutPage", Urls.fullURL(Urls.LOGOUT_PAGE_URL));
		request.setAttribute("userObject", freshUser);
		request.setAttribute("shopObject", freshShop);
		
		request.getRequestDispatcher(Links.UPDATE_PROFILE_SUCCESS_JSP).forward(request, response);
		
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
