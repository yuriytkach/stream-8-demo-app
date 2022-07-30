package org.acme;

import javax.inject.Inject;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

import org.eclipse.microprofile.reactive.messaging.Channel;
import org.eclipse.microprofile.reactive.messaging.Emitter;
import org.eclipse.microprofile.reactive.messaging.Message;
import org.eclipse.microprofile.reactive.messaging.Metadata;

import io.smallrye.reactive.messaging.kafka.api.OutgoingKafkaRecordMetadata;

@Path("/")
public class GreetingResource {

    @Inject
    @Channel("words-out")
    Emitter<String> emitter;

    @GET
    @Path("hello/{word}")
    @Produces(MediaType.TEXT_PLAIN)
    public String hello(
      @PathParam("word") final String word
    ) {

       var metadata = OutgoingKafkaRecordMetadata.builder()
          .withKey("id-" + word.length())
          .build();


        final Message<String> msg = Message.of(word, Metadata.of(metadata));
        emitter.send(msg);

//        emitter.send(word);
        return "Sent: " + word;
    }
}
