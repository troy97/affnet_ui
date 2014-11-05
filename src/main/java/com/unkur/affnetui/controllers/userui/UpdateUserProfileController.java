package com.unkur.affnetui.controllers.userui;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.log4j.Logger;

import com.unkur.affnetui.config.AppConfig;
import com.unkur.affnetui.config.Links;
import com.unkur.affnetui.config.Urls;
import com.unkur.affnetui.dao.exceptions.DbAccessException;
import com.unkur.affnetui.dao.exceptions.NoSuchEntityException;
import com.unkur.affnetui.dao.impl.ShopDaoImpl;
import com.unkur.affnetui.entity.Shop;
import com.unkur.affnetui.entity.User;

/**
 * Servlet implementation class UpdateUserProfileController
 */
@WebServlet("/user/updateProfile")
public class UpdateUserProfileController extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
	private static Logger logger = Logger.getLogger(UpdateUserProfileController.class.getName());
	
    /**
     * @see HttpServlet#HttpServlet()
     */
    public UpdateUserProfileController() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		//get session and user object (this request has passed Auth filter)
		HttpSession session = (HttpSession) request.getSession();
		User user = (User) session.getAttribute(Links.SESSION_USER_ATTR_NAME);
		
		Shop shop = null;
		try {
			shop = new ShopDaoImpl().selectById(user.getShopId());
		} catch (DbAccessException | NoSuchEntityException e) {
			logger.debug("Unable to get Shop instance from DAO " + e);
			response.sendRedirect(Urls.ERROR_PAGE_URL);
			return;
		} 
		
		//check if it's the first attempt,
		//if not, put "wrong" notification to dataModel
		checkErrorParams(request);
		
		request.setAttribute("name", user.getEmail());

		request.setAttribute("checkUpdate", Urls.fullURL(Urls.CHECK_UPDATE_PROFILE_URL));
		request.setAttribute("cabinetPage", Urls.fullURL(Urls.USER_CABINET_PAGE_URL));
		request.setAttribute("logoutPage", Urls.fullURL(Urls.LOGOUT_PAGE_URL));

		request.setAttribute("email", Links.EMAIL_PARAM_NAME);
		request.setAttribute("password", Links.PASSWORD_PARAM_NAME);
		request.setAttribute("firstName", Links.FIRST_NAME_PARAM_NAME);
		request.setAttribute("lastName", Links.LAST_NAME_PARAM_NAME);
		request.setAttribute("shopName", Links.SHOP_NAME_PARAM_NAME);
		request.setAttribute("shopUrl", Links.SHOP_URL_PARAM_NAME);

		request.setAttribute("shopObject", shop);
		request.setAttribute("userObject", user);
		
		//render login page
		request.getRequestDispatcher(Links.UPDATE_USER_PROFILE_JSP).forward(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
	}

	
	/**
	 * Verify if some messages for User are to be added to page
	 * @param ftlData
	 * @param queryStr
	 */
	private void checkErrorParams(HttpServletRequest request) {
		AppConfig cfg = AppConfig.getInstance();
		if(request.getParameter(Links.DUPLICATE_SHOP_PARAM_NAME) != null) {
			request.setAttribute("wrongData", cfg.get("duplicateShopMsg"));		
		} else if(request.getParameter(Links.DUPLICATE_USER_PARAM_NAME) != null) {
			request.setAttribute("wrongData", cfg.get("duplicateUserMsg"));	
		} else if(request.getParameter(Links.ERROR_PARAM_NAME) != null) {
			request.setAttribute("wrongData", cfg.get("wrongSignUpInfo"));	
		} 
	}
}

