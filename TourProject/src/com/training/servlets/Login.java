package com.training.servlets;

import java.io.IOException;
import java.sql.SQLException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.training.daos.SignUpDao;
import com.training.entity.SignUp;

//import com.training.entity.RegisterUser;

/**
 * Servlet implementation class Login
 */
public class Login extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private SignUpDao dao;
    /**
     * @see HttpServlet#HttpServlet()
     */
    public Login() {
        super();
        dao=new SignUpDao();
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		SignUp user=null;
	     String username=request.getParameter("username");
		 String password=request.getParameter("password");
		 String typeOfUser=request.getParameter("typeOfUser");
		 boolean status=false;
	    	try {
				user = dao.findByUser(username);
			} catch (SQLException e) {
				
				e.printStackTrace();
			}
	   	 if (user!=null && user.getPassword().equals(password)&&user.getType().equals(typeOfUser)) {
				status=true;
				System.out.println("Login success");
			}
		 	if(status) {	
				 RequestDispatcher dispatcher=request.getRequestDispatcher("tripHome.jsp");
				 HttpSession session=request.getSession(true);
				session.setAttribute("user",user);
				dispatcher.forward(request, response);
		 	}

		 	else {
				 RequestDispatcher dispatcher=request.getRequestDispatcher("loginStatus.jsp");
					request.setAttribute("err", "Invalid user id and password");
					dispatcher.forward(request, response);
		 	}
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
			 String firstName=request.getParameter("fname");
			 String lastName=request.getParameter("lname");
			 String typeOfUser=request.getParameter("person");
			 String username=request.getParameter("username");
			 String password=request.getParameter("password");
			// String strDob=request.getParameter("dateOfBirth");
			 //LocalDate dob=LocalDate.parse(strDob);
			
			SignUp user=new SignUp(firstName,lastName,username,password,typeOfUser);
			 
			 int result=0;
			 String message="Exception";
			try {
				result = dao.add(user);
			} catch (SQLException e) {
				message="SQL Exception";
			}
		
			 if (result==1) {
				 message="One user added";
			 }		
			 RequestDispatcher dispatcher=request.getRequestDispatcher("login.html");
			 request.setAttribute("user", message);
			 dispatcher.forward(request, response);
		
	}

	}


