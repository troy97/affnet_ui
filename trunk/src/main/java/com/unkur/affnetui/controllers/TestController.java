package com.unkur.affnetui.controllers;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.unkur.affnetui.config.Urls;

/**
 * Servlet implementation class TestController
 */
@WebServlet("/test")
public class TestController extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public TestController() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		System.out.println("TestController Pass");
		System.out.println(request.getContextPath());// = /affnetui
		System.out.println(request.getRequestURI()); // = /affnetui/test
		System.out.println(request.getPathInfo());// = null
		System.out.println(request.getServletPath()); // = /test
		
/*		System.out.println(Urls.ERROR_PAGE_URL);
		response.sendRedirect(Urls.ERROR_PAGE_URL);*/
		
		PrintWriter out = response.getWriter();
		out.write("TestController html");
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
	}

}
