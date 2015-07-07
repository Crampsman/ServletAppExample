package com.goliev.manager.servlet;

import java.io.IOException;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class HomeServlet extends HttpServlet {

	private static final long serialVersionUID = 4728874792496187075L;

	private Date currentDate;
	
	DateFormat dateFormat;
	
	@Override
	public void init() throws ServletException{
		
		dateFormat = new SimpleDateFormat("yyyy/MM/dd HH:mm:ss");
		currentDate = new Date();
	}
	
	//dateFormat.format(currentDate)
	
	@Override
	public void doGet (HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException{
		
		req.setAttribute("date", dateFormat.format(currentDate));
		
		getServletContext().getRequestDispatcher("/home.jsp").forward(req, resp);
		
	}
}
