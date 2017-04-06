package com.security.encryption;

import java.io.IOException;
import java.net.InetAddress;
import java.net.ServerSocket;
import java.net.Socket;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DNSServer {

	ServerSocket server;
	Connection connection;

	public DNSServer() {

		try {
			Class.forName("com.mysql.jdbc.Driver");
			connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/testdatabase", "root", "root");
			if (connection != null) {
				System.err.println("DNS Server DATABASE CONNECTED");
			} else {
				System.out.println("ERROR IN DATABASE CONNECTION..");
				System.exit(0);
			}

			server = new ServerSocket(5004, 10, InetAddress.getLocalHost());
			while (true) {
				System.err.println("WAITING FOR CLIENT ACCESS :");
				Socket socket = server.accept();
				(new IPDatabase(connection, socket)).start();
			}

		} catch (IOException | ClassNotFoundException | SQLException e) {
			e.printStackTrace();
		}

	}

	public static void main(String[] args) {
		new DNSServer();
	}
}
