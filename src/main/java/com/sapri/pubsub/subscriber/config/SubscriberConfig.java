package com.sapri.pubsub.subscriber.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.context.event.ApplicationReadyEvent;
import org.springframework.cloud.gcp.pubsub.core.PubSubTemplate;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.event.EventListener;

import com.sapri.pubsub.subscriber.consumer.PubSubConsumer;

import lombok.extern.slf4j.Slf4j;

@Configuration
@Slf4j
public class SubscriberConfig {

	private PubSubTemplate pubSubTemplate;
	private PubSubConsumer pubSubConsumer;
	
	@Autowired
	public SubscriberConfig(PubSubTemplate pubSubTemplate, PubSubConsumer pubSubConsumer) {
		super();
		this.pubSubTemplate = pubSubTemplate;
		this.pubSubConsumer = pubSubConsumer;
	}
	
	@EventListener(ApplicationReadyEvent.class)
	public void subscribe() {
		SubscriberConfig.log.info("Subscribing {} to {}", pubSubConsumer.getClass().getSimpleName(),
				pubSubConsumer.subscription());
		
		this.pubSubTemplate.subscribe(this.pubSubConsumer.subscription(), this.pubSubConsumer.consumer());
	}
	
}
