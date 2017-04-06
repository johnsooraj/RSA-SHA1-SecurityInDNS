package com.security.encryption;

import java.io.BufferedReader;
import java.io.DataOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.net.Socket;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.StringTokenizer;

public class IPDatabase extends Thread {

	Socket socket;
	Connection connection;
	BufferedReader buffReader;
	DataOutputStream senderStream;
	PreparedStatement preparedStatement;
	String requestURL;
	int bitSize;

	public IPDatabase(Connection connection, Socket socket) {
		this.connection = connection;
		this.socket = socket;
	}

	@Override
	public void run() {
		try {
			if (connection != null) {
				ObjectInputStream objectInputStream = new ObjectInputStream(socket.getInputStream());
				EncryptionValueHolder holder = (EncryptionValueHolder) objectInputStream.readObject();
				String rsaPlaneText = new RSADecryption(holder).decryptHexCipherToPlainMsg();
				System.out.println("\n RSA DECRYPTION : \n" + rsaPlaneText + "\n");
				String sh1PlaneText = new SH1Decryption(holder).DecryptText(rsaPlaneText);
				StringTokenizer stringTokenizer = new StringTokenizer(sh1PlaneText);
				String httpUrl = stringTokenizer.nextToken("/");
				System.out.println(" SHA1 DECRYPTION : \n" + httpUrl + "\n");
				DataOutputStream dout = new DataOutputStream(socket.getOutputStream());
				dout.writeUTF(getFileLocation(httpUrl));
				// dout.writeUTF("hello");

			}
		} catch (IOException | ClassNotFoundException e) {
			e.printStackTrace();
		}
	}

	String ipaddress;

	public String getFileLocation(String url) {

		String query = "SELECT * FROM dnsindex WHERE url=?";
		try {
			preparedStatement = connection.prepareStatement(query);
			preparedStatement.setString(1, url);
			ResultSet resultSet = preparedStatement.executeQuery();
			while (resultSet.next()) {
				ipaddress = resultSet.getString("ipaddress");
			}
			if (ipaddress != null) {
				return getIPAddress(ipaddress);
			} else {
				return "errorpage.jsp";
			}
		} catch (SQLException e) {
			e.printStackTrace();
			return "errorpage.jsp";
		}

	}

	String fileLocation;

	public String getIPAddress(String ip) {
		String query = "SELECT * FROM dnsindexfilelocation WHERE ipaddress=?";
		try {
			preparedStatement = connection.prepareStatement(query);
			preparedStatement.setString(1, ip);
			ResultSet resultSet = preparedStatement.executeQuery();
			while (resultSet.next()) {
				fileLocation = resultSet.getString("filelocation");
			}
			if (fileLocation != null) {
				return fileLocation;
			} else {
				return "errorpage.jsp";
			}
		} catch (SQLException e) {
			e.printStackTrace();
			return "errorpage.jsp";
		}
	}

}
