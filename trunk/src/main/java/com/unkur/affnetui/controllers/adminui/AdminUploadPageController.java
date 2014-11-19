package com.unkur.affnetui.controllers.adminui;

import java.io.IOException;
import java.util.ArrayList;
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

import com.google.common.base.Throwables;
import com.unkur.affnetui.config.AppConfig;
import com.unkur.affnetui.config.HibernateUtil;
import com.unkur.affnetui.config.Links;
import com.unkur.affnetui.config.Urls;
import com.unkur.affnetui.entity.Admin;
import com.unkur.affnetui.entity.Shop;

/**
 * Servlet implementation class AdminUploadPageController
 */
@WebServlet("/admin/upload")
public class AdminUploadPageController extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
	private static Logger logger = Logger.getLogger(AdminUploadPageController.class.getName());
	
    /**
     * @see HttpServlet#HttpServlet()
     */
    public AdminUploadPageController() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		//get session (it can't be null because this request passed through Auth filter)
		HttpSession session = (HttpSession) request.getSession();
		Admin admin = (Admin) session.getAttribute(Links.SESSION_ADMIN_ATTR_NAME);
		System.out.println(admin);
		
		//check if it's the first attempt to upload,
		//if not, put "wrong" notification to dataModel
		if(request.getParameter(Links.ERROR_PARAM_NAME) != null) {
			request.setAttribute("badFileFormat", AppConfig.getInstance().get("badFileFormat"));
		}
		
		List<Shop> shops = new ArrayList<>();
		Session hs = HibernateUtil.getSessionFactory().openSession();
		try {
			//shops = new ShopDaoImpl().getAllShops();
			Query q = hs.createQuery("FROM Shop");
			shops = q.list();
		} catch (Exception e1) {
			logger.error("Unable to get shop list: " + Throwables.getStackTraceAsString(e1));
			response.sendRedirect(Urls.ERROR_PAGE_URL);
			return;
		} finally {
			hs.close();
		}
		
		request.setAttribute("statusPage", Urls.STATUS_PAGE_URL);
		request.setAttribute("name", admin.getEmail());
		request.setAttribute("logoutPage", Urls.LOGOUT_PAGE_URL);
		request.setAttribute("shopList", shops);
		request.setAttribute("uploader", Urls.ADMIN_UPLOAD_CONTROLLER_URL);
		
		request.getRequestDispatcher(Links.ADMIN_UPLOAD_PAGE_JSP).forward(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
	}
	
}
