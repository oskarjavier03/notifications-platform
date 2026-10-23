package com.pinapp.notifications_client.notification.domain.event;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class NotificationEmailCreatedDomainEvent {
    private NotificationType type;
    private String message;
    private String email;
    private String subject;
}
