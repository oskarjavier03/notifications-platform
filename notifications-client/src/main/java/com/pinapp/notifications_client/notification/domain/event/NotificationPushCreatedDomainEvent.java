package com.pinapp.notifications_client.notification.domain.event;


import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class NotificationPushCreatedDomainEvent {
    private NotificationType type;
    private String message;
    private String destination;
    private String sender;
    private String permission;
    private String title;
}
