package com.wendel.DesafioPicpay.services;

import java.util.HashMap;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.kafka.annotation.RetryableTopic;
import org.springframework.kafka.annotation.TopicPartition;
import org.springframework.retry.annotation.Backoff;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import com.wendel.DesafioPicpay.dtos.EmailDTO;
import com.wendel.DesafioPicpay.services.exceptions.ProcessingServiceErrorException;

@Service
public class EmailService {

	@Value("${url-util-devi}")
	private String urlUtilDevi;

	@Autowired
	private RestTemplate restTemplate;

	public void sendEmail() {
		Map<String, String> request = new HashMap<>();

		request.put("title", "Pagamento");
		request.put("body", "Transferência realizada");

		try {
			restTemplate.postForObject(urlUtilDevi + "v1/notify", request, String.class);
		} catch (Exception e) {
			throw new ProcessingServiceErrorException("Erro ao enviar email.");
		}
	}

	@KafkaListener(topicPartitions = @TopicPartition(topic = "email-processed", partitions = { "0",
			"1" }), containerFactory = "emailKafkaListenerContainerFactory")
//	@RetryableTopic(
//				backoff = @Backoff(value = 3000L),
//				attempts = "2",
//				autoCreateTopics = "false"
//			)
	public void emailListener(EmailDTO email) {
		System.out.println("Recebido mensagem do consumidor: " + email);
		Map<String, String> request = new HashMap<>();

		request.put("title", "Pagamento");
		request.put("body", "Transferência realizada");

		try {
			restTemplate.postForObject(urlUtilDevi + "v1/notify", request, String.class);
			System.out.println("Deu bom");
		} catch (Exception e) {
			System.out.println("Erro email");
			throw new ProcessingServiceErrorException("Erro ao enviar email.");
		}
	}

}
