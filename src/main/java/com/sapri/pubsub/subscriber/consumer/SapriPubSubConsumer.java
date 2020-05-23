package com.sapri.pubsub.subscriber.consumer;

import org.springframework.cloud.gcp.pubsub.support.BasicAcknowledgeablePubsubMessage;
import org.springframework.stereotype.Component;

import com.google.pubsub.v1.PubsubMessage;

import lombok.extern.slf4j.Slf4j;

@Component
@Slf4j
public class SapriPubSubConsumer extends PubSubConsumer {

	@Override
	public String subscription() {
		return "hello-pubsub-subscription";
	}

	@Override
	protected void consume(BasicAcknowledgeablePubsubMessage acknowledgeablePubsubMessage) {
		
		PubsubMessage message = acknowledgeablePubsubMessage.getPubsubMessage();
		
		SapriPubSubConsumer.log.info(String.format("Message received: %s", message.getData().toStringUtf8()));
		
		acknowledgeablePubsubMessage.ack();
		
	}

}
