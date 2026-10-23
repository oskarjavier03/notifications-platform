package com.pinapp.notifications_client.notification.domain.event;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class NotificationSmsCreatedDomainEvent {
    private String message;
    private String numberCelPhone;
    private NotificationType type;
}
