package com.wendel.DesafioPicpay.configs;

import java.util.HashMap;
import java.util.Map;

import org.apache.kafka.clients.consumer.ConsumerConfig;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.autoconfigure.kafka.KafkaProperties.Retry.Topic.Backoff;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.kafka.annotation.EnableKafka;
import org.springframework.kafka.config.ConcurrentKafkaListenerContainerFactory;
import org.springframework.kafka.core.ConsumerFactory;
import org.springframework.kafka.core.DefaultKafkaConsumerFactory;
import org.springframework.kafka.listener.DefaultErrorHandler;
import org.springframework.kafka.listener.ContainerProperties.AckMode;
import org.springframework.kafka.support.serializer.ErrorHandlingDeserializer;
import org.springframework.kafka.support.serializer.JsonDeserializer;
import org.springframework.util.backoff.FixedBackOff;

import com.wendel.DesafioPicpay.dtos.EmailDTO;
import com.wendel.DesafioPicpay.dtos.UserResponseDTO;

@EnableKafka
@Configuration
public class KafkaConsumerConfig {
	
	@Value("${spring.kafka.bootstrap-servers}")
	private String bootstrapAddress;
	
	@Value(value = "${spring.kafka.backoff.interval}")
	private Long interval;

	@Value(value = "${spring.kafka.backoff.max_failure}")
	private Long maxAttempts;
	
	@Bean
	public ConsumerFactory<String, EmailDTO> emailConsumerFactory() {
		Map<String, Object> props = new HashMap<>();
		props.put(ConsumerConfig.BOOTSTRAP_SERVERS_CONFIG, bootstrapAddress);
		props.put(ConsumerConfig.GROUP_ID_CONFIG, "email-processed");
		props.put(ConsumerConfig.KEY_DESERIALIZER_CLASS_CONFIG, ErrorHandlingDeserializer.class);
		props.put(ConsumerConfig.VALUE_DESERIALIZER_CLASS_CONFIG, ErrorHandlingDeserializer.class);
		props.put(ErrorHandlingDeserializer.KEY_DESERIALIZER_CLASS, JsonDeserializer.class);
		props.put(ErrorHandlingDeserializer.VALUE_DESERIALIZER_CLASS, JsonDeserializer.class);
		props.put(JsonDeserializer.TRUSTED_PACKAGES, "*");
		props.put(JsonDeserializer.VALUE_DEFAULT_TYPE, "com.wendel.DesafioPicpay.dtos.EmailDTO");
		return new DefaultKafkaConsumerFactory<>(props);
	}
	
	@Bean
	public ConcurrentKafkaListenerContainerFactory<String, EmailDTO> emailKafkaListenerContainerFactory() {
		ConcurrentKafkaListenerContainerFactory<String, EmailDTO> factory = new ConcurrentKafkaListenerContainerFactory<>();
		factory.setConsumerFactory(emailConsumerFactory());
		factory.setCommonErrorHandler(errorHandler());
		factory.getContainerProperties().setAckMode(AckMode.RECORD);
		return factory;
	}
	
	@Bean
	public DefaultErrorHandler errorHandler() {
		FixedBackOff fixedBackOff = new FixedBackOff(interval, maxAttempts);
		DefaultErrorHandler errorHandler = new DefaultErrorHandler((consumerRecord, exception) -> {
			System.out.println("Deu ruim!");
		}, fixedBackOff);
		
		return errorHandler;
	}

}
