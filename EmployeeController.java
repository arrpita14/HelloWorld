package com.zensar;

import java.io.IOException;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.zensar.beans.Employee;

public class EmployeeController extends HttpServlet {

	public void doGet(HttpServletRequest request, HttpServletResponse response) {
		String requestAction = request.getParameter("requestAction");
		if (requestAction.equalsIgnoreCase("viewAll")) {
			System.out.println("I am inside doGet() method");
			EmployeeRepository employeeRepository = new EmployeeRepository();
			List<Employee> listOfAllEmployees = employeeRepository.getAllEmployees();
			System.out.println(listOfAllEmployees);

			RequestDispatcher rd = request.getRequestDispatcher("viewAllEmployees.jsp");
			request.setAttribute("listOfAllEmployees", listOfAllEmployees);
			try {
				rd.forward(request, response);
			} catch (Exception e) {
				System.out.println("An exception occured :" + e);
			}
		} else if (requestAction.equalsIgnoreCase("delete")) {
			System.out.println("We are deleting an employee");

			// code to delete an entry from database
			EmployeeRepository employeeRepository = new EmployeeRepository();
			int employeeId = Integer.parseInt(request.getParameter("employeeId"));
			employeeRepository.deleteEmployee(employeeId);

			try {
				response.sendRedirect("index.jsp");
			} catch (Exception e) {
				System.out.println("An exception occured :" + e);
			}
		} else if (requestAction.equalsIgnoreCase("openUpdateForm")) {
			int employeeId = Integer.parseInt(request.getParameter("employeeId"));
			
			//connect to database using repository and fetch corresponding details of the employee
			EmployeeRepository employeeRepository = new EmployeeRepository();
			Employee employee = employeeRepository.getEmployee(employeeId);
			
			
			RequestDispatcher rd = request.getRequestDispatcher("updateEmployeeForm.jsp");
			request.setAttribute("employee", employee);
			try {
				rd.forward(request, response);
			} catch (Exception e) {
				System.out.println("An exception occured :" + e);
			}
		}else if(requestAction.equalsIgnoreCase("update")) {
			int empoyeeId = Integer.parseInt(request.getParameter("employeeId"));
			String employeeName = request.getParameter("employeeName");
			String employeeDesignation = request.getParameter("employeeDesignation");
			int employeeSalary = Integer.parseInt(request.getParameter("employeeSalary"));
			String employeeGender = request.getParameter("gender");
			String employeeCity = request.getParameter("city");

			Employee employee = new Employee(empoyeeId, employeeName, employeeDesignation, employeeSalary, employeeGender, employeeCity);
			EmployeeRepository employeeRepository = new EmployeeRepository();
			employeeRepository.updateEmployee(employee);
			
			try {
				response.sendRedirect("index.jsp");
			} catch (Exception e) {
				System.out.println("An exception occured :" + e);
			}
		}else if(requestAction.equalsIgnoreCase("add")) {
			int employeeId = Integer.parseInt(request.getParameter("employeeId"));
			String employeeName = request.getParameter("employeeName");
			String employeeDesignation = request.getParameter("employeeDesignation");
			int employeeSalary = Integer.parseInt(request.getParameter("employeeSalary"));
			String employeeGender = request.getParameter("gender");
			String employeeCity = request.getParameter("city");

			System.out.println("Employee Id is :" + employeeId);
			System.out.println("Employee Name is :" + employeeName);
			System.out.println("Employee Designation is :" + employeeDesignation);
			System.out.println("Employee Salary is :" + employeeSalary);
			System.out.println("Employee Gender is :" + employeeGender);
			System.out.println("Employee City is :" + employeeCity);

			EmployeeRepository employeeRepository = new EmployeeRepository();
			Employee employee = new Employee(employeeId, employeeName, employeeDesignation, employeeSalary, employeeGender,employeeCity);

			employeeRepository.addEmployee(employee);

			try {
				response.sendRedirect("index.jsp");
			} catch (Exception e) {
				System.out.println("An exception occured :" + e);
			}
	
		}
		}
	

	
	
	public void doPost(HttpServletRequest request, HttpServletResponse response) {
		doGet(request, response);
			}
}
