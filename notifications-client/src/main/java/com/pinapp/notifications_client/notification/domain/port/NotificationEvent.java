package com.pinapp.notifications_client.notification.domain.port;

import com.pinapp.notifications_client.notification.domain.event.NotificationEmailCreatedDomainEvent;
import com.pinapp.notifications_client.notification.domain.event.NotificationPushCreatedDomainEvent;
import com.pinapp.notifications_client.notification.domain.event.NotificationSmsCreatedDomainEvent;

public interface NotificationEvent {
    void sendEmailNotification(NotificationEmailCreatedDomainEvent event);

    void sendSmsNotification(NotificationSmsCreatedDomainEvent event);

    void sendPushNotification(NotificationPushCreatedDomainEvent event);
}
