 package com.security.controller;

import java.awt.Desktop;
import java.io.IOException;
import java.net.InetAddress;
import java.net.ServerSocket;
import java.net.Socket;
import java.net.URI;
import java.net.URISyntaxException;

import com.security.dnsserver.DNSDataBase;
import com.security.dnsserver.SercurityServer;

public class SecurityController {
	public SecurityController() {
		ServerSocket Server;
		try {
			new DNSDataBase();
			Server = new ServerSocket(5000, 10, InetAddress.getLocalHost());
			System.out.println("TCPServer Waiting for client on port 5000");
			if (Desktop.isDesktopSupported()) {
				Desktop.getDesktop().browse(new URI("http://" + InetAddress.getLocalHost().getHostAddress() + ":5000"));
			}
			while (true) {
				Socket connected = Server.accept();
				(new SercurityServer(connected)).start();
			}
		} catch (IOException | URISyntaxException e) {
			e.printStackTrace();
		}
	}
}
