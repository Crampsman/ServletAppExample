package com.goliev.manager.servlet;

import java.io.IOException;
import java.net.HttpRetryException;
import java.util.ArrayList;
import java.util.Enumeration;
import java.util.List;
import java.util.Map;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.goliev.manager.dao.EmployeeDao;
import com.goliev.manager.dto.Employee;

public class EmployeeServlet extends HttpServlet {

	private static final long serialVersionUID = -6779105482564982090L;

	EmployeeDao empDao = new EmployeeDao();

	@Override
	public void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {


		if (request.getParameter("edit") == null && request.getParameter("id") == null) {

			List<Employee> employees = new ArrayList<Employee>();

			employees = empDao.getEmployees();

			request.setAttribute("employees", employees);

			getServletContext().getRequestDispatcher("/listEmployees.jsp")
					.forward(request, response);

		}

		if (request.getParameter("edit").equals("delete")) {
			
			int id = Integer.parseInt(request.getParameter("id"));
			
			empDao.deleteEmployeeById(id);
			
			response.sendRedirect("/emp-manager/employees");
		}
		
		if(request.getParameter("edit").equals("add")){
			
			request.setAttribute("employee", new Employee());
			
			getServletContext().getRequestDispatcher("/addEmployee.jsp")
			.forward(request, response);
		}
		
		if(request.getParameter("edit").equals("update")){
			
			int id = Integer.parseInt(request.getParameter("id"));
			
			Employee emp = empDao.getEmployeeById(id);
			
			request.setAttribute("employee", emp);
			
			getServletContext().getRequestDispatcher("/addEmployee.jsp")
			.forward(request, response);
		}
	}
	
	public void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		Employee emp = new Employee();
		
		emp.setName(request.getParameter("name"));
		emp.setSalary(Double.parseDouble(request.getParameter("salary")));
		
		if(Integer.parseInt(request.getParameter("id")) == 0){
			empDao.addEmployee(emp);
		}
		else{
			emp.setId(Integer.parseInt(request.getParameter("id")));
			empDao.updateEmployee(emp);
		}
		
		response.sendRedirect("/emp-manager/employees");
		
	}

	
}
