package com.training.servlets;

import java.io.IOException;
import java.sql.SQLException;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.training.daos.TripDetailsDao;
import com.training.entity.TourDetails;

/**
 * Servlet implementation class ShowTrip
 */
public class ShowTrip extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private  TripDetailsDao dao;
    /**
     * @see HttpServlet#HttpServlet()
     */
    public ShowTrip() {
        super();
        dao=new TripDetailsDao();
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		List<TourDetails> result=new ArrayList<>();
		 String message="Exception";
		try {
			result = dao.findAll();
		} catch (SQLException e) {
			message="SQL Exception";
		}
		request.setAttribute("TourDetailsList", result);
//		System.out.println(result);
	
		//System.out.println(result);
			 RequestDispatcher dispatcher=request.getRequestDispatcher("showTrip.jsp");
				request.setAttribute("tourList", result);
				dispatcher.forward(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String strId=request.getParameter("code");
		long code=Long.parseLong(strId);

		 String tourName=request.getParameter("tourName");
		 String strSDate=request.getParameter("startDate");
		 LocalDate startDate=LocalDate.parse(strSDate);
		 String strEDate=request.getParameter("endDate");
		 LocalDate endDate=LocalDate.parse(strEDate);
		 String places=request.getParameter("places");
			String strDays=request.getParameter("numberOfDays");
			long numberOfDays=Long.parseLong(strDays);
			
			TourDetails tour=new TourDetails(code,tourName,startDate,endDate,places,numberOfDays);
		 
		 int result=0;
		 String message="Exception";
		try {
			result = dao.add(tour);
		} catch (SQLException e) {
			message="SQL Exception";
		}
	
		 if (result==1) {
			 message="One Trip Details added";
		 }		
		 RequestDispatcher dispatcher=request.getRequestDispatcher("tripHome.jsp");
		request.setAttribute("details", message);
		dispatcher.forward(request, response);
	}
}

