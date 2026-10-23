package com.pinapp.notifications_api.notification.domain.entity;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class SmsNotificationEvent {
    private NotificationType type;
    private String message;
    private String numberCelPhone;
}
