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

import com.unkur.affnetui.config.HibernateUtil;
import com.unkur.affnetui.config.Links;
import com.unkur.affnetui.config.Urls;
import com.unkur.affnetui.controllers.StatusEndpoint;
import com.unkur.affnetui.dao.exceptions.DbAccessException;
import com.unkur.affnetui.dao.impl.FileDaoImpl;
import com.unkur.affnetui.entity.UploadedFile;
import com.unkur.affnetui.entity.User;

/**
 * Servlet implementation class ViewLastFilesPageController
 */
@WebServlet("/user/viewLastFiles")
public class ViewLastFilesPageController extends HttpServlet {
	private static final long serialVersionUID = 1L;
    
	private static Logger logger = Logger.getLogger(ViewLastFilesPageController.class.getName());
    /**
     * @see HttpServlet#HttpServlet()
     */
    public ViewLastFilesPageController() {
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
		
		HibernateUtil.getCurrentSession().beginTransaction();
		List<UploadedFile> files;
		try {
			files = new FileDaoImpl().getLastNfiles(10, user.getShopId());
			HibernateUtil.getCurrentSession().getTransaction().commit();
		} catch (Exception e) {
			HibernateUtil.getCurrentSession().getTransaction().rollback();
			StatusEndpoint.incrementErrors();
			logger.error("Unable to extract file from DB " + e);
			response.sendRedirect(Urls.ERROR_PAGE_URL);
			return;
		}
		
		request.setAttribute("fileList", files);
		
		request.setAttribute("name", user.getEmail());
		request.setAttribute("logoutPage", Urls.LOGOUT_PAGE_URL);
		request.setAttribute("cabinetPage", Urls.USER_CABINET_PAGE_URL);
		
		request.getRequestDispatcher(Links.VIEW_LAST_FILES_JSP).forward(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
	}

}