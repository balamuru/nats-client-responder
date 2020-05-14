//package com.vgb.nats.client.responder.service;
//
//import com.vgb.nats.client.responder.config.NatsConfigProperties;
//import io.nats.client.Connection;
//import io.nats.client.Dispatcher;
//import org.springframework.beans.BeansException;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.beans.factory.config.BeanFactoryPostProcessor;
//import org.springframework.beans.factory.config.ConfigurableListableBeanFactory;
//import org.springframework.beans.factory.support.BeanDefinitionBuilder;
//import org.springframework.beans.factory.support.BeanDefinitionRegistry;
//import org.springframework.context.ApplicationContext;
//import org.springframework.stereotype.Component;
//
//import javax.annotation.PostConstruct;
//import java.nio.charset.StandardCharsets;
//
//@Component
//public class NatsClientInitializer implements BeanFactoryPostProcessor {
//
//    @Autowired
//    private Connection connection;
//
//    @Autowired
//    private NatsConfigProperties natsConfigProperties;
//
//
//    @Override
//    public void postProcessBeanFactory(ConfigurableListableBeanFactory configurableListableBeanFactory) throws BeansException {
//        final BeanDefinitionRegistry beanDefinitionRegistry = (BeanDefinitionRegistry) configurableListableBeanFactory;
//        System.err.println("Hello, listening");
//        System.err.println("#### " + natsConfigProperties.getUri());
//        natsConfigProperties.getSubjectsAndResponses().entrySet().forEach(subjectAndResponse -> {{
//            final String subject = subjectAndResponse.getKey();
//            final String response = subjectAndResponse.getValue();
//
//            //register dispatcher as bean
//            beanDefinitionRegistry.registerBeanDefinition(subject,
//                    BeanDefinitionBuilder
//                    .rootBeanDefinition(Dispatcher.class)
//                    .setFactoryMethodOnBean("createInstance", "dynamicBeanFactoryProcessor")
//                            .addConstructorArgValue(subject)
//                            .addConstructorArgValue(response)
//                            .addConstructorArgReference("connection")
//.addDependsOn("connection")
//                            .getBeanDefinition()
//                    );
//            System.err.println("Listening on NATS subject: " + subject);
//        }});
//
//    }
//
//    private Dispatcher createInstance(String subject, String response, Connection connection) {
//        return connection.createDispatcher((msg) -> {
//            String str = new String(msg.getData(), StandardCharsets.UTF_8);
//            System.err.println("Received message on subject("+ subject +") " + str);
//            if (msg.getReplyTo() != null && response != null && !response.trim().isEmpty()) {
//                //respond to the reply-to-queue
//                connection.publish(msg.getReplyTo(), response.getBytes());
//            }
//        });
//    }
//}
