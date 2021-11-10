package com.spring.fms.utils;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.net.HttpURLConnection;
import java.net.URL;

public class EmailSender {

	public void sendMail(String number, String content) {
		try {

			URL url = new URL("http://54.165.202.86:3333/sendText");
			HttpURLConnection con = (HttpURLConnection) url.openConnection();
			con.setRequestMethod("POST");
			con.setRequestProperty("Content-Type", "application/json");
			con.setRequestProperty("Accept", "application/json");			
			con.setDoOutput(true);
			String jsonInputString = "[{\"sessionName\":" 
					+ " \"alexcomercial\","
					+ "\"number\": \"" + number + " \",\"text\":\"" + content + "\"}]";
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

		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}

}


/*
CÓDIGO PARA COMUNICAR COM O SMS DEV

public void sendMail(String number, String content) {
	try {

		URL url = new URL("https://api.smsdev.com.br/v1/send");
		HttpURLConnection con = (HttpURLConnection) url.openConnection();
		con.setRequestMethod("POST");
		con.setRequestProperty("Content-Type", "application/json; utf-8");
		con.setRequestProperty("Accept", "application/json");
		con.setDoOutput(true);
		String jsonInputString = "[{\"key\":"
				+ "\"XJNCC5AI8VUIIQJVFAETMZIXTVTJMDGMET820QVS315IJI8KD9DZEYFREGVZIQ5MP2KIHFOJBJB5HRH747I1BWETA41JH1ES4Q00YW3PTP4WPXYJOE0UMJF07M2YH3CF\","
				+ "\"type\":9," + "\"number\":" + number + ",\"msg\":\"" + content + "\"}]";
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

	} catch (Exception e) {
		e.printStackTrace();
	}

}*/