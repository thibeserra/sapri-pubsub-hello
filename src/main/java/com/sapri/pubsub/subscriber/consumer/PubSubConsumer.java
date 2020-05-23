package com.sapri.pubsub.subscriber.consumer;

import java.util.function.Consumer;

import org.springframework.cloud.gcp.pubsub.support.BasicAcknowledgeablePubsubMessage;


public abstract class PubSubConsumer {

    /**
     * Name of an existing Pub/Sub subscription.
     */
    public abstract String subscription();

    /**
     * The actual consumer logic.
     *
     * @param message Wrapper of a Pub/Sub message that adds acknowledging capability.
     */
    protected abstract void consume(BasicAcknowledgeablePubsubMessage message);

    /**
     * Implementation of a {@link Consumer} functional interface which only calls the
     * {@link #consume(BasicAcknowledgeablePubsubMessage) consume} method.
     */
    public Consumer<BasicAcknowledgeablePubsubMessage> consumer() {
        return basicAcknowledgeablePubsubMessage -> consume(basicAcknowledgeablePubsubMessage);
    }

}
