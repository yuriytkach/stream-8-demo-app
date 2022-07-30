package org.acme;

import java.util.stream.Stream;

import javax.enterprise.context.ApplicationScoped;
import javax.enterprise.event.Observes;
import javax.inject.Inject;

import org.apache.kafka.clients.consumer.ConsumerRecord;
import org.eclipse.microprofile.reactive.messaging.Channel;
import org.eclipse.microprofile.reactive.messaging.Emitter;
import org.eclipse.microprofile.reactive.messaging.Incoming;
import org.eclipse.microprofile.reactive.messaging.Message;
import org.eclipse.microprofile.reactive.messaging.Outgoing;

import io.quarkus.runtime.StartupEvent;
import io.smallrye.mutiny.Uni;

@ApplicationScoped
public class MyReactiveMessagingApplication {

    @Inject
    @Channel("words-out")
    Emitter<String> emitter;

    /**
     * Sends message to the "words-out" channel, can be used from a JAX-RS resource or any bean of your application.
     * Messages are sent to the broker.
     **/
    void onStart(@Observes StartupEvent ev) {
        Stream.of("Hello", "with", "SmallRye", "reactive", "message").forEach(string -> emitter.send(string));
    }

    /**
     * Consume the message from the "words-in" channel, uppercase it and send it to the uppercase channel.
     * Messages come from the broker.
     **/
    @Incoming("words-in")
    @Outgoing("uppercase")
    public Message<String> toUpperCase(Message<ConsumerRecord<String, String>> message) {
        System.out.println("Received: " + message.getPayload().key() + "=" + message.getPayload().value());
        return message.withPayload(message.getPayload().value().toUpperCase());
    }

    /**
     * Consume the uppercase channel (in-memory) and print the messages.
     **/
    @Incoming("uppercase")
    public void sink(String word) {
        System.out.println(">> " + word);
    }

    @Incoming("spring-words-in")
    public Uni<Void> fromSpring(Message<ConsumerRecord<String, String>> message) {
        System.out.println(">>>> Received: " + message.getPayload().key() + "=" + message.getPayload().value());
        return Uni.createFrom().voidItem();
    }
}
