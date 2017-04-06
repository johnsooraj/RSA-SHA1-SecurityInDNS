package com.security.database;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class Loginvalidation {

	public static boolean loginValidation(String username, String password, Connection connection) {
		try {
			String query = "SELECT * FROM testdatabase.userdata WHERE username=? AND password=?";
			PreparedStatement preparedStatement;
			preparedStatement = connection.prepareStatement(query);
			preparedStatement.setString(1, username);
			preparedStatement.setString(2, password);
			ResultSet resultSet = preparedStatement.executeQuery();
			while (resultSet.next()) {
				if (resultSet.getString("username").equals(username)
						&& resultSet.getString("password").equals(password)) {
					return true;
				} else {
					return false;
				} 
			}

		} catch (SQLException e) {
			e.printStackTrace();
			return false;
		}
		return false;
	}
}
