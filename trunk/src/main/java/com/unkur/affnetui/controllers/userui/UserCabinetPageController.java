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
import com.unkur.affnetui.entity.User;

/**
 * Servlet implementation class UserCabinetPageController
 */
@WebServlet("/user/cabinet")
public class UserCabinetPageController extends HttpServlet {
	private static final long serialVersionUID = 1L;
    
	private static Logger logger = Logger.getLogger(UserCabinetPageController.class.getName());
    /**
     * @see HttpServlet#HttpServlet()
     */
    public UserCabinetPageController() {
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
		
		request.setAttribute("name", user.getEmail());
		
		request.setAttribute("logoutPage", Urls.LOGOUT_PAGE_URL);
		request.setAttribute("uploadPage", Urls.USER_UPLOAD_PAGE_URL);
		request.setAttribute("updateProfilePage", Urls.UPDATE_USER_PROFILE_PAGE_URL);
		request.setAttribute("viewLastFilesPage", Urls.VIEW_LAST_FILES_PAGE_URL);
		
		AppConfig cfg = AppConfig.getInstance();
		request.setAttribute("uploadPageLinkName", cfg.get("userUploadFileInvaitation"));
		request.setAttribute("updateProfileLinkName", cfg.get("userUpdateProfileInvaitation"));
		request.setAttribute("viewLastFilesLinkName", cfg.get("userLastUploadedFilesInvaitation"));
		
		request.getRequestDispatcher(Links.USER_CABINET_JSP).forward(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
	}

}
