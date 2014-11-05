package com.unkur.affnetui.controllers.adminui;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.log4j.Logger;

import com.google.common.base.Throwables;
import com.unkur.affnetui.config.Links;
import com.unkur.affnetui.config.Urls;
import com.unkur.affnetui.controllers.StatusEndpoint;
import com.unkur.affnetui.dao.AdminDao;
import com.unkur.affnetui.dao.exceptions.DbAccessException;
import com.unkur.affnetui.dao.exceptions.NoSuchEntityException;
import com.unkur.affnetui.dao.impl.AdminDaoImpl;
import com.unkur.affnetui.entity.Admin;
import com.unkur.affnetui.utils.Encrypter;

/**
 * This controller doesn't have any view part, it only gets credentials
 * from login controller and verifies them against those stored in DB.
 * If no match found then redirect back to login page is sent.
 * If match found then redirect to upload page is sent.
 * @author Anton Lukashchuk
 *
 */
@WebServlet("/checkLogin")
public class CheckLoginController extends HttpServlet {
	private static final long serialVersionUID = 1L;
	
	private static Logger logger = Logger.getLogger(CheckLoginController.class.getName());
	
    /**
     * @see HttpServlet#HttpServlet()
     */
    public CheckLoginController() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		response.sendRedirect(Urls.ERROR_PAGE_URL);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

		String email = request.getParameter(Links.EMAIL_PARAM_NAME);
		String pass = request.getParameter(Links.PASSWORD_PARAM_NAME);
		
		if(email == null || pass == null || email.equals("") || pass.equals("")	) {
			response.sendRedirect(Urls.LOGIN_PAGE_URL + Links.createQueryString(Links.ERROR_PARAM_NAME));
			return;
		}
		
		Admin admin;
		try {
			admin = new AdminDaoImpl().selectAdmin(email, Encrypter.encrypt(pass));
		} catch (DbAccessException e) {
			StatusEndpoint.incrementErrors();
			logger.error(Throwables.getStackTraceAsString(e));
			response.sendRedirect(Urls.ERROR_PAGE_URL);
			return;
		} catch (NoSuchEntityException e) {
			logger.info("Bad login attempt, entered credentials: login=\"" + email
					+ "\", pass=\"" + pass + "\"");
			response.sendRedirect(Urls.LOGIN_PAGE_URL + Links.createQueryString(Links.ERROR_PARAM_NAME));
			return;
		}
		logger.info("Successful login: " + email);
		
		//login OK, create new Session and attach this admin to it
		HttpSession session = (HttpSession) request.getSession(true);
		session.setAttribute(Links.SESSION_ADMIN_ATTR_NAME, admin);
		
		response.sendRedirect(Urls.ADMIN_UI_MAIN_PAGE_URL);
	}

}



