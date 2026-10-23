package com.pinapp.notifications_api.common.infrastructure.config;

import notifications_lib.common.config.NotificationFactory;
import notifications_lib.common.config.NotificationLibrary;
import notifications_lib.email.domain.port.EmailRepository;
import notifications_lib.email.infrastructure.EmailRepositoryImpl;
import notifications_lib.push.domain.port.PushRepository;
import notifications_lib.push.infrastructure.PushRepositoryImpl;
import notifications_lib.sms.domain.port.SmsRepository;
import notifications_lib.sms.infrastructure.SmsRepositoryImpl;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;



@Configuration
public class NotificationConfiguration {

    @Bean
    public EmailRepository emailRepository() {
        return new EmailRepositoryImpl();
    }

    @Bean
    public SmsRepository smsRepository() {
        return new SmsRepositoryImpl();
    }

    @Bean
    public PushRepository pushRepository() {
        return new PushRepositoryImpl();
    }

    @Bean(destroyMethod = "close")
    public NotificationLibrary notificationLibrary(
            EmailRepository emailRepository,
            SmsRepository smsRepository,
            PushRepository pushRepository) {

        return NotificationFactory.create(
                emailRepository,
                smsRepository,
                pushRepository
        );
    }
}