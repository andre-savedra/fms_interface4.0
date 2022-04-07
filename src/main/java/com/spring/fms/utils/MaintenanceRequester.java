package com.spring.fms.utils;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.net.HttpURLConnection;
import java.net.URL;

import com.spring.fms.model.ManutVariables;

public class MaintenanceRequester {

	public void check(String tokenRegister, ManutVariables manut) {
		try {
			/*
			URL url = new URL("http://54.165.202.86:3333/sendText");
			HttpURLConnection con = (HttpURLConnection) url.openConnection();
			con.setRequestMethod("POST");
			con.setRequestProperty("Content-Type", "application/json");
			con.setRequestProperty("Accept", "application/json");			
			con.setDoOutput(true);			
			String jsonInputString = "{\n\"sessionName\":" 
					+ " \"andrezap\",\n"
					+ "\"number\": \"" + "55" + number + "\",\n\"text\":\"" + content + "\"\n}";
			System.out.println("jsonInputString");
			System.out.println(jsonInputString);
			
			try (OutputStream os = con.getOutputStream()) {
				byte[] input = jsonInputString.getBytes("utf-8");
				os.write(input, 0, input.length);
			}

			try (BufferedReader br = new BufferedReader(new InputStreamReader(con.getInputStream(), "utf-8"))) {
				StringBuilder response = new StringBuilder();
				String responseLine = null;
				while ((responseLine = br.readLine()) != null) {
					response.append(responseLine.trim());
				}
				System.out.println(response.toString());
			}
			*/

		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}

}
