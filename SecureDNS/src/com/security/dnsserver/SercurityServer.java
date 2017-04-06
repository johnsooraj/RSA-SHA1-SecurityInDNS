package com.security.dnsserver;

import java.io.BufferedReader;
import java.io.DataOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.Socket;
import java.net.URLDecoder;
import java.util.StringTokenizer;

import com.security.encryption.EncryptionController;

public class SercurityServer extends Thread {

	Socket connection;
	String responseString;
	BufferedReader inFromClient;
	DataOutputStream outToClient;
	StringBuilder out = new StringBuilder();

	public SercurityServer(Socket socket) {
		this.connection = socket;
	}

	@SuppressWarnings("deprecation")
	@Override
	public void run() {
		try {
			inFromClient = new BufferedReader(new InputStreamReader(connection.getInputStream()));
			outToClient = new DataOutputStream(connection.getOutputStream());

			// Accepting HTTP Request From Browser
			String requestString = inFromClient.readLine();
			String headerLine = requestString;

			// System.out.println(headerLine);

			// Splitting HTTP Request Two Part
			StringTokenizer tokenizer = new StringTokenizer(headerLine);

			// HTTP Method
			String httpMethod = tokenizer.nextToken();

			// URL Pattern
			String httpQueryString = tokenizer.nextToken();

			StringBuffer responseBuffer = new StringBuffer();

			if (httpMethod.equals("GET")) {
				if (httpQueryString.equals("/")) {
					// HTTP Request with no URL Pattern, EG: 127.0.0.1:5000/
					sendResponse(200, responseBuffer.toString(), false);
				} else if (DNSDataBase.getProjectPath(httpQueryString) != null) {
					System.out.println("DNS SERVER TRUE " + httpQueryString);
					// String fileName = "webcontent/samcomtech/index.html";
					String fileName = new EncryptionController().getURL(httpQueryString);
					System.out.println(fileName);
					fileName = URLDecoder.decode(fileName);
					if (new File(fileName).isFile()) {
						System.out.println("FILE NAME is = " + fileName);
						sendResponse(200, fileName, true);

					} else {
						sendResponse(404, "<b>The Requested resource not found ...."
								+ "Usage: http://127.0.0.1:5000 or http://127.0.0.1:5000/</b>", false);
					}

				} else {
					// This is interpreted as a file name
					String fileName = httpQueryString.replaceFirst("/", "");
					fileName = URLDecoder.decode(fileName);
					if (new File(fileName).isFile()) {
						sendResponse(200, fileName, true);
					} else {
						sendResponse(404, "<b>The Requested resource not found ...."
								+ "Usage: http://127.0.0.1:5000 or http://127.0.0.1:5000/</b>", false);
					}
				}
			} else {
				sendResponse(404, "<b>The Requested cannot be processed ...." + " <br> Request is POST : </b>", false);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public void sendResponse(int statusCode, String responseString, boolean isFile) throws Exception {

		String statusLine = null;
		String serverdetails = "Server: Java HTTPServer";
		String contentLengthLine = null;
		String fileName = null;
		String contentTypeLine = "Content-Type: text/html" + "\r\n";
		FileInputStream fin = null;

		if (statusCode == 200)
			statusLine = "HTTP/1.1 200 OK" + "\r\n";
		else
			statusLine = "HTTP/1.1 404 Not Found" + "\r\n";

		if (isFile) {
			fileName = responseString;
			fin = new FileInputStream(fileName);
			contentLengthLine = "Content-Length: " + Integer.toString(fin.available()) + "\r\n";
			if (!fileName.endsWith(".htm") && !fileName.endsWith(".html"))
				contentTypeLine = "Content-Type: \r\n";
		} else {
			try {
				InputStream in = new FileInputStream("webcontent/home.jsp");
				BufferedReader reader = new BufferedReader(new InputStreamReader(in));

				String line;
				while ((line = reader.readLine()) != null) {
					out.append(line);
				}
				reader.close();

			} catch (IOException e) {
				e.printStackTrace();
			}
			responseString = out.toString();
			contentLengthLine = "Content-Length: " + responseString.length() + "\r\n";
		}

		outToClient.writeBytes(statusLine);
		outToClient.writeBytes(serverdetails);
		outToClient.writeBytes(contentTypeLine);
		outToClient.writeBytes(contentLengthLine);
		outToClient.writeBytes("Connection: close\r\n");
		outToClient.writeBytes("\r\n");

		if (isFile)
			sendFile(fin, outToClient);
		else
			outToClient.writeBytes(responseString);

		outToClient.close();
	}

	public void sendFile(FileInputStream fin, DataOutputStream out) throws Exception {
		byte[] buffer = new byte[1024];
		int bytesRead;

		while ((bytesRead = fin.read(buffer)) != -1) {
			out.write(buffer, 0, bytesRead);
		}
		fin.close();
	}

}
