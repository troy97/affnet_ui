package com.unkur.affnetui.controllers.userui;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.log4j.Logger;
import org.hibernate.Query;
import org.hibernate.Session;

import com.unkur.affnetui.config.Config;
import com.unkur.affnetui.config.HibernateUtil;
import com.unkur.affnetui.config.Links;
import com.unkur.affnetui.config.Urls;
import com.unkur.affnetui.controllers.StatusEndpoint;
import com.unkur.affnetui.entity.User;
import com.unkur.affnetui.utils.Encrypter;

/**
 * Servlet implementation class CheckSignInController
 */
@WebServlet("/checkSignIn")
public class CheckSignInController extends HttpServlet {
	private static final long serialVersionUID = 1L;
    
	private static Logger logger = Logger.getLogger(CheckSignInController.class.getName());
    /**
     * @see HttpServlet#HttpServlet()
     */
    public CheckSignInController() {
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
		
		if(email == null || pass == null ||
				email.isEmpty() || pass.isEmpty()) {
			logger.debug("Query doesn't contain email and/or password");
			response.sendRedirect(Urls.SIGNIN_PAGE_URL + Links.createQueryString(Links.ERROR_PARAM_NAME));
			return;
		}
		
		User freshUser;
		Session hs = HibernateUtil.getSessionFactory().openSession();
		try {
			//UserDao dao = new UserDaoImpl();
			String hql = "FROM User U WHERE U.email=\'" +email+ "\' AND"
					+ " U.encryptedPassword=\'"+Encrypter.encrypt(pass)+"\'";
			Query query = hs.createQuery(hql);
			List<User> results = query.list();
			freshUser = results.get(0);
		} catch (IndexOutOfBoundsException e) {
			logger.info("Bad sign in attempt, entered credentials: email=\"" + email
					+ "\", pass=\"" + pass + "\"");
			response.sendRedirect(Urls.SIGNIN_PAGE_URL + Links.createQueryString(Links.ERROR_PARAM_NAME));
			return;
		} catch (Exception e) {
			StatusEndpoint.incrementErrors();
			logger.error("Sign in failure, exception: " + e);
			response.sendRedirect(Urls.ERROR_PAGE_URL);
			return;
		} finally {
			hs.close();
		}
		
		//OK, create new Session and attach this user to it
		//login OK, create new Session and attach this admin to it
		HttpSession session = (HttpSession) request.getSession(true);
		session.setAttribute(Links.SESSION_USER_ATTR_NAME, freshUser);
		
		String language = request.getParameter(Links.LANGUAGE_PARAM_NAME) == null ? "en" : request.getParameter(Links.LANGUAGE_PARAM_NAME);
		session.setAttribute("language", language);
		//request.setAttribute("language", request.getParameter(Links.LANGUAGE_PARAM_NAME) == null ? "en" : request.getParameter(Links.LANGUAGE_PARAM_NAME));
		session.setAttribute("bundleBasename", Config.BUNDLE_BASENAME);

		//redirect to upload page
		logger.debug("Successfull sign in of \"" + email + "\"");
		response.sendRedirect(Urls.USER_UI_MAIN_PAGE_URL);
		return;
	}

}
