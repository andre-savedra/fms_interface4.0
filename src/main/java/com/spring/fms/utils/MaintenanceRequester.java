package com.spring.fms.utils;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.net.HttpURLConnection;
import java.net.URL;
import java.time.LocalDateTime;
import java.util.ArrayList;

import org.springframework.beans.factory.annotation.Autowired;

import com.spring.fms.model.ManutVariables;
import com.spring.fms.service.ManutVariablesService;

public class MaintenanceRequester {
	
	
	public boolean check(String tokenRegister, ManutVariables manut) {
		
		boolean status = false;
		
		try {
			ArrayList<String> reasons = new ArrayList<>();
							
			if(manut.getCounterClamping() >= manut.getCounterClampingMax())
			{
				reasons.add("Ciclos de fixação");
			}
			else if(manut.getCounterPart() >= manut.getCounterPartMax())
			{
				reasons.add("Geral (ciclos realizados)");
			}
			else if(manut.getCounterPort() >= manut.getCounterPortMax())
			{
				reasons.add("Ciclos de porta");
			}
			else if(manut.getHoursMachining() >= manut.getHoursMachiningMax())
			{
				reasons.add("Horas Trabalhadas");
			}
			
			String message = "Manutenção Necessária: ";
			
			for(String reason : reasons)
			{
				message += reason + ", ";
			}			
			
			
			if(reasons.size() > 0)
			{
				URL url = new URL("https://api.oimelvin.com.br/api/SolicitacaoManutencao");
				HttpURLConnection con = (HttpURLConnection) url.openConnection();
				con.setRequestMethod("POST");
				con.setRequestProperty("Content-Type", "application/json");
				con.setRequestProperty("Authorization", tokenRegister);			
				con.setDoOutput(true);
				
				String jsonInputString = 
					"{\n\"Canal\":" + " \"Sistema\",\n" +
					"\"CodEquipamento\": " + manut.getId().toString() + ", \n" +
					"\"NomeEquipamento\": \"" + manut.getMachine().getName() + "\", \n" +
					"\"Urgencia\":" + " \"ALTA\",\n" +
					"\"Solicitacao\": \"" + message + "\", \n" +
					"\"Arquivada\":" + " \"NÃO\",\n" +
					"\"CodOrdem\": " + 1 + ", \n" +
					"\"Data\": \"" + LocalDateTime.now() + "\", \n" +
					"\"Solicitante\":" + " \"FMS\",\n" +
					"\"Justificativa\": \"PREVENTIVA: " + message + "\", \n" +
					"\"StatusSolicitacao\": " + 0 + ", \n" +
					"\"EquipamentoParado\": " + true + "\n}";					
					
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
				
				manut.setRequested(true);
				System.out.println("Vai salvar manut com id: " + manut.getId());
				
				status = true;
			}
			else {
				System.out.println("Não precisa de pedido de manutenção");
			}
			

		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return status;

	}

}
