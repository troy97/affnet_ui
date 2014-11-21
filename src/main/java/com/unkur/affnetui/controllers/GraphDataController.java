package com.unkur.affnetui.controllers;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.concurrent.TimeUnit;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;


/**
 * Servlet implementation class GraphDataController
 */
@WebServlet("/graphData")
public class GraphDataController extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public GraphDataController() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		System.out.println("graphData");
		long day = TimeUnit.DAYS.toMillis(1);
		long today = System.currentTimeMillis();
		
		long[][] clicks = new long[][]{
				{today, 55L},
				{today-day, 59L},
				{today-day*2, 67L},
				{today-day*3, 49L},
				{today-day*4, 52L},
				{today-day*5, 55L},
				{today-day*6, 34L},
		};
		
		long[][] orders = new long[][]{
				{today, 7L},
				{today-day, 22L},
				{today-day*2, 10L},
				{today-day*3, 5L},
				{today-day*4, 15L},
				{today-day*5, 7L},
				{today-day*6, 6L},
		};
		
		

		
		JSONObject json = new JSONObject();
		json.put("clicks", createJSONArr(clicks));
		json.put("orders", createJSONArr(orders));
		
		PrintWriter out = response.getWriter();
		out.write(json.toJSONString());
	}
	
	private JSONArray createJSONArr(long[][] line) {
		JSONArray result = new JSONArray();
		
		for(int i=0; i<line.length; i++) {
			JSONArray list = new JSONArray();
			for(int j=0; j<line[0].length; j++) {
				list.add(line[i][j]);
			}
			result.add(list);
		}
		
		return result;
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
	}

}
