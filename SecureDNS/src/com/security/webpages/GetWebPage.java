package com.security.webpages;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;

public class GetWebPage {

	static StringBuilder out = new StringBuilder();

	public GetWebPage() {

	}

	public static String getPageCode(String page) {
		try {
			InputStream in = new FileInputStream(new File(page));
			BufferedReader reader = new BufferedReader(new InputStreamReader(in));

			String line;
			while ((line = reader.readLine()) != null) {
				out.append(line);
			}
			reader.close();
			return out.toString();
		} catch (IOException e) {
			e.printStackTrace();
		}
		return "";
	}
}
