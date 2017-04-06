package com.security.database;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DNSDatabaseConnection {

	static Connection connection;

	private DNSDatabaseConnection() {
		try {
			Class.forName("com.mysql.jdbc.Driver");
			connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/testdatabase", "root", "root");
			if (connection != null) {
				System.out.println("DATABASE CONNECTED");
			} else {
				System.exit(0);
			}

		} catch (ClassNotFoundException | SQLException e) {
			e.printStackTrace();
		}
	}

	public static Connection getDatabaseConnection() {
		if (connection != null) {
			return connection;
		} else {
			new DNSDatabaseConnection();
			return connection;
		}

	}
}
