package com.security.encryption;

import java.io.BufferedReader;
import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.io.ObjectOutputStream;
import java.net.InetAddress;
import java.net.Socket;

public class DNSServerConnector {

	Socket socket;
	String reponseURL;
	BufferedReader buffReader;
	DataOutputStream senderStream;

	public DNSServerConnector(EncryptionValueHolder encryptionValueHolder) {
		try {
			socket = new Socket(InetAddress.getLocalHost(), 5004);

			ObjectOutputStream objectOutputStream = new ObjectOutputStream(socket.getOutputStream());
			objectOutputStream.writeObject(encryptionValueHolder);

			DataInputStream dis = new DataInputStream(socket.getInputStream());
			setReponseURL(dis.readUTF());

		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	public String getReponseURL() {
		return reponseURL;
	}

	public void setReponseURL(String reponseURL) {
		this.reponseURL = reponseURL;
	}
}
