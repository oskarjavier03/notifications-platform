package com.pinapp.notifications_client.notification.infrastructure.api;

import com.pinapp.notifications_client.notification.domain.event.NotificationEmailCreatedDomainEvent;
import com.pinapp.notifications_client.notification.domain.event.NotificationPushCreatedDomainEvent;
import com.pinapp.notifications_client.notification.domain.event.NotificationSmsCreatedDomainEvent;
import com.pinapp.notifications_client.notification.domain.event.NotificationType;
import com.pinapp.notifications_client.notification.domain.port.NotificationRepository;
import com.pinapp.notifications_client.notification.infrastructure.event.producer.NotificationEventProducer;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
@Slf4j
public class NotificationRepositoryImpl implements NotificationRepository {

    private final NotificationEventProducer notificationEventProducer;


    @Override
    @Async
    public void generateNotificationEmailAsync(Integer i) {
        NotificationEmailCreatedDomainEvent event=NotificationEmailCreatedDomainEvent.builder()
                .email("hola"+i+"@gmail.com")
                .type(NotificationType.email)
                .subject("hola mundo")
                .message("Hola, como estas?")
                .build();
        notificationEventProducer.sendEmailNotification(event);
    }

    @Override
    @Async
    public void generateNotificationSmsAsync(Integer i) {
        NotificationSmsCreatedDomainEvent event=NotificationSmsCreatedDomainEvent.builder()
                .numberCelPhone("264486658"+i)
                .message("hola mundo "+i)
                .type(NotificationType.SMS)
                .build();
        notificationEventProducer.sendSmsNotification(event);
    }

    @Override
    @Async
    public void generateNotificationPushAsync(Integer i) {
        NotificationPushCreatedDomainEvent event=NotificationPushCreatedDomainEvent.builder()
                .title("hola "+i)
                .type(NotificationType.pushNotification)
                .destination("hola numero "+i)
                .sender("user"+i)
                .message("hola, como estas?")
                .permission("sdfsdfs"+i)
                .build();
        notificationEventProducer.sendPushNotification(event);
    }
}
