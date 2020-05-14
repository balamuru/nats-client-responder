package com.vgb.nats.client.responder.service;

import com.vgb.nats.client.responder.config.NatsConfigProperties;
import io.nats.client.Connection;
import io.nats.client.Dispatcher;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
import java.nio.charset.StandardCharsets;
import java.util.concurrent.CountDownLatch;

@Component
public class NatsClientListener {

    @Autowired
    private Connection nc;

    @Autowired
    private NatsConfigProperties natsConfigProperties;

    @PostConstruct
    public void listen() throws InterruptedException {
        System.err.println("Hello, listening");
        final CountDownLatch latch = new CountDownLatch(1000);

        System.err.println("#### " + natsConfigProperties.getUri());
        natsConfigProperties.getSubjectsAndResponses().entrySet().forEach(subjectAndResponse -> {{
            final String subject = subjectAndResponse.getKey();
            final String response = subjectAndResponse.getValue();
            final Dispatcher d = nc.createDispatcher((msg) -> {
                String str = new String(msg.getData(), StandardCharsets.UTF_8);
                System.err.println("Received message on subject("+ subject +") " + str);
                if (msg.getReplyTo() != null && response != null && !response.trim().isEmpty()) {
                    //respond to the reply-to-queue
                    nc.publish(msg.getReplyTo(), response.getBytes());
                }
                latch.countDown();
            });
            d.subscribe(subject);
            System.err.println("Listening on NATS subject: " + subject);
        }});



        latch.await();
    }
}
