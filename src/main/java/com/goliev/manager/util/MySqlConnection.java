package com.goliev.manager.util;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.sql.Connection;
import java.sql.Driver;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.Properties;

import snaq.db.ConnectionPool;


public class MySqlConnection {

	private static MySqlConnection singletonConnection = null;
	
	private static ConnectionPool connectionPool = null;
	
	private long idleTimeout;
	
	private boolean flag = true;
	
	private MySqlConnection(){
		
		Properties prop = new Properties();
		Class<?> clazz = null;
		
		try {
			
			//InputStream in = getClass().getResourceAsStream("/mysqlConnection.properties");
			prop.load(Thread.currentThread().getContextClassLoader().getResourceAsStream("mysqlConnection.properties"));

		} catch (FileNotFoundException e1) {
			e1.printStackTrace();
		} catch (IOException e1) {	
			e1.printStackTrace();
		}
		
		try {
			
			clazz = Class.forName(prop.getProperty("driverManager"));
			
		} catch (ClassNotFoundException e) {
			flag = false;
			e.printStackTrace();
		}
		
		Driver driver = null;
		
		try {
			
			driver = (Driver) clazz.newInstance();
			
		} catch (InstantiationException e) {
			flag = false;
			e.printStackTrace();
		} catch (IllegalAccessException e) {
			flag = false;
			e.printStackTrace();
		}
		
		try {
			
			DriverManager.registerDriver(driver);
			
		} catch (SQLException e) {
			flag = false;
			e.printStackTrace();
		}
		
		if(flag){
			
			String name = "LocalPool";
			int minPool = 3;
			int maxPool = 5;
			int maxSize = 15;
			idleTimeout = 30;
			
			String url = prop.getProperty("url");
			String user = prop.getProperty("user");
			String password = prop.getProperty("password");
			
			/*
			String url = "jdbc:mysql://localhost:3306/manager";
			String user = "root";
			String password = "123456";
			*/
			
			try {
				connectionPool = new ConnectionPool(name, minPool, maxPool, maxSize, idleTimeout, url, user, password);
			} finally {
				connectionPool.registerShutdownHook();
			}
		}
	}
	
	public Connection openConnection(){
		
		Connection con = null;
		
		try {
			
			con = connectionPool.getConnection();
			flag = true;
			
		} catch (SQLException e) {
			flag = false;
			e.printStackTrace();
		} 
		
		return con;
	}
	
	public static MySqlConnection getInstance(){
		
		if(singletonConnection == null){
			singletonConnection = new MySqlConnection();
		}
		
		return singletonConnection;
	}
	
	public boolean getConnectionStatus(){
		return flag;
	}
}
