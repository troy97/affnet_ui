package com.unkur.affnetui.controllers;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;

/**
 * Servlet implementation class PieDataController
 */
@WebServlet("/pieData")
public class PieDataController extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public PieDataController() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		JSONArray result = new JSONArray();
		
		String name = request.getParameter("name");
		String period = request.getParameter("period");
		
		if(period.equals("current")){
			if(name.equals("orders")) {   //Orders chart
				JSONObject ordersTotal = new JSONObject();
				ordersTotal.put("label", "Confirmed");
				ordersTotal.put("data", 150);

				JSONObject ordersCancelled = new JSONObject();
				ordersCancelled.put("label", "Cancelled");
				ordersCancelled.put("data", 15);

				result.add(ordersTotal);
				result.add(ordersCancelled);
			} else {						//Conversion chart
				JSONObject clicks = new JSONObject();
				clicks.put("label", "Clicks");
				clicks.put("data", 1500);

				JSONObject ordersConfirmed = new JSONObject();
				ordersConfirmed.put("label", "Orders");
				ordersConfirmed.put("data", 61);

				result.add(clicks);
				result.add(ordersConfirmed);
			}
		} else {
			if(name.equals("orders")) {   //Orders chart
				JSONObject ordersTotal = new JSONObject();
				ordersTotal.put("label", "Confirmed");
				ordersTotal.put("data", 150);

				JSONObject ordersCancelled = new JSONObject();
				ordersCancelled.put("label", "Cancelled");
				ordersCancelled.put("data", 20);

				result.add(ordersTotal);
				result.add(ordersCancelled);
			} else {						//Conversion chart
				JSONObject clicks = new JSONObject();
				clicks.put("label", "Clicks");
				clicks.put("data", 1500);

				JSONObject ordersConfirmed = new JSONObject();
				ordersConfirmed.put("label", "Orders");
				ordersConfirmed.put("data", 45);

				result.add(clicks);
				result.add(ordersConfirmed);
			}
		}
		
		PrintWriter w = response.getWriter();
		w.write(result.toJSONString());
	}
	
	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
	}

}
