package com.pinapp.notifications_api.notification.domain.port;

import com.pinapp.notifications_api.notification.domain.entity.EmailNotificationEvent;
import com.pinapp.notifications_api.notification.domain.entity.PushNotificationEvent;
import com.pinapp.notifications_api.notification.domain.entity.SmsNotificationEvent;

public interface NotificationEvent {
    void sendNotificationEmailEvent(EmailNotificationEvent event);

    void sendNotificationSmsEvent(SmsNotificationEvent event);

    void sendNotificationPushEvent(PushNotificationEvent event);


}
