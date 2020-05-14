package com.vgb.nats.client.responder.config;

import io.nats.client.Connection;
import io.nats.client.Nats;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.io.IOException;

@Configuration
public class AppConfiguration {

    @Autowired
    private NatsConfigProperties natsConfigProperties;

    @Bean("connection")
    Connection connection() throws IOException, InterruptedException {
        return Nats.connect(natsConfigProperties.getUri());
    }

}
