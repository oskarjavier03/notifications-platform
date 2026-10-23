package com.pinapp.notifications_client.notification.domain.port;



public interface NotificationRepository {
    void generateNotificationEmailAsync(Integer i);
    void generateNotificationSmsAsync(Integer i);
    void generateNotificationPushAsync(Integer i);
}
