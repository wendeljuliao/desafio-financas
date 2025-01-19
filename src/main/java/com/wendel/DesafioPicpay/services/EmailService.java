package com.wendel.DesafioPicpay.services;

import java.util.HashMap;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

@Service
public class EmailService {
	
	@Value("${url-util-devi}")
	private String urlUtilDevi;
	
	@Autowired
	private RestTemplate restTemplate;
	
	public void enviarEmail() {
		Map<String, String> request = new HashMap<>();
		
		request.put("title", "Pagamento");
		request.put("body", "Transferência realizada");
		
		try {
			restTemplate.postForObject(urlUtilDevi + "v1/notify", request, String.class);
		} catch (Exception e) {
			throw new RuntimeException("Erro ao enviar email.");
		}
	}
	
}
