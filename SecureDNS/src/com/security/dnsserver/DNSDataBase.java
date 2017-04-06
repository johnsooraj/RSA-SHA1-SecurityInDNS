package com.security.dnsserver;

import java.util.HashMap;
import java.util.Map;

public class DNSDataBase {

	static Map<String, String> serverIndex = new HashMap<>();

	public DNSDataBase() {
		System.out.println("Server Index Initialized");
		serverIndex.put("/www.antcolony.com", "webcontent/antcolony/index.html");
		serverIndex.put("/www.samcomtech.com", "webcontent/samcomtech/index.html");
		serverIndex.put("/www.myblog.com", "webcontent/myblog/index.html");
	}

	public static String getProjectPath(String address) {
		return serverIndex.get(address);

	}
}
