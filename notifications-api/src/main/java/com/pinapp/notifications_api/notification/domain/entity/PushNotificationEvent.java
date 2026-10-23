package com.pinapp.notifications_api.notification.domain.entity;


import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class PushNotificationEvent {
    private NotificationType type;
    private String message;
    private String destination;
    private String sender;
    private String permission;
    private String title;
}
