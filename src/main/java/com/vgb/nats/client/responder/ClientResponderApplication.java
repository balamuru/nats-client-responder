package com.vgb.nats.client.responder;

import com.vgb.nats.client.responder.config.NatsConfigProperties;
import com.vgb.nats.client.responder.service.NatsClientListener;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.properties.EnableConfigurationProperties;

@SpringBootApplication
@EnableConfigurationProperties(NatsConfigProperties.class)
public class ClientResponderApplication implements ApplicationRunner {
    @Autowired
    private NatsClientListener natsClientListener;

    public static void main(String[] args) {
        SpringApplication.run(ClientResponderApplication.class, args);
    }

    @Override
    public void run(ApplicationArguments args) throws Exception {
        natsClientListener.listen();
    }
}
