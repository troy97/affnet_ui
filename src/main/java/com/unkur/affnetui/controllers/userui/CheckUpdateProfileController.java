package com.unkur.affnetui.controllers.userui;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.log4j.Logger;
import org.hibernate.Transaction;

import com.google.common.base.Throwables;
import com.unkur.affnetui.config.HibernateUtil;
import com.unkur.affnetui.config.Links;
import com.unkur.affnetui.config.Urls;
import com.unkur.affnetui.controllers.StatusEndpoint;
import com.unkur.affnetui.dao.impl.ShopDaoImpl;
import com.unkur.affnetui.dao.impl.UserDaoImpl;
import com.unkur.affnetui.entity.Shop;
import com.unkur.affnetui.entity.User;

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
		System.err.println("START");
		//get session and user object (this request has passed Auth filter)
		HttpSession session = (HttpSession) request.getSession();
		User oldUser = (User) session.getAttribute(Links.SESSION_USER_ATTR_NAME);
		
		Transaction Tx = HibernateUtil.getCurrentSession().beginTransaction();
		String redirectIfDuplicateUrl = Urls.UPDATE_USER_PROFILE_PAGE_URL + Links.createQueryString(Links.ERROR_PARAM_NAME);
		User freshUser;
		Shop freshShop;
		try {
			Shop oldShop;
			try {
				oldShop = new ShopDaoImpl().selectById(oldUser.getShopId());
			} catch (Exception e1) {
				StatusEndpoint.incrementErrors();
				logger.debug("Unable to get Shop object associated with given User " + e1);
				response.sendRedirect(Urls.ERROR_PAGE_URL);
				throw e1;
			}

			freshUser = oldUser.clone(); //old User is stored in session, so don't change it, unless changes are saved to DB
			freshShop = oldShop;
			freshShop.setName(request.getParameter(Links.SHOP_NAME_PARAM_NAME));
			freshShop.setUrl(request.getParameter(Links.SHOP_URL_PARAM_NAME));

			freshUser.setEmail(request.getParameter(Links.EMAIL_PARAM_NAME));
			freshUser.setPassword(request.getParameter(Links.PASSWORD_PARAM_NAME));
			freshUser.setFirstName(request.getParameter(Links.FIRST_NAME_PARAM_NAME));
			freshUser.setLastName(request.getParameter(Links.LAST_NAME_PARAM_NAME));
		
		
			try {
				new ShopDaoImpl().updateShop(freshShop);
			} catch(Exception e) {
				redirectIfDuplicateUrl = Urls.UPDATE_USER_PROFILE_PAGE_URL + Links.createQueryString(Links.DUPLICATE_SHOP_PARAM_NAME);
				throw e;
			}
			
			try {
				new UserDaoImpl().updateUser(freshUser);
			} catch(Exception e) {
				redirectIfDuplicateUrl = Urls.UPDATE_USER_PROFILE_PAGE_URL + Links.createQueryString(Links.DUPLICATE_USER_PARAM_NAME);
				throw e;
			}
			
			//register OK, update user in session attributes
			session.removeAttribute(Links.SESSION_USER_ATTR_NAME);
			session.setAttribute(Links.SESSION_USER_ATTR_NAME, freshUser); // update user in session
			Tx.commit();
		} catch (Exception e) {
			logger.debug("Profile update failure: " + Throwables.getStackTraceAsString(e));
			Tx.rollback();
			response.sendRedirect(redirectIfDuplicateUrl);
			return;
		} 
		
		logger.info("Profile updated successfully for user email=\"" + freshUser.getEmail() + "\"");

		//create OK page
		request.setAttribute("userObject", freshUser);
		request.setAttribute("shopObject", freshShop);
		
		request.getRequestDispatcher(Links.UPDATE_PROFILE_SUCCESS_JSP).forward(request, response);
		
	}
	
}
