package com.goliev.manager.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import com.goliev.manager.dto.Employee;
import com.goliev.manager.util.MySqlConnection;

public class EmployeeDao {

	private MySqlConnection pool = MySqlConnection.getInstance();

	public Employee getEmployeeById(int id) {

		Connection con = pool.openConnection();

		Employee emp = new Employee();

		String sql = "SELECT id, name, salary FROM Employee WHERE id = ?";

		try {

			PreparedStatement stat = con.prepareStatement(sql);

			stat.setInt(1, id);

			ResultSet rsset = stat.executeQuery();
			rsset.next();
			emp.setId(rsset.getInt("id"));
			emp.setName(rsset.getString("name"));
			emp.setSalary(rsset.getDouble("salary"));

		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			if (con != null) {
				try {
					con.close();
				} catch (SQLException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
		}

		return emp;
	}

	public List<Employee> getEmployees() {

		Connection con = pool.openConnection();

		String sql = "SELECT id, name, salary FROM Employee";

		List<Employee> employees = new ArrayList<Employee>();

		try {

			Statement stat = con.createStatement();
			ResultSet rsset = stat.executeQuery(sql);

			while (rsset.next()) {

				Employee emp = new Employee();

				emp.setId(rsset.getInt("id"));
				emp.setName(rsset.getString("name"));
				emp.setSalary(rsset.getDouble("salary"));

				employees.add(emp);
			}

		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			if (con != null) {
				try {
					con.close();
				} catch (SQLException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
		}

		return employees;

	}

	public void deleteEmployeeById(int id) {

		Connection con = pool.openConnection();

		String sql = "DELETE FROM Employee WHERE id = ?";

		try {

			PreparedStatement stat = con.prepareStatement(sql);

			stat.setInt(1, id);

			stat.executeUpdate();

		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			if (con != null) {
				try {
					con.close();
				} catch (SQLException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
		}
	}

	public void addEmployee(Employee emp) {

		Connection con = pool.openConnection();

		String sql = "INSERT INTO employee (name, salary) values (?,?)";

		try {

			PreparedStatement stat = con.prepareStatement(sql);

			stat.setString(1, emp.getName());
			stat.setDouble(2, emp.getSalary());

			stat.executeUpdate();

		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			if (con != null) {
				try {
					con.close();
				} catch (SQLException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
		}

	}
	
	public void updateEmployee(Employee emp){
		
		Connection con = pool.openConnection();
		
		String sql = "UPDATE Employee SET name = ?, salary = ? WHERE id = ?";
		
		try {
			
			PreparedStatement stat = con.prepareStatement(sql);
			
			stat.setString(1, emp.getName());
			stat.setDouble(2, emp.getSalary());
			stat.setInt(3, emp.getId());
			
			stat.executeUpdate();
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

}
